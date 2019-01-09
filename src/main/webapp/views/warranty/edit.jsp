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
<form:form modelAttribute="waranty"
	action="/waranty/admin/edit.do">
	
	<div>
		<form:label path="title">
			<spring:message code="waranty.title" />
		</form:label>
		<form:input path="waranty.title" />
		<form:errors cssClass="error" path="waranty.title" />
	</div>
	<div>
		<form:label path="terms">
			<spring:message code="waranty.terms" />
		</form:label>
		<form:input path="waranty.terms" />
		<form:errors cssClass="error" path="waranty.terms" />
	</div>
	
	<input type="submit" name="save" value="<spring:message code="warranty.save"/>"/>
</form:form>
