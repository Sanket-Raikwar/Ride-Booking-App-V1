package com.cca.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "trip")
@NoArgsConstructor
@ToString
public class Trip {

	@Id
	private String tripId;

	private String creatorUserId;

	private String vehicleId;

	private LocalDate rideDate;

	private LocalTime rideTime;

	private String rideStatus;

	private int noOfSeat;

	private int seatsFilled;

	private String fromLOC;

	private String toLOC;

	@JsonIgnore
	@OneToMany(mappedBy = "trip")
	private Set<BookRide> bookedRides = new HashSet<>();

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Set<BookRide> getBookedRides() {
		return bookedRides;
	}

	public void setBookedRides(Set<BookRide> bookedRides) {
		this.bookedRides = bookedRides;
	}

	public LocalDate getRideDate() {
		return rideDate;
	}

	public void setRidedDate(LocalDate rideDate) {
		this.rideDate = rideDate;
	}

	public LocalTime getRideTime() {
		return rideTime;
	}

	public void setRideTime(LocalTime rideTime) {
		this.rideTime = rideTime;
	}

	public String getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}

	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public int getSeatsFilled() {
		return seatsFilled;
	}

	public void setSeatsFilled(int seatsFilled) {
		this.seatsFilled = seatsFilled;
	}

	public String getFromLOC() {
		return fromLOC;
	}

	public void setFromLOC(String fromLOC) {
		this.fromLOC = fromLOC;
	}

	public String getToLOC() {
		return toLOC;
	}

	public void setToLOC(String toLOC) {
		this.toLOC = toLOC;
	}

	public Trip(String tripId, String creatorUserId, String vehicleId, LocalDate rideDate, LocalTime rideTime,
			String rideStatus, int noOfSeat, int seatsFilled, String fromLOC, String toLOC) {
		super();
		this.tripId = tripId;
		this.creatorUserId = creatorUserId;
		this.vehicleId = vehicleId;
		this.rideDate = rideDate;
		this.rideTime = rideTime;
		this.rideStatus = rideStatus;
		this.noOfSeat = noOfSeat;
		this.seatsFilled = seatsFilled;
		this.fromLOC = fromLOC;
		this.toLOC = toLOC;
	}

	public void addBooking(BookRide bookRide) {
		this.bookedRides.add(bookRide);
	}

}
