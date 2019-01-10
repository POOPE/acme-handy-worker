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


<display:table name="categories" id="row" requestURI="/category/admin/list.do"
	pagesize="10" class="displaytag">
	<display:column property="title" titleKey="category.title" />
	<!-- delete & edit -->
	
		
			<display:column>
				<a href="category/admin/edit.do?id=${row.id}"><spring:message
						code="category.edit" /></a>
			</display:column>
			<display:column>
				<a href="category/admin/delete.do?id=${row.id}"><spring:message
						code="category.delete" /></a>
			</display:column>
		
	
</display:table>

		<div>
			<a href="category/admin/create.do">
			<spring:message code="category.create" /></a>
		</div>