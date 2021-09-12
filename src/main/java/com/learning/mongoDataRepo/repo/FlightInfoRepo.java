package com.learning.mongoDataRepo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.mongoDataRepo.model.FlightInfo;

@Repository
public interface FlightInfoRepo extends MongoRepository<FlightInfo, String> {
	
	List<FlightInfo> findByDepartureCityAndDestinationCity(String departure, String destination);
	
	@Query(value = "{'aircraft.nbSeats': {$gte: ?0}}")
	List<FlightInfo> findByMinNbSeats(int minNbSeats);
}