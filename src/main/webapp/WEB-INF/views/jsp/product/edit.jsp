<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<c:choose>
	<c:when test="${product.id == '0'}">
		<c:set var="title" value="Add new product" />
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Edit product" />
	</c:otherwise>
</c:choose>
<title>etnShop - <c:out value="${title}" /></title>
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<h2>
			<c:out value="${title}" />
		</h2>
		<form:form method="post" commandName="product">
			<table>
				<tr>
					<th><label> Product name: </label></th>
					<td><form:input path="name" /> <form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<th><label> Serial number: </label></th>
					<td><form:input path="serial" /> <form:errors path="serial" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit"  value="${title}" /></td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>