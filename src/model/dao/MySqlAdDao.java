package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.entity.Ad;
import model.entity.User;

public class MySqlAdDao implements AdDao {
	private static final Logger log = Logger.getLogger(MySqlAdDao.class);
	private final Connection connection;

	public MySqlAdDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Ad ad) {
		// TODO Auto-generated method stub
	}

	@Override
	public Ad read(int id) {
		try {
			String sql = "SELECT * FROM Ads WHERE id= ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
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
			}
		} catch (

		SQLException e)

		{
			log.error("Failed to execute Query " + "\"select * from Ads where id='" + id + "';\"");
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void update(Ad ad) {
		String query = null;
		if (ad.getId() == 0) {
			query = "insert into Ads values (null, '" + ad.getAuthorId() + "', '" + ad.getAuthorName() + "', '"
					+ ad.getSubject() + "', '" + ad.getBody() + "', '" + ad.getLastModified() + "');";
		} else {
			query = "update Ads set subject='" + ad.getSubject() + "', body='" + ad.getBody() + "', lastModified='"
					+ ad.getLastModified() + "' where id='" + ad.getId() + "';";
		}
		try {
			connection.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			log.error("Failed to execute Query " + "\"" + query + "\"");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Ad ad) {
		try {
			connection.createStatement().executeUpdate("delete from Ads where id='" + ad.getId() + "';");
		} catch (SQLException e) {
			log.error("Failed to execute Query " + "\"delete from Ads where id='" + ad.getId() + "';\"");
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Ad> getAll() {
		ArrayList<Ad> adList = new ArrayList<Ad>();
		String query = new String("select * from Ads;");
		try {
			ResultSet rs = connection.createStatement().executeQuery(query);
			if (rs.first()) {
				do {
					Ad ad = new Ad();
					ad.setAuthorId(rs.getInt("authorId"));
					ad.setBody(rs.getString("body"));
					ad.setId(rs.getInt("id"));
					ad.setAuthorName(rs.getString("authorName"));
					ad.setLastModified(rs.getLong("lastModified"));
					ad.setSubject(rs.getString("subject"));
					adList.add(ad);
				} while (rs.next());
			}
		} catch (SQLException e) {
			log.error("Failed to execute Query " + "\"" + query + "\"");
			e.printStackTrace();
		}
		return adList;
	}

	@Override
	public ArrayList<Ad> read(User authUser) {
		ArrayList<Ad> adList = new ArrayList<Ad>();
		String query = new String("select * from Ads where authorId='" + authUser.getId() + "';");
		try {
			ResultSet rs = connection.createStatement().executeQuery(query);
			if (rs.first()) {
				do {
					Ad ad = new Ad();
					ad.setAuthorId(rs.getInt("authorId"));
					ad.setBody(rs.getString("body"));
					ad.setId(rs.getInt("id"));
					ad.setAuthorName(rs.getString("authorName"));
					ad.setLastModified(rs.getLong("lastModified"));
					ad.setSubject(rs.getString("subject"));
					adList.add(ad);
				} while (rs.next());
			}
		} catch (SQLException e) {
			log.error("Failed to execute Query " + "\"" + query + "\"");
			e.printStackTrace();
		}
		return adList;
	}
}
