/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions for handling
 * all persist layer function related to Comment Data.
 * 
 * 
 ***********************************************************/
package group1.persistlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import group1.objectlayer.Comment;

public class CommentPersistImpl {
	private DbAccessImpl dB = new DbAccessImpl();
	private ResultSet rS;
	
	public void create(String query) {
		System.out.println(query);
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.create(query);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	
	public void delete(String commentID) {
		String deletionStatement = "DELETE FROM comments WHERE commentID = "+ commentID +";";
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.delete(deletionStatement);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	public ResultSet retrieve (String query) {
		return dB.retrieve(query);
	}
	
	public ArrayList<Comment> retrieveAlbumComments(String query) {
		ArrayList<Comment> albumComments = new ArrayList<>();
		
		try {
			rS = dB.retrieve(query);
			while (rS.next()) {
				Comment c = new Comment(rS.getString("CommentID"),rS.getString("Content"),rS.getString("UserName"));
				c.setAlbumID(rS.getString("AlbumID"));
				albumComments.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return albumComments;
		
	}
	
	public void update(String updateQuery) {
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.update(updateQuery);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
}
