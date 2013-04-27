package interfaces;

import java.io.IOException;
import java.net.ServerSocket;

import system.BaseBeat;

public class SocketConsoleServer  extends Thread{
	ServerSocket ontoServerConsole=null;

	public void run()  {
		
		
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>Starting to listen to incoming connections on port: "+BaseBeat.getSocketPort()+" </message>");
		try {
			ontoServerConsole=new ServerSocket(BaseBeat.getSocketPort());
		} catch (IOException e) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<message>Unable to open socket server on port: "+BaseBeat.getSocketPort()+" </message>");
			BaseBeat.getSystemNotifier().notifyErrorMessage("<message>"+e.getMessage()+"</message>");
			return;
		}
		while(true){
			SocketConsole mySocketConsole;
			try {
				mySocketConsole=new SocketConsole(ontoServerConsole.accept());
				mySocketConsole.start();
			
			} catch (IOException e) {
				BaseBeat.getSystemNotifier().notifyErrorMessage("<message>I/O troubles for console server on socket port: "+BaseBeat.getSocketPort()+" </message>");
				return;
			}	
		}
	}	
	public void shutDown()  {
		try {
			if(ontoServerConsole!=null) ontoServerConsole.close();
		} catch (IOException e) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<message>Unable to close socket server on port: "+BaseBeat.getSocketPort()+" </message>");
			BaseBeat.getSystemNotifier().notifyErrorMessage("<message>"+e.getMessage()+"<br/>"+e.getCause()+"</message>");
			
		}
		
		
	}		
		
}
