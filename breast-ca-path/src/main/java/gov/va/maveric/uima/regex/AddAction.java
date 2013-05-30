package gov.va.maveric.uima.regex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.exceptions.EvaluationException;

public class AddAction implements Action, java.io.Serializable{

	//================
	//= Data Members =
	//================

	private static final long serialVersionUID = -102275505398942467L;

	private static final String TYPE = "Annotation addition action";

	private final String annotationName;
	private final String startExpression;
	private final String endExpression;
	private final List<FeatureDefinition> definitions;

	//================
	//= Constructors =
	//================

	public AddAction(
			String annotationName, 
			String startExpression, String endExpression, 
			Collection<FeatureDefinition> definitions){

		if(annotationName != null && annotationName.trim().length() != 0){
			this.annotationName = annotationName;	
		}
		else{
			this.annotationName = "";
		}

		if(startExpression != null){
			this.startExpression = startExpression;
		}
		else{
			this.startExpression = "";	
		}

		if(endExpression != null){
			this.endExpression = endExpression;
		}
		else{
			this.endExpression = "";
		}

		this.definitions = new ArrayList<FeatureDefinition>();
		if(definitions != null){
			this.definitions.addAll(definitions);
		}
	}

	//================
	//= Data Members =
	//================

	@Override
	public List<ActionResult> execute(final String context, final Pattern pattern) 
	throws EvaluationException {
		return execute(context, pattern, 0, null);
	}
	
	@Override
	public List<ActionResult> execute(final String context, final Pattern pattern, Filter filter) 
	throws EvaluationException {
		return execute(context, pattern, 0, filter);
	}

	@Override
	public List<ActionResult> execute(final String context, final Pattern pattern, int offset)
	throws EvaluationException{
		return execute(context, pattern, offset,  null);
	}

	@Override
	public List<ActionResult> execute(final String context, final Pattern pattern, 
			int offset, Filter filter) 
	throws EvaluationException {

		List<ActionResult> output = new ArrayList<ActionResult>();
		
		if(context == null || pattern == null){
			return output;
		}
	
		Matcher matcher = pattern.matcher(context);
		while(matcher.find()){
			//Get the matched context fragment
			final String match = matcher.group();
			// if filter is specified, apply filter to the match and conditionally
			// add a result
			if(filter != null){
				if(filter.accept(match)){
					Collection<FeatureDefinition> features = buildFeatures(matcher, offset);
					Interval span = buildSpan(matcher, offset);
					ActionResult result = new ActionResult(annotationName, span, features);
					output.add(result);
				}
			}
			else{
				Collection<FeatureDefinition> features = buildFeatures(matcher, offset);
				Interval span = buildSpan(matcher, offset);
				ActionResult result = new ActionResult(annotationName, span, features);
				output.add(result);
			}
		}

		return output;
	}

	private Collection<FeatureDefinition> buildFeatures(Matcher matcher, int offset)
	throws EvaluationException{

		Map<String, String> features = new ConcurrentHashMap<String, String>();
		for(FeatureDefinition fd : definitions){
			String name = fd.getName();
			String value = evaluateMatchExpression(matcher, fd.getValue());
			if(value != null){
				features.put(name, value);
			}
		}

		List<FeatureDefinition> output = new ArrayList<FeatureDefinition>(features.keySet().size());
		for(String key : features.keySet()){
			output.add(new FeatureDefinition(key, features.get(key)));
		}
		return output;
	}

	private Interval buildSpan(Matcher matcher, int offset) 
	throws EvaluationException{
		int start = evaluateOffsetExpression(matcher, startExpression, offset);
		int end = evaluateOffsetExpression(matcher, endExpression, offset);
		Interval span = new Interval(start, end);
		return span;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	// evaluates the following expressions: @start(n), @end(n)
	private int evaluateOffsetExpression(
			Matcher matcher, String expression, int offset) 
	throws EvaluationException{
		int start = 0;
		Pattern p = Pattern.compile("(?i)(?m)@(start|end)\\(\\s*(\\d+)\\s*\\)");
		Matcher m = p.matcher(expression);
		if(m.find()){
			String operator = m.group(1);
			int groupNum = Integer.parseInt(m.group(2));
			if(operator.trim().equalsIgnoreCase("start")){
				start = matcher.start(groupNum) + offset;
			}
			else if(operator.trim().equalsIgnoreCase("end")){
				start = matcher.end(groupNum) + offset;
			}
		}
		return start;
	}

	// evaluates the following expressions: @group(n)
	private String evaluateMatchExpression(
			Matcher matcher, String expression) 
	throws EvaluationException{
		String match = expression;
		Pattern p = Pattern.compile("(?i)(?m)@(group)\\(\\s*(\\d+)\\s*\\)");
		Matcher m = p.matcher(expression);
		if(m.find()){
			String operator = m.group(1);
			int groupNum = Integer.parseInt(m.group(2));
			if(operator.trim().equalsIgnoreCase("group")){
				match = matcher.group(groupNum);
			}
		}
		return match;
	}
}
