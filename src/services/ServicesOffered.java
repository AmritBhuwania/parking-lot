package services;

import java.util.List;

import exceptions.ParkingLotException;

public interface ServicesOffered {
	
	boolean createParkingLot(String parkingLotSize) throws ParkingLotException;
	
	boolean parkCar(String regNo, String color) throws ParkingLotException;
	
	boolean leaveCar(String regNum) throws ParkingLotException;
	
	List<String> getRegNumsFromColor(String color) throws ParkingLotException;
	
	Integer getTicketOfRegisteredCar(String regNum) throws ParkingLotException;
	
	List<Integer> getAllTicketsPerColor(String color) throws ParkingLotException;
	
	

}
