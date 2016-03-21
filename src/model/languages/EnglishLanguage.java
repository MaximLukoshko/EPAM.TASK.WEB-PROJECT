package model.languages;

import org.apache.log4j.Logger;

public class EnglishLanguage extends AbstractLanguage {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(EnglishLanguage.class);

	public EnglishLanguage() {
		super();
		BulletinBoard = "Bulletin Board";
		BoardName = "Figa List";
		YouEnteredAs = "You entered as";
		ListOfAds = "List of Ads";
		PersonalCabinet = "Personal Cabinet";

		Subject = "Subject";
		Type = "Type";
		Author = "Author";
		LastModifiedDate = "Last-Modified-Date";
		Login = "Login";
		Password = "Password";
		this.setName("Name");
		Email = "E-mail";
		Body = "Body";

		LoginButtonName = "Log In";
		SignUpButtonName = "SignUp";
		CancelButtonName = "Cancel";
		CreateButtonName = "Create";
		LogOutButtonName = "Log Out";
		MainButtonName = "Main";
		CabinetButtonName = "Cabinet";
	}

}
