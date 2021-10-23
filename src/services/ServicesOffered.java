package services;

import java.util.List;

import exceptions.ParkingLotException;

public interface ServicesOffered {
	
	void createParkingLot(String parkingLotSize) throws ParkingLotException;
	
	boolean parkCar(String regNo, String color) throws ParkingLotException;
	
	void leaveCar(String regNum) throws ParkingLotException;
	
	List<Integer> getRegistrationNumbers() throws ParkingLotException;
	
	Integer getTicketOfRegisteredCar() throws ParkingLotException;
	
	List<Integer> getAllTicketsPerColor() throws ParkingLotException;
	
	

}
