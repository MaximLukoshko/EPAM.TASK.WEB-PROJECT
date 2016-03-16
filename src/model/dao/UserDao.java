package model.dao;

import java.util.ArrayList;

import model.entity.User;

public interface UserDao {
	/** Создает новую запись и соответствующий ей объект */
	public void create(User user);

	/**
	 * Возвращает объект соответствующий записи с первичным ключом key или null
	 */
	public User read(String login);

	/** Сохраняет состояние объекта group в базе данных */
	public void update(User User);

	/** Удаляет запись об объекте из базы данных */
	public void delete(User User);

	/**
	 * Возвращает список объектов соответствующих всем записям в базе данных
	 */
	public ArrayList<User> getAll();
}
