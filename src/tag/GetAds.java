package tag;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import entity.Ad;
import entity.User;

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
		if (id > 0) {
			try {
				rs = st.executeQuery("select * from Ads where id='" + id + "';");
				if (rs.first()) {
					Ad ad = new Ad();
					ad.setAuthorId(rs.getInt("authorId"));
					ad.setBody(rs.getString("body"));
					ad.setId(rs.getInt("id"));
					ad.setAuthorId(rs.getInt("authorId"));
					ad.setAuthorName(rs.getString("authorName"));
					ad.setLastModified(rs.getLong("lastModified"));
					ad.setSubject(rs.getString("subject"));
					getJspContext().setAttribute(GetAds.this.var, ad, PageContext.PAGE_SCOPE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			final User authUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
			// В этом списке будут содержаться только отобранные объявления
			ArrayList<Ad> sortedList = new ArrayList<Ad>();

			try {
				String query = new String("select * from Ads");
				if ("my".equals(range)) {
					query += " where authorId='" + authUser.getId() + "'";
				}
				query += ";";
				rs = st.executeQuery(query);
				if (rs.first()) {
					do {
						Ad ad = new Ad();
						ad.setAuthorId(rs.getInt("authorId"));
						ad.setBody(rs.getString("body"));
						ad.setId(rs.getInt("id"));
						ad.setAuthorName(rs.getString("authorName"));
						ad.setLastModified(rs.getLong("lastModified"));
						ad.setSubject(rs.getString("subject"));
						sortedList.add(ad);
					} while (rs.next());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Comparator<Ad> comparator = new Comparator<Ad>() {

				@Override
				public int compare(Ad ad1, Ad ad2) {
					int result = 0;
					if (GetAds.this.sort != null && GetAds.this.sort.equals("date")) {
						result = ad1.getLastModified() < ad2.getLastModified() ? -1
								: ad1.getLastModified() > ad2.getLastModified() ? 1 : 0;
					} else if (GetAds.this.sort != null && GetAds.this.sort.equals("subject")) {
						result = ad1.getSubject().compareTo(ad2.getSubject());
					} else {
						result = ad1.getAuthorName().compareTo(ad2.getAuthorName());
					}

					if (GetAds.this.dir == 'd') {
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
			getJspContext().setAttribute(GetAds.this.var, sortedList, PageContext.PAGE_SCOPE);
		}
	}
}
