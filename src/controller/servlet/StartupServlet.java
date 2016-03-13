package controller.servlet;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import model.dao.DaoFactory;
import model.dao.MySqlDaoFactory;

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
		super.init(config);
		InitialContext initContext;
		DataSource ds;
		Connection conn;
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/epam_test_web");
			conn = ds.getConnection();
			DaoFactory daoFactory = new MySqlDaoFactory(conn);
			getServletContext().setAttribute("DaoFactory", daoFactory);
			log.info("Connection to Database successfully started");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Connection to Database failed;");
		}
		log.info("The servlet successfully started");
	}

}
