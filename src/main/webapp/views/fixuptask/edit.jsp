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


<form:form modelAttribute="fixupTask" action="fixuptask/edit.do">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="author" value="${fixupTask.author.id}" />
	<form:errors cssClass="error" path="author" />
	<form:hidden path="publishDate" />
	<form:errors cssClass="error" path="publishDate" />
	<form:hidden path="ticker" />
	<form:errors cssClass="error" path="ticker" />
	<form:hidden path="locked" />
	<form:errors cssClass="error" path="locked" />
	<form:hidden path="creditCard" value="${fixupTask.creditCard.id}" />
	<form:errors cssClass="error" path="creditCard" />

	<form:label path="description">
		<spring:message code="fixuptask.description" />
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<form:select id="category" path="category">
		<form:options items="${categories}" itemLabel="title" itemValue="id" />
		<form:option value="0" label="----" />
	</form:select>
	<br />
	<form:label path="address">
		<spring:message code="fixuptask.address" />
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	<form:label path="maximumPrice">
		<spring:message code="fixuptask.price" />
	</form:label>
	<form:input path="maximumPrice" />
	<form:errors cssClass="error" path="maximumPrice" />
	<br />
	<form:label path="startDate">
		<spring:message code="fixuptask.start" />
	</form:label>
	<form:input path="startDate" placeholder="dd/MM/yyyy" />
	<form:errors cssClass="error" path="startDate" />
	<br />
	<form:label path="endDate">
		<spring:message code="fixuptask.end" />
	</form:label>
	<form:input path="endDate" placeholder="dd/MM/yyyy" />
	<form:errors cssClass="error" path="endDate" />
	<br />
	<form:label path="warranty">
		<spring:message code="fixuptask.warranty" />
	</form:label>
	<form:select id="warranty" path="warranty">
		<form:options items="${warranties}" itemLabel="title" itemValue="id" />
		<form:option value="0" label="----" />
	</form:select>
	<form:errors cssClass="error" path="warranty" />
	<br />
	<input type="submit" name="save" value="<spring:message code="save"/>" />
</form:form>
