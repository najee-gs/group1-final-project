/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions for handling
 * all persist layer function related to Song Data.
 * 
 * 
 ***********************************************************/
package group1.persistlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group1.objectlayer.Song;

public class SongPersistImpl {
	private DbAccessImpl dB = new DbAccessImpl();
	private ResultSet rS;
	List<Song> songs = new ArrayList<>();
	String query = "SELECT songs.SongID, songs.SongTitle, artists.ArtistName, songs.SongPath FROM songs JOIN artists ON songs.ArtistID = artists.ArtistID;";
	String query2 = "SELECT songs.SongID, songs.SongTitle, artists.ArtistName, songs.SongPath, songs.Local FROM songs JOIN artists ON songs.ArtistID = artists.ArtistID;";
	
	public void create(String insertQuery) {
		System.out.println(insertQuery);
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.create(insertQuery);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	
	public void delete(String songID) {
		String deletionStatement = "DELETE FROM songs WHERE songs.SongID = "+ songID +";";
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.delete(deletionStatement);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	public List<Song> retrieve() {
		rS = dB.retrieve(query2);
		
		try {
			while(rS.next()) {
				songs.add(new Song(rS.getInt("SongID"), rS.getString("SongTitle"), rS.getString("ArtistName"), rS.getString("SongPath"), rS.getString("Local")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return songs;
	}
	
	public void update(String updateQuery) {
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.update(updateQuery);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
}
