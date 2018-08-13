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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			session.invalidate();
			//System.out.println("session invalidated...");
			response.sendRedirect(request.getContextPath() + "/index.jsp?logout=success");
		} else {
			request.setAttribute("error", "먼저 로그인 하세요");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}
}
