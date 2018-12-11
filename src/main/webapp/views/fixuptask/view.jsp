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
	<jstl:out value="${fixupTask.ticker}" />
	<br />
	<jstl:out value="${fixupTask.description}" />
	<br />
	<spring:message code="fixuptask.address" />
	<jstl:out value="${fixupTask.address}" />
	<br />
	<spring:message code="fixuptask.price" />
	<jstl:out value="${fixupTask.maximumPrice}" />
	<br />
	<jstl:out value="${fixupTask.startDate}" />
	&nbsp;-&nbsp;
	<jstl:out value="${fixupTask.endDate}" />
	<br />
</div>
<!-- phases -->
<spring:message></spring:message>
<jstl:if test="${fixupTask.locked}">
	<jstl:forEach var="phase" begin="1" end=${fixupTask.phases.size()}}>
		<div>
			<div>
				<h3>
					<jstl:out value="${phase.position} }" />
					<a href="/workplanphase/delete.do?id=${phase.id}}"><spring:message
							code="workplanphase.delete" /></a> <a
						href="/workplanphase/moveup.do?id=${phase.id}}"><spring:message
							code="workplanphase.moveup" /></a> <a
						href="/workplanphase/movedown.do?id=${phase.id}}"><spring:message
							code="workplanphase.movedown" /></a>
				</h3>
				<h2>
					<jstl:out value="${phase.position} }" />
				</h2>
			</div>
			<div>
				<jstl:out value="${phase.startDate} }" />
				&nbsp;-&nbsp;
				<jstl:out value="${phase.endDate} }" />
			</div>
			<div>
				<jstl:out value="${phase.description} }" />
			</div>


		</div>
	</jstl:forEach>
</jstl:if>

<security:authorize access="hasRole('HANDYWORKER')">
	<jstl:if test="${!fixupTask.locked}">
		<div>
			<a href="handyworker/fixupapplication/create.do"><spring:message
					code="apply" /></a>
		</div>
	</jstl:if>
	<jstl:if test="${fixupTask.locked}">
		<div>
			<a href="handyworker/workplanphase/create.do"><spring:message
					code="workplanphase.create" /></a>
		</div>
	</jstl:if>
</security:authorize>
