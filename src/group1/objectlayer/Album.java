/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Object class models Album data.
 * 
 * 
 ***********************************************************/
package group1.objectlayer;

public class Album {
	private int id;
	private String name;
	private String artist;
	private String coverPath;
	private String playlistPath;
	
	public Album(int id, String name, String artist, String coverPath, String playlistPath) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		if (!coverPath.contains("http")) {
			this.coverPath = "http://localhost:8080/MusicLibrary/resources/album-imgs/" + coverPath;
		} else {
			this.coverPath = coverPath;
		}
		this.playlistPath = playlistPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getPlaylistPath() {
		return playlistPath;
	}

	public void setPlaylistPath(String playlistPath) {
		this.playlistPath = playlistPath;
	}
	
	
}
