package exceptions;

public enum ErrorCode {

	NO_PARKINGLOT_CREATED(1, "No Parking lot created yet! Please create a parking lot."),
	PARKINGLOT_ALREADY_CREATED(1, "A parking lot is already created. It cannot be created again!"),

	NON_POSITIVE_PARKINGLOT_SIZE(2, "Please create parking lot with size greater than 0"),
	INVALID_PARKINGLOT_SIZE(2, "An invalid number was passed for creating the parling lot! Please pass a valid number."),

	PARKINGLOT_FULL(3, "Sorry! Parking lot is already fully occupied."),

	INVALID_REG_NUM(4, "No such car parked with this registration number."),
	DUPLICATE_REGISTRATION_NUMBER(4, "A car with same registration number already exists. Illegal reg num alert!");

	private final String errorMsg;
	private final int errorCode;

	private ErrorCode(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
