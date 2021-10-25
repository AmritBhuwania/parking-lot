package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import services.impl.ParkingLot;

//For executing test cases sequentially
public class CarColorTicketTest {


	ParkingLot parkingLot;

	// Create parking lot
	@Before
	public void createInstance() {
		parkingLot = ParkingLot.getInstance();

		// create a parking lot of size 4
		String maxParkingSize = "4";
		parkingLot.createParkingLot(maxParkingSize);

		parkingLot.parkCar("Reg1", "blue");
		parkingLot.parkCar("Reg2", "black");
		parkingLot.parkCar("Reg3", "blue");
		parkingLot.parkCar("Reg4", "red");
	}

	
	@Test
	public void testcase_1() throws Exception {

		String color = "blue";
		List<Integer> list = this.parkingLot.getAllTicketsPerColor(color);
		List<Integer> result = new ArrayList<Integer>(Arrays.asList(1, 3));
		assertTrue(result.equals(list));
	}
	
	@Test
	public void testcaseForBlack() throws Exception {

		String color = "black";
		List<Integer> list = this.parkingLot.getAllTicketsPerColor(color);
		List<Integer> result = new ArrayList<Integer>(Arrays.asList(2));
		assertTrue(result.equals(list));
	}
	
	@Test
	public void testcaseForRed() throws Exception {

		String color = "red";
		List<Integer> list = this.parkingLot.getAllTicketsPerColor(color);
		List<Integer> result = new ArrayList<Integer>(Arrays.asList(4));
		assertTrue(result.equals(list));
	}
	
	@Test
	public void testcaseForWhite() throws Exception {

		String color = "white";
		List<Integer> list = this.parkingLot.getAllTicketsPerColor(color);
		assertNull(list);
	}

}
