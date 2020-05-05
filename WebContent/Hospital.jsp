
<%@page import="com.auth.service.HospitalService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Registration</title>
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/hospital.js"></script>


</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<form id="formHospital" name="formHospital">
					<h1>Hospital registration.</h1>
				
					Type:    	   		<input id="type" name="type" type="text" class="form-control form-control-sm"> <br> 
					Hospital Name:    	<input id="name" name="name" type="text" class="form-control form-control-sm"> <br> 
					Hospital Address:   <input id="address" name="address" type="text" class="form-control form-control-sm"> <br> 
					Email: 		   		<input id="email" name="email" type="text" class="form-control form-control-sm"> <br> 
					Contact no:    		<input id="contactNo" name="contactNo" type="text" class="form-control form-control-sm"> <br> 
					Password: 		    <input id="password" name="password" type="password" class="form-control form-control-sm"> <br> 

					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divHospitalGrid">
					<%
						HospitalService hospitalService = new HospitalService();
						out.print(hospitalService.readHospital());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>