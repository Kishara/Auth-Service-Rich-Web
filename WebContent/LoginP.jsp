<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Login</title>
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>

</head>
<body>

	<div class="form-group w-50">

		<form id="formPatientLogin"
			class="text-center border border-light p-5" action="PatientLoginAPI"
			method="post">
			<p class="h4 mb-4">Patient Sign in</p>
			<input name="email" type="text" class="form-control form-control-sm"
				placeholder="Enter the email" /> <br> <br> <input
				type="password" name="password" class="form-control form-control-sm"
				placeholder="Enter the password" /> <br>${message} <br>
			<div class="d-flex justify-content-around">
				<div>
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input"
							id="defaultLoginFormRemember"> <label
							class="custom-control-label" for="defaultLoginFormRemember">Remember
							me</label>
					</div>
				</div>
				<div>
					<a href="">Forgot password?</a>
				</div>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Login</button>
			<p>
				Not a member?<a href="Patient.jsp">Register</a>
			</p>
		</form>
	</div>
</body>


<script type="text/javascript">
	$(document).ready(function() {
		$("#formPatientLogin").validate({
			rules : {
				email : {
					required : true,
					email : true
				},

				password : "required",
			},

			messages : {
				email : {
					required : "Please enter email",
					email : "Please enter a valid email address"
				},

				password : "Please enter password"
			}
		});

	});
</script>
</html>


