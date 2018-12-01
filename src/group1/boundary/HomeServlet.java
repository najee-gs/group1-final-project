/***********************************************************
 * Course: CSCI 5436-A Distributed Web System Design 
 * Project Name: Personal Music Library
 * Group #: 1
 * Contributor(s): Najee Searcy
 * Document Description: This Servlet class deals with User data.
 * 
 * 
 ***********************************************************/
package group1.boundary;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

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
import group1.logiclayer.UserLogicImpl;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
	private String templateDir = "/WEB-INF/templates";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		String templateName = "home.ftl";
		Template template = cfg.getTemplate(templateName);
		Writer out = response.getWriter();
		Map<String, Object> root = new HashMap<>();
		String option = request.getParameter("option");
		UserLogicImpl uli = new UserLogicImpl();
		
		uli.update();
		
		if (option!=null) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("Password");
			
			if(option.matches("login")) {
				
				if(uli.isValidUser(userName, password)) {
					System.out.println("Valid User\nUsername: " + userName + "\nPassword: " + password);
					uli.update();
					root.put("activeUsers", uli.getActiveUsers());
					root.put("activeUser", uli.getActiveUsers().get(0).getUserName());
				} else {
					System.out.println("Invalid User\nUsername: " + userName + "\nPassword: " + password);
					root.put("users", uli.getUsers());
					templateName = "loginerror.ftl";
					template = cfg.getTemplate(templateName);
				}
			} else if (option.matches("logout")) {
				uli.logoutByUsername(userName);
				uli.update();
				templateName = "redirect.ftl";
				template = cfg.getTemplate(templateName);
			} else if (option.matches("register")) {
				uli.insert(userName, password, false);
				templateName = "redirect.ftl";
				template = cfg.getTemplate(templateName);
			}
		}
		else {
			uli.update();
			root.put("activeUsers", uli.getActiveUsers());
			root.put("activeUser", uli.getActiveUsers().get(0).getUserName());
		}
		

		try {
			template.process(root, out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
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
