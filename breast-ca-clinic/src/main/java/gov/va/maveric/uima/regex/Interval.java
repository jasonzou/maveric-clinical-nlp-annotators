package gov.va.maveric.uima.regex;

public class Interval implements Comparable<Interval>{

	//================
	//= Data Members =
	//================
	
	private static final String START_BRACKET = "[";
	private static final String END_BRACKET = "]";
	private static final String COLON = ":";

	private Integer start;
	private Integer end;

	//================
	//= Constructors =
	//================

	public Interval(){
		this(null, null);
	}
	
	public Interval(Integer start, Integer end){
		setOffset(start, end);
	}

	//===========
	//= Methods =
	//===========
	
	public synchronized void setOffset(Integer start, Integer end){
		if(start != null && end != null){
			if(end.intValue() < start.intValue()){
				this.start = new Integer(end.intValue());
				this.end = new Integer(start.intValue());
			}
			else{
				this.start = new Integer(start.intValue());
				this.end = new Integer(end.intValue());
			}
		}
		else{
			if(start == null){
				this.start = null;
			}
			else{
				this.start = new Integer(start.intValue());
			}
			if(end == null){
				this.end = null;
			}
			else{
				this.end = new Integer(end.intValue());
			}
		}
	}

	public synchronized Integer getStart(){
		return this.start;
	}

	public synchronized Integer getEnd(){
		return this.end;
	}
	
	public synchronized int getLength(){
		if(start == null || end == null){
			return 0;
		}
		else{
			return end - start;
		}
	}

	@Override
	public int compareTo(Interval aThat){
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		if(this == aThat){
			return EQUAL;
		}
		if(this.start != null && aThat.start != null){
			if(this.start < aThat.start){
				return BEFORE;
			}
			if(this.start > aThat.start){
				return AFTER;
			}
		}
		if(this.end != null && aThat.end != null){
			if(this.end < aThat.end){
				return BEFORE;
			}
			if(this.end > aThat.end){
				return AFTER;
			}
		}
		return EQUAL;
	}

	@Override
	public String toString(){
		final StringBuilder output = new StringBuilder();
		output.append(START_BRACKET);
		output.append(start);
		output.append(COLON);
		output.append(end);
		output.append(END_BRACKET);
		return output.toString();
	}

}
