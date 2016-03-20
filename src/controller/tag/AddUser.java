package controller.tag;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import model.actions.DataBaseInterraction;
import model.entity.User;

public class AddUser extends TagForGettingDaoFactory {
	private static final Logger log = Logger.getLogger(AddUser.class);

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		String errorMessage = null;
		try {
			errorMessage = DataBaseInterraction.addUser(daoFactory, user);
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Error while DataBase interaction");
			
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}
}
