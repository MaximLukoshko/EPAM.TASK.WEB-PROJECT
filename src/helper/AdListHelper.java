package helper;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.Ad;
import entity.AdList;

public abstract class AdListHelper extends Helper {

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
					ad.setAuthorId(rs.getInt("authorId"));
					// ad.setAuthor(User);
					ad.setBody(rs.getString("body"));
					ad.setId(rs.getInt("id"));
					ad.setAuthorId(rs.getInt("authorId"));
					ad.setLastModified(rs.getLong("lastModified"));
					ad.setSubject(rs.getString("subject"));
					list.updateAd(ad);
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

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
