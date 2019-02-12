<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png"
		alt="Acme Handy Worker Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="admin/broadcast.do"><spring:message
								code="master.page.administrator.broadcast" /></a></li>
					<li><a href="warranty/admin/list.do"><spring:message
								code="master.page.warranty.list" /></a></li>
					<li><a href="category/admin/list.do"><spring:message
								code="master.page.category.list" /></a></li>
					<li><a href="admin/siteconfig.do"><spring:message
								code="master.page.siteconfig" /></a></li>
					<li><a href="admin/stats.do"><spring:message
								code="master.page.stats" /></a></li>
					<li><a href="actor/create.do?role=administrator"><spring:message
								code="master.page.createAdmin" /></a></li>
								
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message
						code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="lorem/customer/list.do"><spring:message
								code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message
								code="master.page.customer.action.2" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('HANDYWORKER')">
			<li><a class="fNiv"><spring:message
						code="master.page.application" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixupapplication/handyworker/applications.do"><spring:message
								code="master.page.application.list" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasAnyRole('HANDYWORKER','CUSTOMER')">
			<li><a class="fNiv"><spring:message
						code="master.page.fixuptasks" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixuptask/list.do"><spring:message
								code="master.page.fixuptasks.browse" /></a></li>
					<security:authorize access="hasRole('HANDYWORKER')">
						<li><a href="finder/init.do"><spring:message
									code="master.page.finder" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('CUSTOMER')">
						<li><a href="fixuptask/create.do"><spring:message
									code="master.page.fixuptasks.create" /></a></li>
					</security:authorize>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/create.do?role=customer"><spring:message
								code="master.page.registerCustomer" /></a></li>
					<li><a href="actor/create.do?role=handyWorker"><spring:message
								code="master.page.registerHandyWorker" /></a></li>
					<li><a href="actor/create.do?role=sponsor"><spring:message
								code="master.page.registerSponsor" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="messaging/message.do"><spring:message
								code="master.page.profile.sendmessage" /></a></li>
					<li><a href="messaging/view.do"><spring:message
								code="master.page.profile.messaging" /></a></li>
					<li><a href="actor/edit.do"><spring:message
								code="master.page.profile.view" /></a></li>
					<li><a href="actor/password.do"><spring:message
								code="master.page.password" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

