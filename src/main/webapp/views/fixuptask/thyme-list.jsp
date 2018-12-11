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

<div class="fixupfasklist" th:unless="${#lists.isEmpty(fixupTasks)}">

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th th:text="#{fixuptask.description}">Description</th>
			</tr>
		</thead>
		<tbody>
			<tr th:each="row : ${fixupTasks}">
				<td th:text="${{row.ticker}}">--</td>
				<td th:text="${{row.description}}">--</td>
			</tr>
		</tbody>
	</table>
</div>