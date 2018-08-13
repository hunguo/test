package org.study.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.study.dao.UserDao;
import org.study.model.User;


@WebServlet("/join")
public class JoinController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		
		request.getRequestDispatcher("/WEB-INF/views/joinForm.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel	=request.getParameter("tel");
		String addr	=request.getParameter("addr");
		
		User user = new User();
		user.setId(id);
		user.setPw(pw);
		user.setName(name);
		user.setTel(tel);
		user.setAddr(addr);
		
		System.out.println("name");
		System.out.println("addr");
		//System.out.println(user);
		UserDao dao = new UserDao();
		if (dao.addUser(user)) {	// 사용자 추가 성공
			request.setAttribute("error", "사용자 추가가 성공했습니다. 로그인 하세요");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "아이디가 중복합니다");
			request.getRequestDispatcher("/WEB-INF/views/joinForm.jsp").forward(request, response);
		}
	}
}
