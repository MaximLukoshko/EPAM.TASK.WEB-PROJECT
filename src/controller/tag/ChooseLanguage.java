package controller.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import model.languages.LanguageSimpleFactory;

public class ChooseLanguage extends SimpleTagSupport {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ChooseLanguage.class);

	private String language;

	@Override
	public void doTag() throws JspException, IOException {
		LanguageSimpleFactory languageSimpleFactory = new LanguageSimpleFactory();
		getJspContext().setAttribute("Language", languageSimpleFactory.getLanguage(language),
				PageContext.SESSION_SCOPE);
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
