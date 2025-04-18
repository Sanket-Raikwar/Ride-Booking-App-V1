package com.cca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cca.dtos.BookRideDTO;
import com.cca.dtos.ResponseDTO;
import com.cca.dtos.RideSeekerDTO;
import com.cca.entities.BookRide;
import com.cca.entities.RideSeeker;
import com.cca.entities.Trip;
import com.cca.service.RideSeekerService;
import com.cca.util.DTOMapping;

@CrossOrigin
@RestController
@RequestMapping("/api/rideSeeker")
public class RideSeekerController {

	@Autowired
	RideSeekerService rideSeekerService;

//    Register a Ride Seeker

	@PostMapping("/new")
	public ResponseEntity<ResponseDTO> registerRideSeekerIntoDB(@Valid @RequestBody RideSeekerDTO rideSeekerDTO) {
		String registerRideSeekerId = rideSeekerService.registerRideSeeker(DTOMapping.mapToRideSeeker(rideSeekerDTO));
		ResponseDTO res = new ResponseDTO(registerRideSeekerId);

		return new ResponseEntity<>(res, HttpStatus.CREATED);

	}

//	Update Ride Seeker's Details and Un-register themselves

	@PutMapping("/{rsId}/update")
	public ResponseEntity<ResponseDTO> update(@PathVariable("rsId") String rsId,
			@RequestBody RideSeekerDTO rideSeekerDTO) {
		String responseMessage = rideSeekerService.updateRideSeeker(rsId, DTOMapping.mapToRideSeeker(rideSeekerDTO));
		ResponseDTO res = new ResponseDTO(responseMessage);
		System.out.println("Hello");

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

//	get ride seeker by id

	@GetMapping("/get/{rsId}")
	public ResponseEntity<RideSeeker> getRideSeeker(@PathVariable("rsId") String rsId) {

		RideSeeker rideSeeker = rideSeekerService.getById(rsId);
		return new ResponseEntity<>(rideSeeker, HttpStatus.OK);
	}

//	get all the trips with the search criteria

	@GetMapping("/trips/{fromLoc}/{toLoc}/{rideDate}/{rideTime}")
	public ResponseEntity<List<Trip>> getTripsBySearch(@PathVariable("fromLoc") String fromLoc,
			@PathVariable("toLoc") String toLoc, @PathVariable("rideDate") String rideDate,
			@PathVariable("rideTime") String rideTime) {

		List<Trip> allTripsBySearchCriteria = rideSeekerService.getAllTripsBySearchCriteria(fromLoc, toLoc,
				LocalDate.parse(rideDate), LocalTime.parse(rideTime));

		return new ResponseEntity<List<Trip>>(allTripsBySearchCriteria, HttpStatus.OK);
	}

//	Ride Seeker can book a ride

	@PostMapping("/bookride")
	public ResponseEntity<ResponseDTO> bookRide(@RequestBody BookRideDTO bookRideDTO) {

		String bookRideStaus = rideSeekerService.bookRide(DTOMapping.mapTOBookRide(bookRideDTO));
		ResponseDTO res = new ResponseDTO(bookRideStaus);

		return new ResponseEntity<>(res, HttpStatus.CREATED);

	}

//	Ride Seeker can cancel a ride
	@PutMapping("/bookride/{seekerId}/update")
	public ResponseEntity<ResponseDTO> cancelBookedRide(@RequestBody BookRideDTO bookRideDTO,
			@PathVariable("seekerId") String seekerId) {
		String cancelBookedRideStatus = rideSeekerService.cancelBookedRide(DTOMapping.mapTOBookRide(bookRideDTO),
				seekerId);

		ResponseDTO res = new ResponseDTO(cancelBookedRideStatus);

		return new ResponseEntity<>(res, HttpStatus.OK);

	}

//	Ride Seeker can get booked ride
	@GetMapping(value = "/bookedrides/{seekerId}")
	public List<BookRide> getMyBookedRides(@PathVariable String seekerId) {
		return rideSeekerService.getBookedRides(seekerId);
	}

}
