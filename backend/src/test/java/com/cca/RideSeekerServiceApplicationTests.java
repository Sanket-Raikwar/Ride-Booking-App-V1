package com.cca;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.cca.dtos.BookRideDTO;
import com.cca.dtos.ResponseDTO;
import com.cca.dtos.RideSeekerDTO;
import com.cca.entities.BookRide;
import com.cca.entities.Trip;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RideSeekerServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	ObjectMapper objectMapper;

	// **********Test for Registering RideSeeker***********************//

	@Test
	@Order(1)
	public void testForRegisteringRideSeeker() throws Exception {
		RideSeekerDTO newRideSeeker = new RideSeekerDTO("100000000011", "Sanket@cognizant.com", "1234567890", "Sanket",
				"Rikwar", LocalDate.of(2000, 06, 20), "ABC");
		String jsonRideSeeker = objectMapper.writeValueAsString(newRideSeeker);

		mockMvc.perform(post("/api/rideSeeker/new").content(jsonRideSeeker).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.msg").value("RERI00"));
	}

	// **********Test for Getting List of Trips***********************//
	@Test
	@Order(2)
	public void should_Return_List_Of_Trip_Which_Matches_Source_And_Destination_And_Date_And_Time() throws Exception {
		mockMvc.perform(get("/api/rideSeeker/trips/Jabalpur/Pune/2023-09-14/07:00:00")).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	@Order(3)
	public void testForTripIsNotExisit() throws Exception {
		mockMvc.perform(get("/api/rideSeeker/trips/Pune/Jabalpu/2023-08-29/12:00:00")).andExpect(status().isNotFound());

	}

	// **********Test for BOoking Ride***********************//

	@Test
	@Order(4)
	public void testForBookRide() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip01");
		BookRide bookRide = new BookRide(2, "RERI00", trip);

		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(post("/api/rideSeeker/bookride").content(jsonBookRide).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.msg").value("Your Trip is booked with id trip01"));
	}
	
	@Test
	@Order(5)
	public void testForBookAnotherRide() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip1");
		BookRide bookRide = new BookRide(2, "RERI00", trip);
		
		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(post("/api/rideSeeker/bookride").content(jsonBookRide).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.msg").value("Your Trip is booked with id trip1"));
	}

	@Test
	@Order(6)
	public void testForBookRideGreaterThanAvailableSeats() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip3");
		BookRide bookRide = new BookRide(6, "RERI00", trip);

		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(post("/api/rideSeeker/bookride").content(jsonBookRide).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errMessage").value("Avaiable seats are 5. Please select under the limit"));

	}

	@Test
	@Order(7)
	public void testForBookRideWithZeroSeats() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip5");
		BookRide bookRide = new BookRide(0, "RERI00", trip);
		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(post("/api/rideSeeker/bookride").content(jsonBookRide).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errMessage").value("Selected Seats must be greater than zero"));

	}

	@Test
	@Order(8)
	public void testForBookRideWhenTripIsAlreadyCanceledOrStartedOrEnded() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip6");
		BookRide bookRide = new BookRide(2, "RERI00", trip);
		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(post("/api/rideSeeker/bookride").content(jsonBookRide).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errMessage").value("Trip is already Started"));

	}

	// **********Test for Canceling Booked Ride***********************//
	@Test
	@Order(9)
	public void testForCancelRide() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip01");
		BookRideDTO bookRide = new BookRideDTO("BaPu100RERI00", 1, "RERI00", "Booked", trip);
		bookRide.setTrip(trip);
		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(put("/api/rideSeeker/bookride/SuSe/update").content(jsonBookRide)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", Matchers.is("Your Ride has been Canceled successfully")));

	}
	
	@Test
	@Order(10)
	public void testForCancelRideWhenItIsStarted() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip6");
		BookRideDTO bookRide = new BookRideDTO("BaMu110", 1, "RERA01", "Booked", trip);
		bookRide.setTrip(trip);
		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(put("/api/rideSeeker/bookride/SuSe/update").content(jsonBookRide)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("errMessage", Matchers.is("Your Ride is started. Cannot Cancel the Ride")));

	}

	// **********Test for Update RideSeeker***********************//

	@Test
	@Order(11)
	public void testForUnRegisterRideSeekerWhichDoesNotExisit() throws Exception {
		RideSeekerDTO updatedRideSeeker = new RideSeekerDTO("100000000011", "Sanket@cognizant.com", "1234567890",
				"Sanket", "Rikwar", LocalDate.of(2000, 06, 20), "ABC", "Un-registered");
		String jsonRideSeeker = objectMapper.writeValueAsString(updatedRideSeeker);
		mockMvc.perform(
				put("/api/rideSeeker/ABC/update").content(jsonRideSeeker).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}
	

	@Test
	@Order(12)
	public void testForUnRegisterRideSeekerWhichHasBookedRide() throws Exception {
		RideSeekerDTO updatedRideSeeker = new RideSeekerDTO("100000000011", "Sanket@cognizant.com", "1234567890",
				"Sanket", "Rikwar", LocalDate.of(2000, 06, 20), "ABC", "Un-registered");
		String jsonRideSeeker = objectMapper.writeValueAsString(updatedRideSeeker);
		mockMvc.perform(
				put("/api/rideSeeker/RERI00/update").content(jsonRideSeeker).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errMessage")
						.value("You can not un-register , you have a ride on 2023-09-14 07:00 from Jabalpur to Pune"));
	}

	
	@Test
	@Order(13)
	public void testForAnotherCancelRide() throws Exception {
		Trip trip = new Trip();
		trip.setTripId("trip1");
		BookRideDTO bookRide = new BookRideDTO("JaPu70RERI00", 1, "RERI00", "Booked", trip);
		bookRide.setTrip(trip);
		String jsonBookRide = objectMapper.writeValueAsString(bookRide);
		mockMvc.perform(put("/api/rideSeeker/bookride/SuSe/update").content(jsonBookRide)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", Matchers.is("Your Ride has been Canceled successfully")));

	}
	
	
	@Test
	@Order(14)
	public void testForUnRegisterRideSeeker() throws Exception {
		RideSeekerDTO updatedRideSeeker = new RideSeekerDTO("100000000011", "Sanket@cognizant.com", "1234567890",
				"Sanket", "Rikwar", LocalDate.of(2000, 06, 20), "ABC", "Un-registered");
		String jsonRideSeeker = objectMapper.writeValueAsString(updatedRideSeeker);
		ResponseDTO res = new ResponseDTO("Ride Seeker with Id RERI00 is Un-registered Successfully");
		mockMvc.perform(
				put("/api/rideSeeker/RERI00/update").content(jsonRideSeeker).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg").value("Ride Seeker with Id RERI00 is Un-registered Successfully"));
	}

}
