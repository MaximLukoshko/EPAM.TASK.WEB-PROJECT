package model.languages;

import org.apache.log4j.Logger;

public class EnglishLanguage extends AbstractLanguage implements ILanguage {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(EnglishLanguage.class);

	public EnglishLanguage() {
		super();
		BulletinBoard = "Bulletin Board";
		BoardName = "Figa List";
		YouEnteredAs = "You entered as";
		ListOfAds = "List of Ads";
		PersonalCabinet = "Personal Cabinet";
		RegisterNewUser = "Register New User";
		Language = "Language";
		ChangingAd = "Changing Ad";
		CreatingAd = "Creating Ad";

		Subject = "Subject";
		Type = "Type";
		Author = "Author";
		LastModifiedDate = "Last-Modified-Date";
		Login = "Login";
		Password = "Password";
		Name = "Name";
		Email = "E-mail";
		Body = "Body";

		LoginButtonName = "Log In";
		SignUpButtonName = "Sign Up";
		CancelButtonName = "Cancel";
		CreateButtonName = "Create";
		LogOutButtonName = "Log Out";
		MainButtonName = "Main";
		CabinetButtonName = "Cabinet";
		EditButtonName = "Edit";
		DeleteButtonName = "Delete";
		ConfirmButtonName = "Confirm";
	}
}
