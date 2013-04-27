/**
 * FrameworkProcess.java
 * Can be a transformer, a matcher or a translator
 * Copyright 2007-2008 Andrea Splendiani
 * This software is released under LGPL
 * 
 * TODO rename ? 
 * 
 */
package types.interfaces;




public interface FrameworkProcess extends FrameworkEntity, FrameworkNotifier {
	/**
	 * 
	 * @param paramName the name of the parameter
	 * @param paramValue the value of the parametr
	 * @return true if the parameter was succesfully set, false otherwise (unknown parameter or incompatible value)
	 */
	public boolean setParam(String paramName,String paramValue);

	/**
	 * 
	 * @param params an array of with two columns. For each row, the first column contains a parameter name,
	 * the second the corresponding value.
	 * @return true if the parameter was succesfully set, false otherwise (unknown parameter or incompatible value)
	 */
	public boolean setParams(String[][] params);
	
	/**
	 * reset the process to its initial value (for processes that are not stateless). 
	 * For stateless processes, nothing is done.
	 */
	public void reset();
	
	
	public void startProcess();
	/**
	 * Start the execution of the process
	 */
	

}
