<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="processor" required="true" rtexprvalue="true"%>

<c:if test="${sessionScope.authUser==null }">
	<form action="${processor }" method="post">
		<table>
			<tr>
				<td><c:out value="${language.login }  " />:</td>
				<td><input type="text" name="login"
					value="${sessionScope.userLogin }"></td>
			</tr>
			<tr>
				<td><c:out value="${language.password }  " />:</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<%-- <td>&nbsp;</td>--%>
				<td><input type="submit" value="<c:out value="${language.loginButtonName }  " />"></td>
			</tr>
		</table>
	</form>
</c:if>
