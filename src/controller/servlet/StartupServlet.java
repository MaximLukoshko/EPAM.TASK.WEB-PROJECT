package controller.servlet;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

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
	@SuppressWarnings("deprecation")
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		InitialContext initContext;
		DataSource ds;
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/epam_test_web");
			DaoFactory daoFactory = new MySqlDaoFactory(ds);
			getServletContext().setAttribute("DaoFactory", daoFactory);
			log.info("Connection to Database successfully started");
		} catch (Exception e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Connection to Database failed;");
		}
//		LanguageSimpleFactory languageSimpleFactory = new LanguageSimpleFactory();
//		getServletContext().setAttribute("Language", languageSimpleFactory.getLanguage("eng"));

		log.info("The servlet successfully started");
	}

}
