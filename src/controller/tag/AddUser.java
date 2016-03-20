package controller.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import model.dao.UserDao;
import model.entity.User;

public class AddUser extends TagForGettingConnection {
	private static final Logger log = Logger.getLogger(AddUser.class);

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		String errorMessage = null;
		try {
			if (user.getLogin() == null || user.getLogin().equals("")) {
				errorMessage = "Login cannot be empty!";
			} else if (user.getName() == null || user.getName().equals("")) {
				errorMessage = "User name can not be empty!";
			} else {
				Connection connection = daoFactory.getConnection();
				UserDao userDao = daoFactory.getUserDao(connection);
				if (userDao.read(user.getLogin()) != null) {
					errorMessage = "This Login is busy! Use another one";
				}
				if (errorMessage == null) {
					userDao.create(user);
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error("Error while DataBase interaction");
			e.printStackTrace();
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}
}
