package model.dao;

import java.util.ArrayList;

import model.entity.Ad;
import model.entity.User;

public interface AdDao {
	/** Создает новую запись и соответствующий ей объект */
	public void create(Ad ad);

	/**
	 * Возвращает объект соответствующий записи с первичным ключом key или null
	 */
	public Ad read(int key);

	public ArrayList<Ad> read(User authUser);

	/** Сохраняет состояние объекта group в базе данных */
	public void update(Ad Ad);

	/** Удаляет запись об объекте из базы данных */
	public void delete(Ad Ad);

	/**
	 * Возвращает список объектов соответствующих всем записям в базе данных
	 */
	public ArrayList<Ad> getAll();
}
