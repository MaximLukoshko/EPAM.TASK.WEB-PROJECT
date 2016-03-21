package model.languages;

import org.apache.log4j.Logger;

public class AbstractLanguage {
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

	public void setBulletinBoard(String bulletinBoard) {
		BulletinBoard = bulletinBoard;
	}

	public void setBoardName(String boardName) {
		BoardName = boardName;
	}

	public void setYouEnteredAs(String youEnteredAs) {
		YouEnteredAs = youEnteredAs;
	}

	public void setListOfAds(String listOfAds) {
		ListOfAds = listOfAds;
	}

	public void setPersonalCabinet(String personalCabinet) {
		PersonalCabinet = personalCabinet;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public void setType(String type) {
		Type = type;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setBody(String body) {
		Body = body;
	}

	public void setLoginButtonName(String loginButtonName) {
		LoginButtonName = loginButtonName;
	}

	public void setSignUpButtonName(String signUpButtonName) {
		SignUpButtonName = signUpButtonName;
	}

	public void setCancelButtonName(String cancelButtonName) {
		CancelButtonName = cancelButtonName;
	}

	public void setCreateButtonName(String createButtonName) {
		CreateButtonName = createButtonName;
	}

	public void setLogOutButtonName(String logOutButtonName) {
		LogOutButtonName = logOutButtonName;
	}

	public void setMainButtonName(String mainButtonName) {
		MainButtonName = mainButtonName;
	}

	public void setCabinetButtonName(String cabinetButtonName) {
		CabinetButtonName = cabinetButtonName;
	}
	
	
}
