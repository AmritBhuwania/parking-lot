package test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import services.impl.ParkingLot;

// Testing when parking lot size is zero
public class ParkingLotCarLeavingTest2 {

	ParkingLot parkingLot;

	@Before
	void createInstance() {
		parkingLot = new ParkingLot();
		parkingLot.createParkingLot("0");
	}

	@Test
	public void checkCreatedParkingLotVar() throws Exception {

		parkingLot.leaveParkingLot("Reg1");

		Field isParkingLotCreated = parkingLot.getClass().getDeclaredField("isParkingLotCreated");
		isParkingLotCreated.setAccessible(true);

		boolean isCreated = isParkingLotCreated.getBoolean(parkingLot);

		System.out.println(isCreated);

		assertEquals(isCreated, false);

	}

	@Test
	public void checkCapacityParkingLotVar() throws Exception {

		parkingLot.leaveParkingLot("Reg1");

		Field parkingLotCapacity = parkingLot.getClass().getDeclaredField("parkingLotCapacity");
		parkingLotCapacity.setAccessible(true);

		int capacity = parkingLotCapacity.getInt(parkingLot);

		System.out.println(capacity);

		assertEquals(capacity, 0);

	}
}
