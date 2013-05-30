package gov.va.maveric.uima.breastca;

import gov.va.maveric.uima.regex.ActionResult;
import gov.va.maveric.uima.regex.FeatureDefinition;
import gov.va.maveric.uima.regex.Interval;
import gov.va.maveric.uima.regex.Rule;
import gov.va.maveric.uima.regex.RuleFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import util.exceptions.EvaluationException;
import util.exceptions.PersistenceException;
import util.exceptions.ValidationException;

public class BreastCancerAnnotator extends JCasAnnotator_ImplBase{

	//================
	//= Data Members =
	//================

	private String rulesURL;
	private List<Rule> rules;

	//================
	//= Constructors =
	//================
	
	public BreastCancerAnnotator(){
		//
	}
	
	//===========
	//= Methods =
	//===========

	public void initialize(UimaContext aContext) throws ResourceInitializationException{
		super.initialize(aContext);
		rulesURL = (String) aContext.getConfigParameterValue("rulesURL");
		try {
			rules = RuleFactory.readFile(rulesURL);
		}
		catch(PersistenceException e){
			throw new ResourceInitializationException(e);
		}
		catch(ValidationException e){
			throw new ResourceInitializationException(e);
		}
	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException{
		try{
			List<ActionResult> results = parse(jcas);
			//get the list of restricted intervals in the document
			List<Interval> restricted = getRestrictedIntervals(jcas,results);
			//add annotations
			addGradeAnnotations(jcas, results, restricted);
			addTumorStageAnnotations(jcas, results, restricted);
			addNodalStageAnnotations(jcas, results, restricted);
			addProgesteroneReceptorStatusAnnotations(jcas, results, restricted);
			addEstrogenReceptorStatusAnnotations(jcas, results, restricted);
			addHer2NeuValueAnnotations(jcas, results, restricted);
			addHer2NeuDetectionMethodAnnotations(jcas, results, restricted);
		}
		catch(EvaluationException e){
			throw new AnalysisEngineProcessException(e);
		}
	}
	
	private List<ActionResult> parse(JCas jcas) throws EvaluationException{
		final List<ActionResult> output = new ArrayList<ActionResult>();
		final String text = jcas.getDocumentText();
		if(rules != null){
			for(Rule rule : rules){
				output.addAll(rule.evaluate(text));
			}
		}
		return output;
	}
	
	private List<Interval> getRestrictedIntervals(JCas jcas, List<ActionResult> results){
		
		final List<Interval> output = new ArrayList<Interval>();
		
		for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();
			Interval offset = ar.getOffset();
			
			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					annName.trim().equalsIgnoreCase("suppressible-match")){
				output.add(offset);
			}
		}

