<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="
 " contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:set var="userId" value="${user.id}" />

<!-- info -->
<form:form modelAttribute="tutorial"
	action="/tutorial/handyworker/edit.do">
	<form:hidden path="author" />
	<form:hidden path="lastUpdate"/>
	<div>
		<form:label path="title">
			<spring:message code="tutorial.title" />
		</form:label>
		<form:input path="tutorial.title" />
		<form:errors cssClass="error" path="tutorial.title" />
	</div>
	<div>
		<form:label path="description">
			<spring:message code="tutorial.description" />
		</form:label>
		<form:input path="tutorial.description" />
		<form:errors cssClass="error" path="tutorial.description" />
	</div>
	
	<input type="submit" name="save" value="<spring:message code="save"/>"/>
</form:form>
