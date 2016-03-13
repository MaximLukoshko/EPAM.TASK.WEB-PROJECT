package model.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

	/** Возвращает подключение к базе данных */
	public Connection getConnection() throws SQLException;

	/**
	 * Возвращает объект для управления персистентным состоянием объекта User
	 */
	public UserDao getUserDao();

	/**
	 * Возвращает объект для управления персистентным состоянием объекта Ad
	 */
	public AdDao getAdDao();
}
