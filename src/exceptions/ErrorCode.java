package exceptions;

public enum ErrorCode {
	
	NO_PARKINGLOT_CREATED("No Parking lot created yet! Please create a parking lot."),
	PARKINGLOT_ALREADY_CREATED("A parking lot is already created. It cannot be created again!"),
	INVALID_PARKINGLOT_SIZE("An invalid number was passed for creating the parling lot! Please pass a valid number."),
	PARKINGLOT_FULL("Sorry! Parking lot is already fully occupied."),
	INVALID_REG_NUM("No such car parked with this registration number.");
	
	private final String errorMsg;

	private ErrorCode(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public String getErrorMsg() {
		return errorMsg;
	}
	
}
