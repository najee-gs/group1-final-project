/***********************************************************
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This document contains Java functions, or logic, for communication
 * between the ArtistServlet and the persist layer as well as other Servlets that
 * utilize data of type Artist.
 * 
 * 
 ***********************************************************/
package group1.logiclayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import group1.objectlayer.Artist;
import group1.persistlayer.ArtistPersistImpl;


public class ArtistLogicImpl {
	private ArtistPersistImpl ap = new ArtistPersistImpl();
	private List<Artist> artists = new ArrayList<>();
	private List<Artist> artistsAZ;
	
	public void insert(String name, String debut, String imagePath, String instagram) {
		String query = "insert into artists(ArtistName, Debut, ImagePath, Instagram) values ('" + name + "',"+ debut + ",'" + imagePath + "','" + instagram +"');";
		ap.create(query);
	}
	
	public void delete(String artistID) {
		ap.delete(artistID);

	}
	
	public void update() {
		artists = ap.retrieve();
	}

	public List<Artist> getArtists() {
		return artists;
	}
	
	public List<Artist> getAristsSortedByNameAZ() {
		artistsAZ = new ArrayList<Artist>(artists);
		Collections.sort(artistsAZ, new Comparator<Artist>() {
			public int compare (Artist a1, Artist a2) {
				String a1Name = a1.getName().toUpperCase();
				String a2Name = a2.getName().toUpperCase();
				return Integer.valueOf(a1Name.compareTo(a2Name));
			}
		});
		return artistsAZ;
	}
	
	
	public void modify(String[] params) {
		String aid = params[0];
		for(int i = 1; i < params.length; i++) {
			if(params[i] != null) {
				System.out.println(params[i]);
				if(i == 1) {
					modifyArtistName(aid, params[i]);
				} else if (i == 2) {
					modifyArtistDebut(aid, params[i]); 
				} else if (i == 3) {
					modifyImagePath(aid, params[i]);
				} else {
					modifyInstagram(aid, params[i]);
				}
			}
		}
	}
	
	private void modifyInstagram(String aid, String instagram) {
		String updateQuery = "UPDATE artists SET Instagram = '"+ instagram +"' WHERE artists.ArtistID = "+ aid +"; ";
		ap.update(updateQuery);
		
	}
	
	private void modifyImagePath(String aid, String imagePath) {
		String updateQuery = "UPDATE artists SET ImagePath = '"+ imagePath +"' WHERE artists.ArtistID = "+ aid +"; ";
		ap.update(updateQuery);
		
	}
	
	private void modifyArtistDebut(String aid, String debut) {
		int d = Integer.parseInt(debut);
		String updateQuery = "UPDATE artists SET Debut = "+ d +" WHERE artists.ArtistID = "+ aid +"; ";
		ap.update(updateQuery);
		
	}
	
	private void modifyArtistName(String aid, String name) {
		String updateQuery = "UPDATE artists SET ArtistName = '"+ name +"' WHERE artists.ArtistID = "+ aid +"; ";
		ap.update(updateQuery);
	}
	
	
}
