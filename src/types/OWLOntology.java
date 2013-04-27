/**
 * OWLOntology.java
 * An OWL ontology, somehow confused with an RDF model, at the moment.
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL 
 * 
 * TODO define what's poper of OWL and what's not. Differentiate respect to RDFModel.
 * 
 * WARNING: WILL REFACTOR (relation between OntEntity, OntEntityInterface, OWLOntology, Alignemnt should be refined)
 * TODO see above
 * 
 * TODO switch to Java5 container
 * 
 * TODO refactor OWLOntology interface (some methods are dead)
 * 
 */
package types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import system.BaseBeat;
import system.Config;
import types.interfaces.FrameworkOWLOntology;
import Utils.TimeCounter;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OWLOntology extends RDFNetwork  implements FrameworkOWLOntology {
	
	protected boolean supportsInference=false;
	protected boolean RDFSInference=false;
	protected boolean OWLInference=false;
	protected boolean TransInference=false;
	
	protected OntModel myModel=null; 
	
	public OWLOntology() {
		super();
		this.name="unnamed";
		this.uri="not-declared";
		this.type=FrameworkTypes.OWLONTOLOGY;
		//type="owl-onto";
		isVolatile=true;
		baseModel=ModelFactory.createDefaultModel();
		applyInference();
		isAvailable=false;
		
	}
	public OWLOntology(String name, String uri) {
		super();
		this.name=name;
		this.uri=uri;
		type=FrameworkTypes.OWLONTOLOGY;
		isVolatile=true;
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>Loading ontology from URI "+uri+" in memory</message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		baseModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		baseModel.read(uri);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>Loaded. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		applyInference();
		isAvailable=true;
	}
	

	/*
	public OWLOntology(String currentName, ModelRDB open) {
		super();
		this.name=currentName;
		uri="to_be_recovered!";
		isVolatile=false;
		this.type=FrameworkTypes.OWLONTOLOGY;
		
		CoreComponents.getSystemNotifier().notifyProgressMessage("<message>Recovering a DB model (name: "+name+")</message>"); 
		
		baseModel=open;
		
		
		applyInference();
		isAvailable=true;
		
	}
	*/
	
	public OWLOntology(OWLOntology[] ontologyList) {
		super();
		this.uri="";
		isVolatile=true;
		this.type=FrameworkTypes.OWLONTOLOGY;
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>creating union of ontologies</Message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		baseModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		
		for(int i=0;i<ontologyList.length;i++) {
			System.out.println("adding "+ontologyList[i].getName());
			baseModel=baseModel.union(ontologyList[i].getJenaModel(false));
			BaseBeat.getSystemNotifier().notifyProgressStatus(0, i+1, ontologyList.length);
		}
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		//setHasLuceneIndex(false);
		applyInference();
		isAvailable=true;
	}
	
	public void delete() {
		myModel.removeAll();
		super.delete();
	}
	public Model getJenaModel(boolean considerInference) {
		if(considerInference) return myModel;
		else return myModel.getBaseModel();
		
	}
	public OntModel getJenaOntModel() {
		return myModel;
		
		
	}
	/*
	public OntModel getModel() {
		return myModel;
	}
	public OntModel getOntModel() {
		return myModel;
	}
	*/
	
	

	public OWLOntology(String varname, String uri, boolean directlyInDB) {
		super();
		this.name=name;
		this.uri=uri;
		this.type=FrameworkTypes.OWLONTOLOGY;
		isVolatile=false;
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>Loading ontology from URI "+uri+" in database </message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		
		
		baseModel.read(uri);
		
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>Loaded. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		applyInference();
		isAvailable=true;
	}
	
	
	public OWLOntology(RDFNetwork rdfNetwork) {
		setName(rdfNetwork.name);
		setURI(rdfNetwork.uri);
		isVolatile=rdfNetwork.isVolatile;
		baseModel=rdfNetwork.baseModel;
		type=FrameworkTypes.OWLONTOLOGY;
		setNoInference();
		applyInference();
		
		isAvailable=true;
		
		
	}
	private boolean supportsInference() {
		return supportsInference;
	}
	
	public FrameworkInformationEntityNotifierImpl getCopyInMemory() {
		//OntModel newModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,myModel.getBaseModel());
		OWLOntology newOnto=new OWLOntology();
		newOnto.setName(this.name);
		newOnto.setURI(this.uri);
		newOnto.baseModel.add(baseModel);
		if(OWLInference) newOnto.setOWLInference();
		if(TransInference) newOnto.setTransitiveInference();
		if(RDFSInference) newOnto.setRDFSInference(); 
		if(!supportsInference) newOnto.setNoInference();
		newOnto.applyInference();
		return newOnto;
	}
	
	public void applyInference() {
		if(TransInference) {
			notifyProgressMessage("<message>Wrapping "+name+" with transitive (Jena) inference</message>"); 
			TimeCounter tc=TimeCounter.getTimeCounter();
			myModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_TRANS_INF,baseModel);
			myModel.prepare();
			notifyProgressMessage("<message>Done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
			isAvailable=true;
		}
		else if(RDFSInference) {
			notifyProgressMessage("<message>Wrapping "+name+" with RDFS (Jena) inference</message>"); 
			TimeCounter tc=TimeCounter.getTimeCounter();
			myModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF,baseModel);
			myModel.prepare();
			notifyProgressMessage("<message>Done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
			isAvailable=true;
			
		}
		else if(OWLInference) {
			notifyProgressMessage("<message>Wrapping "+name+") with OWL inferebnce (Pellet)</message>"); 
			TimeCounter tc=TimeCounter.getTimeCounter();
			myModel=ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC,baseModel);
			myModel.prepare();
			notifyProgressMessage("<message>Done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
			isAvailable=true;
		}
		else {
			notifyProgressMessage("<message>Wrapping "+name+" with no inference</message>"); 
			TimeCounter tc=TimeCounter.getTimeCounter();
			myModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,baseModel);
			myModel.prepare();
			notifyProgressMessage("<message>Done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
			isAvailable=true;
		}
		
	}
	

	public void setNoInference() {
		if(supportsInference=true) notifyWarningMessage("<Message>Inference model changed! Now: none</Message>");
		supportsInference=false;
		RDFSInference=false;
		TransInference=false;
		OWLInference=false;
		
	}

	public void setOWLInference() {
		if(!OWLInference) notifyWarningMessage("<Message>Inference model changed! Now: OWL</Message>");
		supportsInference=true;
		RDFSInference=false;
		TransInference=false;
		OWLInference=true;
		
	}

	public void setRDFSInference() {
		if(!RDFSInference) notifyWarningMessage("<Message>Inference model changed! Now: RDFS</Message>");
		supportsInference=true;
		RDFSInference=true;
		TransInference=false;
		OWLInference=false;
		
	}

	public void setTransitiveInference() {
		if(!TransInference) notifyWarningMessage("<Message>Inference model changed! Now: Transitive</Message>");
		supportsInference=true;
		RDFSInference=false;
		TransInference=true;
		OWLInference=false;
		
	}

	public boolean makePersistent() {
		String thisResource=Config.thisResource;
		String isInternalType=Config.isInternalTypeProperty;
		Resource myRes=ResourceFactory.createResource(thisResource);
		Property myProp=ResourceFactory.createProperty(isInternalType);
		
		StmtIterator oldStats= baseModel.listStatements(myRes, myProp,(RDFNode) null);
		baseModel.remove(oldStats.toList());
		
		
		Literal newValue=ResourceFactory.createTypedLiteral("owl-onto", XSDDatatype.XSDstring);
		baseModel.add(baseModel.createStatement(myRes, myProp, newValue)); 
		
		
		super.makePersistent();
		applyInference();
		return true;
		
	}
	
	
	
	
	public String getSpecificDescription() {
		String desc=super.getSpecificDescription(); 
		desc+="<uri>"+this.uri+"</uri>\n";
		String reasoningDescription="";
		if(!supportsInference) reasoningDescription="none";
		if(RDFSInference) reasoningDescription="RDFS (Jena)";
		if(OWLInference) reasoningDescription="OWL (Pellet)";
		if(TransInference) reasoningDescription="Transitive (Jena)";
		desc+="<reasoningDescription>"+reasoningDescription+"</reasoningDescription>\n";
		//desc+="<luceneSupport>"+hasLuceneIndex()+"</luceneSupport>\n";
		Map myPrefixeMap=myModel.getNsPrefixMap();
		Set prefixes=myPrefixeMap.keySet();
		Iterator i=prefixes.iterator();
		desc+="<prefixMap><table><tr><td>prefix</td><td>uri</td></tr>";
		while(i.hasNext()) {
			String prefix=(String)i.next();
			String uri=(String) myPrefixeMap.get(prefix);
			desc+="<tr><td>"+prefix+"</td><td>"+uri+"</td></tr>";
		}
		desc+="</table></prefixMap>";
		return desc;
		
	}	
	/*
	public String getClassesListDescription() {
		OntClass[] classes=getJenaClassesArray();
		String classList="";
		for(int i=0;i<classes.length;i++) classList+="<class>"+classes[i]+"</class>\n";
		return classList;
	}
	
	public String getIndividualsListDescription() {
		Individual[] individuals=getJenaIndividualsArray();
		String individualList="";
		for(int i=0;i<individuals.length;i++) individualList+="<individual>"+individuals[i]+"</individual>\n";
		return individualList;
	}
	*/
	
	public  OntClass[] getJenaClassesArray() {
		ExtendedIterator classList=myModel.listNamedClasses();
		ArrayList tempList=new ArrayList();
		while(classList.hasNext()) {
			tempList.add(classList.next());
		}
		OntClass[] arrayClassList=new OntClass[0];
		arrayClassList=(OntClass[]) tempList.toArray(arrayClassList);
		return arrayClassList;
	}
	
	
	public  ObjectProperty[] getJenaObjectPropertiesArray() {
		ExtendedIterator propertyList=myModel.listObjectProperties();
		ArrayList tempList=new ArrayList<ObjectProperty>();
		while(propertyList.hasNext()) {
			tempList.add(propertyList.next());
		}
		ObjectProperty[] arrayPropertyList=new ObjectProperty[0];
		arrayPropertyList=(ObjectProperty[]) tempList.toArray(arrayPropertyList);
		return arrayPropertyList;
	}
	public  DatatypeProperty[] getJenaDatatypePropertiesArray() {
		ExtendedIterator propertyList=myModel.listDatatypeProperties();
		ArrayList tempList=new ArrayList();
		while(propertyList.hasNext()) {
			tempList.add(propertyList.next());
		}
		DatatypeProperty[] arrayPropertyList=new DatatypeProperty[0];
		arrayPropertyList=(DatatypeProperty[]) tempList.toArray(arrayPropertyList);
		return arrayPropertyList;
	}
	public  AnnotationProperty[] getJenaAnnotationPropertiesArray() {
		ExtendedIterator propertyList=myModel.listAnnotationProperties();
		ArrayList tempList=new ArrayList();
		while(propertyList.hasNext()) {
			tempList.add(propertyList.next());
		}
		AnnotationProperty[] arrayPropertyList=new AnnotationProperty[0];
		arrayPropertyList=(AnnotationProperty[]) tempList.toArray(arrayPropertyList);
		return arrayPropertyList;
	}
	
	public  Property[] getJenaPropertiesArray() {
		ExtendedIterator propertyList=myModel.listOntProperties();
		ArrayList tempList=new ArrayList();
		while(propertyList.hasNext()) {
			tempList.add(propertyList.next());
		}
		Property[] arrayPropertyList=new Property[0];
		arrayPropertyList=(Property[]) tempList.toArray(arrayPropertyList);
		return arrayPropertyList;
	}
	
	
	public  Individual[] getJenaIndividualsArray() {
		ExtendedIterator individualList=myModel.listIndividuals();
		ArrayList<Individual> tempList=new ArrayList<Individual>();
		while(individualList.hasNext()) {
			tempList.add((Individual) individualList.next());
		}
		Individual[] arrayIndividualList=new Individual[0];
		arrayIndividualList=(Individual[]) tempList.toArray(arrayIndividualList);
		return arrayIndividualList;
	}

	public void collapseEntailmentsToBaseModel() {
		TimeCounter tc=TimeCounter.getTimeCounter();
		notifyProgressMessage("<Message>Collapsing to my info to baseModel</Message>");
		if(isVolatile) {
			notifyDebugMessage("<Message>In memory model</Message>");
			baseModel.add(myModel.listStatements());
			
			
		}
		else {
			notifyDebugMessage("<Message>In db model</Message>");
			baseModel.add(myModel.listStatements());
			
		}
		applyInference();
		notifyProgressMessage("<message>Done. Total time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		
	}
	public void addJenaStatememnt(Resource class1, Property myProperty, RDFNode object) {
		myModel.add(class1,myProperty,object);
		
	}
	
	public void registerPrefix(String pr, String uri) {
		try {
		baseModel.setNsPrefix(pr,uri);
		myModel.setNsPrefix(pr, uri);
		} catch (Exception e) {
			notifyDebugMessage("<Message>Failed trying to register prefix: "+pr+" for "+uri+"</Message>");
		}
		
	}
	
	

	

}
