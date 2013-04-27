/**
 * SimpleAlignmentCell.java
 * A single corrispondence WILL REFACTOR!!!
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL 
 * 
 * 
 * 
 * WARNING: WILL REFACTOR (relation between OntEntity, OntEntityInterface, OWLOntology, Alignemnt should be refined)
 * TODO see above
 *
 * 
 */
package types;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ReifiedStatement;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.ReifiedStatementImpl;

public class SimpleAlignmentCell {
	ReifiedStatement myStatement;
	Model baseModel;
	SupportPack[] mySupportPack=null;
	
	public SimpleAlignmentCell(ReifiedStatement stat,Model model) {
		myStatement=stat;
		baseModel=model;
		
	}

	public void setReifiedStatement(ReifiedStatement stat) {
		myStatement=stat;
		
		
	}

	public Resource getLeft() {
		
		return myStatement.getStatement().getSubject();
	}
	public Resource getRight() {
		
		return (Resource)myStatement.getStatement().getObject();
	}

	public Property getRelation() {
		
		return myStatement.getStatement().getPredicate();
	}
	public SupportPack[] getSupportPack() {
		if(mySupportPack==null) {
			//System.out.println("mk support");
			StmtIterator answers= baseModel.listStatements(myStatement, ResourceFactory.createProperty(SimpleAlignment.hasEvidencePropertyString ),(RDFNode) null);
			mySupportPack=new SupportPack[answers.toList().size()];
			answers.close();
			answers= baseModel.listStatements(myStatement, ResourceFactory.createProperty(SimpleAlignment.hasEvidencePropertyString ),(RDFNode) null);
			int i=0;
			while(answers.hasNext()) {
				mySupportPack[i]=new SupportPack();
				Statement current=answers.nextStatement();
				Resource currentBlank=(Resource)current.getObject();
				mySupportPack[i].confidence=((Literal)(baseModel.listObjectsOfProperty(currentBlank, ResourceFactory.createProperty(SimpleAlignment.confidencePropertyString)).nextNode())).getInt();
				mySupportPack[i].type=((Literal)(baseModel.listObjectsOfProperty(currentBlank, ResourceFactory.createProperty(SimpleAlignment.typePropertyString)).nextNode())).getString();
				mySupportPack[i].description=((Literal)(baseModel.listObjectsOfProperty(currentBlank, ResourceFactory.createProperty(SimpleAlignment.descriptionPropertyString)).nextNode())).getString();
				//System.out.println(i+" -> "+mySupportPack[i].confidence);
				i++;
			}
		}
		//System.out.println("mk out");
		return mySupportPack;
		
	}
	public class SupportPack {
		public String type="";
		public String description="";
		public int confidence=0;
		public Model explanation=null;
		
	}
	
	
	
}
