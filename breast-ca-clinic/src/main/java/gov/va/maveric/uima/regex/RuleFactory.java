package gov.va.maveric.uima.regex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import util.exceptions.PersistenceException;
import util.exceptions.ValidationException;

public class RuleFactory {

	//================
	//= Data Members =
	//================

	private static final String THIS_CLASS_NAME = RuleFactory.class.getName();

	//================
	//= Constructors =
	//================

	//===========
	//= Methods =
	//===========


	public static synchronized List<Rule> read(String xml) 
	throws PersistenceException, ValidationException{

		if(xml == null){
			throw new PersistenceException(
			"The XML string representing the rules is null. ");
		}

		// convert XML string into list of rules
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			Reader reader = new StringReader(xml);
			doc = builder.build(reader);
			reader.close();
		}
		catch (JDOMException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
		catch (IOException e) {
			throw new PersistenceException(e.getMessage(), e);
		}

		return readXMLRules(doc);
	}

	public static synchronized List<Rule> readFile(String location) 
	throws PersistenceException, ValidationException{

		if(location == null){
			throw new PersistenceException(
					"The location of XML rules file is invalid: '" + 
					location + "'");
		}

		// convert rules into the JDOM document
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			InputStream is = new FileInputStream(location.trim());
			doc = builder.build(is);
			is.close();
		}
		catch (FileNotFoundException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
		catch (JDOMException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
		catch (IOException e) {
			throw new PersistenceException(e.getMessage(), e);
		}

		return readXMLRules(doc);
	}
	
	public static synchronized List<Rule> read(URL url) 
	throws PersistenceException, ValidationException{

		if(url == null){
			throw new PersistenceException(
					"The URL of XML rules file is invalid: '" + 
					url + "'");
		}

		// convert rules into the JDOM document
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			InputStream is = new FileInputStream(url.getFile());
			doc = builder.build(is);
			is.close();
		}
		catch (FileNotFoundException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
		catch (JDOMException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
		catch (IOException e) {
			throw new PersistenceException(e.getMessage(), e);
		}

		return readXMLRules(doc);
	}

	public static synchronized List<Rule> readXMLRules(Document doc) 
	throws PersistenceException, ValidationException{

		// extract rules from the JDOM document
		if(doc == null){
			throw new PersistenceException("Rules XML DOM is null.");
		}

		Element root = doc.getRootElement();

		if(root == null){
			throw new PersistenceException("Cannot find rules XML JDOM root.");
		}

		// Create a list to hold the rules
		List<Rule> rules = new ArrayList<Rule>(); 		

		// Get the list of nodes containing rules. 
		List<Element> ruleNodes = root.getChildren("rule");

		if(ruleNodes == null || ruleNodes.size() == 0){
			Logger.getLogger(THIS_CLASS_NAME).warning(
			"No rules were discovered in the XML document.");
			return rules;
		}

		for(Element ruleNode : ruleNodes){
			Rule rule = getRule(ruleNode);
			rules.add(rule);
		}

		return rules;
	}

	
	private static Rule getRule(Element ruleNode) 
	throws PersistenceException, ValidationException{

		Rule rule = null;

		if(ruleNode == null){
			throw new PersistenceException(
					"Invalid XML rule definition: rule node is null.");
		}

		Element desc = ruleNode.getChild("description");
		Element def = ruleNode.getChild("definition");

		if(def == null){
			throw new ValidationException(
			"Required element 'definition' is missing.");
		}

		List<Action> _actions = new ArrayList<Action>();
		Element actions = ruleNode.getChild("actions");
		if(actions != null){
			List<Element> actionList = actions.getChildren("action");
			for(Element action : actionList){
				Element annotation = action.getChild("annotation");
				Action _action = getAction(annotation);
				_actions.add(_action);
			}
		}

		String _description = null;
		if(desc != null){
			_description = desc.getText().trim();
		}

		Element _regex = def.getChild("regex");
		Element _filters = def.getChild("filters");
		
		if(_regex == null){
			throw new ValidationException("Required element 'regex' is missing.");
		}
		
		// all whitespace should be removed from the pattern
		String _definition = _regex.getText().trim().replaceAll("\\s+", "");
		
		Filter filter = null;
		if(_filters != null){ //optional
			List<Element> filterList = _filters.getChildren("filter");
			if(filterList != null && !filterList.isEmpty()){
				//XXX Currently, only a single filter is accepted.
				Element filterEl = filterList.get(0);
				Attribute typeAttr =  filterEl.getAttribute("type");
				if(typeAttr == null){
					throw new ValidationException(
							"Element 'filter' is missing a required " +
							"'type' attribute.");
				}
				final String type = typeAttr.getValue().trim();
				if(!type.equalsIgnoreCase("accept") && !type.equalsIgnoreCase("reject")){
					throw new ValidationException(
							"Element 'filter' has unrecognized value " +
							"of the 'type' attribute: '" + type + 
							"'. The only valid values are 'reject' " +
							"or 'accept'.");
				}
				
				// all whitespace should be removed from the pattern
				String filterExpression = filterEl.getText().replaceAll("\\s+", "").trim();
				if(type.equalsIgnoreCase("accept")){
					filter = new RegexFilter(filterExpression, true);	
				}
				else if(type.equalsIgnoreCase("reject")){
					filter = new RegexFilter(filterExpression, false);
				}
			}
		}

		rule = new RegExRule(_description, _definition, _actions, filter);

		return rule;
	}

	private static Action getAction(Element annotation) 
	throws ValidationException{

		Action action = null;

		if(annotation == null){
			throw new ValidationException(
					"Invalid XML rule definition: annotation node is null.");
		}

		Element name = annotation.getChild("name");
		Element span = annotation.getChild("span");
		Element features = annotation.getChild("features");

		if(name == null){
			throw new ValidationException(
			"Required element 'name' is missing.");
		}
		if(span == null){
			throw new ValidationException(
			"Required element 'span' is missing.");
		}

		Element start = span.getChild("start");
		Element end = span.getChild("end");

		String startExpression = null;
		String endExpression = null;

		if(start != null){
			startExpression = start.getText().trim();
		}

		if(end != null){
			endExpression = end.getText().trim();
		}

		List<FeatureDefinition> definitions = new ArrayList<FeatureDefinition>();
		if(features != null){
			List<Element> featureList = features.getChildren("feature");
			for(Element feature : featureList){
				Element fname = feature.getChild("name");
				Element fvalue = feature.getChild("value");
				if(fname != null && fvalue != null){
					String _fname = fname.getText().trim();
					String _fvalue = fvalue.getText();
					FeatureDefinition fd = new FeatureDefinition(_fname, _fvalue);
					definitions.add(fd);
				}
			}
		}

		String annotationName = null;
		if(name != null){
			annotationName = name.getText().trim();
		}

		action = new AddAction(annotationName, startExpression, endExpression, definitions);


		return action;
	}

}
