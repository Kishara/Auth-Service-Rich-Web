<%@page import="com.auth.service.PatientService"%>
<%@page import="com.auth.model.Patient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient</title>
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<!-- <script src="Components/items.js"></script> -->


</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Patient Registration</h1>
				<form id="formPatient" name="formPatient">
					First Name:    <input id="firstName" name="firstName" type="text" class="form-control form-control-sm"> <br> 
					Last Name:     <input id="lastName" name="lastName" type="text" class="form-control form-control-sm"> <br> 
					Date of birth: <input id="DOB" name="DOB" type="text" class="form-control form-control-sm"> <br> 
					Age: 		   <input id="age" name="age" type="text" class="form-control form-control-sm"> <br> 
					Sex:    	   <input id="sex" name="sex" type="text" class="form-control form-control-sm"> <br> 
					NIC: 		   <input id="nic" name="nic" type="text" class="form-control form-control-sm"> <br> 
					Address: 	   <input id="address" name="address" type="text" class="form-control form-control-sm"> <br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidPatientIDSave" name="hidPatientIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divPatientGrid">
					<%
						PatientService patientService = new PatientService();
						out.print(patientService.readPatient());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>