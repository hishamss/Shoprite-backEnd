package com.revature.shoprite.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.shoprite.DAO.JdbcPostgresqlConnection;

/**
 * Servlet implementation class ChangeEmployeeRole
 */
@WebServlet("/change")
public class ChangeEmployeeRole extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				if (currentUser.equals("admin")) {
					String id = request.getParameter("id");
					String newRole = request.getParameter("newRole");
					if (!id.isEmpty() && !newRole.isEmpty()) {
						JdbcPostgresqlConnection connection = new JdbcPostgresqlConnection();
						if (connection.change(Integer.parseInt(id), newRole)) {
							response.getWriter().append("Employee's role has been changed");
							response.setStatus(200);
						} else {
							response.getWriter().append("smth went wrong; try again !");
							response.setStatus(500);
						}

					} else {
						response.getWriter().append("id & new role are required!!");
						response.setStatus(422);
					}
				} else {
					response.getWriter().append("Not allowed to add");
					response.setStatus(401);
				}

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
