/**
 * SimpleAlignment.java
 * An Alignemnt
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL 
 * 
 * WARNING: WILL REFACTOR (relation between OntEntity, OntEntityInterface, OWLOntology, Alignemnt should be refined)
 * TODO see above
 * 
 * TODO to rename
 * 
 * TODO to implement
 * 
 * TODO is an alignment an OntoEntity, or an OWL ontology ?
 * 
 */
package types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import system.BaseBeat;
import system.Config;
import types.interfaces.FrameworkAlignment;

import Utils.TimeCounter;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RSIterator;
import com.hp.hpl.jena.rdf.model.ReifiedStatement;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class SimpleAlignment extends RDFNetwork implements FrameworkAlignment {
	
	public static String confidencePropertyString="http://www.bootstrep.eu/enrichment#alignmentConfidence";
	public static String typePropertyString="http://www.bootstrep.eu/enrichment#alignmentType";
	public static String descriptionPropertyString="http://www.bootstrep.eu/enrichment#alignmentDescription";
	
	static public String hasEvidencePropertyString="http://www.bootstrep.eu/enrichment#hasEvidence";
	
	private String leftNamePropertyString="http://www.bootstrep.eu/enrichment#left";
	private String rightNamePropertyString="http://www.bootstrep.eu/enrichment#right";
	
	Property confidenceProperty;
	Property typeProperty;
	Property descriptionProperty;
	
	Property hasEvidenceProperty;
	
	Property leftNameProperty;
	Property rightNameProperty;
	
	private String matcher="";
	private ArrayList params;
	//private ArrayList<AlignmentCell> cells;
	//private SimpleAlignmentCell[] myCells; //This is old, should be moved... here just to have a partial evolution of code....
	/*
	 * Note: we want RDF here, no ?
	 */
	
	
	public String getSpecificDescription() {
		String desc=super.getSpecificDescription()+"";
		desc+="<leftOntology>"+getLeftName()+"</leftOntology>\n";
		desc+="<rightOntology>"+getRightName()+"</rightOntology>\n";
		
		RSIterator reifList=baseModel.listReifiedStatements();
		desc+="<numberOfRelations>"+reifList.toSet().size()+"</numberOfRelations>";
		/*
		desc+="<Param>";
		Iterator iter=params.iterator();
		while(iter.hasNext()) {
			desc+="<Param>";
			String[] param=(String[])iter.next();
			desc+="<ParamName>"+param[0]+"</ParamName><ParamValue>"+param[1]+"</ParamValue>";
			desc+=listContent();
			desc+="</Param>";
		}
		*/
		return desc;
		
		
	}
	
	public boolean makePersistent() {
		String thisResource=Config.thisResource;
		String isInternalType=Config.isInternalTypeProperty;
		Resource myRes=ResourceFactory.createResource(thisResource);
		Property myProp=ResourceFactory.createProperty(isInternalType);
		
		StmtIterator oldStats= baseModel.listStatements(myRes, myProp,(RDFNode) null);
		baseModel.remove(oldStats.toList());
		
		
		Literal newValue=ResourceFactory.createTypedLiteral("alignment-list", XSDDatatype.XSDstring);
		baseModel.add(baseModel.createStatement(myRes, myProp, newValue)); 
		
		
		super.makePersistent();
		
		return true;
		
	}

/*
	public SimpleAlignment(String name,SimpleAlignmentCell[] myCells) {
		setName(name);
		//this.myCells=myCells;
		isVolatile=true;
		this.type="alignment-list";
		params=new ArrayList();
		confidenceProperty=ResourceFactory.createProperty(confidencePropertyString);
		typeProperty=ResourceFactory.createProperty(typePropertyString);
		descriptionProperty=ResourceFactory.createProperty(descriptionPropertyString);
		
		leftNameProperty=ResourceFactory.createProperty(leftNamePropertyString);
		rightNameProperty=ResourceFactory.createProperty(rightNamePropertyString);
		
	}
*/

	public SimpleAlignment() {
		//this.myCells=myCells;
		isVolatile=true;
		this.type=FrameworkTypes.ALIGNMENT;
		params=new ArrayList();
		confidenceProperty=ResourceFactory.createProperty(confidencePropertyString);
		typeProperty=ResourceFactory.createProperty(typePropertyString);
		descriptionProperty=ResourceFactory.createProperty(descriptionPropertyString);
		
		hasEvidenceProperty=ResourceFactory.createProperty(hasEvidencePropertyString);
		
		leftNameProperty=ResourceFactory.createProperty(leftNamePropertyString);
		rightNameProperty=ResourceFactory.createProperty(rightNamePropertyString);
		
	}
	
	public SimpleAlignment(SimpleAlignment[] alignmentList) {
		super();
		this.uri="";
		isVolatile=true;
		this.type=FrameworkTypes.ALIGNMENT;
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>creating union of alignments</Message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		baseModel=ModelFactory.createDefaultModel();
		
		for(int i=0;i<alignmentList.length;i++) {
			System.out.println("adding "+alignmentList[i].getName());
			baseModel=baseModel.union(alignmentList[i].getJenaModel(false));
			BaseBeat.getSystemNotifier().notifyProgressStatus(0, i+1, alignmentList.length);
		}
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		
		isAvailable=true;
	}
	
	
	
	public SimpleAlignment(RDFNetwork myObject) {
		//	this.myCells=myCells;
		isVolatile=myObject.isVolatile;
		setName(myObject.name);
		this.type=FrameworkTypes.ALIGNMENT;
		params=new ArrayList();
		confidenceProperty=ResourceFactory.createProperty(confidencePropertyString);
		typeProperty=ResourceFactory.createProperty(typePropertyString);
		descriptionProperty=ResourceFactory.createProperty(descriptionPropertyString);
	
		hasEvidenceProperty=ResourceFactory.createProperty(hasEvidencePropertyString);
		
		leftNameProperty=ResourceFactory.createProperty(leftNamePropertyString);
		rightNameProperty=ResourceFactory.createProperty(rightNamePropertyString);
		baseModel=myObject.getJenaModel(false);
	}

	/*
	public String listContent() {
		String desc="<Alignment>";
		
		for (int i=0;i<myCells.length;i++) {
			desc+="<Cell>\n";
			desc+="<Left>"+myCells[i].left.getURI()+"</Left>\n<Right>"+myCells[i].right.getURI()+"</Right>\n";
			desc+="<Relation>"+myCells[i].relation+"</Relation>\n";
			desc+="<Explanation>"+myCells[i].explanation+"</Explanation>\n";
			desc+="<Confidence>"+myCells[i].confidence+"</Confidence>\n";
			desc+="</Cell>\n";
		}
		desc+="<NumberOfElements>"+myCells.length+"</NumberOfElements>\n";
		desc+="</Alignment>\n";
		return desc;
	}
	*/
	public void addParam(String paramName, String paramValue) {
		params.add(new String[] {paramName,paramValue});
	}

	
	public void addCell(SimpleAlignmentCell cell) {
		System.out.println("\tUnimplemnted");
		// TODO Auto-generated method stub
		
	}

	
	
	public SimpleAlignmentCell[] listAlignments(int filterSignificanceLevel) {
		notifyDebugMessage("<message>Filter on significance is not implemented</message>");
		RSIterator alignemntsIter=baseModel.listReifiedStatements();
		SimpleAlignmentCell[] alignArray=new SimpleAlignmentCell[alignemntsIter.toList().size()];
		
		alignemntsIter.close();
		alignemntsIter=baseModel.listReifiedStatements();
		
		int sac=0;
		while(alignemntsIter.hasNext()) {
			alignArray[sac]=new SimpleAlignmentCell(alignemntsIter.nextRS(),baseModel);
			sac++;
			
		}
		
		return alignArray;
	}

	
	

	


	
	public String getMethodName() {
		System.out.println("\tUnimplemnted");
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getStats() {
		System.out.println("\tUnimplemnted");
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	

	public void addJenaSimpleAlignment(Resource class1, Resource class2, Property property, int confidence, String type, String description, Model explanation) {
		Statement alignment=baseModel.createStatement(class1, property, class2);
		ReifiedStatement reifiedAlignment=alignment.createReifiedStatement();
		baseModel.add(alignment);
		Resource evidence=baseModel.createResource();
		baseModel.add(reifiedAlignment,hasEvidenceProperty,evidence);
		baseModel.add(evidence,confidenceProperty,ResourceFactory.createTypedLiteral(Integer.toString(confidence),XSDDatatype.XSDint));
		baseModel.add(evidence,typeProperty,ResourceFactory.createTypedLiteral(type,XSDDatatype.XSDstring));
		baseModel.add(evidence,descriptionProperty,ResourceFactory.createTypedLiteral(description,XSDDatatype.XSDstring));
		if(explanation!=null) {
			// TODO here we should add an RDF fragment
		}
		
	}
	
	public void setLeftName(String left) {
		String thisResource=Config.thisResource;
		Resource myRes=ResourceFactory.createResource(thisResource);
		StmtIterator oldLeftNames= baseModel.listStatements(myRes, leftNameProperty,(Literal)null);
		baseModel.remove(oldLeftNames.toList());
		
		
		Literal newValue=ResourceFactory.createTypedLiteral(left, XSDDatatype.XSDstring);
		baseModel.add(baseModel.createStatement(myRes, leftNameProperty, newValue)); 		
	} 
	
	public void setRightName(String right) {
		String thisResource=Config.thisResource;
		Resource myRes=ResourceFactory.createResource(thisResource);
		StmtIterator oldRightNames= baseModel.listStatements(myRes, rightNameProperty,(Literal)null);
		baseModel.remove(oldRightNames.toList());
		
		
		Literal newValue=ResourceFactory.createTypedLiteral(right, XSDDatatype.XSDstring);
		baseModel.add(baseModel.createStatement(myRes, rightNameProperty, newValue)); 	
	} 
	
	public String getLeftName() {
		String thisResource=Config.thisResource;
		Resource myRes=ResourceFactory.createResource(thisResource);
		
		StmtIterator answers= baseModel.listStatements(myRes, leftNameProperty,(RDFNode) null);
		int i=0;
		String answer="";
		while(answers.hasNext()) {
			Statement next=answers.nextStatement();
			answer=next.getLiteral().getString();
			i++;
		}
		if(i>1) notifyDebugMessage("<Message>Too many names in the left ontology!</Message>");
		return answer;
		
		
	}

	public String getRightName() {
		String thisResource=Config.thisResource;
		Resource myRes=ResourceFactory.createResource(thisResource);
		
		StmtIterator answers= baseModel.listStatements(myRes, rightNameProperty,(RDFNode) null);
		int i=0;
		String answer="";
		while(answers.hasNext()) {
			Statement next=answers.nextStatement();
			answer=next.getLiteral().getString();
			i++;
		}
		if(i>1) notifyDebugMessage("<Message>Too many names in the right ontology!</Message>");
		return answer;
		
		
	}

	
	public void export(String file, String format) {
		//System.out.println("In "+file+"---"+format);
		FileWriter fo=null;
		try {
			fo=new FileWriter(new File(file));
		} catch (Exception e) {
			notifyWarningMessage("<message>Files locations are intended on the server!</message>");
			notifyErrorMessage("<message>Unable to open file :"+file+" for output.<br/>"+e.getMessage()+"<br/></message>");
			return;
		}
		//System.out.println(1);
		String myFormat="txt";
		if(format.equalsIgnoreCase("cell")) {
			notifyDebugMessage("<message>export in cell format is not implemented yet</message>");
			return;
		}
		//System.out.println(1);
		if(myFormat.equalsIgnoreCase("txt")) {
			SimpleAlignmentCell[] myAlignments=listAlignments(0);
			//System.out.println("!"+myAlignments.length);
			notifyProgressMessage("<message>Exporting "+myAlignments.length+" alignments</message>");
			try {
				fo.write("leftOntology\trelation\trightOntology\tConfidence\tType\tDescription\n");
				for(int i=0;i<myAlignments.length;i++) {
				
					fo.write(myAlignments[i].getLeft().getLocalName() +"\t"+myAlignments[i].getRelation().getLocalName()+"\t"+myAlignments[i].getRight().getLocalName()+"\t");
						
					for(int j=0;j<myAlignments[i].getSupportPack().length;j++) {
						if(j!=0) fo.write("idem\t----\t----\t");
						fo.write(myAlignments[i].getSupportPack()[j].confidence+"\t");
						fo.write(myAlignments[i].getSupportPack()[j].type+"\t");
						fo.write("Description: "+myAlignments[i].getSupportPack()[j].description+"\n");
					
					}
				
					fo.write("\n");
					
					//console.displayDebugMessage(null, "<Message>ok for "+i+"</Message>");
				}
				fo.close();
			} catch (Exception e) {
				notifyErrorMessage("<message>Unable to write "+name+" to file :"+file+".<br/>"+e.getMessage()+"</message>");
				return;
			}
		}
		if(myFormat.equalsIgnoreCase("cowl")) {
			SimpleAlignmentCell[] myAlignments=listAlignments(0);
			//System.out.println("!"+myAlignments.length);
			notifyProgressMessage("<message>Exporting "+myAlignments.length+" alignments (cowl)</message>");
			try {
				fo.write("<?xml version=\"1.0\"?>\n"+
						"<!DOCTYPE rdf:RDF [\n"+
						    "<!ENTITY rdf   \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >\n"+
						    "<!ENTITY rdfs  \"http://www.w3.org/2000/01/rdf-schema#\" >\n"+
						    "<!ENTITY xsd   \"http://www.w3.org/2001/XMLSchema#\" >\n"+
						    "<!ENTITY owl   \"http://www.w3.org/2002/07/owl#\" >\n"+
						    "<!ENTITY cowl  \"http://www.itc.it/cowl#\" >\n"+
						  "]>\n"+
						"<rdf:RDF\n"+
						 "xmlns      =\"&cowl;\"\n"+
						 "xmlns:cowl =\"&cowl;\"\n"+
						 "xmlns:owl  =\"&owl;\"\n"+
						 "xmlns:rdf  =\"&rdf;\"\n"+
						 "xmlns:rdfs =\"&rdfs;\"\n"+
						 "xml:base   =\"http://www.bootstrep.eu/platform/cowl\"\n");
				
				fo.write("<cowl:Mapping rdf:ID="+getLeftName()+"-"+getRightName()+">\n"+
				 	"<rdfs:comment> Mapping "+getLeftName()+ " to "+getRightName()+"</rdfs:comment>\n"+
				 	"<cowl:sourceOntology><owl:Ontology rdf:about=\""+getLeftName() +"\"/></cowl:sourceOntology>\n"+
					"<cowl:targetOntology><owl:Ontology rdf:about=\""+getRightName()+"\"/></cowl:targetOntology>\n");
				
				
				for(int i=0;i<myAlignments.length;i++) {
				
					
					
					fo.write("<cowl:bridgeRule>\n"+
							"<cowl:Equivalent>\n"+
							"<cowl:source><owl:Class rdf:about=\""+myAlignments[i].getLeft().getURI()+"\"/></cowl:source>\n"+
				        	"<cowl:target><owl:Class rdf:about=\""+myAlignments[i].getRight().getURI()+"\"/></cowl:target>\n"+
				        	"</cowl:Equivalent>\n"+
							"</cowl:bridgeRule>\n");
					
	
				}
				 fo.write("</cowl:Mapping>"+
						 "</rdf:RDF>");
				fo.close();
			} catch (Exception e) {
				notifyErrorMessage("<message>Unable to write "+name+" to file :"+file+".<br/>"+e.getMessage()+"</message>");
				return;
			}
		}
		
	}


	

}
