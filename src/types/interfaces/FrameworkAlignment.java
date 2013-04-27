/**
 * Alignmnet.java
 * The interface of an alignemnt
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 * WARNING: THIS IS GOING TO BE "HEVAILY" REFACTORED.
 * It will be a sublcass of OWLOntology (or RDFModel if this is introduced), implementing additional classes.
 * Persistence will be managed through RDF.
 * Internally, more representations should be possible. URI prop URI (with notes on reification?)
 * URI prop LINK porp URI (eventually)
 * From this it should be possible to derive a transformation to ontology constrains.
 * This should also provide interfaces to the InraAPI or to FOAM (as well as an XML I/O)
 * The problem in having this as a subclass of OWLOntology is that methods like getClass[] makeInference and so on have no effect.
 * 
 * WARNING: WILL REFACTOR (relation between OntEntity, OntEntityInterface, OWLOntology, Alignemnt should be refined)
 * TODO see above
 * 
 */

package types.interfaces;


import types.SimpleAlignmentCell;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public interface FrameworkAlignment {
	
	public SimpleAlignmentCell[] listAlignments(int filterSignificanceLevel);
	
	/**
	 * Add a new alignment cell
	 * @param cell
	 */
	//public void addCell(SimpleAlignmentCell cell);
	
	/**
	 * @return the name of the method used to generate this alignment
	 */
	//public String getMethodName();
	/**
	 * @return a description of the alignment for human inspection (not further specified).
	 * 
	 */
	//public String getDescription();
	/**
	 * 
	 * @return statistics on this alignmnet
	 */
	public String getStats();
	/**
	 * Add a new alignment 
	 * Migrating from old representation.... must clean things after!
	 */
	public void addJenaSimpleAlignment(Resource leftClass, Resource rightClass, Property alignmentProperty, int alignmentConfidence, String alignmentType, String alignmentDescription, Model alignmentExplanation);

	public void export(String file, String format);
}
