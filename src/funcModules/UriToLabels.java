/**
 * UriToLAbels.java
 * Converts URI string (local part) into labels. For ontologies as GRO.
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 */
package funcModules;

import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.OWLOntology;
import types.WrongType;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.EnrichmentProcess;
import Utils.TimeCounter;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class UriToLabels extends AbstractProcessImpl implements EnrichmentProcess {
	OWLOntology myOntology;
	String propertyString="http://www.w3.org/2000/01/rdf-schema#label";
	boolean classes=true;
	boolean individuals=true;
	int resolutionTick=1000;
	
	
	public UriToLabels() {
		super();
		name="uritolabels";
		type=FrameworkTypes.ENRICHER;
		description="<description>" +
				"<descriptionText>Add the local name of URIs as a RDF property, for classes and/or individuals</descriptionText>\n" +
				"<argument>OWLOntology</argument>"+
				"<parameters>" +
				"<table>" +
				"<tr><td>Parameter</td><td>value</td><td>default</td></tr>" +
				"<tr><td>property</td><td>the uri of the property values should be assigned to</td><td> rdfs:label</td></tr>" +
				"<tr><td>treatClasses</td><td>true/false</td><td>apply to classes (default: true)</td></tr>" +
				"<tr><td>treatIndividuals</td><td>true/false</td><td>apply to individuals (default: true)</td></tr>" +
				"</table></parameters></description>";
		
	}

	public void setArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			myOntology=(OWLOntology)myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of OWLOntology");
		}
	}

	public boolean isMatcher() {
		return false;
	}

	public boolean isTransformer() {
		return true;
	}

	public void reset() {
		propertyString="http://www.w3.org/2000/01/rdf-schema#label";
		classes=true;
		individuals=true;
		

	}

	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equalsIgnoreCase("property")) {
			propertyString=paramValue;
			notifyDebugMessage("<Message>Setting property to "+paramValue+"</Message>");
			return true;
		}
		else if(paramName.equalsIgnoreCase("treatClasses")) {
			if(paramValue.equalsIgnoreCase("true")) {
				classes=true;
				
			}
			else if(paramValue.equalsIgnoreCase("false")) classes=false;
			else {
				notifyDebugMessage("<Message>Unrecognized parameter "+paramValue+" for option "+paramName+"</Message>");
				return false;
			}
			notifyDebugMessage("<Message>Setting classes to "+paramValue+"</Message>");
		}
		else if(paramName.equalsIgnoreCase("treatIndividuals")) {
			if(paramValue.equalsIgnoreCase("true")) {
				individuals=true;
				
			}
			else if(paramValue.equalsIgnoreCase("false")) individuals=false;
			else {
				notifyDebugMessage("<Message>Unrecognized parameter "+paramValue+" for option "+paramName+"</Message>");
				return false;
			}
			notifyDebugMessage("<Message>Setting individuals to "+paramValue+"</Message>");
		}
		
		else {
			notifyDebugMessage("<Message>Unrecognized parameter "+paramName+"</Message>");
			return false;
		}
		return true;
		
		
		
	}

	public void startProcess() {
		if(myOntology==null) {
			notifyDebugMessage("<Message>"+name+" started without an ontology set </Message>");
			return;
		}
		isAvailable=false;
		myOntology.setIsAvailble(false);
		TimeCounter tc=TimeCounter.getTimeCounter();
		Property myProperty=ResourceFactory.createProperty(propertyString);
		int c=0;
		int i=0;
		if(classes) {
			OntClass[] myClasses=myOntology.getJenaClassesArray();
			int tk=0;
			for(;c<myClasses.length;c++ ) {
				String annot=myClasses[c].getLocalName();
				if(annot!=null) {
					Literal object=ResourceFactory.createTypedLiteral(annot, XSDDatatype.XSDstring);
					myOntology.addJenaStatememnt(myClasses[c],myProperty,object);
				}
				tk++;
				if(tk==resolutionTick) {
					tk=0;
					notifyProgressStatus(0, c, myClasses.length);
				}
			}
			
		}
		notifyProgressMessage("<Message> processed "+c+" labels to classes</Message>");
		
		if(individuals) {
			Individual[] myIndividuals=myOntology.getJenaIndividualsArray();
			int tk=0;
			
			for(;i<myIndividuals.length;i++ ) {
				
				String annot=myIndividuals[i].getLocalName();
				if(annot!=null) {
					Literal object=ResourceFactory.createTypedLiteral(annot, XSDDatatype.XSDstring);
					myOntology.addJenaStatememnt(myIndividuals[i],myProperty,object);
				}
				tk++;
				if(tk==resolutionTick) {
					tk=0;
					notifyProgressStatus(0, i, myIndividuals.length);
				}
			}
		}
		notifyProgressMessage("<Message> processed "+i+" labels to individuals</Message>");
		notifyProgressMessage("<message>Loaded. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");

		
		isAvailable=true;
		myOntology.setIsAvailble(true);
		
	}



}
