/**
 * ConsoleRenderer.java
 * Transaltes internal message in a representation for rendering
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * WARNING: TO IMPLEMENT!
 * TODO to implement transformation
 * 
 */
package renderer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.hpl.jena.sparql.function.library.matches;

public class ConsoleRenderer {

	public static String translateMessage(String message) {
		String myTranslatedMessage=message;
		if(message.length()>200000) return message;
		// We clean \n, we don't want them...
		myTranslatedMessage= myTranslatedMessage.replaceAll("\n","");
		
		
		// First, we remove the <Message> </Messages> tags, that we don't know what to do with.
		myTranslatedMessage= myTranslatedMessage.replaceAll("<message>","");
		myTranslatedMessage= myTranslatedMessage.replaceAll("</message>","");
		myTranslatedMessage= myTranslatedMessage.replaceAll("<Message>","");
		myTranslatedMessage= myTranslatedMessage.replaceAll("</Message>","");
		
		
		// We look for tables here.
		try {
		while(myTranslatedMessage.indexOf("<table>")>=0) {
			//System.out.println("Found one table");
			
			String beforeTableText="";
			if(myTranslatedMessage.indexOf("<table>")>0) {
				beforeTableText=myTranslatedMessage.substring(0, myTranslatedMessage.indexOf("<table>"));
			}
			//System.out.println("Pre text: "+beforeTableText);
			
			String afterTableText="";
			if(myTranslatedMessage.indexOf("</table>")+8< myTranslatedMessage.length()) {
				afterTableText=myTranslatedMessage.substring(myTranslatedMessage.indexOf("</table>")+8);
			}
			//System.out.println("After text: "+afterTableText);
			
			String tableText=myTranslatedMessage.substring(myTranslatedMessage.indexOf("<table>")+7,myTranslatedMessage.indexOf("</table>"));
			//System.out.println("Table text: "+tableText);
			
			tableText=tableText.replaceAll("<tr>","");
			tableText.trim();
			String[] rows=tableText.split("</tr>");
			int rowNumber=rows.length;
			//System.out.println("Row number: "+rowNumber);
			
			int columnNumber=rows[0].split("</td>").length;
			//System.out.println("Column number: "+columnNumber);
			
			int maxlength[]=new int[columnNumber];
			for(int i=0;i<maxlength.length;i++) {
				maxlength[i]=0;
			}
			
			String[][] table=new String[rowNumber][columnNumber];
			for(int i=0;i<rowNumber;i++) {
				String tempRow=rows[i].replaceAll("<td>", "");
				String[] columns=tempRow.split("</td>");
				for(int j=0;j<columns.length;j++) {
					table[i][j]=columns[j];
					if(table[i][j].length()>maxlength[j]) maxlength[j]=table[i][j].length();
					//System.out.println("Got: "+table[i][j]+" maxlength gets to "+maxlength);
				}
				for(int j=columns.length;j<columnNumber;j++) table[i][j]="";
				
			}
			//System.out.println("Done");
			
			String myOutputTable=new String();
			for(int i=0;i<rowNumber;i++) {
				for(int j=0;j<columnNumber;j++) {
					//System.out.println(i+","+j+": "+table[i][j]);
					myOutputTable=myOutputTable+table[i][j];
					for(int k=table[i][j].length();k<maxlength[j];k++) myOutputTable=myOutputTable+" ";
					myOutputTable=myOutputTable+" | ";
				}
				myOutputTable=myOutputTable+"\n";
			}
			myTranslatedMessage=beforeTableText+myOutputTable+afterTableText;
		}
		} catch (Exception e) {
			myTranslatedMessage="<tableParsingError>"+message+"</tableParsingError>";
		}
		
		
		//Processing descriptions;	
		try {
		while(myTranslatedMessage.indexOf("<description>")>=0) {
		
		
			String beforeDescription="";
			if(myTranslatedMessage.indexOf("<description>")>0) {
				beforeDescription=myTranslatedMessage.substring(0, myTranslatedMessage.indexOf("<description>"));
			}
		
		
			String afterDescription="";
			if(myTranslatedMessage.indexOf("</description>")+14< myTranslatedMessage.length()) {
				afterDescription=myTranslatedMessage.substring(myTranslatedMessage.indexOf("</description>")+14);
			}
			
		
			String descriptionText=myTranslatedMessage.substring(myTranslatedMessage.indexOf("<description>")+13,myTranslatedMessage.indexOf("</description>"));
		    
			descriptionText=renderBit(descriptionText,"name","Name: ","</br>");
			descriptionText=renderBit(descriptionText,"type","Type: ","</br>");
			descriptionText=renderBit(descriptionText,"isVolatile","Is in volatile memory: ","</br>");
			descriptionText=renderBit(descriptionText,"isAvailable","Is ready: ","</br>");
			descriptionText=renderBit(descriptionText,"uri","URI: ","</br>");
			descriptionText=renderBit(descriptionText,"reasoningDescription","Inference supported: ","</br>");
			descriptionText=renderBit(descriptionText,"luceneSupport","Lucene indexing available: ","</br>");
			descriptionText=renderBit(descriptionText,"descriptionText","Description: ","</br>");
			descriptionText=renderBit(descriptionText,"note","Note: ","</br>");
			descriptionText=renderBit(descriptionText,"leftOntology","\"Left\" ontology: ","</br>");
			descriptionText=renderBit(descriptionText,"rightOntology","\"Right\" ontology: ","</br>");
			descriptionText=renderBit(descriptionText,"numberOfRelations","Number of relations: ","</br>");
			descriptionText=renderBit(descriptionText,"numberOfRows","Number of rows: ","</br>");
			descriptionText=renderBit(descriptionText,"numberOfColumns","Number of columns: ","</br>");
			descriptionText=renderBit(descriptionText,"argument","Arg type: ","</br>");
			descriptionText=renderBit(descriptionText,"prefixMap","Prefixes: </br>","");
			descriptionText=renderBit(descriptionText,"parameters","Parameters: </br>","");
			descriptionText=renderBit(descriptionText,"language","Language:","</br>");
			descriptionText=renderBit(descriptionText,"query","Query: </br>","");
			myTranslatedMessage=beforeDescription+descriptionText+afterDescription;
		}
		} catch (Exception e) {
			myTranslatedMessage="<descriptionParsingError>"+message+"</descriptionParsingError>";
		}

		//We change </br> into new line
		myTranslatedMessage= myTranslatedMessage.replaceAll("</br>","\n");
		myTranslatedMessage= myTranslatedMessage.replaceAll("<br/>","\n");
		myTranslatedMessage= myTranslatedMessage.replaceAll("<br>","\n");
		return myTranslatedMessage;
	}

	private static String renderBit(String text, String tagString,
			String preString, String postString) {
			
			String beforeTag="";
			String afterTag="";
			String tagValue="";
				try {
					if(text.indexOf(tagString)<0) return text;
					
					if(text.indexOf("<"+tagString+">")>0) {
						beforeTag=text.substring(0, text.indexOf("<"+tagString+">"));
					}
	
					
					if(text.indexOf("</"+tagString+">")+tagString.length()+3< text.length()) {
						afterTag=text.substring(text.indexOf("</"+tagString+">")+tagString.length()+3);
					}
		
	
					tagValue=text.substring(text.indexOf("<"+tagString+">")+tagString.length()+2,
							text.indexOf("</"+tagString+">"));
				}
				catch (Exception e) {
					return "<ParsingTagError>"+text+"</ParsingTagError>";
				}
			if(tagValue!=null || tagValue.equalsIgnoreCase("")) return beforeTag+preString+tagValue+postString+afterTag;
			else return beforeTag+afterTag;
		
	}

}
