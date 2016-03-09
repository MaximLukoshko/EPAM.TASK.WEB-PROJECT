package controller.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.actions.DataBaseInterraction;
import model.entity.User;

public class Login extends TagForGettingConnection {
	private String login;

	private String password;

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String errorMessage = null;
		User user = null;
		if (login == null || login.equals("")) {
			errorMessage = "Login can not be empty!";
		} else {
			super.doTag();
			user = DataBaseInterraction.login(st, login, password, errorMessage);
			getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
			if (user == null) {
				getJspContext().setAttribute("userLogin", login, PageContext.SESSION_SCOPE);
				errorMessage = "Check login/passowrd";
			}
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
