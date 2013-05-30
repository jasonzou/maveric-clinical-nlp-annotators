package util.exceptions;

public class FieldNotFoundException extends SystemException{

	private static final long serialVersionUID = 191813998369821163L;

	public FieldNotFoundException() {
		super();
	}
	
	public FieldNotFoundException(String message) {
		super(message);
	}
	
	public FieldNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FieldNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
