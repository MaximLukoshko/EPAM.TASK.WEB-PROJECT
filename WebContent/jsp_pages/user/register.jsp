<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<c:remove var="userLogin" scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register new user</title>
</head>
<body>
	<jsp:include page="../static/header.jsp"></jsp:include>
	<div style="float: right;">
		<comp:chooseLanguage processor="../doChooseLanguage.jsp" />
	</div>
	<comp:layout1Column>
		<h1>
			<c:out value="${language.registerNewUser }" />
		</h1>
		<comp:errorMessage />
		<my:clearErrorMessage />

		<form action="doRegister.jsp" method="post">
			<table>
				<tr>
					<td><c:out value="${language.login }  " />:</td>
					<td><input type="text" name="login"
						value="${sessionScope.userData.login }"></td>
				</tr>
				<tr>
					<td><c:out value="${language.password }  " />:</td>
					<td><input type="password" name="password"
						value="${sessionScope.userData.password }"></td>
				</tr>
				<tr>
					<td><c:out value="${language.name } " />:</td>
					<td><input type="text" name="name"
						value="${sessionScope.userData.name }"></td>
				</tr>
				<tr>
					<td><c:out value="${language.email }" />:</td>
					<td><input type="text" name="email"
						value="${sessionScope.userData.email }"></td>
				</tr>
				<tr>
					<td><input type="submit" value="${language.signUpButtonName }"></td>
					<td><input type="button" value="${language.cancelButtonName }"
						onclick="window.location='<c:remove var="userData" scope="session" /><c:url value="../index.jsp" />';"></td>
				</tr>

			</table>
		</form>
	</comp:layout1Column>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>