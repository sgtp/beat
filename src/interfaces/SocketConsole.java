/**
 * SocketConsole.java
 * A socket listener for interactive consoles.
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * TODO implement multi-threaded service to manage more than one remote console.
 * TODO to redirect syntax errors properly
 * TODO there should not be a new parser for each statement!
 * TODO exec function may have interactions here
 * 
 */
package interfaces;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import parser.SimpleOntoMatcherParserNotifier;
import parser.simpleOntoMatcherLexer;
import renderer.ConsoleRenderer;
import system.BaseBeat;
import types.interfaces.FrameworkEntity;

import commands.SystemCommands;

public class SocketConsole extends Thread implements ConsoleObserver{
	private boolean debug=true;
	private boolean error=true;
	private boolean warning=true;
	private boolean progress=true;
	ServerSocket ontoServerConsole=null;
	String line;
	DataInputStream is;
	PrintStream os=null;
	Socket clientSocket=null;
	boolean working=false;
	boolean shouldRun=true;
	
	static int lastConsoleNumber=0;
	int consoleNumber;

	
	
	public SocketConsole() {
		super();
		/*
		os=System.err;
		CoreComponents.getSystemNotifier().notifyProgressMessage("<Message>Opening a socket on port: "+CoreComponents.getSocketPort()+" </Message>");
		try {
			ontoServerConsole=new ServerSocket(CoreComponents.getSocketPort());
		} catch (IOException e) {
			CoreComponents.getSystemNotifier().notifyErrorMessage("<Message>Unable to open socket on port: "+CoreComponents.getSocketPort()+" </Message>");
			return;
		}	
		try {
			clientSocket=ontoServerConsole.accept();
			is=new DataInputStream(clientSocket.getInputStream());
			os=new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			CoreComponents.getSystemNotifier().notifyErrorMessage("<Message>Unable to open I/O for socket on port: "+CoreComponents.getSocketPort()+" </Message>");
			return;
		}	
		*/
	}
	
	

public SocketConsole(Socket clientSocket) {
	lastConsoleNumber++;
	consoleNumber=lastConsoleNumber;
	try {
		is=new DataInputStream(clientSocket.getInputStream());
		os=new PrintStream(clientSocket.getOutputStream());
	} catch (IOException e) {
		BaseBeat.getSystemNotifier().notifyErrorMessage("<message>I/O troubles for socket on port: "+BaseBeat.getSocketPort()+" for console "+consoleNumber+" while opening a server socket</message>");
		return;
	}
}


public void run()  {
	
	/*
	CoreComponents.getSystemNotifier().notifyProgressMessage("<message>Opening a socket on port: "+CoreComponents.getSocketPort()+" </message>");
	try {
		ontoServerConsole=new ServerSocket(CoreComponents.getSocketPort());
	} catch (IOException e) {
		CoreComponents.getSystemNotifier().notifyErrorMessage("<message>Unable to open socket on port: "+CoreComponents.getSocketPort()+" </message>");
		return;
	}	
	try {
		clientSocket=ontoServerConsole.accept();
		is=new DataInputStream(clientSocket.getInputStream());
		os=new PrintStream(clientSocket.getOutputStream());
	} catch (IOException e) {
		CoreComponents.getSystemNotifier().notifyErrorMessage("<Message>Unable to open I/O for socket on port: "+CoreComponents.getSocketPort()+" </Message>");
		return;
	}	
	*/
	os.println("Hello, I'm a socket based shell to OntoServices. My name is number "+consoleNumber);
	os.println("You can talk to m with the interpreted language you should know (read the grammar)");
	//CoreComponents.getCommandNotifier().registerObserver(this);
	//CoreComponents.getSystemNotifier().registerObserver(this);
	
	BaseBeat.getSystemNotifier().notifyDebugMessage("<message>Consolle number "+consoleNumber+" is up</message>");
	
	BufferedReader in=new BufferedReader(new InputStreamReader(is));
	while (shouldRun) {
		os.println("\033[32m yes, sir... >>");
		CharStream input;
		try {
			input = new ANTLRStringStream(in.readLine());
			simpleOntoMatcherLexer lex = new simpleOntoMatcherLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lex);
			SimpleOntoMatcherParserNotifier g = new SimpleOntoMatcherParserNotifier(tokens,this);
			g.registerObserver(this);
			g.prog();
		} catch (IOException e1) {
			displayErrorMessage(null, "<message>I really could not read this!</message>");
		} catch (RecognitionException e2) {
			displayErrorMessage(null, "<message>I really didn't get what you said! Check the grammar... </br>and remember, syntax errors are still going to the master consolle!</message>");
		}

	}
}
	
	

	public void displayDebugMessage(FrameworkEntity item, String message) {
		if(debug) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			if(os!=null) os.println("\033[35m \t"+from+ConsoleRenderer.translateMessage(message));
		}

	}

	public void displayErrorMessage(FrameworkEntity item, String message) {
		if(error) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			if(os!=null) os.println("\033[31m \t "+from+ConsoleRenderer.translateMessage(message));
		}
		

	}

	public void displayProgressMessage(FrameworkEntity item, String message) {
		if(progress) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			if(os!=null) os.println("\033[30m \t"+from+ConsoleRenderer.translateMessage(message));
		}

	}

	public void displayProgressStatus(FrameworkEntity item, int min, int current,
			int max) {
		if(progress) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			int perc=(current*100)/(max-min);
			if(os!=null) os.println("\033[30m \t"+from+perc+"%");
		}
		// TODO Auto-generated method stub

	}

	public void displayUserMessage(FrameworkEntity item, String message) {
		String from;
		if(item==null) from="";
		else from=item.getName()+":\t";
		if(os!=null) os.println("\033[30m"+from+ConsoleRenderer.translateMessage(message));

	}

	public void displayWarningMessage(FrameworkEntity item, String message) {
		if(warning) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			if(os!=null) os.println("\033[34m \t"+from+ConsoleRenderer.translateMessage(message));
		}
	}

	public void notifyChange(FrameworkEntity item) {
		// TODO Auto-generated method stub

	}

	public void setDebugLevel(int level) {
		/**
		 * Set the level of detail that should be used to visualize messages:
		 * 0 None
		 * 1 Errors
		 * 2 Progress/Done information
		 * 3 warning
		 * 4 Debug messages
		 * @param level 0,1,2,3
		 */
		if(level<0) level=0;
		if(level>4) level=4;
		if(level==0) {error=false; debug=false; warning=false; progress=false;}
		else if(level==1) {error=true; debug=false; warning=false; progress=false;}
		else if(level==2) {error=true; debug=false; warning=false; progress=true;}
		else if(level==3) {error=true; debug=false; warning=true; progress=true;}
		else if(level==4) {error=true; debug=true; warning=true; progress=true;}
		else {error=true; debug=false; warning=false; progress=false;}
		if(os!=null) os.println("Verbose level is "+level); //Note: this goes to the console!

	}
	public void showHelp() {
		if(os!=null)  os.println("\033[30m "+ConsoleRenderer.translateMessage(SystemCommands.getHelpMessage()));
		
	}



	public void closeConnection() {
	
		os.close();
		
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ontoServerConsole.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void stopConsole() {
		shouldRun=false;
		
	}
	
}
