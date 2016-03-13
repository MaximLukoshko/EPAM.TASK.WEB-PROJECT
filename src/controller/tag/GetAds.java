package controller.tag;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import model.actions.DataBaseInterraction;
import model.entity.User;

public class GetAds extends TagForGettingConnection {
	private int id = 0;
	// Поле данных для атрибута range (диапазон объявлений)
	private String range;
	// Поле данных для атрибута sort (основание сортировки)
	private String sort;
	// Поле данных для атрибута dir (направление сортировки)
	private char dir;
	// Поле данных для атрибута var (контейнер результата)
	private String var;

	public void setId(int id) {
		this.id = id;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		final User authUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
		Object result = null;
		try {
			result = DataBaseInterraction.getAds(id, range, sort, dir, daoFactory.getConnection().createStatement(),
					authUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getJspContext().setAttribute(GetAds.this.var, result, PageContext.PAGE_SCOPE);
	}
}
