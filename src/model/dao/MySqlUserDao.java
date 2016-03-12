package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.entity.User;

public class MySqlUserDao implements UserDao {
	private static final Logger log = Logger.getLogger(MySqlUserDao.class);
	private final Connection connection;

	public MySqlUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public User create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User read(String login) {
		User user = null;
		String sql = "select * from Users where login='" + login + "';";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.first()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			log.error("Failed to execute Query " + "\"select * from Users where login='" + login + "';\"");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(User user) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate("insert into Users values (null, '" + user.getName() + "', '" + user.getEmail() + "', '"
					+ user.getLogin() + "', '" + user.getPassword() + "');");
		} catch (SQLException e) {
			log.error("Failed to execute Query " + "\"insert into Users values (null, '" + user.getName() + "', '"
					+ user.getEmail() + "', '" + user.getLogin() + "', '" + user.getPassword() + "');\"");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User User) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
