
<%@page import="com.auth.service.DoctorService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Registration</title>
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/doctor.js"></script>


</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<form id="formDoctor" name="formDoctor">
					<h1>Doctor registration.</h1>
				
					Type:    	   <input id="type" name="type" type="text" class="form-control form-control-sm" value="Doctor" readonly> <br> 
					First Name:    <input id="firstName" name="firstName" type="text" class="form-control form-control-sm"> <br> 
					Last Name:     <input id="lastName" name="lastName" type="text" class="form-control form-control-sm"> <br>
					NIC: 		   <input id="NIC" name="NIC" type="text" class="form-control form-control-sm"> <br>  
					Sex:    	   <input id="sex" name="sex" type="text" class="form-control form-control-sm"> <br> 
					Specialization:    	   <input id="specialization" name="specialization" type="text" class="form-control form-control-sm"> <br> 
					Email: 		   <input id="email" name="email" type="text" class="form-control form-control-sm"> <br> 
					Contact no:    <input id="contactNo" name="contactNo" type="text" class="form-control form-control-sm"> <br> 
					Password: 		   <input id="password" name="password" type="password" class="form-control form-control-sm"> <br> 

					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidDoctorIDSave" name="hidDoctorIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divDoctorGrid">
					<%
						DoctorService doctorService = new DoctorService();
						out.print(doctorService.readDoctor());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>