package funcModules;


/**
 * StringMatcher.java
 * A generic superclass for string matchers
 * Copyright 2009 Andrea Splendiani
 * This software is released under GPL
 * 
 * WARNING: REFACTORING PENDING!
 */



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkQuery;
import types.FrameworkTypes;
import types.OWLOntology;
import types.RDFNetwork;
import types.SimpleAlignment;
import types.WrongType;
import types.interfaces.DoubleFunctionProcess;
import types.interfaces.FrameworkInformationEntity;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ReifiedStatement;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;


public class ExpStringMatcher  extends AbstractProcessImpl implements DoubleFunctionProcess {
	private double one=1.0;
	
	Model myMatchingGraph=ModelFactory.createDefaultModel();
	Property hasWord=ResourceFactory.createProperty("http://beat.org/hasWord");
	Property hasType=ResourceFactory.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
	Resource wordType=ResourceFactory.createResource("http://beat.org/types/word");
	String beatWordsPrefix="http://beat.org/words#";
	Property side=ResourceFactory.createProperty("http://beat.org/side");
	Resource leftSide=ResourceFactory.createResource("http://beat.org/left");
	Resource rightSide=ResourceFactory.createResource("http://beat.org/right");
	Property mapProp=ResourceFactory.createProperty("http://beat.org/wordMap");
	Property wordCountProp=ResourceFactory.createProperty("http://beat.org/wordCount");
	
		
	protected ArrayList<String> leftPropertyList;
	protected ArrayList<String> rightPropertyList;
	protected ArrayList<String> referenceOntologyListNames;
	protected String referencePropertyString=null;
	
	protected Property[] leftPropertyArray=null;
	protected Property[] rightPropertyArray=null;	
	
	//private Hashtable<String,Integer> wordsHashTable;
	
	
	protected boolean exactMatch=false;	
	protected boolean wordsOverlap=false;
	protected boolean wordsLeftInclude=false;
	
	protected boolean useReference=false;
	
	private RDFNetwork leftNetwork;
	private RDFNetwork rightNetwork;
	
	private FrameworkQuery leftResourcesQuery=null;
	private FrameworkQuery rightResourcesQuery=null;
	
	private String leftResourcesQueryName=null;
	private String rightResourcesQueryName=null;
	
	private String referenceExplanationProperty=""; //TODO set default to rdfs:subClassOf
	OWLOntology[] referenceOntologies=new OWLOntology[0];
	
	protected String assertedRelationString=null;
	//protected Property genericStringRelation;
	
	
	protected boolean filterString=true;
	protected boolean dropLang=true; 	
	

	
	protected Resource[] leftResources=null;
	protected Resource[] rightResources=null;
	
	//protected String[][] leftResourcesMergedString=null;
	//protected String[][] rightResourcesMergedString=null;
	
	
	SimpleAlignment result;
	

	
	int minSimilarity=70;
	int maxDistance=20;
	
	
	

	
	
