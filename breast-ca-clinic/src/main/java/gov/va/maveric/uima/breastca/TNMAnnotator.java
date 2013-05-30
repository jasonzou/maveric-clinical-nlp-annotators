package gov.va.maveric.uima.breastca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;

public class TNMAnnotator extends JCasAnnotator_ImplBase{

	//================
	//= Data Members =
	//================

	//================
	//= Constructors =
	//================
	
	public TNMAnnotator(){
		//
	}
	
	//===========
	//= Methods =
	//===========

	public void initialize(UimaContext aContext) throws ResourceInitializationException{
		super.initialize(aContext);
	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException{
		final String category = "breast-ca";
		final String type = "tnm";
		List<Concept> anns = getConcepts(jcas,category,type);
		List<Concept> nonOverlapping = getNonOverlapping(anns);
		addResults(jcas, nonOverlapping);
	}
	
	private List<Concept> getConcepts(JCas jcas, String category, String type){
		final List<Concept> output = new ArrayList<Concept>();
		@SuppressWarnings("rawtypes")
		FSIndex index = jcas.getAnnotationIndex(Concept.type);
		@SuppressWarnings("unchecked")
		Iterator<Concept> conceptIter = index.iterator();
		while(conceptIter.hasNext()){
			Concept concept = (Concept) conceptIter.next();
			
			Map<String, String> metadata = new LinkedHashMap<String, String>();
			FSArray features = concept.getFeatures();
			for(int m = 0; m < features.size(); m++){
				ConceptFeature f = (ConceptFeature) features.get(m);
				final String name = f.getName();
				final String value = f.getValue();
				metadata.put(name.trim().toLowerCase(), value.trim().toLowerCase());
			}
			
			String cCategory = metadata.get("category");
			String cType = metadata.get("type");
			
			boolean categoryMatch = true;
			boolean typeMatch = true;
			
			if(category != null){
				if(cCategory != null){
					if(!category.equalsIgnoreCase(cCategory)){
						categoryMatch = false;
					}
				}
				else{
					categoryMatch = false;
				}
			}
			
			if(type != null){
				if(cType != null){
					if(!type.equalsIgnoreCase(cType)){
						typeMatch = false;
					}
				}
				else{
					typeMatch = false;
				}
			}
			
			if(categoryMatch && typeMatch){
				output.add(concept);
			}
			
		}
		return output;
	}
	
	private String normalizeValue(String val){
		if(val == null){
			return "";
		}
		else if(val.trim().equalsIgnoreCase("0") || val.trim().equalsIgnoreCase("O") || val.trim().equalsIgnoreCase("is")){
			return "0";
		}
		else if(val.trim().equalsIgnoreCase("1") || val.trim().equalsIgnoreCase("I")){
			return "1";
		}
		else if(val.trim().equalsIgnoreCase("2")){
			return "2";
		}
		else if(val.trim().equalsIgnoreCase("3")){
			return "3";
		}
		else if(val.trim().equalsIgnoreCase("4")){
			return "4";
		}
		else if(val.trim().equalsIgnoreCase("x")){
			return "x";
		}
		else if(val.trim().equalsIgnoreCase("0-1")){
			return "0-1";
		}
		else if(val.trim().equalsIgnoreCase("1-2")){
			return "1-2";
		}
		else if(val.trim().equalsIgnoreCase("2-3")){
			return "2-3";
		}
		else{
			return "";
		}
	}
	
	private String normalizeSubValue(String val){
		if(val == null){
			return "";
		}
		else if(val.trim().equalsIgnoreCase("a")){
			return "a";
		}
		else if(val.trim().equalsIgnoreCase("b")){
			return "b";
		}
		else if(val.trim().equalsIgnoreCase("c")){
			return "c";
		}
		else if(val.trim().equalsIgnoreCase("mic")){
			return "mic";
		}
		else if(val.trim().equalsIgnoreCase("mi")){
			return "mi";
		}
		else{
			return "";
		}
	}
	
	private void addResults(JCas jcas, List<Concept> anns){
		
		for(Concept concept : anns){
			
			int start = concept.getBegin();
			int end = concept.getEnd();
			
			Map<String, String> metadata = new LinkedHashMap<String, String>();
			FSArray features = concept.getFeatures();
			for(int m = 0; m < features.size(); m++){
				ConceptFeature f = (ConceptFeature) features.get(m);
				final String name = f.getName();
				final String value = f.getValue();
				metadata.put(name.trim().toLowerCase(), value.trim().toLowerCase());
			}
			
			String t = normalizeValue(metadata.get("t"));
			String tSub = normalizeSubValue(metadata.get("t-sub"));
			String n = normalizeValue(metadata.get("n"));
			String nSub = normalizeSubValue(metadata.get("n-sub"));
			String m = normalizeValue(metadata.get("m"));
			
			TNMDescriptor tnm = new TNMDescriptor(jcas, start, end);
			tnm.setM(m);
			tnm.setN(n);
			tnm.setNSub(nSub);
			tnm.setT(t);
			tnm.setTSub(tSub);
			tnm.addToIndexes();
		}

	}

	private List<Concept> getNonOverlapping(List<Concept> anns){
		
		//Resolve potential overlapping offsets of the matched fragments
		Map<String, Concept> nonOverlapping = new LinkedHashMap<String, Concept>();
		
		for(Concept ann : anns){

			final int candBegin = ann.getBegin();
			final int candEnd = ann.getEnd();
			final int candLength = candEnd - candBegin;

			boolean disqualify = false;

			List<Concept> toDelete = new ArrayList<Concept>();

			for(Concept existing : nonOverlapping.values()){
				
				final int existBegin = existing.getBegin();
				final int existEnd = existing.getEnd();
				final int existLength = existEnd - existBegin;

				if(candBegin >= existBegin && candEnd <= existEnd){
					disqualify = true;
				}
				else if(existBegin < candBegin && existEnd > candBegin){
					if(existLength < candLength){
						toDelete.add(existing);
					}
					else{
						disqualify = true; //disqualify the candidate
					}
				}
				else if(existBegin > candBegin && existBegin < candEnd){
					if(existLength < candLength){
						toDelete.add(existing);
					}
					else{
						disqualify = true; //disqualify the candidate
					}
				}
			}

			if(!disqualify){
				for(Concept f : toDelete){
					String key = "[" + f.getBegin() + "-" + f.getEnd() + "]";
					nonOverlapping.remove(key);
				}
				nonOverlapping.put("[" + ann.getBegin() + "-" + ann.getEnd() + "]", ann);
			}
			
		}
		
		return new ArrayList<Concept>(nonOverlapping.values());
	}
	
}