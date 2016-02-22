package helper;

import javax.naming.NamingException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.Ad;
import entity.AdList;

public abstract class AdListHelper extends Helper {
	// Логический путь к файлу, в котором хранятся данные об объявлени
	private static final String ADS_FILENAME = "WEB-INF/ads.dat";
	// Полный путь к файлу, в котором хранятся данные об объявлениях
	private static String ADS_PATH = null;

	public AdListHelper() throws NamingException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static AdList readAdList(ServletContext servletContext) {
		try {
			Connection conn = Helper.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Ads");
			AdList list = new AdList();
			if (rs.first()) {
				Ad ad = new Ad();
				while (rs.next()) {
					ad.setAuthorId(authorId);
					ad.setAuthor(author);
					ad.setBody(body);
					ad.setId(id);
					ad.setLastModified(lastModified);
					ad.setSubject(subject);
				}
			} else {
				throw new Exception();
			}
			return list;
		} catch (Exception e) {
			// Если возникли проблемы с чтением из файла, возвращаем
			// пустой список
			return new AdList();
		}

	}

	public static void saveAdList(AdList ads) {
		// TODO Auto-generated method stub
		synchronized (ads) {
			try {
				// Создаем объектный поток вывода на основе файлового
				// потока
				@SuppressWarnings("resource")
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ADS_PATH));
				// Записываем содержимое объекта в поток
				out.writeObject(ads);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
