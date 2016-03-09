package tag;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.entity.User;

public class AddUser extends TagForGettingConnection {

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Изначально описание ошибки = null (т.е. ошибки нет)
		String errorMessage = null;
		// Проверить, что логин не пустой
		if (user.getLogin() == null || user.getLogin().equals("")) {
			errorMessage = "Login cannot be empty!";
		} else if (user.getName() == null || user.getName().equals("")) {
			errorMessage = "User name can not be empty!";
		} else {
			super.doTag();
			try {
				rs = st.executeQuery("select * from Users where login='" + user.getLogin() + "';");
				if(rs.first()){
					errorMessage = "This Login is busy! Use another one";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Если ошибки не было - добавить пользователя
		if (errorMessage == null) {
			// Непосредственное добавление пользователя делает UserList
			try {
				System.out.println("insert into Users values (null, '" + user.getName() + "', '" + user.getEmail()
						+ "', '" + user.getLogin() + "', '" + user.getPassword() + "');");
				st.executeUpdate("insert into Users values (null, '" + user.getName() + "', '" + user.getEmail()
						+ "', '" + user.getLogin() + "', '" + user.getPassword() + "');");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Сохранить описание ошибки (текст или null) в сессии
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);

	}

}
