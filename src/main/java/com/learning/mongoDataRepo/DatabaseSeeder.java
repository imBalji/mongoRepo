package com.learning.mongoDataRepo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.learning.mongoDataRepo.model.Aircraft;
import com.learning.mongoDataRepo.model.Airport;
import com.learning.mongoDataRepo.model.FlightInfo;
import com.learning.mongoDataRepo.model.FlightType;
import com.learning.mongoDataRepo.repo.AirportRepo;
import com.learning.mongoDataRepo.repo.FlightInfoRepo;

// For data entry only
@Component @Order(value = 1)
public class DatabaseSeeder implements CommandLineRunner {
	
	private FlightInfoRepo flightInfoRepo;
	private AirportRepo airportRepo;
	
	public DatabaseSeeder(FlightInfoRepo flightInfoRepo, AirportRepo airportRepo) {
		this.flightInfoRepo = flightInfoRepo;
		this.airportRepo = airportRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		empty();
		seed();
	}
	
	private void empty() {
		this.flightInfoRepo.deleteAll();
		this.airportRepo.deleteAll();
	}

	private void seed() {
		
		Airport Hyderabad = new Airport("613cf4ef71c8531a066c1501", "Rajiv Gandhi International Airport", "Hyderabad", 30298531);
		Airport Mumbai = new Airport("613cf4ef71c8531a066c1502", "Chhatrapati Shivaji Maharaj International Airport", "Mumbai", 40298531);
		Airport Delhi = new Airport("613cf4ef71c8531a066c1503", "Indira Gandhi International Airport", "Delhi", 50298531);
		Airport Kolkata = new Airport("613cf4ef71c8531a066c1504", "Netaji Subhas Chandra Bose International Airport", "Kolkata", 10298531);
		
		Airport Belgium = new Airport("613cf4ef71c8531a066c1505", "Brussels Airport", "Belgium", 10298531);
		Airport Netherlands = new Airport("613cf4ef71c8531a066c1506", "Amsterdam Airport Schiphol", "Netherlands", 20298531);
		Airport Thailand = new Airport("613cf4ef71c8531a066c1507", "Suvarnabhumi Airport", "Thailand", 30298531);
		
		this.airportRepo.insert(List.of(Hyderabad, Mumbai, Delhi, Kolkata, Belgium, Netherlands, Thailand));
		System.out.println("Total airport in database : " + this.airportRepo.count());
		
		FlightInfo flight1 = new FlightInfo();
		flight1.setId("613cf4ef71c8531a066c1591");
		flight1.setDepartureCity(Hyderabad);
		flight1.setDestinationCity(Mumbai);
		flight1.setDescription("An internal flight travelling from Hyderabad to Mumbai");
		flight1.setDurationMin(400);
		flight1.setDelayed(false);
		flight1.setDepartureDate(LocalDate.of(2012, 12, 21));
		flight1.setAircraft(new Aircraft("indiGo", 200));
		flight1.setType(FlightType.Internal);
		
		FlightInfo flight2 = new FlightInfo();
		flight2.setId("613cf4ef71c8531a066c1592");
		flight2.setDepartureCity(Hyderabad);
		flight2.setDestinationCity(Delhi);
		flight2.setDescription("An internal flight travelling from Hyderabad to Delhi");
		flight2.setDurationMin(400);
		flight2.setDelayed(false);
		flight2.setDepartureDate(LocalDate.of(2012, 12, 21));
		flight2.setAircraft(new Aircraft("indiGo", 200));
		flight2.setType(FlightType.Internal);
		
		FlightInfo flight3 = new FlightInfo();
		flight3.setId("613cf4ef71c8531a066c1593");
		flight3.setDepartureCity(Hyderabad);
		flight3.setDestinationCity(Kolkata);
		flight3.setDescription("An internal flight travelling from Hyderabad to Kolkata");
		flight3.setDurationMin(400);
		flight3.setDelayed(false);
		flight3.setDepartureDate(LocalDate.of(2012, 12, 21));
		flight3.setAircraft(new Aircraft("indiGo", 200));
		flight3.setType(FlightType.Internal);
		
		FlightInfo flight4 = new FlightInfo();
		flight4.setId("613cf4ef71c8531a066c1594");
		flight4.setDepartureCity(Mumbai);
		flight4.setDestinationCity(Netherlands);
		flight4.setDescription("An international flight travelling from Mumbai to Netherlands");
		flight4.setDurationMin(1400);
		flight4.setDelayed(false);
		flight4.setDepartureDate(LocalDate.of(2021, 12, 21));
		flight4.setAircraft(new Aircraft("indiGo", 200));
		flight4.setType(FlightType.International);
		
		FlightInfo flight5 = new FlightInfo();
		flight5.setId("613cf4ef71c8531a066c1595");
		flight5.setDepartureCity(Mumbai);
		flight5.setDestinationCity(Belgium);
		flight5.setDescription("An international flight travelling from Mumbai to Belgium");
		flight5.setDurationMin(1400);
		flight5.setDelayed(false);
		flight5.setDepartureDate(LocalDate.of(2021, 12, 21));
		flight5.setAircraft(new Aircraft("indiGo", 200));
		flight5.setType(FlightType.International);
		
		FlightInfo flight6 = new FlightInfo();
		flight6.setId("613cf4ef71c8531a066c1596");
		flight6.setDepartureCity(Mumbai);
		flight6.setDestinationCity(Thailand);
		flight6.setDescription("An international flight travelling from Mumbai to Thailand");
		flight6.setDurationMin(1400);
		flight6.setDelayed(false);
		flight6.setDepartureDate(LocalDate.of(2021, 12, 21));
		flight6.setAircraft(new Aircraft("indiGo", 200));
		flight6.setType(FlightType.International);
		
		this.flightInfoRepo.insert(List.of(flight1, flight2, flight3, flight4, flight5, flight6));
		
		System.out.println("Total flights in database : " + this.flightInfoRepo.count());
		
		new InfoPrinter(this.flightInfoRepo.findAll(Sort.by("departureCity").ascending()));
		
		System.out.println("--- Seeder finished ---\n");
	}
}