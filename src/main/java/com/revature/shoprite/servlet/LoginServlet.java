package com.revature.shoprite.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		if (username.equals("user") && password.equals("pass")) {
			Cookie loginCookie = new Cookie("username", username);
			loginCookie.setMaxAge(60*60);
			//loginCookie.setDomain("ec2-3-101-140-233.us-west-1.compute.amazonaws.com");
			loginCookie.setDomain("localhost");
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			response.getWriter().append(username);
			response.setStatus(200);
			
		}

		/*
		 * 'admin' Does everything that user can do, but
		 * also gain the ability to select an employee by name and change their job.
		 */

		else if (username.equals("admin") && password.equals("pass")) {
			Cookie loginCookie = new Cookie("username", username);
			loginCookie.setMaxAge(60*60);
//loginCookie.setDomain("ec2-3-101-140-233.us-west-1.compute.amazonaws.com");
			loginCookie.setDomain("localhost");
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			response.getWriter().append(username);
			response.setStatus(200);
		} else {
			response.getWriter().append("Incorrect Username/Paswowrd!!");
			response.setStatus(401);
		}
	}
}
