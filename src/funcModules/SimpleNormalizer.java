/**
 * @author Andrea Splendiani
 * @author Elena Beisswanger
 * 
 * Copyright 2007-2009
 */

// TODO define parameters (new schema ?) and rationalize grouping of  combination steps
package funcModules;

import org.apache.lucene.search.spans.TermSpans;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkInformationEntity;
import Utils.TimeCounter;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.uea.stemmer.UEALite;

/**
 * A normalizer that applies a configurable set of basic methods.
 *
 */
public class SimpleNormalizer extends AbstractProcessImpl implements EnrichmentProcess{
	
	public SimpleNormalizer() {
		super();
		this.name="simplenormalizer";
		// TODO complete this
		this.description="<desc>simplenormalizer applies a set of basic methods to normalize strings</desc>" +
				"<parameters>" +
				"" +
				"</parameters>";
		this.type=FrameworkTypes.ENRICHER;
	}

	private String inputPropertyString="http://www.w3.org/2000/01/rdf-schema#label";	//default
	private String outputPropertyString="http://www.bootstrep.eu/analysis#simpleNormString";	//default
	private RDFNetwork myNetwork=null;
	private int stringcounter=0;
	private int changedcounter=0;
	
	private boolean applyBasicCommon=true;
	private boolean applyEvidenceCodeCommonRemoval=false;
	private boolean applyGOMF=false;
	private boolean applyDiseaseOntologyCommonRemoval=false;
	private boolean applyChemicalOntologyNorm=false;
	
	private int minLength=2;
	

	
	public void setArgument(FrameworkInformationEntity fInfoEntity) throws WrongType {
		myNetwork=BaseBeat.getOm().getRDFNetwork(fInfoEntity);
	}

	public void reset() {
		// Nothing to do here
		
	}

	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equals("inputProperty")) {
			inputPropertyString=paramValue;
			return true;
		}
		else if(paramName.equals("outputProperty")) {
			outputPropertyString=paramValue;
			return true;
		}
		else if(paramName.equals("Specific")) {
			if(!paramValue.equalsIgnoreCase("chemical")) {
				applyChemicalOntologyNorm=true;
			}			
			else if(!paramValue.equalsIgnoreCase("EC")) {
				applyEvidenceCodeCommonRemoval=true;
			}
			else if(!paramValue.equalsIgnoreCase("DO")) {
				applyDiseaseOntologyCommonRemoval=true;
			}
			else if(!paramValue.equalsIgnoreCase("GO-MF")) {
				applyGOMF=true;
			}
			else return false;
			return true;
		}
		else return false;		
	}

	public void startProcess() {
		if(myNetwork==null) {
			notifyDebugMessage("<message>RDFNetwork undefined</message>");
		}
		
		notifyProgressMessage("<message>Starting simple normalization</message>");
		
		changedcounter=0;
		stringcounter=0;
		TimeCounter tc=TimeCounter.getTimeCounter();
		
		Property inputProperty=ResourceFactory.createProperty(inputPropertyString);
		Property targetProperty=ResourceFactory.createProperty(outputPropertyString);
		StmtIterator statementList=myNetwork.getJenaModel(true).listStatements(null, inputProperty, (Literal)null);
		
		
		
		
		while(statementList.hasNext()) {
			Statement stat=statementList.nextStatement();
			RDFNode termNode=stat.getObject();
			Resource sourceNode=stat.getSubject();
			String termString=null;
			String termProcessedString=null;
			if(termNode.isLiteral()) termString=((Literal)termNode).getString();
			if(termString!=null) {
					termProcessedString=normalizeString(termString);
					if(termProcessedString!=null) {
						Literal object=ResourceFactory.createTypedLiteral(termProcessedString, XSDDatatype.XSDstring);
						myNetwork.addJenaStatememnt(sourceNode, targetProperty, object);
						
						//System.out.println(sourceNode.getURI()+" : \n"+termString+"\n"+termProcessedString);
					}
			}
			
		}
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message> "+changedcounter+" out of "+stringcounter+" strings where altered</message>");
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>Done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
	}

	private String normalizeString(String termString) {
		//System.out.println("Attempting to normalize \""+termString+"\"");
		UEALite stemmer=new UEALite();
		String result=new String(termString);
		result=result.trim();
		if(applyBasicCommon) {
			result=result.toLowerCase();
			result=result.replaceAll("[_|-]", " ");
			result=result.trim();
			if(result.indexOf(" ")==-1) {
				//System.out.println("In: "+termString+" out: "+result);
				if(result.length()>0) return result;
				else return "";
			}
			
			String[] words=result.split(" ");
			
			result="";
			//stem last token of word
			for (int i = 0; i < words.length-1; i++) {
				// TODO Stemming should be here
				// result+=(stemmer.stem(words[words.length - 1])).toString();
				
				result+= words[i] +" ";				
			}
			result+= words[words.length-1] ;
			
			
			result.trim();
		}
		if(applyEvidenceCodeCommonRemoval) {
			result=result.replaceAll("inferred from", "");
		}
		if(false) {
			result=result.replaceAll("\\(.*[Ss]ensu[s]* .+\\)", "");
		}
		if(applyDiseaseOntologyCommonRemoval) {
			result=result.replaceAll("\\(disorder\\)", "");
			result=result.replaceAll("\\(finding\\)", "");
			result=result.replaceAll("\\[Ambigous\\]", "");
		}
		if(applyChemicalOntologyNorm) {
			
		}
		if(applyGOMF) {
			result=result.replaceAll("activity$", "");
		}
		if(!applyChemicalOntologyNorm) {
			result=result.replaceAll("\\{", " ");
			result=result.replaceAll("\\}", " ");
			result=result.replaceAll("\\(", " ");
			result=result.replaceAll("\\)", " ");
			result=result.replaceAll("\\[", " ");
			result=result.replaceAll("\\]", " ");
			result=result.replaceAll("\\.", " ");
			result=result.replaceAll("\\;", " ");
			result=result.replaceAll("\\:", " ");
			result=result.replaceAll("\\,", " ");
		}
		if(result!=null) result=result.trim();
		if(applyBasicCommon) {
			result=result.replaceAll("^[0-9]+$","");
			result=result.replaceAll("[\\s]+", " ");
		}
		
		if(result.length()<=minLength) result=null;
		if(!termString.equals(result)) changedcounter++;
		stringcounter++;
		return result;
	}

	






}
