package org.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.study.model.Application;
import org.study.model.User;

public class ApplyDao {

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
	
	
	/*
	 * 1 --> success
	 * -1 --> general failure
	 * -99 --> 이미 등록 한 상황
	 * 
	 */
	public int submitApplication(Application application) {
		Connection conn = getConnection();
		PreparedStatement ps0 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result;
		
		if (conn != null && application != null) {
			String preSql = "select * from USERPLAN_TABLE " + 
					" where userid=? and plan=? and planDESC=?";
			
			String sql = "insert into USERPLAN_TABLE (id, userId, PLAN, PLANDESC) " + 
					"values (apply_id_seq.nextval, ?, ?, ?)";
			try {
				ps0 = conn.prepareStatement(preSql);
				ps0.setString(1, application.getUserId());
				ps0.setString(2, application.getPLAN());
				ps0.setString(3, application.getPLANDESC());
			
				
				rs = ps0.executeQuery();
				
				if (rs.next()) {
					return -99;
				}
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, application.getUserId());
				ps.setString(2, application.getPLAN());
				ps.setString(3, application.getPLANDESC());
			
				
				result = ps.executeUpdate();
				
				if (result == 0) {
					return -1;
				} else {
					return 1;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {		
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (ps0 != null) {
					try {
						ps0.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (rs != null) {
					try {
						rs.close();
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
	
	public Application[] getApplications(String userId) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (conn != null) {			
			String sql = "select * from USERPLAN_TABLE" + 
					" where userid=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, userId);
				
				rs = ps.executeQuery();
				
				List<Application> list = new ArrayList<>();
				while (rs.next()) {
					Application application = new Application(userId);
					
					application.setPLAN(rs.getString(3));
					application.setPLANDESC(rs.getString(4));
								
					list.add(application);
				}
				
				return list.toArray(new Application[0]);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {		
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (rs != null) {
					try {
						rs.close();
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
	
	public boolean deleteApplication(int id) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result;
		
		if (conn != null) {			
			String sql = "delete * from USERPLAN_TABLE where id=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				result = ps.executeUpdate();
				
				if (result > 0) {
					return true;
				} else {
					return false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {		
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