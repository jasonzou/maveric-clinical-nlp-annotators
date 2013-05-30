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

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import util.exceptions.EvaluationException;
import util.exceptions.PersistenceException;
import util.exceptions.ValidationException;

public class RegexAnnotator extends JCasAnnotator_ImplBase{

	//================
	//= Data Members =
	//================

	private String rulesURL;
	private List<Rule> rules;

	//================
	//= Constructors =
	//================
	
	public RegexAnnotator(){
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
			addConcepts(jcas, results, restricted);
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
	
	private void addConcepts(
			JCas jcas, List<ActionResult> results, List<Interval> restricted){
		
		List<Annotation> anns = new ArrayList<Annotation>();
			
		outer: for(ActionResult result : results){
			
			//create fast feature lookup by name
			final Collection<FeatureDefinition> fds = result.getFeatures();
			final Map<String, FeatureDefinition> featureLookup = new LinkedHashMap<String, FeatureDefinition>();
			for(FeatureDefinition fd : fds){
				featureLookup.put(fd.getName().trim().toLowerCase(), fd);
			}
			
			String category = null;
			FeatureDefinition categoryFD = featureLookup.get("category");
			if(categoryFD != null){
				category = categoryFD.getValue();
			}
			
			if(category == null || !category.equalsIgnoreCase("breast-ca")){
				continue outer;
			}
				
			Interval offset = result.getOffset();
			int start = offset.getStart();
			int end = offset.getEnd();
			
			//verify that the interval doesn't intersect with any of the restricted fragments
			for(Interval rInt : restricted){
				int rStart = rInt.getStart();
				int rEnd = rInt.getEnd();
				if( (start >= rStart && start < rEnd) || (end > rStart && end <= rEnd) ){
					continue outer;//current match overlaps with restricted fragment
				}
			}
		
			
			List<FeatureDefinition> features = new ArrayList<FeatureDefinition>(result.getFeatures()); 
			
			Concept concept = new Concept(jcas, offset.getStart(), offset.getEnd());
			
			FSArray fsArray = new FSArray(jcas, features.size());
			
			for(int i = 0; i < features.size(); i++){
				FeatureDefinition feature = features.get(i);
				final String fname = feature.getName();
				final String fvalue = feature.getValue();
				ConceptFeature cf = new ConceptFeature(jcas); //add offsets?
				cf.setName(fname);
				cf.setValue(fvalue);
				//cf.addToIndexes();
				fsArray.set(i, cf);
			}
			
			concept.setFeatures(fsArray);
			concept.addToIndexes();
		}

		for(Annotation ann : anns){
			ann.addToIndexes();
		}
	}

}
