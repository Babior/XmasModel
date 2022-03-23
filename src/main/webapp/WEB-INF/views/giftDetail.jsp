<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gift Detail</title>
</head>
<body>

<h1>Gift Detail</h1>
<h2>Update gift description</h2>

<form name="admin" action="giftDetail" method="post">
		<label for="description">Description:</label> 
		<input type="text" name="description"
			id="description" value="${description}"> 
		<br> 
		
		<input type="hidden" name="giftId" value="${giftId}"> 
		<input type="hidden" name="cmd" value=""> 
		<input type="hidden" name="token" value='${requestScope["token"]}'> 
		<input type="button" value="Update Gifts" onClick="admin.cmd.value='do-update-gift';admin.submit()"> 
</form>

</body>
</html>