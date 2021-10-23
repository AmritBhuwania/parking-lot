package entity;

public class ParkingLot {
	
	private Car car;
	
	public ParkingLot() {};
	
	public ParkingLot(Car car) {
		super();
		this.car = car;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "ParkingLot [car=" + car + "]";
	}
	
}
