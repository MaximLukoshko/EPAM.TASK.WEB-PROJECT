package controller.tag;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.entity.Ad;
import model.entity.User;

public class UpdateAd extends TagForGettingConnection {

	private Ad ad;

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		String errorMessage = null;
		User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);

		if (ad.getSubject() == null || ad.getSubject().equals("")) {
			errorMessage = "Subject can not be empty!";
		} else {
			if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
				errorMessage = "You can not change this add";
			}
		}
		if (errorMessage == null) {
			ad.setLastModified(Calendar.getInstance().getTimeInMillis());
			super.doTag();
			String querry;
			if (ad.getId() == 0) {
				querry = "insert into Ads values (null, '" + ad.getAuthorId() + "', '" + ad.getAuthorName() + "', '"
						+ ad.getSubject() + "', '" + ad.getBody() + "', '" + ad.getLastModified() + "');";
			} else {
				querry = "update Ads set subject='" + ad.getSubject() + "', body='" + ad.getBody() + "', lastModified='"
						+ ad.getLastModified() + "' where id='" + ad.getId() + "';";
			}
			try {
				st.executeUpdate(querry);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}
