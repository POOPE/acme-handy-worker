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

<form:form modelAttribute="fixupApplication"
	action="/fixupapplication/edit.do">
	<form:hidden path="sender" />
	<form:hidden path="container" />
	<form:hidden path="deliveryDate" />
	<form:label path="recipients">
		<spring:message code="message.recipients" />
	</form:label>
	<form:input path="recipients" />
	<form:errors cssClass="error" path="recipients" />

	<form:label path="subject">
		<spring:message code="message.subject" />
	</form:label>
	<form:input path="subject" />
	<form:errors cssClass="error" path="subject" />

	<form:label path="body">
		<spring:message code="message.body" />
	</form:label>
	<form:textarea path="body" />
	<form:errors cssClass="error" path="body" />

	<form:select id="priority" path="priority">
		<form:options items="${priorities}" itemLabel="priority" itemValue="id" />
		<form:option value="0" label="----" />
	</form:select>
	
	<input type="submit" name="save" value="<spring:message code="save"/>"/>
</form:form>