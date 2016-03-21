<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<fmt:requestEncoding value="UTF-8" />

<my:chooseLanguage language="${param.languageSelect }" />
<c:set var="langCode" scope="session" value="${param.languageSelect }" />
<c:out value="${param.languageSelect }" />
<c:redirect url="/jsp_pages/index.jsp"></c:redirect>

