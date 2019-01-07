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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- receives: messagebox list, messagebox, messages list -->
<jstl:if test="${not empty messageBox}}">
	<a href="messaging/messagebox/<c:out value="${messageBox.parent.id}"/>">Go
		back</a>
	<jstl:out value="${messageBox.name}" />
</jstl:if>
<!-- create messagebox -->
<div>
	<a href="messaging/create.do?=${messageBox.id}">
		<spring:message code="create"/>
	</a>
</div>

<!-- message boxes -->

<display:table name="messageBoxes" id="row"
	requestURI="${boxRequestURI}" pagesize="10" class="displaytag">
	<!-- Name of messagebox links to open the messagebox -->
	<display:column>
		<a href="messaging/view.do?id=${row.id}"> <jstl:out
				value="${row.name}" />
		</a>
	</display:column>
</display:table>

<!-- messages -->

<display:table name="messages" id="row" requestURI="${messageRequestURI}"
	pagesize="10" class="displaytag">
	<display:column property="priority">
		<jstl:out value="${row.priority}"/>
	</display:column>
	<!-- name of message is link to open message -->
	<display:column property="subject">
		<a href="messaging/message.do?id=${row.id}}"> <jstl:out
				value="${row.subject}" />
		</a>
	</display:column>
	<display:column property="sender">
		<jstl:out value="${row.sender.name}"/>
	</display:column>
	<display:column property="deliveryDate">
		<fmt:formatDate value="${row.deliveryDate}" pattern="dd/MM/yyyy" />
	</display:column>
</display:table>
