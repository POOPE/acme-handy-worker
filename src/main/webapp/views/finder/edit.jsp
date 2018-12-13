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
<form:form modelAttribute="finder"
	action="/finder/handyworker/edit.do">
	<form:hidden path="handyWorker" />
	<form:hidden path="creationDate"/>
	<form:hidden path="fixUpTasks"/>
	<div>
		<form:label path="keyWord">
			<spring:message code="finder.keyWord" />
		</form:label>
		<form:input path="finder.keyWord" />
		<form:errors cssClass="error" path="finder.keyWord" />
	</div>
	<div>
		<form:label path="category">
			<spring:message code="finder.category" />
		</form:label>
		<form:input path="finder.category" />
		<form:errors cssClass="error" path="finder.category" />
	</div>
	<div>
		<form:label path="warranty">
			<spring:message code="finder.warranty" />
		</form:label>
		<form:input path="finder.warranty" />
		<form:errors cssClass="error" path="finder.warranty" />
	</div>
	<div>
		<form:label path="minDate">
			<spring:message code="finder.minDate" />
		</form:label>
		<form:input path="finder.minDate" />
		<form:errors cssClass="error" path="finder.minDate" />
	</div>
	<div>
		<form:label path="maxDate">
			<spring:message code="finder.maxDate" />
		</form:label>
		<form:input path="finder.maxDate" />
		<form:errors cssClass="error" path="finder.maxDate" />
	</div>
	<div>
		<form:label path="minPrice">
			<spring:message code="finder.minPrice" />
		</form:label>
		<form:input path="finder.minPrice" />
		<form:errors cssClass="error" path="finder.minPrice" />
	</div>
	<div>
		<form:label path="maxPrice">
			<spring:message code="finder.maxPrice" />
		</form:label>
		<form:input path="finder.maxPrice" />
		<form:errors cssClass="error" path="finder.maxPrice" />
	</div>

	<input type="submit" name="save" value="<spring:message code="save"/>"/>
</form:form>
