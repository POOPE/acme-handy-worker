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
<script type="text/javascript" src="scripts/warranty.js"></script>

<!-- info -->
<form:form modelAttribute="warranty" action="warranty/admin/save.do">

	<form:hidden path="laws" id="lawsfield" />
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="locked" />


	<div>
		<form:label path="title">
			<spring:message code="warranty.title" />
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
	</div>
	<div>
		<form:label path="terms">
			<spring:message code="warranty.terms" />
		</form:label>
		<form:input path="terms" />
		<form:errors cssClass="error" path="terms" />
	</div>
	<div>
		<form:label path="laws">
			<spring:message code="warranty.laws" />
		</form:label>
		<div id="lawscontainer">
			<jstl:forEach items="${warranty.laws}" var="law">
				<div class="list-item" id="${law.id}" onclick="removediv(this)">
					<div>
						<b><jstl:out value="${law.title}" /></b>
					</div>
					<div>
						<jstl:out value="${law.relevantText}" />
					</div>
				</div>
			</jstl:forEach>
		</div>
		<div>
			<select id="lawselect">
				<jstl:forEach items="${laws}" var="law">
					<option value="${law.id}"><jstl:out value="${law.title}"/></option>
				</jstl:forEach>
				
			</select>
			<button type="button" onclick="addItem(); return false;" id="addlaw">Add</button>
		</div>
		<form:errors cssClass="error" path="laws" />
	</div>


	<input type="submit" name="save" value="<spring:message code="save"/>" />
</form:form>
