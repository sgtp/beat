package funcModules;

import java.util.Iterator;

import Utils.TimeCounter;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.OWLOntology;
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkInformationEntity;

public class PropertiesTyperFilter  extends AbstractProcessImpl implements EnrichmentProcess{
	RDFNetwork myRDFNetwork;
	String targetPropertyString="http://www.bootstrep.eu/enrichment#lexicalRelation";
	String relatedPropertyString="http://www.bootstrep.eu/ontology/enrichment#relatedTo";
	String equivalentPropertyString="http://www.bootstrep.eu/ontology/enrichment#equivalentTo";
	int resolutionTick=1000;
	
	
	public PropertiesTyperFilter() {
		super();
		name="propertytyperfilter";
		type=FrameworkTypes.ENRICHER;
		description="<description>" +
				"<descriptionText>Check whether for a relation set of properties hold. If so, assigns a specific property to this relation</descriptionText>\n" +
				"<argument>RDFNetwork</argument>"+
				"<parameters>" +
				"<table>"+
				"<tr><td>Name</td><td>Value</td><td>Default</td><td>Additional param. (default)</td></tr>"+
				"<tr><td>inputProperty</td><td></td><td>http://www.bootstrep.eu/ontology/enrichment#genericMatch</td><td></td></tr>"+
				"<tr><td>outputProperty</td><td></td><td></td><td>http://www.bootstrep.eu/ontology/enrichment#isA</td></tr>"+
				"<tr><td>isAntiSymmetric</td><td>true/false</td><td>true</td><td></td></tr>"+
				"<tr><td>isIntransitive</td><td>true/false</td><td>false</td><td></td></tr>"+
				
				"<tr><td>builtin</td><td>partOf,isA,equivalentTo,relatedTo,participatesIn</td><td>isA</td><td></td></tr>"+
				"<tr><td>fallbackOn</td><td>true/false</td><td>true</td><td></td></tr>"+
				"<tr><td>fallback</td><td></td><td>http://www.bootstrep.eu/ontology/enrichment#relatedTo</td><td></td></tr>"+
				"" +
				"</parameters></description>";
		
	}

	public void setArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			myRDFNetwork=(RDFNetwork)myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of an RDFNetwork");
		}
	}



	public void reset() {
	
	}

	public boolean setParam(String paramName, String paramValue) {
	
		return true;
	}

	public void startProcess() {
		if(myRDFNetwork==null) {
			notifyDebugMessage("<Message>no RDFNetwork found</Message>");
			return;
		}
		int counter=0;
		int eqc=0;
		int rec=0;
		isAvailable=false;
		TimeCounter tc=TimeCounter.getTimeCounter();
		Property targetProperty=ResourceFactory.createProperty(targetPropertyString);
		Property equivalentProperty=ResourceFactory.createProperty(equivalentPropertyString);
		Property relatedProperty=ResourceFactory.createProperty(relatedPropertyString);
		
		StmtIterator iter=myRDFNetwork.getJenaModel(true).listStatements((Resource)null, targetProperty,(Resource) null);
		while(iter.hasNext()) {
			Statement stat=iter.nextStatement();
			Resource subject=stat.getSubject();
			Resource object=(Resource) stat.getObject();
			if(myRDFNetwork.getJenaModel(true).contains(object, targetProperty, subject)) {
				myRDFNetwork.addJenaStatememnt(subject, equivalentProperty, object);
				myRDFNetwork.addJenaStatememnt(object, equivalentProperty, subject);
				eqc++;
			}
			else {
				myRDFNetwork.addJenaStatememnt(subject, relatedProperty, object);
				myRDFNetwork.addJenaStatememnt(object, relatedProperty, subject);
				rec++;
			}
			if(counter % resolutionTick==0) {
				notifyProgressMessage("<Message> processed "+counter+" relations</Message>");
			}
			counter++;
		}
		notifyProgressMessage("<Message> Found "+eqc+" equivalence relations, "+rec+" generic relations</Message>");
		notifyProgressMessage("<message>Loaded. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");

		
		isAvailable=true;
		
		
	}

}