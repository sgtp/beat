package funcModules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import system.BaseBeat;
import types.AbstractProcessImpl;
import types.FrameworkTypes;
import types.Matrix;
import types.OWLOntology;
import types.WrongType;
import types.interfaces.FrameworkInformationEntity;
import types.interfaces.SingleFunctionProcess;

public class FindRelatedOntologies extends AbstractProcessImpl implements SingleFunctionProcess {
	OWLOntology myOntology=null;
	Matrix myResult=null;
	protected ArrayList propertyList;
	protected Property[] propertyArray;
	protected Property[] myPropertyArray;
	
	
	public FindRelatedOntologies() {
		super();
		this.name="findRelatedOntologies";
		this.type=FrameworkTypes.TRANSLATOR;
		this.description="<description><name>findrelatedontologies</name>" +
				"<descriptionText>Returns words' overlap values between this ontology and the other ontologies in memory</descriptionText>" +
				"</description>";
		propertyList=new ArrayList();
	}


	public void setArgument(FrameworkInformationEntity myOnto) throws WrongType {
		try {
			myOntology=(OWLOntology) myOnto;
		} catch (Exception e) {
			throw new WrongType(myOnto.getType()+" instead of OWLOntology");
		}
	}

	
	public FrameworkInformationEntity getResult() {
		return myResult;
	}

	
	

	
	public void reset() {
		

	}

	
	public boolean setParam(String paramName, String paramValue) {
		if(paramName.equals("addProperty")) {
			propertyList.add(paramValue);
			return true;
		}
		else return false;
	}
	

	
	public void startProcess() {
		TreeSet myWords=new TreeSet();
		ArrayList<String[]> resultList=new ArrayList();
		notifyProgressMessage("<message>Starting to look for similar ontologies...</message>");
		notifyProgressMessage("<message>First I look at myself</message>");
		if(propertyList.size()==0) {
			propertyList.add("http://www.w3.org/2000/01/rdf-schema#label");
			notifyDebugMessage("<Message>Property set to default: rdfs:label</Message>");
		}
		propertyArray=expandProperties(propertyList);
		notifyDebugMessage("<Message>Properties expanded</Message>");
		myPropertyArray=filterProp(propertyArray, myOntology.getJenaOntModel());
		OntModel myOntModel=myOntology.getJenaOntModel();
		OntClass[] myClasses=myOntology.getJenaClassesArray();
		notifyDebugMessage("<Message>N.of classes: "+myClasses.length+"</Message>");
		int counter=0;
		int tick=myClasses.length/10;
		for(int cn=0;cn<myClasses.length;cn++) {
			for(int pn=0;pn<myPropertyArray.length;pn++) {
				NodeIterator striter= myOntModel.listObjectsOfProperty(myClasses[cn], myPropertyArray[pn]);
				while(striter.hasNext()) {
					RDFNode node=striter.nextNode();
					String longTerm=filterString(node.toString());
					String[] tokens=longTerm.split(" ");
					for(int inner=0;inner<tokens.length;inner++)
						myWords.add(tokens[inner]);
				}
			}
			counter++;
			if(counter>tick) {
				counter=0;
				notifyProgressStatus(0, cn,myClasses.length );
			}
		}
		notifyDebugMessage("<Message>I contain "+myWords.size()+" words </Message>");
		
		FrameworkInformationEntity[] varList=BaseBeat.getOm().getVarList();
		notifyProgressMessage("<message>Now I look at the other ontologies I'm aware of </message>");
		
		for(int varN=0;varN<varList.length;varN++) {
			notifyProgressMessage("<Message>Comparing to "+varList[varN].getName()+"</Message>");
			OWLOntology otherOntology=null;
			boolean isOntology=false;
			try {
				otherOntology=(OWLOntology) varList[varN];
				isOntology=true;
			}
			catch (Exception e) {
				isOntology=false;
				notifyDebugMessage("<Message>"+varList[varN].getName()+" is not an Ontology</Message>");
			}
			if(isOntology) {
				///////////////////////
				HashSet otherWords=new HashSet();
				Property[] otherPropertyArray=filterProp(propertyArray, myOntology.getJenaOntModel());
				OntModel otherOntModel=otherOntology.getJenaOntModel();
				OntClass[] otherClasses=otherOntology.getJenaClassesArray();
				notifyDebugMessage("<Message>N.of classes: "+otherClasses.length+"</Message>");
				int counter2=0;
				int tick2=otherClasses.length/10;
				for(int cn=0;cn<otherClasses.length;cn++) {
					for(int pn=0;pn<otherPropertyArray.length;pn++) {
						NodeIterator striter= otherOntModel.listObjectsOfProperty(otherClasses[cn], otherPropertyArray[pn]);
						while(striter.hasNext()) {
							RDFNode node=striter.nextNode();
							String longTerm=filterString(node.toString());
							String[] tokens=longTerm.split(" ");
							for(int inner=0;inner<tokens.length;inner++)
								otherWords.add(tokens[inner]);
						}
					}
					counter2++;
					if(counter2>tick) {
						counter2=0;
						notifyProgressStatus(0, cn,otherClasses.length );
					}
				}
				
				int total=myWords.size()+otherWords.size();
				int leftTotal=myWords.size();
				TreeSet newSet=new TreeSet(myWords);
				newSet.retainAll(otherWords);
				int overIndex=(newSet.size()*200)/(total);
				int leftCoverageIndex=(newSet.size()*100)/(leftTotal);
				
				String[] resItem=new String[3];
				resItem[0]=otherOntology.getName();
				resItem[1]=overIndex+"%";
				resItem[2]=leftCoverageIndex+"%";
				resultList.add(resItem);
				notifyProgressMessage("<Message>"+myOntology.getName()+" with "+otherOntology.getName() +" overlap= "+overIndex+"% left coverage="+leftCoverageIndex+"%</Message>");
				
				
			}
		}
		
		String[][] result=new String[resultList.size()][3];
		for(int i=0;i<resultList.size();i++) {
			result[i][0]=resultList.get(i)[0];
			result[i][1]=resultList.get(i)[1];
			result[i][2]=resultList.get(i)[2];
		}
		myResult=new Matrix(result);
	}

	
	
	
	protected Property[] expandProperties(ArrayList pArray) {
		Property[] properties=new Property[pArray.size()];
		Iterator piter=pArray.iterator();
		int i=0;
		while(piter.hasNext()) {
			String pel=(String)piter.next();
			properties[i]=ResourceFactory.createProperty(pel);
			i++;
		}
		return properties;
	}
	
	/**
	 * returns the subset of the array of properties that are present in the Ontology
	 * @param expandProperties
	 * @param onto1
	 * @return
	 */
	protected Property[] filterProp(Property[] properties, OntModel myModel) {
		notifyDebugMessage("<Message>Checking if properties exists in model</Message>");
		int ok=0;
		for(int i=0;i<properties.length;i++) {
			
			if(myModel.containsResource(properties[i])) {
				notifyDebugMessage("<Message>Found "+properties[i]+"</Message>");
				ok++;
			}
			else {
				properties[i]=null;
				

			}
		}
		Property[] newPSet=new Property[ok];
		int j=0;
		for(int i=0;i<properties.length;i++) {
			if(properties[i]!=null) {
				newPSet[j]=properties[i];
				j++;
			}
		}	
		notifyDebugMessage("<Message>Total properties on this ontology: "+newPSet.length+"</Message>");
		// TODO Auto-generated method stub
		return newPSet;
	}

	protected String filterString(String string) {
		
			int lastAt=string.lastIndexOf('@');
			if(lastAt>=string.length()-4 && lastAt>0) return string.substring(0, lastAt);
			return string;
		
	}

}
