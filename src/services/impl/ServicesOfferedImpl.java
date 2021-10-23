package services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import entity.Car;
import exceptions.ErrorCode;
import exceptions.ParkingLotException;
import services.ServicesOffered;

//Problem Statement/ Functionality
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

	List<Car> carsInParkingLot = new ArrayList<Car>();

	// Map of Color and List of Registration numbers
	Map<String, List<String>> carColorRegListMap;

	// Map of registration number and ticket number
	Map<String, Integer> carRegNoTicketMap;

	// Map of Color and ticket number
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
			this.carsInParkingLot = new ArrayList<Car>();

			for (int i = 1; i <= parkingLotCapacity; ++i) {
				availableParkingLotList.add(i);
			}

			this.carColorRegListMap = new HashMap<String, List<String>>();
			this.carRegNoTicketMap = new HashMap<String, Integer>();
			this.carColorTicketListMap = new HashMap<String, List<Integer>>();

			logInfoMessage(String.format("Parking lot created successsfully with capacity as: [%d]", parkingLotCapacity));

		} catch (NumberFormatException e) {
			throw new ParkingLotException(ErrorCode.INVALID_PARKINGLOT_SIZE, e);
		}
	}

	@Override
	public boolean parkCar(String regNo, String color) throws ParkingLotException {
		try {

			if (this.parkingLotCapacity == 0) {
				throw new ParkingLotException(ErrorCode.NO_PARKINGLOT_CREATED);

			} else if (this.carRegNoTicketMap.size() >= this.parkingLotCapacity) {
				throw new ParkingLotException(ErrorCode.PARKINGLOT_FULL);

			} else {
				++ticketNum;

				Car car = new Car(color, regNo);
				this.carsInParkingLot.add(car);

				logInfoMessage(String.format("Ticket number is: [%d]", ticketNum));

				if (this.carColorRegListMap.containsKey(color)) {
					this.carColorRegListMap.get(color).add(regNo);

				} else {
					this.carColorRegListMap.put(color, new ArrayList<String>(Arrays.asList(regNo)));
				}

				this.carRegNoTicketMap.put(regNo, ticketNum);

				if (this.carColorTicketListMap.containsKey(color)) {
					this.carColorTicketListMap.get(color).add(ticketNum);

				} else {
					this.carColorTicketListMap.put(color, new ArrayList<Integer>(Arrays.asList(ticketNum)));
				}

				//remove the slot for which ticket is provided
				this.availableParkingLotList.remove(new Integer(ticketNum));

				logInfoMessage(String.format("Map1 : %s", carColorRegListMap));
				logInfoMessage(String.format("Map2 : %s", carRegNoTicketMap));
				logInfoMessage(String.format("Map3 : %s", carColorTicketListMap));

				logInfoMessage(String.format("Allocated ticket: [%d], slots remaining: [%d]", ticketNum,
						this.parkingLotCapacity - this.carRegNoTicketMap.size()));

				System.out.println("\n\n");

			}
		} catch (ParkingLotException e) {
			// TODO Auto-generated catch block
			logger.error(String.format("ErrorCode: [%s], ErrorMessage: [%s]",
					e.getErrorCode().getErrorCode(), e.getErrorCode()));
		}

		return false;
	}

	@Override
	public void leaveCar(String regNum) throws ParkingLotException {

		try {

			if (this.parkingLotCapacity == 0) {
				throw new ParkingLotException(ErrorCode.NO_PARKINGLOT_CREATED);

			} else if (!this.carRegNoTicketMap.containsKey(regNum)) {
				throw new ParkingLotException(ErrorCode.INVALID_REG_NUM);

			} else {

				int ticketNum = this.carRegNoTicketMap.get(regNum);
				logInfoMessage(String.format("Ticket num is: [%d] and regNum is: [%s]", ticketNum, regNum));
				this.carRegNoTicketMap.remove(regNum);

				Car car = carsInParkingLot.stream().filter(c -> c.getRegNo().equalsIgnoreCase(regNum))
						.collect(Collectors.toList()).get(0);

				this.carsInParkingLot.remove(car);

				logInfoMessage(String.format("Car obj: [%s]", car));

				String color = car.getColor();
				if (this.carColorRegListMap.containsKey(color)) {
					this.carColorRegListMap.get(color).remove(car.getRegNo());

					if (this.carColorRegListMap.get(color).size() == 0) {
						this.carColorRegListMap.remove(color);
					}
				}

				if (this.carColorTicketListMap.containsKey(color)) {
					List<Integer> ticketList = this.carColorTicketListMap.get(color);

					// create ticket object of Integer type
					ticketList.remove(new Integer(ticketNum));
					if (ticketList.size() > 0) {
						this.carColorTicketListMap.put(color, ticketList);
					}
					else {
						this.carColorTicketListMap.remove(color);
					}
				}

				logInfoMessage(String.format("Map1 : %s", carColorRegListMap));
				logInfoMessage(String.format("Map2 : %s", carRegNoTicketMap));
				logInfoMessage(String.format("Map3 : %s", carColorTicketListMap));

				logInfoMessage(String.format("Removed car with ticket: [%d], slots remaining: [%d]", ticketNum,
						this.parkingLotCapacity - this.carRegNoTicketMap.size()));

				System.out.println("\n");

			}
		}catch (ParkingLotException e) {
			// TODO Auto-generated catch block
			logger.error(String.format("ErrorCode: [%s], ErrorMessage: [%s]",
					e.getErrorCode().getErrorCode(), e.getErrorCode()));
		}

	}

	@Override
	public List<String> getRegNumsFromColor(String color) throws ParkingLotException {

		return this.carColorRegListMap.get(color);

	}

	@Override
	public Integer getTicketOfRegisteredCar(String regNum) throws ParkingLotException {

		return this.carRegNoTicketMap.get(regNum);
	}

	@Override
	public List<Integer> getAllTicketsPerColor(String color) throws ParkingLotException {
		return this.carColorTicketListMap.get(color);
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


		offered.createParkingLot("5");

		offered.parkCar("ABC1", "blue"); // 1 
		offered.parkCar("ABC2", "black"); //2
		offered.parkCar("ABC3", "red"); // 3
		offered.parkCar("ABC4", "black"); //4

		offered.parkCar("ABC5", "blue"); //5
		//offered.leaveCar("ABC4"); //-4

		offered.leaveCar("ABC7");
		offered.leaveCar("ABC4");
		offered.leaveCar("ABC3");
//		offered.leaveCar("ABC1");

		offered.parkCar("ABC7", "blue"); //6

		offered.parkCar("ABC8", "marron"); //

		System.out.println(offered.getRegNumsFromColor("blue"));
		System.out.println(offered.getAllTicketsPerColor("blue"));
		System.out.println(offered.getTicketOfRegisteredCar("ABC5"));

	}
}
