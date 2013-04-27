/**
 * OntologyManager.java
 * Handle persistance
 * Copyright 2007-2010 Andrea Splendiani
 * This software is released under GPL
 *  
 */
package system;


import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import types.FrameworkInformationEntityNotifierImpl;
import types.Matrix;
import types.FrameworkQuery;
import types.OWLOntology;
import types.RDFNetwork;
import types.Rules;
import types.SimpleAlignment;
import types.interfaces.FrameworkInformationEntity;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;


public class ResourceManager {
	private Hashtable<String, FrameworkInformationEntity> mem=null;
	private Model beatBaseModel=null;
	private Dataset beatDataset=null;
	
	/**
	 * On construction, resource manager look for a local triplestore (TDB).
	 * If not found, it creates one.
	 * Then it constructs a dataset and scan all graph structures ti "rember".
	 */
	public ResourceManager () {
		mem=new Hashtable<String, FrameworkInformationEntity>();
		BaseBeat.getSystemNotifier().notifyProgressMessage("Init: resource manager");
		
		
		String homeDir=System.getProperty("user.home");
		BaseBeat.getSystemNotifier().notifyDebugMessage("User home:"+homeDir);
		String tripleStoreURL=homeDir+"/"+Config.defaultTdbName;
		File myDir=new File(tripleStoreURL);
		if(!myDir.exists()) {
			BaseBeat.getSystemNotifier().notifyProgressMessage("I'm creating "+tripleStoreURL+" for you");
			try {
				myDir.mkdir();
			} catch (Exception e) {
				BaseBeat.getSystemNotifier().notifyErrorMessage("Sorry, one of these file problems... have to leave you");
				BaseBeat.getSystemNotifier().notifyDebugMessage("e.getMessage()");
				System.exit(-1);
			}
		}
		try {

			beatDataset=TDBFactory.createDataset(tripleStoreURL);
			beatBaseModel=beatDataset.getDefaultModel();
			BaseBeat.defaultTripleStoreURL=tripleStoreURL;
		} catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("Sorry, one of these file problems...");
			BaseBeat.getSystemNotifier().notifyDebugMessage("e.getMessage()");
			System.out.println("Unable to find triplestore");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		long tsSize=beatBaseModel.size();
		BaseBeat.getSystemNotifier().notifyProgressMessage("Found triplestore. Content amount to "+ tsSize +" triples");
		
		//End connection to TripleStore
		
		
		
		/**
		 * This is my own destructor.
		 * I need it, you know, I have a file system to maintain...
		 * If there is a generic destructor for ONDEXGraph, I would be happy to use it.
		 */
		Thread jenaTDBBasedGraphDestructor=new Thread() {
			public void run(){
				// TODO  perhaps synch all files ?
				beatDataset.close();
			}
		};
		Runtime.getRuntime().addShutdownHook(jenaTDBBasedGraphDestructor);
		
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Beat </Message>");
		checkDirectories();
		initOntologies();
		initRules();
		initQueries();
		initMatrixes();
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Ontology Manager is ready</Message>");
	}
	
	
	public void put(String varName, FrameworkInformationEntity targetVar) {
		//if(mem.containsKey(varName)) CoreComponents.getCommandNotifier().notifyWarningMessage("<Message>var "+varName+" was overridden"+"</Message>");
		mem.put(varName, targetVar);
		
	}




	
	
	
	public String compareLocal(String onto1, String onto2, String method) {
		return null;
	}
	public String[] getComparisons() {
		return null;
	}

	public FrameworkInformationEntity get(String varName) {
		FrameworkInformationEntity res=null;
		if(mem.containsKey(varName)) res= mem.get(varName);
		//else  CoreComponents.getCommandNotifier().notifyWarningMessage("<Message>var "+varName+" is not defined</Message>");
		return res;
	}
	public FrameworkInformationEntity[] getVarList() {
		FrameworkInformationEntity[] list=new FrameworkInformationEntity[mem.size()];
		Enumeration elements=mem.elements();
		int i=0;
		while(elements.hasMoreElements()) {
			list[i]=(FrameworkInformationEntity)elements.nextElement();
			i++;
		}
		return list;
	}
	public boolean contains(String varname) {
		if(mem.containsKey(varname)) return true;
		else return false;
	}
	public void delete(String varname) {
		mem.remove(varname);
		
	}
	public void put(FrameworkInformationEntityNotifierImpl onto) {
		put(onto.getName(),onto);
		
	}

