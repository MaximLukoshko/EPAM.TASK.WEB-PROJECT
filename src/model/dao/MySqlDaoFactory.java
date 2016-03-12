package model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MySqlDaoFactory implements DaoFactory {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MySqlDaoFactory.class);

	@Override
	public Connection getConnection() throws SQLException {
		// InitialContext initContext = null;
		// DataSource ds = null;
		// Connection conn = null;
		// try {
		// initContext = new InitialContext();
		// ds = (DataSource)
		// initContext.lookup("java:comp/env/jdbc/epam_test_web");
		// conn = ds.getConnection();
		// // getServletContext().setAttribute("databaseConnection", conn);
		// log.info("Connection to Database successfully started");
		// } catch (Exception e) {
		// e.printStackTrace();
		// log.error("Connection to Database failed;");
		// }
		return null;
	}

	@Override
	public UserDao getUserDao(Connection connection) {
		return new MySqlUserDao(connection);
	}

	@Override
	public AdDao getAdDao(Connection connection) {
		return new MySqlAdDao(connection);
	}
}
