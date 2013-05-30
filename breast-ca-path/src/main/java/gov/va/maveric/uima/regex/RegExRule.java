package gov.va.maveric.uima.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import util.exceptions.EvaluationException;
import util.exceptions.ValidationException;

public class RegExRule implements Rule, java.io.Serializable{

	//================
	//= Data Members =
	//================

	private static final long serialVersionUID = -3645572689100424121L;

	private static final String TYPE = "regex";

	private final String description;
	private final Pattern definition;
	private final List<Action> actions;
	private Filter filter;

	//================
	//= Constructors =
	//================

	public RegExRule(String description, String definition, List<Action> actions, Filter filter) 
	throws ValidationException{
		
		if(description != null){
			this.description = description;
		}
		else{
			this.description = "";
		}

		if(definition == null){
			throw new ValidationException(
			"Regular expression rule definition is not specified.");
		}

		try{
			this.definition = Pattern.compile(definition);
		}
		catch(PatternSyntaxException e){
			throw new ValidationException(
					"Error compiling the rule: " + e.getMessage());
		}

		this.actions = new ArrayList<Action>();
		if(actions != null){
			for(Action a : actions){
				this.actions.add(a);
			}
		}
		
		this.filter = filter;
	}

	//================
	//= Data Members =
	//================

	@Override
	public List<ActionResult> evaluate(String context) throws EvaluationException{
		return evaluate(context, 0);
	}

	@Override
	public List<ActionResult> evaluate(String context, int offset) throws EvaluationException {

		List<ActionResult> output = new ArrayList<ActionResult>();
		
		if(context == null){
			return output;
		}

		for(Action action : actions){
			output.addAll( action.execute(context, definition, offset, filter) );
		}

		return output;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	@Override
	public String toString(){
		return definition.pattern() + " - '" + description + "'";
	}

}
