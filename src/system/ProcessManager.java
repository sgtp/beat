/**
 * ProcessManager.java
 * A simple executor of "modules" in background
 * Copyright 2007-2010 Andrea Splendiani
 * This software is released under GPL
 * 
 * TODO to complete
 *  
 */
package system;

import types.interfaces.FrameworkInformationEntity;
import types.interfaces.FrameworkProcess;
import types.interfaces.FunctionProcess;

public class ProcessManager extends Thread {
	FrameworkProcess module=null;
	private boolean setres=false;
	private String resTarget="";
	
	public ProcessManager(FrameworkProcess m) {
		super();
		module=m;
	}
	public void run() {
		if(module!=null) module.startProcess(); 
		if(setres) {
			try {
				FunctionProcess trans=(FunctionProcess)module;
				FrameworkInformationEntity res=trans.getResult();
				if(BaseBeat.getOm().contains(resTarget)) {
					System.out.println("+name clash+");
					// TODO should signal somebody else used that variable name!
				}
				else {
					res.setName(resTarget);
					BaseBeat.getOm().put(resTarget,res);
					
				}
			} catch (Exception e) {
				System.out.println("+cast error+");
				// TODO should signal there was a cast error
			}
			
		}
	}
	public void delegateSetResult(String target1) {
		setres=true;
		resTarget=target1;
		
	}
	
}
