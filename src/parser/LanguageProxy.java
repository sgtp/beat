/**
 * LangaugeProxy.java
 * A long list of functions corresponding to commands in the OntoServieManager language.
 * Computations are not performed here. Only the link between commands and computation and some variable related functions.
 * 
 * Copyright 2007 Andrea Splendiani
 * This software is released under GPL
 * 
 * TODO iterators Java5 style
 * TODO complement list
 * 
 */
package parser;



import interfaces.ConsoleObserver;
import interfaces.SocketConsole;

import java.util.ArrayList;

import system.BaseBeat;
import system.ProcessManager;
import types.FrameworkInformationEntityNotifierImpl;
import types.FrameworkQuery;
import types.FrameworkTypes;
import types.Matrix;
import types.OWLOntology;
import types.RDFNetwork;
import types.Rules;
import types.SimpleAlignment;
import types.SimpleAlignmentCell;
import types.WrongType;
import types.interfaces.DoubleFunctionProcess;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkAlignment;
import types.interfaces.FrameworkEntity;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.FrameworkProcess;
import types.interfaces.SingleFunctionProcess;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import commands.SystemCommands;

public class LanguageProxy {
	//static MyNotifier notifier=null;
	ConsoleObserver console=null;
	
	/*
	public static void setNotifier(SimpleOntoMatcherParserNotifier tnotifier) {
		notifier=tnotifier;
		
	}
	*/
	public   void setConsole(ConsoleObserver tconsole) {
		console=tconsole;
		
	}

	public   void setVerbose(int level) {
		console.setDebugLevel(level);
		
	}
	public  void help() {
		console.showHelp();
		
	}
	public static void endSystem() {
		SystemCommands.stopSystem();
		
	}
	public  void listfunc() {
		FrameworkProcess[] functions=BaseBeat.getFuncManager().getFunctionList();
		String message="<message><table><tr><td>Name</td><td>Type</td></tr>\n";
		for(int i=0;i<functions.length;i++) {
			message+="<tr><td>"+functions[i].getName()+"</td><td>";
			message+=FrameworkTypes.translate(functions[i].getType());
			
			message+="</td></tr>\n";
			
		}
		message+="</table></message>";
		console.displayUserMessage(null,message );
	}
	
	public  void listvar() {
		FrameworkInformationEntity[] varList=BaseBeat.getOm().getVarList();
		String message="<Message><table><tr><td>Name</td><td>Type</td><td>Persistent/Volatile</td></tr>\n";
		
		for(int i=0;i<varList.length;i++) {
			message+="<tr><td>"+varList[i].getName()+"</td><td>"+FrameworkTypes.translate(varList[i].getType())+"</td><td>";
			if(varList[i].isPermanent()) message+="in db";
			else  message+="in memory";
			message+="</td></tr>\n";
		}
		message+="</table></Message>";
		console.displayUserMessage(null,message );
		
	}
	/*
	public  void describeFunction(String funcname) {
		
		if(!CoreComponents.getFuncManager().hasFunc(funcname)) {
			console.displayWarningMessage(null,"<Message>Undefined function: "+funcname+"</Message>");
			return;
		}
		Module m=CoreComponents.getFuncManager().getFunction(funcname);
		console.displayUserMessage(m, m.getDescription());
		
	}
	*/
	public static void execFile(String uri) {
		SystemCommands.execFile(uri);
		
	}
	public static void readFunc(String uri) {
		SystemCommands.execFile(uri);
		
	}
	public  void publish(String var) {
		FrameworkInformationEntity variable=getMemoryEntity(var);
		OWLOntology ontologyToPublish=getOWLOntology(variable);
		if(ontologyToPublish!=null) SystemCommands.publish(variable);
		
	}
	
	
	/*

	public  void describeVar(String varname) {
		GenericEntityInterface myVar=getGenericItem(varname);
		if(myVar!=null) console.displayUserMessage(null,myVar.getDescription()); 
	}
	*/
	
