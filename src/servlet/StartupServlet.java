package servlet;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class StartupServlet
 */
@WebServlet("/StartupServlet.do")
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StartupServlet.class);

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
//		BasicConfigurator.configure();
//		String log4jConfPath = "/WEB-INF/lib/log4j.properties";
//		PropertyConfigurator.configure(log4jConfPath);
		super.init(config);
		InitialContext initContext;
		DataSource ds;
		Connection conn;
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/epam_test_web");
			conn = ds.getConnection();
			getServletContext().setAttribute("databaseConnection", conn);
			log.info("Connection to Database successdully started");
			// System.out.println("Connection to Database successdully
			// started");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Connection to Database failed;");
			// System.out.println("Connection to Database failed");
		}
		System.out.println("Servlet is loaded");
	}

}
