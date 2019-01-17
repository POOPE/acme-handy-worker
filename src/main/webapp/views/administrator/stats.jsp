

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<h4><spring:message code="stats.tasksPerUser" /></h4>

<spring:message code="stats.average" />
<jstl:out value="${stats1[0]}" />
<br />

<spring:message code="stats.minimum" />
<jstl:out value="${stats1[1]}" />
<br />

<spring:message code="stats.maximum" />
<jstl:out value="${stats1[2]}" />
<br />

<spring:message code="stats.deviation" />
<jstl:out value="${stats1[3]}" />
<br />

<h4><spring:message code="stats.applicationPerTask" /></h4>

<spring:message code="stats.average" />
<jstl:out value="${stats2[0]}" />
<br />

<spring:message code="stats.minimum" />
<jstl:out value="${stats2[1]}" />
<br />

<spring:message code="stats.maximum" />
<jstl:out value="${stats2[2]}" />
<br />

<spring:message code="stats.deviation" />
<jstl:out value="${stats2[3]}" />
<br />

<h4><spring:message code="stats.pricePerTask" /></h4>

<spring:message code="stats.average" />
<jstl:out value="${stats3[0]}" />
<br />

<spring:message code="stats.minimum" />
<jstl:out value="${stats3[1]}" />
<br />

<spring:message code="stats.maximum" />
<jstl:out value="${stats3[2]}" />
<br />

<spring:message code="stats.deviation" />
<jstl:out value="${stats3[3]}" />
<br />

<h4><spring:message code="stats.pendingApplications" /></h4>
<jstl:out value="${stats4}" />
<br />

<h4><spring:message code="stats.acceptedApplications" /></h4>
<jstl:out value="${stats5}" />
<br />

<h4><spring:message code="stats.rejectedApplications" /></h4>
<jstl:out value="${stats6}" />
<br />

<h4><spring:message code="stats.lapsedApplications" /></h4>
<jstl:out value="${stats7}" />
<br />

<h4><spring:message code="stats.mostCustomers" /></h4>
<jstl:set var="customerIndex" value="${1}" />
<jstl:forEach var="x" items="${stats8}">
	<jstl:out value="${customerIndex}" />. 
	<jstl:out value="${x.getName()}" /> (<jstl:out value="${x.getUser().getUsername()}" />)
	<br/>
	<jstl:set var="customerIndex" value="${customerIndex+1}" />
</jstl:forEach>
<br />