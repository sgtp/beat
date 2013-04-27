/**
 * Starter.java
 * Starts OntologyServiceManager, and open a console for the language interpreter
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 */

import interfaces.OntoConsole;
import interfaces.SocketConsole;
import interfaces.SocketConsoleServer;
import system.BaseBeat;
import system.GenericNotifier;


public class Starter {
	
	public static void main(String args[]) throws Exception {
		/**
		 * Ideally the start should start,
		 * the console should check whether an OntoServiceManager is active and in case connect.
		 * Here, for the time being, the connection is not up and a console is issued to "take control".
		 */
		BaseBeat.setSystemNotifier(new GenericNotifier());
		
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
		consolle.startConsole();
		
		
		
		
		
	
	}	
  
	
	


}
