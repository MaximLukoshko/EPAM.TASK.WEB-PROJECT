<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<c:if test="${sessionScope.langCode==null }">
	<c:set var="langCode" scope="session" value="eng" />
</c:if>
<c:if
	test="${!fn:contains(sessionScope.langCode, param.languageSelect)}">
	<my:chooseLanguage language="${param.languageSelect }" />
	<c:set var="langCode" scope="session" value="${param.languageSelect }" />
</c:if>
<c:set var="language" scope="session" value="${sessionScope.Language }" />

<%-- Обработать параметр сортировки --%>
<c:if test="${param.sort!=null}">
	<c:set var="sort" scope="session" value="${param.sort}" />
</c:if>
<%-- Обработать параметр направления сортировки --%>
<c:if test="${param.dir!=null}">
	<c:set var="dir" scope="session" value="${param.dir}" />
</c:if>
<%-- Общая декоративная "шапка" для всех страниц --%>
<div style="background-color: #a0c8ff; padding: 10px;">
	<table style="width: 100%;">
		<tr>
			<td><img src="<c:url  value="/resources/figa.png"/>" width="60"
				height="60" border="0" align="left"></td>
			<td>
				<div
					style="font-family: 'Trebuchet'; font-size: 30px; height: 53px; margin-left: 80px;">
					<c:out value="${language.bulletinBoard }  " />
					"
					<c:out value="${language.boardName }" />
					"
				</div>
			</td>
			<td style="float: right"><comp:chooseLanguageForm processor="" />
		</tr>
	</table>
</div>
<%-- Панель отображается если пользователь аутентифицирован --%>
<div style="background-color: #ccc; padding: 5px">
	<comp:mainButton />
	<c:if test="${sessionScope.authUser!=null}">

		<comp:cabinetButton />
		<div
			style="background-color: #ccc; border: 1px solid black; float: right; margin: 1px; margin-top: 1px; padding: 1px 0px; text-align: center; width: 150px;">
			<a href="<c:url value= "/jsp_pages/user/doLogout.jsp" />"><c:out
					value="${language.logOutButtonName }  " /></a>
		</div>
		<div style="float: right;">
			<c:out value="${language.youEnteredAs }  " />
			:
			<c:out value="${sessionScope.authUser.name}" />
			(
			<c:out value="${sessionScope.authUser.login}" />
			)
		</div>

		<div style="clear: both;"></div>
	</c:if>
</div>
