/**
 * TimeCounter.java
 * Counts time in ontology operations
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL 
 * 
 * 
 */
package Utils;

public class TimeCounter {
	private long beginTime=0;
	
	public TimeCounter(long currentTimeMillis) {
		beginTime=currentTimeMillis;
	}


	public static TimeCounter getTimeCounter() {
		return new TimeCounter(System.currentTimeMillis());
	}
	
	public void reset() {
		beginTime=System.currentTimeMillis();
	}
	
	public long getElapsedTimeMsec() {
		return System.currentTimeMillis()-beginTime;
	}
	
}
