package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.study.model.User;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		request.setAttribute("name", user.getName());
		request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
	}
}
