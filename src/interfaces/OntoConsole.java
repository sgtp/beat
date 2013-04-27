/**
 * OntoConsole.java
 * A console for OntoServiceManager
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * TODO check compatibility with SocketConsole (this still gets syntax error messages!) 
 * TODO there should not be a new parser for each statement!
 * TODO exec function may have interactions here
 * 
 */
package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

public class OntoConsole implements ConsoleObserver {
	private boolean debug=true;
	private boolean error=true;
	private boolean warning=true;
	private boolean progress=true;
	

	
	public void startConsole() throws IOException {
		
		System.out.println("Hello, I'm a shell to OntoServices");
		System.out.println("You can talk to me with the interpreted language you should know (read the grammar)");
		//CoreComponents.getCommandNotifier().registerObserver(this);
		//CoreComponents.getSystemNotifier().registerObserver(this);
		BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>System notifier linked</Message>");
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("yes, sir... >>");
			CharStream input=new ANTLRStringStream(in.readLine());
			simpleOntoMatcherLexer lex = new simpleOntoMatcherLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lex);
			SimpleOntoMatcherParserNotifier g = new SimpleOntoMatcherParserNotifier(tokens,this);
			g.registerObserver(this);
			try {
				g.prog();
			} catch (RecognitionException e) {
				displayErrorMessage(null, "I really didn't get the last word!");
			}

		}
	}
	public void displayDebugMessage(FrameworkEntity item, String message) {
		if(debug) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			System.out.println("\t"+from+ConsoleRenderer.translateMessage(message));
		}

	}

	public void displayErrorMessage(FrameworkEntity item, String message) {
		if(error) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			System.out.println("\t "+from+ConsoleRenderer.translateMessage(message));
		}
		

	}

	public void displayProgressMessage(FrameworkEntity item, String message) {
		if(progress) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			System.out.println("\t"+from+ConsoleRenderer.translateMessage(message));
		}

	}

	public void displayProgressStatus(FrameworkEntity item, int min, int current,
			int max) {
		if(progress) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			int perc=(current*100)/(max-min);
			System.out.println("\t"+from+perc+"%");
		}

	}

	public void displayUserMessage(FrameworkEntity item, String message) {
		String from;
		if(item==null) from="";
		else from=item.getName()+":\n";
		System.out.println(from+ConsoleRenderer.translateMessage(message));

	}

	public void displayWarningMessage(FrameworkEntity item, String message) {
		if(warning) {
			String from;
			if(item==null) from="";
			else from=item.getName()+":\t";
			System.out.println("\t"+from+ConsoleRenderer.translateMessage(message));
		}
	}

	public void notifyChange(FrameworkEntity item) {
		

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
		System.out.println("Verbose level is "+level); //Note: this goes to the console!

	}
	public void showHelp() {
		System.out.println(ConsoleRenderer.translateMessage(SystemCommands.getHelpMessage()));
		
	}
	

}
