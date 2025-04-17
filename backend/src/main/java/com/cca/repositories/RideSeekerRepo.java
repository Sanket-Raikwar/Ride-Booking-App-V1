package com.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cca.entities.RideSeeker;

public interface RideSeekerRepo extends JpaRepository<RideSeeker, String> {
}
