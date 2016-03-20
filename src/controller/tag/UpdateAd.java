package controller.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import model.entity.Ad;
import model.entity.User;

public class UpdateAd extends TagForGettingConnection {
	private static final Logger log = Logger.getLogger(UpdateAd.class);

	private Ad ad;

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
		String errorMessage = null;
		try {
			if (ad.getSubject() == null || ad.getSubject().equals("")) {
				errorMessage = "Subject can not be empty!";
			} else {
				if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
					errorMessage = "You can not change this add";
				}
			}
			if (errorMessage == null) {
				ad.setLastModified(Calendar.getInstance().getTimeInMillis());
				Connection connection = daoFactory.getConnection();
				if (ad.getId() == 0) {
					daoFactory.getAdDao(connection).create(ad);

				} else {
					daoFactory.getAdDao(connection).update(ad);
				}
				connection.close();
			}
		} catch (SQLException e) {
			log.error("Error while DataBase interaction");
			e.printStackTrace();
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
