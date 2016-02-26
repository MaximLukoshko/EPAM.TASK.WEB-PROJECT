package tag;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import entity.User;

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
		if (login == null || login.equals("")) {
			errorMessage = "Login can not be empty!";
		} else {
			super.doTag();
			try {
				rs = st.executeQuery("select * from Users where login='" + login + "';");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (!rs.first() || !rs.getString("password").equals(password)) {
					errorMessage = "Check login/passowrd";
				} else {
					User user = new User();
					user.setId(rs.getInt("id"));
					System.out.println(user.getId());
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setLogin(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
