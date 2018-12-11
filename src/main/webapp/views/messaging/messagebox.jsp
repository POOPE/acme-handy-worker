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

<!-- receives: messagebox list, messagebox, messages list -->
<jstl:if test="${not empty messagebox}">
	<a href="messaging/messagebox/<c:out value="${messagebox.parent.id}"/>">Go
		back</a>
	<jstl:out value="${messagebox.name}" />
</jstl:if>
<!-- message boxes -->
<jstl:if test="${not empty messageboxes}">
	<display:table name="messageboxes" id="row" requestURI="${requestURI}"
		pagesize="10" class="displaytag">
		<!-- Name of messagebox links to open the messagebox -->
		<display:column>
			<a href="messaging/box.do?id=${row.id}}"> <jstl:out
					value="${row.name}" />
			</a>
		</display:column>
	</display:table>
</jstl:if>
<!-- messages -->
<jstl:if test="${not empty messages}">
	<display:table name="messages" id="row" requestURI="${requestURI}"
		pagesize="10" class="displaytag">
		<display:column property="priority" titleKey="row.priority"/>	
		<!-- name of message is link to open message -->
		<display:column>
			<a href="messaging/message.do?id=${row.id}}"> <jstl:out
					value="${row.subject}" />
			</a>
		</display:column>
		<display:column property="sender" titleKey="row.sender"/>
		<display:column property="received">
			<fmt:formatDate value="${row.deliveryDate}" pattern="dd/MM/yyyy" /> 
		</display:column>
	</display:table>
</jstl:if>