package controller.tag;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import model.actions.DataBaseInterraction;
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
			errorMessage = DataBaseInterraction.updateAd(daoFactory, ad, currentUser);
		} catch (SQLException e) {
			log.error("Error while DataBase interaction");
			e.printStackTrace();
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
