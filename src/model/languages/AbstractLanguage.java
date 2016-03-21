package model.languages;

import org.apache.log4j.Logger;

public abstract class AbstractLanguage {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(AbstractLanguage.class);

	protected String BulletinBoard = null;
	protected String BoardName = null;
	protected String YouEnteredAs = null;
	protected String ListOfAds = null;
	protected String PersonalCabinet = null;

	protected String Subject = null;;
	protected String Type = null;
	protected String Author = null;
	protected String LastModifiedDate = null;
	protected String Login = null;
	protected String Password = null;
	protected String Name = null;
	protected String Email = null;
	protected String Body = null;

	protected String LoginButtonName = null;
	protected String SignUpButtonName = null;
	protected String CancelButtonName = null;
	protected String CreateButtonName = null;
	protected String LogOutButtonName = null;
	protected String MainButtonName = null;
	protected String CabinetButtonName = null;

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
