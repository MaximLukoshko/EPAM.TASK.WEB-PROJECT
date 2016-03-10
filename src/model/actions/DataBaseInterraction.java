package model.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import model.entity.User;

public class DataBaseInterraction {
	private static final Logger log = Logger.getLogger(DataBaseInterraction.class);

	public static String addUser(Statement st, User user) {
		String errorMessage = null;
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
		if (errorMessage == null) {
			try {
				st.executeUpdate("insert into Users values (null, '" + user.getName() + "', '" + user.getEmail()
						+ "', '" + user.getLogin() + "', '" + user.getPassword() + "');");
			} catch (SQLException e) {
				log.error("Failed to execute Query " + "\"insert into Users values (null, '" + user.getName() + "', '"
						+ user.getEmail() + "', '" + user.getLogin() + "', '" + user.getPassword() + "');\"");
				e.printStackTrace();
			}
		}
		return errorMessage;
	}

	public static String login(Statement st, String login, String password, User user) {
		if (login == null || login.equals("")) {
			user.setId(0);
			return "Login can not be empty!";
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
					user.setId(0);
					return "Check login/passowrd";
				} else {
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setLogin(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					return null;
				}
			} catch (SQLException e) {
				log.error("Failed to check password for login " + login);
				e.printStackTrace();
			}
		}
		return null;
	}
}
