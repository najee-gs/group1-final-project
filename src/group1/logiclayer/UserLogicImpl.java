/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions, or logic, for communication
 * between the all the Servlets and the persist layer.
 * 
 * 
 ***********************************************************/
package group1.logiclayer;

import java.util.ArrayList;

import group1.objectlayer.User;
import group1.persistlayer.UserPersistImpl;

public class UserLogicImpl {
	private UserPersistImpl up = new UserPersistImpl();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<User> activeUsers;
	
	public void insert(String username, String password, String imagePath, boolean active) {
		String insertQuery = "insert into users (Username, Pass, ImagePath, Active) values ('" + username + "','" + password + "','" + imagePath + "',"+ active +");";
		up.create(insertQuery);
	}
	
	public void insert(String username, String password, boolean active) {
		String insertQuery = "insert into users (Username, Pass, Active) values ('" + username + "','" + password + "'," + active +");";
		up.create(insertQuery);
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void delete(String userID) {
		up.delete(userID);
	}
	
	public ArrayList<User> getActiveUsers() {
		activeUsers = new ArrayList<>();
		for (User u: users) {
			if (u.isActive()) {
				activeUsers.add(u);
			}
		}
		
		return activeUsers;
	}
	
	public void update() {
		users = up.retrieve();
	}
	
	public boolean isValidUser(String username, String password) {
		for (User u: users) {
			if (u.getUserName().matches(username) && u.getPassword().matches(password)) {
				cancelOtherSessions();
				modifyRowToSetActiveUser(u.getUserID());
				return true;
			}
		}
		
		return false;
	}
	
	private void logout(String userID) {
		String modifyQuery = "UPDATE users SET Active = false WHERE UserID = "+ userID +";";
		
		up.update(modifyQuery);
	}
	
	public void logoutByUsername(String username) {
		activeUsers = new ArrayList<> (getActiveUsers());
		
		for (User u: activeUsers) {
			System.out.println(u.getUserName());
			if (u.getUserName().matches(username)) {
				logout(u.getUserID());
			}
		}
	}
	
	public void modifyRowToSetActiveUser(String userID) {
		String modifyQuery = "UPDATE users SET Active = true WHERE UserID = "+ userID +";";
		up.update(modifyQuery);
		
	}
	
	private void cancelOtherSessions() {
		for (User u: users) {
			if (u.isActive()) {
				logout(u.getUserID());
			}
		}
	}
}
