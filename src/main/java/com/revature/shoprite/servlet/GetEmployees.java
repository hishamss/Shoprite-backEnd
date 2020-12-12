package com.revature.shoprite.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.shoprite.DAO.JdbcPostgresqlConnection;
import com.revature.shoprite.model.Employee;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/employees")
public class GetEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String currentUser = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					currentUser = cookie.getValue();
					break;
				}

			}
			if (!currentUser.isEmpty() && currentUser != null) {
				JdbcPostgresqlConnection connection = new JdbcPostgresqlConnection();
				List<Employee> employees = connection.viewAll();
				String jsonString = objectMapper.writeValueAsString(employees);
				response.getWriter().append(jsonString);
				response.setContentType("application/json");
				response.setStatus(200);
			} else {
				response.getWriter().append("Please Login");
				response.setStatus(401);
			}

		} else {
			System.out.println("user is not logged in");
			response.getWriter().append("Please Login");
			response.setStatus(401);
		}

	}

}
