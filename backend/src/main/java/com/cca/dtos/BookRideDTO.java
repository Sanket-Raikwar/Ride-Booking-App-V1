package com.cca.dtos;

import org.springframework.stereotype.Component;

import com.cca.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BookRideDTO {
	private String bookingId; 
	private int numberOfSeat;
	private String seekerId;
	private String status;
	private Trip trip;

}
