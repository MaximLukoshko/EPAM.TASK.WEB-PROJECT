package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import model.entity.User;

public class MySqlUserDao implements UserDao {
	private static final Logger log = Logger.getLogger(MySqlUserDao.class);
	private final Connection connection;

	public MySqlUserDao(Connection connection) {
		this.connection = connection;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void create(User user) {
		try {
			String sql = "insert into Users values (null, ?, ?, ?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getPassword());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Failed to create new user " + user.getName());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public User read(String login) {
		User user = null;
		String sql = "select * from Users where login= ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.first()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Failed to read user with login " + login);
			
		}
		return user;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

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