	private void clearMethods() {
		exactMatch=false;
		wordsOverlap=false;
		wordsLeftInclude=false;
	}
	
	
	
	
	
	
	
	

	
	
	
	public ExpStringMatcher() {
		super();
		//myMatchingGraph=ModelFactory.createDefaultModel();
		
		leftPropertyList=new ArrayList<String>();
		rightPropertyList=new ArrayList<String>();
		referenceOntologyListNames=new ArrayList<String>();
		//wordsHashTable=new Hashtable<String, Integer>();
		
		name= "expstringmatcher";
		type=FrameworkTypes.MATCHER;
		
	
		
		description=new String();
		description+="<description><name>ExpStringMatcher</name>" +
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
		else if(paramName.equals("reset")) {			
			reset();
			return true;
		}
		else if(paramName.equals("setLeftQuery")) {
			if(paramValue!=null) {
				leftResourcesQueryName=paramValue;
				return true;
			}
			else return false;
		}
		else if(paramName.equals("setRightQuery")) {
			if(paramValue!=null) {
				rightResourcesQueryName=paramValue;
				return true;
			}
			else return false;
			
		}
		else if(paramName.equals("addReferenceOntology")) {
			referenceOntologyListNames.add(paramValue);
			return true;
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
			
			else return false;
			
		}
		else if(paramName.equals("minSimilarity")) {
			minSimilarity=Integer.parseInt(paramValue);
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
		myMatchingGraph.removeAll();
		leftPropertyList=new ArrayList<String>();
		rightPropertyList=new ArrayList<String>();
		referenceOntologyListNames=new ArrayList<String>();
		referenceOntologies=new OWLOntology[0];
		leftResourcesQuery=null;
		rightResourcesQuery=null;
		assertedRelationString=null;
		//wordsHashTable=new Hashtable<String,Integer>();
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
	protected Property[] filterProp(Property[] properties, Model myModel) {
		notifyDebugMessage("<message>Checking if properties exists in model</message>");
		int ok=0;
		for(int i=0;i<properties.length;i++) {
			
			if(myModel.contains(null,properties[i])) {
				notifyDebugMessage("<message>Found "+properties[i]+"</message>");
				ok++;
			}
			else {
				notifyDebugMessage("<message>"+properties[i]+" not found in model</message>");
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
			leftNetwork=(RDFNetwork) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of RDFNetwork for "+myOnto.getName());
		}
		
	}

	
	public void setRightArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			rightNetwork=(RDFNetwork) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of RDFNetwork for "+myOnto.getName());
		}		
	}
	
	
	public void startProcess()  {
		if (checkData())
			if(prepareData())
				startComputation();		
	}
	
	private boolean checkData()  {
		notifyProgressMessage("<message>Checking data</message>");
		
		
		
		if(exactMatch==false &&
		
		wordsOverlap==false &&
		wordsLeftInclude==false 
		
		) {
			notifyDebugMessage("<Message>Opting for default method: ExtactStringMatch</Message>");	
			wordsOverlap=true;
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
		
		//Check that the queries exist.
		if(leftResourcesQueryName==null) {
			notifyErrorMessage("<message>No left query variable selected</message>");
			return false;
		}
		if(rightResourcesQueryName==null) {
			notifyErrorMessage("<message>No right query variable selected</message>");
			return false;
		}
		
		
		if(!BaseBeat.getOm().contains(leftResourcesQueryName)) {
			notifyErrorMessage("<message>Unknown variable "+leftResourcesQueryName+"</message>");
			return false;
		}
		if(!BaseBeat.getOm().contains(rightResourcesQueryName)) {
			notifyErrorMessage("<message>Unknown variable "+rightResourcesQueryName+"</message>");
			return false;
		}
		
		FrameworkInformationEntity leftResourcesQueryEntity=BaseBeat.getOm().get(leftResourcesQueryName);
		if(leftResourcesQueryEntity==null) {
			notifyErrorMessage("<message>Unable to recover variable: "+leftResourcesQueryName+"</message>");
			return false;
		}
		FrameworkInformationEntity rightResourcesQueryEntity=BaseBeat.getOm().get(rightResourcesQueryName);
		if(rightResourcesQueryEntity==null) {
			notifyErrorMessage("<message>Unable to recover variable: "+rightResourcesQueryName+"</message>");
			return false;
		}
		
		
		leftResourcesQuery=BaseBeat.getOm().getFrameworkQuery(leftResourcesQueryEntity);
		rightResourcesQuery=BaseBeat.getOm().getFrameworkQuery(rightResourcesQueryEntity);
		
		if(leftResourcesQuery==null) {
			notifyErrorMessage("<message>Unable to recover query from variable: "+leftResourcesQueryName+"</message>");
			return false;
		}
		if(rightResourcesQuery==null) {
			notifyErrorMessage("<message>Unable to recover query from variable: "+rightResourcesQueryName+"</message>");
			return false;
		}
		
		
		
		if(!leftNetwork.makeRDFQueryAsResourcesArrayInModel(leftResourcesQuery,myMatchingGraph,side,leftSide)) {
			notifyErrorMessage("<message>Something went wrong in query</message>");
			return false;
		}
		notifyProgressMessage("<message>Size od my mapping model: "+myMatchingGraph.size()+" statements</message>");

		if(!rightNetwork.makeRDFQueryAsResourcesArrayInModel(rightResourcesQuery,myMatchingGraph,side,rightSide)){
			notifyErrorMessage("<message>Right query yielded null!!</message>");
			return false;
		}
		notifyProgressMessage("<message>Size od my mapping model: "+myMatchingGraph.size()+" statements</message>");

		
		//////////
		
		//Build list of resources
		//Modify expand Properties
		
		
		
		leftPropertyArray=expandProperties(leftPropertyList);
		rightPropertyArray=expandProperties(rightPropertyList);
		notifyDebugMessage("<message>Properties expanded. Left="+leftPropertyArray.length+", Right="+rightPropertyArray.length+" </message>");
		leftPropertyArray=filterProp(leftPropertyArray, leftNetwork.getJenaModel(false));
		rightPropertyArray=filterProp(rightPropertyArray, rightNetwork.getJenaModel(false));
		
		notifyDebugMessage("<message>Properties filtered</message>");
		/** TODO to port
		if(referenceOntologyListNames!=null) {
			
			if(referenceOntologyListNames.size()>0) {
				useReference=true;
				int i=0;
				notifyDebugMessage("<message>Checking that the reference ontologies exist</message>");
				ArrayList<OWLOntology> tempList=new ArrayList<OWLOntology>();
				//referenceOntologies=new OWLOntology[referenceOntologyListNames.size()];
				while(i<referenceOntologyListNames.size()) {
					FrameworkInformationEntity tempItem=CoreComponents.getMemory().get(referenceOntologyListNames.get(i));
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
		*/
		return true;
	}
	
	
	
	
	private boolean prepareData() {
		result=new SimpleAlignment();
		result.setLeftName(leftNetwork.getName());
		result.setRightName(rightNetwork.getName());
		int tickLimit=1000;
		Model om1=leftNetwork.getJenaModel(false);
		Model om2=rightNetwork.getJenaModel(false);
		
		//fill the arrays...
		
		//Resource[] leftResourcesT=new Resource[1];
		
		ResIterator leftResIter= myMatchingGraph.listSubjectsWithProperty(side,leftSide);
		List<Resource> leftList=leftResIter.toList();
		leftResources=new Resource[leftList.size()];
		for(int i=0;i<leftList.size();i++) {
			leftResources[i]=leftList.get(i);
		}
		
		
		//rightResources=new Resource[1];
		ResIterator rightResIter= myMatchingGraph.listSubjectsWithProperty(side,rightSide);
		List<Resource> rightList=rightResIter.toList();
		rightResources=new Resource[rightList.size()];
		for(int i=0;i<rightList.size();i++) {
			rightResources[i]=rightList.get(i);
		}
		
		
		
		notifyDebugMessage("<message>N.of classes: "+leftResources.length+ " (left), "+rightResources.length+" (right)</message>");

		
		tickLimit=leftResources.length/100;
		int leftcounter=0;
		for(int cn=0;cn<leftResources.length;cn++) {
			for(int pn=0;pn<leftPropertyArray.length;pn++) {
				NodeIterator striter= om1.listObjectsOfProperty(leftResources[cn], leftPropertyArray[pn]);
				while(striter.hasNext()) {
					RDFNode node=striter.nextNode();
					if(node.isLiteral()) {
						String literal=((Literal)node).getString();
						//System.out.println("my Word: \""+literal+"\"");
						String words[]=literal.split(" ");
						if(words!=null) {
							for(int i=0;i<words.length;i++) {
								Resource myWord=myMatchingGraph.createResource(beatWordsPrefix+words[i]);
								//System.out.println(myWord);
								myMatchingGraph.add(leftResources[cn],hasWord,myWord);
								myMatchingGraph.add(myWord, hasType, wordType);
							}
						}
						leftcounter+=1;
					}
				}
			}
			if(cn%tickLimit==0) notifyProgressStatus(0, cn, leftResources.length);
		}
		
		tickLimit=rightResources.length/100;
		int rightcounter=0;
		for(int cn=0;cn<rightResources.length;cn++) {
			for(int pn=0;pn<rightPropertyArray.length;pn++) {
				NodeIterator striter= om2.listObjectsOfProperty(rightResources[cn], rightPropertyArray[pn]);
				while(striter.hasNext()) {
					RDFNode node=striter.nextNode();
					if(node.isLiteral()) {
						String literal=((Literal)node).getString();
						String words[]=literal.split(" ");
						if(words!=null) {
							for(int i=0;i<words.length;i++) {
								Resource myWord=myMatchingGraph.createResource(beatWordsPrefix+words[i]);
								myMatchingGraph.add(rightResources[cn],hasWord,myWord);
								myMatchingGraph.add(myWord, hasType, wordType);
							}
						}
						rightcounter+=1;
					}
					    //strSet.add(filterString(node.toString()));
				}
			}
			if(cn%tickLimit==0) notifyProgressStatus(0, cn, rightResources.length);
		}
		notifyDebugMessage("<message>N.of strings processed: "+leftcounter+" (left), " +
					rightcounter+ " (right). My Model got to "+myMatchingGraph.size()+" statements</Message>");
		
		return true;
	}
	
	public void startComputation() {
		notifyDebugMessage("<message>Starting a word by word analysis</Message>");
		int wordCounter=0;
		int tickLimit=1000;
		ResIterator wordIterator= myMatchingGraph.listSubjectsWithProperty(hasType,wordType);
		Set<Resource> wordSet=wordIterator.toSet();
		//Hashtable score =new Hashtable<Long,Integer>(10000);
		int nOfWords=wordSet.size();
		System.out.println("Number of words: "+nOfWords);
		Iterator<Resource> wordIterator2=wordSet.iterator();
		wordCounter++;
		while(wordIterator2.hasNext()) {
			Resource wordURI=wordIterator2.next();
			System.out.println("Processing "+wordURI+" #"+wordCounter);
			ResIterator resRes=myMatchingGraph.listSubjectsWithProperty(hasWord, wordURI);
			ArrayList<Resource>leftResourcesPerWord=new ArrayList<Resource>();
			ArrayList<Resource>rightResourcesPerWord=new ArrayList<Resource>();
			while(resRes.hasNext()) {
				Resource tempRes=resRes.nextResource();
				if(myMatchingGraph.contains(tempRes,side,leftSide)) {
					leftResourcesPerWord.add(tempRes);
				}
				if(myMatchingGraph.contains(tempRes,side,rightSide)) {
					rightResourcesPerWord.add(tempRes);
				}
				
			}
			//System.out.println("Left: "+leftResourcesPerWord.size());
			//System.out.println("Right: "+rightResourcesPerWord.size());
			//Post analysis:
			for(int i=0;i<leftResourcesPerWord.size();i++) {
				for(int j=0;j<rightResourcesPerWord.size();j++) {
					Resource leftR=leftResourcesPerWord.get(i);
					Resource rightR=rightResourcesPerWord.get(j);
					Statement myMapStat=myMatchingGraph.createStatement(leftR, mapProp, rightR);
					ReifiedStatement myMapStatReif=myMatchingGraph.createReifiedStatement(myMapStat);
					StmtIterator countI =myMapStatReif.listProperties(wordCountProp);
					int count=0;
					if(countI.hasNext()) {
						Statement st=countI.nextStatement();
						count=st.getInt();
						myMapStatReif.removeAll(wordCountProp);
						count++;
						
						myMatchingGraph.add(myMapStatReif,wordCountProp,ResourceFactory.createPlainLiteral(Integer.toString(count)));
						
					}
					else {
						myMatchingGraph.add(myMapStatReif,wordCountProp,ResourceFactory.createPlainLiteral("1"));
						
					}
					System.out.println(leftR+" "+rightR+" "+count);
					
					
				}
			}
			
			wordCounter++;
		
		}
		
			
								
						
				
		//result.addJenaSimpleAlignment(leftResources[lc],rightResources[rc], assertedPropery, result2.confidence, result2.type, result2.explanation, null);
		
			
	}
	
	public FrameworkInformationEntity getResult() {
		return result;
	}
	
	

	

}

