/**
 * StringLefMatcher.java
 * A generic superclass for string matchers
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * WARNING: REFACTORING PENDING!
 */
package funcModules;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.OWLOntology;
import types.SimpleAlignment;
import types.WrongType;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.DoubleFunctionProcess;

import Utils.TimeCounter;


import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.larq.IndexBuilderString;
import com.hp.hpl.jena.query.larq.IndexLARQ;
import com.hp.hpl.jena.query.larq.LARQ;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import fr.inrialpes.exmo.align.impl.method.StringDistances;


// TODO don't we need to specify a default n for n-gram distance ?


public  class SpeedyStringMatcher extends AbstractProcessImpl implements DoubleFunctionProcess {
	
	private double one=1.0;
		
	protected ArrayList leftPropertyList;
	protected ArrayList rightPropertyList;
	protected ArrayList referenceOntologyListNames;
	protected String referencePropertyString=null;
	
	protected Property[] leftPropertyArray=null;
	protected Property[] rightPropertyArray=null;
	protected boolean caseSensitive=false;
	protected boolean ignoreSameURI=true; 	
	protected boolean useLucene=false;
	protected boolean mergeProperties=true;
	protected boolean hashReference=false;
	protected boolean considerClasses=true;
	protected boolean considerIndividuals=false;
	//int wordsOverlapSensitivity=70;
	
	
	//String comparisons
	protected boolean exactMatch=true;
	protected boolean leftInclude=false;
	protected boolean wordsOverlap=false;
	protected boolean hammingDistance=false;
	protected boolean jaroMeasure=false;
	protected boolean jaroWinklerMeasure=false;
	protected boolean levenshteinDistance=false;
	protected boolean needlemanWunch2Distance=false;
	protected boolean needlemanWunchDistance=false;
	protected boolean ngramDistance=false;
	protected boolean smoaDistance=false;
	protected boolean subStringDistance=false;
	
	private void clearMethods() {
		exactMatch=false;
		leftInclude=false;
		wordsOverlap=false;
		hammingDistance=false;
		jaroMeasure=false;
		jaroWinklerMeasure=false;
		levenshteinDistance=false;
		needlemanWunch2Distance=false;
		needlemanWunchDistance=false;
		ngramDistance=false;
		smoaDistance=false;
		subStringDistance=false;
	}
	
	//String/Ontology based support
	protected boolean useReference=false;
	
	
	
	
	
	OWLOntology leftOntology;
	OWLOntology rightOntology;
	OWLOntology[] referenceOntologies=null;
	
	protected String genericStringRelationString="http://www.bootstrep.eu/enrichment#lexicalRelation";
	protected Property genericStringRelation;
	
	
	protected boolean filterString=true;
	protected boolean dropLang=true; 	
	

	
	protected OntClass[] leftClasses=null;
	protected OntClass[] rightClasses=null;
	protected Individual[] leftIndividuals=null;
	protected Individual[] rightIndividuals=null;
	protected String[][] leftClassesMergedString=null;
	protected String[][] rightClassesMergedString=null;
	protected String[][][] leftClassesPropertiesString=null;
	protected String[][][] rightClassesPropertiesString=null;
	protected String[][] leftIndividualsMergedString=null;
	protected String[][] rightIndividualsMergedString=null;
	protected String[][][] leftIndividualsPropertiesString=null;
	protected String[][][] rightIndividualsPropertiesString=null;
	SimpleAlignment result;
	
	
	boolean hasHammingDistance=false;
	boolean hasJaroMeasure=false;
	boolean hasJaroWinklerMeasure=false;
	boolean hasLevenshteinDistance=false;
	boolean hasNeedlemanWunch2Distance=false;
	boolean hasNeedlemanWunchDistance=false;
	boolean hasNgramDistance=false;
	boolean hasSmoaDistance=false;
	boolean hasSubStringDistance=false;
	
