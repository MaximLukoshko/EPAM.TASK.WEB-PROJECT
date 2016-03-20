package controller.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import model.dao.DaoFactory;

public class TagForGettingDaoFactory extends SimpleTagSupport {
	protected DaoFactory daoFactory;

	@Override
	public void doTag() throws JspException, IOException {
		daoFactory = (DaoFactory) getJspContext().getAttribute("DaoFactory", PageContext.APPLICATION_SCOPE);
	}
}
