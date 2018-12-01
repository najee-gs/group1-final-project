/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions, or logic, for communication
 * between the GenreServlet and the perist layer as well as other Servlets that
 * utilize data of type Genre.
 * 
 * 
 ***********************************************************/
package group1.logiclayer;

import java.util.ArrayList;
import java.util.List;

import group1.objectlayer.Genre;
import group1.persistlayer.GenrePersistImpl;

public class GenreLogicImpl {
	private GenrePersistImpl gp = new GenrePersistImpl();
	private List<Genre> genres = new ArrayList<>();
	
	public void update() {
		genres = gp.retrieve();
	}
	
	public void delete (String gid) {
		gp.delete(gid);
	}
	
	public void insert(String name, String imagePath) {
		String query = "INSERT INTO genres(GenreName, ImagePath) VALUES ('"+name+"','"+imagePath+"');";
		gp.create(query);
	}

	public List<Genre> getGenres() {
		return genres;
	}
}
