/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions for handling
 * all persist layer function related to Artist Data.
 * 
 * 
 ***********************************************************/
package group1.persistlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group1.objectlayer.Artist;

public class ArtistPersistImpl {
	private DbAccessImpl dB = new DbAccessImpl();
	private ResultSet rS;
	private List<Artist> artists = new ArrayList<>();
	private String showAllQuery = "SELECT * FROM Artists";
	
	public void create(String query) {
		System.out.println(query);
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.create(query);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	
	public void delete(String artistID) {
		String deletionStatement = "DELETE FROM artists WHERE artistID = "+ artistID +";";
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.delete(deletionStatement);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	public List<Artist> retrieve() {
		rS = dB.retrieve(showAllQuery);
		
		try {
			while(rS.next()) {
				artists.add(new Artist(rS.getInt("ArtistID"), rS.getString("ArtistName"), rS.getString("Debut"), rS.getString("ImagePath"), rS.getString("Instagram")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return artists;
	}
	
	public void update(String updateQuery) {
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.update(updateQuery);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
}
