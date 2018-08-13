package org.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.study.model.User;

public class LoginFilter implements Filter{

	FilterConfig config;
	String[] excludedUrls;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		/*System.out.println("url: " + req.getRequestURL());
		System.out.println("uri: " + req.getRequestURI());
		System.out.println("queryString: " + req.getQueryString());*/
		
		String path = req.getRequestURI();
		
		for (String url : excludedUrls) {
			if (path.equals(url)) {	// 인증 필터를 적용할 필요가 없는 예외 URL인 경우
				chain.doFilter(request, response);	
				//System.out.println("예외처리....");
				return;
			} 
		}
	
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		if (req.getQueryString() != null) {
			path = path + "?" + req.getQueryString();
		}
		 
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			req.setAttribute("error", "먼저 로그인 하세요");
			req.setAttribute("orgReqPath", path );
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		
		return;
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;
		String params = config.getInitParameter("excluded");
		excludedUrls = params.split(",");
		String ctxPath = config.getServletContext().getContextPath();
		
		for (int i = 0; i < excludedUrls.length; i++) {
			excludedUrls[i] = ctxPath + excludedUrls[i].trim();
			//System.out.println(excludedUrls[i]);
		}
	}

}
