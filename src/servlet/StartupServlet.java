package servlet;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

/**
 * Servlet implementation class StartupServlet
 */
@WebServlet("/StartupServlet.do")
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		InitialContext initContext;
		DataSource ds;
		Connection conn;
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/epam_test_web");
			conn = ds.getConnection();
			getServletContext().setAttribute("databaseConnection", conn);
			System.out.println("Connection to Database successdully started");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection to Database failed");
		}
		System.out.println("Servlet is loaded");
	}

}
