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
<div>
	<h2>
		<jstl:out value="${warranty.title}" />
	</h2>
	
	<jstl:out value="${warranty.terms}" />
	<br />
</div>
<div>
	<jstl:forEach var="laws" begin="1" end="${warranty.laws.size()}">
		<div>
			<jstl:out value="${warranty.laws.title}" />
			<jstl:out value="${warranty.laws.relevantText}" />
			
			<br />
		</div>
	</jstl:forEach>
</div>




<!-- edit -->

<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${warranty.author.id=userId}">
		<a href="warranty/edit.do?id=${warranty.id}}"><spring:message
						code="warranty.edit" /></a>
	</jstl:if>
</security:authorize>


