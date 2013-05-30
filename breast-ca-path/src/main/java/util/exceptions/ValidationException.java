package util.exceptions;

public class ValidationException extends DataException{

	private static final long serialVersionUID = -1982050905433189729L;

	public ValidationException() {
		super();
	}
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ValidationException(Throwable cause) {
		super(cause);
	}
	
}
