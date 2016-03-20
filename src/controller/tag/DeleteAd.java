package controller.tag;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.entity.Ad;
import model.entity.User;

public class DeleteAd extends TagForGettingDaoFactory {

	private Ad ad;

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
		String errorMessage = null;
		if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
			errorMessage = "You can not change this add";
		}
		if (errorMessage == null) {
			java.sql.Connection connection;
			try {
				connection = daoFactory.getConnection();
				daoFactory.getAdDao(connection).delete(ad);
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}
}
