package funcModules;

import java.util.Enumeration;
import java.util.Hashtable;
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

public class ExperimentalTyper2  extends AbstractProcessImpl implements EnrichmentProcess{
	RDFNetwork myRDFNetwork;
	Hashtable<String,InfoPack> infoPackTable=null;
	
	String sameCuiPropertyString="http://bootstrep.eu/ontologies/enriched#sameUMLSCui";
	String sameStringPropertyString="http://bootstrep.eu/ontologies/enriched#sameString";
	String stringIncludesPropertyString="http://bootstrep.eu/ontologies/enriched#stringIncludes";
	String stringSimilarPropertyString="http://bootstrep.eu/ontologies/enriched#stringSimilar";
	String stringSimilarThroughGeneralizationPropertyString="http://bootstrep.eu/ontologies/enriched#stringSimilarThroughGeneralization";
	
	
	String equivalentToPropertyString="http://bootstrep.eu/ontologies/enriched/analysis#possiblyEquivalentTo";
	String involvedInToPropertyString="http://bootstrep.eu/ontologies/enriched/analysis#possiblyInvolvedIn";
	String moreGeneralString="http://bootstrep.eu/ontologies/enriched/analysis#possiblyMoreGeneralThen";
	String lessGeneralString="http://bootstrep.eu/ontologies/enriched/analysis#possiblyLessGeneralThen";
	String generalizationRelatedString="http://bootstrep.eu/ontologies/enriched/analysis#possiblyRelatedThroughGeneralization";
	String relatedToString="http://bootstrep.eu/ontologies/enriched/analysis#possiblyRelatedTo";
	

	
	
	
	int resolutionTick=1000;
	
	int cuiCounter=0;
	int sameStringCounter=0;
	int similarCounter=0;
	int inclusionCounter=0;
	int genSameStringCounter=0;
	
