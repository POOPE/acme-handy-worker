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

<!-- browse applications // only available to author -->
<security:authorize access="hasRole('CUSTOMER')">
	<jstl:if test="${fixupTask.author.id == user.id && !fixupTask.locked}">
		<a href="fixupapplication/customer/bytask.do?id=${fixupTask.id}">
			<spring:message code="fixupapplications" />
		</a>
	</jstl:if>
</security:authorize>

<!-- info -->
<div>
	<jstl:out value="${fixupTask.ticker}" />
	<br /> <br /> <b><spring:message code="fixuptask.description" /></b>
	<br />
	<jstl:out value="${fixupTask.description}" />
	<br /> <b><spring:message code="fixuptask.address" /></b> <br />
	<spring:message code="fixuptask.address" />
	<jstl:out value="${fixupTask.address}" />
	<br /> <b><spring:message code="fixuptask.price" /></b> <br />
	<jstl:out value="${fixupTask.maximumPrice}" />
	<br /> <b><spring:message code="fixuptask.date" /></b> <br />
	<jstl:out value="${fixupTask.startDate}" />
	&nbsp;-&nbsp;
	<jstl:out value="${fixupTask.endDate}" />
	<br />
</div>
<!-- phases -->
<jstl:if test="${not empty workPlanPhases}">
	<h3>
		<spring:message code="workplan" />
	</h3>
	<jstl:forEach var="phase" items="${workPlanPhases}">
		<jstl:if test="${user.id == handyWorker.id}">
			<div>
				<a href="fixuptask/handyworker/editphase.do?id=${phase.id}"><i
					class="fa fa-pencil" aria-hidden="true"></i></a> &nbsp;<a
					href="fixuptask/handyworker/deletephase.do?id=${phase.id}"><i
					class="fa fa-times" aria-hidden="true"></i></a>
			</div>
		</jstl:if>
		<div>
			<div>
				<b><jstl:out value="${phase.position}" />&nbsp;-&nbsp;<jstl:out
						value="${phase.title}" /></b>

			</div>
			<div>
				<jstl:out value="${phase.startDate}" />
				&nbsp;-&nbsp;
				<jstl:out value="${phase.endDate}" />
			</div>
			<div>
				<jstl:out value="${phase.description}" />
			</div>


		</div>
	</jstl:forEach>
</jstl:if>

<security:authorize access="hasRole('HANDYWORKER')">
	<!-- apply -->

	<br />
	<jstl:if test="${!fixupTask.locked}">
		<a href="fixupapplication/handyworker/create.do?id=${fixupTask.id}">
			<spring:message code="apply" />
		</a>
	</jstl:if>
	<br />
	<jstl:if test="${fixupTask.locked && user.id == handyWorker.id}">
		<div>
			<a href="fixuptask/handyworker/newphase.do?id=${fixupTask.id}"><spring:message
					code="workplanphase.create" /></a>
		</div>
	</jstl:if>
</security:authorize>
