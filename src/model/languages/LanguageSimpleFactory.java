package model.languages;

import org.apache.log4j.Logger;

public class LanguageSimpleFactory implements ILanguageSimpleFactory {
	private static final Logger log = Logger.getLogger(LanguageSimpleFactory.class);

	@Override
	public AbstractLanguage getLanguage(String language) {

		if (language.equals("eng")) {
			return new EnglishLanguage();
		} else if (language.equals("rus")) {

		}
		log.info("Not Valid parameter of language. The value of parameter is " + language);
		return new EnglishLanguage();
	}
}
