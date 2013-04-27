/**
 * Value.java
 * A Value (usualy resulting from a comparison operation)
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 */
package types;

import types.interfaces.FrameworkInformationEntity;

public interface Value extends FrameworkInformationEntity {
	boolean testEqualTo(Value reference) throws WrongType;
	boolean testMinorThan(Value reference) throws WrongType;
	boolean testMajorThan(Value reference) throws WrongType;

}