	public  void listVarContent(String varName, String varType) {
		FrameworkInformationEntity generic=null;
		generic=getMemoryEntity(varName);
		if(generic==null) return;
		
		OWLOntology ontology=null;
		Matrix matrix=null;
		FrameworkAlignment align=null;
		String head=null;
		if(isOWLOntology(generic)) {
			ontology=getOWLOntology(varName);
			//String head="<message>";
			Resource[] resourceList=null;
			if(varType.equalsIgnoreCase("Classes")) {
				OntClass[] classes=ontology.getJenaClassesArray();
				head ="Classses in "+varName+" : </br>";
				resourceList=classes;
			}
			else if (varType.equalsIgnoreCase("Individuals")) {
				Individual[] individuals=ontology.getJenaIndividualsArray();
				head ="Individuals in "+varName+" : </br>";
				resourceList=individuals;
			}
			else if (varType.equalsIgnoreCase("ObjectProperties")) {
				ObjectProperty[] objectProperties=ontology.getJenaObjectPropertiesArray();
				head="Object properties in "+varName+" : </br>";
				resourceList=objectProperties;
				
			}
			else if (varType.equalsIgnoreCase("DatatypeProperties")) {
				DatatypeProperty[] datatypeProperties=ontology.getJenaDatatypePropertiesArray();
				head="DatatypeProperties in "+varName+" : </br>";
				resourceList=datatypeProperties;
			}
			else if (varType.equalsIgnoreCase("AnnotationProperties")) {
				AnnotationProperty[] annotationProperties=ontology.getJenaAnnotationPropertiesArray();
				head="Annotation Properties in "+varName+" : </br>";
				resourceList=annotationProperties;
			}
			else if (varType.equalsIgnoreCase("Properties")) {
				Property[] properties=ontology.getJenaPropertiesArray();
				head="Properties in "+varName+" : </br>";
				resourceList=properties;
			}
			else if (varType.equalsIgnoreCase("All")) {
				console.displayWarningMessage(null,"<message>This is an ontology. Please be more specific!</message>");
				return;
				
			}
			else {
				console.displayWarningMessage(null, "<message>Undefined type"+varType+"</message>");
				return;
			}
			String result=null;
			if(resourceList.length<1000) {
				result="<message>"+head+"<table>";
				int i=0;
				for(i=0;i<resourceList.length;i++) {
					result+="<tr><td>"+resourceList[i].getLocalName()+"</td><td> ("+resourceList[i].getNameSpace()+")</td></tr>";
				}
				result+="</table></br>";
				result+="Total number: "+i+"</br>";
				result+="</message>";
				console.displayUserMessage(null, result);
			}
			else {
				console.displayUserMessage(null, "<message>List is too long, going interactive</message>");
				int i=0;
				for(i=0;i<resourceList.length;i++) {
					console.displayUserMessage(null,"<message>"+resourceList[i].getLocalName()+"\t\t ("+resourceList[i].getNameSpace()+")</message>");
				}
				
				console.displayUserMessage(null, "<message>Total number: "+i+"</message>");
				
			}
			
			
			
			
			
			
		
		}
		else if(isMatrix(generic)) {
			matrix=getMatrix(varName);
			head=null;
			
			if(varType.equalsIgnoreCase("All")) {
				String[][] strings=matrix.getMatrixStrings();
				if(strings.length<1000) {
					head="<message>Items in "+varName+" : </br>";
					head+="<table>";
					int i=0;
					int j=0;
				
					for(i=0;i<strings.length;i++) {
						String line="<tr>";
					
						for (j=0;j<strings[0].length;j++) {
							line+="<td>"+strings[i][j]+"</td>";
						}
						line+="</tr>";
						head+=line;
					
					}
					head+="</table>";
					head+="Total of "+i+" x "+j+" items";
					head+="</message>";
					console.displayUserMessage(null,head );
				}
				else {
					console.displayDebugMessage(null,"<message>List is too long, formatting aborted</message>" );
					for(int i=0;i<strings.length;i++) {
						String line="";
					
						for (int j=0;j<strings[0].length;j++) {
							line+="|"+strings[i][j]+"\t\t\t|";
						}
						line+="";
						head+=line;
						console.displayUserMessage(null,"<message>"+line+"</message>");
					
					}
					
				}
			}
			else {
				console.displayWarningMessage(null, "<message>Don't be so specific for "+varName+", just ask all!</message>");
			}
			
		}
		else if(isAlignment(generic)){
			console.displayDebugMessage(null, "<message>Listing of alignment</message>");
			if(varType.equalsIgnoreCase("All")) {
				align=getAlignment(generic);
				console.displayDebugMessage(null, "<message>ok</message>");
				head="<Message>Alignments in "+varName+" : \n";
				
				int i=0;
				
				SimpleAlignmentCell[] myAlignments=align.listAlignments(0);
				console.displayDebugMessage(null, "<Message>ok2</Message>");
				console.displayDebugMessage(null, "<Message>Total n.: "+myAlignments.length+"</Message>");
				if(myAlignments.length<1000) {
					for(i=0;i<myAlignments.length;i++) {
						//console.displayDebugMessage(null, "<Message>going for "+i+"</Message>");
						String line="<table>";
						line+="<tr><td>"+myAlignments[i].getLeft().getLocalName() +"</td><td>"+myAlignments[i].getRelation().getLocalName()+"</td><td>"+myAlignments[i].getRight().getLocalName()+"</td></tr>\n";
						//console.displayDebugMessage(null, "<Message>N of supports: "+myAlignments[i].getSupportPack().length+"</Message>");
						for(int j=0;j<myAlignments[i].getSupportPack().length;j++) {
							line+="<tr>\n";
							line+="<td>Confidence: "+myAlignments[i].getSupportPack()[j].confidence+"</td>\n";
							line+="<td>Type: "+myAlignments[i].getSupportPack()[j].type+"</td>\n";
							line+="<td>Description: "+myAlignments[i].getSupportPack()[j].description+"</td>\n";
							line+="</tr>\n";
						}
						line+="</table>";
						head+=line;
						//console.displayDebugMessage(null, "<Message>ok for "+i+"</Message>");
					}
					
					head+="Total of "+i+" items\n";
					head+="</Message>";
					console.displayUserMessage(null,head );
				}
				else {
					console.displayDebugMessage(null, "<Message>List is too long, going interactive</Message>");
					for(i=0;i<myAlignments.length;i++) {
						
						String line=myAlignments[i].getLeft().getLocalName() +"   "+myAlignments[i].getRelation().getLocalName()+"   "+myAlignments[i].getRight().getLocalName()+"<br>";
						//console.displayDebugMessage(null, "<Message>N of supports: "+myAlignments[i].getSupportPack().length+"</Message>");
						for(int j=0;j<myAlignments[i].getSupportPack().length;j++) {
							line+="\tConfidence: "+myAlignments[i].getSupportPack()[j].confidence+"<br>";
							line+="\tType: "+myAlignments[i].getSupportPack()[j].type+"<br>";
							line+="\tDescription: "+myAlignments[i].getSupportPack()[j].description+"<br>";
							console.displayUserMessage(null,"<message>"+line+"</message>" );
						}
						
					}
					
					console.displayUserMessage(null,"Total of "+i+" items\n");
					
					
				}
			}
			else {
				console.displayWarningMessage(null, "<Message>Don't be so specific for "+varName+", just ask all!</Message>");
			}
		}
		else {
			console.displayWarningMessage(null,"<Message>Can list the content only of ontologies, lists or alignments!</Message>" );
		}
		
		
		
	}