	int minSimilarity=70;
	int maxDistance=20;
	int needlemanWunchParam=1;
	
	
	public SpeedyStringMatcher() {
		super();
		leftPropertyList=new ArrayList();
		rightPropertyList=new ArrayList();
		referenceOntologyListNames=new ArrayList();
		genericStringRelation=ResourceFactory.createProperty(genericStringRelationString);
		name= "speedystringmatcher";
		type=FrameworkTypes.MATCHER;

		
		try {
			StringDistances.hammingDistance("AAA", "BBB");
			hasHammingDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Hamming distance is unavailable</message>");
		}	   
		try {
			StringDistances.jaroMeasure("AAA", "BBB");
			hasJaroMeasure=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Jaro Measure is unavailable</message>");
		}	
		try {	           
			StringDistances.jaroWinklerMeasure("AAA", "BBB");
			hasJaroWinklerMeasure=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Jaro Wrinkler distance is unavailable</message>");
		}	
		try {           
			StringDistances.levenshteinDistance("AAA", "BBB");
			hasLevenshteinDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Levenshtein distance is unavailable</message>");
		}	
		try {          
			StringDistances.needlemanWunch2Distance("AAA", "BBB");
			hasNeedlemanWunch2Distance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>NeedlemanWunch2D distance is unavailable</message>");
		}	
		try {         
			StringDistances.needlemanWunchDistance("AAA", "BBB", 1);
			hasNeedlemanWunchDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>NeedlemanWunch distance is unavailable</message>");
		}	
		try {        
			StringDistances.ngramDistance("AAA", "BBB");
			hasNgramDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Ngram distance is unavailable</message>");
		}	
		try {       
			StringDistances.smoaDistance("AAA", "BBB");
			hasSmoaDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>SmoaDistance distance is unavailable</message>");
		}	
		try {      
			StringDistances.subStringDistance("AAA", "BBB") ;
			hasSmoaDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Hamming distance is unavailable</message>");
		}
		
		description=new String();
		description+="<description><name>StringMatcher</name>" +
				"<descriptionText>(a genereic string matching tool)<br>" +
				"A set of possible string distance measures are provided.<br>" +
				"(Note that parameters takes int value from 0 to 100 corresponding to values from 0 to 1)<br>" +
				"Note also that normalization and other operation should be computed in a previous step</descriptionText>" +
				"<parameters>" +
				"<table>"+
				"<tr><td>Name</td><td>Value</td><td>Default</td><td>Additional param. (default)</td></tr>"+
				"<tr><td>addLeftProperty</td><td>property URI</td><td>rdfs:label</td><td></td></tr>"+
				"<tr><td>addRightProperty</td><td>property URI</td><td>rdfs:label</td><td></td></tr>"+
				"<tr><td>setCaseSensitive</td><td>true/false</td><td>false</td><td></td></tr>"+
				"<tr><td>addReferenceOntology</td><td>ref. ont name</td><td>none</td><td></td></tr>"+
				"<tr><td>strategy</td><td>ExactStringMatch</td><td>ExactStringMatch</td><td></td></tr>"+
				"<tr><td></td><td>StringLeftInclude</td><td></td><td></td></tr>"+
				"<tr><td></td><td>WordsOverlapSimilarity</td><td></td><td>minSimilarity(70)</td></tr>"+
				"<tr><td>useLucene</td><td>true/false</td><td>false</td><td></td></tr>"+
				"<tr><td>hashReference</td><td>true/false</td><td>false</td><td></td></tr>"+
				"<tr><td>condiderClasses</td><td>true/false</td><td>true</td><td></td></tr>"+
				"<tr><td>considerIndividuals</td><td>true/false</td><td>false</td><td></td></tr>"+
				"<tr><td>mergeProperties</td><td>true/false</td><td>true</td><td></td></tr>";
		if(hasHammingDistance=true) description+="<tr><td></td><td>hammingDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasJaroMeasure=true) description+="<tr><td></td><td>jaroMeasure</td><td></td><td>minSimilarity(70)</td></tr>";
		if(hasJaroWinklerMeasure=true) description+="<tr><td></td><td>jaroWinklerMeasure</td><td></td><td>minSimilarity(70)</td></tr>";
		if(hasLevenshteinDistance=true) description+="<tr><td></td><td>levenshteinDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasNeedlemanWunch2Distance=true) description+="<tr><td></td><td>needlemanWunch2Distance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasNeedlemanWunchDistance=true) {
			description+="<tr><td></td><td>needlemanWunchDistance</td><td></td><td>maxDistance(20)</td></tr>";
			description+="<tr><td></td><td></td><td></td><td>needlemanWunchParam(1)</td></tr>";
		}
		if(hasNgramDistance=true) description+="<tr><td></td><td>ngramDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasSmoaDistance=true) description+="<tr><td></td><td>smoaDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasSubStringDistance=true) description+="<tr><td></td><td>subStringDistance</td><td></td><td>maxDistance(20)</td></tr>";
		
		description+="</table></parameters>" +
				"</description>";
		
	}
	
	
	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equals("addLeftProperty")) {
			leftPropertyList.add(paramValue);
			return true;
		}
		else if(paramName.equals("addRightProperty")) {
			rightPropertyList.add(paramValue);
			return true;
		}
		else if(paramName.equals("addReferenceProperty")) {
			referencePropertyString=paramValue;
			return true;
		}
		else if(paramName.equals("setCaseSensitive")) {
			
			if(paramValue.equals("true")) {
				caseSensitive=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				caseSensitive=false;
				return true;
			}
			else return false;
		}
		else if(paramName.equals("ignoreSameURI")) {
			if(paramValue.equals("true")) {
				ignoreSameURI=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				ignoreSameURI=false;
				return true;
			}
			else return false;
		}
		else if(paramName.equals("considerClasses")) {
			if(paramValue.equals("true")) {
				considerClasses=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				considerClasses=false;
				return true;
			}
			else return false;
		}
		else if(paramName.equals("considerIndividuals")) {
			if(paramValue.equals("true")) {
				considerIndividuals=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				considerIndividuals=false;
				return true;
			}
			else return false;
		}
		else if(paramName.equals("mergeProperties")) {
			if(paramValue.equals("true")) {
				mergeProperties=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				mergeProperties=false;
				return true;
			}
			else return false;
		}
		else if(paramName.equals("addReferenceOntology")) {
			referenceOntologyListNames.add(paramValue);
			return true;
		}
		else if(paramName.equals("useLucene")) {
			if(paramValue.equals("true")) {
				useLucene=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				useLucene=false;
				return true;
			}
			else return false;
		}
		
		else if(paramName.equals("hashReference")) {
			if(paramValue.equals("true")) {
				hashReference=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				hashReference=false;
				return true;
			}
			else return false;
			
		}
		else if(paramName.equals("strategy")) {
			if(paramValue.equals("ExtactStringMatch")) {
				clearMethods();
				exactMatch=true;
				return true;
			}
			else if(paramValue.equals("StringLeftInclude")) {
				clearMethods();
				leftInclude=true;
				return true;
			}
			else if(paramValue.equals("WordsOverlapSimilarity")) {
				clearMethods();
				wordsOverlap=true;
				return true;
			}
			if(paramValue.equals("hammingDistance")) {
				if(hasHammingDistance) {  
					clearMethods();
					hammingDistance=true;
					notifyProgressMessage("<message>strategy=hamming distance</message>");
					return true;
				}
				else {
					notifyProgressMessage("<message>hamming distance is not available</message>");
					return false;
				}
			}
			if(paramValue.equals("jaroMeasure")) {
				if(hasJaroMeasure) {   
					clearMethods();
					jaroMeasure=true; 
					return true;
				}
				else return false;
			}
			
			if(paramValue.equals("jaroWrinklerMeasure")) {
				if(hasJaroWinklerMeasure) {   
					clearMethods();
					jaroWinklerMeasure=true;
					return true;
				}
				else return false;
			}
			
			if(paramValue.equals("levenshteinDistance")) {
				if(hasLevenshteinDistance) { 
					clearMethods();
					levenshteinDistance=true;
					return true;  
				}
				else return false;
			}
			if(paramValue.equals("needlemanWunch2Distance")) {
				if(hasNeedlemanWunch2Distance) { 
					clearMethods();
					needlemanWunch2Distance=true;
					return true;  
				}
				else return false;
			}
			if(paramValue.equals("needlemanWunchDistance")) {
				if(hasNeedlemanWunchDistance) {  
					clearMethods();
					needlemanWunchDistance=true;
					return true;  
				}
				else return false;
			}
			if(paramValue.equals("ngramDistance")) {
				if(hasNgramDistance) {  
					clearMethods();
					ngramDistance =true;
					return true; 
				}
				else return false;
			}
			if(paramValue.equals("smoaDistance")) {
				if(hasSmoaDistance) { 
					clearMethods();
					smoaDistance =true;
					return true;
				}
				else return false;
			}
			if(paramValue.equals("subStringDistance")) {
				if(hasSubStringDistance) {  
					clearMethods();
					subStringDistance=true;
					return true; 
				} 
				else return false;
			}
			else return false;
		}
		
		else if(paramName.equals("maxDistance")) {
			maxDistance=Integer.parseInt(paramValue);
			return true;
		}
		else if(paramName.equals("minSimilarity")) {
			minSimilarity=Integer.parseInt(paramValue);
			return true;
		}
		else if(paramName.equals("needlemanWunchParam")) {
			needlemanWunchParam=Integer.parseInt(paramValue);
			return true;
		}
	
		
		else return false;
	}
	


	protected String filterString(String string) {
		if(!filterString) return string;
		if(dropLang) {
			int lastAt=string.lastIndexOf('@');
			if(lastAt>=string.length()-4) return string.substring(0, lastAt);
		}
		return string;
	}
	
	protected Property[] expandProperties(ArrayList pArray) {
		Property[] properties=new Property[pArray.size()];
		Iterator piter=pArray.iterator();
		int i=0;
		while(piter.hasNext()) {
			String pel=(String)piter.next();
			properties[i]=ResourceFactory.createProperty(pel);
			i++;
		}
		return properties;
	}

	

	
	public void reset() {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	/**
	 * returns the subset of the array of properties that are present in the Ontology
	 * @param expandProperties
	 * @param onto1
	 * @return
	 */
	protected Property[] filterProp(Property[] properties, OntModel myModel) {
		notifyDebugMessage("<message>Checking if properties exists in model</message>");
		int ok=0;
		for(int i=0;i<properties.length;i++) {
			
			if(myModel.containsResource(properties[i])) {
				notifyDebugMessage("<message>Found "+properties[i]+"</message>");
				ok++;
			}
			else {
				properties[i]=null;
				

			}
		}
		Property[] newPSet=new Property[ok];
		int j=0;
		for(int i=0;i<properties.length;i++) {
			if(properties[i]!=null) {
				newPSet[j]=properties[i];
				j++;
			}
		}	
		notifyDebugMessage("<message>Total properties on this ontology: "+newPSet.length+"</message>");
		// TODO Auto-generated method stub
		return newPSet;
	}
	
	public String getDescription() {

		
		return description;
	}
	
	
	public void setLeftArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			leftOntology=(OWLOntology) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of OWLOntology");
		}
		
	}

	
	public void setRightArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			rightOntology=(OWLOntology) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of OWLOntology");
		}
		
	}
	
	
	
	
	public void startProcess()  {
		if (checkData())
			if(prepareData())
				startComputation();
		
	}
	
	private boolean checkData()  {
		notifyProgressMessage("<message>Checking data</message>");
		if(leftPropertyList.size()==0) {
			leftPropertyList.add("http://www.w3.org/2000/01/rdf-schema#label");
			notifyDebugMessage("<Message>Left property set to default: rdfs:label</Message>");
		}
		if(rightPropertyList.size()==0) {
			rightPropertyList.add("http://www.w3.org/2000/01/rdf-schema#label");
			notifyDebugMessage("<message>Right property set to default: rdfs:label</message>");
		}
		if(referencePropertyString==null) {
			referencePropertyString="http://www.w3.org/2000/01/rdf-schema#label";
			notifyDebugMessage("<message>Reference property set to default: rdfs:label</message>");
		}
		notifyDebugMessage("<message>Dealing with properties</message>");
		leftPropertyArray=expandProperties(leftPropertyList);
		rightPropertyArray=expandProperties(rightPropertyList);
		notifyDebugMessage("<message>Properties expanded</message>");
		leftPropertyArray=filterProp(leftPropertyArray, leftOntology.getJenaOntModel());
		rightPropertyArray=filterProp(rightPropertyArray, rightOntology.getJenaOntModel());
		
		notifyDebugMessage("<message>Properties filtered</message>");
		if(referenceOntologyListNames!=null) {
			
			if(referenceOntologyListNames.size()>0) {
				useReference=true;
				int i=0;
				notifyDebugMessage("<message>Checking that the reference ontologies exist</message>");
				ArrayList<OWLOntology> tempList=new ArrayList<OWLOntology>();
				//referenceOntologies=new OWLOntology[referenceOntologyListNames.size()];
				while(i<referenceOntologyListNames.size()) {
					FrameworkInformationEntity tempItem=BaseBeat.getOm().get((String)referenceOntologyListNames.get(i));
					if(tempItem==null) {
						notifyErrorMessage("<message>Reference ontology: "+referenceOntologyListNames.get(i)+" was not found</message>");	
						return false;
					}
					try {
						OWLOntology tempOntology=(OWLOntology) tempItem;
						if(tempOntology.getJenaOntModel().containsResource(ResourceFactory.createProperty(referencePropertyString))) tempList.add(tempOntology);
						else notifyDebugMessage("<message>Property : "+referencePropertyString+" not found in "+tempItem.getName()+"<message>");
					} catch (Exception e) {
						notifyErrorMessage("<message>Wrong type: "+tempItem.getType()+" instead of OWLOntology</message>");	
						return false;
					}
				
					i++;
				}
				referenceOntologies=tempList.toArray(referenceOntologies);
			}
	
		}
		return true;
	}
	
	private boolean prepareData() {
		result=new SimpleAlignment();
		result.setLeftName(leftOntology.getName());
		result.setRightName(rightOntology.getName());
		
		OntModel om1=leftOntology.getJenaOntModel();
		OntModel om2=rightOntology.getJenaOntModel();
		leftClasses=leftOntology.getJenaClassesArray();
		rightClasses=rightOntology.getJenaClassesArray();
		notifyDebugMessage("<message>N.of classes: "+leftClasses.length+ " (left), "+rightClasses.length+" (right)</message>");

		
		leftIndividuals=leftOntology.getJenaIndividualsArray();
		rightIndividuals=rightOntology.getJenaIndividualsArray();
		notifyDebugMessage("<message>N.of individuals: "+leftIndividuals.length+ " (left), "+rightIndividuals.length+" (right)</message>");
		
		if(considerClasses) {
			if(mergeProperties) {
				leftClassesMergedString=new String[leftClasses.length][];
				rightClassesMergedString=new String[rightClasses.length][];
			}
			else {
				leftClassesPropertiesString=new String[leftClasses.length][leftPropertyArray.length][];
				rightClassesPropertiesString=new String[rightClasses.length][rightPropertyArray.length][];
			}
		
			int leftcounter=0;
			for(int cn=0;cn<leftClasses.length;cn++) {
				Set strMergedSet=new HashSet();
				for(int pn=0;pn<leftPropertyArray.length;pn++) {
					NodeIterator striter= om1.listObjectsOfProperty(leftClasses[cn], leftPropertyArray[pn]);
					Set strPerPropertySet=new HashSet();
					while(striter.hasNext()) {
						RDFNode node=striter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							if(mergeProperties)strMergedSet.add(((Literal)node).getString());
							else strPerPropertySet.add(((Literal)node).getString());
							leftcounter+=1;
						};
					
					}
					if(!mergeProperties) {
						String[] tempSTRA=new String[1];
						leftClassesPropertiesString[cn][pn]=(String[]) strMergedSet.toArray(tempSTRA);
					}
				}
				if(mergeProperties) {
					String[] tempSTRA=new String[1];
					leftClassesMergedString[cn]=(String[]) strMergedSet.toArray(tempSTRA);
				}
			}
			notifyDebugMessage("<message>Considering "+leftcounter+" strings on the left side</message>");
		
			int rightcounter=0;
			for(int cn=0;cn<rightClasses.length;cn++) {
				for(int pn=0;pn<rightPropertyArray.length;pn++) {
					NodeIterator striter= om2.listObjectsOfProperty(rightClasses[cn], rightPropertyArray[pn]);
					Set strSet=new HashSet();
					while(striter.hasNext()) {
						RDFNode node=striter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							strSet.add(((Literal)node).getString());	
						};
					strSet.add(filterString(node.toString()));
					}
					rightcounter+=strSet.size();
					String[] tempSTRA=new String[1];
					rightClassesMergedString[cn]=(String[]) strSet.toArray(tempSTRA);
				}
			}
			notifyDebugMessage("<Message>Considering "+rightcounter+" strings on the right side</Message>");
		}
		
		/////////////
		
		if(considerIndividuals) {
			if(mergeProperties) {
				leftIndividualsMergedString=new String[leftIndividuals.length][];
				rightIndividualsMergedString=new String[rightIndividuals.length][];
			}
			else {
				leftIndividualsPropertiesString=new String[leftIndividuals.length][leftPropertyArray.length][];
				rightIndividualsPropertiesString=new String[rightIndividuals.length][rightPropertyArray.length][];
			}
		
			int leftcounter=0;
			for(int cn=0;cn<leftIndividuals.length;cn++) {
				Set strMergedSet=new HashSet();
				for(int pn=0;pn<leftPropertyArray.length;pn++) {
					NodeIterator striter= om1.listObjectsOfProperty(leftIndividuals[cn], leftPropertyArray[pn]);
					Set strPerPropertySet=new HashSet();
					while(striter.hasNext()) {
						RDFNode node=striter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							if(mergeProperties)strMergedSet.add(((Literal)node).getString());
							else strPerPropertySet.add(((Literal)node).getString());
							leftcounter+=1;
						};
					
					}
					if(!mergeProperties) {
						String[] tempSTRA=new String[1];
						leftIndividualsPropertiesString[cn][pn]=(String[]) strMergedSet.toArray(tempSTRA);
					}
				}
				if(mergeProperties) {
					String[] tempSTRA=new String[1];
					leftIndividualsMergedString[cn]=(String[]) strMergedSet.toArray(tempSTRA);
				}
			}
			notifyDebugMessage("<message>Considering "+leftcounter+" strings on the left side</message>");
		
			int rightcounter=0;
			for(int cn=0;cn<rightIndividuals.length;cn++) {
				for(int pn=0;pn<rightPropertyArray.length;pn++) {
					NodeIterator striter= om2.listObjectsOfProperty(rightIndividuals[cn], rightPropertyArray[pn]);
					Set strSet=new HashSet();
					while(striter.hasNext()) {
						RDFNode node=striter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							strSet.add(((Literal)node).getString());	
						};
					strSet.add(filterString(node.toString()));
					}
					rightcounter+=strSet.size();
					String[] tempSTRA=new String[1];
					rightIndividualsMergedString[cn]=(String[]) strSet.toArray(tempSTRA);
				}
			}
			notifyDebugMessage("<Message>Considering "+rightcounter+" strings on the right side</Message>");
		}
		
		return true;
	}
	public void startComputation() {
		double max=(((double)maxDistance)/((double)100));
		double min=(((double)minSimilarity)/((double)100));
		IndexLARQ index=null;
		notifyProgressMessage("<Message>Starting matching</Message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		int ticklimit=(leftClasses.length)*(rightClasses.length)/100;
		int tick=0;
		int tickpercentage=0;
		int totaleval=0;
		if(!useLucene) {
			notifyDebugMessage("<message>No Lucene indexes, going to make lot of cycles!</message>");
			/*
			if(!(useReference || exactMatch || leftInclude || wordsOverlap)) {
				notifyDebugMessage("<message>No strategy defined!</message>");
				return;
			}
			*/
			if(useReference && ! hashReference) {
				notifyWarningMessage("<message>Using only the first of the reference ontologies at the moment!!!</message>");
				// TODO implement multi-reference ontology. speed up things with direct access...
				
				String indexFileName=BaseBeat.getIndexFileName();
				File indexFile=new File(indexFileName);
				IndexWriter indexWriter;
				try {
					indexWriter = new IndexWriter(indexFile,new StandardAnalyzer(), false);
					IndexBuilderString larqBuilder=new IndexBuilderString(indexWriter);
					index = larqBuilder.getIndex() ;
				} catch (IOException e) {
					notifyDebugMessage("<message>problems in reading Lucene index</message>");
					return;
				}
			}
			if(useReference && hashReference) {
				Property referenceProperty=ResourceFactory.createProperty(referencePropertyString);
				Hashtable<String,OntClass>[] referenceHashSet=new Hashtable[referenceOntologies.length];
				for (int h = 0; h < referenceOntologies.length; h++) {
					notifyProgressMessage("<message>Building hash for ontology "+referenceOntologies[h].getName()+"</message>");
					OntClass[] myRefClasses=referenceOntologies[h].getJenaClassesArray();
					Hashtable<String,OntClass> termTable=new Hashtable<String,OntClass>();
					for (int cn = 0; cn < myRefClasses.length; cn++) {
						NodeIterator striter= referenceOntologies[h].getJenaOntModel().listObjectsOfProperty(myRefClasses[cn], referenceProperty);
						
						while(striter.hasNext()) {
							RDFNode node=striter.nextNode();
							if(node.isLiteral()) {
								String myString=(((Literal)node).getString());
								if(!caseSensitive) myString=myString.toLowerCase();
								termTable.put(myString, myRefClasses[cn]);
									
								
							}
							
						}
											
					}
					referenceHashSet[h]=termTable;
				}
					
				
			}
			boolean stringMatched=false;
			for(int lc=0;lc<leftClasses.length;lc++) {
				for(int rc=0;rc<rightClasses.length;rc++) {
					String globalExplanation="";
					for(int x=0; x<leftClassesMergedString[lc].length;x++) {
						for(int y=0; y<rightClassesMergedString[rc].length;y++) {
							stringMatched=false;
							String explanation="";
							int confidence=0;
							String type="";
							if(leftClassesMergedString[lc][x]!=null && rightClassesMergedString[rc][y]!=null) {
								//Here we depends on the methods
								
								 if(useReference&& !hashReference) {
									String[] leftTokens=leftClassesMergedString[lc][x].split(" ");
									String[] rightTokens=rightClassesMergedString[rc][y].split(" ");
									boolean leftMatch=false;
									boolean rightMatch=false;
									System.out.println(" >>"+totaleval);
									for(int inner=0;inner<leftTokens.length;inner++) {
										leftTokens[inner]=leftTokens[inner].replaceAll("'", "\\x54");
										System.out.print("L: "+leftTokens[inner]+": ");
										String queryString="PREFIX pf: <http://jena.hpl.hp.com/ARQ/property#>\n" +
										"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
										"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
										"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"+
										"select ?term ?p ?lit\n" +
										"where {\n"+
										"?lit pf:textMatch \"+"+leftTokens[inner]+"\" .\n" +
										"?term rdfs:label ?lit .\n" +
										"?term rdf:type owl:Class .\n" +
										"}";
										//System.out.println(queryString);
										
										Query query=QueryFactory.create(queryString);
										QueryExecution qExec=QueryExecutionFactory.create(query,referenceOntologies[0].getJenaModel(true));
										LARQ.setDefaultIndex(qExec.getContext(), index); 
										
										ResultSet res=qExec.execSelect();
										
										while(res.hasNext()) {
											QuerySolution sol= res.nextSolution();
											explanation+="";													
											explanation+="Left word: "+leftTokens[inner]+" matched "+sol.get("term").toString()+" on string "+sol.get("lit").toString()+"\n";
											leftMatch=true;
											System.out.print("l");
										}	
										System.out.println();
										
									}
									for(int inner=0;inner<rightTokens.length;inner++) {
										rightTokens[inner]=rightTokens[inner].replaceAll("'", "\\x54");
										System.out.print("R: "+rightTokens[inner]+": ");
										String queryString="PREFIX pf: <http://jena.hpl.hp.com/ARQ/property#>\n" +
										"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
										"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
										"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"+
										"select ?term ?p ?lit\n" +
										"where {\n"+
										"?lit pf:textMatch \"+"+rightTokens[inner]+"\" .\n" +
										"?term rdfs:label ?lit .\n" +
										"?term rdf:type owl:Class .\n" +
										"}";
										//System.out.println(queryString);
										Query query=QueryFactory.create(queryString);
										QueryExecution qExec=QueryExecutionFactory.create(query,referenceOntologies[0].getJenaModel(true));
										LARQ.setDefaultIndex(qExec.getContext(), index); 
										
										ResultSet res=qExec.execSelect();
										
										while(res.hasNext()) {
											QuerySolution sol= res.nextSolution();
											explanation+="";													
											explanation+="Right word: "+rightTokens[inner]+" matched "+sol.get("term").toString()+" on string "+sol.get("lit").toString()+"\n";
											rightMatch=true;
											System.out.print("r");
										}	
										System.out.println();
									}
									stringMatched=leftMatch&&rightMatch;	
									
								}
								else if(exactMatch) {
									if(caseSensitive) stringMatched=leftClassesMergedString[lc][x].equals(rightClassesMergedString[rc][y]);
									else stringMatched=leftClassesMergedString[lc][x].equalsIgnoreCase(rightClassesMergedString[rc][y]);	
									explanation="exact Match: ("+leftClassesMergedString[lc][x]+")";
									confidence=100;
									type="http://www.bootstrep.eu/enrichment#stringExactMatch";
								}
								else if(leftInclude) {
									if(caseSensitive) {
										if(leftClassesMergedString[lc][x].indexOf(rightClassesMergedString[rc][y])>-1) stringMatched=true;
									}
									else {
										if((leftClassesMergedString[lc][x].toLowerCase()).indexOf((rightClassesMergedString[rc][y]).toLowerCase())>-1) stringMatched=true;
									}
									explanation="left inclusion: ("+rightClassesMergedString[rc][y]+" IN "+leftClassesMergedString[lc][x]+")";
									confidence=100;
									type="http://www.bootstrep.eu/enrichment#stringIncludeMatch";
								}
								else if(wordsOverlap) {
									String[] leftTokens=leftClassesMergedString[lc][x].split(" ");
									String[] rightTokens=rightClassesMergedString[rc][y].split(" ");
									TreeSet left=new TreeSet();
									for(int inner=0;inner<leftTokens.length;inner++)
										left.add(leftTokens[inner]);
									HashSet right=new HashSet();
									for(int inner=0;inner<rightTokens.length;inner++)
										right.add(rightTokens[inner]);
									left.retainAll(right);
									int overIndex=(left.size()*200)/(leftTokens.length+rightTokens.length);
									if(overIndex>minSimilarity)  stringMatched=true;
									explanation="Overlap="+overIndex+" in ("+leftClassesMergedString[lc][x]+" AND "+rightClassesMergedString[rc][y]+")";
									confidence=overIndex;
									type="http://www.bootstrep.eu/enrichment#stringOverlap";
								}
								else if(hammingDistance) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.hammingDistance(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance<max) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.hammingDistance(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance<max) 
											stringMatched=true;
									}
									explanation="approximate string match(hamming distance, max. "+maxDistance+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringHammingDistanceMatch";
								}
								else if(jaroMeasure) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.jaroMeasure(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance>min) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.jaroMeasure(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance>min) 
											stringMatched=true;
									}
									explanation="approximate string match(jaro Measure, min. "+minSimilarity+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (int)(distance*100);
									type="http://www.bootstrep.eu/enrichment#stringJaroSimilarityMatch";
								}
								else if(jaroWinklerMeasure==true) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.jaroWinklerMeasure(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance>min) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.jaroWinklerMeasure(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance>min) 
											stringMatched=true;
									}
									explanation="approximate string match(JaroWinkler measure, min. "+minSimilarity+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringJaroWinklerSimilarityMatch";
								}
								else if(levenshteinDistance==true) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.levenshteinDistance(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance<max) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.levenshteinDistance(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance<max) 
											stringMatched=true;
									}
									explanation="approximate string match(levenshtein distance, max. "+maxDistance+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringLevenstheinDistanceMatch";
								}
								else if(needlemanWunch2Distance==true) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.needlemanWunch2Distance(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance<max) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.needlemanWunch2Distance(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance<max) 
											stringMatched=true;
									}
									explanation="approximate string match(needleman wunch 2 distance, max. "+maxDistance+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringNeedlemanWunch2DistanceMatch";
								}
								else if(needlemanWunchDistance==true) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.needlemanWunchDistance(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y],needlemanWunchParam);
										if(distance<max) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.needlemanWunchDistance(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase(),needlemanWunchParam);
										if(distance<max) 
											stringMatched=true;
									}
									explanation="approximate string match(needleman wunch distance, max. "+maxDistance+" parm="+needlemanWunchParam+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringNeedlemanWunchDistanceMatch";
								}
								else if(ngramDistance==true) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.ngramDistance(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance<max) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.ngramDistance(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance<max) 
											stringMatched=true;
									}
									explanation="approximate string match(ngram distance, max. "+maxDistance+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringNgramDistanceMatch";
								}
								else if(smoaDistance==true) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.smoaDistance(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance<max) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.smoaDistance(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance<max) 
											stringMatched=true;
									}
									explanation="approximate string match(smoa distance, max. "+maxDistance+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringSmoaDistanceMatch";
								}
								else if(subStringDistance==true) {
									double distance=0;
									if(caseSensitive) {
										distance=StringDistances.subStringDistance(leftClassesMergedString[lc][x],rightClassesMergedString[rc][y]);
										if(distance<max) 
											stringMatched=true;
									}
									else {
										distance=StringDistances.subStringDistance(leftClassesMergedString[lc][x].toLowerCase(),rightClassesMergedString[rc][y].toLowerCase());
										if(distance<max) 
											stringMatched=true;
									}
									explanation="approximate string match(substring distance, max. "+maxDistance+"): ("+rightClassesMergedString[rc][y]+" AND "+leftClassesMergedString[lc][x]+")";
									confidence= (1-(int)(distance*100));
									type="http://www.bootstrep.eu/enrichment#stringSubstringDistanceMatch";
								}
								
									
								if(stringMatched) {
									globalExplanation+=explanation+"\n";
								}
								if(stringMatched) {
									String leftURI=leftClasses[lc].getURI();
									String rightURI=rightClasses[rc].getURI();
									if(!leftURI.equals(rightURI)) {
										notifyDebugMessage("<message>Found alignment between:</br>" +
										"1) "+leftClasses[lc]+"</br>"+
										"2) "+rightClasses[rc]+"</br>"+
										"explanation: </br>"+
										globalExplanation+"<message>");
															/*
															SimpleAlignmentCell cc=new SimpleAlignmentCell(leftClasses[lc], rightClasses[rc],"ontoMatcher:nameSimilar",leftString[lc][x],1);
															alignment.add(cc);
															*/
										result.addJenaSimpleAlignment(leftClasses[lc],rightClasses[rc], genericStringRelation, confidence, type, explanation, null);
									}
									x=leftClassesMergedString[lc].length;
									y=rightClassesMergedString[rc].length;
								}
								totaleval++;
							}
						}
					}
					tick++;
					if(tick>ticklimit) {
						tick=0;
						tickpercentage++;
						notifyProgressStatus(0, lc, leftClasses.length);
						notifyProgressMessage("+Computed: "+totaleval);
					}
				}
			}
		}
			/*
			// TODO Auto-generated method stub
			SimpleAlignmentCell[] returnAlign=new SimpleAlignmentCell[0];
			SimpleAlignment myAlignment=new SimpleAlignment(onto1.getName()+"-"+onto2.getName(),(SimpleAlignmentCell[])alignment.toArray(returnAlign));
			
			String metricValue="";
			if(simpleMetric) metricValue="simple";
			else metricValue="shuflle";
			myAlignment.addParam("metric", metricValue);
			
			myAlignment.addParam("caseSensitive", Boolean.toString(caseSensitive));
			myAlignment.addParam("ignoreSameURI", Boolean.toString(ignoreSameURI));
			String resultValue="";
			if(softResult) resultValue="soft";
			else resultValue="hard";
			myAlignment.addParam("result", resultValue);
			
			String filterStringValue="";
			if(!filterString) filterStringValue="none";
			if(dropLang) filterStringValue="dropLang";
			myAlignment.addParam("filterString", filterStringValue);	
			*/	
			
		
		else {
			notifyDebugMessage("<message>Using Lucene indexes</message>");
			notifyWarningMessage("<message>Function unimplemented yet</message>");
		}
		
	
	}
	
	public FrameworkInformationEntity getResult() {
		return result;
	}
	
}
