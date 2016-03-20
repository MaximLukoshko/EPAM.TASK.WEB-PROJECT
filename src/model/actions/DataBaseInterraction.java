package model.actions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.Ad;
import model.entity.User;

public abstract class DataBaseInterraction {
	private static final Logger log = Logger.getLogger(DataBaseInterraction.class);

	public static String addUser(DaoFactory daoFactory, User user) throws SQLException {
		String errorMessage = null;
		if (user.getLogin() == null || user.getLogin().equals("")) {
			errorMessage = "Login cannot be empty!";
		} else if (user.getName() == null || user.getName().equals("")) {
			errorMessage = "User name can not be empty!";
		} else {
			Connection connection = daoFactory.getConnection();
			UserDao userDao = daoFactory.getUserDao(connection);
			if (userDao.read(user.getLogin()) != null) {
				errorMessage = "This Login is busy! Use another one";
			}
			if (errorMessage == null) {
				userDao.create(user);
			}
			connection.close();
		}
		return errorMessage;

	}

	public static User login(DaoFactory daoFactory, String login, String password, ArrayList<String> errorMessage)
			throws SQLException {
		if (errorMessage.isEmpty()) {
			errorMessage.add("");
		}
		if (login == null || login.equals("")) {
			errorMessage.set(0, "Login can not be empty!");
			return null;
		}

		Connection connection = daoFactory.getConnection();
		User user = daoFactory.getUserDao(connection).read(login);
		connection.close();
		if (user == null || !password.equals(user.getPassword())) {
			errorMessage.set(0, "Check login/passowrd");
			user = null;
		}
		return user;
	}

	public static Object getAds(int id, String range, final String sort, final char dir, User authUser,
			DaoFactory daoFactory) throws SQLException {
		if (id > 0) {
			Connection connection = daoFactory.getConnection();
			Ad ad = daoFactory.getAdDao(connection).read(id);
			connection.close();
			return ad;
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
			return sortedList;
		}
	}

	@SuppressWarnings("deprecation")
	public static String deleteAd(DaoFactory daoFactory, Ad ad, User currentUser) {
		String errorMessage = null;
		if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
			errorMessage = "You can not change this add";
		}
		if (errorMessage == null) {
			java.sql.Connection connection;
			try {
				connection = daoFactory.getConnection();
				daoFactory.getAdDao(connection).delete(ad);
				connection.close();
			} catch (SQLException e) {
				log.log(Priority.ERROR, "Exeption: ", e);
			}
		}
		return errorMessage;
	}

	public static String updateAd(DaoFactory daoFactory, Ad ad, User currentUser) throws SQLException {
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
			Connection connection = daoFactory.getConnection();
			if (ad.getId() == 0) {
				daoFactory.getAdDao(connection).create(ad);

			} else {
				daoFactory.getAdDao(connection).update(ad);
			}
			connection.close();
		}
		return errorMessage;
	}
}
