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

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import group1.logiclayer.ArtistLogicImpl;
import group1.logiclayer.CommentLogicImpl;
import group1.logiclayer.UserLogicImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import freemarker.template.*;

/**
 * Servlet implementation class ArtistServlet
 */
@WebServlet("/ArtistServlet")
public class ArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
	
	private String templateDir = "/WEB-INF";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistServlet() {
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
		String templateName = "/templates/artists.ftl";
		Template template = cfg.getTemplate(templateName);
		String option = request.getParameter("option");
		ArtistLogicImpl ali = new ArtistLogicImpl();
		UserLogicImpl uli = new UserLogicImpl();
		CommentLogicImpl cli = new CommentLogicImpl();
		Writer out = response.getWriter();
		Map<String, Object> root = new HashMap<>();
		
		if (option != null && option.matches("delete")) {
			String aid = request.getParameter("artistID");
			ali.delete(aid);
		} else if (option != null && option.matches("insert")) {
			String artistName = request.getParameter("artistName");
			String debut =  request.getParameter("artistDebut");
			String imagePath = request.getParameter("imagePath");
			String instagram = request.getParameter("instagram");
			
			ali.insert(artistName, debut, imagePath, instagram);
		} else if (option != null && option.matches("modify")) {
			String aid = request.getParameter("artistID");
			String artistName = request.getParameter("artistName");
			String debut = request.getParameter("debut");
			String imagePath = request.getParameter("imagePath");
			String instagram = request.getParameter("instagram");
			
			String[] parameters = new String[] {aid, artistName, debut, imagePath, instagram};
			
			ali.modify(parameters);
		} else if (option != null && option.matches("addComment")) {
			String userID = request.getParameter("userID");
			String artistID = request.getParameter("artistID");
			String content = request.getParameter("content");
			String target = "artist";
			
			cli.insert(userID, content, artistID, target);
			
		} else if (option != null && option.matches("deleteComment")) {
			String commentID = request.getParameter("commentID");
			cli.delete(commentID);
		}
		
		try {
			ali.update();
			root.put("artists", ali.getArtists());
			root.put("artistsAZ", ali.getAristsSortedByNameAZ());
			uli.update();
			root.put("activeUsers", uli.getActiveUsers());
			root.put("activeUser", uli.getActiveUsers().get(0));
			cli.update();
			root.put("comments", cli.getArtistComments());
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
