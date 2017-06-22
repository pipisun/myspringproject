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
<body onload='document.loginForm.username.focus();'>
	<div>
		<form:form id="loginForm" modelAttribute="user" action="/login" method='POST'>
			User Name: <input name='username' /><br />
			Password: <input name="password" type="password" /><br />
			<input type="submit" value="Log In" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" name="email" value="javascript:document.loginForm.username.val();"/>
		</form:form>
	</div>
</body>
</html>