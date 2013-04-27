/**
 * GenericEntityInterface.java
 * An "enity" considered withinh this framerk: processes, ontologies, alignments, values...
 * Copyright 2007-2008 Andrea Splendiani
 * This software is released under LGPL
 * 
 */

package types.interfaces;

public interface FrameworkEntity {
	/**	 
	 *  Note that a name is a "native" property of an ontology, but not of other types (like alignments). 
	 *  The name here is the name as known to the system. May be different (in case of an ontology) from the original name.
	 *	@return the name of this object (example "go-mf"), as known by the system.
	 * 
	 */
	public String getName();
	

	
	/**
	 * This method is redundant in theory. It is introduced for prototyping and will be deprecated soon.
	 * (unless we opt not to class types matching this type...)
	 * @return the type of this object (ontology, alignment....). Types are defined as static in FrameworkTypes
	 */
	public int getType() ;
	
	/**
	 * @return a description of this object (summary), Formatted in a quasi-html style.
	 */
	public  String getDescription() ;

	

	
	
}
