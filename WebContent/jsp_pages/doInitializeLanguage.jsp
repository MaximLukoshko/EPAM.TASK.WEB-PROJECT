<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<c:set var="langCode" scope="session" value="eng" />
<my:chooseLanguage language="eng" />
<c:redirect url="/jsp_pages/index.jsp" />