	public  void deleteVarl(String varname) {
		FrameworkInformationEntity variable=getMemoryEntity(varname);
		if(variable!=null) {
			variable.delete();
			BaseBeat.getOm().delete(varname);
		}
	}
	public  void readowl(String varname, String uri) {
		OWLOntology onto=null;
		if(checkVariableWriteTo(varname)) {
			try {
				onto=new OWLOntology(varname,uri);
			} catch (Exception e) {
				console.displayErrorMessage(null, "<message>Unable to read ontology at "+uri+"</message>");
				return;
			}
			BaseBeat.getOm().put(onto);
			onto.registerObserver(console);
		}
		
	}
	public  void makeUnion(ArrayList vars, String varTarget) {
		//System.out.println("size :"+vars.size());
		FrameworkInformationEntity[] genericVars=new FrameworkInformationEntity[vars.size()];
		for(int i=0;i<vars.size();i++) {
			String varName=(String)vars.get(i);
			genericVars[i]=getMemoryEntity(varName);
			if(genericVars[i]==null) return;
		}
		ArrayList<OWLOntology> owlOntologies=new ArrayList<OWLOntology>();
		ArrayList<SimpleAlignment> alignments=new ArrayList<SimpleAlignment>();
		ArrayList<RDFNetwork> rdfNetworks=new ArrayList<RDFNetwork>();
		boolean hasOWLOntology=false;
		boolean hasSimpleAlignment=false;
		
		for(int i=0;i<genericVars.length;i++) {
			OWLOntology tempOntology=getOWLOntology(genericVars[i]);
			SimpleAlignment tempAlign=getSimpleAlignment(genericVars[i]);
			if(tempOntology==null && tempAlign==null) {
				console.displayDebugMessage(null,"<Message>Only Alignments and Ontologies can be merged, not other data types</Messages>");
			}
			else if(tempOntology!=null && tempAlign==null) {
				hasOWLOntology=true;
				owlOntologies.add(tempOntology);
				rdfNetworks.add(tempOntology);
			}
			else if(tempOntology==null && tempAlign!=null) {
				hasSimpleAlignment=true;
				alignments.add(tempAlign);
				rdfNetworks.add(tempAlign);
			}
			else {
				console.displayErrorMessage(null,"<Message>I-m confused: "+genericVars[i]+" has two types... uh!?</Messages>");
			}
				
		}
		FrameworkInformationEntityNotifierImpl newVar=null;
		if(hasOWLOntology && hasSimpleAlignment) {
			console.displayProgressMessage(null,"<Message>Making union of ontologies and alignemnts: rdf!</Messages>");
			RDFNetwork[] networkList=new RDFNetwork[rdfNetworks.size()];
			networkList=rdfNetworks.toArray(networkList);
			newVar=new RDFNetwork(networkList);
		}
		else if(hasOWLOntology && !hasSimpleAlignment) {
			console.displayProgressMessage(null,"<Message>Making union of ontologies</Messages>");
			OWLOntology[] ontologyList=new OWLOntology[owlOntologies.size()];
			ontologyList=owlOntologies.toArray(ontologyList);
			newVar=new OWLOntology(ontologyList);
		}
		else if(!hasOWLOntology && hasSimpleAlignment) {
			console.displayProgressMessage(null,"<Message>Making union of alignments</Messages>");
			SimpleAlignment[] alignmentList=new SimpleAlignment[alignments.size()];
			alignmentList=alignments.toArray(alignmentList);
			newVar=new SimpleAlignment(alignmentList);
		}
		
		if(newVar!=null) {
		
			newVar.setName(varTarget);
			BaseBeat.getOm().put(varTarget, newVar);
			newVar.registerObserver(console);
		}
		else console.displayDebugMessage(null,"<Message>Something went wrong while trying to make an union</Messages>");
		
		
		
	}
	public  void recordQuery(String queryString,  String var, String langString) {
		String queries = queryString.substring(2,queryString.length()-2);
		if(checkVariableWriteTo(var)) {
			int lang=0;
			if(langString.equalsIgnoreCase("RDQL")) lang=1;
			if(langString.equalsIgnoreCase("SPARQL")) lang=2;
			FrameworkQuery newQuery=new FrameworkQuery(var, queries,lang);
			//newQuery.registerObserver(console);
			BaseBeat.getOm().put(var,newQuery);	
			
		}
		else console.displayWarningMessage(null,"<Message>Did nothing. "+var+" was already defined</Message>");
		
	}
	
