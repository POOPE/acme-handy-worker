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

	<form:hidden path="laws" value=""/>


	<form:hidden path="terms" id="termsfield" value=""/>

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
		<div id="termslist">
			<jstl:forEach items="${warranty.terms}" var="term">
				<div class="list-item" onclick="removeterms(this)" id="${term}">
					<jstl:out value="${term}"></jstl:out>
					&nbsp;<i class="fa fa-times" aria-hidden="true"></i>
				</div>
			</jstl:forEach>
		</div>
		<input type="text" id="terminput" />
		<button type="button" onclick="addItem(); return false;"
			id="addterm">Add</button>
			<form:errors cssClass="error" path="terms" />
	</div>


	<input type="submit" name="save" value="<spring:message code="save"/>" />
</form:form>
