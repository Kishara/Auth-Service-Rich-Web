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
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidDoctorIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "DoctorAPI",
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onDoctorSaveComplete(response.responseText, status);
		}
	});
});

//DELETE ============================================
$(document).on('click', '.btnRemove', function(event){
		 $.ajax({
		 url : "DoctorAPI",
		 type : "DELETE",
		 data : "ID=" + $(this).data("id"),
		 dataType : "text",
		 complete : function(response, status){
			 onDoctorDeleteComplete(response.responseText, status);
		 }
	});
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
 $("#type").val($(this).closest("tr").find('td:eq(0)').text());
 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
 $("#password").val($(this).closest("tr").find('td:eq(2)').text());
 $("#contactNo").val($(this).closest("tr").find('td:eq(3)').text());
 $("#firstName").val($(this).closest("tr").find('td:eq(4)').text());
 $("#lastName").val($(this).closest("tr").find('td:eq(5)').text());
 $("#NIC").val($(this).closest("tr").find('td:eq(6)').text());
 $("#sex").val($(this).closest("tr").find('td:eq(7)').text());
 $("#specialization").val($(this).closest("tr").find('td:eq(8)').text());
}); 

//SAVE ============================================
function onDoctorSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divDoctorGrid").html(resultSet.data);
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
	$("#hidDoctorIDSave").val("");
	$("#formDoctor")[0].reset();
}

// VALIDATION================================================================
function validateDoctorForm() {
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
	// NIC
	if ($("#NIC").val().trim() == "") {
		return "Insert NIC.";
	}
	// sex
	if ($("#sex").val().trim() == "") {
		return "Insert Sex.";
	}
	
	//specialization
	if ($("#specialization").val().trim() == "") {
		return "Insert Specialization.";
	}
	// email
	if ($("#email").val().trim() == "") {
		return "Insert Email.";
	}
	// contactNo
	if ($("#contactNo").val().trim() == "") {
		return "Insert Contact No.";
	}
	// numerical validation for contact no
	var tmpcontactNo = $("#contactNo").val().trim();
	if (!$.isNumeric(tmpcontactNo)) {
		return "Insert a numerical value for contact no.";
	}
	// password
	if ($("#password").val().trim() == "") {
		return "Insert Password.";
	}
	return true;
}

//DELETE ============================================
function onDoctorDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divDoctorGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}