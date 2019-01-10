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


<display:table name="warranties" id="row" requestURI="/warranty/admin/list.do"
	pagesize="10" class="displaytag">
	<display:column property="title" titleKey="warranty.title" />
	<!-- delete & edit -->
	
		
			<display:column>
				<a href="warranty/admin/edit.do?id=${row.id}"><spring:message
						code="warranty.edit" /></a>
			</display:column>
			<display:column>
				<a href="warranty/admin/delete.do?id=${row.id}}"><spring:message
						code="warranty.delete" /></a>
			</display:column>
		
	
</display:table>

		<div>
			<a href="warranty/admin/create.do">
			<spring:message code="warranty.create" /></a>
		</div>