<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>
<c:if test="${empty requestScope.persons }">
	No person!
</c:if>
<c:if test="${!empty requestScope.persons }">
	<table><tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Phone number</th>
			<th>City</th>
			<th>State</th>
			<th>Country</th>
			<th>ZIP code</th>
			<th>Enable</th>
		</tr>
		<c:forEach items="${requestScope.persons}" var="person">
		<tr>
			<td>${person.id} </td>			
			<td>${person.firstName} </td>			
			<td>${person.lastName} </td>			
			<td>${person.email} </td>			
			<td>${person.phone} </td>			
			<td>${person.address.city} </td>			
			<td>${person.address.state} </td>			
			<td>${person.address.country} </td>			
			<td>${person.address.zipcode} </td>			
			<td>${person.enable} </td>			
			<td><a href="/admin/updatePerson/${person.id}">Update</a></td>			
		</tr>
		</c:forEach>
	</table>
</c:if>
<a href="/admin">Go back</a>
</div>
</body>
</html>