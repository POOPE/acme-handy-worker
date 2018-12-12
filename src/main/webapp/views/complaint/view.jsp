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


<!-- report -->
<
<security:authorize access="hasRole('REFEREE')">
	<a href="/report/create.do"><spring:message code="complaint.report" /></a>
</security:authorize>
<div>
	<fmt:formatDate value="${row.deliveryDate}" pattern="dd/MM/yyyy" />
</div>
<div>
	<jstl:out value="${complaint.description}" />
</div>
<div>
<!-- display attachments -->
	<jstl:forEach var="attachment" begin="1"
		end="${complaint.attachments.size()}">
		<div style="background-image:url('${attachment}')" class="attachment">
		</div>
	</jstl:forEach>
	<!-- add attachment -->
	<div>
		<form action="/complaint/addAttachment.do?id=${complaint.id}" method="get">
			<input type="text" id="attachment" name="attachment_url">
			<button type="submit">
				<spring:message code="complaint.attachment" />
			</button>
		</form>
	</div>
</div>
