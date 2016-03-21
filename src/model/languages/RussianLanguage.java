package model.languages;

import org.apache.log4j.Logger;

public class RussianLanguage extends AbstractLanguage {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(RussianLanguage.class);
	
	public RussianLanguage() {
		super();
		BulletinBoard = "Доска объявлений";
		BoardName = "Фиговый Листок";
		YouEnteredAs = "Вы вошли как";
		ListOfAds = "Список объявлений";
		PersonalCabinet = "Личный кабинет";
		RegisterNewUser = "Регистрация нового пользователя";

		Subject = "Название";
		Type = "Тип";
		Author = "Автор";
		LastModifiedDate = "Дата последнего изменения";
		Login = "Логин";
		Password = "Пароль";
		Name = "Имя";
		Email = "E-mail";
		Body = "обЪявление";

		LoginButtonName = "Войти";
		SignUpButtonName = "Зарегистрироваться";
		CancelButtonName = "Отмена";
		CreateButtonName = "Создать";
		LogOutButtonName = "Выйти";
		MainButtonName = "Главная";
		CabinetButtonName = "Кабинет";
		EditButtonName = "Ред.";
		DeleteButtonName = "Уд.";
	}
}
