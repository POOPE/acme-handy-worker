<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jstl:set var="userId" value="${user.id}" />
<spring:message code="actor.ban" var="mBan" />
<spring:message code="actor.unban" var="mUnBan" />

<div>
	<spring:message code="actor.name" />
	<jstl:out value="${actor.name}" />
	<br />
	<spring:message code="actor.middleName" />
	<jstl:out value="${actor.middleName}" />
	<br />
	<spring:message code="actor.surname" />
	<jstl:out value="${actor.surname}" />
	<br />
	<spring:message code="actor.photo" />
	<img src="<jstl:out value="${actor.photo}" />">
	<br />
	<spring:message code="actor.email" />
	<jstl:out value="${actor.email}" />
	<br />
	<spring:message code="actor.phoneNumber" />
	<jstl:out value="${actor.phoneNumber}" />
	<br />
	<spring:message code="actor.address" />
	<jstl:out value="${actor.address}" />
	<br />

	<jstl:if test="${row.id == userId}">
		<a href="/actor/edit.do"><jstl:out value="${actor.edit}" /></a>
	</jstl:if>

	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="actor.flagged" />
		<jstl:out value="${actor.flagged}" />
		<br />
		<spring:url var="banURI" value="actor/admin/ban.do">
			<spring:param name="userId" value="${row.user.id}" />
		</spring:url>
		<jstl:if test="${!row.getFlagged()}">
			<a href="${banURI}">${mBan}</a>
		</jstl:if>
		<jstl:if test="${row.getFlagged()}">
			<a href="${banURI}">${mUnBan}</a>
		</jstl:if>
	</security:authorize>
</div>