	public RDFNetwork getRDFNetwork(FrameworkInformationEntity infoEntity) {
		RDFNetwork result=null;
		try{
			result=(RDFNetwork)infoEntity;
		}
		catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<message>Access to "+infoEntity.getName()+" as an RDFNetwork failed</message>");
		}
		return result;
	}
	public FrameworkQuery getFrameworkQuery(FrameworkInformationEntity infoEntity) {
		FrameworkQuery result=null;
		try{
			result=(FrameworkQuery)infoEntity;
		}
		catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<message>Access to "+infoEntity.getName()+" as an FrameworkQuery failed</message>");
		}
		return result;
	}
	public OWLOntology getOWLOntology(FrameworkInformationEntity infoEntity) {
		OWLOntology result=null;
		try{
			result=(OWLOntology)infoEntity;
		}
		catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<message>Access to "+infoEntity.getName()+" as an OWLOntology failed</message>");
		}
		return result;
	}

	


		
	private void initOntologies() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Retrieving ontologies in memory</Message>");
		Iterator varNames=beatDataset.listNames();  
		
		RDFNetwork defaultNetwork=new RDFNetwork("defaultNetwork",beatBaseModel);
		put("defaultNetwork",defaultNetwork);
		RDFNetwork unionNetwork=new RDFNetwork("unionNetwork",beatDataset.getNamedModel("urn:x-arq:UnionGraph"));
		put("unionNetwork",unionNetwork);
		
		while(varNames.hasNext()) {
			String currentName=(String) varNames.next();
			BaseBeat.getSystemNotifier().notifyProgressMessage("<message>loading: "+currentName+"</message>");
			
			RDFNetwork myObject=new RDFNetwork(currentName,beatDataset.getNamedModel(currentName));
			if(myObject.isOWLOntology()) myObject=new OWLOntology(myObject);
			if(myObject.isAlignment()) myObject=new SimpleAlignment(myObject);
			put(currentName,myObject);
			//CoreComponents.getSystemNotifier().notifyProgressMessage("Done");
			
		}
	}
	
	private void initRules() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Retrieving rules in memory</Message>");
		String rulesDirName=BaseBeat.getRuleDirName();
		File rulesDir=new File(rulesDirName);
		if(!rulesDir.isDirectory()) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Rules directory missing</Message>");
			return;
		}
		try {
			File[] rulesFiles=rulesDir.listFiles();
			for (int i = 0; i < rulesFiles.length; i++) {
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Reading "+rulesFiles[i].getName()+"</Message>");
				Rules rule=new Rules(rulesFiles[i]);
				put(rule.getName(),rule);
				
			}
		} catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Problems in reading rules</Message>");
		}
		
	}
	private void initMatrixes() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Retrieving lists in memory</Message>");
		String matrixDirName=BaseBeat.getMatrixDirName();
		File matrixDir=new File(matrixDirName);
		if(!matrixDir.isDirectory()) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Matrix directory missing</Message>");
			return;
		}
		try {
			File[] matrixFiles=matrixDir.listFiles();
			for (int i = 0; i < matrixFiles.length; i++) {
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Reading "+matrixFiles[i].getName()+"</Message>");
				Matrix matrix=new Matrix(matrixFiles[i]);
				put(matrix.getName(),matrix);
				
			}
		} catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Problems in reading matrix</Message>");
		}
		
	}



	private void initQueries() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Retrieving queries in memory</Message>");
		String queryDirName=BaseBeat.getQueryDirName();
		File queryDir=new File(queryDirName);
		if(!queryDir.isDirectory()) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Query directory missing</Message>");
			return;
		}
		try {
			File[] queryFiles=queryDir.listFiles();
			for (int i = 0; i < queryFiles.length; i++) {
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Reading "+queryFiles[i].getName()+"</Message>");
				FrameworkQuery query=new FrameworkQuery(queryFiles[i]);
				put(query.getName(),query);
				
			}
		} catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Problems in reading queries</Message>");
		}
		
	}
	
	private static void checkDirectories() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Checking directories</Message>");
		String baseDirName=BaseBeat.getBaseDirName();
		String rulesDirName=BaseBeat.getRuleDirName();
		String queryDirName=BaseBeat.getQueryDirName();
		String matrixDirName=BaseBeat.getMatrixDirName();
		File baseDir=new File(baseDirName);
		File rulesDir=new File(rulesDirName);
		File queryDir=new File(queryDirName);
		File matrixDir=new File(matrixDirName);
		if(!baseDir.exists()) {
			try {
				baseDir.mkdir();
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Base directory created</Message>");
			} catch (Exception e) {
				BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Unable to initialize base directory: "+baseDir.getPath()+"</Message>");
			}
		} else BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Base dir: "+baseDir.getPath()+"</Message>");
		
		if(!rulesDir.exists()) {
			try {
				rulesDir.mkdir();
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Rules directory created</Message>");
			} catch (Exception e) {
				BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Unable to initialize rules directory: "+rulesDir.getPath()+"</Message>");
			}
		} else BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Ruke dir: "+rulesDir.getPath()+"</Message>");
	
		if(!queryDir.exists()) {
			try {
				queryDir.mkdir();
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Queries directory created</Message>");
			} catch (Exception e) {
				BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Unable to initialize queries directory: "+queryDir.getPath()+"</Message>");
			}
		} else BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Query dir: "+queryDir.getPath()+"</Message>");
		
		if(!matrixDir.exists()) {
			try {
				matrixDir.mkdir();
				BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Matrix directory created</Message>");
			} catch (Exception e) {
				BaseBeat.getSystemNotifier().notifyErrorMessage("<Message>Unable to initialize matrix directory: "+matrixDir.getPath()+"</Message>");
			}
		} else BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Matrix dir: "+matrixDir.getPath()+"</Message>");
		
	}



	       

}
