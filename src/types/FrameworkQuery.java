/**
 * MyQuery.java
 * A query
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 * 
 */
package types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import system.BaseBeat;

/**
 * Hold the text of a query, manages persistence
 * @author andreasplendiani
 *
 */
public class FrameworkQuery extends FrameworkInformationEntityImpl {
	private String query=null;
	private int language=0; 
	
	public static int RDQL=1;
	public static int SPARQL=2;
	public static String typeToString(int lan) {
		if(lan==RDQL) return "RDQL";
		else if(lan==SPARQL) return "SPARQL";
		else return "";
	}
	
	public FrameworkQuery(String queryName, String queryArg, int ll) {
		super();
		
		//CoreComponents.getCommandNotifier().notifyDebugMessage("<Message>Got this string: "+query+" </Message>");
		name = queryName;
		language = ll;
		query=queryArg;
		type=FrameworkTypes.QUERY;
	}
	public FrameworkQuery(File queryFile) {
		super();
		name=queryFile.getName();
		isVolatile=false;
	
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(queryFile));
			String line = inFile.readLine();
			if(line==null) {
				BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Empty query file</Message>");
				return;
			}
			else {
				if(line.equalsIgnoreCase("rdql")) language=RDQL;
				else if(line.equalsIgnoreCase("sparql")) language=SPARQL;
				else {
					BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Unknown query language</Message>");
					return;
				}
			}
			line = inFile.readLine();
			query="";
			while(line!=null) {
				query+=line+"\n";
				line = inFile.readLine();
			}
			
		}
		catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Problems in reading file</Message>");
		}
		this.type=FrameworkTypes.QUERY;
		
		
		
			
	}
	

	public void delete() {
		if(isPermanent()) {
			String myDirName=BaseBeat.getQueryDirName();
			BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Deleting from : "+myDirName+"</Message>");
			File fo=new File(myDirName+"/"+name);
			fo.delete();
			BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Done</Message>");
		}
	}
	public String getSpecificDescription() {
		String description=super.getSpecificDescription();
		description+="<language>"+typeToString(language)+"</language>\n";
		description+="<query>"+query+"</query>\n";
		return description;
		
	}


	public int getQueryLanguage() {
		return language;
	}
	



	
	public boolean makePersistent() {
		String myDirName=BaseBeat.getQueryDirName();
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Saving to : "+myDirName+"</Message>");
		try {
			FileWriter fo=new FileWriter(new File(myDirName+"/"+name));
			if(language==FrameworkQuery.RDQL) fo.write("RDQL\n");
			else if(language==FrameworkQuery.SPARQL) fo.write("SPARQL\n");
			else fo.write("\n");
			fo.write(query+"\n");
			fo.close();
		}
		catch(Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Problems in writing file</Message>");
		}
		this.isVolatile=false;
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Done</Message>");
		return true;
	}
	public FrameworkInformationEntityImpl getCopyInMemory() {
		FrameworkQuery newQuery=new FrameworkQuery(this.name,  this.query, this.language);
		return newQuery;
	}
	public String getQueryText() {
		return this.query;
	}


	
	

}
