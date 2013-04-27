/**
 * GenericNotifier.java
 * Interface of an item that produces status information
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 *  
 */
package system;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import types.interfaces.FrameworkNotifier;
import types.interfaces.FrameworkObserver;

public class GenericNotifier implements FrameworkNotifier {
	private Collection<FrameworkObserver> observers;
	
	
	public GenericNotifier() {
		observers=new HashSet<FrameworkObserver>();
	}

	public void notifyChange() {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().notifyChange(null);
		}

	}

	public void notifyDebugMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayDebugMessage(null,message);
		}

	}

	public void notifyErrorMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayErrorMessage(null,message);
		}

	}

	public void notifyProgressMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayProgressMessage(null,message);
		}

	}

	public void notifyProgressStatus(int min, int current, int max) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayProgressStatus(null,min,current,max);
		}

	}

	public void notifyUserMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayUserMessage(null,message);
		}

	}

	public void notifyWarningMessage(String message) {
		Iterator<FrameworkObserver> iter=observers.iterator();
		while(iter.hasNext()) {
			iter.next().displayWarningMessage(null,message);
		}

	}

	public void registerObserver(FrameworkObserver observer) {
		observers.add(observer);

	}

	public void unregisterObserver(FrameworkObserver observer) {
		observers.remove(observer);

	}

}
