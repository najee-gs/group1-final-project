/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Servlet class deals with Genre data.
 * 
 * 
 ***********************************************************/
package group1.boundary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class GenreServlet
 */
@WebServlet("/GenreServlet")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
	
	private String templateDir = "/WEB-INF";   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreServlet() {
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
		String templateName = "/templates/genres.ftl";
		Template template = cfg.getTemplate(templateName);
		Writer out = response.getWriter();
		Map<String, Object> root = new HashMap<>();
		GenreLogicImpl g = new GenreLogicImpl();
		UserLogicImpl uli = new UserLogicImpl();

		/* insertion and deletion block goes here */
		String option = request.getParameter("option");
		if (option != null) {
			if (option.matches("insert")) {
				String genreName = request.getParameter("name");
				String imagePath = request.getParameter("imagePath");
				
				g.insert(genreName, imagePath);
			} else if (option.matches("deletion")) {
				String genreID = request.getParameter("genreID");
				
				g.delete(genreID);
			}
		}
		
		try {
			g.update();
			uli.update();
			root.put("activeUsers", uli.getActiveUsers());
			root.put("activeUser", uli.getActiveUsers().get(0).getUserName());
			root.put("genres", g.getGenres());
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
