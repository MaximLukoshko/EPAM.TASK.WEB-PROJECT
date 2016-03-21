<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
<%@ attribute name="processor" required="true" rtexprvalue="true"%>
	--%>

<form action="../jsp_pages/doChooseLanguage.jsp" method="post">
	<c:out value="${language.language}" />
	: <select name="languageSelect">
		<option value="eng">English</option>
		<option value="rus">Русский</option>
	</select> <input type="submit"
		value="<c:out value="${language.loginButtonName }  " />">
</form>
