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
	action="fixupapplication/handyworker/edit.do">
	<form:hidden path="fixupTask" value="${fixupApplication.fixupTask.id}"/>
	<form:errors cssClass="error" path="fixupTask" />
	<form:hidden path="author" value="${fixupApplication.author.id}"/>
	<form:errors cssClass="error" path="author" />
	<form:hidden path="publishDate"/>
	<form:errors cssClass="error" path="publishDate" />
	<form:hidden path="status"/>
	<form:errors cssClass="error" path="status" />
	<form:label path="offeredRate">
		<spring:message code="fixupapplication.rate" />
	</form:label>
	<form:input path="offeredRate" />
	<form:errors cssClass="error" path="offeredRate" />
	<br/>
	<input type="submit" name="save" value="<spring:message code="save"/>"/>
</form:form>
