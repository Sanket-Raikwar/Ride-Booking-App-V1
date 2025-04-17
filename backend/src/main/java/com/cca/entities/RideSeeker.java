package com.cca.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rideseeker")
public class RideSeeker {
	@Id
	@Column(name = "rsid")
	private String rsId;

	@Column(name = "adharcard", unique = true, nullable = false)
	private long adharCard;

	@Column(name = "emailid")
	private String emailId;

	@Column(name = "phone", unique = true)
	private long phone;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "status")
	private String status;

	public String getRsId() {
		return rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public long getAdharCard() {
		return adharCard;
	}

	public void setAdharCard(long adharCard) {
		this.adharCard = adharCard;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RideSeeker() {
		super();
	}

	public RideSeeker(String rsId,long adharCard, String emailId, long phone, String firstName, String lastName, LocalDate dob,
			String address,String status) {
		super();
		this.rsId=rsId;
		this.adharCard = adharCard;
		this.emailId = emailId;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.status=status;
	}
}
