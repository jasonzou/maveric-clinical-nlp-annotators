package util.exceptions;

public class SystemException extends Exception{

	private static final long serialVersionUID = 5274123022411899716L;

	public SystemException() {
		super();
	}
	
	public SystemException(String message) {
		super(message);
	}
	
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SystemException(Throwable cause) {
		super(cause);
	}
	
}
