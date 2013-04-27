/**
 * ConsoleObserver.java
 * An interface that describes Observers that are consolles (interactive)
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 */
package interfaces;

import types.interfaces.FrameworkObserver;

public interface ConsoleObserver extends FrameworkObserver {
	public void showHelp() ;
	public void setDebugLevel(int level);
}
