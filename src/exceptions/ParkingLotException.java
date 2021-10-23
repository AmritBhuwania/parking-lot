package exceptions;

public class ParkingLotException extends Exception {

	private String errorCode;
	private Object[] errorStack;


	public ParkingLotException(String message)
	{
		super(message);
	}

	public ParkingLotException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	public ParkingLotException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ParkingLotException(String message, String errorCode, Object[] errorStack) {
		super(message);
		this.errorCode = errorCode;
		this.errorStack = errorStack;
	}

	public ParkingLotException(String message, String errorCode, Throwable throwable)
	{
		super(message, throwable);
		this.setErrorCode(errorCode);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object[] getErrorStack() {
		return errorStack;
	}

	public void setErrorStack(Object[] errorStack) {
		this.errorStack = errorStack;
	}


}
