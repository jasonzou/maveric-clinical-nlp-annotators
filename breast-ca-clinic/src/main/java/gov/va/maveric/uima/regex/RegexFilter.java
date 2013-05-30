package gov.va.maveric.uima.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import util.exceptions.ValidationException;

public class RegexFilter implements Filter{

	//================
	//= Data Members =
	//================
	
	private Pattern pattern;
	private boolean accept; 
	
	//================
	//= Constructors =
	//================
	
	public RegexFilter(String regex, boolean accept) throws ValidationException{
		if(regex == null || regex.length() == 0){
			throw new ValidationException("Regular expresion is null or empty.");
		}
		try{
			this.pattern = Pattern.compile(regex);
		}
		catch(PatternSyntaxException e){
			throw new ValidationException(
					"Regular expression is invalid: " + e.getMessage(), e);
		}
		this.accept = accept;
	}
	
	//===========
	//= Methods =
	//===========
	
	@Override
	public synchronized boolean accept(String context){
		if(context == null){
			return false;
		}
		Matcher m = pattern.matcher(context);
		if(accept){
			return m.find();
		}
		else{
			return !m.find();
		}
	}
	
	@Override
	public String toString(){
		if(accept){
			return "Filter type: accept condition, expression: " + pattern.pattern();	
		}
		else{
			return "Filter type: reject condition, expression: " + pattern.pattern();
		}
	}

}
