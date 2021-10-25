package test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import services.impl.ParkingLot;

public class CarRegTicketTest {

	ParkingLot parkingLot;

	// Create parking lot
	@Before
	public void createInstance() {
		parkingLot = ParkingLot.getInstance();

		// create a parking lot of size 3
		String maxParkingSize = "3";
		parkingLot.createParkingLot(maxParkingSize);

		parkingLot.parkCar("Reg1", "blue");
		parkingLot.parkCar("Reg2", "black");
		parkingLot.parkCar("Reg3", "blue");
	}


	@Test
	public void testcase_1() throws Exception {

		String regNum = "Reg1";
		int ticket = this.parkingLot.getTicketOfRegisteredCar(regNum);
		int result = 1;
		assertTrue(result == ticket);
	}

	@Test
	public void testcase_2() throws Exception {

		String regNum = "Reg2";
		int ticket = this.parkingLot.getTicketOfRegisteredCar(regNum);
		int result = 2;
		assertTrue(result == ticket);
	}

	@Test
	public void testcase_3() throws Exception {

		String regNum = "Reg3";
		int ticket = this.parkingLot.getTicketOfRegisteredCar(regNum);
		int result = 3;
		assertTrue(result == ticket);
	}

	@Test(expected = NullPointerException.class)
	public void testcase_4() throws Exception {

		String regNum = "Reg4";
		int ticket = this.parkingLot.getTicketOfRegisteredCar(regNum);
	}

}
