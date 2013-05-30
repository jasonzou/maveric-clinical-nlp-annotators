package gov.va.maveric.uima.regex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionResult implements java.io.Serializable{

	//================
	//= Data Members =
	//================

	private static final long serialVersionUID = 7576301219110905911L;
	
	private final String annotationName;
	private final Interval offset;
	private final Map<String, String> features;
	
	//================
	//= Constructors =
	//================
	
	public ActionResult(String annotationName, Interval span, Collection<FeatureDefinition> features){
		this.annotationName = annotationName;
		this.offset = new Interval(span.getStart(), span.getEnd());
		this.features = new ConcurrentHashMap<String, String>();
		if(features != null){
			for(FeatureDefinition def : features){
				this.features.put(def.getName(), def.getValue());
			}
		}
	}
	
	//===========
	//= Methods =
	//===========
	
	@Override
	public String toString(){
		return annotationName + ":\t" + offset;
	}
	
	public String toExtendedString(){
		StringBuilder output = new StringBuilder();
		output.append("--------------------------------------------").append("\r\n");
		output.append(annotationName + ":\t" + offset).append("\r\n");
		if(features != null){
			List<String> keys = new ArrayList<String>(features.keySet());
			Collections.sort(keys);
			for(String key : keys){
				output.append("\t").append(key).append("=").append(features.get(key)).append("\r\n");	
			}
		}
		output.append("--------------------------------------------");
		return output.toString();
	}
	
	public String getAnnotationName(){
		return annotationName;
	}
	
	public Interval getOffset(){
		return new Interval(offset.getStart(), offset.getEnd());
	}
	
	public Collection<FeatureDefinition> getFeatures(){
		List<FeatureDefinition> output = new ArrayList<FeatureDefinition>();
		for(String name : features.keySet()){
			FeatureDefinition def = new FeatureDefinition(name, features.get(name));
			output.add(def);
		}
		return output;
	}
	
}
