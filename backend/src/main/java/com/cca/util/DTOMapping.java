package com.cca.util;

import org.springframework.stereotype.Component;

import com.cca.dtos.BookRideDTO;
import com.cca.dtos.RideSeekerDTO;
import com.cca.dtos.TripDTO;
import com.cca.entities.BookRide;
import com.cca.entities.RideSeeker;
import com.cca.entities.Trip;

@Component
public class DTOMapping {

	// mapping functions
	public static RideSeeker mapToRideSeeker(RideSeekerDTO rideSeekerDTO) {
		if (rideSeekerDTO.getAdharCard() != null && rideSeekerDTO.getPhone() != null) {

			return new RideSeeker(rideSeekerDTO.getRsId(), Long.parseLong(rideSeekerDTO.getAdharCard()),
					rideSeekerDTO.getEmailId(), Long.parseLong(rideSeekerDTO.getPhone()), rideSeekerDTO.getFirstName(),
					rideSeekerDTO.getLastName(), rideSeekerDTO.getDob(), rideSeekerDTO.getAddress(),
					rideSeekerDTO.getStatus());
		}

		return new RideSeeker();
	}

	public static Trip mapToTrip(TripDTO tripDTO) {
		return new Trip(tripDTO.getTripId(), tripDTO.getCreatorUserId(), tripDTO.getVehicleId(), tripDTO.getRideDate(),
				tripDTO.getRideTime(), tripDTO.getRideStatus(), tripDTO.getNoOfSeat(), tripDTO.getSeatsFilled(),
				tripDTO.getFromLOC(), tripDTO.getToLOC());

	}

	public static BookRide mapTOBookRide(BookRideDTO bookRideDTO) {
		return new BookRide(bookRideDTO.getBookingId(), bookRideDTO.getNumberOfSeat(), bookRideDTO.getSeekerId(),
				bookRideDTO.getStatus(), bookRideDTO.getTrip());
	}

}
