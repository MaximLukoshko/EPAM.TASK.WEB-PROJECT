package controller.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.actions.DataBaseInterraction;
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
		errorMessage = DataBaseInterraction.deleteAd(daoFactory, ad, currentUser);

		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}
}
