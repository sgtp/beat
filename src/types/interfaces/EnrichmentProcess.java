/**
 * EnrichmentProcess.java
 * A process that add information to an information entity. 
 * It accepts parameters, some of which can refer to other entities in this framework
 * Copyright 2007-2008 Andrea Splendiani
 * This software is released under LGPL
 * 
 */
package types.interfaces;

import types.WrongType;


public interface EnrichmentProcess extends FrameworkProcess {
	/**
	 * The framework entity that should be enriched
	 * @param myOnto
	 * @throws WrongType
	 */
	public void setArgument(FrameworkInformationEntity fInfoEntity) throws WrongType;
}
