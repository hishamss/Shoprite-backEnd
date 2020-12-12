package com.revature.shoprite.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;



//Class to implement database connection
public class DAOUtilities {

	
	  private static final String URL = "jdbc:postgresql://localhost:5432/Shoprite"; // Env Variable for DB URL 
	  private static final String CONNECTION_USERNAME = System.getenv("DB_USERNAME"); // Env Variable for DB username 
	  private static final String CONNECTION_PASSWORD = System.getenv("DB_PASSWORD"); // Env Variable for DB password
	  //////////////////////////// local variables on Ec2 instance/////
	  //private static final String URL = System.getenv("DB_URL_shoprite"); // Env Variable for DB URL 
	  //private static final String CONNECTION_USERNAME = System.getenv("DB_USERNAME"); // Env Variable for DB username 
	  //private static final String CONNECTION_PASSWORD = System.getenv("DB_PASSWORD"); // Env Variable for DB password
	  //final static Logger logger = Logger.getLogger(DAOUtilities.class);	
	// PostgreSQL on docker
	//private static final String URL_DOCKER = System.getenv("URL_DOCKER"); // Env Variable for DB URL
	//private static final String CONNECTION_USERNAME_DOCKER = System.getenv("CONNECTION_USERNAME_DOCKER"); // Env Variable for DB username
	//private static final String CONNECTION_PASSWORD_DOCKER = System.getenv("CONNECTION_PASSWORD_DOCKER"); // Env Variable for DB password
	
	private static Connection connection;

	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
//				logger.debug("Could not register driver!");
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			// PostgreSQL on Docker
			//connection = DriverManager.getConnection(URL_DOCKER, CONNECTION_USERNAME_DOCKER, CONNECTION_PASSWORD_DOCKER);
		}

		// If connection was closed then retrieve a new connection
		if (connection.isClosed()) {
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			// PostgreSQL on Docker
			//connection = DriverManager.getConnection(URL_DOCKER, CONNECTION_USERNAME_DOCKER, CONNECTION_PASSWORD_DOCKER);
		}
		return connection;
	}

}