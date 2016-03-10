package model.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import org.apache.log4j.Logger;

import model.entity.Ad;
import model.entity.User;

public class DataBaseInterraction {
	private static final Logger log = Logger.getLogger(DataBaseInterraction.class);

	public static String addUser(Statement st, User user) {
		String errorMessage = null;
		if (user.getLogin() == null || user.getLogin().equals("")) {
			errorMessage = "Login cannot be empty!";
		} else if (user.getName() == null || user.getName().equals("")) {
			errorMessage = "User name can not be empty!";
		} else {
			try {
				ResultSet rs = st.executeQuery("select * from Users where login='" + user.getLogin() + "';");
				if (rs.first()) {
					errorMessage = "This Login is busy! Use another one";
				}
			} catch (SQLException e) {
				log.error(
						"Failed to execute Query " + "\"select * from Users where login='" + user.getLogin() + "';\"");
				e.printStackTrace();
			}
		}
		if (errorMessage == null) {
			try {
				st.executeUpdate("insert into Users values (null, '" + user.getName() + "', '" + user.getEmail()
						+ "', '" + user.getLogin() + "', '" + user.getPassword() + "');");
			} catch (SQLException e) {
				log.error("Failed to execute Query " + "\"insert into Users values (null, '" + user.getName() + "', '"
						+ user.getEmail() + "', '" + user.getLogin() + "', '" + user.getPassword() + "');\"");
				e.printStackTrace();
			}
		}
		return errorMessage;
	}

	public static String login(Statement st, String login, String password, User user) {
		if (login == null || login.equals("")) {
			user.setId(0);
			return "Login can not be empty!";
		} else {
			ResultSet rs = null;
			try {
				rs = st.executeQuery("select * from Users where login='" + login + "';");
			} catch (SQLException e) {
				log.error("Failed to execute Query " + "\"select * from Users where login='" + login + "';\"");
				e.printStackTrace();
			}
			try {
				if (!rs.first() || !rs.getString("password").equals(password)) {
					user.setId(0);
					return "Check login/passowrd";
				} else {
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setLogin(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					return null;
				}
			} catch (SQLException e) {
				log.error("Failed to check password for login " + login);
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object getAds(int id, String range, final String sort, final char dir, Statement st, User authUser) {
		ResultSet rs;
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
					return ad;
					// getJspContext().setAttribute(GetAds.this.var, ad,
					// PageContext.PAGE_SCOPE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
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
			return sortedList;
		}
		return null;
	}

	public static String deleteAd(Statement st, Ad ad, User currentUser) {
		String errorMessage = null;
		// Проверить, что объявление изменяется его автором, а не чужаком
		if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
			errorMessage = "You can not change this add";
		}
		if (errorMessage == null) {
			try {
				st.executeUpdate("delete from Ads where id='" + ad.getId() + "';");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return errorMessage;
	}

	public static String updateAd(Statement st, Ad ad, User currentUser) {
		String errorMessage = null;
		if (ad.getSubject() == null || ad.getSubject().equals("")) {
			errorMessage = "Subject can not be empty!";
		} else {
			if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
				errorMessage = "You can not change this add";
			}
		}
		if (errorMessage == null) {
			ad.setLastModified(Calendar.getInstance().getTimeInMillis());
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
		return errorMessage;
	}
}
