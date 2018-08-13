package org.study.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.study.model.User;
import org.study.sec.PasswordAuthentication;

public class UserDao {
	private Connection conn;
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CALENDAR";
	private static final String PW = "1111";
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(URL, USER, PW);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public boolean addUser(User user) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result;
		
		if (conn != null && user != null) {
			String sql = "insert into USERMEMBER(id, pw, name, tel, addr) " +
					" values (?, ?, ?, ?, ?)";
			try {
				PasswordAuthentication passAuth = new PasswordAuthentication();
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getId());
				ps.setString(2, passAuth.hash(user.getPw().toCharArray()));
				ps.setString(3, user.getName());
				ps.setString(4, user.getTel());
				ps.setString(5, user.getAddr());


				
				result = ps.executeUpdate();
				
				if (result == 0) {
					return false;
				} else {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	public User authenticateUser(String id, String pw) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (conn != null) {
			String sql = "select id, pw, name from USERMEMBER where id=?";
			try {
				PasswordAuthentication passAuth = new PasswordAuthentication();
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					if (passAuth.authenticate(pw.toCharArray(), rs.getString(2))) {
						String name = rs.getString(3);
						User user = new User();
						user.setId(id);
						user.setPw(pw);
						user.setName(name);
						
						return user;
					} else {
						return null;
					}
				} else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
			
		return null;
	}

	public int existUserId(String id) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (conn != null) {
			String sql = "select * from USERMEMBER where id=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					return 1;
				} else {
					return 0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return -1;
	}
	public boolean getProfile(User user) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (conn != null && user != null) {
			String sql =  "select * from USERMEMBER where id=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getId());
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					user.setName(rs.getString(1));
					user.setAddr(rs.getString(2));
					user.setTel(rs.getString(3));
					
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return false;
	}
}