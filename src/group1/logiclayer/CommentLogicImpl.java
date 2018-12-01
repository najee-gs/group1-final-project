/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions, or logic, for communication
 * between the all other Servlets that utilize Comment data and the persist layer.
 * 
 * 
 ***********************************************************/
package group1.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import group1.persistlayer.CommentPersistImpl;
import group1.objectlayer.Comment;

public class CommentLogicImpl {
	private ResultSet rS;
	private CommentPersistImpl comp = new CommentPersistImpl();
	private ArrayList<Comment> artistComments = new ArrayList<>();
	private ArrayList<Comment> albumComments = new ArrayList<>();
	
	public void insert(String userID, String content, String targetID, String target) {
		String query = "";
		
		if (target.matches("artist")) {
			query = "insert into comments(Content, UserID, ArtistID) values ('" + content + "',"+ userID + "," + targetID + ");";
		} else if (target.matches("album")) {
			query = "insert into comments(Content, UserID, AlbumID) values ('" + content + "',"+ userID + "," + targetID + ");";
		} else if (target.matches("song")) {
			query = "insert into comments(Content, UserID, SongID) values ('" + content + "',"+ userID + "," + targetID + ");";
		}
	
		comp.create(query);
	}
	
	public void delete (String commentID) {
		comp.delete(commentID);
	}
	
	
	public void update () {
		String getAllArtistComments = "SELECT CommentID, UserName, Content, ArtistID FROM comments JOIN users ON users.UserID where (users.UserID = comments.UserID) AND (ArtistID IS NOT NULL);\r\n" + 
				"";
		String getAllAlbumComments = "SELECT CommentID, UserName, Content, AlbumID FROM comments JOIN users ON users.UserID where (users.UserID = comments.UserID) AND (AlbumID IS NOT NULL);\r\n" + 
				"";
		
		try {
			rS = comp.retrieve(getAllArtistComments);
			while (rS.next()) {
				Comment c = new Comment(rS.getString("CommentID"),rS.getString("Content"),rS.getString("UserName"));
				c.setArtistID(rS.getString("ArtistID"));
				artistComments.add(c);
			}
			
			rS = comp.retrieve(getAllAlbumComments);
			while (rS.next()) {
				Comment c = new Comment(rS.getString("CommentID"),rS.getString("Content"),rS.getString("UserName"));
				c.setAlbumID(rS.getString("AlbumID"));
				albumComments.add(c);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		
	}

	public ArrayList<Comment> getArtistComments() {
		return artistComments;
	}

	public void setArtistComments(ArrayList<Comment> artistComments) {
		this.artistComments = artistComments;
	}

	public ArrayList<Comment> getAlbumComments() {
		return albumComments;
	}

	public void setAlbumComments(ArrayList<Comment> albumComments) {
		this.albumComments = albumComments;
	}

}
