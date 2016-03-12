package model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.entity.Ad;

public class MySqlAdDao implements AdDao {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MySqlAdDao.class);
	@SuppressWarnings("unused")
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
	public void delete(Ad Ad) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Ad> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
