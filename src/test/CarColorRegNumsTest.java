package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import services.impl.ParkingLot;

public class CarColorRegNumsTest {

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
	public void testcaseBlue() throws Exception {

		String color = "blue";
		List<String> list = this.parkingLot.getRegNumsFromColor(color);
		List<String> result = new ArrayList<>(Arrays.asList("Reg1", "Reg3"));
		assertTrue(result.equals(list));
	}
	
	@Test
	public void testcaseBlack() throws Exception {

		String color = "black";
		List<String> list = this.parkingLot.getRegNumsFromColor(color);
		List<String> result = new ArrayList<>(Arrays.asList("Reg2"));
		assertTrue(result.equals(list));
	}
	
	@Test
	public void testcaseRed() throws Exception {

		String color = "red";
		List<String> list = this.parkingLot.getRegNumsFromColor(color);
		List<String> result = new ArrayList<>(Arrays.asList("Reg4"));
		assertTrue(result.equals(list));
	}
		
	@Test
	public void testcaseForWhite() throws Exception {

		String color = "white";
		List<String> list = this.parkingLot.getRegNumsFromColor(color);
		assertNull(list);
	}

}

