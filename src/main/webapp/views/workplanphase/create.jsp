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

<form:form modelAttribute="workPlanPhase" action="fixuptask/handyworker/savephase.do">
	<form:hidden path="fixupTask" value="${workPlanPhase.fixupTask.id}"/>
	<form:hidden path="position"/>
	<!-- title -->
	<form:label path="title">
		<spring:message code="workplanphase.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br/>
	<!-- description -->
	<form:label path="description">
		<spring:message code="workplanphase.description"/>
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br/>
	<!-- start date -->
	<form:label path="startDate">
		<spring:message code="workplanphase.start"/>
	</form:label>
	<form:input path="startDate" placeholder="dd/MM/yyyy"/>
	<form:errors cssClass="error" path="startDate"/>
	<br/>
	<!-- end date -->
	<form:label path="endDate">
		<spring:message code="workplanphase.end"/>
	</form:label>
	<form:input path="endDate" placeholder="dd/MM/yyyy"/>
	<form:errors cssClass="error" path="endDate"/>
	<br/>
	<!-- submit -->
	<input type="submit" name="save" value="<spring:message code="save"/>"/>
</form:form>
<a href="/fixuptask/view.do?id=${fixuptask.id}}"><spring:message code="cancel"/></a>