	int equivalentToCounter=0;
	int involvedIntoCounter=0;
	int moreGeneralCounter=0;
	int lessGeneralCounter=0;
	int generalCounter=0;
	int relatedToCounter=0;
	
	
	public ExperimentalTyper2() {
		super();
		name="experimentaltyper2";
		type=FrameworkTypes.ENRICHER;
		description="<description>" +
				"<descriptionText>Tries to assign a type to basic relations</descriptionText>\n" +
				"<argument>RDFNetwork</argument>"+
				"<parameters></parameters></description>";
		infoPackTable=new Hashtable<String, InfoPack>();
		
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
		notifyProgressMessage("<message>Starting analysis</message>");
		if(myRDFNetwork==null) {
			notifyDebugMessage("<Message>no RDFNetwork found</Message>");
			return;
		}
		int counter=0;
		infoPackTable=new Hashtable<String, InfoPack>();
		
		isAvailable=false;
		TimeCounter tc=TimeCounter.getTimeCounter();
		Property sameCuiProperty=ResourceFactory.createProperty(sameCuiPropertyString);
		Property sameStringProperty=ResourceFactory.createProperty(sameStringPropertyString);
		Property stringIncludesProperty=ResourceFactory.createProperty(stringIncludesPropertyString);
		Property stringSimilarProperty=ResourceFactory.createProperty(stringSimilarPropertyString);
		Property stringSimilarThroughGeneralizationProperty=ResourceFactory.createProperty(stringSimilarThroughGeneralizationPropertyString);
		
		
		Property equivalentToProperty=ResourceFactory.createProperty(equivalentToPropertyString);
		Property involvedInToProperty=ResourceFactory.createProperty(involvedInToPropertyString);
		Property moreGeneral=ResourceFactory.createProperty(moreGeneralString);
		Property lessGeneral=ResourceFactory.createProperty(lessGeneralString);
		Property relatedByGeneralization=ResourceFactory.createProperty(generalizationRelatedString);
		Property relatedTo=ResourceFactory.createProperty(relatedToString);
		
		/**
		 * CUI Analysis
		 */
		
		notifyProgressMessage("<message>Starting CUI analysis</message>");
		StmtIterator iter=myRDFNetwork.getJenaModel(true).listStatements((Resource)null, sameCuiProperty,(Resource) null);
		while(iter.hasNext()) {
			Statement stat=iter.nextStatement();
			Resource subject=stat.getSubject();
			Resource object=(Resource) stat.getObject();
			String uri1=subject.getURI();
			String uri2=subject.getURI();
			String key=null;
			Resource left=null;
			Resource right=null;
			if(uri1.compareTo(uri2)<0) {
				key=uri1+uri2;
				left=subject;
				right=object;
			}
			else {	
				key=uri2+uri1;
				left=object;
				right=subject;
			}
			InfoPack myInfoPack=infoPackTable.get(key);
			if(myInfoPack==null) {
				myInfoPack=new InfoPack();
				myInfoPack.left=left;
				myInfoPack.right=right;
				infoPackTable.put(key, myInfoPack);
			}
			myInfoPack.shareCui=true;
			cuiCounter++;
			if(cuiCounter % resolutionTick==0) {
				notifyProgressMessage("<Message> processed "+cuiCounter+" relations</Message>");
			}
		}
		
		/**
		 * Same String Analysis
		 */
		
		notifyProgressMessage("<message>Same String analysis</message>");
		iter=myRDFNetwork.getJenaModel(true).listStatements((Resource)null, sameStringProperty,(Resource) null);
		while(iter.hasNext()) {
			Statement stat=iter.nextStatement();
			Resource subject=stat.getSubject();
			Resource object=(Resource) stat.getObject();
			String uri1=subject.getURI();
			String uri2=subject.getURI();
			String key=null;
			Resource left=null;
			Resource right=null;
			if(uri1.compareTo(uri2)<0) {
				key=uri1+uri2;
				left=subject;
				right=object;
			}
			else {	
				key=uri2+uri1;
				left=object;
				right=subject;
			}
			InfoPack myInfoPack=infoPackTable.get(key);
			if(myInfoPack==null) {
				myInfoPack=new InfoPack();
				myInfoPack.left=left;
				myInfoPack.right=right;
				infoPackTable.put(key, myInfoPack);
			}
			myInfoPack.sameString=true;
			sameStringCounter++;
			if(sameStringCounter % resolutionTick==0) {
				notifyProgressMessage("<Message> processed "+sameStringCounter+" relations</Message>");
			}
		}
		
		
		/**
		 * Similar String Analysis
		 */
		
		notifyProgressMessage("<message>Starting Similar String analysis</message>");
		iter=myRDFNetwork.getJenaModel(true).listStatements((Resource)null, stringSimilarProperty,(Resource) null);
		while(iter.hasNext()) {
			Statement stat=iter.nextStatement();
			Resource subject=stat.getSubject();
			Resource object=(Resource) stat.getObject();
			String uri1=subject.getURI();
			String uri2=subject.getURI();
			String key=null;
			Resource left=null;
			Resource right=null;
			if(uri1.compareTo(uri2)<0) {
				key=uri1+uri2;
				left=subject;
				right=object;
			}
			else {	
				key=uri2+uri1;
				left=object;
				right=subject;
			}
			InfoPack myInfoPack=infoPackTable.get(key);
			if(myInfoPack==null) {
				myInfoPack=new InfoPack();
				myInfoPack.left=left;
				myInfoPack.right=right;
				infoPackTable.put(key, myInfoPack);
			}
			myInfoPack.similarString=true;
			similarCounter++;
			if(similarCounter % resolutionTick==0) {
				notifyProgressMessage("<Message> processed "+similarCounter+" relations</Message>");
			}
		}
		
		/**
		 * String inclusion analysis
		 */
		
		notifyProgressMessage("<message>Starting String inclusion analysis</message>");
		iter=myRDFNetwork.getJenaModel(true).listStatements((Resource)null, stringIncludesProperty,(Resource) null);
		while(iter.hasNext()) {
			Statement stat=iter.nextStatement();
			Resource subject=stat.getSubject();
			Resource object=(Resource) stat.getObject();
			String uri1=subject.getURI();
			String uri2=subject.getURI();
			String key=null;
			Resource left=null;
			Resource right=null;
			boolean reverse=false;
			if(uri1.compareTo(uri2)<0) {
				key=uri1+uri2;
				left=subject;
				right=object;
				reverse=false;
			}
			else {	
				key=uri2+uri1;
				left=object;
				right=subject;
				reverse=true;
			}
			InfoPack myInfoPack=infoPackTable.get(key);
			if(myInfoPack==null) {
				myInfoPack=new InfoPack();
				myInfoPack.left=left;
				myInfoPack.right=right;
				infoPackTable.put(key, myInfoPack);
			}
			if(!reverse) myInfoPack.leftIncludes=true;
			else myInfoPack.rightIncludes=true;
			
			inclusionCounter++;
			if(inclusionCounter % resolutionTick==0) {
				notifyProgressMessage("<Message> processed "+inclusionCounter+" relations</Message>");
			}
		}
		
		
		
		/**
		 * String similar through generalization analysis
		 */
		
		notifyProgressMessage("<message>String similar through generalization analysis</message>");
		iter=myRDFNetwork.getJenaModel(true).listStatements((Resource)null, stringSimilarThroughGeneralizationProperty,(Resource) null);
		while(iter.hasNext()) {
			Statement stat=iter.nextStatement();
			Resource subject=stat.getSubject();
			Resource object=(Resource) stat.getObject();
			String uri1=subject.getURI();
			String uri2=subject.getURI();
			String key=null;
			Resource left=null;
			Resource right=null;
			if(uri1.compareTo(uri2)<0) {
				key=uri1+uri2;
				left=subject;
				right=object;
			}
			else {	
				key=uri2+uri1;
				left=object;
				right=subject;
			}
			InfoPack myInfoPack=infoPackTable.get(key);
			if(myInfoPack==null) {
				myInfoPack=new InfoPack();
				myInfoPack.left=left;
				myInfoPack.right=right;
				infoPackTable.put(key, myInfoPack);
			}
			myInfoPack.generic=true;
			genSameStringCounter++;
			if(genSameStringCounter % resolutionTick==0) {
				notifyProgressMessage("<Message> processed "+genSameStringCounter+" relations</Message>");
			}
		}
			
		
		notifyProgressMessage("<Message> Starting typing analysis</Message>");
		
		
		Enumeration<InfoPack> infoPacks=infoPackTable.elements();
		counter=0;
		while(infoPacks.hasMoreElements()) {
			InfoPack tempInfoPack=infoPacks.nextElement();
			/*
			 * Here is the logic
			 */
			if(tempInfoPack.sameString) { 
				equivalentToCounter++;
				System.out.println(tempInfoPack.left.getLocalName() + " equivalentTo "+ tempInfoPack.right.getLocalName());
				myRDFNetwork.addJenaStatememnt(tempInfoPack.left, equivalentToProperty, tempInfoPack.right);
				myRDFNetwork.addJenaStatememnt(tempInfoPack.right, equivalentToProperty, tempInfoPack.left);
			}
			if(tempInfoPack.shareCui) {
				equivalentToCounter++;
				System.out.println(tempInfoPack.left.getLocalName() + " equivalentTo "+ tempInfoPack.right.getLocalName());
				myRDFNetwork.addJenaStatememnt(tempInfoPack.left, equivalentToProperty, tempInfoPack.right);
				myRDFNetwork.addJenaStatememnt(tempInfoPack.right, equivalentToProperty, tempInfoPack.left);
			}
			else if ((tempInfoPack.leftIncludes) && (tempInfoPack.rightIncludes)) {
				equivalentToCounter++;
				System.out.println(tempInfoPack.left.getLocalName() + " equivalentTo "+ tempInfoPack.right.getLocalName());
				myRDFNetwork.addJenaStatememnt(tempInfoPack.left, equivalentToProperty, tempInfoPack.right);
				myRDFNetwork.addJenaStatememnt(tempInfoPack.right, equivalentToProperty, tempInfoPack.left);
			}
			else if ((tempInfoPack.leftIncludes) ) {
				involvedIntoCounter++;
				System.out.println(tempInfoPack.left.getLocalName() + " involvedIn "+ tempInfoPack.right.getLocalName());
				myRDFNetwork.addJenaStatememnt(tempInfoPack.left, involvedInToProperty, tempInfoPack.right);
			}
			else if((tempInfoPack.rightIncludes)) {
				involvedIntoCounter++;
				System.out.println(tempInfoPack.right.getLocalName() + " involvedIn "+ tempInfoPack.left.getLocalName());
				myRDFNetwork.addJenaStatememnt(tempInfoPack.right, involvedInToProperty, tempInfoPack.left);
			}
			else if(tempInfoPack.generic) {
				generalCounter++;
				System.out.println(tempInfoPack.right.getLocalName() + " more or less generic "+ tempInfoPack.left.getLocalName());
				myRDFNetwork.addJenaStatememnt(tempInfoPack.left, relatedByGeneralization, tempInfoPack.right);
				myRDFNetwork.addJenaStatememnt(tempInfoPack.right, relatedByGeneralization, tempInfoPack.left);
			}
			else if(tempInfoPack.similarString) {
				relatedToCounter++;
				System.out.println(tempInfoPack.right.getLocalName() + " related To  "+ tempInfoPack.left.getLocalName());
				myRDFNetwork.addJenaStatememnt(tempInfoPack.left, relatedTo, tempInfoPack.right);
				myRDFNetwork.addJenaStatememnt(tempInfoPack.right, relatedTo, tempInfoPack.left);
			}
			else {
				
			}
			counter++;
		}
		
		notifyProgressMessage("<Message> Summary:<br/>" +
				"Found:<br/>" +
				"sameCui asserted: "+cuiCounter+"<br/>" +
				"sameString asserted: "+sameStringCounter+"<br/>" +
				"similarString asserted: "+similarCounter+"<br/>" +
				"inclusionString asserted: "+inclusionCounter+"<br/>" +
				"genSameString asserted: "+genSameStringCounter+"<br/>" +
				"Conlusions:<br/>" +
				"equiavlentTo relations: "+equivalentToCounter+"<br/>" +
				"involved in relations: "+involvedIntoCounter+"<br/>" +
				"more or less general relations: "+generalCounter+"<br/>" +
				"relatedTo relations: "+relatedToCounter+"<br/>" +
				"</Message>");
		
		
		
	
		
		
		notifyProgressMessage("<message>Loaded. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");

		
		isAvailable=true;
		
		
	}
	
	
	private class InfoPack {
		Resource left=null;
		Resource right=null;
		boolean shareCui=false;
		boolean sameString=false;
		boolean similarString=false;
		boolean moreGeneric=false;
		boolean generic=true;
		boolean lessGeneric=false;
		boolean leftIncludes=false;
		boolean rightIncludes=false;
	}
	
	
	/*
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
			*/
	 

}
