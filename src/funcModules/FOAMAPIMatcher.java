package funcModules;

import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.WrongType;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.DoubleFunctionProcess;

public class FOAMAPIMatcher extends AbstractProcessImpl implements DoubleFunctionProcess {
	
	
	public FOAMAPIMatcher() {
		super();
		this.name="FOAM-matcher";
		this.type=FrameworkTypes.MATCHER;
		this.description="";
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
		
	}

	
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
