package services;

import java.util.List;

import exceptions.ParkingLotException;

public interface ServicesOffered {
	
	void createParkingLot(String parkingLotSize) throws ParkingLotException ;
	
	boolean parkCar() throws ParkingLotException ;
	
	List<Integer> getRegistrationNumbers() throws ParkingLotException ;
	
	Integer getTicketOfRegisteredCar() throws ParkingLotException ;
	
	List<Integer> getAllTicketsPerColor() throws ParkingLotException ;
	
	

}
