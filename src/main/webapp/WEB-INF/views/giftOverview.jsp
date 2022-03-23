<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gift overview</title>
</head>
<body>

	<h1>Gift overview</h1>

	<table border="1px solid black" style="border-collapse: collapse">
		<tr>
			<th>Child</th>
			<th>Gift</th>
		</tr>
		<c:forEach var="child" items='${childArray}'>
			<tr>
				<td>${child.getName()}</td>
				
				<td>
					<c:forEach var="gift" items='${child.getGifts()}'>
						<span>${gift.getDescription()}</span><br>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>