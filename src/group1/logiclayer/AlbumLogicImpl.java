/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions, or logic, for communication
 * between the AlbumServlet and the persist layer as well as other Servlets that
 * utilize data of type Album.
 * 
 * 
 ***********************************************************/
package group1.logiclayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import group1.objectlayer.Album;
import group1.persistlayer.AlbumPersistImpl;


public class AlbumLogicImpl {
	private AlbumPersistImpl alp = new AlbumPersistImpl();
	private List<Album> albums = new ArrayList<>();
	private List<Album> byArtistAZ;
	private List<Album> byAlbumTitle;
	
	/**
	 * insert : this function handles insertion by receiving instructions from the servlet and relaying the information to the persist layer
	 * @param name
	 * @param artist
	 * @param imagePath
	 * @param playlist
	 * @param genre
	 */
	public void insert (String name, String artist, String imagePath, String playlist, String genre) {
		String query = "insert into albums(AlbumName, ArtistID, ImagePath, PlaylistYT, GenreID) values('" + name +"'," + artist +", '" + imagePath +"', '" + playlist +"', "+ genre +");";
		alp.create(query);
	}
	
	/**
	 * delete: this function handles deletion by receiving instructions from the servlet and relaying the information to the persist layer
	 * @param aid
	 */
	public void delete (String aid) {
		alp.delete(aid);
	}
	
	/**
	 * update: this function gets album data from the persist layer
	 */
	public void update () {
		albums = alp.retrieve();
	}
	
	/**
	 * modify: this function handles data modifying by recieving instructions from the servlet and calling the appropriate function for
	 * communicating with the persist layer.
	 * @param params
	 */
	public void modify(String[] params) {
		String aid = params[0];
		for(int i = 1; i < params.length; i++) {
			if(params[i] != null) {
				System.out.println(params[i]);
				if(i == 1) {
					modifyAlbumName(aid, params[i]);
				} else if (i == 2) {
					modifyArtist(aid, params[i]); 
				} else if (i == 3) {
					modifyImagePath(aid, params[i]);
				} else if (i == 4) {
					modifyPlaylistPath(aid, params[i]);
				} else {
					modifyGenreID(aid, params[i]);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param albumID
	 * @param albumName
	 */
	private void modifyAlbumName(String albumID, String albumName) {
		String updateQuery = "UPDATE  albums SET albums.AlbumName = '"+ albumName +"' WHERE albums.AlbumID = "+ albumID +";";
		alp.update(updateQuery);
	}
	
	/**
	 * modifyArtist: called by modifyAlbumName 
	 * @param albumID
	 * @param artist
	 */
	private void modifyArtist(String albumID, String artist) {
		String updateQuery = "UPDATE  albums SET albums.ArtistID = '"+ artist +"' WHERE albums.AlbumID = "+ albumID +";";
		alp.update(updateQuery);
	}
	
	/**
	 * modifyImagePath: called by modifyAlbumName 
	 * @param albumID
	 * @param imagePath
	 */
	private void modifyImagePath(String albumID, String imagePath) {
		String updateQuery = "UPDATE  albums SET albums.ImagePath = '"+ imagePath +"' WHERE albums.AlbumID = "+ albumID +";";
		alp.update(updateQuery);
	}
	
	/**
	 * modifyPlaylistPath: called by modifyAlbumName 
	 * @param albumID
	 * @param playlistPath
	 */
	private void modifyPlaylistPath(String albumID, String playlistPath) {
		String updateQuery = "UPDATE  albums SET albums.PlaylistYT = '"+ playlistPath +"' WHERE albums.AlbumID = "+ albumID +";";
		alp.update(updateQuery);
	}
	
	/**
	 * modifyGenreID: called by modifyAlbumName 
	 * @param albumID
	 * @param genreID
	 */
	private void modifyGenreID(String albumID, String genreID) {
		String updateQuery = "UPDATE  albums SET albums.GenreID = '"+ genreID +"' WHERE albums.AlbumID = "+ albumID +";";
		alp.update(updateQuery);
	}

	/**
	 * getAlbums: returns a list of Albums
	 * @return
	 */
	public List<Album> getAlbums() {
		return albums;
	}
	
	
	/**
	 * getAlbumsSortedByArtistAZ: returns a sorted list of Albums
	 * @return
	 */
	public List<Album> getAlbumsSortedByArtistAZ() {
		byArtistAZ = new ArrayList<Album>(albums);
		Collections.sort(byArtistAZ, new Comparator<Album>() {
			public int compare (Album a1, Album a2) {
				return Integer.valueOf(a1.getArtist().compareTo(a2.getArtist()));
			}
		});
		
		return byArtistAZ;
	}
	
	/**
	 * getAlbumsSortedByTitleAZ: returns a sorted list of Albums
	 * @return
	 */
	public List<Album> getAlbumsSortedByTitleAZ() {
		byAlbumTitle = new ArrayList<Album>(albums);
		Collections.sort(byAlbumTitle, new Comparator<Album>() {
			public int compare (Album a1, Album a2) {
				String a1AlbumName = a1.getName().toUpperCase();
				String a2AlbumName = a2.getName().toUpperCase();
				return Integer.valueOf(a1AlbumName.compareTo(a2AlbumName));
			}
		});
		
		return byAlbumTitle;
	}
	
	
}
