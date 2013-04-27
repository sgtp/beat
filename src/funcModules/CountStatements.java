/**
 * CountStatements.java
 * Counts the statements in a model
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 */
package funcModules;

import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.IntValue;
import types.OWLOntology;
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.SingleFunctionProcess;

public class CountStatements extends AbstractProcessImpl implements SingleFunctionProcess {
	RDFNetwork myRDFNetwork=null;
	
	IntValue myResult=null;
	
	
	public CountStatements() {
		super();
		type=FrameworkTypes.TRANSLATOR;
		name="countstatements";
		description="<description><name>countstatements</name>" +
				"<descriptionText>Returns the number of statements in this ontology</descriptionText></description>";
	}

	public FrameworkInformationEntity getResult() {
		return myResult;
	}

	public void setArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			myRDFNetwork=(RDFNetwork) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of OWLOntology");
		}
	}



	public void reset() {
		

	}

	public boolean setParam(String paramName, String paramValue) {
		return true;
	}

	public void startProcess() {
		long i=myRDFNetwork.getJenaModel(true).size();
		myResult=new IntValue(i);

	}

	

	

	
	

	

}
