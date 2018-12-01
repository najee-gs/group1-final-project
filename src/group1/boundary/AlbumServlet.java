/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Servlet class deals with Album data.
 * 
 * 
 ***********************************************************/
package group1.boundary;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import group1.logiclayer.AlbumLogicImpl;
import group1.logiclayer.ArtistLogicImpl;
import group1.logiclayer.CommentLogicImpl;
import group1.logiclayer.GenreLogicImpl;
import group1.logiclayer.UserLogicImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class AlbumServlet
 */
@WebServlet("/AlbumServlet")
public class AlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
	
	private String templateDir = "/WEB-INF";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.27) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_28);
		
		
		File file = new File(getServletContext().getRealPath(templateDir));
		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		try {
			cfg.setDirectoryForTemplateLoading(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		cfg.setLogTemplateExceptions(false);

		// Wrap unchecked exceptions thrown during template processing into TemplateException-s.
		cfg.setWrapUncheckedExceptions(true);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String templateName = "/templates/albums.ftl";
		Template template = cfg.getTemplate(templateName);
		String option = request.getParameter("option");
		AlbumLogicImpl albli = new AlbumLogicImpl();
		ArtistLogicImpl ali = new ArtistLogicImpl();
		GenreLogicImpl gli = new GenreLogicImpl();
		UserLogicImpl uli = new UserLogicImpl();
		CommentLogicImpl cli = new CommentLogicImpl();
		Writer out = response.getWriter();
		Map<String, Object> root = new HashMap<>();
		
		if (option != null && option.matches("delete")) {
			String aid = request.getParameter("albumID");
			albli.delete(aid);
		} else if (option != null && option.matches("insert")) {
			String name = request.getParameter("albumName");
			String artist = request.getParameter("artistName");
			String imagePath = request.getParameter("imagePath");
			String playlist = request.getParameter("playlist");
			String genre = request.getParameter("genreName");
			
			albli.insert(name, artist, imagePath, playlist, genre);
		} else if (option != null && option.matches("modify")) {
			String aid = request.getParameter("albumID");
			String name = request.getParameter("albumName");
			String artist = request.getParameter("artistName");
			String imagePath = request.getParameter("imagePath");
			String playlist = request.getParameter("playlist");
			String genre = request.getParameter("genreName");
			
			String[] parameters = new String[] {aid, name, artist, imagePath, playlist, genre};
			albli.modify(parameters);
		}  else if (option != null && option.matches("addComment")) {
			String userID = request.getParameter("userID");
			String albumID = request.getParameter("albumID");
			String content = request.getParameter("content");
			String target = "album";
			
			cli.insert(userID, content, albumID, target);
			
		} else if (option != null && option.matches("deleteComment")) {
			String commentID = request.getParameter("commentID");
			cli.delete(commentID);
		}
		
		try {
			albli.update();
			ali.update();
			gli.update();
			uli.update();
			cli.update();
			root.put("activeUsers", uli.getActiveUsers());
			root.put("activeUser", uli.getActiveUsers().get(0));
			root.put("genres", gli.getGenres());
			root.put("albums", albli.getAlbums());
			root.put("albumsByArtistAZ", albli.getAlbumsSortedByArtistAZ());
			root.put("albumsByTitleAZ", albli.getAlbumsSortedByTitleAZ());
			root.put("artists", ali.getArtists());
			root.put("comments", cli.getAlbumComments());
			template.process(root, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
