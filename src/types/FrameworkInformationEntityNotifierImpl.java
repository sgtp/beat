/**
 * OntoEntity.java
 * An abstract class implementing OntoEntityInterface anf offering a few low level methods.
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 * WARNING: WILL REFACTOR (relation between OntEntity, OntEntityInterface, OWLOntology, Alignemnt should be refined)
 * TODO see above
 * 
 */
package types;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import system.BaseBeat;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.FrameworkNotifier;
import types.interfaces.FrameworkObserver;


public abstract class FrameworkInformationEntityNotifierImpl extends FrameworkInformationEntityImpl implements  FrameworkNotifier{
	private Collection<FrameworkObserver> observers;
	
	
	
	public FrameworkInformationEntityNotifierImpl() {
		super();
		observers=new HashSet<FrameworkObserver>();
		
	}


	public void addNote(String note) {
		this.note=note;
		
	}

	
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

	public String getDescription() {
		String description="<description>";
		description+=getSpecificDescription();
		description+="</description>";
		return description;
	}
	
	protected String getSpecificDescription() {
		String description=new String();
		description+="<name>"+name+"</name>\n";
		description+="<descriptionText>"+descriptionText+"</descriptionText>\n";
		description+="<type>"+FrameworkTypes.translate(type)+"</type>\n";
		description+="<note>"+note+"</note>\n";
		description+="<isVolatile>"+isVolatile+"</isVolatile>\n";
		
		return description;
	}


	

	


	
	
	
	
	
}
