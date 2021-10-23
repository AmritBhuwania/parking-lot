package services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import entity.Car;
import exceptions.ErrorCode;
import exceptions.ParkingLotException;
import services.ServicesOffered;

public class ServicesOfferedImpl implements ServicesOffered {

	private static Logger logger = Logger.getLogger(ServicesOffered.class);

	// initial capacity as 0
	private int parkingLotCapacity = 0;

	private static ServicesOfferedImpl servicesImplInstance;

	List<Integer> availableParkingLotList;

	// Map of Color and List of Registration numbers
	Map<String, List<String>> carColorRegListMap;

	// Map of ticket number with a given registration number
	Map<String, String> carRegNoTicketMap;

	// Map of RegNo and Slot
	Map<String, List<String>> carColorTicketListMap;

	// singleton pattern
	public static ServicesOfferedImpl getInstance() {

		if (servicesImplInstance == null) {

			synchronized (ServicesOfferedImpl.class) {

				if (servicesImplInstance == null) {
					servicesImplInstance = new ServicesOfferedImpl();
				}
			}
		}

		return servicesImplInstance;
	}


	@Override
	public void createParkingLot(String lotCount) throws ParkingLotException {

		try {

			this.parkingLotCapacity = Integer.parseInt(lotCount);

			this.availableParkingLotList = new ArrayList<Integer>();

			this.carColorRegListMap = new HashMap<String, List<String>>();
			this.carRegNoTicketMap = new HashMap<String, String>();
			this.carColorTicketListMap = new HashMap<String, List<String>>();

			logInfoMessage(String.format("Parking lot created successsfully with capacity as: [%d]", parkingLotCapacity));

		} catch (NumberFormatException e) {
			throw new ParkingLotException(ErrorCode.INVALID_PARKINGLOT_SIZE.getErrorMsg(), e);
		}

	}


	@Override
	public boolean parkCar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> getRegistrationNumbers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTicketOfRegisteredCar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllTicketsPerColor() {
		// TODO Auto-generated method stub
		return null;
	}

	private void logInfoMessage(String message) {
		logger.info(String.format("%s%s%s",Thread.currentThread().getName(), "\t", message));
	}
	
	// Testing Purpose
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		ServicesOfferedImpl offered = ServicesOfferedImpl.getInstance();
		
		try {
			offered.createParkingLot("5");
		} catch (ParkingLotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
