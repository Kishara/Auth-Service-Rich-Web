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
	var status = validateHospitalForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "HospitalAPI",
		type : type,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onHospitalSaveComplete(response.responseText, status);
		}
	});
});


$(document).on('click', '.btnRemove', function(event){
		 $.ajax({
		 url : "HospitalAPI",
		 type : "DELETE",
		 data : "ID=" + $(this).data("id"),
		 dataType : "text",
		 complete : function(response, status){
			 onHospitalDeleteComplete(response.responseText, status);
		 }
	});
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
 $("#type").val($(this).closest("tr").find('td:eq(0)').text());
 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
 $("#password").val($(this).closest("tr").find('td:eq(2)').text());
 $("#contactNo").val($(this).closest("tr").find('td:eq(3)').text());
 $("#name").val($(this).closest("tr").find('td:eq(4)').text());
 $("#address").val($(this).closest("tr").find('td:eq(5)').text());
}); 


function onHospitalSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divHospitalGrid").html(resultSet.data);
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
	$("#hidHospitalIDSave").val("");
	$("#formHospital")[0].reset();
}

//CLIENT-MODEL================================================================
function validateHospitalForm() {
	// type
	if ($("#type").val().trim() == "") {
		return "Insert Type";
	}
	// Name
	if ($("#name").val().trim() == "") {
		return "Insert Hospital Name.";
	}
	// Address
	if ($("#address").val().trim() == "") {
		return "Insert Hospital Address.";
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

	// // is numerical value
	// var tmpPrice = $("#itemPrice").val().trim();
	// if (!$.isNumeric(tmpPrice)) {
	// return "Insert a numerical value for Item Price.";
	// }
	// // convert to decimal price
	// $("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
	// // DESCRIPTION------------------------
	// if ($("#itemDesc").val().trim() == "") {
	// return "Insert Item Description.";
	// }
	return true;
}


function onHospitalDeleteComplete(response, status){
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divHospitalGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}