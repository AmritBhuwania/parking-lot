package test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import services.impl.ParkingLot;

//For executing test cases sequentially
@FixMethodOrder(MethodSorters.DEFAULT)
public class ParkingLotCarLeavingTest {


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

	// if a registration num of car is provided as input which is present in parking lot.
	@Test
	public void testcase_1() throws Exception {

		String regNum = "Reg1";
		boolean isCarLeftSuccess = this.parkingLot.leaveParkingLot(regNum);
		assertEquals(isCarLeftSuccess, true);
	}
	
	// if a registration num of car is provided as input which has already left 
	@Test
	public void testcase_2() throws Exception {

		String regNum = "Reg1";
		boolean isCarLeftSuccess = this.parkingLot.leaveParkingLot(regNum);
		assertEquals(isCarLeftSuccess, true);
	}
	
	// if a registration num of car is provided as input which is not present in parking lot.
	@Test
	public void testcase_3() throws Exception {

		String regNum = "Reg4";
		boolean isCarLeftSuccess = this.parkingLot.leaveParkingLot(regNum);
		assertEquals(isCarLeftSuccess, false);
	}

	// if a registration num of car is provided as input which is present in parking lot.
	@Test
	public void testcase_4() throws Exception {
		String regNum = "Reg2";
		boolean isCarLeftSuccess = this.parkingLot.leaveParkingLot(regNum);
		assertEquals(isCarLeftSuccess, true);
	}
	
	// if a registration num of car is provided as input which is present in parking lot.
	@Test
	public void testcase_5() throws Exception {
		String regNum = "Reg3";
		boolean isCarLeftSuccess = this.parkingLot.leaveParkingLot(regNum);
		assertEquals(isCarLeftSuccess, true);
	}
	
	// if a registration num of car is provided as input when parking lot is empty.
	@Test
	public void testcase_6() throws Exception {
		String regNum = "Reg5";
		boolean isCarLeftSuccess = this.parkingLot.leaveParkingLot(regNum);
		assertEquals(isCarLeftSuccess, false);
	}
	
	@Test
	public void testcase_7() throws Exception {

		Field isParkingLotCreated = parkingLot.getClass().getDeclaredField("isParkingLotCreated");
	    isParkingLotCreated.setAccessible(true);
	    
	    boolean isCreated = isParkingLotCreated.getBoolean(parkingLot);
		
		assertEquals(isCreated, true);
		
	}
	
	@Test
	public void testcase_8() throws Exception {

		parkingLot = null;
		parkingLot = new ParkingLot();
		parkingLot.createParkingLot("0");
		Field isParkingLotCreated = parkingLot.getClass().getDeclaredField("isParkingLotCreated");
	    isParkingLotCreated.setAccessible(true);
	    
	    boolean isCreated = isParkingLotCreated.getBoolean(parkingLot);
	    
	    System.out.println(isCreated);
		
		assertEquals(isCreated, false);
		
	}


}
