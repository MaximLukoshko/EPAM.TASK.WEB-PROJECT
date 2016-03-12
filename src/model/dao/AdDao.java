package model.dao;

import java.util.ArrayList;

import model.entity.Ad;

public interface AdDao {
	/** Создает новую запись и соответствующий ей объект */
	public Ad create();

	/**
	 * Возвращает объект соответствующий записи с первичным ключом key или null
	 */
	public Ad read(int key);

	/** Сохраняет состояние объекта group в базе данных */
	public void update(Ad Ad);

	/** Удаляет запись об объекте из базы данных */
	public void delete(Ad Ad);

	/**
	 * Возвращает список объектов соответствующих всем записям в базе данных
	 */
	public ArrayList<Ad> getAll();
}
