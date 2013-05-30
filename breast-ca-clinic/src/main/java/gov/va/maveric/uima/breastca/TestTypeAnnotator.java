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

public class TestTypeAnnotator extends JCasAnnotator_ImplBase{

	//================
	//= Data Members =
	//================

	//================
	//= Constructors =
	//================
	
	public TestTypeAnnotator(){
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
		String type = "test-type";
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

	private void addResults(JCas jcas, List<Concept> anns){
		
		for(Concept concept : anns){
			
			int start = concept.getBegin();
			int end = concept.getEnd();
			
			//System.out.println("---> " + jcas.getDocumentText().substring(start,  end).replaceAll("\\s+"," "));
			
			Map<String, String> metadata = new LinkedHashMap<String, String>();
			FSArray features = concept.getFeatures();
			for(int m = 0; m < features.size(); m++){
				ConceptFeature f = (ConceptFeature) features.get(m);
				final String name = f.getName();
				final String value = f.getValue();
				metadata.put(name.trim().toLowerCase(), value.trim().toLowerCase());
			}
			
			//extract test type
			String test = normalize(metadata.get("test"));
			if(!test.equals("")){
				TestType tt = new TestType(jcas, start, end);
				tt.setTestType(test);
				tt.addToIndexes();	
			}
			
		}

	}
	
	private String normalize(String tt){
		if(tt == null){
			return "";
		}
		tt = tt.replaceAll("\\s+", "").trim().toLowerCase();
		if(tt.equals("ihc")){
			return "IHC";
		}
		if(tt.equals("fish")){
			return "FISH";
		}
		return "";
	}

	private List<Concept> getNonOverlapping(List<Concept> anns){
		//we probably should not remove overlapping annotations because we may lose
		//some information if we do
		return anns;
	}
	
//	private List<Concept> getNonOverlapping(List<Concept> anns){
//		
//		//Resolve potential overlapping offsets of the matched fragments
//		Map<String, Concept> nonOverlapping = new LinkedHashMap<String, Concept>();
//		
//		for(Concept ann : anns){
//
//			final int candBegin = ann.getBegin();
//			final int candEnd = ann.getEnd();
//			final int candLength = candEnd - candBegin;
//
//			boolean disqualify = false;
//
//			List<Concept> toDelete = new ArrayList<Concept>();
//
//			for(Concept existing : nonOverlapping.values()){
//				
//				final int existBegin = existing.getBegin();
//				final int existEnd = existing.getEnd();
//				final int existLength = existEnd - existBegin;
//
//				if(candBegin >= existBegin && candEnd <= existEnd){
//					disqualify = true;
//				}
//				else if(existBegin < candBegin && existEnd > candBegin){
//					if(existLength < candLength){
//						toDelete.add(existing);
//					}
//					else{
//						disqualify = true; //disqualify the candidate
//					}
//				}
//				else if(existBegin > candBegin && existBegin < candEnd){
//					if(existLength < candLength){
//						toDelete.add(existing);
//					}
//					else{
//						disqualify = true; //disqualify the candidate
//					}
//				}
//			}
//
//			if(!disqualify){
//				for(Concept f : toDelete){
//					String key = "[" + f.getBegin() + "-" + f.getEnd() + "]";
//					nonOverlapping.remove(key);
//				}
//				nonOverlapping.put("[" + ann.getBegin() + "-" + ann.getEnd() + "]", ann);
//			}
//			
//		}
//		
//		return new ArrayList<Concept>(nonOverlapping.values());
//	}
	
}