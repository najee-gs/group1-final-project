/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Object class models Artist data.
 * 
 * 
 ***********************************************************/
package group1.objectlayer;


public class Artist {
	private int id;
	private String name;
	private String debut;
	private String imagePath;
	private String insta;
	private String instaHeader = "https://www.instagram.com/";

	public Artist(int id, String name, String debut, String imagePath) {
		this.id = id;
		this.name = name;
		this.debut = debut;
		this.imagePath = formatImagePath(imagePath);
		
	}
	
	public Artist(int id, String name, String debut, String imagePath, String insta) {
		this.id = id;
		this.name = name;
		this.debut = debut;
		this.imagePath = formatImagePath(imagePath);
		
		if(!insta.contains(instaHeader)) {
			this.insta = instaHeader + insta;
		} else {
			this.insta = insta;
		}
		
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

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getInsta() {
		return insta;
	}

	public void setInsta(String insta) {
		this.insta = insta;
	}
	
	private String formatImagePath(String imagePath) {
		String realPath = "";
		if(imagePath.isEmpty()) {
			realPath = "http://localhost:8080/MusicLibrary/resources/artist-imgs/default.png";
		} else if (!imagePath.contains("http")) {
			realPath = "http://localhost:8080/MusicLibrary/resources/artist-imgs/" + imagePath;
		} else {
			realPath = imagePath;
		}
		
		return realPath;
		
	}
	
	
}
