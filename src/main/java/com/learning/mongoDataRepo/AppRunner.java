package com.learning.mongoDataRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.learning.mongoDataRepo.model.Airport;
import com.learning.mongoDataRepo.repo.AirportRepo;
import com.learning.mongoDataRepo.repo.FlightInfoRepo;

@Service @Order(value = 2)
public class AppRunner implements CommandLineRunner {
	
	private FlightInfoRepo flightInfoRepo;
	private AirportRepo airportRepo;
	
	public AppRunner(FlightInfoRepo flightInfoRepo, AirportRepo airportRepo) {
		this.flightInfoRepo = flightInfoRepo;
		this.airportRepo = airportRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		Airport temp = this.airportRepo.findById("613cf4ef71c8531a066c1507").get();
		temp.setName("Noi Bai International Airport");
		temp.setCity("Vietnam");
		this.airportRepo.save(temp);
		
		System.out.println("-> After update to Vietnam airport");
		new InfoPrinter(this.flightInfoRepo.findAll());
	}
}