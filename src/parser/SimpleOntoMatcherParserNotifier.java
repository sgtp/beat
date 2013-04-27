/**
 * SimpleOntoMatcherParserNotifier.java
 * This class exists for "practical" reason since the parser code is generated through an external utility.
 * It extends the class simpleOntoMatcherParser (or the class that comes from the parser generator software)
 * with the implementation of a notifer. It also handle the reference to the console.
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * WARNING: REFACTOTING PENDING (the implementation of the Noitifier Interface is not used anymore).
 * 
 * TODO remove implementation of the notifier interface
 * 
 */
package parser;

import interfaces.ConsoleObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.antlr.runtime.TokenStream;

import types.interfaces.FrameworkNotifier;
import types.interfaces.FrameworkObserver;

public class SimpleOntoMatcherParserNotifier extends simpleOntoMatcherParser
		
	implements FrameworkNotifier {
	//private ConsoleObserver console; //Note: this is needed because some commands are directly issued to the console (dverbose)
	private Collection<FrameworkObserver> observers;
	
	
	public SimpleOntoMatcherParserNotifier(TokenStream input, ConsoleObserver console) {
		super(input);
		observers=new ArrayList<FrameworkObserver>();
		languageProxy =new LanguageProxy();
		languageProxy.setConsole(console);
		//this.console=console;
		
		
		
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