	public  void recordRules(String rulesString, String var,String langString) {
		String rules = rulesString.substring(2,rulesString.length()-2);
		if(checkVariableWriteTo(var)) {
			int lang=0;
			if(langString.equalsIgnoreCase("Jena")) lang=1;
			if(langString.equalsIgnoreCase("SPARQL")) lang=2;
			Rules newRules=new Rules(var, rules,lang);
			//newRules.registerObserver(console);
			BaseBeat.getOm().put(var,newRules);
		}
		else console.displayWarningMessage(null,"<Message>Did nothing. "+var+" wa already defined</Message>");
		
	}
	

	private  boolean checkVariableWriteTo(String varName) {
		if(BaseBeat.getOm().contains(varName)) {
			console.displayErrorMessage(null,"<Message>: "+varName+" was already defined!</Message>");
			return false;
		}
		else return true;
	}
	public  void makePersistent(String varname) {
		FrameworkInformationEntity var=getMemoryEntity(varname);
		if(var!=null) {
			if(var.makePersistent()) console.displayProgressMessage(null,"<Message>"+varname+" is now in permanent memory</Message>");
			else console.displayErrorMessage(null,"<Message>Unable to write "+varname+" to file</Message>");
		}
		
	}

	public void addInference(String myVar, String resType) {
		OWLOntology var=getOWLOntology(myVar);
		if(var!=null) {
			if(resType.equalsIgnoreCase("none")) var.setNoInference();
			else if (resType.equalsIgnoreCase("RDFS")) var.setRDFSInference();
			else if (resType.equalsIgnoreCase("OWL")) var.setOWLInference();
			else if (resType.equalsIgnoreCase("trans")) var.setTransitiveInference();
			var.applyInference();
		}
		
	}

	public void track(String var) {
		FrameworkInformationEntity variable=getMemoryEntity(var);
		if(variable==null) return;
		FrameworkInformationEntityNotifierImpl onto=null;
		if(isOWLOntology(variable)) onto=getOWLOntology(variable);
		if(onto==null) if(isSimpleAlignment(variable)) onto=getSimpleAlignment(variable);
		if(onto!=null) onto.registerObserver(console);
		
		
		
		
	}

	public void endConsole() {
		try {
			SocketConsole myConsole=(SocketConsole)console;
			myConsole.closeConnection();
			myConsole.stopConsole();
		} catch (Exception e) {
			console.displayWarningMessage(null, "<Message>You cannot quit me, because I've decided so. But if you want, you can shutdown the system</Message>");
		}
	}

	public void copy(String source, String target) {
		FrameworkInformationEntity tempSource=getMemoryEntity(source);
		if(tempSource==null) return;
		if(checkVariableWriteTo(target)) {
			FrameworkInformationEntity targetVar=tempSource.getCopyInMemory();
			targetVar.setName(target);
			BaseBeat.getOm().put(target,targetVar);
		}
	}

	public void extract(String sourceName, String targetName, String type) {
		console.displayDebugMessage(null, "<Message>extraction of "+type+" from "+sourceName+" in "+targetName+"</Message>");
		FrameworkInformationEntity tempSource=getMemoryEntity(sourceName);
		if(tempSource==null) return;
		if(checkVariableWriteTo(targetName)) {
			console.displayDebugMessage(null, "<Message>Target variable ok (not defined)</Message>");
			OWLOntology owlonto=null;
			Matrix matrix=null;
			if(isOWLOntology(tempSource)) {
				owlonto=getOWLOntology(tempSource);
				console.displayDebugMessage(null, "<Message>"+sourceName+" is an OWLOntology</Message>");
				String[] stringArray=null;
				int i=0;
				if(type.equalsIgnoreCase("Classes")) {
					OntClass[] classes=owlonto.getJenaClassesArray();
					stringArray=new String[classes.length];
					for(i=0;i<classes.length;i++) stringArray[i]=classes[i].getURI();
				}
				else if (type.equalsIgnoreCase("Individuals")) {
					Individual[] individuals=owlonto.getJenaIndividualsArray();
					stringArray=new String[individuals.length];
					for(i=0;i<individuals.length;i++) stringArray[i]=individuals[i].getURI();
				}
				else if (type.equalsIgnoreCase("Properties")) {
					Property[] properties=owlonto.getJenaPropertiesArray();
					stringArray=new String[properties.length];
					for(i=0;i<properties.length;i++) stringArray[i]=properties[i].getURI();
				}
				else if (type.equalsIgnoreCase("ObjectProperties")) {
					ObjectProperty[] properties=owlonto.getJenaObjectPropertiesArray();
					stringArray=new String[properties.length];
					for(i=0;i<properties.length;i++) stringArray[i]=properties[i].getURI();
				}
				else if (type.equalsIgnoreCase("DatatypePropertis")) {
					DatatypeProperty[] properties=owlonto.getJenaDatatypePropertiesArray();
					stringArray=new String[properties.length];
					for(i=0;i<properties.length;i++) stringArray[i]=properties[i].getURI();
				}
				else if (type.equalsIgnoreCase("AnnotationProperties")) {
					AnnotationProperty[] properties=owlonto.getJenaAnnotationPropertiesArray();
					stringArray=new String[properties.length];
					for(i=0;i<properties.length;i++) stringArray[i]=properties[i].getURI();
				}
				else if (type.equalsIgnoreCase("All")) {
					console.displayWarningMessage(null,"<Message>This is an ontology. Please be more specific!</Message>");
					
				}
				else {
					console.displayWarningMessage(null, "<Message>Undefined type"+type+"</Message>");
				}
				if(stringArray!=null) {
					Matrix tempMatrix=new Matrix(stringArray);
					tempMatrix.setName(targetName);
					BaseBeat.getOm().put(targetName, tempMatrix);
				}
			}
			else if(isMatrix(tempSource))
			{
				matrix=getMatrix(tempSource);
				if (type.equalsIgnoreCase("All")) {
					Matrix tempMatrix=new Matrix(matrix.getMatrixStrings());
					tempMatrix.setName(targetName);
					BaseBeat.getOm().put(targetName, tempMatrix);
					
				}
				else {
					console.displayWarningMessage(null, "<Message>Cannot be so specific, this is not an ontology!</Message>");
				}
			}
			else console.displayWarningMessage(null, "<Message>Can extract values only from ontologies, alignments (maybe) and lists</Message>");
		}
		
	}
	
	
	