		return output;
	}
	
	private void addGradeAnnotations(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
		
		outer: for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();


			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			String value = null;
			FeatureDefinition valueFD = featureLookup.get("value");
			if(valueFD != null){
				value = valueFD.getValue();
			}

			String ruleID = null;
			FeatureDefinition ruleFD = featureLookup.get("rule");
			if(ruleFD != null){
				ruleID = ruleFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					annName.trim().equalsIgnoreCase("grade")){
				
				Interval offset = ar.getOffset();
				int start = offset.getStart();
				int end = offset.getEnd();
				
				//verify that the interval doesn't intersect with any of the restricted fragments
				for(Interval rInt : restricted){
					int rStart = rInt.getStart();
					int rEnd = rInt.getEnd();
					if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
						//System.out.println("Grade Disqualification: " + offset + " overlaps with restricted " + rInt + "; " + featureLookup.get("string").getValue().replaceAll("\\s+", " "));
						continue outer;//current match overlaps with restricted fragment
					}
				}
				
				value = normalizeGradeValue(value);

				if(value != null && value.trim().length() > 0){
					Grade ann = new Grade(jcas, offset.getStart(), offset.getEnd());
					ann.setValue(value);
					ann.setCategory(category);
					ann.setRuleID(ruleID);
					anns.add(ann);
				}

			}
		}

		//resolve overlaps
		List<Annotation> nonOverlapping = getNonOverlapping(anns);
		for(Annotation ann : nonOverlapping){
			ann.addToIndexes();
		}
	}
	
	private String normalizeGradeValue(String value){
		if(value == null || value.trim().length() == 0){
			return "";
		}
		value = value.replaceAll("\\s+", " ").trim();
		Pattern p1 = Pattern.compile("(?i)^III|3|high\\s+nuclear|high|poorly$");
		Matcher m1 = p1.matcher(value);
		if(m1.find()){
			return "3";
		}
		
		Pattern p2 = Pattern.compile("(?i)^II|2|intermediate\\s+nuclear|intermediate|moderately$");
		Matcher m2 = p2.matcher(value);
		if(m2.find()){
			return "2";
		}
		
		Pattern p3 = Pattern.compile("(?i)^I|1|low\\s+nuclear|low|well$");
		Matcher m3 = p3.matcher(value);
		if(m3.find()){
			return "1";
		}
		
		Pattern p4 = Pattern.compile("(?i)unknown$");
		Matcher m4 = p4.matcher(value);
		if(m4.find()){
			return "x";
		}
		
		return "";
	}
	
	private void addTumorStageAnnotations(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
		
		outer: for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();


			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			String value = null;
			FeatureDefinition valueFD = featureLookup.get("value");
			if(valueFD != null){
				value = valueFD.getValue();
			}

			String ruleID = null;
			FeatureDefinition ruleFD = featureLookup.get("rule");
			if(ruleFD != null){
				ruleID = ruleFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					annName.trim().equalsIgnoreCase("tumor-stage")){
				
				Interval offset = ar.getOffset();
				int start = offset.getStart();
				int end = offset.getEnd();
				
				//verify that the interval doesn't intersect with any of the restricted fragments
				for(Interval rInt : restricted){
					int rStart = rInt.getStart();
					int rEnd = rInt.getEnd();
					if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
						//System.out.println("Tumor Stage Disqualification: " + offset + " overlaps with restricted " + rInt + "; " + featureLookup.get("string").getValue().replaceAll("\\s+", " "));
						continue outer;//current match overlaps with restricted fragment
					}
				}

				if(value != null){
					TumorStage ann = new TumorStage(jcas, offset.getStart(), offset.getEnd());
					ann.setValue(value);
					ann.setCategory(category);
					ann.setRuleID(ruleID);
					anns.add(ann);
				}
				
			}
		}

		//resolve overlaps
		List<Annotation> nonOverlapping = getNonOverlapping(anns);
		for(Annotation ann : nonOverlapping){
			ann.addToIndexes();
		}
	}

	private void addNodalStageAnnotations(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
		
		outer: for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();


			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			String value = null;
			FeatureDefinition valueFD = featureLookup.get("value");
			if(valueFD != null){
				value = valueFD.getValue();
			}

			String ruleID = null;
			FeatureDefinition ruleFD = featureLookup.get("rule");
			if(ruleFD != null){
				ruleID = ruleFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					annName.trim().equalsIgnoreCase("nodal-stage")){
				
				Interval offset = ar.getOffset();
				int start = offset.getStart();
				int end = offset.getEnd();
				
				//verify that the interval doesn't intersect with any of the restricted fragments
				for(Interval rInt : restricted){
					int rStart = rInt.getStart();
					int rEnd = rInt.getEnd();
					if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
						//System.out.println("Nodal Stage Disqualification: " + offset + " overlaps with restricted " + rInt + "; " + featureLookup.get("string").getValue().replaceAll("\\s+", " "));
						continue outer;//current match overlaps with restricted fragment
					}
				}

				if(value != null){
					NodalStage ann = new NodalStage(jcas, offset.getStart(), offset.getEnd());
					ann.setValue(value);
					ann.setCategory(category);
					ann.setRuleID(ruleID);
					anns.add(ann);
				}
				
			}
		}

		//resolve overlaps
		List<Annotation> nonOverlapping = getNonOverlapping(anns);
		for(Annotation ann : nonOverlapping){
			ann.addToIndexes();
		}
	}

	private void addProgesteroneReceptorStatusAnnotations(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
		
		outer: for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();


			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			String value = null;
			FeatureDefinition valueFD = featureLookup.get("value");
			if(valueFD != null){
				value = valueFD.getValue();
			}

			String ruleID = null;
			FeatureDefinition ruleFD = featureLookup.get("rule");
			if(ruleFD != null){
				ruleID = ruleFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					annName.trim().equalsIgnoreCase("progesterone-receptor-status")){
				
				Interval offset = ar.getOffset();
				int start = offset.getStart();
				int end = offset.getEnd();
				
				//verify that the interval doesn't intersect with any of the restricted fragments
				for(Interval rInt : restricted){
					int rStart = rInt.getStart();
					int rEnd = rInt.getEnd();
					if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
						//System.out.println("Progesterone Receptor Status Disqualification: " + offset + " overlaps with restricted " + rInt + "; " + featureLookup.get("string").getValue().replaceAll("\\s+", " "));
						continue outer;//current match overlaps with restricted fragment
					}
				}

				if(value != null){
					ProgesteroneReceptorStatus ann = new ProgesteroneReceptorStatus(jcas, offset.getStart(), offset.getEnd());
					ann.setValue(value);
					ann.setCategory(category);
					ann.setRuleID(ruleID);
					anns.add(ann);
				}
				
			}
		}

		//resolve overlaps
		List<Annotation> nonOverlapping = getNonOverlapping(anns);
		for(Annotation ann : nonOverlapping){
			ann.addToIndexes();
		}
	}

	private void addEstrogenReceptorStatusAnnotations(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
		
		outer: for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();


			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			String value = null;
			FeatureDefinition valueFD = featureLookup.get("value");
			if(valueFD != null){
				value = valueFD.getValue();
			}

			String ruleID = null;
			FeatureDefinition ruleFD = featureLookup.get("rule");
			if(ruleFD != null){
				ruleID = ruleFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					annName.trim().equalsIgnoreCase("estrogen-receptor-status")){
				
				Interval offset = ar.getOffset();
				int start = offset.getStart();
				int end = offset.getEnd();
				
				//verify that the interval doesn't intersect with any of the restricted fragments
				for(Interval rInt : restricted){
					int rStart = rInt.getStart();
					int rEnd = rInt.getEnd();
					if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
						//System.out.println("Estrogen Receptor Status Disqualification: " + offset + " overlaps with restricted " + rInt + "; " + featureLookup.get("string").getValue().replaceAll("\\s+", " "));
						continue outer;//current match overlaps with restricted fragment
					}
				}

				if(value != null){
					EstrogenReceptorStatus ann = new EstrogenReceptorStatus(jcas, offset.getStart(), offset.getEnd());
					ann.setValue(value);
					ann.setCategory(category);
					ann.setRuleID(ruleID);
					anns.add(ann);
				}
				
			}
		}

		//resolve overlaps
		List<Annotation> nonOverlapping = getNonOverlapping(anns);
		for(Annotation ann : nonOverlapping){
			ann.addToIndexes();
		}
	}
	
	private void addHer2NeuValueAnnotations(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
		
		outer: for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();


			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			String value = null;
			FeatureDefinition valueFD = featureLookup.get("value");
			if(valueFD != null){
				value = valueFD.getValue();
			}

			String ruleID = null;
			FeatureDefinition ruleFD = featureLookup.get("rule");
			if(ruleFD != null){
				ruleID = ruleFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					annName.trim().equalsIgnoreCase("her2-neu-value")){
				
				Interval offset = ar.getOffset();
				int start = offset.getStart();
				int end = offset.getEnd();
				
				//verify that the interval doesn't intersect with any of the restricted fragments
				for(Interval rInt : restricted){
					int rStart = rInt.getStart();
					int rEnd = rInt.getEnd();
					if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
						//System.out.println("HER2/NEU Value Disqualification: " + offset + " overlaps with restricted " + rInt + "; " + featureLookup.get("string").getValue().replaceAll("\\s+", " "));
						continue outer;//current match overlaps with restricted fragment
					}
				}

				if(value != null){
					Her2NeuValue ann = new Her2NeuValue(jcas, offset.getStart(), offset.getEnd());
					ann.setValue(value);
					ann.setCategory(category);
					ann.setRuleID(ruleID);
					anns.add(ann);
				}
				
			}
		}

		//resolve overlaps
		List<Annotation> nonOverlapping = getNonOverlapping(anns);
		for(Annotation ann : nonOverlapping){
			ann.addToIndexes();
		}
	}
	
	private void addHer2NeuDetectionMethodAnnotations(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
		
		outer: for(ActionResult ar : results){
			
			String annName = ar.getAnnotationName();


			Collection<FeatureDefinition> fds = ar.getFeatures();
			Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}

			String value = null;
			FeatureDefinition valueFD = featureLookup.get("value");
			if(valueFD != null){
				value = valueFD.getValue();
			}

			String ruleID = null;
			FeatureDefinition ruleFD = featureLookup.get("rule");
			if(ruleFD != null){
				ruleID = ruleFD.getValue();
			}

			if(category != null && category.equalsIgnoreCase("breast-ca") && 
					(annName.trim().equalsIgnoreCase("her2-neu-test-fish") || 
							annName.trim().equalsIgnoreCase("her2-neu-test-ihc"))
							){
				
				Interval offset = ar.getOffset();
				int start = offset.getStart();
				int end = offset.getEnd();
				
				//verify that the interval doesn't intersect with any of the restricted fragments
				for(Interval rInt : restricted){
					int rStart = rInt.getStart();
					int rEnd = rInt.getEnd();
					if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
						//System.out.println("HER2/NEU Detection Method Disqualification: " + offset + " overlaps with restricted " + rInt + "; " + featureLookup.get("string").getValue().replaceAll("\\s+", " "));
						continue outer;//current match overlaps with restricted fragment
					}
				}

				if(value != null){
					Her2NeuDetectionMethod ann = new Her2NeuDetectionMethod(jcas, offset.getStart(), offset.getEnd());
					ann.setValue(value);
					ann.setCategory(category);
					ann.setRuleID(ruleID);
					anns.add(ann);
				}
				
			}
		}

		//resolve overlaps
		List<Annotation> nonOverlapping = getNonOverlapping(anns);
		for(Annotation ann : nonOverlapping){
			ann.addToIndexes();
		}
	}
	
	private List<Annotation> getNonOverlapping(List<Annotation> anns){
		
		//Resolve potential overlapping offsets of the matched fragments
		Map<String, Annotation> nonOverlapping = new LinkedHashMap<String, Annotation>();
		
		for(Annotation ann : anns){

			final int candBegin = ann.getBegin();
			final int candEnd = ann.getEnd();
			//final int candLength = candEnd - candBegin;

			boolean disqualify = false;

			List<Annotation> toDelete = new ArrayList<Annotation>();

			for(Annotation existing : nonOverlapping.values()){
				
				final int existBegin = existing.getBegin();
				final int existEnd = existing.getEnd();
				//final int existLength = existEnd - existBegin;

				if(candBegin >= existBegin && candEnd <= existEnd){
					disqualify = true;
				}
				else if(existBegin < candBegin && existEnd > candBegin){
					//if(existLength < candLength){
					//	toDelete.add(existing);
					//}
					//else{
						disqualify = true; //disqualify the candidate
					//}
				}
				else if(existBegin > candBegin && existBegin < candEnd){
					//if(existLength < candLength){
						toDelete.add(existing);
					//}
					//else{
					//	disqualify = true; //disqualify the candidate
					//}
				}
			}

			if(!disqualify){
				for(Annotation f : toDelete){
					String key = "[" + f.getBegin() + "-" + f.getEnd() + "]";
					nonOverlapping.remove(key);
				}
				nonOverlapping.put("[" + ann.getBegin() + "-" + ann.getEnd() + "]", ann);
			}
			
		}
		
		return new ArrayList<Annotation>(nonOverlapping.values());
	}

}