/**
 * RDFNetwork.java
 * An RDF Model. Does not know a priori about classes and other owl constructs.
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL 
 * 
 * 
 */
package types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import system.BaseBeat;
import system.Config;
import types.interfaces.FrameworkConcurrentEntity;
import types.interfaces.FrameworkEntity;
import types.interfaces.FrameworkNotifier;
import Utils.TimeCounter;

import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.query.larq.IndexLARQ;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.tdb.TDBFactory;

public class RDFNetwork extends FrameworkInformationEntityNotifierImpl implements FrameworkEntity, FrameworkConcurrentEntity, FrameworkNotifier {
	protected String uri; //the uri this ontology is loaded from: this is not the URI of the ontology!
	protected Model baseModel=null;
	protected boolean isAvailable;
	
	public RDFNetwork() {
		super();
		this.name="unnamed";
		this.uri="not-declared";
		type=FrameworkTypes.RDFNETWORK;
		isVolatile=true;
		baseModel=ModelFactory.createDefaultModel();
		isAvailable=false;
		
	}
	public RDFNetwork(String currentName, Model open) {
		name=currentName;
		uri="to_be_recovered!";
		isVolatile=false;
		type=FrameworkTypes.RDFNETWORK;
		
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>Recovering a generic RDF Network from DB (name: "+name+")</message>"); 
		
		baseModel=open;
		
		
		
		isAvailable=true;
	}
	
	public RDFNetwork(RDFNetwork[] networkList) {
		super();
		this.uri="";
		isVolatile=true;
		this.type=FrameworkTypes.RDFNETWORK;
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>creating union of generic rdf networks</Message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		baseModel=ModelFactory.createDefaultModel();
		
		for(int i=0;i<networkList.length;i++) {
			System.out.println("adding "+networkList[i].getName());
			baseModel=baseModel.union(networkList[i].getJenaModel(false));
			BaseBeat.getSystemNotifier().notifyProgressStatus(0, i+1, networkList.length);
		}
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		
		
		isAvailable=true;
	}
	
	
	public void delete() {
		if(!isVolatile) {
			try {
				((ModelRDB)baseModel).remove();
			} catch (Exception e) {
				notifyErrorMessage("<Message>Uh!? thought I had a model in memory but doesn't seem like...</Message>");
			}
		}
	}
	public String getUri() {
		return uri;
	}
	public void setURI(String uri) {
		this.uri=uri;
	}
	
	public Model getJenaModel(boolean considerInference) {
		 return baseModel;
		
	}
	
	public FrameworkInformationEntityNotifierImpl getCopyInMemory() {
		//OntModel newModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,myModel.getBaseModel());
		RDFNetwork newNetwork=new RDFNetwork();
		newNetwork.setName(this.name);
		newNetwork.setURI(this.uri);
		newNetwork.type=this.type;
		newNetwork.baseModel.add(baseModel);
		
		return newNetwork;
	}
	
	