	private  FrameworkInformationEntity getMemoryEntity(String varName) {
		if(!BaseBeat.getOm().contains(varName)) {
			console.displayErrorMessage(null,"<Message>Undefined variable: "+varName+"</Message>");
			return null;
		}
		FrameworkInformationEntity variable=null;
		variable=BaseBeat.getOm().get(varName);
		return variable;
	}
	
	// TODO
	// This type checking should all go to CommonMemory!!!
	//
	
	private  OWLOntology getOWLOntology(String varName) {
		FrameworkInformationEntity myItem=getMemoryEntity(varName);
		if(myItem!=null) return getOWLOntology(myItem);
		else return null;
	}
	private  SimpleAlignment getSimpleAlignment(String varName) {
		FrameworkInformationEntity myItem=getMemoryEntity(varName);
		if(myItem!=null) return getSimpleAlignment(myItem);
		else return null;
	}
	private  Matrix getMatrix(String varName) {
		FrameworkInformationEntity myItem=getMemoryEntity(varName);
		if(myItem!=null) return getMatrix(myItem);
		else return null;
	}
	private  Rules getRules(String varName) {
		FrameworkInformationEntity myItem=getMemoryEntity(varName);
		if(myItem!=null) return getRules(myItem);
		else return null;
	}
	private  FrameworkQuery getQuery(String varName) {
		FrameworkInformationEntity myItem=getMemoryEntity(varName);
		if(myItem!=null) return getQuery(myItem);
		else return null;
	}
	private FrameworkAlignment getAlignment(String varName) {
		FrameworkInformationEntity myItem=getMemoryEntity(varName);
		if(myItem!=null) return getAlignment(myItem);
		else return null;
	}
	
	private  OWLOntology getOWLOntology(FrameworkInformationEntity gItem) {
		OWLOntology ontology=null;
		try {
		ontology=(OWLOntology)gItem;
		} catch (ClassCastException e) {
			console.displayErrorMessage(null,"<Message>Variable: "+gItem.getName()+" is not an ontology or alignment!</Message>");
			return null;
		}
		return ontology;
	}
	private  SimpleAlignment getSimpleAlignment(FrameworkInformationEntity gItem) {
		SimpleAlignment simpleAl=null;
		try {
		simpleAl=(SimpleAlignment)gItem;
		} catch (ClassCastException e) {
			console.displayErrorMessage(null,"<Message>Variable: "+gItem.getName()+" is not an alignment!</Message>");
			return null;
		}
		return simpleAl;
	}
	private  Matrix getMatrix(FrameworkInformationEntity gItem) {
		Matrix matrix=null;
		try {
		matrix=(Matrix)gItem;
		} catch (ClassCastException e) {
			console.displayErrorMessage(null,"<Message>Variable: "+gItem.getName()+" is not a Matrix!</Message>");
			return null;
		}
		return matrix;
	}
	private  Rules getRules(FrameworkInformationEntity gItem) {
		Rules rules=null;
		try {
		rules=(Rules)gItem;
		} catch (ClassCastException e) {
			console.displayErrorMessage(null,"<Message>Variable: "+gItem.getName()+" is not a Rule set!</Message>");
			return null;
		}
		return rules;
	}
	private  FrameworkQuery getQuery(FrameworkInformationEntity gItem) {
		FrameworkQuery query=null;
		try {
		query=(FrameworkQuery)gItem;
		} catch (ClassCastException e) {
			console.displayErrorMessage(null,"<Message>Variable: "+gItem.getName()+" is not a Query!</Message>");
			return null;
		}
		return query;
	}
	private  FrameworkAlignment getAlignment(FrameworkInformationEntity gItem) {
		FrameworkAlignment alignment=null;
		try {
		alignment=(FrameworkAlignment)gItem;
		} catch (ClassCastException e) {
			console.displayErrorMessage(null,"<Message>Variable: "+gItem.getName()+" is not an Alignment!</Message>");
			return null;
		}
		return alignment;
	}
	
