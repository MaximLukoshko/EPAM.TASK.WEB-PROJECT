package controller.tag;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.entity.Ad;
import model.entity.User;

public class DeleteAd extends TagForGettingConnection {

	// Поле данных для атрибута
	private Ad ad;

	// Метод-сеттер для установки атрибута (вызывается контейнером)
	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Изначально описание ошибки = null (т.е. ошибки нет)
		String errorMessage = null;
		// Извлечь из сессии описание текущего пользователя
		User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
		// Проверить, что объявление изменяется его автором, а не чужаком
		if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
			errorMessage = "You can not change this add";
		}
		if (errorMessage == null) {
			super.doTag();
			try {
				st.executeUpdate("delete from Ads where id='" + ad.getId() + "';");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);

	}

}
