package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.Car;
import services.impl.ParkingLot;


// For executing test cases sequentially
@FixMethodOrder(MethodSorters.DEFAULT)
public class ParkingLotParkingTest {

	ParkingLot parkingLot;

	// Create parking lot
	@Before
	public void createInstance() {
		parkingLot = ParkingLot.getInstance();

		// create a parking lot of size 2
		String maxParkingSize = "2";
		parkingLot.createParkingLot(maxParkingSize);
	}

	// add a car
	@Test
	public void testcase_1() throws Exception {

		Car car = new Car("Red", "Reg1");
		boolean isCarParkedSuccess = this.parkingLot.parkCar(car.getRegNo(), car.getColor());
		assertEquals(isCarParkedSuccess, true);
	}

	// add a car with same reg num. Car should not be parked
	@Test
	public void testcase_2() throws Exception {
		Car car = new Car("Black", "Reg1");
		boolean isCarParkedSuccess = this.parkingLot.parkCar(car.getRegNo(), car.getColor());
		assertEquals(isCarParkedSuccess, false);
	}

	// add a different car
	@Test
	public void testcase_3() throws Exception {
		Car car = new Car("Black", "Reg2");
		boolean isCarParkedSuccess = this.parkingLot.parkCar(car.getRegNo(), car.getColor());
		assertEquals(isCarParkedSuccess, true);
	}

	// add a different car, but max lot size increased.
	@Test
	public void testcase_4() throws Exception {
		Car car = new Car("Blue", "Reg3");
		boolean isCarParkedSuccess = this.parkingLot.parkCar(car.getRegNo(), car.getColor());
		assertEquals(isCarParkedSuccess, false);
	}

	// add a car with null as the reg number 
	@Test
	public void testcase_5() throws Exception {
		Car car = new Car(null, "Blue");
		boolean isCarParkedSuccess = this.parkingLot.parkCar(car.getRegNo(), car.getColor());
		assertEquals(isCarParkedSuccess, false);
	}

	// add a car with null as the color 
	@Test
	public void testcase_6() throws Exception {
		Car car = new Car("Blue", null);
		boolean isCarParkedSuccess = this.parkingLot.parkCar(car.getRegNo(), car.getColor());
		assertEquals(isCarParkedSuccess, false);
	}


}
