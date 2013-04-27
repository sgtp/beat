/**
 * SingleFunctionProcess.java
 * computes a function of an informsation entity (result can be a value, but also another ontology)
 * Copyright 2007-2008 Andrea Splendiani
 * This software is released under LGPL
 * 
 * 
 */
package types.interfaces;

import types.WrongType;



public interface SingleFunctionProcess extends FunctionProcess{
	public void setArgument(FrameworkInformationEntity myOnto) throws WrongType;
	
}




