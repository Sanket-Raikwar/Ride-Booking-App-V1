package com.cca.repositories;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cca.entities.Trip;

public interface TripRepo extends JpaRepository<Trip, String> {

	List<Trip> findByFromLOCAndToLOCAndRideDateAndRideTime(String fromloc, String toLoc, LocalDate rideDate,
			LocalTime rideTime);

	Optional<List<Trip>> findByTripId(String tripId);

}
