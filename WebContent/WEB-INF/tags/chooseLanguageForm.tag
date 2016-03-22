<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="processor" required="true" rtexprvalue="true"%>

<form action="${processor }" method="post">
	<c:out value="${language.language}" />
	: <select name="languageSelect" onchange="this.form.submit()">
		<option value="eng"
			<c:if test="${fn:contains(sessionScope.langCode, 'eng')}">selected="selected"</c:if>>English</option>
		<option value="rus"
			<c:if test="${fn:contains(sessionScope.langCode, 'rus')}">selected="selected"</c:if>>Русский</option>
	</select> <br>
</form>
