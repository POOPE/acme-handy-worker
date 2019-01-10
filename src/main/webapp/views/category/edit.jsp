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


<!-- info -->
<form:form modelAttribute="category"
	action="category/admin/edit.do">
	
	
	
	<form:hidden path="parent" value="${category.parent.id}"/>
	<form:errors cssClass="error" path="parent" />
	
	<div>
		<form:label path="title">
			<spring:message code="category.title" />
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
	</div>
	
	
	<input type="submit" name="save" value="<spring:message code="save"/>"/>
</form:form>
