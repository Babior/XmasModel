<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Children Management</title>
</head>
<body>

	<h1>Children Management</h1>
	<h2>Children</h2>

	<form:form name="admin" method="post" action="childrenManagement">
		<table border="1px solid black" style="border-collapse: collapse">
			<tr>
				<th></th>
				<th>ID</th>
				<th>Name</th>
				<th>Number of gifts</th>
			</tr>
			<c:forEach var="child" items='${childArray}'>
				<tr>
					<td><input type="radio" name="childId"
						value="${child.getId()}"></td>
					<td>${child.getId()}</td>
					<td><c:out value="${child.getName()}" escapeXml="true" /></td>
					<td>${child.getGifts().size()}</td>
				</tr>
			</c:forEach>
		</table>

		<br><br>
		<input type="hidden" name="childId" value="${child.getId()}">

		<input type="hidden" name="token" value='${requestScope["token"]}'>

		<input type="button" value="View & Update Child"
			onclick="admin.cmd.value='show-detail-page';admin.submit()">
		<input type="button" value="Delete Child"
			onclick="admin.cmd.value='delete-child';admin.submit()">


		<h2>Add child</h2>
		Name: <input type="text" name="childName" />
		<br>
		Address: <input type="text" name="address" />
		<br>
		<input type="hidden" name="cmd" value="">
		<input type="hidden" name="token" value='${requestScope["token"]}'>
		<input type="button" value="Add Child"
			onclick="admin.cmd.value='add-child';admin.submit()">
			
			
		<br><br><br>
		<input type="button" value="Gift Overview Page"
			onclick="admin.cmd.value='gift-overview';admin.submit()">
	</form:form>

</body>
</html>