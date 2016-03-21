package model.languages;

import org.apache.log4j.Logger;

public class LanguageSimpleFactory implements ILanguageSimpleFactory {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(LanguageSimpleFactory.class);

	@Override
	public AbstractLanguage getLanguage(String language) {
		AbstractLanguage _language;

		if (language.equals("russian")) {

		}

		_language = new EnglishLanguage();
		return _language;
	}
}
