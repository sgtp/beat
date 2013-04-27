/**
 * WrongType.java
 * A type mismatch exception
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 * TODO check that errors are properly reported
 * 
 */
package types;

@SuppressWarnings("serial")
public class WrongType extends Exception {

	public WrongType(String message) {
		super(message);
		
	}

}
