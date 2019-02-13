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
<form:form id="finderform" modelAttribute="finder" action="finder/handyworker/find.do">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="handyWorker" value="${finder.handyWorker.id}"/>
	<form:errors cssClass="error" path="handyWorker" />
	<form:hidden path="creationDate"/>
	<form:errors cssClass="error" path="creationDate" />
	<form:hidden path="fixUpTasks"/>
	<form:errors cssClass="error" path="fixUpTasks" />
	<div>
		<form:label path="keyWord">
			<spring:message code="finder.keyWord" />
		</form:label>
		<form:input path="keyWord" />
		<form:errors cssClass="error" path="keyWord" />
	</div>
	<div>
		<form:label path="category">
			<spring:message code="finder.category" />
		</form:label>
		<form:input path="category" />
		<form:errors cssClass="error" path="category" />
	</div>
	<div>
		<form:label path="warranty">
			<spring:message code="finder.warranty" />
		</form:label>
		<form:input path="warranty" />
		<form:errors cssClass="error" path="warranty" />
	</div>
	<div>
		<form:label path="minDate">
			<spring:message code="finder.minDate" />
		</form:label>
		<form:input path="minDate" />
		<form:errors cssClass="error" path="minDate" />
	</div>
	<div>
		<form:label path="maxDate">
			<spring:message code="finder.maxDate" />
		</form:label>
		<form:input path="maxDate" />
		<form:errors cssClass="error" path="maxDate" />
	</div>
	<div>
		<form:label path="minPrice">
			<spring:message code="finder.minPrice" />
		</form:label>
		<form:input path="minPrice" />
		<form:errors cssClass="error" path="minPrice" />
	</div>
	<div>
		<form:label path="maxPrice">
			<spring:message code="finder.maxPrice" />
		</form:label>
		<form:input path="maxPrice" />
		<form:errors cssClass="error" path="maxPrice" />
	</div>
	<input type="submit" name="save" value="<spring:message code="save"/>" />
</form:form>

<display:table name="fixupTasks" id="row" 
pagesize="10" class="displaytag"> 
	<display:column property="ticker" titleKey="fixuptask.ticker" /> 
	<display:column property="publishDate" titleKey="fixuptask.publishdate" /> 
	<display:column property="description" titleKey="fixuptask.description" /> 
	<display:column property="maximumPrice" titleKey="fixuptask.price" /> 
 	<display:column property="startDate" titleKey="fixuptask.start" /> 
 	<display:column property="endDate" titleKey="fixuptask.end" /> 
 </display:table> 


