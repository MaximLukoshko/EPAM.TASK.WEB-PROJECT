<%@ tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="my" uri="/WEB-INF/taglibs/taglib.tld"%>

<%@ attribute name="adListing" required="true" rtexprvalue="true"
	type="java.util.AbstractCollection"%>
<%@ attribute name="editMode" required="false" rtexprvalue="false"
	type="java.lang.Boolean"%>


<c:if test="${adListing!=null }">
	<table style="width: 100%;">
		<tr bgcolor="#cccccc" align="center">
			<td><c:out value="${language.subject }  " /><br> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="subject"/>
				<c:param name="dir" value="asc"/></c:url>"><img
					src="<c:url value="/resources/asc.png"/>" width="20" height="19"
					border="0"></a> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="subject"/>
				<c:param name="dir" value="desc"/>
				</c:url>"><img
					src="<c:url value="/resources/desc.png"/>" width="20" height="19"
					border="0"></a>
			</td>
			<td style="width: 20%"><c:out value="${language.author }  " /><br> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="author"/>
				<c:param name="dir" value="asc"/>
				</c:url>"><img
					src="<c:url value="/resources/asc.png"/>" width="20" height="19"
					border="0"></a> <a
				href="<c:url value="${pageContext.request.requestURL}"> <c:param name="sort" value="author"/><c:param name="dir" value="desc"/>				</c:url>"><img
					src="<c:url value="/resources/desc.png"/>" width="20" height="19"
					border="0"></a>
			</td>
			<td style="width: 30%"><c:out value="${language.lastModifiedDate }  " /><br> <a
				href="<c:url value="${pageContext.request.requestURL}">
				<c:param name="sort" value="date"/>
				<c:param name="dir" value="asc"/>
				</c:url>"><img
					src="<c:url value="/resources/asc.png"/>" width="20" height="19"
					border="0"></a> <a
				href="<c:url value="${pageContext.request.requestURL}">	
				<c:param	name="sort" value="date"/>
				<c:param name="dir" value="desc"/></c:url>">
					<img src="<c:url value="/resources/desc.png"/>" width="20"
					height="19" border="0">
			</a>
			</td>
		</tr>
		<c:forEach items="${adListing}" var="ad">
			<tr valign="middle" align="center">
				<td><table style="width: 100%">
						<tr valign="middle" align="center">
							<td><a
								href="<c:url value="/jsp_pages/ad/viewAd.jsp"><c:param name="id" value="${ad.id}" /></c:url>">
									<c:out value="${ad.subject}" />
							</a></td>
							<c:if test="${editMode==true}">
								<td style="width: 30%;"><comp:editButton ad="${ad}" /> <comp:deleteButton
										ad="${ad}" /></td>
							</c:if>
						</tr>
					</table></td>
				<td><c:out value="${ad.authorName}" /></td>
				<td><fmt:formatDate pattern="hh:mm:ss dd-MM-yyyy"
						value="${ad.lastModifiedDate}" /></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