	public boolean isOWLOntology(FrameworkInformationEntity item) {
		try {
		OWLOntology onto=(OWLOntology)item;
		} catch (ClassCastException e) {
			return false;
		}
		return true;
	}
	public boolean isRDFNetwork(FrameworkInformationEntity item) {
		try {
			RDFNetwork onto=(RDFNetwork)item;
			} catch (ClassCastException e) {
				return false;
			}
			return true;
		}
	public boolean isSimpleAlignment(FrameworkInformationEntity item) {
		try {
			SimpleAlignment onto=(SimpleAlignment)item;
			} catch (ClassCastException e) {
				return false;
			}
			return true;
		}
	public boolean isMatrix(FrameworkInformationEntity item) {
		try {
			Matrix onto=(Matrix)item;
			} catch (ClassCastException e) {
				return false;
			}
			return true;
	}
	public boolean isQuery(FrameworkInformationEntity item) {
		try {
			FrameworkQuery onto=(FrameworkQuery)item;
			} catch (ClassCastException e) {
				return false;
			}
			return true;
	}
	public boolean isRules(FrameworkInformationEntity item) {
		try {
			Rules onto=(Rules)item;
			} catch (ClassCastException e) {
				return false;
			}
			return true;
	}
	private boolean isAlignment(FrameworkInformationEntity item) {
		try {
			FrameworkAlignment onto=(FrameworkAlignment)item;
			} catch (ClassCastException e) {
				return false;
			}
			return true;
	}
	
	public void makeQuery(String queryString, String resultString, String targetString, String queryType) {
		if(queryType==null || targetString==null || resultString==null || queryString==null) {
			console.displayDebugMessage(null, "<Message>Got a null string</Message>");
			return;
		}
		if(!queryType.equalsIgnoreCase("select")) {
			console.displayWarningMessage(null, "<Message>Only \"select\" queries are implemented now... </Message>");
			
		}
		if(!BaseBeat.getOm().contains(targetString)) {
			console.displayErrorMessage(null, "<Message>"+targetString+" is unknown</Message>");
		}
		RDFNetwork knowledgeToQuery=BaseBeat.getOm().getRDFNetwork(BaseBeat.getOm().get(targetString));
		if(knowledgeToQuery==null) return;
		if(!checkVariableWriteTo(resultString)) return;
		FrameworkQuery query=getQuery(queryString);
		if(query==null) return;
		Matrix result=knowledgeToQuery.makeRDFQuery(query);
		
		if(result==null) {
			
			console.displayErrorMessage(null, "<Message>Something went wrong with your query, maybe the syntax</Message>");
		}
		else {
			result.setName(resultString);
			BaseBeat.getOm().put(resultString, result);
		}
		
	}

	public void export(String var, String file, String format) {
		if(var==null) var="";
		if(file=="") file="";
		if(format==null) format="";
		FrameworkInformationEntity myItem=getMemoryEntity(var);
		if(myItem==null) return;
		if(isAlignment(myItem)) {
			if(format.equalsIgnoreCase(".txt")) {
				((FrameworkAlignment)myItem).export(file,"txt");
			}
			else if(format.equalsIgnoreCase(".cell")) {
				((FrameworkAlignment)myItem).export(file,"cell");
			}
			else {
				console.displayWarningMessage(null, "<message>\""+format+"\" is not valid for an Alignmnet<br/>" +
						"Valid formats are:<br/>" +
						".txt<br/>" +
						".cell<br/>" +
						"</message>");
			}
			
		}
		else if(isRDFNetwork(myItem)) {
			if(format.equalsIgnoreCase(".owl") || format.equalsIgnoreCase(".rdf-xml")) {
				((RDFNetwork)myItem).export(file,"rdf-xml");
			}
			else if(format.equalsIgnoreCase(".rdf-xml-abbv")) {
				((RDFNetwork)myItem).export(file,"rdf-xml-abbv");
			}
			else if(format.equalsIgnoreCase(".n3")) {
				((RDFNetwork)myItem).export(file,"n3");
			}
			else {
				console.displayWarningMessage(null, "<message>\""+format+"\" is not valid for an RDF Model<br/>" +
						"Valid formats are:<br/>" +
						".rdf-xml<br/>" +
						".rdf-xml-abbv<br/>" +
						".n3<br/>" +
						"</message>");
			}
		}
		else {
			console.displayDebugMessage(null, "<message>Export of files is supported only for ontologies and alignments at the moment.<br/>" +
					"Note: queries and lists persistency are anyway on files, it is then possible to recover them.<br/>" +
					"Note: RDF models cannot be exported at this stage. Anyway, a transformer that updates them to OWL ontologies could be implemented.</message>");
		}
	}

	public void makeSearch(String searchString,  String targetString) {
		String searchStr=searchString.substring(2,searchString.length()-2);
		OWLOntology toBeSearched=getOWLOntology(targetString);
		if(toBeSearched==null) return;
		console.displayUserMessage(null, toBeSearched.getStringSearchTextResults(searchStr));
	
		
	}

	public void readowldirect(String varname, String uri) {
		OWLOntology onto=null;
		if(checkVariableWriteTo(varname)) {
			try {
			onto=new OWLOntology(varname,uri,true);
			} catch (Exception e) {
				console.displayErrorMessage(null, "<message>Unable to read ontology at "+uri+"</message>");
				return;
			}
			BaseBeat.getOm().put(onto);
			onto.registerObserver(console);
		}
		
	}

