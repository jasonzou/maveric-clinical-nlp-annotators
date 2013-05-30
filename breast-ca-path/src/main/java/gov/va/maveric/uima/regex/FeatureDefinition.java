package gov.va.maveric.uima.regex;

public class FeatureDefinition implements java.io.Serializable{

	//================
	//= Data Members =
	//================
	
	private static final long serialVersionUID = -5758956363724217296L;
	private final String name;
	private final String value;
	
	//================
	//= Constructors =
	//================
	
	public FeatureDefinition(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	//===========
	//= Methods =
	//===========
	
	public String getName(){
		return name;
	}
	
	public String getValue(){
		return value;
	}
	
	@Override
	public String toString(){
		return "'" + name + "' = '" + value + "'";
	}
	
}
