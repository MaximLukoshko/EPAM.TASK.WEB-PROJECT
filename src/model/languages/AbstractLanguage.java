package model.languages;

import org.apache.log4j.Logger;

public abstract class AbstractLanguage {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(AbstractLanguage.class);

	private String BulletinBoard = null;
	private String BoardName = null;
	private String YouEnteredAs = null;
	private String ListOfAds = null;
	private String PersonalCabinet = null;

	private String Subject = null;;
	private String Type = null;
	private String Author = null;
	private String LastModifiedDate = null;
	private String Login = null;
	private String Password = null;
	private String Name = null;
	private String Email = null;
	private String Body = null;

	private String LoginButtonName = null;
	private String SignUpButtonName = null;
	private String CancelButtonName = null;
	private String CreateButtonName = null;
	private String LogOutButtonName = null;
	private String MainButtonName = null;
	private String CabinetButtonName = null;

	public String getBulletinBoard() {
		return BulletinBoard;
	}

	public String getBoardName() {
		return BoardName;
	}

	public String getYouEnteredAs() {
		return YouEnteredAs;
	}

	public String getListOfAds() {
		return ListOfAds;
	}

	public String getPersonalCabinet() {
		return PersonalCabinet;
	}

	public String getSubject() {
		return Subject;
	}

	public String getType() {
		return Type;
	}

	public String getAuthor() {
		return Author;
	}

	public String getLastModifiedDate() {
		return LastModifiedDate;
	}

	public String getLogin() {
		return Login;
	}

	public String getPassword() {
		return Password;
	}

	public String getName() {
		return Name;
	}

	public String getEmail() {
		return Email;
	}

	public String getBody() {
		return Body;
	}

	public String getLoginButtonName() {
		return LoginButtonName;
	}

	public String getSignUpButtonName() {
		return SignUpButtonName;
	}

	public String getCancelButtonName() {
		return CancelButtonName;
	}

	public String getCreateButtonName() {
		return CreateButtonName;
	}

	public String getLogOutButtonName() {
		return LogOutButtonName;
	}

	public String getMainButtonName() {
		return MainButtonName;
	}

	public String getCabinetButtonName() {
		return CabinetButtonName;
	}
}
