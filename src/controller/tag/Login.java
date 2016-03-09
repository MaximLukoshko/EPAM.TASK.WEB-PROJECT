package controller.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.actions.DataBaseInterraction;
import model.entity.User;

public class Login extends TagForGettingConnection {
	// Поле данных для атрибута login
	private String login;

	// Поле данных для атрибута password
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
		super.doTag();
		User user = DataBaseInterraction.login(st, login, password, errorMessage);
		getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
		// if (user == null) {
		// getJspContext().setAttribute("userLogin", login,
		// PageContext.SESSION_SCOPE);
		// }
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
