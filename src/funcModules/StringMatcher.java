/**
 * StringMatcher.java
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
import java.util.List;
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


import com.hp.hpl.jena.graph.Factory;
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
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.impl.RDFListImpl;
import com.wcohen.ss.Jaro;
import com.wcohen.ss.JaroWinkler;

import fr.inrialpes.exmo.align.impl.method.StringDistances;

public  class StringMatcher extends AbstractProcessImpl implements DoubleFunctionProcess {
	
	private double one=1.0;
		
	protected ArrayList<String> leftPropertyList;
	protected ArrayList<String> rightPropertyList;
	protected ArrayList<String> referenceOntologyListNames;
	protected String referencePropertyString=null;
	
	
	
	protected Property[] leftPropertyArray=null;
	protected Property[] rightPropertyArray=null;
	protected boolean caseSensitive=false;
	protected boolean ignoreSameURI=true; 	
	protected boolean useLucene=false;
	protected boolean mergeProperties=true;
	protected boolean hashReference=true;
	protected boolean considerClasses=true;
	protected boolean considerIndividuals=false;
	protected boolean mixClassesAndIndividuals=false;
	protected boolean speedy=false;
	//int wordsOverlapSensitivity=70;
	
	private Hashtable<String,HashSet<OntClass>>[] referenceHashSet;
	boolean dropAmbigousReferences=true;
	//private Hashtable<String, OntClass> postProcessedReferenceHashSet;
	//String comparisons
	protected boolean exactMatch=false;
	protected boolean leftInclude=false;
	protected boolean stringInclusion=false;	
	protected boolean wordsOverlap=false;
	protected boolean wordsLeftInclude=false;
	protected boolean wordsInclusion=false;	
	protected boolean hammingDistance=false;
	protected boolean jaroAPI=false;
	protected boolean jaroWinklerAPI=false;
	protected boolean jaroSecondString=false;
	protected boolean jaroWinklerSecondString=false;
	protected boolean levenshteinDistance=false;
	protected boolean needlemanWunsch2Distance=false;
	protected boolean needlemanWunschDistance=false;
	protected boolean ngramDistance=false;
	protected boolean smoaDistance=false;
	protected boolean subStringDistance=false;
	
	private Hashtable<String,String[]> segmentedStrings;
	
	private void clearMethods() {
		exactMatch=false;
		leftInclude=false;
		stringInclusion=false;
		wordsOverlap=false;
		wordsLeftInclude=false;
		wordsInclusion=false;
		hammingDistance=false;
		jaroAPI=false;
		jaroWinklerAPI=false;
		jaroSecondString=false;
		jaroWinklerSecondString=false;	
		levenshteinDistance=false;
		needlemanWunsch2Distance=false;
		needlemanWunschDistance=false;
		ngramDistance=false;
		smoaDistance=false;
		subStringDistance=false;
	}
	
	//String/Ontology based support
	protected boolean useReference=false;
	
	
	
	
	
	OWLOntology leftOntology;
	OWLOntology rightOntology;
	OWLOntology[] referenceOntologies=new OWLOntology[0];
	
	protected String assertedRelationString=null;
	//protected Property genericStringRelation;
	
	
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
	boolean hasJaroAPI=false;
	boolean hasJaroWinklerAPI=false;
	boolean hasJaroSecondString=false;
	boolean hasJaroWinklerSecondString=false;
	boolean hasLevenshteinDistance=false;
	boolean hasNeedlemanWunsch2Distance=false;
	boolean hasNeedlemanWunschDistance=false;
	boolean hasNgramDistance=false;
	boolean hasSmoaDistance=false;
	boolean hasSubStringDistance=false;
	
	int minSimilarity=70;
	int maxDistance=20;
	int needlemanWunschParam=1;

	
	
	
	public StringMatcher() {
		super();
		leftPropertyList=new ArrayList<String>();
		rightPropertyList=new ArrayList<String>();
		referenceOntologyListNames=new ArrayList<String>();
		segmentedStrings=new Hashtable<String, String[]>();
		//genericStringRelation=ResourceFactory.createProperty(assertedRelation);
		name= "extendedstringmatcher";
		type=FrameworkTypes.MATCHER;
		
		try {
			StringDistances.hammingDistance("AAA", "BBB");
			hasHammingDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Hamming distance is unavailable</message>");
		}	   
		try {
			StringDistances.jaroMeasure("AAA", "BBB");
			hasJaroAPI=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Jaro Measure is unavailable</message>");
		}	
		try {	           
			StringDistances.jaroWinklerMeasure("AAA", "BBB");
			hasJaroWinklerAPI=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Jaro Wrinkler distance is unavailable</message>");
		}	
		try {
			(new Jaro()).score("AAA", "BBB");
			hasJaroSecondString=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>Jaro Measure is unavailable</message>");
		}	
		try {	           
			(new JaroWinkler()).score("AAA", "BBB");
			hasJaroWinklerSecondString=true;
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
			hasNeedlemanWunsch2Distance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>NeedlemanWunsch2D distance is unavailable</message>");
		}	
		try {         
			StringDistances.needlemanWunchDistance("AAA", "BBB", 1);
			hasNeedlemanWunschDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>NeedlemanWunsch distance is unavailable</message>");
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
			hasSubStringDistance=true;
		} catch (Exception e) {
			notifyDebugMessage("<message>SubstringDistance is unavailable</message>");
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
				"<tr><td>hashReference</td><td>true/false</td><td>true</td><td></td></tr>"+
				"<tr><td>setAssertedRelation</td><td>URI</td><td>http://www.bootstrep.eu/ontology/enrichment#genericLexicalRelation</td><td></td></tr>"+
				"<tr><td>strategy</td><td>exactStringMatch</td><td>ExactStringMatch</td><td></td></tr>"+
				"<tr><td></td><td>stringLeftInclude</td><td></td><td></td></tr>"+
				"<tr><td></td><td>stringInclusion</td><td></td><td></td></tr>"+				
				"<tr><td></td><td>subStringDistance</td><td></td><td>maxDistance(20)</td></tr>"+
				"<tr><td></td><td>wordsLeftInclude</td><td></td><td></td></tr>"+
				"<tr><td></td><td>wordsOverlapSimilarity</td><td></td><td>minSimilarity(70)</td></tr>"+
				"<tr><td></td><td>wordsInclusion</td><td></td><td></td></tr>";
	
		if(hasHammingDistance==true) description+="<tr><td></td><td>hammingDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasJaroAPI==true) description+="<tr><td></td><td>jaroMeasureAPI</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasJaroWinklerAPI==true) description+="<tr><td></td><td>jaroWinklerMeasureAPI</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasJaroSecondString==true) description+="<tr><td></td><td>jaroMeasureSecondString</td><td></td><td>minSimilarity(70)</td></tr>";
		if(hasJaroWinklerSecondString==true) description+="<tr><td></td><td>jaroWinklerMeasureSecondString</td><td></td><td>minSimilarity(70)</td></tr>";
		if(hasLevenshteinDistance==true) description+="<tr><td></td><td>levenshteinDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasNeedlemanWunsch2Distance==true) description+="<tr><td></td><td>needlemanWunsch2Distance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasNeedlemanWunschDistance==true) {
			description+="<tr><td></td><td>needlemanWunschDistance</td><td></td><td>maxDistance(20)</td></tr>";
			description+="<tr><td></td><td></td><td></td><td>needlemanWunschParam(1)</td></tr>";
		}
		if(hasNgramDistance==true) description+="<tr><td></td><td>ngramDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasSmoaDistance==true) description+="<tr><td></td><td>smoaDistance</td><td></td><td>maxDistance(20)</td></tr>";
		if(hasSubStringDistance==true) description+="<tr><td></td><td>subStringDistance</td><td></td><td>maxDistance(20)</td></tr>";
		description+="<tr><td>useLucene</td><td>true/false</td><td>false</td><td></td></tr>"+
		"<tr><td>hashReference</td><td>true/false</td><td>false</td><td></td></tr>"+
		"<tr><td>considerClasses</td><td>true/false</td><td>true</td><td></td></tr>"+
		"<tr><td>considerIndividuals</td><td>true/false</td><td>false</td><td></td></tr>"+
		"<tr><td>mergeProperties</td><td>true/false</td><td>true</td><td></td></tr>"+
		"<tr><td>mixClassesAndIndividuals</td><td>true/false</td><td>false</td><td></td></tr>"+
		"<tr><td>speedy</td><td>true/false</td><td>false</td><td></td></tr>"+
		"<tr><td>minSimilarity</td><td>[0,100]</td><td>70</td><td></td></tr>"+
		"<tr><td>maxDistance</td><td>[0,100]</td><td>20</td><td></td></tr>"+
		"</table></parameters>" +
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
		else if(paramName.equals("reset")) {			
			reset();
			return true;
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
		else if(paramName.equals("mixClassesAndIndividuals")) {
			if(paramValue.equals("true")) {
				mixClassesAndIndividuals=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				mixClassesAndIndividuals=false;
				return true;
			}
			else return false;
			
		}
		else if(paramName.equals("speedy")) {
			if(paramValue.equals("true")) {
				speedy=true;
				return true;
			}
			else if(paramValue.equals("false")) {
				speedy=false;
				return true;
			}
			else return false;
			
		}
		else if(paramName.equals("setAssertedRelation")) {
			if(paramValue!=null) {
				assertedRelationString=paramValue;
				return true;
			}
			else return false;
			
		}
		else if(paramName.equals("strategy")) {
			if(paramValue.equals("extactStringMatch")) {
				clearMethods();
				exactMatch=true;
				assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#exactStringMatch";
				return true;
			}
			else if(paramValue.equals("stringLeftInclude")) {
				clearMethods();
				leftInclude=true;
				assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringLeftIncludeMatch";
				return true;
			}
			else if(paramValue.equals("stringInclusion")) {
				clearMethods();
				stringInclusion=true;
				assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringInclusionMatch";
				return true;
			}
			else if(paramValue.equals("wordsOverlapSimilarity")) {
				clearMethods();
				wordsOverlap=true;
				assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringOverlapMatch";
				return true;
			}
			else if(paramValue.equals("wordsLeftInclude")) {
				clearMethods();
				wordsLeftInclude=true;
				assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#wordsLeftIncludeMatch";
				return true;
			}
			else if(paramValue.equals("wordsInlcusion")) {
				clearMethods();
				wordsInclusion=true;
				assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#wordsInclusionMatch";
				return true;
			}
			else if(paramValue.equals("hammingDistance")) {
				if(hasHammingDistance) {  
					clearMethods();
					hammingDistance=true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringHammingDistanceMatch";
					notifyProgressMessage("<message>strategy=hamming distance</message>");
					return true;
				}
				else {
					notifyProgressMessage("<message>hamming distance is not available</message>");
					return false;
				}
			}
			else if(paramValue.equals("jaroMeasureAPI")) {
				if(hasJaroAPI) {   
					clearMethods();
					jaroAPI=true; 
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringJaroAPIMatch";
					return true;
				}
				else return false;
			}
			
			else if(paramValue.equals("jaroWinklerMeasureAPI")) {
				if(hasJaroWinklerAPI) {   
					clearMethods();
					jaroWinklerAPI=true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringJaroWinklerAPIMatch";
					return true;
				}
				else return false;
			}
			
			else if(paramValue.equals("jaroMeasureSecondString")) {
				if(hasJaroSecondString) {   
					clearMethods();
					jaroSecondString=true; 
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringJaroSecondStringMatch";
					return true;
				}
				else return false;
			}
			
			else if(paramValue.equals("jaroWinklerMeasureSecondString")) {
				if(hasJaroWinklerSecondString) {   
					clearMethods();
					jaroWinklerSecondString=true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringJaroWinklerSecondStringMatch";
					return true;
				}
				else return false;
			}		
			
			else if(paramValue.equals("levenshteinDistance")) {
				if(hasLevenshteinDistance) { 
					clearMethods();
					levenshteinDistance=true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringLevenstheinDistanceMatch";
					return true;  
				}
				else return false;
			}
			else if(paramValue.equals("needlemanWunsch2Distance")) {
				if(hasNeedlemanWunsch2Distance) { 
					clearMethods();
					needlemanWunsch2Distance=true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringNeedlemanWunsch2DistanceMatch";
					return true;  
				}
				else return false;
			}
			else if(paramValue.equals("needlemanWunschDistance")) {
				if(hasNeedlemanWunschDistance) {  
					clearMethods();
					needlemanWunschDistance=true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringNeedlemanWunschDistanceMatch";
					return true;  
				}
				else return false;
			}
			else if(paramValue.equals("ngramDistance")) {
				if(hasNgramDistance) {  
					clearMethods();
					ngramDistance =true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringNgramDistanceMatch";
					return true; 
				}
				else return false;
			}
			else if(paramValue.equals("smoaDistance")) {
				if(hasSmoaDistance) { 
					clearMethods();
					smoaDistance =true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringSmoaDistanceMatch";
					return true;
				}
				else return false;
			}
			else if(paramValue.equals("subStringDistance")) {
					clearMethods();
					subStringDistance=true;
					assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#stringSubstringDistanceMatch";
					return true; 
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
		else if(paramName.equals("needlemanWunschParam")) {
			needlemanWunschParam=Integer.parseInt(paramValue);
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
	
	protected Property[] expandProperties(ArrayList<String> pArray) {
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
		notifyDebugMessage("<message>Resetting extended string matcher</message>");
		clearMethods();
		leftPropertyList=new ArrayList<String>();
		rightPropertyList=new ArrayList<String>();
		referenceOntologyListNames=new ArrayList<String>();
		referenceOntologies=new OWLOntology[0];
		assertedRelationString=null;
		//TODO: 
		//minSimilarity=; 
		//maxDistance=;
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
		notifyDebugMessage("<message>Total properties considered for this ontology: "+newPSet.length+"</message>");
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

		if(!useLucene) notifyDebugMessage("<message>No Lucene indexes, going to make lot of cycles!</message>");		
		else notifyDebugMessage("<message>Lucene index is used. This case is not completely implemented yet.</message>");
		
		if(!(mixClassesAndIndividuals)) notifyDebugMessage("<message>Considering only classes, no individuals.</message>");
		else notifyDebugMessage("<message>Considering classes and individuals.</message>");
		
		if (mergeProperties) notifyDebugMessage("<message>Merge properties.</message>");
		else notifyDebugMessage("<message>Properties are not merged. This case is not completely implemented yet.</message>");
		
		if(exactMatch==false &&
		leftInclude==false &&
		stringInclusion==false &&
		wordsOverlap==false &&
		wordsLeftInclude==false &&
		wordsInclusion==false &&	
		hammingDistance==false &&
		jaroAPI==false &&
		jaroWinklerAPI==false &&
		jaroSecondString==false &&
		jaroWinklerSecondString==false &&
		levenshteinDistance==false &&
		needlemanWunsch2Distance==false &&
		needlemanWunschDistance==false &&
		ngramDistance==false &&
		smoaDistance==false &&
		subStringDistance==false) {
			notifyDebugMessage("<Message>Opting for default method: ExtactStringMatch</Message>");	
			exactMatch=true;
		}
		
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
		if(assertedRelationString==null) {
			assertedRelationString="http://www.bootstrep.eu/ontology/enrichment#genericLexicalRelation";
			notifyDebugMessage("<message>Asserted Relation set to "+assertedRelationString+"</message>");
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
					FrameworkInformationEntity tempItem=BaseBeat.getOm().get(referenceOntologyListNames.get(i));
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
				referenceOntologies=new OWLOntology[1];
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
				Set<String> strMergedSet=new HashSet<String>();
				for(int pn=0;pn<leftPropertyArray.length;pn++) {
					NodeIterator striter= om1.listObjectsOfProperty(leftClasses[cn], leftPropertyArray[pn]);
					Set<String> strPerPropertySet=new HashSet<String>();
					while(striter.hasNext()) {
						RDFNode node=striter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							if(mergeProperties)strMergedSet.add(((Literal)node).getString());
							else strPerPropertySet.add(((Literal)node).getString());
							leftcounter+=1;
						}
					
					}
					if(!mergeProperties) {
						String[] tempSTRA=new String[1];
						leftClassesPropertiesString[cn][pn]= strPerPropertySet.toArray(tempSTRA);
					}
				}
				if(mergeProperties) {
					String[] tempSTRA=new String[1];
					leftClassesMergedString[cn]= strMergedSet.toArray(tempSTRA);
				}
			}
		
			int rightcounter=0;
			for(int cn=0;cn<rightClasses.length;cn++) {
				Set<String> strSet=new HashSet<String>();
				for(int pn=0;pn<rightPropertyArray.length;pn++) {
					NodeIterator striter= om2.listObjectsOfProperty(rightClasses[cn], rightPropertyArray[pn]);
					
					while(striter.hasNext()) {
						RDFNode node=striter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							strSet.add(((Literal)node).getString());	
							rightcounter+=1;
						}
					    //strSet.add(filterString(node.toString()));
					}
					
					String[] tempSTRA=new String[1];
					rightClassesMergedString[cn]= strSet.toArray(tempSTRA);
				}
			}
			notifyDebugMessage("<message>N.of strings considered: "+leftcounter+" (left), " +
					rightcounter+ " (right)</Message>");
		}
		
		/**
		 * considerIndividuals is untested TODO
		 * `
		 */
		
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
			for(int cn=0;cn<leftClasses.length;cn++) {
				Set<String> strMergedSet=new HashSet<String>();
				for(int pn=0;pn<leftPropertyArray.length;pn++) {
					NodeIterator strIter= om1.listObjectsOfProperty(leftClasses[cn], leftPropertyArray[pn]);
					Set<String> strPerPropertySet=new HashSet<String>();
					while(strIter.hasNext()) {
						RDFNode node=strIter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							if(mergeProperties)strMergedSet.add(((Literal)node).getString());
							else strPerPropertySet.add(((Literal)node).getString());
							leftcounter+=1;
						}				
					}
					if(!mergeProperties) {
						String[] tempSTRA=new String[1];
						leftClassesPropertiesString[cn][pn]= strPerPropertySet.toArray(tempSTRA);
					}
				}
				if(mergeProperties) {
					String[] tempSTRA=new String[1];
					leftClassesMergedString[cn]= strMergedSet.toArray(tempSTRA);
				}
			}
			notifyDebugMessage("<message>Considering "+leftcounter+" strings on the left side</message>");
		
			int rightcounter=0;
			for(int cn=0;cn<rightClasses.length;cn++) {
				Set<String> strMergedSet=new HashSet<String>();
				for(int pn=0;pn<rightPropertyArray.length;pn++) {
					NodeIterator strIter= om2.listObjectsOfProperty(rightClasses[cn], rightPropertyArray[pn]);
					Set<String> strPerPropertySet=new HashSet<String>();
					while(strIter.hasNext()) {
						RDFNode node=strIter.nextNode();
						if(node.isLiteral()) {
							//strSet.add(filterString(((Literal)node).getString()));	
							if(mergeProperties)strMergedSet.add(((Literal)node).getString());
							else strPerPropertySet.add(((Literal)node).getString());
							rightcounter+=1;
						}				
					}
					if(!mergeProperties) {
						String[] tempSTRA=new String[1];
						rightClassesPropertiesString[cn][pn]= strPerPropertySet.toArray(tempSTRA);
					}
				}
				if(mergeProperties) {
					String[] tempSTRA=new String[1];
					rightClassesMergedString[cn]= strMergedSet.toArray(tempSTRA);
				}
			}
			notifyDebugMessage("<message>Considering "+rightcounter+" strings on the right side</message>");
		}		
		
		if(hashReference) {
			notifyProgressMessage("<message>Setting up hastables for reference ontologies</message>");
			notifyDebugMessage("<message>Going to do this all times, for the time being, but of course I can cache my knowledge</message>");
			Property referenceProperty=ResourceFactory.createProperty(referencePropertyString);
			referenceHashSet=new Hashtable[referenceOntologies.length];
			for (int h = 0; h < referenceOntologies.length; h++) {
				notifyProgressMessage("<message>Building hash for ontology "+referenceOntologies[h].getName()+"</message>");
				
				OntClass[] myRefClasses=referenceOntologies[h].getJenaClassesArray();
				Hashtable<String,HashSet<OntClass>> termTable=new Hashtable<String,HashSet<OntClass>>();
				for (int cn = 0; cn < myRefClasses.length; cn++) {
					NodeIterator striter= referenceOntologies[h].getJenaOntModel().listObjectsOfProperty(myRefClasses[cn], referenceProperty);
					
					while(striter.hasNext()) {
						RDFNode node=striter.nextNode();
						if(node.isLiteral()) {
							String myString=(((Literal)node).getString());
							if(!caseSensitive) myString=myString.toLowerCase();
							HashSet<OntClass> mySetOfOntoIDs=termTable.get(myString);
							if(mySetOfOntoIDs==null) {
								mySetOfOntoIDs=new HashSet<OntClass>();	
								termTable.put(myString,mySetOfOntoIDs);
							}
							mySetOfOntoIDs.add(myRefClasses[cn]);
							
							if(mySetOfOntoIDs.size()>1) {
								//Iterator ambClasses=mySetOfOntoIDs.iterator();
								String message="";
								for (OntClass ontClass : mySetOfOntoIDs) {
									message+=ontClass.getLocalName()+"<br>";
								}
								notifyDebugMessage("<message>Amibguity detected for term: "+myString+", classes:<br>"+message+"</message>");
							}
								
							
						}
						
					}
					if(cn%1000==0) notifyProgressStatus(0, cn, myRefClasses.length);
										
				}
				Hashtable<String,HashSet<OntClass>> tempTermTable=new Hashtable<String,HashSet<OntClass>>();
				// At this point we decide what to do with amibous terms
				if(dropAmbigousReferences) {
					notifyProgressMessage("<message>Processing ambigous terms</message>");
					Iterator<String>keySet=termTable.keySet().iterator();
					int dropCounter=0;
					for (; keySet.hasNext();) {
						String currentKey= keySet.next();
						if(!(termTable.get(currentKey).size()>1)) {
							tempTermTable.put(currentKey,termTable.get(currentKey));
							
							
						}
						else dropCounter++;
						
					}
					termTable=tempTermTable;
					notifyProgressMessage("<message>Dropped "+dropCounter+" ambiguous classes</message>");
					
				}
				
				referenceHashSet[h]=termTable;
				//System.out.println("GOT: ("+h+")"+referenceHashSet[h].size());
			}
			notifyProgressMessage("<message>Hashtables are ready</message>");
				
			
		}
		
		return true;
	}
	public void startComputation() {
		double max=(((double)maxDistance)/((double)100));
		double min=(((double)minSimilarity)/((double)100));
		IndexLARQ index=null;
		notifyProgressMessage("<Message>Starting string based matching ...</Message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		int ticklimit=(leftClasses.length)*(rightClasses.length)/100;
		int tick=0;
		int tickpercentage=0;
		int totaleval=0;
		int refcounter=0;
		notifyDebugMessage("<message>Asserted relation: "+assertedRelationString+"</message>");
		Property assertedPropery=ResourceFactory.createProperty(assertedRelationString);
		if(!useLucene) {
			//notifyDebugMessage("<message>No Lucene indexes, going to make lot of cycles!</message>");
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
			
			if(!(mixClassesAndIndividuals)) {
				//notifyDebugMessage("<message>Only classes. String based matching. Merged properties.</message>");
				if(leftClassesMergedString==null || rightClassesMergedString==null) {
					notifyDebugMessage("<message>No strings on one of the two sides...(possibly two)</message>");
					return;
				}
				for(int lc=0;lc<leftClasses.length;lc++) {
					for(int rc=0;rc<rightClasses.length;rc++) {
						if(mergeProperties) {
							for(int x=0; x<leftClassesMergedString[lc].length;x++) {
								for(int y=0; y<rightClassesMergedString[rc].length;y++) {
									//String explanation="";
									//int confidence=0;
									//String type="";
									if(leftClassesMergedString[lc][x]!=null && rightClassesMergedString[rc][y]!=null) {
										String leftString=leftClassesMergedString[lc][x];
										String rightString=rightClassesMergedString[rc][y];
										leftString=leftString.trim();
										rightString=rightString.trim();
										if(leftString.length()>0 && rightString.length()>0) {
											if(!caseSensitive){
												leftString=leftString.toLowerCase();
												rightString=rightString.toLowerCase();
											}
											RefResult refRef=null;
											String postMessage="";
											if(useReference) {
												if(useLucene) refRef=processLARQReference(leftString,rightString,index);
												else if(hashReference)refRef=processHashReference(leftString,rightString);
												if(refRef!=null) {
													refcounter++;
													if(leftString.length()!=refRef.leftStringReduced.length()) postMessage+="LL: "+leftString+" --> "+refRef.leftStringReduced+"("+leftString.length()+"--"+refRef.leftStringReduced.length()+")<br/>";
													if(rightString.length()!=refRef.rightStringReduced.length()) postMessage+="RR: "+rightString+" --> "+refRef.rightStringReduced+"("+rightString.length()+"--"+refRef.rightStringReduced.length()+")<br/>";
													leftString=refRef.leftStringReduced;
													rightString=refRef.rightStringReduced;
												}												
											}
											MatchResult result2=matchStrings(leftString,rightString,max,min,1);
										
											if(result2!=null) {
												String leftURI=leftClasses[lc].getURI();
												String rightURI=rightClasses[rc].getURI();
												if(!leftURI.equals(rightURI)) {
													//String postMessage="";
													if(refRef!=null) {
														postMessage=refRef.explanationText+"<br/>"+postMessage;
														
														Iterator<Resource[]> myExpIter=refRef.explanationSet.iterator();
														while (myExpIter.hasNext()) {
															Resource[] row = (Resource[]) myExpIter.next();														
														}
													}
												
													notifyDebugMessage("<message>Found alignment between:</br>" +
															"1) "+leftClasses[lc]+"</br>"+
															"2) "+rightClasses[rc]+"</br>"+
															"explanation: "+
															result2.explanation+"<br/>"+postMessage+".<br/><message>");
																	/*
																	SimpleAlignmentCell cc=new SimpleAlignmentCell(leftClasses[lc], rightClasses[rc],"ontoMatcher:nameSimilar",leftString[lc][x],1);
																	alignment.add(cc);
																	*/
													result.addJenaSimpleAlignment(leftClasses[lc],rightClasses[rc], assertedPropery, result2.confidence, result2.type, result2.explanation, null);
												}
												if(speedy==true) {
													x=leftClassesMergedString[lc].length;
													y=rightClassesMergedString[rc].length;
												}
											}
											totaleval++;									
										}
									}
								}
							}
						}
						tick++;
						if(tick>ticklimit) {
							tick=0;
							tickpercentage++;
							notifyProgressStatus(0, lc, leftClasses.length);
							
							notifyProgressMessage("<message>+Computed: "+totaleval+"</message>");
						}
					}
				}
			}
			
								
						
				
		} // use Lucene
		notifyDebugMessage("<message>+Ref found: "+refcounter+"</message>");
		notifyProgressMessage("<message>Loaded. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");	
	}
	
	public FrameworkInformationEntity getResult() {
		return result;
	}
	
	/**
	 * TODO should be update with the new RefResult (see prossHashReference)
	 * @param leftString
	 * @param rightString
	 * @param index
	 * @return
	 */
	private RefResult processLARQReference(String leftString, String rightString, IndexLARQ index) {
		RefResult result=null;
		
			String explanation="";
			String[] leftTokens=leftString.split(" ");
			String[] rightTokens=rightString.split(" ");
			boolean leftMatch=false;
			boolean rightMatch=false;
			
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
			if(leftMatch&&rightMatch)	{
				result=new RefResult();
				//result.explanation=explanation;
			}
			return result;
	}
		
	private RefResult processHashReference(String leftString, String rightString) {
		RefResult result=new RefResult();
			
				
		//System.out.println("Processing ("+o+")"+referenceOntologies[o].getName());
				
		Hashtable<String, HashSet<OntClass>> currentTermTable;
					
		//System.out.println(termTable.size()+"!!!");
		String[] leftSegments=segment(leftString);
		String[] rightSegments=segment(rightString);
		String leftStringReduced=leftString;
		String rightStringReduced=rightString;
				
		//ArrayList<OntClass> leftMatched=new ArrayList<OntClass>();
		//ArrayList<OntClass> rightMatched=new ArrayList<OntClass>();
		boolean termChanged=false;		
		for(int o=0;o< referenceOntologies.length;o++) {		
			currentTermTable=referenceHashSet[o];
			for (int l = 0; l < leftSegments.length; l++) {
				if(currentTermTable.get(leftSegments[l])!=null) {
					OntClass leftClass=(currentTermTable.get(leftSegments[l])).iterator().next();
					for (int r = 0; r < rightSegments.length; r++) {
						if(currentTermTable.get(rightSegments[r])!=null) {
							OntClass rightClass=(currentTermTable.get(rightSegments[r])).iterator().next();
							//Trying to find which relation links the two
							//testing subclass
								
							if(leftClass.hasSubClass(rightClass)) {
								//System.out.println("YEAH! :"+leftSegments[l]+" superclass_of "+rightSegments[r]+" ("+leftClass.getURI()+" "+rightClass.getURI());
								leftStringReduced=removeSubString(leftStringReduced,leftSegments[l] );
								rightStringReduced=removeSubString(rightStringReduced,rightSegments[r] );
								result.addRefExplanation(leftClass,ResourceFactory.createProperty("http://www.w3.org/2000/01/rdf-schema#subClassOf"),rightClass);
								result.explanationText=	leftSegments[l]+" superclass_of "+rightSegments[r]+" ("+leftClass.getURI()+" "+rightClass.getURI()+")";	
								termChanged=true;
							}
							if(leftClass.hasSuperClass(rightClass)) {
								//System.out.println("YEAH!! :"+leftSegments[l]+" subclass_of "+rightSegments[r]+" ("+leftClass.getURI()+" "+rightClass.getURI());
								leftStringReduced=removeSubString(leftStringReduced,leftSegments[l] );
								rightStringReduced=removeSubString(rightStringReduced,rightSegments[r] );
								result.addRefExplanation(leftClass,ResourceFactory.createProperty("http://www.w3.org/2000/01/rdf-schema#subClassOf"),rightClass);
								result.explanationText=	leftSegments[l]+" subclass_of "+rightSegments[r]+" ("+leftClass.getURI()+" "+rightClass.getURI()+")";
								termChanged=true;
							}
									//if(leftClass.isDisjointWith(rightClass)) {}
									//rightMatched.add(termTable.get(rightSegments[r]));
									
								
						}
					}
							
							//leftMatched.add(termTable.get(leftSegments[l]));
							//System.out.println("LEFT :"+leftSegments[l]);
				}
			}
					
					
		}
		if(termChanged) {
			result.leftStringReduced=leftStringReduced;
			result.rightStringReduced=rightStringReduced;
			return result;
		}
		else return null;
		
	}
	
	
	private MatchResult matchStrings(String leftString, String rightString, double max, double min, int wordRef) {
		MatchResult res=null;
		if(exactMatch) {
			boolean found=leftString.equals(rightString);
			if(found) {
				res=new MatchResult();	
				res.explanation="exact Match: ("+leftString+")";
				res.confidence=100;
				res.type=assertedRelationString;
			}
		}
		else if(leftInclude) {
			boolean found=false;
			if(leftString.indexOf(rightString)>-1) found=true;
			if(found) {
				res=new MatchResult();	
				res.explanation="left includes right: ("+rightString+" IN "+leftString+")";
				res.confidence=100;
				res.type=assertedRelationString;
			}
		}
		else if(stringInclusion) {
			boolean found=false;
			if(leftString.indexOf(rightString)>-1 || rightString.indexOf(leftString)>-1) found=true;
			if(found) {
				res=new MatchResult();	
				res.explanation="one string includes the other in (" + rightString+" AND "+leftString+")";
				res.confidence=100;
				res.type=assertedRelationString;
			}
		}
		else if(wordsOverlap) {
			Set<String> left= getDistinctTokens(leftString);
			Set<String> right = getDistinctTokens(rightString);
			Set<String> numDistinctTokens = new HashSet<String>();
			numDistinctTokens.addAll(left);
			numDistinctTokens.addAll(right);		
			left.retainAll(right);
			double similarity=((double)left.size())/numDistinctTokens.size();
			if(similarity>min) {
				res=new MatchResult();	
				res.explanation= left.size() + "-token overlap in ("+leftString+" AND "+rightString+")";
				res.confidence=(int)(similarity*100);
				res.type=assertedRelationString;
			}
		}
		else if(wordsLeftInclude) {
			Set<String> left= getDistinctTokens(leftString);
			Set<String> right = getDistinctTokens(rightString);		
			if(left.containsAll(right)) {
				res=new MatchResult();	
				res.explanation="left words include right words in ("+leftString+" AND "+rightString+")";
				res.confidence=100;
				res.type=assertedRelationString;
			}
		}
		else if(wordsInclusion) {
			Set<String> left= getDistinctTokens(leftString);
			Set<String> right = getDistinctTokens(rightString);
			if (left.containsAll(right) || right.containsAll(left)) {
				res=new MatchResult();	
				res.explanation="words on one side include words on other side in ("+leftString+" AND "+rightString+")";
				res.confidence=100;
				res.type=assertedRelationString;
			}
		}
		else if(hammingDistance) {
			boolean found=false;
			double distance=StringDistances.hammingDistance(leftString,rightString);
			if(distance<max) found=true;
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(hamming distance, max. "+maxDistance+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100);
				res.type=assertedRelationString;
			}
		}
		else if(jaroAPI) {
			boolean found=false;
			double distance=StringDistances.jaroMeasure(leftString,rightString);
			if(distance<max) found=true; //TODO: check with alignment API if distance is a similarity or dissimilarity!
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(jaro Measure, min. "+minSimilarity+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100); //check with alignment API!
				res.type=assertedRelationString;
			}
		}
		else if(jaroWinklerAPI==true) {
			boolean found=false;
			double distance=StringDistances.jaroWinklerMeasure(leftString,rightString);
			if(distance<min) found=true; //TODO: check with alignment API if distance is a similarity or dissimilarity!
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(JaroWinkler measure, min. "+minSimilarity+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100); //check with alignment API! 
				res.type=assertedRelationString;
			}
		}
		else if(jaroSecondString) {
			boolean found=false;
			double similarity=(new Jaro()).score(leftString,rightString);
			if(similarity>min) found=true; //TODO: check with alignment API if distance is a similarity or dissimilarity!
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(jaro Measure, min. "+minSimilarity+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)(similarity*100); //check with alignment API!
				res.type=assertedRelationString;
			}
		}
		else if(jaroWinklerSecondString==true) {
			boolean found=false;
			double similarity=(new JaroWinkler()).score(leftString,rightString);
			if(similarity>min) found=true; //TODO: check with alignment API if distance is a similarity or dissimilarity!
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(JaroWinkler measure, min. "+minSimilarity+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)(similarity*100); //check with alignment API! 
				res.type=assertedRelationString;
			}
		}
		else if(levenshteinDistance==true) {
			boolean found=false;
			double distance=0;
			distance=StringDistances.levenshteinDistance(leftString,rightString);
			if(distance<max) found=true;
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(levenshtein distance, max. "+maxDistance+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100);
				res.type=assertedRelationString;
			}
		}
		else if(needlemanWunsch2Distance==true) {
			boolean found=false;
			double distance=StringDistances.needlemanWunch2Distance(leftString,rightString);
			if(distance<max) found=true;
			if(found) {
				res=new MatchResult();		
				res.explanation="approximate string match(needleman wunsch 2 distance, max. "+maxDistance+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100);
				res.type=assertedRelationString;
			}
		}
		else if(needlemanWunschDistance==true) {
			boolean found=false;
			double distance=StringDistances.needlemanWunchDistance(leftString,rightString,needlemanWunschParam);
			if(distance<max) found=true;
			if(found) {
				res=new MatchResult();		
				res.explanation="approximate string match(needleman wunsch distance, max. "+maxDistance+" parm="+needlemanWunschParam+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100);
				res.type=assertedRelationString;
			}
		}
		else if(ngramDistance==true) {
			boolean found=false;
			//double distance=StringDistances.ngramDistance(leftString,rightString); //buggy! (returns
			//values outside [0,1] for strings that contain duplicate triples.  
			//This is an alternative implementation of the 3gram distance, based on counting distinct
			//matching 3grams.
	        int n = 3; // tri-grams for the moment
	        Set<String> leftNGrams = getNGrams(leftString, n);
	        Set<String> rightNGrams = getNGrams(rightString, n); 
	        Set<String> allNGrams = new HashSet<String>();
	        allNGrams.addAll(leftNGrams);
	        allNGrams.addAll(rightNGrams);      
	        leftNGrams.retainAll(rightNGrams);           
	        double similarity = ((double)leftNGrams.size())/allNGrams.size();
			if(similarity>min) found=true;
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(ngram distance, max. "+maxDistance+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((similarity)*100);
				res.type=assertedRelationString;
			}
		}
		else if(smoaDistance==true) {
			boolean found=false;
			double distance=StringDistances.smoaDistance(leftString,rightString);
			if(distance<max) found=true;
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(smoa distance, max. "+maxDistance+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100);
				res.type=assertedRelationString;
			}
		}
		else if(subStringDistance==true) {
			boolean found=false;
			double distance=StringDistances.subStringDistance(leftString,rightString);
//		find length of longest common substring of two strings by dynamic programming
//			int[][] array = new int[leftString.length()][rightString.length()];
//		    int lcsLength = 0; 
//		    for(int i = 0; i < leftString.length(); i++){
//		    	for (int j = 0 ; j < rightString.length(); j++){
//		    		if (leftString.charAt(i) == rightString.charAt(j)){
//		    			if (i == 0 || j == 0) array[i][j] = 1;
//		    			else array[i][j] = array[i-1][j-1] + 1;
//		    			lcsLength = Math.max(array[i][j], lcsLength);
//		    		}
//		    	}
//		    }
//		    distance = 1 - ((double)lcsLength * 2 )/(leftString.length() + rightString.length());			
			if(distance<max) found=true;
			if(found) {
				res=new MatchResult();	
				res.explanation="approximate string match(substring distance, max. "+maxDistance+"): ("+leftString+" AND "+rightString+")";
				res.confidence= (int)((1-distance)*100);
				res.type=assertedRelationString;
			}
		}
		return res;	
	}
	
	 private Set<String> getDistinctTokens(String string) {
			String[] tokens = string.split(" ");
			Set<String> tokenList = new HashSet<String>();
			for(String token : tokens)
				tokenList.add(token.trim());		
		return tokenList;
	}
	 
	private Set<String> getNGrams(String string, int n){
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < string.length() - n + 1 ; i++){
			set.add(string.substring(i, i + n));
		}
		return set;
	}

	class RefResult {
		public String explanationText="";
		public String rightStringReduced="";
		public String leftStringReduced="";
		ArrayList<Resource[]> explanationSet;
		public RefResult() {
			super();
			explanationSet=new ArrayList<Resource[]>();
			
		}

		public void addRefExplanation(OntClass leftClass,
				Property property, OntClass rightClass) {
			Resource[] tempLine=new Resource[3];
			tempLine[0]=leftClass;
			tempLine[1]=property;
			tempLine[2]=rightClass;
			explanationSet.add(tempLine);
			
		}
		
	}
	
	 class MatchResult {
		String explanation="";
		int confidence=0;
		String type="";
	}
	 
	 private String[] segment(String string) {
		 String[] segments=segmentedStrings.get(string);
		 if(segments==null) {
			ArrayList<String> tempList=new ArrayList<String>();
			String[] fragments=string.split(" ");
			segments=new String[1];
			tempList.add(string);
			for (int i = 0; i < fragments.length-1; i+=2) {
				tempList.add(fragments[i]+" "+fragments[i+1]);
			}
			for (int i = 1; i < fragments.length-1; i+=2) {
				tempList.add(fragments[i]+" "+fragments[i+1]);
			}
			for (int i = 0; i < fragments.length; i++) {
				tempList.add(fragments[i]);
			}
			
			segments=tempList.toArray(segments);
			segmentedStrings.put(string, segments);
		 }
		 return segments;
		 
	 }
	 
	 private String removeSubString(String mainString,String subString) {
		 if(mainString.indexOf(subString)<0) return mainString;
		 String pre="";
		 String post="";
		 if(mainString.indexOf(subString)>0) {
			 pre=mainString.substring(0,mainString.indexOf(subString));
		 }
		 if(mainString.indexOf(subString)+subString.length()<mainString.length()) {
			 post=mainString.substring(mainString.indexOf(subString)+subString.length());
		 }
		 String res=pre+post;
		 res=res.replaceAll("  ", " ");
		 res=res.trim();
		 //System.out.println(res);
		 return res;
	 }
}
