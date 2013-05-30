package util.exceptions;

public class PersistenceException extends DataException{

	private static final long serialVersionUID = 2980475796456022627L;

	public PersistenceException() {
		super();
	}
	
	public PersistenceException(String message) {
		super(message);
	}
	
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PersistenceException(Throwable cause) {
		super(cause);
	}
	
}
