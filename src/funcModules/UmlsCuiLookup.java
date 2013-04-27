/** 
 * UMLSSynReader.java
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
public class UmlsCuiLookup  extends AbstractProcessImpl implements EnrichmentProcess{
	private String inputPropertyString="http://www.w3.org/2000/01/rdf-schema#label";	//default
	private String outputPropertyString="http://www.bootstrep.eu/analysis#umlsCui";	//default
	private String mrconFileString="externalData/MRCON";
	private Map<String, HashSet<String>> stringCuiMap = null;	
	private RDFNetwork myNetwork=null;
	
	public UmlsCuiLookup() {
		super();
		this.name="umlscuilookup";
		this.type=FrameworkTypes.ENRICHER;
		this.stringCuiMap = new HashMap<String,HashSet<String>>();
		this.description="<description><name>UmlsCuiLookup</name>" +
		"<descriptionText>Adds UMLS concept identifiers (CUIs) to class description<br>" +
		"Adds zero to many UMLS CUIs to ontology classes, depending on the class designators specified as inputProperty.<br>" +
		"Note that for each class designator preprocessing operations such as normalization should be computed in a previous step.</descriptionText>" +
		"<parameters><table>"+
		"<tr><td>Name</td><td>Value</td><td>Default</td></tr>"+
		"<tr><td>inputProperty</td><td>property URI</td><td>rdfs:label</td></tr>"+
		"<tr><td>outputProperty</td><td>property URI</td><td>http://www.bootstrep.eu/analysis#umlsCui</td></tr>"+
		"<tr><td>mrconFile</td><td>path to UMLS MRCON file</td><td>externalData/MRCON</td></tr>"+
		"</table></parameters>" +
		"</description>";
	}		
	
	
	/**
	 * 
	 */
	private void readInMRCONFile(){	
		File file = new File(mrconFileString);
		if (file.isFile()){		
			try {
				notifyUserMessage("<message>Starting UMLS CUI list analysis</message>");
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    while(br.ready()) {		
			    	String line = br.readLine();
			    	String[] array = line.split("\\|");
			    	String cui = array[0];
			    	String string = array[6].toLowerCase();
			    	
			    	//fill stringCuiMap with strings as keys and all cuis this string is associated with as values (in a hashset)
			    	if (stringCuiMap.get(string)==null){
			    		HashSet<String> newCuiSet = new HashSet<String>();	
			    		newCuiSet.add(cui);
			    		stringCuiMap.put(string, newCuiSet);
			    	}
			    	else{
			    		HashSet<String> oldCuiSet=stringCuiMap.get(string); 
			    		oldCuiSet.add(cui);			    		
			    		stringCuiMap.put(string, oldCuiSet);			    
			    	}			    	
			    }
			    br.close();
			    notifyUserMessage("<message>created UMLS CUI list</message>");
			}
			catch (Exception e) {
				 notifyErrorMessage("<message>Could not locate UMLS data<br/>"+ e.getStackTrace() + "</message>");
					
			}
		}
		else {
			notifyErrorMessage("<message>could not find the MRCON file at </message>" + mrconFileString);
		}
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
		else if(paramName.equals("mrconFile")) {
			mrconFileString=paramValue;
			return true;
		}
		else return false;
	}

	
	public void startProcess() {
		if(myNetwork==null) {
			notifyDebugMessage("<message>RDFNetwork undefined</message>");
		}		
		if (stringCuiMap.isEmpty()){
			readInMRCONFile();
		}
		notifyProgressMessage("<message>Starting UMLS CUI lookup</message>");
		Property inputProperty=ResourceFactory.createProperty(inputPropertyString);
		Property targetProperty=ResourceFactory.createProperty(outputPropertyString);	
		StmtIterator statementList=myNetwork.getJenaModel(true).listStatements(null, inputProperty, (Literal)null);
		while(statementList.hasNext()) {
			Statement stat=statementList.nextStatement();
			RDFNode termNode=stat.getObject();
			Resource sourceNode=stat.getSubject();
			String stringToBeProcessed=null;
			HashSet<String> cuiSet=null;
			if(termNode.isLiteral()) stringToBeProcessed=((Literal)termNode).getString();
			if(stringToBeProcessed!=null) {
				//look up which CUIs are in stringCuiMap for the string termString
				cuiSet=stringCuiMap.get(stringToBeProcessed.toLowerCase());
				if(cuiSet!=null) {
					for (String cui : cuiSet){
						Literal object=ResourceFactory.createTypedLiteral(cui, XSDDatatype.XSDstring);
						//if(!((Model)myNetwork).contains(sourceNode, targetProperty, object)){
							myNetwork.addJenaStatememnt(sourceNode, targetProperty, object);
							System.out.println(sourceNode.getURI()+" : \nadded umls cui "+cui);
						//}						
					}
				}
			}
		}
		notifyProgressMessage("<message>Done</message>");
	}
}

