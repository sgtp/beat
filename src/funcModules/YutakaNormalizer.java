package funcModules;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
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
import types.RDFNetwork;
import types.WrongType;
import types.interfaces.EnrichmentProcess;
import types.interfaces.FrameworkInformationEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;

public class YutakaNormalizer extends AbstractProcessImpl implements EnrichmentProcess{
	public YutakaNormalizer() {
		super();
		this.name="yutakanormalizer";
		this.description="pending";
		this.type=FrameworkTypes.ENRICHER;
	}

	private String inputPropertyString="http://www.w3.org/2000/01/rdf-schema#label";	//default
	private String outputPropertyString="http://www.bootstrep.eu/analysis#yutakaNormString";	//default
	private RDFNetwork myNetwork=null;
	private static int GOCHEBI=1;
	private static int PROTNAME=2;
	private static int GOCHEBISTEM=3;
	private static int PROTNAMESTEM=4;
	private int strategy=1; // This is our default
	
	private String parsingFiles[]={"nope.pl","go-chebi-rewrite.pl","bio-rewrite.pl","stemmed-go-chebi-rewrite.pl","stemmed-bio-rewrite.pl"};
	private String parsingScript="";
	private String parsingScriptDir="";
	
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
		else if(paramName.equals("Normalizer")) {
			if(!paramValue.equalsIgnoreCase("go-chebi")) strategy=GOCHEBI;
			else if(!paramValue.equalsIgnoreCase("go-chebi-stem")) strategy=GOCHEBISTEM;
			else if(!paramValue.equalsIgnoreCase("protname")) strategy=PROTNAME;
			else if(!paramValue.equalsIgnoreCase("protname-stem")) strategy=PROTNAMESTEM;
			else return false;
			return true;
		}
		else return false;
		
	}

	public void startProcess() {
		if(myNetwork==null) {
			notifyDebugMessage("<message>RDFNetwork undefined</message>");
		}
		if(strategy==0) {
			notifyDebugMessage("<message>Undefined normalizer</message>");
			return;
		}
		else {
			if(strategy==GOCHEBI)  parsingScript=parsingFiles[GOCHEBI];
			else if(strategy==GOCHEBISTEM) parsingScript=parsingFiles[GOCHEBISTEM];
			else if(strategy==PROTNAME) parsingScript=parsingFiles[PROTNAME];
			else if(strategy==PROTNAMESTEM)parsingScript=parsingFiles[PROTNAMESTEM];
		}
		//now looking for the parsing file.
		String workingDir=System.getProperty("user.dir");
		notifyDebugMessage("<message>Working dir:"+workingDir+"</message>");
		String scriptFile=workingDir+"/frameworkExtTools3/"+parsingScript;
		File script=new File(scriptFile);
		if(!script.exists()) {
			notifyErrorMessage("<message>Unable to find script "+scriptFile+"</message>");
			return;
		}
		notifyUserMessage("<message>Normalizer copyright 2008 by Yutaka Sasaki<br>To be used only within Bootstrep WP6 Activities</message>");
		
		String tempInputFileName=workingDir+"/frameworkExtTools/tempInput.txt";
		File tempInputFile=new File(tempInputFileName);
		tempInputFile.delete();
		FileWriter fo;
		
		try {
			tempInputFile.createNewFile();
			fo=new FileWriter(tempInputFile);
		} catch (IOException e) {
			notifyErrorMessage("<message>Unable to create input file</message>");
			e.printStackTrace();
			return;
		}
		notifyProgressMessage("<message>Setting up input to external script</message>");
		
		//Here we query our knowledge
		Property inputProperty=ResourceFactory.createProperty(inputPropertyString);
		ArrayList<Resource> resources=new ArrayList<Resource>();
		StmtIterator statementList=myNetwork.getJenaModel(true).listStatements(null, inputProperty, (Literal)null);
		int cc=0;
		try {
			while(statementList.hasNext()) {
				Statement stat=statementList.nextStatement();
				RDFNode termNode=stat.getObject();
				Resource sourceNode=stat.getSubject();
				String termString=null;
				String termProcessedString=null;
				if(termNode.isLiteral()) termString=((Literal)termNode).getString();
				if(termString!=null) {
					fo.write(termString.replaceAll("[\n|\r]", " ")+"\n");
					resources.add(sourceNode);
					cc++;
				}
			}
			fo.close();
		} catch (Exception e) {
			notifyErrorMessage("<message>Unable to write to input file</message>");
			e.printStackTrace();
			return;
		}
		notifyProgressMessage("<message>written "+cc+" lines</message>");
		String[] command=new String[3];
		command[0]="bash";
		command[1]="-c";
		command[2]="cat "+tempInputFileName+" | perl "+scriptFile+" > "+workingDir+"/frameworkExtTools/tempOutput.txt";
		notifyProgressMessage("<message>Calling external script execution:<br>"+
				command[2]
				+"</message>");
		try {
			Process proc = Runtime.getRuntime().exec(command);
		      
			  // Make sure the process exits...
			  try { 
			    proc.waitFor(); 
			  }
			  catch (InterruptedException e) {          
			   notifyDebugMessage("<message>"+ e.getMessage()+"</message>" );
			  }   
		} catch (Exception e) {
			notifyErrorMessage("<message>Unable to run:<br>"+
					command[2]
					+"</message>");
			e.printStackTrace();
			return;
		}
		
		notifyProgressMessage("<message>Parsing result</message>");
		ArrayList<String> linesRead=new ArrayList<String>();
		int dd=0;
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(workingDir+"/frameworkExtTools/tempOutput.txt"));
			String line = inFile.readLine();
			if(line==null) {
				BaseBeat.getSystemNotifier().notifyWarningMessage("<Message>Empty output file</Message>");
				return;
			}
			while(line!=null) {
				linesRead.add(line);
				dd++;
				line = inFile.readLine();
			}
			
		}
		catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Problems in reading file</Message>");
		}
		if(linesRead.size()==cc && resources.size()==cc) notifyProgressMessage("<message>"+cc+" lines read (ok)</message>");
		else {
			notifyErrorMessage("<message>Error!!! Wrote "+cc+" lines, read "+linesRead.size()+"("+dd+") res #="+resources.size()+"</message>");
			return;
		}
		
		String listStringList[]=new String[linesRead.size()];
		listStringList=linesRead.toArray(listStringList);
		Resource[] res=new Resource[resources.size()];
		res=resources.toArray(res);
		Property targetProperty=ResourceFactory.createProperty(outputPropertyString);
		notifyProgressMessage("<message>Adding properties</message>");
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i].toString()+" - "+targetProperty.toString()+" - "+listStringList[i]);
			Literal object=ResourceFactory.createTypedLiteral(listStringList[i], XSDDatatype.XSDstring);
			myNetwork.addJenaStatememnt(res[i], targetProperty, object);
		}
		notifyProgressMessage("<message>Done, thanks & bye.</message>");
	}

	






}
