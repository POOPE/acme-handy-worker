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

<display:table name="fixupApplications" id="row" requestURI="fixupapplication/list.do"
	pagesize="10" class="displaytag">
	<display:column property="fixupTask.ticker" titleKey="fixupapplication.fixup" />
	<display:column property="author" titleKey="fixupapplication.author" />
	<display:column property="publishDate" titleKey="fixupapplication.publishdate" />
	<display:column property="offeredRate" titleKey="fixupapplication.rate" />
	<display:column property="status" titleKey="fixupapplication.status" />
	<!-- delete & edit -->
	<security:authorize access="hasRole('HANDYWORKER')">
		<jstl:if test="${row.author.id=userId}">
			<display:column>
				<a href="fixupapplication/edit.do?id=${row.id}}"><spring:message
						code="edit" /></a>
			</display:column>
			<display:column>
				<a href="fixupapplication/delete.do?id=${row.id}}"><spring:message
						code="delete" /></a>
			</display:column>
		</jstl:if>
	</security:authorize>
</display:table>