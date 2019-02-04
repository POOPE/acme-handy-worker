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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jstl:set var="df"><spring:message code="lorem.pattern"/></jstl:set>

<button type="button" name="back" onclick="history.back()"><spring:message code="back"/></button>

<display:table name="lorems" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column titleKey="lorem.ticker">
		<a href="lorem/view.do?id=${row.id}">
			<jstl:out value="${row.ticker}"/>
		</a>
	</display:column>
	<display:column titleKey="lorem.reference">
		<a href="fixuptask/view.do?id=${row.fixupTask.id}">
			<jstl:out value="${row.fixupTask.ticker}"/>
		</a>
	</display:column>
	<display:column class="date" titleKey="lorem.date">
		<fmt:formatDate  value="${row.publishDate}" pattern="${df}" />
	</display:column>
	<display:column property="ticker" titleKey="lorem.ticker"/>
	<display:column>
		<jstl:if test="${!row.locked}">
			<a href="lorem/customer/edit.do?id=${row.id}"><i
				class="fa fa-pencil" aria-hidden="true"></i></a>
		</jstl:if>
	</display:column>
	<display:column>
		<jstl:if test="${!row.locked}">
			<a href="lorem/customer/delete.do?id=${row.id}"><i
				class="fa fa-times" aria-hidden="true"></i></a>
		</jstl:if>
	</display:column>
	<display:column>
		<jstl:if test="${!row.locked}">
			<a href="lorem/customer/lock.do?id=${row.id}"><i
				class="fa fa-floppy-o" aria-hidden="true"></i></a>
		</jstl:if>
		<jstl:if test="${row.locked}">
			<i class="fa fa-lock" aria-hidden="true"></i>
		</jstl:if>
	</display:column>
	
	
</display:table>