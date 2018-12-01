/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This class contains logic to establish a database connection.
 * 
 * 
 ***********************************************************/
package group1.persistlayer;

import java.sql.*;




public class DbAccessImpl  extends DbAccessConfiguration {
	private static Connection myConn;
	private static Statement statement;
	private static ResultSet resultSet;
	
	public DbAccessImpl() {
		try {
			myConn = DriverManager.getConnection(DB_CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
			//System.out.println("Connection successful");
			statement = myConn.createStatement();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
    public static Connection connect() {
    	return myConn;
    }

	public ResultSet retrieve (String query) {
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void executeQuery (String query) {
		int ra = 0;
		try {
			ra = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(ra + " row(s) affected");
	}
	
	public static int create (String query) {
		try {
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static int update (String query) {
		try {
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int delete (String query) {
		try {
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void disconnect() {
		try {
			myConn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
}
