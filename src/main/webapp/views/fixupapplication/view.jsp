<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jstl:set var="userId" value="${user.id}" />

<!-- info -->
<div>
	<span>By &nbsp; <jstl:out
			value="${fixupApplication.author.name}" />&nbsp;<jstl:out
			value="${fixupApplication.author.surname}" /></span> <br />

	<jstl:out value="${fixupApplication.publishDate}" />
	<br /> <br /> <b><spring:message code="fixupapplication.rate" /></b>
	<br />
	<jstl:out value="${fixupApplication.offeredRate}" />
	<br /> <b><spring:message code="fixupapplication.status" /></b> <br />
	<jstl:out value="${fixupApplication.status}" />
	<br /> <br /> <b><spring:message code="fixupapplication.comment" /></b>
</div>
<div>
	<jstl:forEach var="comment" items="${fixupApplication.comments}">
		<div>
			<jstl:out value="${comment}" />
			<br />
		</div>
	</jstl:forEach>
</div>
<!-- add comment -->
<security:authorize access="hasAnyRole('HANDYWORKER','CUSTOMER')">
	<form action="fixupapplication/addcomment.do" method="get">
		<input type="hidden" value="${fixupApplication.id}" name="id">
		<input type="text" id="comment" name="c">
		<button type="submit">
			<spring:message code="fixupapplication.addcomment" />
		</button>
	</form>
</security:authorize>
<!-- edit -->
<jstl:if test="${fixupApplication.fixupTask.locked}">
	<a href=""><spring:message code="edit" /></a>
</jstl:if>

