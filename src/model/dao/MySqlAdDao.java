package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import model.entity.Ad;
import model.entity.User;

public class MySqlAdDao implements AdDao {
	private static final Logger log = Logger.getLogger(MySqlAdDao.class);
	private final Connection connection;

	public MySqlAdDao(Connection connection) {
		this.connection = connection;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void create(Ad ad) {
		String sql = "insert into Ads values (null, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ad.getAuthorId());
			statement.setString(2, ad.getAuthorName());
			statement.setString(3, ad.getSubject());
			statement.setString(4, ad.getBody());
			statement.setLong(5, ad.getLastModified());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Failed to create Ad " + ad.toString());
			
		}
	}

	@SuppressWarnings("deprecation")
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
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Failed to execute Query " + "\"select * from Ads where id='" + id + "';\"");
			
		}
		return null;

	}

	@Override
	public void update(Ad ad) {
		String sql = "update Ads set subject= ?, body= ?, lastModified= ? where id= ?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, ad.getSubject());
			statement.setString(2, ad.getBody());
			statement.setLong(3, ad.getLastModified());
			statement.setInt(4, ad.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Failed to update Ad " + ad.toString());
			
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void delete(Ad ad) {
		String sql = "delete from Ads where id = ?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ad.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Failed to delete ad " + ad.toString());
			
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public ArrayList<Ad> getAll() {
		ArrayList<Ad> adList = new ArrayList<Ad>();
		String sql = new String("select * from Ads;");
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
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
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Failed to get all ads");
			
		}
		return adList;
	}

	@SuppressWarnings("deprecation")
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
			log.log(Priority.ERROR, "Exeption: ", e);
			log.error("Failed to execute Query " + "\"" + query + "\"");
			
		}
		return adList;
	}
}
