$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validatePatientForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidPatientIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "PatientAPI",
		type : type,
		data : $("#formPatient").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onPatientSaveComplete(response.responseText, status);
		}
	});
});

function onPatientSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPatientGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidPatientIDSave").val("");
	$("#formPatient")[0].reset();
}

// CLIENT-MODEL================================================================
function validatePatientForm() {
	// type
	if ($("#type").val().trim() == "") {
		return "Insert Type";
	}
	
	// first name
	if ($("#firstName").val().trim() == "") {
		return "Insert First Name.";
	}
	// last name
	if ($("#lastName").val().trim() == "") {
		return "Insert Last Name.";
	}
	// date of birth
	if ($("#DOB").val().trim() == "") {
		return "Insert Date of birth.";
	}
	
	// age
	if ($("#age").val().trim() == "") {
		return "Insert age.";
	}
	//numerical validation for age
	var tmpage = $("#age").val().trim();
	if (!$.isNumeric(tmpage)) {
		return "Insert a numerical value for age.";
	}
	// sex
	if ($("#sex").val().trim() == "") {
		return "Insert Sex.";
	}
	// nic
	if ($("#NIC").val().trim() == "") {
		return "Insert NIC.";
	}
	// address
	if ($("#address").val().trim() == "") {
		return "Insert address.";
	}
	// email
	if ($("#email").val().trim() == "") {
		return "Insert Email.";
	}
	// contactNo
	if ($("#contactNo").val().trim() == "") {
		return "Insert Contact No.";
	}
	//numerical validation for contact no
	var tmpcontactNo = $("#contactNo").val().trim();
	if (!$.isNumeric(tmpcontactNo)) {
		return "Insert a numerical value for contact no.";
	}
	// password
	if ($("#password").val().trim() == "") {
		return "Insert Password.";
	}
	
	
	
	
//	// is numerical value
//	var tmpPrice = $("#itemPrice").val().trim();
//	if (!$.isNumeric(tmpPrice)) {
//		return "Insert a numerical value for Item Price.";
//	}
//	// convert to decimal price
//	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
//	// DESCRIPTION------------------------
//	if ($("#itemDesc").val().trim() == "") {
//		return "Insert Item Description.";
//	}
	return true;
}