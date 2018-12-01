/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions for handling
 * all persist layer function related to User Data.
 * 
 * 
 ***********************************************************/
package group1.persistlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import group1.objectlayer.User;

public class UserPersistImpl {
	private DbAccessImpl dB = new DbAccessImpl();
	private ResultSet rS;
	ArrayList<User> users = new ArrayList<User>();
	private String showAllUsers = "SELECT * FROM Users";
	
	public void create(String query) {
		System.out.println(query);
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.create(query);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	
	public void delete(String userID) {
		String deletionStatement = "delete from users where users.UserID = " + userID + ";";
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.delete(deletionStatement);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	public ArrayList<User> retrieve() {
		rS = dB.retrieve(showAllUsers);
		users.clear();
		try {
			while(rS.next()) {
				users.add(new User(rS.getString("UserID"), rS.getString("Username"), rS.getString("Pass"), rS.getString("ImagePath"), rS.getBoolean("Active")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void update(String updateQuery) {
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.update(updateQuery);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
}
