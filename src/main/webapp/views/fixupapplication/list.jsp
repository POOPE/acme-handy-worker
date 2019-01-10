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

<display:table name="fixupApplications" id="row"
	requestURI="${requestURI}" pagesize="10" class="displaytag">
	<display:column>
		<a href="fixupapplication/view.do?id=${row.id}"> <spring:message code="view"/>
		</a>
	</display:column>
	<display:column
		titleKey="fixupapplication.fixup">
		<a href="fixuptask/view.do?id=${row.fixupTask.id}"> <jstl:out
				value="${row.fixupTask.ticker}" />
		</a>
	</display:column>
	<display:column property="publishDate"
		titleKey="fixupapplication.publishdate" />
	<display:column property="offeredRate" titleKey="fixupapplication.rate" />
	<display:column property="status" titleKey="fixupapplication.status" />
	<!-- accept/reject -->
	<security:authorize access="hasRole('CUSTOMER')">

		<display:column>
			<jstl:if test="${row.status == 'PENDING'}">
				<a href="fixupapplication/customer/eval.do?appId=${row.id}&status=1">
					<i class="fa fa-check" aria-hidden="true"></i>
				</a>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${row.status=='PENDING'}">
				<a href="fixupapplication/customer/eval.do?appId=${row.id}&status=0">
					<i class="fa fa-times" aria-hidden="true"></i>
				</a>
			</jstl:if>
		</display:column>

	</security:authorize>
	<!-- delete & edit -->
	<security:authorize access="hasRole('HANDYWORKER')">
		<display:column>
			<jstl:if test="${row.author.id==user.id && row.status=='PENDING'}">
				<a href="fixupapplication/handyworker/edit.do?id=${row.id}"><i class="fa fa-pencil"
					aria-hidden="true"></i></a>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${row.author.user.id==user.user.id && row.status=='PENDING'}">
				<a href="fixupapplication/handyworker/delete.do?id=${row.id}"><i
					class="fa fa-times" aria-hidden="true"></i></a>
			</jstl:if>
		</display:column>

	</security:authorize>
</display:table>