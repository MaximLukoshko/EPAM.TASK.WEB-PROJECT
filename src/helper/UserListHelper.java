package helper;

import javax.naming.NamingException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

import entity.Ad;
import entity.AdList;
import entity.User;
import entity.UserList;

public abstract class UserListHelper extends Helper {
	private static final String USERS_FILENAME = "WEB-INF/users.dat";

	private static String USERS_PATH = null;

	public UserListHelper() throws NamingException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static UserList readUserList(ServletContext servletContext) {
		try {
			try {
				Connection conn = Helper.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from Ads");
				UserList list = new UserList();
				if (rs.first()) {
					User user = new User();
					while (rs.next()) {
						user.setId(id);
						user.setName(name);
						user.setEmail(email);
						user.setLogin(login);
						user.setPassword(password);
					}
				} else {
					throw new Exception();
				}
				return list;

		} catch (Exception e) {
			// Если возникли проблемы с чтением из файла, возвращаем
			// пустой список
			return new UserList();
		}
	}

	public static void saveUserList(UserList users) {
		// TODO Auto-generated method stub
		synchronized (users) {
			try {
				// Создаем объектный поток вывода на основе файлового
				// потока
				@SuppressWarnings("resource")
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
				// Записываем содержимое объекта в поток
				out.writeObject(users);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
