package util.exceptions;

public class InitializationException extends SystemException{

	private static final long serialVersionUID = 7265718465689381402L;

	public InitializationException() {
		super();
	}
	
	public InitializationException(String message) {
		super(message);
	}
	
	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InitializationException(Throwable cause) {
		super(cause);
	}
	
}
