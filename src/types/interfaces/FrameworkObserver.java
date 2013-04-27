/**
 * MyObserver.java
 * Interface of an item that can display messages from a generic item  
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * TODO what does notifyChange() do in here ? It's hardly used. Maybe remove ?
 *  
 */
package types.interfaces;


public interface FrameworkObserver {
	/**
	 * Just notify that the object this observer is watching has changed status (something should be refreshed)
	 *
	 */
	public void notifyChange(FrameworkEntity item);
	
	/**
	 * Display a Message for the user (should be usually shown under most circumstances;
	 * @param item the item notifying the message
	 * @param message
	 */
	public void displayUserMessage(FrameworkEntity item, String message);
	
	/**
	 * Display a Warning Message 
	 * @param item the item notifying the message
	 * @param message
	 */
	public void displayWarningMessage(FrameworkEntity item, String message);
	
	/**
	 * Display a Debug Message 
	 * @param item the item notifying the message
	 * @param message
	 */
	public void displayDebugMessage(FrameworkEntity item, String message);

	/**
	 * Display an Error Message 
	 * @param item the item notifying the message
	 * @param message
	 */
	public void displayErrorMessage(FrameworkEntity item, String message);
	
	
	/**
	 * Display a Progres Message 
	 * @param item the item notifying the message
	 * @param message
	 */
	public void displayProgressMessage(FrameworkEntity item, String message);

	/**
	 * Displays a progress
	 * @param item the item is progress referes to
	 * @param a message associated
	 * @param min the mininum value
	 * @param current the current value
	 * @param max the max value
	 * 
	 */
	public void displayProgressStatus(FrameworkEntity item,int min, int current, int max);
	/**
	 * Set the level of detail that should be used to visualize messages:
	 * 0 None
	 * 1 Errors
	 * 2 Progress/Done information
	 * 3 warning
	 * 4 Debug messages
	 * @param level 0,1,2,3,4
	 */
	public void setDebugLevel(int level);
	
}