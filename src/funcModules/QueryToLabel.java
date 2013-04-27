package funcModules;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkQuery;
import types.FrameworkTypes;
import types.Matrix;
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkInformationEntity;

public class QueryToLabel  extends AbstractProcessImpl implements EnrichmentProcess{
	private String queryVariableName;
	private String outputPropertyString="http://www.bootstrep.eu/enrichment#extlabel";
	private RDFNetwork myNetwork;
	private boolean suppressLanguage=true; // default

	public QueryToLabel() {
		super();
		this.name="querytolabel";
		this.type=FrameworkTypes.ENRICHER;
		this.description="Pending";
	}
	public void setArgument(FrameworkInformationEntity fInfoEntity) throws WrongType {
		myNetwork = BaseBeat.getOm().getRDFNetwork(fInfoEntity);
		
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equals("queryvar")) {
			queryVariableName = paramValue;
			return true;
		}
		else if(paramName.equals("outputProperty")) {
			outputPropertyString=paramValue;
			return true;
		}
		else if(paramName.equals("suppressLanguage")) {
			if(paramValue.equalsIgnoreCase("true")) suppressLanguage=true;
			else if(paramValue.equalsIgnoreCase("false")) suppressLanguage=false;
			else return false;
			return true;
		}
		else return false;
	}

	public void startProcess() {
		if(queryVariableName==null) {
			notifyErrorMessage("<message>No query variable selected</message>");
			return;
		}
		if(!BaseBeat.getOm().contains(queryVariableName)) {
			notifyErrorMessage("<message>Unknown variable</message>");
			return;
		}
		FrameworkInformationEntity myQueryEntity=BaseBeat.getOm().get(queryVariableName);
		if(myQueryEntity==null) {
			notifyErrorMessage("<message>Unable to recover variable</message>");
			return;
		}
		FrameworkQuery myQuery=BaseBeat.getOm().getFrameworkQuery(myQueryEntity);
		if(myQueryEntity==null) {
			notifyErrorMessage("<message>Unable to recover query from variable</message>");
			return;
		}
		Matrix result=myNetwork.makeRDFQuery(myQuery);
		if(result==null) {
			notifyErrorMessage("<message>Query yielded null!!</message>");
			return;
		}
		notifyProgressMessage("<message>Query performed. Yielded "+result.getSizeString() +" results</message>");
		String[][] resString=result.getMatrixStrings();
		if(resString.length==0) {
			notifyErrorMessage("<message>Result set is empty</message>");
			return;
		}
		if(resString[0].length!=2) {
			notifyErrorMessage("<message>For this function, queries should yield resource - literal pairs</message>");
			return;
		}
		Property myProp=ResourceFactory.createProperty(outputPropertyString);
		for (int i = 0; i < resString.length; i++) {
			Resource res=ResourceFactory.createResource(resString[i][0]);
			Literal lit=ResourceFactory.createTypedLiteral(resString[i][1], XSDDatatype.XSDstring);
			myNetwork.addJenaStatememnt(res,myProp,lit);
			if(i%1000==0) notifyProgressStatus(0, i, resString.length);
			//System.out.println();
		}
		
	}

}
