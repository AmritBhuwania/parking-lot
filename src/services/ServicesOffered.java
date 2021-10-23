package services;

import java.util.List;

public interface ServicesOffered {
	
	void createParkingLot();
	
	boolean parkCar();
	
	List<Integer> getRegistrationNumbers();
	
	Integer getTicketOfRegisteredCar();
	
	List<Integer> getAllTicketsPerColor();
	
	

}
