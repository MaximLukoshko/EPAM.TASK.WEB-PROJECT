package controller.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.actions.DataBaseInterraction;
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
		super.doTag();
		// Изначально описание ошибки = null (т.е. ошибки нет)
		// Извлечь из сессии описание текущего пользователя
		User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
		String errorMessage = DataBaseInterraction.deleteAd(st, ad, currentUser);

		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);

	}

}
