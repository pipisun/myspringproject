<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coffee Shop Admin Page</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div>
	<form id="signupForm" action="/signup">
			<input type="submit" value="Signup User" />
	</form>
	<br/>
	Product:<br />
	<a href="/product/createProduct">Create</a>&nbsp;&nbsp;
	<a href="/product/listProduct">List of Product</a>
	<br />
	<br /> 
	Person:<br />
	<a href="/admin/createPerson">Create</a>&nbsp;&nbsp;
	<a href="/admin/listPerson">List of Person</a> 
	<br />
	<br /> 
	Order:<br />
	<a href="/admin/listOrder">List of Order</a>
	<br />
	<br />
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<form action="/login?logout" id="logoutForm">
		<a href="javascript:formSubmit()">Log out</a>
	 	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	</div>
</body>
</html>