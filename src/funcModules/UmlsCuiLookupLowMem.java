package funcModules;


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
 * 
 * Modification "memory doesn't grow on tree" by Andrea Splendiani, 2008
 **/



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkInformationEntity;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;


/**
 * TODO insert description
 * @author beisswanger
 */
public class UmlsCuiLookupLowMem  extends AbstractProcessImpl implements EnrichmentProcess{
	private String inputPropertyString="http://www.w3.org/2000/01/rdf-schema#label";	//default
	private String outputPropertyString="http://www.bootstrep.eu/analysis#umlsCui";	//default
	private String mrconFileString="externalData/MRCON";
	//private Map<String, HashSet<String>> stringCuiMap = null;	
	private RDFNetwork myNetwork=null;
	
	private Hashtable<String,HashSet<Resource>> ontologyHashTable=null;
	
	public UmlsCuiLookupLowMem() {
		super();
		this.name="umlscuilookuplm";
		this.type=FrameworkTypes.ENRICHER;
		//this.stringCuiMap = new HashMap<String,HashSet<String>>();
		this.ontologyHashTable=new Hashtable<String, HashSet<Resource>>();
		this.description="<description><name>UmlsCuiLookup-LowMemory</name>" +
		"<descriptionText>Adds UMLS concept identifiers (CUIs) to class description<br>" +
		"Adds zero to many UMLS CUIs to ontology classes, depending on the class designators specified as inputProperty.<br>" +
		"Note that for each class designator preprocessing operations such as normalization should be computed in a previous step.</descriptionText>" +
		"<parameters><table>"+
		"<tr><td>Name</td><td>Value</td><td>Default</td></tr>"+
		"<tr><td>inputProperty</td><td>property URI</td><td>rdfs:label</td></tr>"+
		"<tr><td>outputProperty</td><td>property URI</td><td>http://www.bootstrep.eu/analysis#umlsCui</td></tr>"+
		"<tr><td>mrconFile</td><td>path to UMLS MRCON file</td><td>src/externalData/MRCON</td></tr>"+
		"</table></parameters>" +
		"</description>";
	}		
	
	
	


	private void prepareOntologyHashTable(){	
		notifyProgressMessage("<message>Setting up memory hashtable</message>");
		ontologyHashTable=new Hashtable<String, HashSet<Resource>>();
		Property inputProperty=ResourceFactory.createProperty(inputPropertyString);
		int termCounter=0;	
		int totalCounter=0;
		StmtIterator statementList=myNetwork.getJenaModel(true).listStatements(null, inputProperty, (Literal)null);
		while(statementList.hasNext()) {
			Statement stat=statementList.nextStatement();
			RDFNode termNode=stat.getObject();
			Resource sourceNode=stat.getSubject();
			String literalToStore=null;
			if(termNode.isLiteral()) literalToStore=((Literal)termNode).getString().toLowerCase();
			if(literalToStore!=null) {
				HashSet<Resource> resourcesForLiteral=ontologyHashTable.get(literalToStore);
				if(resourcesForLiteral==null) {
					resourcesForLiteral=new HashSet<Resource>();
					ontologyHashTable.put(literalToStore,resourcesForLiteral);
					termCounter++;
				}
				resourcesForLiteral.add(sourceNode);
				totalCounter++;
			}
		}
		notifyProgressMessage("<message>"+totalCounter+" non distinct resources where added to "+termCounter+" terms</message>");
		notifyProgressMessage("<message>Done</message>");
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
		prepareOntologyHashTable();
		notifyUserMessage("<message>Reading UMLS file</message>");
		
		Property targetProperty=ResourceFactory.createProperty(outputPropertyString);	
		File file = new File(mrconFileString);
		int linecounter=0;
		int lineBlockCounter=0;
		int matchCounter=0;
		if (file.isFile()){		
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
			    while(br.ready()) {		
			    	linecounter++;
			    	String line = br.readLine();
			    	String[] array = line.split("\\|");
			    	String cui = array[0];
			    	String string = array[6].toLowerCase();
			    	//System.out.println(string);
			    	HashSet<Resource> possibleSubject=null;
			    	possibleSubject=ontologyHashTable.get(string);
			    	if(possibleSubject!=null) {
			    		Iterator<Resource> resIter=possibleSubject.iterator();
			    		while(resIter.hasNext()) {
			    			Resource subject=resIter.next();
			    			Literal object=ResourceFactory.createTypedLiteral(cui, XSDDatatype.XSDstring);
							
							myNetwork.addJenaStatememnt(subject, targetProperty, object);
							matchCounter++;
							//notifyProgressMessage("<message>"+subject.getURI()+" : \nadded umls cui "+cui+"</message>");
			    		}
			    	}
			    	if(linecounter%1000000==0) {
			    		linecounter=0;
			    		lineBlockCounter++;
			    		notifyProgressMessage("<message>read "+lineBlockCounter+" x M lines</message>");
			    	}  	
			    }
			    br.close();
			    notifyUserMessage("<message>Found "+matchCounter+" matches</message>");
			}
			catch (Exception e) {
				 notifyErrorMessage("<message>Could not locate UMLS data<br/>"+ e.getStackTrace() + "</message>");
					
			}
		}
		else {
			notifyErrorMessage("<message>could not find the MRCON file at </message>" + mrconFileString);
		}
		
		
	}
}