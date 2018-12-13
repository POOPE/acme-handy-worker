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

<display:table name="fixupTasks" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column property="ticker" titleKey="fixuptask.ticker" />
	<display:column property="publishDate" titleKey="fixuptask.publishdate" />
	<display:column property="description" titleKey="fixuptask.description" />
	<display:column property="maximumPrice" titleKey="fixuptask.price" />
	<display:column property="startDate" titleKey="fixuptask.start" />
	<display:column property="endDate" titleKey="fixuptask.end" />
	<!-- delete & edit -->
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
				<jstl:if test="${row.author.id==userId}">
						<a href="fixuptask/edit.do?id=${row.id}}"><spring:message
								code="edit" /></a>
				</jstl:if>
		</display:column>
		<display:column>
				<jstl:if test="${row.owner.author.id==userId}">
						<a href="fixuptask/customer/delete.do?id=${row.id}}"><spring:message
								code="delete" /></a>
				</jstl:if>
		</display:column>
	</security:authorize>
</display:table>