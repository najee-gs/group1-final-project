/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions for handling
 * all persist layer function related to Genre Data.
 * 
 * 
 ***********************************************************/
package group1.persistlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group1.objectlayer.Genre;

public class GenrePersistImpl {
	private DbAccessImpl dB = new DbAccessImpl();
	private ResultSet rS;
	private List<Genre> genres = new ArrayList<>();
	private String getAllGenres = "SELECT * FROM genres";
	private String query = "SELECT genres.GenreID, genres.GenreName, COUNT(genres.GenreName) as AlbumCount, genres.ImagePath FROM genres JOIN albums ON albums.genreID = genres.GenreID\r\n" + 
			"group by genres.GenreName;";
	
	public void create(String query) {
		System.out.println(query);
		
		int res = DbAccessImpl.create(query);
		System.out.println(res + "row(s) affected");
	}
	
	
	public void delete(String gid) {
		String deletionStatement = "DELETE FROM genres WHERE GenreID = "+ gid +";";
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.delete(deletionStatement);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
	
	public List<Genre> retrieve() {
		rS = dB.retrieve(getAllGenres);
		
		try {
			while(rS.next()) {
				genres.add(new Genre(rS.getInt("GenreID"), rS.getString("GenreName"), rS.getString("ImagePath") ));
			}
			
			rS = dB.retrieve(query);
			while(rS.next()) {
				for (Genre g: genres) {
					if(g.getId() == rS.getInt("GenreID")) {
						g.setAlbumCount(rS.getInt("AlbumCount"));
					}
				}
			}	
			
			String query2 = "SELECT songs.GenreID FROM songs;";
			rS = dB.retrieve(query2);
			
			while(rS.next()) {
				for (Genre g: genres) {
					if(g.getId() == rS.getInt("GenreID")) {
						g.incrementSinglesCount();
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return genres;
	}
	
	public void update(String updateQuery) {
		int rowsAffected = 0;
		
		rowsAffected = DbAccessImpl.update(updateQuery);
		
		System.out.println(rowsAffected + " row(s) affected.");
	}
}
