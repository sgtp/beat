
/**
 * FuncManager.java
 * Handle functions (this are not handled as  other variable beacouse they will eventually be loaded on demand)
 * Copyright 2007-2010 Andrea Splendiani
 * This software is released under GPL
 * 
 * WARNING: REFACTORING PENDING
 * TODO implement dynamic loader
 * 
 */
package system;

import java.util.Enumeration;
import java.util.Hashtable;

import types.interfaces.FrameworkProcess;
import funcModules.CountStatements;
import funcModules.CountStatementsDiff;
import funcModules.ExperimentalTyper1;
import funcModules.FindRelatedOntologies;
import funcModules.GoSynToLabel;
import funcModules.Lucenizer;
/*
import funcModules.MultiStrategyStringMatcher;
*/
import funcModules.ExpStringMatcher;
import funcModules.GenericStringMatcher;
import funcModules.QueryToLabel;
import funcModules.SimpleNormalizer;
import funcModules.StringMatcher;
import funcModules.UmlsCuiLookup;
import funcModules.UmlsSemTypeLookup;
import funcModules.UriToLabels;
import funcModules.YutakaNormalizer;

public class FunctionManager {
	private Hashtable<String, FrameworkProcess> functions=null;
	
	public FunctionManager() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Function Manager init</Message>");
		functions=new Hashtable<String, FrameworkProcess>();
		loadBuiltin();
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Function Manager init completed</Message>");
	}

	public boolean hasFunc(String funcName) {
		if(functions.containsKey(funcName)) return true;
		else return false;
	}
	public FrameworkProcess[] getFunctionList() {
		FrameworkProcess[] list=new FrameworkProcess[functions.size()];
		Enumeration<FrameworkProcess> en=functions.elements();
		int i=0;
		while(en.hasMoreElements()) {
			list[i]=en.nextElement();
			i++;
		}
		return list;
	}
	private void loadBuiltin() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Loading built in functions</Message>");
		FrameworkProcess m;
		/*
		m=new StringExactMatcher();
		functions.put(m.getName(), m);
		CoreComponents.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		*/
		
		m=new StringMatcher();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		
		m=new UriToLabels();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new CountStatements();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new Lucenizer();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new CountStatementsDiff();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new FindRelatedOntologies();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new YutakaNormalizer();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new QueryToLabel();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new GoSynToLabel();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new SimpleNormalizer();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new ExperimentalTyper1();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new UmlsCuiLookup();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new UmlsSemTypeLookup();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new GenericStringMatcher();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		m=new ExpStringMatcher();
		functions.put(m.getName(), m);
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
		/*
		m=new MultiStrategyStringMatcher();
		functions.put(m.getName(), m);
		*/
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Registered : "+m.getName()+"</Message>");
	}

	public FrameworkProcess getFunction(String funcname) {
		if(functions.containsKey(funcname)) return (FrameworkProcess)functions.get(funcname);
		else return null;
	}

	public void reset(String functionName) {
		if(functions.containsKey(functionName))  ((FrameworkProcess)functions.get(functionName)).reset();
		
	}
}

