<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<a class="btn btn-default btn-md" href="/etnshop/product/edit"
			role="button">New product</a>
	</div>
	<div id=search>
		<input type="text" class="form-search"> <input type="submit"
			class="btn btn-default btn-md" value="Search">
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.serial}</td>

					<td width=20><spring:url value="/resources/core/images/edit.png" var="edit" /> <a
						href="edit?id=${product.id}"> <img src="${edit}">
					</a></td>
					<td width=20><spring:url value="/resources/core/images/delete.png" var="delete" /> <a
						href="delete?id=${product.id}"> <img src="${delete}">
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<div id="recordsNum">Records: ${count}</div>
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