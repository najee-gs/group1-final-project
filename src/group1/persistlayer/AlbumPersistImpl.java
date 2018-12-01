/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions for handling
 * all persist layer function related to Album Data.
 * 
 * 
 ***********************************************************/
package group1.persistlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group1.objectlayer.Album;

public class AlbumPersistImpl {
	private DbAccessImpl dB = new DbAccessImpl();
	private ResultSet rS;
	private String ShowAllQuery = "SELECT albums.AlbumID, albums.AlbumName, artists.ArtistName, albums.ImagePath, albums.PlaylistYT FROM Albums JOIN artists ON albums.ArtistID = artists.ArtistID;";
	
	public void create(String query) {
		System.out.println(query);
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.create(query);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	public void delete(String aid) {
		String deletionStatement = "DELETE FROM albums WHERE albums.AlbumID = " + aid + ";";
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.delete(deletionStatement);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	public List<Album> retrieve() {
		rS = dB.retrieve(ShowAllQuery);
		List<Album> albums = new ArrayList<> ();
		
		try {
			while(rS.next()) {
				albums.add(new Album(rS.getInt("AlbumID"), rS.getString("AlbumName"), rS.getString("ArtistName"), rS.getString("ImagePath"), rS.getString("PlaylistYT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return albums;
	}
	
	public void update(String updateQuery) {
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.update(updateQuery);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
}
