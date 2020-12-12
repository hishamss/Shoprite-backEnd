package com.revature.shoprite.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					loginCookie = cookie;
					break;
				}
			}
			
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
//loginCookie.setDomain("ec2-3-101-140-233.us-west-1.compute.amazonaws.com");
				loginCookie.setDomain("localhost");
				loginCookie.setPath("/");
				response.addCookie(loginCookie);
				response.getWriter().append("logged out Successfully");
				response.setStatus(200);
			}
		}else {
			response.getWriter().append("Please Login");
			response.setStatus(401);
		}
		
		
		
	}

}
