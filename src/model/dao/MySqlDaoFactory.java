package model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class MySqlDaoFactory implements DaoFactory {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MySqlDaoFactory.class);
	private final DataSource dataSource;

	public MySqlDaoFactory(DataSource dataSource) {
		this.dataSource = dataSource;
		if (dataSource == null) {
			System.out.println("noConnection");
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
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
