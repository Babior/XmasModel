<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Child Detail</title>
</head>
<body>

	<h1>Child Detail</h1>

	<form name="admin" action="childDetail" method="post">
		<label for="name">Name:</label> <input type="text" name="name"
			id="name" value="${name}"> 
		<br> 
		
		<input type="hidden" name="childId" value="${childId}"> 
		<input type="hidden" name="addressId" value="${addressId}">
		<input type="hidden" name="cmd" value=""> 
		<input type="hidden" name="token" value='${requestScope["token"]}'> 
		<input type="button" value="Update Child Name" onClick="admin.cmd.value='do-update-child';admin.submit()"> 
		
		<br><br><br>
		<label for="name">Address:</label> <input type="text" name="address"
			id="address" value="${address}"> 
		<br> 
		<input type="button" value="Update Address" onClick="admin.cmd.value='do-update-address';admin.submit()">
		
		
		<h2>Gifts</h2>
		
		<ol>
			<c:forEach var="gift" items='${giftArray}'>
				<li>
					<input type="radio" name="giftId"
								value="${gift.getId()}">
					<span>${gift.getDescription()}</span>
				</li>
			</c:forEach>
		</ol>
		
		<input type="button" value="View & Update Gift"
			onclick="admin.cmd.value='show-detail-page-gift';admin.submit()">
			<input type="button" value="Delete Gift"
			onclick="admin.cmd.value='delete-gift';admin.submit()">
			
		
		<h3>Add Gift</h3>
		Description: <input type="text" name="giftDescription" />
		<br>
		<input type="button" value="Add Gift"
			onclick="admin.cmd.value='add-gift';admin.submit()">
			
		<br><br><br><br>
		<input type="button" value="Children Management Page"
			onclick="admin.cmd.value='childrenManagement';admin.submit()">
	</form>




</body>
</html>