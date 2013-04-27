/**
 * MyNotifier.java
 * Interface of an item that produces (status) information
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 *  
 */
package types.interfaces;


public interface FrameworkNotifier {
	/**
	 * register a listener (follows the observer design pattern http://en.wikipedia.org/wiki/Observer_pattern)
	 */
	public void registerObserver(FrameworkObserver observer);
	/**
	 * unregister a listener (follows the observer design pattern http://en.wikipedia.org/wiki/Observer_pattern)
	 */
	public void unregisterObserver(FrameworkObserver observer);
	/**
	 * notify (follows the observer design pattern http://en.wikipedia.org/wiki/Observer_pattern)
	 */
	
	/**
	 * Just notify that the object this observer is watching has changed status (something should be refreshed)
	 * @message a message associated with the change (null if none)
	 */
	public void notifyChange();
	
	/**
	 * Display a message to be communicated to the user. Eg: status or result of an operation.  
	 * @param message
	 */
	public void notifyUserMessage( String message);
	
	/**
	 * Notify an error message
	 * @param message
	 */
	public void notifyErrorMessage(String message);
	
	/**
	 * Notify a warning message
	 * @param message
	 */
	public void notifyWarningMessage(String message);
	
	/**
	 * Notify a message relevant only for debug purpouses
	 * @param message
	 */
	public void notifyDebugMessage(String message);
	/**
	 * Notify a progress message
	 * @param message
	 */
	public void notifyProgressMessage(String message);
	/**
	 * Displays a progress
	 * @param message
	 * @param min the mininum value
	 * @param current the current value
	 * @param max the max value
	 */
	public void notifyProgressStatus(int min, int current, int max);
	

}
