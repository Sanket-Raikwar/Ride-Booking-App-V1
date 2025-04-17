package com.cca.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
public class BookRide {
	@Id
	private String bookingId;
	private int numberOfSeat;
	private String seekerId;
	private String status;

	@ManyToOne()
	@JoinColumn(name = "trip_id", referencedColumnName = "tripId")
	private Trip trip;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}

	public String getSeekerId() {
		return seekerId;
	}

	public void setSeekerId(String seekerId) {
		this.seekerId = seekerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BookRide(int numberOfSeat, String seekerId, Trip trip) {
		super();
		this.numberOfSeat = numberOfSeat;
		this.seekerId = seekerId;
		this.trip = trip;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}
