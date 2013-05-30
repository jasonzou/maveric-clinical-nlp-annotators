package util.exceptions;

public class EvaluationException extends SystemException{

	private static final long serialVersionUID = -5582377771669669678L;

	public EvaluationException() {
		super();
	}
	
	public EvaluationException(String message) {
		super(message);
	}
	
	public EvaluationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EvaluationException(Throwable cause) {
		super(cause);
	}
	
}
