package controller.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import model.entity.Ad;
import model.entity.User;

public class GetAds extends TagForGettingDaoFactory {
	private static final Logger log = Logger.getLogger(GetAds.class);

	private int id = 0;
	private String range;
	private String sort;
	private char dir;
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

	@SuppressWarnings("deprecation")
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		final User authUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
		Object result = null;
		try {
			if (id > 0) {
				Connection connection = daoFactory.getConnection();
				Ad ad = daoFactory.getAdDao(connection).read(id);
				connection.close();
				result = ad;
			} else {
				Connection connection = daoFactory.getConnection();
				ArrayList<Ad> sortedList = new ArrayList<Ad>();
				if ("my".equals(range)) {
					sortedList = daoFactory.getAdDao(connection).read(authUser);
				} else {
					sortedList = daoFactory.getAdDao(connection).getAll();
				}
				connection.close();

				Comparator<Ad> comparator = new Comparator<Ad>() {

					@Override
					public int compare(Ad ad1, Ad ad2) {
						int result = 0;
						if (sort != null && sort.equals("date")) {
							result = ad1.getLastModified() < ad2.getLastModified() ? -1
									: ad1.getLastModified() > ad2.getLastModified() ? 1 : 0;
						} else if (sort != null && sort.equals("subject")) {
							result = ad1.getSubject().compareTo(ad2.getSubject());
						} else {
							result = ad1.getAuthorName().compareTo(ad2.getAuthorName());
						}
						if (dir == 'd') {
							result = -result;
						}
						return result;
					}

				};

				if (sortedList.size() == 0) {
					sortedList = null;
				} else {
					Collections.sort(sortedList, comparator);
				}
				result = sortedList;
			}
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Error while DataBase interaction");
			
		}
		getJspContext().setAttribute(GetAds.this.var, result, PageContext.PAGE_SCOPE);
	}
}
