/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Object class models Song data.
 * 
 * 
 ***********************************************************/
package group1.objectlayer;

public class Song {
	private int id;
	private String name;
	private String artist;
	private String path;
	private String local;
	
	public Song(int id, String name, String artist, String path) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		if (!path.contains("http")) {
			this.path = "http://localhost:8080/MusicLibrary/resources/songs/" + path;
		} else {
			this.path = formatPath(path);
		}
	}
	
	public Song(int id, String name, String artist, String path, String local) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		if (!path.contains("http")) {
			this.path = "http://localhost:8080/MusicLibrary/resources/songs/" + path;
		} else {
			this.path = formatPath(path);
		}
		this.local = local;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	private String formatPath(String path) {
		String newPath = path.replace("watch?v=", "embed/");
		newPath = newPath.concat("?rel=0");
		return newPath;
	}
	
	
}
