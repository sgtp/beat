/**
 * BaseBeat.java
 * Beat initialization and container of initialized objects.
 * A container for static (initialized) objects and other informations.
 * Copyright 2007-2010 Andrea Splendiani
 * This software is released under GPL
 * 
 * WARNING: REFACTORING PENDING
 * TODO implement reading from configuration file
 * TODO check overlap with Config.java
 * TODO version number generated from changelog file
 * TODO implement getInfo()
 * TODO implement getStatus()
 * 
 */
package system;

import types.interfaces.FrameworkNotifier;
import interfaces.OntoConsole;
import interfaces.SocketConsoleServer;



public class BaseBeat {
	static private ResourceManager resourceManager=null;
	static private FunctionManager functionManager=null;
	
	//public static GenericNotifier commandNotifier=null;
	public static GenericNotifier systemNotifier=null;
	
	public static boolean isVerbose() {return true;} // TODO To Remove!!!
	/**
	 * Some static strings (hard-coded) define properties used by OntoServiceManager to annotate its ontologies.
	 */
	// preTDB private static String isLucenizedProperty="http://www.bootstrep.eu/system#islucenized";
	
	
	private static SocketConsoleServer socketConsoleServer;
	
	public static String defaultTripleStoreURL=null;
	
	
	public static void startBeat() {
		/**
		 * TODO what is method does, it's still not so clear
		 */
		BaseBeat.setSystemNotifier(new GenericNotifier()); //TODO revise notifier policy
		
		OntoConsole consolle=new OntoConsole(); // This is the standard consolle
		BaseBeat.getSystemNotifier().registerObserver(consolle);
		
		
		//SocketConsole remoteConsole=new SocketConsole();
		BaseBeat.setRemoteConsoleServer (new SocketConsoleServer());
		//CoreComponents.getSystemNotifier().registerObserver(remoteConsole);
		
		System.out.println("DIRECT MESSAGE: Beginning Init");
		BaseBeat.init();
		System.out.println("DIRECT MESSAGE: Done Init");
		
		System.out.println("DIRECT MESSAGE: Starting consolle server");
		BaseBeat.getRemoteConsoleServer().start();
		System.out.println("DIRECT MESSAGE: Done");
		
		System.out.println("DIRECT MESSAGE: Starting consolle");
		//consolle.startConsole();
		
		
		
		
		
	
	}	
	
	
	
	public static void init() {
		if(systemNotifier==null) setSystemNotifier(new GenericNotifier());
		systemNotifier.notifyProgressMessage("OntoService init. "+Config.versionInfo);
		if(functionManager==null) setFuncManager(new FunctionManager());
		if(resourceManager==null) setOm(new ResourceManager());
		//if(commandNotifier==null) setCommandNotifier(new GenericNotifier());
		
	}

	public static ResourceManager getOm() {
		return resourceManager;
	}

	public static void setOm(ResourceManager om) {
		BaseBeat.resourceManager = om;
	}
	
	
	
	public static FunctionManager getFuncManager() {
		return functionManager;
	}
	public static void setFuncManager(FunctionManager funcManager) {
		BaseBeat.functionManager = funcManager;
	}
	/*
	public static MyNotifier getCommandNotifier() {
		return commandNotifier;
	}
	*/
	/*
	public static void setCommandNotifier(GenericNotifier commandNotifier) {
		CoreComponents.commandNotifier = commandNotifier;
	}
	*/
	public static void setSystemNotifier(GenericNotifier systemNotifier) {
		BaseBeat.systemNotifier = systemNotifier;
	}

	public static FrameworkNotifier getSystemNotifier() {
		return systemNotifier;
	}
	static String getBaseDirName() {
		return System.getProperty("user.dir")+"/"+Config.baseDir;
	}
	
	public static String getRuleDirName() {
		return getBaseDirName()+"/rules";
	}
	public static String getQueryDirName() {
		return getBaseDirName()+"/queries";
	}
	public static String getMatrixDirName() {
		return getBaseDirName()+"/matrixes";
	}
	public static int getSocketPort() {
		return Config.socketPort;
	}
	public static String getIndexFileName() {
		return System.getProperty("user.dir")+"/larqIndex";
	}
/* Pre TDB
	public static String getIsLucenizedProperty() {
		return isLucenizedProperty;
	}
*/
	

	public static void setRemoteConsoleServer(
			SocketConsoleServer socketConsoleServerArg) {
		socketConsoleServer=socketConsoleServerArg;
		
	}

	public static SocketConsoleServer getRemoteConsoleServer() {
		if(socketConsoleServer==null) {
			getSystemNotifier().notifyDebugMessage("<message>Asking for a Socket Console when I know none. Creating a new one.</message>");
			socketConsoleServer=new SocketConsoleServer();
		}
		return socketConsoleServer;
	}
	
	

	
}
