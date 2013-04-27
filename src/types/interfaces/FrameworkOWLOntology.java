/**
 * OntoEntityInterface.java
 * An ontology (somehow confused, at the moment, with an RDF model)
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 * TODO see whether differentiate between OWLOntology and RDFModels (the latter should superclass alignments)
 * 
 * WARNING: WILL REFACTOR (relation between OntEntity, OntEntityInterface, OWLOntology, Alignemnt should be refined)
 * TODO see above
 * 
 */
package types.interfaces;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;

public interface FrameworkOWLOntology {
	
	public OntModel getJenaOntModel();
	
	
	public void setOWLInference();
	public void setRDFSInference();
	public void setTransitiveInference();
	public void setNoInference();
	public void collapseEntailmentsToBaseModel();
	
	
	public  OntClass[] getJenaClassesArray();
	
	
	public  ObjectProperty[] getJenaObjectPropertiesArray();
	public  DatatypeProperty[] getJenaDatatypePropertiesArray();
	public  AnnotationProperty[] getJenaAnnotationPropertiesArray();
	
	public  Property[] getJenaPropertiesArray();
	
	
	public  Individual[] getJenaIndividualsArray() ;
	
	//public String getStatistics();
	
	//public String getClassesListDescription();
	
	//public String getIndividualsListDescription();
	
	
}
