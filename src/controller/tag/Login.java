package controller.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import model.entity.User;

public class Login extends TagForGettingDaoFactory {
	private static final Logger log = Logger.getLogger(Login.class);
	private String login;

	private String password;

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		String errorMessage = null;
		User user = null;
		try {
			if (login == null || login.equals("")) {
				errorMessage = "Login can not be empty!";
			}

			Connection connection = daoFactory.getConnection();
			user = daoFactory.getUserDao(connection).read(login);
			connection.close();
			if (user == null || !password.equals(user.getPassword())) {
				errorMessage = "Check login/passowrd";
				user = null;
			}
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Error while DataBase interaction");
		}
		getJspContext().setAttribute("userLogin", login, PageContext.SESSION_SCOPE);
		getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
