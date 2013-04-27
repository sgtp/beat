package funcModules;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;

import Utils.TimeCounter;

import com.hp.hpl.jena.query.larq.IndexBuilderString;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.OWLOntology;
import types.WrongType;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.EnrichmentProcess;

public class Lucenizer extends AbstractProcessImpl implements EnrichmentProcess {
	
	private boolean makeIndex=false;
	private boolean destroyIndex=false;
	private boolean set=false;
	private boolean unset=false;
	OWLOntology myOntology=null;
	
	


	public Lucenizer() {
		super();
		this.name="lucenizer";
		this.type=FrameworkTypes.ENRICHER;
	}


	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equalsIgnoreCase("Action")) {
			if(paramValue.equalsIgnoreCase("Build")) {
				makeIndex=true;
				return true;
			}
			else if(paramValue.equalsIgnoreCase("DestroyIndex")) {
				destroyIndex=true;
				return true;
			}
			else if(paramValue.equalsIgnoreCase("lucene-on")) {
				set=true;
				return true;
			}
			else if(paramValue.equalsIgnoreCase("lucene-off")) {
				unset=true;
				return true;
				
			}
			else {
				notifyDebugMessage("<message>Unrecognized value "+paramValue+" for parameter "+paramName+"</message>");
				return false;
			}
		}
		else {
			notifyDebugMessage("<message>Unrecognized parameter "+paramName+"</message>");
			return false; 
		}
	}

	
	public void startProcess() {
		/**
		if(set) myOntology.setHasLuceneIndex(true);
		if(unset) myOntology.setHasLuceneIndex(false);
		if(makeIndex) {
			String indexFileName=CoreComponents.getIndexFileName();
			notifyProgressMessage("<message>Building Lucene index in "+indexFileName+"</message>");
			File indexFile=new File(indexFileName);
			IndexBuilderString larqBuilder;
			if(!indexFile.exists()){
				larqBuilder = new IndexBuilderString(indexFileName);
				TimeCounter tc=TimeCounter.getTimeCounter();
				larqBuilder.indexStatements(myOntology.getJenaModel(true).listStatements()) ;
				larqBuilder.closeWriter() ;
				notifyProgressMessage("<message>Time elapsed forf query "+tc.getElapsedTimeMsec()+" msec</message>");
			}
			else {
				try {
					IndexWriter index=new IndexWriter(indexFile,new StandardAnalyzer(), false);
					larqBuilder=new IndexBuilderString(index);
					// -- Create an index based on existing statements
					TimeCounter tc=TimeCounter.getTimeCounter();
					larqBuilder.indexStatements(myOntology.getJenaModel(true).listStatements()) ;
					larqBuilder.closeWriter() ;
					notifyProgressMessage("<message>Time elapsed forf query "+tc.getElapsedTimeMsec()+" msec</message>");
				} catch (IOException e) {
					notifyDebugMessage("<message>Problems in writing Lucene index</message>");
					return;
				}
			}
			
			  
			myOntology.setHasLuceneIndex(true);
			set=true;
			
		}
		if(destroyIndex) {
			notifyDebugMessage("<message>I really don't know how to destroy indexes... I'll fake I didn't understand you.</message>");
			set=false;
		}
		*/
	}

	
	public String getDescription() {
		return "<description><descriptionText>Add Lucene indexing to this ontology</descriptionText>" +
				"<note>the index regards literals and is not updated if the ontology changes.</br>" +
				"(if this is desired, indexing should be re-run)</note>" +
				"<parameters>"+
				"<table>" +
				"<tr><td>Action</td><td>Build</td><td>build indexes for this var literals</td></tr>"+
				"<tr><td>Action</td><td>lucene-on</td><td>use lucene for this literal (declaration, no garantees)</td></tr>"+
				"<tr><td>Action</td><td></td><td>don't use lucene for this literal (declaration, no garantees)</td></tr>"+
				"<tr><td>Action</td>destroyIndex<td></td><td>destroy ALL indexes (use lucene-off !)</td></tr></table>"+
				"</parameters>"+
				"</description>";
	}

	


	
	public void setArgument(FrameworkInformationEntity myOnto) throws WrongType {
		
		try {
			myOntology=(OWLOntology)myOnto;
		} catch (Exception e) {
			throw new WrongType("<message>Wrong type: "+type+" instead of OWLOntology</message>");
		}
	
		
	}

}
