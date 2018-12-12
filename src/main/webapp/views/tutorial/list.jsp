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
<spring:message code="tutorial.create" var="mCreate" />

<display:table name="tutorials" id="row" requestURI="tutorial/list.do"
	pagesize="10" class="displaytag">
	<display:column property="title" titleKey="tutorial.title" />
	<display:column property="author" titleKey="tutorial.author" />
	<display:column property="lastUpdate" titleKey="tutorial.lastUpdate" />
	<!-- delete & edit -->
	<security:authorize access="hasRole('HANDYWORKER')">
		<jstl:if test="${row.author.id=userId}">
			<display:column>
				<a href="tutorial/edit.do?id=${row.id}}"><spring:message
						code="tutorial.edit" /></a>
			</display:column>
			<display:column>
				<a href="tutorial/delete.do?id=${row.id}}"><spring:message
						code="tutorial.delete" /></a>
			</display:column>
		</jstl:if>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('HANDYWORKER')">
	<jstl:if test="${createURI!=null}">
		<a href="${createURI}">${mCreate}</a>
	</jstl:if>
</security:authorize>