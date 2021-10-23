package services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;

import entity.Car;
import exceptions.ErrorCode;
import exceptions.ParkingLotException;
import services.ServicesOffered;

//* Registration numbers of all cars of a particular Color.
//* Ticket number in which a car with a given registration number is placed.
//* Ticket numbers of all ticket where a car of a particular color is placed.
//
//# This should be simple command line utility with minimum these capabilities
//- create a parking lot
//- park cars in it
//- run the above specified queries

public class ServicesOfferedImpl implements ServicesOffered {

	private static Logger logger = Logger.getLogger(ServicesOffered.class);

	// initial capacity as 0
	private int parkingLotCapacity = 0;
	private static int ticketNum = 0;

	private static ServicesOfferedImpl servicesImplInstance;

	List<Integer> availableParkingLotList;

	// Map of Color and List of Registration numbers
	Map<String, List<String>> carColorRegListMap;

	// Map of ticket number with a given registration number
	Map<Integer, String> carRegNoTicketMap;

	// Map of RegNo and ticket number
	Map<String, List<Integer>> carColorTicketListMap;

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
			
			for (int i = 1; i <= parkingLotCapacity; ++i) {
				availableParkingLotList.add(i);
			}

			this.carColorRegListMap = new HashMap<String, List<String>>();
			this.carRegNoTicketMap = new HashMap<Integer, String>();
			this.carColorTicketListMap = new HashMap<String, List<Integer>>();

			logInfoMessage(String.format("Parking lot created successsfully with capacity as: [%d]", parkingLotCapacity));

		} catch (NumberFormatException e) {
			throw new ParkingLotException(ErrorCode.INVALID_PARKINGLOT_SIZE.getErrorMsg(), e);
		}

	}


	@Override
	public boolean parkCar(String regNo, String color) throws ParkingLotException {

		try {

			if (this.parkingLotCapacity == 0) {
				System.out.println("here");
				throw new ParkingLotException(ErrorCode.NO_PARKINGLOT_CREATED.getErrorMsg());

			} else if (this.carColorRegListMap.size() == this.parkingLotCapacity) {
				System.out.println("There");
				throw new ParkingLotException(ErrorCode.PARKINGLOT_FULL.getErrorMsg());

			} else {
				++ticketNum;

				Collections.sort(availableParkingLotList);

				int ticket = availableParkingLotList.get(0);
				System.out.println(ticket);

				if (this.carColorRegListMap.containsKey(color)) {
					this.carColorRegListMap.get(color).add(regNo);

				} else {
					this.carColorRegListMap.put(color, new ArrayList<String>(Arrays.asList(regNo)));
				}

				this.carRegNoTicketMap.put(ticket, regNo);

				if (this.carColorTicketListMap.containsKey(color)) {
					this.carColorTicketListMap.get(color).add(ticket);

				} else {
					this.carColorTicketListMap.put(color, new ArrayList<Integer>(Arrays.asList(ticket)));
				}

				// remove the slot for which ticket is provided
				this.availableParkingLotList.remove(0);
				
				logInfoMessage(String.format("Map1 : %s", carColorRegListMap));
				logInfoMessage(String.format("Map2 : %s", carRegNoTicketMap));
				logInfoMessage(String.format("Map3 : %s", carColorTicketListMap));
				
				logInfoMessage(String.format("Allocated ticket: [%d], slots remaining: [%d]", ticket,
						this.availableParkingLotList.size()));
				
				System.out.println("\n\n");

			}
		} catch (Exception e) {
			logExceptionMessage("Exception: ", e);
		}


		return false;
	}



	//				Map<String, List<String>> carColorRegListMap;
	//				Map<String, String> carRegNoTicketMap;
	//				Map<String, List<String>> carColorTicketListMap;
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
	
	private void logExceptionMessage(String message, Exception e) {
		logger.error(String.format("%s%s%s", Thread.currentThread().getName(), "\t", message), e);
	}

	// Testing Purpose
	public static void main(String[] args) {

		BasicConfigurator.configure();
		ServicesOfferedImpl offered = ServicesOfferedImpl.getInstance();

		try {
			offered.createParkingLot("5");
			
			offered.parkCar("ABC1", "blue");
			offered.parkCar("ABC2", "black");
			offered.parkCar("ABC3", "red");
			offered.parkCar("ABC4", "black");
			
		} catch (ParkingLotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
