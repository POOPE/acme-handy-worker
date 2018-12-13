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
		<jstl:out value="${tutorial.title}" />
	</h2>
	<br /> <span>By &nbsp; <jstl:out
			value="${tutorial.author.name}" /></span> <br />
	<jstl:out value="${tutorial.lastUpdate}" />
	<br />

	<jstl:out value="${tutorial.offeredRate}" />
	<br />
	<jstl:out value="${tutorial.status}" />
</div>
<div>
	<jstl:forEach var="section" begin="1" end="${tutorial.sections.size()}">
		<div>
			<jstl:out value="${section.title}" />
			<jstl:out value="${section.description}" />
			<jstl:forEach var="photo" begin="1" end="${sections.photos.size()}">
				<div style="background-image:url('${photo}')" class="photo"></div>
			</jstl:forEach>
			<br />
		</div>
	</jstl:forEach>
</div>

<div>
	<jstl:forEach var="photo" begin="1" end="${tutorial.photos.size()}">
		<div style="background-image:url('${photo}')" class="photo"></div>
	</jstl:forEach>
</div>

<div>
		<div style="background-image:url('${sponsorship.bannerUrl}')" class="banner"></div>
	
</div>

<!-- edit -->

<security:authorize access="hasRole('HANDYWORKER')">
	<jstl:if test="${tutorial.author.id=userId}">
		<a href="tutorial/edit.do?id=${tutorial.id}}"><spring:message
						code="tutorial.edit" /></a>
	</jstl:if>
</security:authorize>


