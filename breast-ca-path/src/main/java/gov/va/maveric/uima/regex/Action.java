package gov.va.maveric.uima.regex;

import java.util.List;
import java.util.regex.Pattern;

import util.exceptions.EvaluationException;

public interface Action {
	public List<ActionResult> execute(String context, Pattern pattern, int offset, Filter filter) throws EvaluationException;
	public List<ActionResult> execute(String context, Pattern pattern, int offset) throws EvaluationException;
	public List<ActionResult> execute(String context, Pattern pattern, Filter filter) throws EvaluationException;
	public List<ActionResult> execute(String context, Pattern pattern) throws EvaluationException;
	public String getType();
}
