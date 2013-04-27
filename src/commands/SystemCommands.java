/**
 * SystemCommands.java
 * A few commands affecting the system
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 */
package commands;

import java.util.Random;

import system.BaseBeat;
import system.Config;
import types.interfaces.FrameworkEntity;

public class SystemCommands {
	
private static String[] exitMessages={"yes, I'm tired too... bye.",
		"as you want... bye.",
		"hope you enjoyed spending time with me... bye.",
		"see you soon. Bye.",
		"do you think I serve to something ? Bye.",
		"are you happy ? Bye.",
		"see you later, bye.",
		"save the world! Bye.",
		"and yet another day that I feel I've done nothing... Bye.",
		"sometimes I wonder what's the meaning of this all. Bye.",
		"don't you think all these ontologies are too complex ??, Bye."};


public static String getHelpMessage() {
	return("<Message>Welcome to OntoMatcherEnv <Version>"+Config.versionInfo+"</Version><br />\n" +
			"Help is not available at the moment (ww had it, but since we keep changing...<br />\n" +
			"please refer the the current grammar.<br />\n" +
			"and In hope what you think is current is what I think is current.< br/>\n" +
			"Bye.</Message>");
	
}

public static void stopSystem() {
	Random randomizer=new Random(System.currentTimeMillis());
	int choice=randomizer.nextInt(exitMessages.length);
	BaseBeat.getSystemNotifier().notifyUserMessage("<message>Volatile info will be lost</br>" +
			"Persistent will remain</br>"
			+exitMessages[choice] +"</message>");
	BaseBeat.getRemoteConsoleServer().shutDown();
	System.exit(0);
}



public static void execFile(String uri) {
	BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Executing script at "+uri+"</Message>");
	BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Unimplemented!</Message>"); 
}

public static void readFunc(String uri) {
	BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>ELoading function at "+uri+"</Message>");
	BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Unimplemented!</Message>"); 
	
}

public static void publish(FrameworkEntity variable) {
	BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Making  "+variable.getName()+" available for queries</Message>");
	BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Unimplemented!</Message>"); 
	
}



}
