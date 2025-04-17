package com.cca.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Component
public class RideSeekerDTO {

	private String rsId;

	@Pattern(regexp = "\\d{12}", message = "Aadhar card should be a 12-digit number")
	private String adharCard;

	@Email(message = "Please enter a valid email Id with @cognizant.com", regexp = "^[a-zA-Z0-9]+@cognizant.com")
	private String emailId;

	@Pattern(regexp = "[0-9]{10}", message = "Phone number should be 10 digits")
	private String phone;
 
	@Pattern(regexp = "[a-zA-z]+", message = "Only use Alphabats for first name")
	private String firstName;

	@Pattern(regexp = "[a-zA-z]+", message = "Only use Alphabats for last name")
	@Size(min = 3, message = "LastName must have a minimum of 3 characters")
	private String lastName;

	private LocalDate dob;

	private String address;

	@Pattern(regexp = "^(Registered|Un-registered)$", message = "Status should be either 'Registered' or 'Un-registered'")
	private String status;

	public String getRsId() {
		return this.rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getAdharCard() {
		return this.adharCard;
	}

	public void setAdharCard(String adharCard) {
		this.adharCard = adharCard;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return this.dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RideSeekerDTO( String adharCard, String emailId, String phone, String firstName, String lastName,
			LocalDate dob, String address, String status) {
		super();
		this.rsId = rsId;
		this.adharCard = adharCard;
		this.emailId = emailId;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.address = address;
		this.status = status;
	}

	public RideSeekerDTO(
			@Pattern(regexp = "\\d{12}", message = "Aadhar card should be a 12-digit number") String adharCard,
			@Email(message = "Please enter a valid email Id with @cognizant.com", regexp = "^[a-zA-Z0-9]+@cognizant.com") String emailId,
			@Pattern(regexp = "[0-9]{10}", message = "Phone number should be 10 digits") String phone,
			@Pattern(regexp = "[a-zA-z]+", message = "Only use Alphabats for first name") String firstName,
			@Pattern(regexp = "[a-zA-z]+", message = "Only use Alphabats for last name") @Size(min = 3, message = "LastName must have a minimum of 3 characters") String lastName,
			LocalDate dob, String address) {
		super();
		this.adharCard = adharCard;
		this.emailId = emailId;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.address = address;
	}
	

}
