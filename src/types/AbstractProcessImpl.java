/**
 * AbstarctModule.java
 * An abstract implementation of a module (provides implementation for the notifier interface) 
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 *  
 */
package types;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import types.interfaces.FrameworkConcurrentEntity;
import types.interfaces.FrameworkProcess;
import types.interfaces.FrameworkNotifier;
import types.interfaces.FrameworkObserver;

public abstract class AbstractProcessImpl implements FrameworkProcess {
	
	protected String name="";
	protected String description="";
	protected int type=0;
	protected boolean isAvailable=true;
	private Collection<FrameworkObserver> observers;
	public AbstractProcessImpl() {
		super();
		observers=new HashSet<FrameworkObserver>();
	}



	public boolean setParams(String[][] params) {
		boolean res=true;
		if(params==null) return false;
		
		for (int i=0;i<params.length;i++) {
			res=(res&&setParam(params[i][0],params[i][1]));
		}
		return res;
	}

	

	public boolean isAvailable() { return isAvailable;}

	

	public void registerObserver(FrameworkObserver observer) {
		
		observers.add(observer);
		
	}

	
	public void unregisterObserver(FrameworkObserver observer) {
		observers.remove(observer);
		
	}
	
	public void notifyProgressStatus(int min, int current, int max) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayProgressStatus(this,min,current,max);
		}
		
	}

	
	public void notifyChange() {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().notifyChange(this);
		}
		
	}

	
	public void notifyDebugMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayDebugMessage(this,message);
		}
		
	}

	
	public void notifyErrorMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayErrorMessage(this,message);
		}
		
	}

	
	public void notifyUserMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayUserMessage(this,message);
		}
		
	}

	
	public void notifyWarningMessage(String message) {
		
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayWarningMessage(this,message);
		}
		
	}
	public void notifyProgressMessage(String message) {
		
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayWarningMessage(this,message);
		}
		
	}
	public int getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}


	
}
