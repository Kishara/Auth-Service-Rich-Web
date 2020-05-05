package com.auth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.auth.database.DBConnection;

import com.auth.model.Patient;

public class PatientService {

	
	
/* User type - Patient */
	
//Insert patient
	public String insertPatient(String type, String email, String password, String contactNo, String firstName, String lastName, String DOB, String age, String sex, String NIC, String address) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			
			// create a prepared statement
			String query = " insert into patient (`id`,`type`,`email`,`password`,`contactNo`,`firstName`,`lastName`,`dob`,`age`,`sex`,`nic`,`address`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, password);
			preparedStmt.setString(5, contactNo);
			preparedStmt.setString(6, firstName);
			preparedStmt.setString(7, lastName);
			preparedStmt.setString(8, DOB);
			preparedStmt.setString(9, age);
			preparedStmt.setString(10, sex);
			preparedStmt.setString(11, NIC);
			preparedStmt.setString(12, address);


			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newPatient = readPatient();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newPatient + "\"}"; 
			//output = "Inserted successfully";
			 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": "
					+ "\"Error while inserting the Patient.\"}"; 
			//output = "Error while inserting the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		

//Read patient list	
	public String readPatient() {
			String output = "";
			try {
				Connection con = DBConnection.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>User type</th><th>Patient Email</th><th>Password</th><th>Contact no</th>"
						+ "<th>First name</th><th>Last name</th><th>DOB</th><th>Age</th><th>sex</th><th>NIC</th><th>Address</th>"
						+ "<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from patient";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				
				// iterate through the rows in the result set
				while (rs.next()) {
					String ID = Integer.toString(rs.getInt("ID"));
					String type = rs.getString("type");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String contactNo = rs.getString("contactNo");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String DOB = rs.getString("DOB");
					String age = rs.getString("age");
					String sex = rs.getString("sex");
					String NIC = rs.getString("NIC");
					String address = rs.getString("address");
					
					// Add into the HTML table
					output += "<tr><td><input id='hidPatientIDUpdate' name='hidPatientIDUpdate' type='hidden' value='" + ID + "'>" + type + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + password + "</td>";
					output += "<td>" + contactNo + "</td>";
					output += "<td>" + firstName + "</td>";
					output += "<td>" + lastName + "</td>";
					output += "<td>" + DOB + "</td>";
					output += "<td>" + age + "</td>";
					output += "<td>" + sex + "</td>";
					output += "<td>" + NIC + "</td>";
					output += "<td>" + address + "</td>";
					
					
					// buttons
				
					
					/*
					 * output += "<td><input name=\"btnUpdate\" type=\"button\"" +
					 * " value=\"Update\" class=\"btn btn-secondary\"></td>" +
					 * "<td><form method=\"post\" action=\"patient.jsp\">" +
					 * "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"" +
					 * " class=\"btn btn-danger\">" + "<input name=\"ID\" type=\"hidden\" value=\""
					 * + ID + "\">" + "</form></td></tr>";
					 */
					
					

					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'  data-ID= '" + ID + "'></td></tr>"; 
				}
				con.close();
				
				
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the patient.";
				System.err.println(e.getMessage());
			}
			return output;
			
		}		
			
			
//Patient update
	public String updatePatient(String ID, String type, String email, String password, String contactNo, String firstName, String lastName, String DOB, String age, String sex, String NIC, String address) {
				String output = "";
				try {
					Connection con = DBConnection.connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					
					
					// create a prepared statement
					String query = "UPDATE patient SET type=?, email=?,password=?,contactNo=?, firstName=?, lastName=?, DOB=?, age=?, sex=?, NIC=?, address=? WHERE ID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, type);
					preparedStmt.setString(2, email);
					preparedStmt.setString(3, password);
					preparedStmt.setString(4, contactNo);
					preparedStmt.setString(5, firstName);
					preparedStmt.setString(6, lastName);
					preparedStmt.setString(7, DOB);
					preparedStmt.setString(8, age);
					preparedStmt.setString(9, sex);
					preparedStmt.setString(10, NIC);
					preparedStmt.setString(11, address);
					preparedStmt.setInt(12, Integer.parseInt(ID));
					
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					String newPatient = readPatient();
					 output = "{\"status\":\"success\", \"data\": \"" +
					 newPatient + "\"}"; 
					
					//output = "Updated successfully";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":"
							+ "\"Error while updating the item.\"}"; 
					
					//output = "Error while updating the patient.";
					System.err.println(e.getMessage());
				}
				return output;
			}
		
		
			
//Delete existing patient
	public String deletePatient(String ID) {
					String output = "";
					try {
						Connection con = DBConnection.connect();
						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}
						
						
						// create a prepared statement
						String query = "delete from patient where id=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(ID));
						
						
						// execute the statement
						preparedStmt.execute();
						con.close();
						
						String newPatient = readPatient();
						 output = "{\"status\":\"success\", \"data\": \"" +
						 newPatient + "\"}"; 
						//output = "Deleted successfully";
					} catch (Exception e) {
						output = "{\"status\":\"error\", \"data\":"
								+ "\"Error while deleting the Patient.\"}"; 
						
						//output = "Error while deleting the patient.";
						System.err.println(e.getMessage());
					}
					return output;
				}
						
				
				
				
//Retrieve  single patient
	public static Patient getPatientDetails(String id) {
					Patient patient = null;
					try {

						Connection con = DBConnection.connect();

						String getSql = "SELECT * FROM patient WHERE id = ? ";
						PreparedStatement ps_getPatientDetails = con.prepareStatement(getSql);
						ps_getPatientDetails.setInt(1, Integer.parseInt(id));

						ResultSet rs = ps_getPatientDetails.executeQuery();

						while (rs.next()) {

							
							patient = new Patient(Integer.parseInt(id), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8) , rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

						
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					return patient;
				}

				
}
