package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.study.dao.UserDao;
import org.study.model.User;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String orgPath = request.getParameter("orgReqPath");
		//System.out.println("orgPath: " + orgPath);
				UserDao dao = new UserDao();
				
				User user = dao.authenticateUser(id, pw);
				// 인증
				if (user != null) {	// 인증 성공
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					if (orgPath.length() == 0) {
						response.sendRedirect(request.getContextPath() + "/");
					} else {
						response.sendRedirect(orgPath);
					}
					
				} else {
					request.setAttribute("error", "주어진 정보가 맞지않습니다.");
					request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				}
				
		}
}


