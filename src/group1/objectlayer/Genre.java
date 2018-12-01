/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Object class models Genre data.
 * 
 * 
 ***********************************************************/
package group1.objectlayer;

public class Genre {
	private int id;
	private int albumCount;
	private int singlesCount;
	private String name;
	private String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Genre(int id, String name, int albumCount, int singlesCount, String imagePath) {
		this.id = id;
		this.albumCount = albumCount;
		this.singlesCount = singlesCount;
		this.name = name;
		if (!imagePath.contains("http")) {
			this.imagePath = "http://localhost:8080/MusicLibrary/resources/genre-imgs/" + imagePath;
		} else {
			this.imagePath = imagePath;
		}
	}
	
	public Genre(int id, String name, int albumCount, int singlesCount) {
		this.id = id;
		this.albumCount = albumCount;
		this.singlesCount = singlesCount;
		this.name = name;
	}
	
	public Genre(int id, String name, int albumCount, String imagePath) {
		this.id = id;
		this.albumCount = albumCount;
		this.name = name;
		if (!imagePath.contains("http")) {
			this.imagePath = "http://localhost:8080/MusicLibrary/resources/genre-imgs/" + imagePath;
		} else {
			this.imagePath = imagePath;
		}
	}
	
	public Genre(int id, String name, String imagePath) {
		this.id = id;
		this.name = name;
		if (!imagePath.contains("http")) {
			this.imagePath = "http://localhost:8080/MusicLibrary/resources/genre-imgs/" + imagePath;
		} else {
			this.imagePath = imagePath;
		}
	}
	
	public Genre(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public int getAlbumCount() {
		return albumCount;
	}

	public int getSinglesCount() {
		return singlesCount;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAlbumCount(int albumCount) {
		this.albumCount = albumCount;
	}

	public void setSinglesCount(int singlesCount) {
		this.singlesCount = singlesCount;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void incrementSinglesCount() {
		singlesCount++;
	}
	
	
}
