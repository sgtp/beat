/** 
 * UmlsSemTypeLookup.java
 * 
 * Copyright (c) 2008, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: beisswanger
 * 
 * Current version: 1.0
 * Since version:   1.0
 *
 * Creation date: 27.05.2008 
 **/

package funcModules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkInformationEntity;


/**
 * TODO insert description
 * @author beisswanger
 */
public class UmlsSemTypeLookup  extends AbstractProcessImpl implements EnrichmentProcess{

	private String inputPropertyString="http://www.w3.org/2000/01/rdf-schema#label";	//default
	private String outputPropertyString="http://www.bootstrep.eu/analysis#umlsSemType";	//default
	private String semTypeFileString="externalData/stringSemType";
	private Map<String, HashSet<String>> stringSemTypeMap = new HashMap<String,HashSet<String>>();	
	private RDFNetwork myNetwork=null;
	
	public UmlsSemTypeLookup() {
		super();
		this.name="umlssemtypelookup";
		this.type=FrameworkTypes.ENRICHER;
		this.description="<description><name>UmlsSemTypeLookup</name>" +
		"<descriptionText>Adds UMLS Semantic Network types to class description<br>" +
		"Adds zero to many semantic types from the UMLS Semantic Network to ontology classes, " +
		"depending on the class designators specified as inputProperty.<br>" +
		"Note that for each designator preprocessing operations such as normalization should " +
		"be computed in a previous step.</descriptionText>" +
		"<parameters><table>"+
		"<tr><td>Name</td><td>Value</td><td>Default</td></tr>"+
		"<tr><td>inputProperty</td><td>property URI</td><td>rdfs:label</td></tr>"+
		"<tr><td>outputProperty</td><td>property URI</td><td>http://www.bootstrep.eu/analysis#umlsSemType</td></tr>"+
		"<tr><td>semTypeFile</td><td>path to semTypeFile</td><td>externalData/stringSemType</td></tr>"+
		"</table></parameters>" +
		"</description>";
	}	
	
	/**
	 * 
	 */
	private void readInSemTypeFile(){	
		File file = new File(semTypeFileString);
		if (file.isFile()){		
			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    while(br.ready()) {		
			    	String line = br.readLine();
			    	String[] array = line.split("\t");
			    	String semType = array[1];
			    	String string = array[0].toLowerCase();
			    	HashSet<String> semTypeSet = new HashSet<String>();	
			    	//fill stringSemTypeMap with strings as keys and all semTypes this string is associated with as values (in a hashset)
			    	if (stringSemTypeMap.get(string)==null){
			    		semTypeSet.add(semType);
			    		stringSemTypeMap.put(string, semTypeSet);
			    	}
			    	else{
			    		semTypeSet.addAll(stringSemTypeMap.get(string));
			    		semTypeSet.add(semType);			    		
			    		stringSemTypeMap.put(string, semTypeSet);			    
			    	}			    	
			    }
			    br.close();
			    notifyUserMessage("<message>created UMLS SemType list</message>");
			}
			catch (Exception e) {
				e.printStackTrace();	
			}
		}
		else {
			notifyErrorMessage("<message>could not find the stringSemType file at </message>" + semTypeFileString);
		}
	}

	private HashSet<String> lookUpUmlsSemTypes(String string){
		HashSet<String> SemTypeSet = null;
		if (stringSemTypeMap.isEmpty()){
			readInSemTypeFile();
		}
		if (stringSemTypeMap.containsKey(string)){
			SemTypeSet = stringSemTypeMap.get(string);
		}
		return SemTypeSet;
	}
	
	
	public void setArgument(FrameworkInformationEntity infoEntity) throws WrongType {
		myNetwork=BaseBeat.getOm().getRDFNetwork(infoEntity);
	}
	

	
	public void reset() {
		//Nothing to be done.		
	}

	
	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equals("inputProperty")) {
			inputPropertyString=paramValue;
			return true;
		}
		else if(paramName.equals("outputProperty")) {
			outputPropertyString=paramValue;
			return true;
		}
		else if(paramName.equals("semTypeFile")) {
			semTypeFileString=paramValue;
			return true;
		}
		else return false;
	}

	
	public void startProcess() {
		if(myNetwork==null) {
			notifyDebugMessage("<message>RDFNetwork undefined</message>");
		}		
		notifyProgressMessage("<message>Starting UMLS SemType lookup</message>");
		Property inputProperty=ResourceFactory.createProperty(inputPropertyString);
		Property targetProperty=ResourceFactory.createProperty(outputPropertyString);	
		StmtIterator statementList=myNetwork.getJenaModel(true).listStatements(null, inputProperty, (Literal)null);
		while(statementList.hasNext()) {
			Statement stat=statementList.nextStatement();
			RDFNode termNode=stat.getObject();
			Resource sourceNode=stat.getSubject();
			String stringToBeProcessed=null;
			HashSet<String> semTypeSet=null;
			if(termNode.isLiteral()) stringToBeProcessed=((Literal)termNode).getString();
			if(stringToBeProcessed!=null) {
				//look up which SemTypes are in stringSemTypeMap for the string termString
				semTypeSet=lookUpUmlsSemTypes(stringToBeProcessed.toLowerCase());
				if(semTypeSet!=null) {
					for (String semType : semTypeSet){
						Literal object=ResourceFactory.createTypedLiteral(semType, XSDDatatype.XSDstring);
						//if(!((Model)myNetwork).contains(sourceNode, targetProperty, object)){
							myNetwork.addJenaStatememnt(sourceNode, targetProperty, object);
							System.out.println(sourceNode.getURI()+" : \nadded umls SemType "+semType);
						//}						
					}
				}
			}
		}
		notifyProgressMessage("<message>Done</message>");
	}
}

