package helper;

import javax.naming.NamingException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

import entity.User;
import entity.UserList;

public abstract class UserListHelper extends Helper {

	public UserListHelper() throws NamingException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static UserList readUserList(ServletContext servletContext) {
		try {
			Connection conn = Helper.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Users");
			UserList list = new UserList();
			if (rs.first()) {
				User user = new User();
				while (rs.next()) {
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setLogin(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					list.addUser(user);
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
