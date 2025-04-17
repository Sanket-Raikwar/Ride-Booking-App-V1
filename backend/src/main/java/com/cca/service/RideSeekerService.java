package com.cca.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.cca.entities.BookRide;
import com.cca.entities.RideSeeker;
import com.cca.entities.Trip;

public interface RideSeekerService {

	String registerRideSeeker(RideSeeker rideSeeker);

	String updateRideSeeker(String rsId, RideSeeker rideSeeker);

	RideSeeker getById(String rsID);

	List<Trip> getAllTripsBySearchCriteria(String fromLoc, String toLoc, LocalDate date, LocalTime time);

	String bookRide(BookRide bookRide);

	String cancelBookedRide(BookRide bookRide, String seekerId);

	List<BookRide> getBookedRides(String seekerId);



}
