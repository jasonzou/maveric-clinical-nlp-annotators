package util.exceptions;

public class DataException extends SystemException{
	
	private static final long serialVersionUID = 4458672832734694107L;

	public DataException() {
		super();
	}
	
	public DataException(String message) {
		super(message);
	}
	
	public DataException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataException(Throwable cause) {
		super(cause);
	}
	
}