	public void setPrefix(String pr, String uri, String varName) {
		OWLOntology myOnto=getOWLOntology(varName);
		if(myOnto==null) return;
		myOnto.registerPrefix(pr,uri);
		
	}

	public void describe(String itemname) {
		String narrative=" intended as ";
		if(!(BaseBeat.getFuncManager().hasFunc(itemname) && BaseBeat.getOm().contains(itemname))) {
			narrative=" is ";
		}
		if(BaseBeat.getFuncManager().hasFunc(itemname)) {
			console.displayUserMessage(null, "<message>\""+itemname+"\""+narrative+"a function: </message>");
			FrameworkProcess m=BaseBeat.getFuncManager().getFunction(itemname);
			console.displayUserMessage(null, m.getDescription());
		} else
		if(BaseBeat.getOm().contains(itemname)) { 
			console.displayUserMessage(null, "<message>\""+itemname+"\""+narrative+"an ontology item (ontology, alignment, list...): </message>");
			FrameworkEntity myVar=getMemoryEntity(itemname);
			if(myVar!=null) console.displayUserMessage(null,myVar.getDescription());
		} else console.displayWarningMessage(null, "<message>\""+itemname+"\" is unknwon</message>");
	
		
	
		
	}

	public void collapseToBaseModel(String varname) {
		OWLOntology myOnto=getOWLOntology(varname);
		if(myOnto==null) return;
		myOnto.collapseEntailmentsToBaseModel();
		
	}

	public void perfomTransformation(String function, String onto, ArrayList parameters, boolean inbackground) {
		FrameworkProcess myFunction=null;
		EnrichmentProcess transformer;
		FrameworkInformationEntity myArgument=getMemoryEntity(onto);
		if(myArgument==null) return;
		if(!BaseBeat.getFuncManager().hasFunc(function)) {
			console.displayWarningMessage(null, "<Message>"+function+" is not defined</Message>");
			return;
		}
		else myFunction=BaseBeat.getFuncManager().getFunction(function);
		
		try {
			transformer=(EnrichmentProcess)myFunction;
		} catch (Exception e) {
			console.displayDebugMessage(null, "<Message>cannot see this function as a transformer</Message>");
			return;
		}
		
		String[][] parameterBlock=new String[1][2];
		parameterBlock[0][0]="";
		parameterBlock[0][1]="";
		
		if(parameters!=null) {
			parameterBlock=new String[parameters.size()][2];
			for(int i=0;i<parameters.size();i++) {
				String[] tempParams=(String[]) parameters.get(i);
				parameterBlock[i][0]=tempParams[0];
				parameterBlock[i][1]=tempParams[1];
				console.displayDebugMessage(null, "<Message> Setting param "+parameterBlock[i][0]+" to "+parameterBlock[i][1]+"</Message>");
			}
		}
		
		transformer.registerObserver(console);
		transformer.setParams(parameterBlock);
		try {
			transformer.setArgument(myArgument);
		} catch (WrongType e) {
			console.displayErrorMessage(null,"<Message>Type mismatch! ("+e.getMessage()+")</Message>");
			return;
		}
		
		if(inbackground) {
			ProcessManager exec= new ProcessManager(transformer);
			console.displayDebugMessage(null, "<Message>Starting process in background</Message>");
			exec.start();
		}
		else {
			
			transformer.startProcess();
		}
	}

	public void perfomTranslationAndSaveTo(String function, String onto, String target1, ArrayList parameters, boolean inBackground) {
		FrameworkProcess myFunction=null;
		SingleFunctionProcess translator;
		FrameworkInformationEntity myArgument=getMemoryEntity(onto);
		if(myArgument==null) return;
		if(!checkVariableWriteTo(target1)) return;
		if(!BaseBeat.getFuncManager().hasFunc(function)) {
			console.displayWarningMessage(null, "<Message>"+function+" is not defined</Message>");
			return;
		}
		else myFunction=BaseBeat.getFuncManager().getFunction(function);
		
		
		try {
			translator=(SingleFunctionProcess)myFunction;
		} catch (Exception e) {
			console.displayDebugMessage(null, "<Message>cannot see this function as a translator</Message>");
			return;
		}
		
		String[][] parameterBlock=new String[1][2];
		parameterBlock[0][0]="";
		parameterBlock[0][1]="";
		
		if(parameters!=null) {
			parameterBlock=new String[parameters.size()][2];
			for(int i=0;i<parameters.size();i++) {
				String[] tempParams=(String[]) parameters.get(i);
				parameterBlock[i][0]=tempParams[0];
				parameterBlock[i][1]=tempParams[1];
				console.displayDebugMessage(null, "<Message> Setting param "+parameterBlock[i][0]+" to "+parameterBlock[i][1]+"</Message>");
			}
		}
		
		translator.registerObserver(console);
		translator.setParams(parameterBlock);
		try {
			translator.setArgument(myArgument);
		} catch (WrongType e) {
			console.displayErrorMessage(null, "<Message>Type mismatch! ("+e.getMessage()+")</Message>");
			return;
		}
		if(inBackground) {
			ProcessManager exec= new ProcessManager(translator);
			console.displayDebugMessage(null, "<Message>Starting process in background</Message>");
			exec.delegateSetResult(target1);
			exec.start();
			
		}
		else {
			
			translator.startProcess();
			FrameworkInformationEntity result=translator.getResult();
			checkVariableWriteTo(target1);
			result.setName(target1);
			BaseBeat.getOm().put(target1,result);
		}
		
	}

