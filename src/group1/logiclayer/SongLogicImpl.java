/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions, or logic, for communication
 * between the SongServlet and the persist layer.
 * 
 * 
 ***********************************************************/
package group1.logiclayer;

import java.util.ArrayList;
import java.util.List;

import group1.objectlayer.Song;
import group1.persistlayer.SongPersistImpl;

public class SongLogicImpl {
	SongPersistImpl sp = new  SongPersistImpl();
	List<Song> songs = new ArrayList<>();

	public void insert(String songTitle, String artistID, String songPath, String genreID, String local) {
		String insertQuery = "INSERT INTO songs(SongTitle, ArtistID, SongPath, GenreID, `Local`) VALUES ('"+ songTitle +"', "+ artistID +", '"+ songPath +"', "+ genreID +", "+ local +");";
		sp.create(insertQuery);
	}
	
	public void delete(String songID) {
		sp.delete(songID);
	}
	
	public void update() {
		songs = sp.retrieve();
	}
	
	public void modify(String[] params) {
		String aid = params[0];
		for(int i = 1; i < params.length; i++) {
			if(params[i] != null) {
				System.out.println(params[i]);
				if(i == 1) {
					modifySongTitle(aid, params[i]);
				} else if (i == 2) {
					modifyArtistID(aid, params[i]); 
				} else if (i == 3) {
					modifySongPath(aid, params[i]);
				} else {
					modifyLocal(aid, params[i]);
				}
			}
		}
	}
	
	
	private void modifySongTitle(String songID, String songTitle) {
		String updateQuery = "UPDATE songs SET SongTitle = '"+ songTitle +"' WHERE SongID = "+ songID +";";
		sp.update(updateQuery);
	}
	
	private void modifyArtistID(String songID, String artistID) {
		String updateQuery = "UPDATE songs SET ArtistID = '"+ artistID +"' WHERE SongID = "+ songID +";";
		sp.update(updateQuery);
	}
	
	private void modifySongPath(String songID, String path) {
		String updateQuery = "UPDATE songs SET SongPath = '"+ path +"' WHERE SongID = "+ songID +";";
		sp.update(updateQuery);
	}
	
	private void modifyLocal(String songID, String state) {
		String updateQuery = "UPDATE songs SET `Local` = '"+ state +"' WHERE SongID = "+ songID +";";
		sp.update(updateQuery);
	}

	public List<Song> getSongs() {
		return songs;
	}
	
}
