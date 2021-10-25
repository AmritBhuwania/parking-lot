package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.ParkingLotException;
import services.impl.ParkingLot;

public class ParkingLotCreationTest {

	ParkingLot parkingLot;
	
	// Create parking lot
	@Before
	public void createInstance() {
		parkingLot = ParkingLot.getInstance();
	}

	// Create parking lot for first time
	@Test
	public void createParkingLot() throws Exception {
		boolean isPrakingLotCreated = parkingLot.createParkingLot("4");
		assertEquals(isPrakingLotCreated, true);
	}

	// Create parking lot again
	@Test
	public void createAnotherParkingLot() throws Exception {
		boolean isPrakingLotCreated = parkingLot.createParkingLot("5");
		assertEquals(isPrakingLotCreated, false);
	}

	// Create parking lot with a non-integer number
	@Test(expected = ParkingLotException.class)
	public void createAnotherParkingLot2() throws Exception {
		parkingLot.createParkingLot("5.5");
	}


	// Create parking lot with a non-positive number
	@Test
	public void createAnotherParkingLot3() throws Exception {
		boolean isPrakingLotCreated = parkingLot.createParkingLot("-5");
		assertEquals(isPrakingLotCreated, false);
	}

}
