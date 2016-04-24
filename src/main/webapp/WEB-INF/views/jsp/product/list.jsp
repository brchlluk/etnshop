<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>etnShop</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<div class="container">
	<h2>Products</h2>
	<div id=add>
		<c:if test="${searchFlag == 1}">
			<a class="btn btn-default btn-md" href="/etnshop/product/list"
				role="button">All products</a>
		</c:if>

		<a class="btn btn-default btn-md" href="/etnshop/product/edit"
			role="button">New product</a>
	</div>
	<div id=search>
		<form:form method="post">
			<input type="text" name="expression">
			<input type="submit" class="btn btn-default btn-md" value="Search">
		</form:form>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${searchFlag == 1 && count == 0}">
				<tr>
					<td>
						<p align=center>No records were found for "${expression}".</p>
					</td>
				</tr>
			</c:if>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.serial}</td>

					<td width=20><spring:url
							value="/resources/core/images/edit.png" var="edit" /> <a
						href="edit?id=${product.id}"> <img src="${edit}">
					</a></td>
					<td width=20><spring:url
							value="/resources/core/images/delete.png" var="delete" /> <a
						href="delete?id=${product.id}"> <img src="${delete}">
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<div id="recordsNum">
		${count} records found.
	</div>
	<footer>
		<p>&copy; Etnetera a.s. 2015</p>
	</footer>
</div>

<spring:url value="/resources/core/css/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>