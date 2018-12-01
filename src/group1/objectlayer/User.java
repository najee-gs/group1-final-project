/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Object class models User data.
 * 
 * 
 ***********************************************************/
package group1.objectlayer;

public class User {
	private String userID;
	private String userName;
	private String password;
	private String imagePath;
	private boolean active;
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String password, boolean active) {
		this.userName = userName;
		this.password = password;
		this.active = active;
	}
	
	public User(String userName, String password, String imagePath, boolean active) {
		this.userName = userName;
		this.password = password;
		this.imagePath = imagePath;
		this.active = active;
	}
	
	public User(String userID, String userName, String password, String imagePath, boolean active) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.imagePath = imagePath;
		this.active = active;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean isUser(String user, String pass) {
		boolean isUser = false;
		if (user.equals(userName) && pass.equals(password)) {
			isUser = true;
		}
		
		return isUser;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
