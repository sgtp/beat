package funcModules;

import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.WrongType;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.DoubleFunctionProcess;

public class InraAPIMatcher extends AbstractProcessImpl implements DoubleFunctionProcess{
	public String[] availableMethods=null;
	
	public InraAPIMatcher() {
		this.type=FrameworkTypes.MATCHER;
		this.name="InraAPIMatcher";
		this.description="";
		// TODO here some dynamic code too which options are available.
		// Proper construction is required that get to know which methods are available
	}

	
	public void setLeftArgument(FrameworkInformationEntity myOnto) throws WrongType {
		// TODO Auto-generated method stub
		
	}

	
	public void setRightArgument(FrameworkInformationEntity myOnto) throws WrongType {
		// TODO Auto-generated method stub
		
	}

	
	public FrameworkInformationEntity getResult() {
		// TODO Auto-generated method stub
		return null;
	}



	
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean setParam(String paramName, String paramValue) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void startProcess() {
		// TODO Auto-generated method stub
		
		// check ontologies
		// check method
		// check parameters
		// translate left ontology (methods in system/FrameworkBridge)
		// translate right ontology (methods in Core Components)
		// run alignment
		// convert alignment to OntoServer format (methods in system/FrameworkBrdige)
		
	}

	

	
	

}
