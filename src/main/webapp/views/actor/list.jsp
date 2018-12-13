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

<spring:message code="actor.ban" var="mBan" />
<spring:message code="actor.unban" var="mUnBan" />

<display:table name="actors" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column property="name" titleKey="actor.name" />
	<display:column property="middleName" titleKey="actor.middleName" />
	<display:column property="surname" titleKey="actor.surname" />
	<display:column property="photo" titleKey="actor.photo" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phoneNumber" titleKey="actor.phoneNumber" />
	<display:column property="address" titleKey="actor.address" />
	<!-- delete & edit -->
	<security:authorize access="hasRole('ADMIN')">
	<display:column property="flagged" titleKey="actor.flagged" />
		<display:column>
			<spring:url var="banURI" value="actor/admin/ban.do">
				<spring:param name="userId" value="${row.user.id}" />
			</spring:url>
			<jstl:if test="${!row.getFlagged()}">
				<a href="${banURI}">${mBan}</a>
			</jstl:if>
			<jstl:if test="${row.getFlagged()}">
				<a href="${banURI}">${mUnBan}</a>
			</jstl:if>
		</display:column>
	</security:authorize>
</display:table>	
<jstl:if test="${customMessage != null}">
	<br />
	<span class="message"><spring:message code="${customMessage}" /></span>
</jstl:if>