package gov.va.maveric.uima.regex;

import java.util.List;

import util.exceptions.EvaluationException;

public interface Rule {
	public List<ActionResult> evaluate(String context) throws EvaluationException;
	public List<ActionResult> evaluate(String context, int offset) throws EvaluationException;
	public String getDescription();
	public String getType();
}
