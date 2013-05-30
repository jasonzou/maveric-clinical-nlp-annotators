package util.exceptions;

public class UnsupportedOperationException extends SystemException{

	private static final long serialVersionUID = -3424418960992887391L;

	public UnsupportedOperationException() {
		super();
	}
	
	public UnsupportedOperationException(String message) {
		super(message);
	}
	
	public UnsupportedOperationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UnsupportedOperationException(Throwable cause) {
		super(cause);
	}
	
}
