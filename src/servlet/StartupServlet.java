package servlet;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import entity.Ad;
import entity.AdList;
import entity.UserList;
import helper.AdListHelper;
import helper.UserListHelper;

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
		Connection conn;
		InitialContext initContext;
		DataSource ds;
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/jsp");
			conn = ds.getConnection();
			getServletContext().setAttribute("databaseConnection", conn);
			System.out.println("Server is started");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection to Database failed");
		}
		System.out.println("Servlet is loaded");
	}

}
