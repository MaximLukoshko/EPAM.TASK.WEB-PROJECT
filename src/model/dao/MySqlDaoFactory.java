package model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MySqlDaoFactory implements DaoFactory {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MySqlDaoFactory.class);
	private final Connection connection;

	public MySqlDaoFactory(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return connection;
	}

	@Override
	public UserDao getUserDao() {
		return new MySqlUserDao(connection);
	}

	@Override
	public AdDao getAdDao() {
		return new MySqlAdDao(connection);
	}
}
