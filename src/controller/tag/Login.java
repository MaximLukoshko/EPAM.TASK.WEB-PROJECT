package controller.tag;

import java.io.IOException;
import java.util.ArrayList;

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
		ArrayList<String> errorMessage = new ArrayList<String>();
		super.doTag();
		User user = DataBaseInterraction.login(daoFactory, login, password, errorMessage);
		getJspContext().setAttribute("userLogin", login, PageContext.SESSION_SCOPE);
		getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
		if (errorMessage.get(0).equals("")) {
			errorMessage.set(0, null);
		}
		getJspContext().setAttribute("errorMessage", errorMessage.get(0), PageContext.SESSION_SCOPE);
	}

}
