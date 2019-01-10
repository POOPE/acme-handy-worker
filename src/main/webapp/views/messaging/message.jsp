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

<jstl:if test="${not empty message}">
	<!-- subject -->
	<div>
		<jstl:out value="${message.subject}" />
	</div>
	<!-- sender + send message to sender -->
	<div class="tooltip">
		<span class="tooltiptext">Send message to this user</span> <span>
			From&nbsp; <a
			href="messaging/send.do?recipients=${message.sender.id}"> <jstl:out
					value="${message.sender.user.username}" />
		</a>
		</span>
	</div>
	<!-- delivery date -->
	<div>
		<fmt:formatDate value="${row.deliveryDate}" pattern="dd/MM/yyyy" />
	</div>
	<!-- body -->
	<div>
		<textarea name="body">
			<jstl:out value="${row.body}" /> </textarea>
	</div>
	
</jstl:if>