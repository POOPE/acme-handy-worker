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

<jstl:set var="userId" value="${user.id}" />

<!-- info -->
<div>
	<h2><jstl:out value="${fixupapplication.title}" /></h2>
	<br />
	<span>By&nbps;<jstl:out value="${fixupApplication.author.name}" /></span>
	<br />
	<jstl:out value="${fixupApplication.publishDate}" />
	<br />
	<spring:message code="fixupapplication.rate" />
	<jstl:out value="${fixupApplication.offeredRate}" />
	<br />
	<spring:message code="fixupapplication.status" />
	<jstl:out value="${fixupApplication.status}" />
</div>
<jstl:if test="${fixupApplication.fixupTask.locked}">
	<a href=""><spring:message code="edit"/></a>
</jstl:if>

