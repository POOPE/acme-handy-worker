<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form action="actor/edit.do" modelAttribute="actor">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="flagged" />
	<jstl:if test="${role=='customer'}">
		<form:hidden path="score" />
	</jstl:if>
	<jstl:if test="${role=='handyWorker'}">		
		<form:hidden path="make" />
		<form:hidden path="score" />
	</jstl:if>
	<h3><spring:message code="actor.signupTitle" /></h3>
	<div>
		<form:label path="name"><spring:message code="actor.name" /></form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
	</div>
	<div>
		<form:label path="middleName"><spring:message code="actor.middleName" /></form:label>
		<form:input path="middleName" />
		<form:errors cssClass="error" path="middleName" />
	</div>
	<div>
		<form:label path="surname"><spring:message code="actor.surname" /></form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname" />
	</div>
	<div>
		<form:label path="photo"><spring:message code="actor.photo" /></form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />
	</div>
	<div>
		<form:label path="email"><spring:message code="actor.email" /></form:label>
		<form:input path="email" />
		<form:errors cssClass="error" path="email" />
	</div>
	<div>
		<form:label path="phone"><spring:message code="actor.phoneNumber" /></form:label>
		<form:input id="checkedPhone" path="phone" />
		<form:errors cssClass="error" path="phone" />
	</div>
	<div>
		<form:label path="address"><spring:message code="actor.address" /></form:label>
		<form:input path="address" />
		<form:errors cssClass="error" path="address" />
	</div>
	<h3><spring:message code="actor.signupTitle2" /></h3>
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />
	<form:hidden path="userAccount.authorities" />
	<div>
		<form:label path="userAccount.username"><spring:message code="actor.username" /></form:label>
		<form:input path="userAccount.username" />
		<form:errors cssClass="error" path="userAccount.username" />
	</div>
	<div>
		<form:label path="userAccount.password"><spring:message code="actor.password" /></form:label>
		<form:password path="userAccount.password" />
		<form:errors cssClass="error" path="userAccount.password" />
	</div>
	
	<div>
		<jstl:if test="${role=='customer'}">
			<input type="submit" value="<spring:message code="actor.save" />" name="toCustomer">
		</jstl:if>
		<jstl:if test="${role=='handyWorker'}">
			<input type="submit" value="<spring:message code="actor.save" />" name="toHandyWorker">
		</jstl:if>
		<jstl:if test="${role=='administrator'}">
			<input type="submit" value="<spring:message code="actor.save" />" name="toAdministrator">
		</jstl:if>
		<jstl:if test="${role=='referee'}">
			<input type="submit" value="<spring:message code="actor.save" />" name="toReferee">
		</jstl:if>
		<jstl:if test="${role=='sponsor'}">
			<input type="submit" value="<spring:message code="actor.save" />" name="toSponsor">
		</jstl:if>
		<a href="welcome/index.do"><spring:message code="actor.cancel" /></a>
	</div>
</form:form>