	public boolean makePersistent() {
		if(!isVolatile) {
			notifyDebugMessage("<Message>"+name+" is already in db</Message>");
			return true;
		}
		notifyProgressMessage("<message>Backing rdf network "+name+" on db </message>"); 
		TimeCounter tc=TimeCounter.getTimeCounter();
		
		
		Model tempBaseModel=TDBFactory.createNamedModel(this.name, BaseBeat.defaultTripleStoreURL);
		tempBaseModel.add(baseModel); 
		baseModel=tempBaseModel;
		notifyProgressMessage("<message>Done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		isVolatile=false;
		return true;
		
	}
	
	/*
	public Matrix  makeQuery(MyQuery myQuery)  {
		return makeQuery(myQuery,baseModel);
	}
	*/
	
	public Matrix  makeRDFQuery(FrameworkQuery myQuery) {
		ResultSet queryResults=null;
		QueryExecution qe=null;
		Query query=null;
		TimeCounter tc=TimeCounter.getTimeCounter();
		
		String prefixPack=new String();
		
		Map myPrefixeMap=getJenaModel(true).getNsPrefixMap();
		Set prefixes=myPrefixeMap.keySet();
		if(prefixes.size()>0) {
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) prefixPack+=" \n Using ";
			if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) prefixPack+="";
		}
		Iterator iter=prefixes.iterator();
		while(iter.hasNext()) {
			String prefix=(String)iter.next();
			String uri=(String) myPrefixeMap.get(prefix);
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) prefixPack+=prefix+" for <"+uri+">\n";
			if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) prefixPack+="prefix "+prefix+": <"+uri+"> \n";
		}
		
		
		String finalTextForQuery="";
		try {
			
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) {
				finalTextForQuery=myQuery.getQueryText()+prefixPack;
				query=QueryFactory.create(finalTextForQuery,Syntax.syntaxRDQL);
				notifyDebugMessage("<Message>Ok RDQL</Message>");
			}
			else if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) {
				finalTextForQuery=prefixPack+myQuery.getQueryText();
				query=QueryFactory.create(finalTextForQuery,Syntax.syntaxSPARQL);	
				notifyDebugMessage("<Message>Ok SPARQL</Message>");
			}
			else {
				notifyWarningMessage("<Message>Unsupported language: "+myQuery.getQueryLanguage()+"</Message>");
				return null;
			}
	
		} catch (Exception e) {
			notifyErrorMessage("<Message>Query syntax is wrong in:\n"+finalTextForQuery+"\n"+e.getMessage()+"</Message>");
			return null;
		}
		
		notifyDebugMessage("<Message>The knowledge-base you are asking has "+getJenaModel(true).size()+" statements</Message>");
		notifyDebugMessage("<Message>Your query is "+query.toString(Syntax.syntaxRDQL)+"</Message>");
		qe=QueryExecutionFactory.create(query,getJenaModel(true));
		queryResults=qe.execSelect();
		List queryVariables=query.getResultVars();
		notifyDebugMessage("<Message>Doing tedious unwrapping work!</Message>");
		notifyProgressMessage("<message>Time elapsed for query "+tc.getElapsedTimeMsec()+" msec</message>");
		
		int i=0;
		int j=0;
		ArrayList rows=new ArrayList();
		
		
		while(queryResults.hasNext()) {
			QuerySolution sol= queryResults.nextSolution();
			String[] resultStringArray=new String[queryVariables.size()];
			j=0;
			for (Iterator iterator = queryVariables.iterator(); iterator
					.hasNext();) {
				String var = (String) iterator.next();
				RDFNode result=sol.get(var);
				if(result!=null) {
					if(result.isLiteral()) {
						try {
							resultStringArray[j]=((Literal)result).getString();
							
						} catch (Exception e) {
							resultStringArray[j]=result.toString();
						}
					}
					else if(result.isURIResource()){
						resultStringArray[j]=result.toString();
					}
					else {
						resultStringArray[j]=result.toString();
					}
				j++;
				}
			}
			rows.add(resultStringArray);
			
			i++;
		}
		notifyDebugMessage("<Message>Your query yielded "+i+" results (vars: "+j+") </Message>");
		String[][] results;
		if(i>0 && j>0) results=new String[i][j];
		else {
			results=new String[1][1];
			results[0][0]="";
		}
		
		
		
		for(int x=0;x<i;x++) {
			String[] tempString=(String[]) rows.get(x);
		
			for(int y=0;y<j;y++) {
				results[x][y]=tempString[y];
			}
		}
		notifyProgressMessage("<message>Done. Total time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		qe.close();
		Matrix myMatrix=new Matrix(results);
		return myMatrix;
				
	}
	
	/**
	 * As makeRDFQuery, only a different packing of results. Code should be factorized.
	 * @param myQuery
	 * @return
	 */
	public Resource[]  makeRDFQueryAsResourcesArray(FrameworkQuery myQuery) {
		ResultSet queryResults=null;
		QueryExecution qe=null;
		Query query=null;
		TimeCounter tc=TimeCounter.getTimeCounter();
		
		String prefixPack=new String();
		
		Map myPrefixeMap=getJenaModel(true).getNsPrefixMap();
		Set prefixes=myPrefixeMap.keySet();
		if(prefixes.size()>0) {
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) prefixPack+=" \n Using ";
			if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) prefixPack+="";
		}
		Iterator iter=prefixes.iterator();
		while(iter.hasNext()) {
			String prefix=(String)iter.next();
			String uri=(String) myPrefixeMap.get(prefix);
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) prefixPack+=prefix+" for <"+uri+">\n";
			if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) prefixPack+="prefix "+prefix+": <"+uri+"> \n";
		}
		
		
		String finalTextForQuery="";
		try {
			
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) {
				finalTextForQuery=myQuery.getQueryText()+prefixPack;
				query=QueryFactory.create(finalTextForQuery,Syntax.syntaxRDQL);
				notifyDebugMessage("<Message>Ok RDQL</Message>");
			}
			else if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) {
				finalTextForQuery=prefixPack+myQuery.getQueryText();
				query=QueryFactory.create(finalTextForQuery,Syntax.syntaxSPARQL);	
				notifyDebugMessage("<Message>Ok SPARQL</Message>");
			}
			else {
				notifyWarningMessage("<Message>Unsupported language: "+myQuery.getQueryLanguage()+"</Message>");
				return null;
			}
	
		} catch (Exception e) {
			notifyErrorMessage("<Message>Query syntax is wrong in:\n"+finalTextForQuery+"\n"+e.getMessage()+"</Message>");
			return null;
		}
		
		notifyDebugMessage("<Message>The knowledge-base you are asking has "+getJenaModel(true).size()+" statements</Message>");
		notifyDebugMessage("<Message>Your query is "+query.toString(Syntax.syntaxRDQL)+"</Message>");
		qe=QueryExecutionFactory.create(query,getJenaModel(true));
		queryResults=qe.execSelect();
		List queryVariables=query.getResultVars();
		notifyDebugMessage("<Message>Doing tedious unwrapping work!</Message>");
		notifyProgressMessage("<message>Time elapsed for query "+tc.getElapsedTimeMsec()+" msec</message>");
		
		int i=0;
		int j=0;
		ArrayList rows=new ArrayList();
		
		
		while(queryResults.hasNext()) {
			QuerySolution sol= queryResults.nextSolution();
			
			Iterator iterator = queryVariables.iterator();
			String var = (String) iterator.next();
			RDFNode result=sol.get(var);
			if(result!=null) {
				if(result.isURIResource()){
					rows.add(result);
					i++;
				}
						
			}
			
		}
		notifyDebugMessage("<Message>Your query yielded "+i+" results</Message>");
		Resource[] results;
		if(i>0) results=new Resource[i];
		else {
			results=new Resource[1];
			results[0]=null;
		}
		
		
		
		for(int x=0;x<i;x++) {
			Resource tempRes=(Resource) rows.get(x);
		
			
				results[x]=tempRes;
			
		}
		notifyProgressMessage("<message>Done. Total time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		qe.close();
		return results;
				
	}
	
	public boolean makeRDFQueryAsResourcesArrayInModel(FrameworkQuery myQuery,Model resultModel,Property resultProperty, Resource resultValue) {
		ResultSet queryResults=null;
		QueryExecution qe=null;
		Query query=null;
		TimeCounter tc=TimeCounter.getTimeCounter();
		
		String prefixPack=new String();
		
		Map myPrefixeMap=getJenaModel(true).getNsPrefixMap();
		Set prefixes=myPrefixeMap.keySet();
		if(prefixes.size()>0) {
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) prefixPack+=" \n Using ";
			if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) prefixPack+="";
		}
		Iterator iter=prefixes.iterator();
		while(iter.hasNext()) {
			String prefix=(String)iter.next();
			String uri=(String) myPrefixeMap.get(prefix);
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) prefixPack+=prefix+" for <"+uri+">\n";
			if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) prefixPack+="prefix "+prefix+": <"+uri+"> \n";
		}
		
		
		String finalTextForQuery="";
		try {
			
			if(myQuery.getQueryLanguage()==FrameworkQuery.RDQL) {
				finalTextForQuery=myQuery.getQueryText()+prefixPack;
				query=QueryFactory.create(finalTextForQuery,Syntax.syntaxRDQL);
				notifyDebugMessage("<Message>Ok RDQL</Message>");
			}
			else if(myQuery.getQueryLanguage()==FrameworkQuery.SPARQL) {
				finalTextForQuery=prefixPack+myQuery.getQueryText();
				query=QueryFactory.create(finalTextForQuery,Syntax.syntaxSPARQL);	
				notifyDebugMessage("<Message>Ok SPARQL</Message>");
			}
			else {
				notifyWarningMessage("<Message>Unsupported language: "+myQuery.getQueryLanguage()+"</Message>");
				return false;
			}
	
		} catch (Exception e) {
			notifyErrorMessage("<Message>Query syntax is wrong in:\n"+finalTextForQuery+"\n"+e.getMessage()+"</Message>");
			return false;
		}
		
		notifyDebugMessage("<Message>The knowledge-base you are asking has "+getJenaModel(true).size()+" statements</Message>");
		notifyDebugMessage("<Message>Your query is "+query.toString(Syntax.syntaxRDQL)+"</Message>");
		qe=QueryExecutionFactory.create(query,getJenaModel(true));
		queryResults=qe.execSelect();
		List queryVariables=query.getResultVars();
		notifyDebugMessage("<Message>Doing tedious unwrapping work!</Message>");
		notifyProgressMessage("<message>Time elapsed for query "+tc.getElapsedTimeMsec()+" msec</message>");
		
		int i=0;
		int j=0;
		ArrayList rows=new ArrayList();
		
		
		while(queryResults.hasNext()) {
			QuerySolution sol= queryResults.nextSolution();
			
			Iterator iterator = queryVariables.iterator();
			String var = (String) iterator.next();
			RDFNode result=sol.get(var);
			if(result!=null) {
				if(result.isURIResource()){
					Resource newRes=resultModel.createResource(((Resource) result).getURI());
					resultModel.add(newRes, resultProperty, resultValue);
					i++;
				}
						
			}
			
		}
		notifyDebugMessage("<Message>Your query yielded "+i+" results</Message>");
		
		
		notifyProgressMessage("<message>Done. Total time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		qe.close();
		return true;
				
	}
	/*
	public String getSearchTextResult(String searchString) {
		return getSearchTextResult( searchString, baseModel);
	}
	*/
	public String getStringSearchTextResults(String searchString) {
		IndexLARQ index=null;
		String queryString="";
		/*
		if(hasLuceneIndex()) {
			notifyDebugMessage("<Message>LARQ query starting</Message>");
			queryString="PREFIX pf: <http://jena.hpl.hp.com/ARQ/property#>\n" +
			"select ?term ?p ?lit\n" +
			"where {\n"+
			"?lit pf:textMatch '"+searchString+"' .\n" +
			"?term ?p ?lit\n" +
			"}";
			
			notifyDebugMessage("<Message>Going to open Lucene index</Message>");
			String indexFileName=CoreComponents.getIndexFileName();
			File indexFile=new File(indexFileName);
			IndexWriter indexWriter;
			try {
				indexWriter = new IndexWriter(indexFile,new StandardAnalyzer(), false);
				IndexBuilderString larqBuilder=new IndexBuilderString(indexWriter);
				index = larqBuilder.getIndex() ;
			} catch (IOException e) {
				notifyDebugMessage("<Message>problems in reading Lucene index</Message>");
				return"";
			}
			
		}
		*/
		//else {
			notifyDebugMessage("<Message>Jena literal query starting</Message>");
			queryString=
			"select ?term ?p ?lit\n" +
			"where {\n"+
			"?term ?p ?lit .\n" +
			"filter regex (str(?lit) ,\""+searchString+"\")\n"+
			"}";
		//}
		notifyDebugMessage("<Message>Query is :\n"+queryString+"</Message>");
		
		
		TimeCounter tc=TimeCounter.getTimeCounter();
		Query query=QueryFactory.create(queryString);
		QueryExecution qExec=QueryExecutionFactory.create(query,getJenaModel(true));
		//if(hasLuceneIndex() && index!=null) LARQ.setDefaultIndex(qExec.getContext(), index); 
		notifyDebugMessage("<Message>Staring query</Message>");
		
		ResultSet res=qExec.execSelect();
		notifyDebugMessage("<Message>Packing result</Message>");
		String message="<Message>Answer for "+searchString+"\n<table>\n";
		for( ;res.hasNext();) {
			QuerySolution sol= res.nextSolution();
				message+="<tr></td>";
				message+=sol.get("term").toString()+"</td><td>";
				message+=sol.get("p").toString()+"</td><td>";
				message+=sol.get("lit").toString()+"</td><td></tr>\n";
				
			
			
			
		}
		message+="</table></Message>";
		notifyProgressMessage("<message>Loaded. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		return message;
	}

	public boolean isAvailable() {
		return isAvailable;
	}
	public void setIsAvailble(boolean b) {
		isAvailable=b;
		
	}
	public void addJenaStatememnt(Resource class1, Property myProperty, RDFNode object) {
		
		baseModel.add(class1,myProperty,object);
		
	}
	/*
	public boolean hasLuceneIndex() {
		String thisResource=CoreComponents.getThisResource();
		String isLucenized=CoreComponents.getIsLucenizedProperty();
		Resource myRes=ResourceFactory.createResource(thisResource);
		Property myProp=ResourceFactory.createProperty(isLucenized);
		StmtIterator oldStats= baseModel.listStatements(myRes, myProp,(RDFNode) null);
		int i=0;
		boolean answer=false;
		while(oldStats.hasNext()) {
			Statement next=oldStats.nextStatement();
			answer=next.getLiteral().getBoolean();
			i++;
		}
		if(i>1) notifyDebugMessage("<Message>Too many values for is lucenized!</Message>");
		return answer;
	}
	*/
	/*
	public void setHasLuceneIndex(boolean luceneSupport) {
		String thisResource=CoreComponents.getThisResource();
		String isLucenized=CoreComponents.getIsLucenizedProperty();
		Resource myRes=ResourceFactory.createResource(thisResource);
		Property myProp=ResourceFactory.createProperty(isLucenized);
		
		StmtIterator oldStats= baseModel.listStatements(myRes, myProp,(RDFNode) null);
		
		baseModel.remove(oldStats.toList());
		
		
		Literal newValue=ResourceFactory.createTypedLiteral(Boolean.toString(luceneSupport), XSDDatatype.XSDboolean);
		baseModel.add(baseModel.createStatement(myRes, myProp, newValue)); 
		
		
		 
	}
	*/

	public void registerPrefix(String prefix, String uri) {
		try {
		baseModel.setNsPrefix(prefix,uri);
		
		} catch (Exception e) {
			notifyDebugMessage("<Message>Failed trying to register prefix: "+prefix+" for "+uri+"</Message>");
		}
		
	}
	public boolean isOWLOntology() {
		boolean answer=false;
		String thisResource=Config.thisResource;
		String isInternalTypeProperty=Config.isInternalTypeProperty;
		Resource myRes=ResourceFactory.createResource(thisResource);
		Property myProp=ResourceFactory.createProperty(isInternalTypeProperty);
		StmtIterator oldStats= baseModel.listStatements(myRes, myProp,(RDFNode) null);
		int i=0;
		while(oldStats.hasNext()) {
			Statement next=oldStats.nextStatement();
			answer=next.getLiteral().getString().equals("owl-onto");
			notifyDebugMessage("<message>"+this.name+" has type "+next.getLiteral().getString()+"</message>");
			i++;
		}
		if(i>1) notifyDebugMessage("<message>Too many values for isInternalType!</message>");
		return answer;
		
	}
	public boolean isAlignment() {
		boolean answer=false;
		String thisResource=Config.thisResource;
		String isInternalTypeProperty=Config.isInternalTypeProperty;
		Resource myRes=ResourceFactory.createResource(thisResource);
		Property myProp=ResourceFactory.createProperty(isInternalTypeProperty);
		StmtIterator oldStats= baseModel.listStatements(myRes, myProp,(RDFNode) null);
		int i=0;
		while(oldStats.hasNext()) {
			Statement next=oldStats.nextStatement();
			answer=next.getLiteral().getString().equals("alignment-list");
			notifyDebugMessage("<message>"+this.name+" has type "+next.getLiteral().getString()+"</message>");
			i++;
		}
		if(i>1) notifyDebugMessage("<message>Too many values for isInternalType!</message>");
		return answer;
	}
	public void export(String file, String format) {
		FileOutputStream outFileStream=null;
		try {
			outFileStream=new FileOutputStream(new File(file));
		} catch (FileNotFoundException e) {
			notifyWarningMessage("<message>Files locations are intended on the server!</message>");
			notifyErrorMessage("<message>Unable to open file :"+file+" for output.<br/>"+e.getMessage()+"<br/></message>");
			return;
		}
		String myFormat="RDF/XML";
		if(format.equalsIgnoreCase("n3")) myFormat="N3";
		if(format.equalsIgnoreCase("rdf-xml-abbv")) myFormat="RDF/XML-ABBREV";
		try {
		getJenaModel(false).write(outFileStream,myFormat);
		} catch (Exception e) {
			notifyErrorMessage("<message>Errors in exporting "+name+" to file :"+file+" .<br/>"+e.getMessage()+"<br/></message>");
			
		}
		
		
	}

}
