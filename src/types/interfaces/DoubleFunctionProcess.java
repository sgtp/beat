/**
 * DoubleProcess.java
 * a function that operates on two information entities. Can return a value or an ontology. 
 * Under this class ontology matching or ontology comparison functions can be implemented
 * Copyright 2007-2008 Andrea Splendiani
 * This software is released under LGPL
 * 
 * TODO rename ? 
 * 
 */
package types.interfaces;

import types.WrongType;


public interface DoubleFunctionProcess extends FunctionProcess {
	public void setLeftArgument(FrameworkInformationEntity myOnto) throws WrongType;
	public void setRightArgument(FrameworkInformationEntity myOnto) throws WrongType;

	
}

