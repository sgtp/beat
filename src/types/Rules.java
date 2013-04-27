/**
 * Rules.java
 * A Rule block
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL 
 *
 */
package types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import system.BaseBeat;
import types.interfaces.FrameworkInformationEntity;

	/**
	 * Hold the text of a query, manages persistence
	 * @author andreasplendiani
	 *
	 */
	public class Rules extends FrameworkInformationEntityImpl {
		String rules=null;
		int language=0;
		public static int JENA=1;
		public static int SPARQL=2;
		
		public Rules(String name, String rulesArg, int language) {
			super();
			
			//CoreComponents.getCommandNotifier().notifyDebugMessage("<Message>Got this string: "+query+" </Message>");
			this.name = name;
			this.language = language;
			this.rules=rulesArg;
			this.type=FrameworkTypes.RULES;
		}
		
		public Rules(File rulesFile) {
			super();
			name=rulesFile.getName();
			isVolatile=false;
			
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(rulesFile));
				String line = inFile.readLine();
				if(line==null) {
					BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Empty rule file</Message>");
					return;
				}
				else {
					if(line.equalsIgnoreCase("sparql")) language=2;
					else if(line.equalsIgnoreCase("jena")) language=1;
					else {
						BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Unknown rule language</Message>");
						return;
					}
				}
				line = inFile.readLine();
				while(line!=null) {
					rules+=line+"\n";
					line = inFile.readLine();
				}
				
			}
			catch (Exception e) {
				BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Problems in reading file</Message>");
			}
			this.type=FrameworkTypes.RULES;
			
			
			
			
				
		}
		

		public void delete() {
			if(isPermanent()) {
				String myDirName=BaseBeat.getRuleDirName();
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Deleting from : "+myDirName+"</Message>");
				File fo=new File(myDirName+"/"+name);
				fo.delete();
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Done</Message>");
			}
		}
		
		public String getSpecificDescription() {
			String description=super.getSpecificDescription();
			description+="<Language>"+language+"</Language>\n";
			description+="<Rules>"+rules+"</Rules>\n";
			return description;
			
		}


		
	

		

		public int getLanguage() {
			return language;
		}
		



		
		public boolean makePersistent() {
			String myDirName=BaseBeat.getRuleDirName();
			BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Saving to : "+myDirName+"</Message>");
			try {
				FileWriter fo=new FileWriter(new File(myDirName+"/"+name));
				if(language==Rules.JENA) fo.write("JENA\n");
				else if(language==Rules.SPARQL) fo.write("SPARQL\n");
				else fo.write("\n");
				fo.write(rules+"\n");
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
			Rules newRules=new Rules(this.name, this.rules,this.language);
			return newRules;
		}


		
		

	}



