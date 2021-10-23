package entity;

public class Car {

	private String color;
	private String regNo;

	public Car() {};

	public Car(String color, String regNo) {
		this.color = color;
		this.regNo = regNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	

	/*
	 * Customized hashcode and equals method
	 */
	@Override
	public int hashCode() {
		
		return ((regNo == null) ? 0 : regNo.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		
		if (regNo == null) {
			if (other.regNo != null)
				return false;
		} else if (!regNo.equalsIgnoreCase(other.regNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [color=" + color + ", regNo=" + regNo + "]";
	}

}
