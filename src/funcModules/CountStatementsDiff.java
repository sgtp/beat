/**
 * ListDiff.java
 * Lists different
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 */
package funcModules;

import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.IntValue;
import types.OWLOntology;
import types.WrongType;
import types.interfaces.DoubleFunctionProcess;
import types.interfaces.FrameworkInformationEntity;


public class CountStatementsDiff extends AbstractProcessImpl implements DoubleFunctionProcess {
	OWLOntology myLeftOntology;
	OWLOntology myRightOntology;
	IntValue myResult;
	
	public CountStatementsDiff() {
		super();
		this.type=FrameworkTypes.MATCHER;
		this.name="countstatementsdiff";
	}

	
	public FrameworkInformationEntity getResult() {
		return myResult;
	}

	
	public void setLeftArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			myLeftOntology=(OWLOntology) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of OWLOntology");
		}
			

	}

	
	public void setRightArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			myRightOntology=(OWLOntology) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of OWLOntology");
		}
		

	}

	
	public boolean isMatcher() { return true; }

	public boolean isTransformer() { return false;}

	public void reset() { }

	public boolean setParam(String paramName, String paramValue) { return false;}

	

	
	public void startProcess() {
		long i=(myLeftOntology.getJenaModel(true).difference(myRightOntology.getJenaModel(true))).size();
		myResult=new IntValue(i);
		// TODO Auto-generated method stub

	}

	
	public String getDescription() {
		
		
		 return "<description><name>countstatementsdiff</name>" +
		 		"<descriptionText>return the number of statements in the first ontology that are not present in the second (can be used to test increments)</descriptionText>" +
		 		"</description>";
	}

	
	

	

	

	

	


}
