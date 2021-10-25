package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import exceptions.ParkingLotException;
import services.impl.ParkingLot;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ParkingLotCreationTest {

	ParkingLot parkingLot;

	// Create parking lot
	@Before
	public void createInstance() {
		parkingLot = ParkingLot.getInstance();
	}

	// Create parking lot for first time
	@Test
	//public void createParkingLot_1() throws Exception {
	public void testcase_1() throws Exception {
		boolean isParkingLotCreated = parkingLot.createParkingLot("4");
		assertEquals(isParkingLotCreated, true);
	}

	// Create parking lot again
	@Test
	public void testcase_2() throws Exception {
		boolean isParkingLotCreated = parkingLot.createParkingLot("5");
		assertEquals(isParkingLotCreated, false);
	}

	// Create parking lot with a non-integer number
	@Test(expected = ParkingLotException.class)
	public void createParkingLotWithNonInteger_3() throws Exception {
		parkingLot.createParkingLot("5.5");
	}

	// Create parking lot with a non-positive number
	@Test
	public void createParkingLotWithNonPositive_4() throws Exception {
		boolean isPrakingLotCreated = parkingLot.createParkingLot("-5");
		assertEquals(isPrakingLotCreated, false);
	}

	@Test
	public void createParkingLotWithZero_5() throws Exception {
		boolean isParkingLotCreated = parkingLot.createParkingLot("0");
		assertEquals(isParkingLotCreated, false);
	}

}
