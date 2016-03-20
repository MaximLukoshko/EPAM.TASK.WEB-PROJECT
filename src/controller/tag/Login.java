package controller.tag;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import model.actions.DataBaseInterraction;
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
		ArrayList<String> errorMessage = new ArrayList<String>();
		super.doTag();
		User user = null;
		try {
			user = DataBaseInterraction.login(daoFactory, login, password, errorMessage);
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Error while DataBase interaction");
		}
		getJspContext().setAttribute("userLogin", login, PageContext.SESSION_SCOPE);
		getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
		if (errorMessage.get(0).equals("")) {
			errorMessage.set(0, null);
		}
		getJspContext().setAttribute("errorMessage", errorMessage.get(0), PageContext.SESSION_SCOPE);
	}

}
