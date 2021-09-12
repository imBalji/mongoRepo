package com.learning.mongoDataRepo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.learning.mongoDataRepo.model.FlightInfo;
import com.learning.mongoDataRepo.repo.FlightInfoRepo;

@Service @Order(value = 2)
public class AppRunner implements CommandLineRunner {
	
	private FlightInfoRepo flightInfoRepo;
	
	public AppRunner(FlightInfoRepo flightInfoRepo) {
		this.flightInfoRepo = flightInfoRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		printById("613cf4ef71c8531a066c1593");
		delayFlight("613cf4ef71c8531a066c1593", 30);
		printByDepartureAndDestination("Hyderabad", "Delhi");
		printByMinNbSeats(200);
		removeFlight("613cf4ef71c8531a066c1593");
	}
	
	private void printById(String id) {
		System.out.println("Flight " + id);
		new InfoPrinter(List.of(this.flightInfoRepo.findById(id).get()));
	}
	
	private void delayFlight(String id, int duration) {
		FlightInfo flight = this.flightInfoRepo.findById(id).get();
		flight.setDurationMin(flight.getDurationMin() + duration);
		this.flightInfoRepo.save(flight);
		System.out.println("Updated flight " + id + "\n");
	}
	
	private void removeFlight(String id) {
		this.flightInfoRepo.deleteById(id);
		System.out.println("Deleted flight " + id + "\n");
	}
	
	private void printByDepartureAndDestination(String dep, String dst) {
		new InfoPrinter(this.flightInfoRepo.findByDepartureCityAndDestinationCity(dep, dst));
	}
	
	private void printByMinNbSeats(int minNbSeats) {
		new InfoPrinter(this.flightInfoRepo.findByMinNbSeats(minNbSeats));
	}
}