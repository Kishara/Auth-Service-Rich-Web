<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home page</title>
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
</head>
<body>
	<div style="text-align: center">
		<h1>Welcome to Patient home page</h1>
		<b>(${patient.email}) </b> <br>
		<br> <a href="PatientLoginAPI">Logout</a>
	</div>
</body>
</html>