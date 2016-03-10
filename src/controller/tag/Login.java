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
		User user = new User();
		super.doTag();
		errorMessage = DataBaseInterraction.login(st, login, password, user);
		if (user.getId() == 0) {
			user = null;
		}
		getJspContext().setAttribute("userLogin", login, PageContext.SESSION_SCOPE);
		getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
