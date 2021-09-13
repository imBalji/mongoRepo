package com.learning.mongoDataRepo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.mongoDataRepo.model.Airport;

public interface AirportRepo extends MongoRepository<Airport, String> {
}