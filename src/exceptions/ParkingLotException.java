package exceptions;

public class ParkingLotException extends RuntimeException {

	private ErrorCode errorCode;

	public ParkingLotException(String message)
	{
		super(message);
	}

	public ParkingLotException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	public ParkingLotException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ParkingLotException(ErrorCode errorCode, Throwable throwable)
	{
		super(throwable);
		this.setErrorCode(errorCode);
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
