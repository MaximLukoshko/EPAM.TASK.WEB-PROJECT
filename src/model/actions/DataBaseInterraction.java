package model.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import model.entity.User;

public class DataBaseInterraction {
	private static final Logger log = Logger.getLogger(DataBaseInterraction.class);

	public static void addUser(Statement st, User user, String errorMessage) {
		// Проверить, что логин не пустой
		if (user.getLogin() == null || user.getLogin().equals("")) {
			errorMessage = "Login cannot be empty!";
		} else if (user.getName() == null || user.getName().equals("")) {
			errorMessage = "User name can not be empty!";
		} else {
			try {
				ResultSet rs = st.executeQuery("select * from Users where login='" + user.getLogin() + "';");
				if (rs.first()) {
					errorMessage = "This Login is busy! Use another one";
				}
			} catch (SQLException e) {
				log.error(
						"Failed to execute Query " + "\"select * from Users where login='" + user.getLogin() + "';\"");
				e.printStackTrace();
			}
		}
		// Если ошибки не было - добавить пользователя
		if (errorMessage == null) {
			// Непосредственное добавление пользователя делает UserList
			try {
				st.executeUpdate("insert into Users values (null, '" + user.getName() + "', '" + user.getEmail()
						+ "', '" + user.getLogin() + "', '" + user.getPassword() + "');");
			} catch (SQLException e) {
				log.error("Failed to execute Query " + "\"insert into Users values (null, '" + user.getName() + "', '"
						+ user.getEmail() + "', '" + user.getLogin() + "', '" + user.getPassword() + "');\"");
				e.printStackTrace();
			}
		}
	}

	public static User login(Statement st, String login, String password, String errorMessage) {
		if (login == null || login.equals("")) {
			errorMessage = "Login can not be empty!";
		} else {
			ResultSet rs = null;
			try {
				rs = st.executeQuery("select * from Users where login='" + login + "';");
			} catch (SQLException e) {
				log.error("Failed to execute Query " + "\"select * from Users where login='" + login + "';\"");
				e.printStackTrace();
			}
			try {
				if (!rs.first() || !rs.getString("password").equals(password)) {
					errorMessage = "Check login/passowrd";
					return null;
				} else {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setLogin(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			} catch (SQLException e) {
				log.error("Failed to check password for login " + login);
				e.printStackTrace();
			}
		}
		return null;
	}
}
