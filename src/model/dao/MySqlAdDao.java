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
	public void update(Ad Ad) {
		// TODO Auto-generated method stub

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
