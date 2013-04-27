package funcModules;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.OWLOntology;
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkInformationEntity;

public class GoSynToLabel  extends AbstractProcessImpl implements EnrichmentProcess{

	private String inputPropertyString="http://www.geneontology.org/formats/oboInOwl#hasExactSynonym"; //Default
	private String outputPropertyString="http://www.bootstrep.eu/analysis#synonyms"; // Default
	private String rdfsLabel="http://www.w3.org/2000/01/rdf-schema#label";
	private OWLOntology myOntology=null;
	public GoSynToLabel() {
		super();
		this.name="gosyntolabel";
		this.type=FrameworkTypes.ENRICHER;
		this.description="Pending";
	}

	public void setArgument(FrameworkInformationEntity fInfoEntity) throws WrongType {
		myOntology=BaseBeat.getOm().getOWLOntology(fInfoEntity);
		
		
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equals("inputProperty")) {
			inputPropertyString = paramValue;
			return true;
		}
		else if(paramName.equals("outputProperty")) {
			outputPropertyString=paramValue;
			return true;
		}
		else return false;
	}

	public void startProcess() {
		if(myOntology==null) {
			notifyErrorMessage("<message>no valid ontology</message>");
			return;
		}
		Property syProp=ResourceFactory.createProperty(inputPropertyString);
		Property labelProp=ResourceFactory.createProperty(rdfsLabel);
		OntClass[] classes=myOntology.getJenaClassesArray();
		int cc=0;
		for (; cc < classes.length; cc++) {
			StmtIterator statementList=myOntology.getJenaModel(true).listStatements(classes[cc], syProp, (Resource)null);
			while(statementList.hasNext()) {
				Statement stat=statementList.nextStatement();
					RDFNode termNode=stat.getObject();
					String termString=null;
					if(termNode.isLiteral()) termString=((Literal)termNode).getString();
					if(termString!=null) {
						System.out.println(termString);
						Resource synRes=ResourceFactory.createResource(termString);
						StmtIterator statementList2=myOntology.getJenaModel(true).listStatements(synRes, labelProp, (Literal)null);
						while(statementList2.hasNext()) {
							System.out.println("+");
							Statement stat2=statementList2.nextStatement();
							RDFNode labelNode=stat2.getObject();
							String synString=null;
							if(labelNode.isLiteral()) synString=((Literal)labelNode).getString();
							if(synString!=null) System.out.println(">> "+synString);
						}
					}
			}	
		}
	}

}
