package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.entity.Ad;

public class MySqlAdDao implements AdDao {
	private static final Logger log = Logger.getLogger(MySqlAdDao.class);
	private final Connection connection;

	public MySqlAdDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Ad create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ad read(int key) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}
}
