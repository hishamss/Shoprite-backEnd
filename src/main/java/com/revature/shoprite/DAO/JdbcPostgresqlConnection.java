package com.revature.shoprite.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.shoprite.model.Employee;
import com.revature.shoprite.utilities.DAOUtilities;

public class JdbcPostgresqlConnection {

	public List<Employee> viewAll() {

		try (Connection connection = DAOUtilities.getConnection()) {

			System.out.println("Connected to PostgreSQL server successfully");

			String sql = "SELECT * from employees";

			Statement statement = connection.createStatement();

			ResultSet results = statement.executeQuery(sql);
			List<Employee> employees = new ArrayList<Employee>();
			while (results.next()) {
				Employee employee = new Employee();

				employee.setId(results.getInt("id"));
				employee.setNames(results.getString("names"));
				employee.setJobs(results.getString("jobs"));
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean change(int id1, String job1) {
		try (Connection connection = DAOUtilities.getConnection()) {

			System.out.println("Connected to PostgreSQL server successfully");

			String sql = "UPDATE employees set jobs =? where id =?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, job1);
			statement.setInt(2, id1);

			if (statement.executeUpdate() != 0) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		// SQL select based on ID number, change job to equal new job
	}

	public boolean fire(int id1) {
		try (Connection connection = DAOUtilities.getConnection()) {

			System.out.println("Connected to PostgreSQL server successfully");

			String sql = "DELETE from employees WHERE id=?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, id1);

			if (statement.executeUpdate() != 0) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		// SQL select based on ID number, delete the whole row
	}

	public boolean addEmployee(String name, String job) {
		try (Connection connection = DAOUtilities.getConnection()) {

			String sql = "INSERT INTO employees (names, jobs) VALUES (?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, name);
			statement.setString(2, job);

			if (statement.executeUpdate() != 0) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
