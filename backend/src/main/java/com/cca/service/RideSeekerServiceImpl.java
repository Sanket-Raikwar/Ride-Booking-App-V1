package com.cca.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cca.entities.BookRide;
import com.cca.entities.RideSeeker;
import com.cca.entities.Trip;
import com.cca.exceptions.BookRideException;
import com.cca.exceptions.ExisitingRideSeekerException;
import com.cca.exceptions.NotFoundExceptionClass;
import com.cca.exceptions.TripAlreadyStartedException;
import com.cca.exceptions.UnRegistrationException;
import com.cca.repositories.BookRideRepo;
import com.cca.repositories.RideSeekerRepo;
import com.cca.repositories.TripRepo;

@Service
public class RideSeekerServiceImpl implements RideSeekerService {

	@Autowired
	RideSeekerRepo rideSeekerRepo;

	@Autowired
	TripRepo tripRepo;

	@Autowired
	BookRideRepo bookRideRepo;

	@Override
	@Transactional
	public String registerRideSeeker(RideSeeker rideSeeker) {

		String rsID = ("RE" + rideSeeker.getLastName().substring(0, 2).toUpperCase()
				+ String.format("%02d", rideSeeker.getDob().getYear() % 100));

		Optional<RideSeeker> existingRideSeeker = rideSeekerRepo.findById(rsID);

		if (existingRideSeeker.isPresent()) {
			throw new ExisitingRideSeekerException("Ride Seeker is already exisit");
		}

		rideSeeker.setStatus("Registered");
		rideSeeker.setRsId(rsID);
		rideSeekerRepo.save(rideSeeker);

		return rsID;
	}

	@Override
	@Transactional
	public String updateRideSeeker(String rsId, RideSeeker rideSeeker) {

		rideSeeker.setRsId(rsId);

		RideSeeker exisitngRideSeeker = rideSeekerRepo.findById(rsId)
				.orElseThrow(() -> new NotFoundExceptionClass("Ride Seeker is not exisit"));

		if (rideSeeker.getStatus().equalsIgnoreCase("Registered")) {
			rideSeekerRepo.save(rideSeeker);
			return "Details Updated successfully";

		} 
		
		if (rideSeeker.getStatus().equalsIgnoreCase("Un-registered")) {
			List<BookRide> bookedRides = bookRideRepo.findAllBySeekerId(rsId).get();

			for (BookRide bookedRide : bookedRides) {
				if (bookedRide.getStatus().equalsIgnoreCase("Booked")) {
					Trip bookedTrip = bookedRide.getTrip();
					throw new UnRegistrationException("You can not un-register , you have a ride on "
							+ bookedTrip.getRideDate() + " " + bookedTrip.getRideTime() + " from "
							+ bookedTrip.getFromLOC() + " to " + bookedTrip.getToLOC());
				}

			}
			rideSeekerRepo.save(rideSeeker);
			return "Ride Seeker with Id " + rsId + " is Un-registered Successfully";
		}
		return "";

	}

	@Override
	public RideSeeker getById(String rsId) {

		return rideSeekerRepo.findById(rsId).orElseThrow(() -> new NotFoundExceptionClass("Ride Seeker is not exisit"));

	}

	@Override
	@Transactional
	public List<Trip> getAllTripsBySearchCriteria(String fromLoc, String toLoc, LocalDate rideDate,
			LocalTime rideTime) {

		List<Trip> trips = tripRepo.findByFromLOCAndToLOCAndRideDateAndRideTime(fromLoc, toLoc, rideDate, rideTime);
		if (trips.isEmpty()) {
			throw new NotFoundExceptionClass("Trip is not Available");

		}

		return trips;
	}

	@Override
	@Transactional
	public String bookRide(BookRide bookRide) {
		Trip existingTrip = tripRepo.findById(bookRide.getTrip().getTripId()).get();
		System.out.println(existingTrip);

		if (existingTrip.getNoOfSeat() == existingTrip.getSeatsFilled()) {
			throw new BookRideException("All Seats are booked");

		} else if (bookRide.getNumberOfSeat() > (existingTrip.getNoOfSeat() - existingTrip.getSeatsFilled())) {
			throw new BookRideException("Avaiable seats are "
					+ (existingTrip.getNoOfSeat() - existingTrip.getSeatsFilled()) + ". Please select under the limit");

		} else if (bookRide.getNumberOfSeat() <= 0) {
			throw new BookRideException("Selected Seats must be greater than zero");

		}

		if (existingTrip.getRideStatus().equalsIgnoreCase("Planed")) {

			bookRide.setBookingId((existingTrip.getFromLOC().substring(0, 2) + existingTrip.getToLOC().substring(0, 2)
					+ existingTrip.getRideTime().getHour() + existingTrip.getRideTime().getMinute())
					+ bookRide.getSeekerId());

			existingTrip.addBooking(bookRide);
			bookRide.setTrip(existingTrip);
			bookRide.setStatus("Booked");

			BookRide bookedRide = bookRideRepo.save(bookRide);

			return "Your Trip is booked with id " + bookedRide.getTrip().getTripId();

		} else {
			throw new BookRideException("Trip is already " + existingTrip.getRideStatus());
		}

	}

	@Override
	@Transactional
	public String cancelBookedRide(BookRide bookRide, String seekerId) {

		Trip existingTrip = tripRepo.findById(bookRide.getTrip().getTripId()).get();

		if (!existingTrip.getRideStatus().equalsIgnoreCase("Started")) {

			bookRide.setStatus("Canceled");
			bookRideRepo.save(bookRide);
			return "Your Ride has been Canceled successfully";

		}

		throw new TripAlreadyStartedException("Your Ride is started. Cannot Cancel the Ride");

	}

	@Override
	public List<BookRide> getBookedRides(String seekerId) {
		return bookRideRepo.findAllBySeekerId(seekerId).get();
	}

}
