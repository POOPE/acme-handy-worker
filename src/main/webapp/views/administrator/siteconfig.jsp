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
<script type="text/javascript" src="scripts/siteconfig.js"></script>
<form:form action="admin/siteconfig.do"
	modelAttribute="siteConfiguration">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="spamWords" id="spamwordsfield"/>
	<form:hidden path="goodWords" />
	<form:hidden path="badWords" />

	<div>
		<b><spring:message code="config.apperance" /></b> <br />
		<!-- site name -->
		<div>
			<form:label path="siteName">
				<spring:message code="config.siteName" />
			</form:label>
			<form:input path="siteName" />
			<form:errors cssClass="error" path="siteName" />
		</div>
		<div>
			<!-- banner url -->
			<form:label path="bannerUrl">
				<spring:message code="config.bannerUrl" />
			</form:label>
			<form:input path="bannerUrl" />
			<form:errors cssClass="error" path="bannerUrl" />
		</div>
		<div>
			<!-- welcome message -->
			<form:label path="welcomeMessage">
				<spring:message code="config.welcomeMessage" />
			</form:label>
			<form:input path="welcomeMessage" />
			<form:errors cssClass="error" path="welcomeMessage" />
		</div>
	</div>
	<div>
		<div>
			<b><spring:message code="config.misc" /></b> <br />
			<!--  vat rate -->
			<form:label path="vatRate">
				<spring:message code="config.vatRate" />
			</form:label>
			<form:input path="vatRate" />
			<form:errors cssClass="error" path="vatRate" />
		</div>
		<div>
			<!-- default country code -->
			<form:label path="defaultCountryCode">
				<spring:message code="config.defaultCountryCode" />
			</form:label>
			<span style="color: gray">#</span>
			<form:input path="defaultCountryCode" />
			<form:errors cssClass="error" path="defaultCountryCode" />
		</div>
		<div>
			<!-- finder limit -->
			<form:label path="finderResLimit">
				<spring:message code="config.finderResLimit" />
			</form:label>
			<form:input path="finderResLimit" />
			<form:errors cssClass="error" path="finderResLimit" />
		</div>
		<div>
			<!-- finder reset -->
			<form:label path="finderTimeLimit">
				<spring:message code="config.finderTimeLimit" />
			</form:label>
			<form:input path="finderTimeLimit" />
			<form:errors cssClass="error" path="finderTimeLimit" />
		</div>
	</div>
	<div>
		<b><spring:message code="config.security" /></b> <br />
		<div>
			<spring:message code="config.spamWords" />
			: <br />
			<div id="spamwordlist">
				<jstl:forEach items="${siteConfiguration.spamWords}" var="spamWord">
					<div class="list-item" onclick="removespam(this)" id="${spamWord}">
						<jstl:out value="${spamWord}"></jstl:out>
						&nbsp;<i  class="fa fa-times" aria-hidden="true"></i>
					</div>
				</jstl:forEach>
			</div>
			<input type="text" id="spamwordinput" />
			<button type="button" onclick="addItem(); return false;" id="addspamword">Add</button>

		</div>
	</div>
	</br>
	<input type="submit" value="<spring:message code="save" />" name="save">
</form:form>

