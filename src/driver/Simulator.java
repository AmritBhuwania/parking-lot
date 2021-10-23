package driver;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import exceptions.ParkingLotException;
import services.impl.ServicesOfferedImpl;

public class Simulator {

	private static Logger logger = Logger.getLogger(Simulator.class);

	public static void main(String[] args) {

		BasicConfigurator.configure();

		Scanner sc = new Scanner(System.in);

		ServicesOfferedImpl offeredImpl = ServicesOfferedImpl.getInstance();

		logInfoMessage("Enter a value: ");
		int userInput = sc.nextInt();

		while (userInput != -1) {

			logInfoMessage("Choose an option: ");
			userInput = sc.nextInt();

			switch (userInput) {

			// Create parking lot of given size
			case 1:

				logInfoMessage("Enter the maximum car pool size: ");
				String maxParkingLotSize = sc.next();

				try {
					offeredImpl.createParkingLot(maxParkingLotSize);
				} catch (ParkingLotException e) {
					logger.error(e.getMessage() + ", Exception: " + e);
				}

				break;

				// park a car
			case 2:

				logInfoMessage("Car is getting ready to get parked. Enter Registration number and color details of car.");

				logInfoMessage("Enter the registration number of car: ");
				String regNo = sc.next();
				logInfoMessage("Enter the color of car: ");
				String color = sc.next();

				boolean isCarParkedSuccess = offeredImpl.parkCar(regNo, color);
				logInfoMessage(String.format("Is car successfully parked: %s", isCarParkedSuccess));

				break;

				// vacate car from parking lot
			case 3:
				logInfoMessage("Enter the registration number of car to vacate: ");
				String regNum = sc.next();

				boolean isCarVacatedSuccess = offeredImpl.leaveParkingLot(regNum);
				logInfoMessage(String.format("Is car successfully vacated: %s", isCarVacatedSuccess));

				break;

				// Registration numbers of all cars of a particular Color.
			case 4:
				logInfoMessage("Enter the color to get all car registration numbers with provided color: ");
				color = sc.next();

				List<String> regNums = offeredImpl.getRegNumsFromColor(color);

				if (null != regNums) {
					logInfoMessage(String.format("Car registration numbers with color %s are: %s",
							color, regNums));
				} else {
					logInfoMessage(String.format("No cars are parked with color %s", color));
				}

				break;

				// Ticket number in which a car with a given registration number is placed.
			case 5:
				logInfoMessage("Enter the registration number of car to get ticket number: ");
				regNum = sc.next();

				Integer ticketNum = offeredImpl.getTicketOfRegisteredCar(regNum);

				if (null != ticketNum) {
					logInfoMessage(String.format("Car ticket number with registration number %s is: %s",
							regNum, offeredImpl.getTicketOfRegisteredCar(regNum)));
				} else {
					logInfoMessage(String.format("No cars is yet parked with registration num as: %s", regNum));
				}

				break;

				// Ticket numbers of all ticket where a car of a particular color is placed.	
			case 6:
				logInfoMessage("Enter the color to get all car ticket numbers with provided color: ");
				color = sc.next();

				List<Integer> ticketNums = offeredImpl.getAllTicketsPerColor(color);

				if (null != ticketNums) {
					logInfoMessage(String.format("Car ticket numbers with color: %s are %s",
							color, ticketNums));
				} else {
					logInfoMessage(String.format("No cars are parked with color: %s", color));
				}

				break;

			case -1:
				logInfoMessage("Exiting now!");
				sc.close();

				System.exit(0);

			default:
				logInfoMessage("Invalid input, please try again with a valid input!");
				break;
			}

		}

	}

	private static void logInfoMessage(String message) {
		logger.info(String.format("%s%s%s",Thread.currentThread().getName(), "\t", message));
	}

}
