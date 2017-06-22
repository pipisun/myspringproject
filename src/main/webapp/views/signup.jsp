<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Coffee Shop Login Page</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div>
		<form:form modelAttribute="users" action="/admin/registration" method="POST">
		<table>
			<tr>
				<td><form:label path="email">Email:</form:label></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			<tr>
				<td><form:label path="enabled">Enabled:</form:label></td>
				<td><input name="enabled" value="1" readonly /></td>
			</tr>
			<tr>
				<td><form:label path="role">ROLE:</form:label></td>
				<td><input name="role" value="ROLE_USER" readonly /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form:form>

	</div>
</body>
</html>