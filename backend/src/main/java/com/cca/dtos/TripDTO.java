package com.cca.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
public class TripDTO {

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

	public TripDTO(String tripId, String creatorUserId, String vehicleId, LocalDate rideDate, LocalTime rideTime,
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

	public TripDTO() {
		super();
	}

}
