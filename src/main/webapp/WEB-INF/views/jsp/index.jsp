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
	<spring:url value="/resources/core/images/logo_etn.png" var="logo" />
	<div id=logo>
		<img src="${logo}">
	</div>
	<div id=menu>
		<a class="btn btn-primary btn-lg" href="/etnshop/product/list"
			role="button">Products</a>
	</div>

</div>

<spring:url value="/resources/core/css/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>