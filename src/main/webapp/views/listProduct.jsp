<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Products</title>
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>
<c:if test="${empty requestScope.products }">
	No Products!
</c:if>
<c:if test="${!empty requestScope.products }">
	<table><tr>
			<th>Id</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Type of Product</th>
		</tr>
		<c:forEach items="${requestScope.products}" var="product">
		<tr>
			<td>${product.id} </td>			
			<td>${product.productName} </td>
			<td>${product.price} </td>				
			<td>${product.description} </td>			
			<td>${product.productType} </td>			
			<td><a href="/product/updateProduct/${product.id}">Update</a></td>
			<td><a href="/product/deleteProduct/${product.id}">Delete</a></td>			
		</tr>
		</c:forEach>
	</table>
</c:if>
<a href="/admin">Go back</a>
</div>
</body>
</html>