package com.cca.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cca.entities.BookRide;


public interface BookRideRepo extends JpaRepository<BookRide, String> {

	Optional<BookRide> findBySeekerId(String seekerId);
	Optional<List<BookRide>> findAllBySeekerId(String seekerId);

}
