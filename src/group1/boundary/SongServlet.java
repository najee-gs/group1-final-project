/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Servlet class deals with Songs data.
 * 
 * 
 ***********************************************************/
package group1.boundary;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import group1.logiclayer.ArtistLogicImpl;
import group1.logiclayer.CommentLogicImpl;
import group1.logiclayer.GenreLogicImpl;
import group1.logiclayer.SongLogicImpl;
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
 * Servlet implementation class SongServlet
 */
@WebServlet("/SongServlet")
public class SongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
	
	private String templateDir = "/WEB-INF";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String templateName = "/templates/songs.ftl";
		Template template = cfg.getTemplate(templateName);
		String option = request.getParameter("option");
		UserLogicImpl uli = new UserLogicImpl();
		SongLogicImpl sli = new SongLogicImpl();
		ArtistLogicImpl ali = new ArtistLogicImpl();
		GenreLogicImpl gli = new GenreLogicImpl();
		CommentLogicImpl cli = new CommentLogicImpl();
		Writer out = response.getWriter();
		Map<String, Object> root = new HashMap<>();
		
		if (option != null && option.matches("insert")) {
			String songTitle = request.getParameter("songTitle");
			String artistID = request.getParameter("artistName");
			String songPath = request.getParameter("songPath");
			String genre = request.getParameter("genreName");
			String state = request.getParameter("local");
			
			sli.insert(songTitle, artistID, songPath, genre, state);
			
		} else if (option != null && option.matches("delete")) {
			String songID = request.getParameter("songID");
			
			sli.delete(songID);
			
		} else if (option != null && option.matches("modify")) {
			String songID = request.getParameter("songID");
			String songTitle = request.getParameter("songTitle");
			String artistID = request.getParameter("artistName");
			String songPath = request.getParameter("songPath");
			String state = request.getParameter("local");
			
			String[] param = {songID, songTitle, artistID, songPath, state};
			sli.modify(param);
		}
		
		try {

			sli.update();
			uli.update();
			ali.update();
			gli.update();
			cli.update();
			root.put("activeUsers", uli.getActiveUsers());
			root.put("activeUser", uli.getActiveUsers().get(0).getUserName());
			root.put("genres", gli.getGenres());
			root.put("artists", ali.getArtists());
			root.put("songs", sli.getSongs());
			template.process(root, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