	public void perfomMatchingAndSaveTo(String function, String onto1,
			String onto2, ArrayList parameters, String varTarget, boolean inBackground) {
		FrameworkProcess myFunction;
		DoubleFunctionProcess matcher;
		if(function==null || onto1==null || onto2==null || varTarget==null) {
			console.displayWarningMessage(null, "<Message>Got a wrong item</Message>");
			return;
		}
		FrameworkInformationEntity myArgument1=getMemoryEntity(onto1);
		FrameworkInformationEntity myArgument2=getMemoryEntity(onto2);
		if(myArgument1==null) return;
		if(myArgument2==null) return;
		if(!checkVariableWriteTo(varTarget)) return;
		if(!BaseBeat.getFuncManager().hasFunc(function)) {
			console.displayWarningMessage(null, "<Message>"+function+" is not defined</Message>");
			return;
		}
		else myFunction=BaseBeat.getFuncManager().getFunction(function);
		
		
		try {
			matcher=(DoubleFunctionProcess)myFunction;
		} catch (Exception e) {
			console.displayDebugMessage(null, "<Message>cannot see this function as a matcher</Message>");
			return;
		}
		
		String[][] parameterBlock=new String[1][2];
		parameterBlock[0][0]="";
		parameterBlock[0][1]="";
		
		if(parameters!=null) {
			parameterBlock=new String[parameters.size()][2];
			for(int i=0;i<parameters.size();i++) {
				String[] tempParams=(String[]) parameters.get(i);
				parameterBlock[i][0]=tempParams[0];
				parameterBlock[i][1]=tempParams[1];
				console.displayDebugMessage(null, "<Message> Setting param "+parameterBlock[i][0]+" to "+parameterBlock[i][1]+"</Message>");
			}
		}
		
		matcher.registerObserver(console);
		matcher.setParams(parameterBlock);
		try {
			matcher.setLeftArgument(myArgument1);
			matcher.setRightArgument(myArgument2);
		} catch (WrongType e) {
			console.displayErrorMessage(null, "<Message>Type mismatch! ("+e.getMessage()+")</Message>");
			return;
		}
		if(inBackground) {
			ProcessManager exec= new ProcessManager(matcher);
			console.displayDebugMessage(null, "<Message>Starting process in background</Message>");
			exec.delegateSetResult(varTarget);
			exec.start();
		}
		else {
			
			matcher.startProcess();
			FrameworkInformationEntity result=matcher.getResult();
			checkVariableWriteTo(varTarget);
			if(result!=null) {
				result.setName(varTarget);
				BaseBeat.getOm().put(varTarget,result);
			}
		}
		
	}
	
	
	/*
	public static void perfomMatchingAndSaveTo(String function, String onto1,
			String onto2, ArrayList parameters, String varTarget) {
			boolean verbose=CoreComponents.isVerbose();
			if(verbose) {
				System.out.println("\tApplying matching "+function+"to ontologies "+onto1+","+onto2);
				System.out.println("\twith params:");
				Iterator iter=parameters.iterator();
				while(iter.hasNext()) {
					String[] tempElement=(String[]) iter.next();
					System.out.println("\t"+tempElement[0]+"\t=\t"+tempElement[1]);
				}
				System.out.println("\tStoring result in var:"+varTarget);
			}
			if(!CoreComponents.getFuncManager().hasFunc(function)) {
				System.out.println("\tUndefined function"+function);
				return;
			}
			Module myFunc=CoreComponents.getFuncManager().getFunction(function);
			if(!myFunc.isMatcher()) {
				System.out.println("\t"+function+" is not a matcher!");
				return;
			}
			Matcher myMatcher=(Matcher)myFunc;
			if(!CoreComponents.getMemory().contains(onto1)) {
				System.out.println("\tUndefined variable"+onto1);
				return;
			}
			OntoEntity myVar1=CoreComponents.getMemory().get(onto1);
			if(!myVar1.getType().equals("owl-onto")) {
				System.out.println("\tVariable"+onto1+" is not an ontology");
				return;
			}
			if(!CoreComponents.getMemory().contains(onto2)) {
				System.out.println("\tUndefined variable"+onto2);
				return;
			}
			OntoEntity myVar2=CoreComponents.getMemory().get(onto2);
			if(!myVar2.getType().equals("owl-onto")) {
				System.out.println("\tVariable"+onto2+" is not an ontology");
				return;
			}
			
			//setting parameters...
			//myMatcher.setVerbose(verbose);
			Iterator iter=parameters.iterator();
			String params[][]=new String[parameters.size()][2];
			int i=0;
			while(iter.hasNext()) {
				String[] param=(String[]) iter.next();
				myFunc.setParam(param[0], param[1]);
			}
			
			
			SimpleAlignment myAlignment=myMatcher.compare((OWLOntology)myVar1, (OWLOntology)myVar2);
			myAlignment.setName(varTarget);
			CoreComponents.getMemory().put(varTarget, myAlignment);
			
			
	}
	*/
	
	
	

}
