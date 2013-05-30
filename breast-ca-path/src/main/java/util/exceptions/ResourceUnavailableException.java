package util.exceptions;

public class ResourceUnavailableException extends InitializationException{

	private static final long serialVersionUID = -362539473952137671L;

	public ResourceUnavailableException() {
		super();
	}
	
	public ResourceUnavailableException(String message) {
		super(message);
	}
	
	public ResourceUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ResourceUnavailableException(Throwable cause) {
		super(cause);
	}
	
}
