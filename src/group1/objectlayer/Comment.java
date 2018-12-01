/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Object class models Comment data.
 * 
 * 
 ***********************************************************/
package group1.objectlayer;

public class Comment {
	private String id;
	private String content;
	private String userName;
	private String artistID;
	private String albumID;
	private String songID;
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getArtistID() {
		return artistID;
	}


	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}


	public String getAlbumID() {
		return albumID;
	}


	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}


	public String getSongID() {
		return songID;
	}


	public void setSongID(String songID) {
		this.songID = songID;
	}


	public Comment(String id, String content, String userID) {
		this.id = id;
		this.content = content;
		this.userName = userID;
	}
	
}
