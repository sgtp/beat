grammar simpleOntoMatcher;

@header {
/*UNCOMMENT
package parser;
import commands.*;

import system.CoreComponents;
UNCOMMENT*/
}

prog:   stat;
		//General Commands
stat	:	'verbose'  v1=INT NEWLINE 	{
				String stringLevel=$v1.text;
				/*UNCOMMENT 
				int level=Integer.parseInt(stringLevel);
				languageProxy.setVerbose(level); 
				UNCOMMENT*/}
	|	'help' NEWLINE  	{/*UNCOMMENT languageProxy.help(); UNCOMMENT*/}
	|	endWord NEWLINE  	{/*UNCOMMENT languageProxy.endConsole(); UNCOMMENT*/}
	|	'exec' '(' URI ')' NEWLINE { String uri=$URI.text; /*UNCOMMENT languageProxy.execFile(uri);   UNCOMMENT*/  }
	|	'load' '(' URI ')' NEWLINE { String uri=$URI.text; /*UNCOMMENT languageProxy.readFunc(uri);   UNCOMMENT*/  }
	|	'publish' VARNAME NEWLINE { String var=$VARNAME.text; /*UNCOMMENT languageProxy.publish(var);   UNCOMMENT*/  }
	|	'track' VARNAME NEWLINE	{ String var=$VARNAME.text; /*UNCOMMENT  languageProxy.track(var); UNCOMMENT*/}
	|	'shutdown'  NEWLINE	{ /*UNCOMMENT languageProxy.endSystem(); UNCOMMENT*/}
	
	
		//Descriptive commands
	|	'list' 'functions' NEWLINE 	{/*UNCOMMENT languageProxy.listfunc(); UNCOMMENT*/}
	|	'list' 'variables' NEWLINE 	{/*UNCOMMENT languageProxy.listvar(); UNCOMMENT*/}	
	|	'describe' VARNAME NEWLINE {String itemname=$VARNAME.text;  /*UNCOMMENT languageProxy.describe(itemname);   UNCOMMENT*/  }
	|	'list' vi=item 'of' VARNAME NEWLINE {	String varType=$vi.text;
				               	String varName=$VARNAME.text;  
					/*UNCOMMENT 
					languageProxy.listVarContent(varName,varType);   
					UNCOMMENT*/  }	
	
					
					 
		//Variables manipulation commands (load,copy,delete, in-out db)		 
	|	'delete'  VARNAME  NEWLINE {String varname=$VARNAME.text;  /*UNCOMMENT languageProxy.deleteVarl(varname);   UNCOMMENT*/  }
	|	'make' VARNAME  'persistent'  NEWLINE {String varname=$VARNAME.text;  /*UNCOMMENT languageProxy.makePersistent(varname);   UNCOMMENT*/  }
	|	'make' VARNAME  'hard' {String varname=$VARNAME.text;  /*UNCOMMENT languageProxy.collapseToBaseModel(varname);   UNCOMMENT*/  }
	|	VARNAME'=' 'read' 'ontology' '(' URI ')' NEWLINE {
			String varname=$VARNAME.text; 
			String uri=$URI.text; 
			/*UNCOMMENT languageProxy.readowl(varname,uri);   UNCOMMENT*/  
			}
	|	VARNAME'=' 'read'  'ontology' '(' URI ')' 'directly''in' 'db' NEWLINE {
			String varname=$VARNAME.text; 
			String uri=$URI.text; 
			/*UNCOMMENT languageProxy.readowldirect(varname,uri);   UNCOMMENT*/  
			}
	|	VARNAME'=' 'read' 'alignment' '(' URI ')' 'with' 'format' fileformat NEWLINE {
			String varname=$VARNAME.text; 
			String uri=$URI.text; 
			String format=$fileformat.text;
			/*UNCOMMENT languageProxy.readalign(varname,uri,format);   UNCOMMENT*/  
			}	
	|	'export' VARNAME 'to' FILENAME 'with' 'format' fileformat NEWLINE {
			String var=$VARNAME.text;
			String file=$FILENAME.text;
			String format=$fileformat.text;
			/*UNCOMMENT  languageProxy.export(var,file,format); UNCOMMENT*/
	}		
	|	v1=VARNAME '=' v2=VARNAME NEWLINE {String var1=$v1.text; String var2=$v2.text; /*UNCOMMENT languageProxy.copy(var2,var1); UNCOMMENT*/}
	|	VARNAME '=' lang1=querylanguagehead  'query' query=QUERYORRULE {
				String queryString=$query.text; 
				String var=$VARNAME.text;
				String langString=$lang1.text; 
				/*UNCOMMENT  languageProxy.recordQuery(queryString,var,langString); UNCOMMENT*/}
	|	VARNAME '=' lang2=rulelanguagehead 'rules' rules=QUERYORRULE {
				String rulesString=$rules.text; 
				String var=$VARNAME.text; 
				String langString=$lang2.text; 
				/*UNCOMMENT  languageProxy.recordRules(rulesString,var,langString); UNCOMMENT*/}
	|	'add' 'comment' QUERYORRULE 'to' VARNAME NEWLINE {
				String comment=$QUERYORRULE.text;
				String varname=$VARNAME.text;
				/*UNCOMMENT  languageProxy.addComment(comment,varname); UNCOMMENT*/
	}	
		 //Extraction/query commands
						
	|	result=VARNAME '=' 'make' qt=querytype 'query' query=VARNAME  'on' target=VARNAME NEWLINE 
		{String resultString=$result.text; 
		String queryString=$query.text;
		String targetString=$target.text;
		String queryType=$qt.text;
		 /*UNCOMMENT  languageProxy.makeQuery(queryString,resultString,targetString,queryType); UNCOMMENT*/}
	|	target=VARNAME'=' 'extract' what=item 'from' source=VARNAME NEWLINE {
			String targetName=$target.text;
			String sourceName=$source.text;
			String type=$what.text;
			 /*UNCOMMENT  languageProxy.extract(sourceName, targetName, type); UNCOMMENT*/
		}
	|	 'search' search=QUERYORRULE 'in' target=VARNAME NEWLINE {
			
			String searchString=$search.text;
			String targetString=$target.text;
			 /*UNCOMMENT  languageProxy.makeSearch(searchString,targetString); UNCOMMENT*/
	}
	
	
		
		//Transformation related commands
		
	|	
		'make' reasoningType  VARNAME  NEWLINE{
		String resType=$reasoningType.text; 
		String myVar=$VARNAME.text; 
		/*UNCOMMENT  languageProxy.addInference(myVar,resType); UNCOMMENT*/}
	
	|	VARNAME'=' 'union' '(' varList ')' NEWLINE { 
			String varTarget=$VARNAME.text;
			ArrayList vars=$varList.vars;
			/*UNCOMMENT  languageProxy.makeUnion(vars,varTarget); UNCOMMENT*/}
	|	VARNAME'=' 'intersection' '(' varList ')' NEWLINE { 
			String varTarget=$VARNAME.text;
			ArrayList vars=$varList.vars;
			/*UNCOMMENT  languageProxy.makeIntersection(vars,varTarget); UNCOMMENT*/}
	|	'set' 'prefix' pre=uriorvar 'for' postfix=uriorvar 'in' varn=VARNAME NEWLINE {
			String pr=$pre.text;
			String uri=$postfix.text;
			String varName=$varn.text;
			/*UNCOMMENT languageProxy.setPrefix(pr, uri, varName); UNCOMMENT*/
		}
	|	v1=VARNAME'='   f=VARNAME '('  o1=VARNAME ',' o2=VARNAME ')' 'with' 'params' paramlist NEWLINE 
		{String varTarget=$v1.text; 
		String function=$f.text;
		 String onto1=$o1.text; 
		 String onto2=$o2.text; 
		 ArrayList parameters=$paramlist.params; 
		/*UNCOMMENT languageProxy.perfomMatchingAndSaveTo(function,onto1,onto2,parameters,varTarget,false); UNCOMMENT*/
		}
	|	v1=VARNAME'=' '&'   f=VARNAME '('  o1=VARNAME ',' o2=VARNAME ')' 'with' 'params' paramlist NEWLINE 
		{String varTarget=$v1.text; 
		String function=$f.text;
		 String onto1=$o1.text; 
		 String onto2=$o2.text; 
		 ArrayList parameters=$paramlist.params; 
		/*UNCOMMENT languageProxy.perfomMatchingAndSaveTo(function,onto1,onto2,parameters,varTarget,true); UNCOMMENT*/
		}
		
	|	 'apply '  f=VARNAME 'to'  o=VARNAME    'with' 'params' paramlist NEWLINE 
		{
		String function=$f.text;
		 String onto=$o.text;  
		 ArrayList parameters=$paramlist.params; 
		/*UNCOMMENT languageProxy.perfomTransformation(function,onto,parameters,false); UNCOMMENT*/
		}
	|	 'apply&' f=VARNAME 'to'  o=VARNAME    'with' 'params' paramlist NEWLINE 
		{
		String function=$f.text;
		 String onto=$o.text;  
		 ArrayList parameters=$paramlist.params; 
		/*UNCOMMENT languageProxy.perfomTransformation(function,onto,parameters,true); UNCOMMENT*/
		}
	|	v1=VARNAME  '='    f=VARNAME '('  o=VARNAME  ')'  'with' 'params' paramlist NEWLINE 
		{
		String function=$f.text;
		 String onto=$o.text;  
		 ArrayList parameters=$paramlist.params;  
		 String target1=$v1.text;
		/*UNCOMMENT languageProxy.perfomTranslationAndSaveTo(function,onto,target1,parameters,false); UNCOMMENT*/
		}
	|	v1=VARNAME  '='  '&' f=VARNAME '('  o=VARNAME  ')'  'with' 'params' paramlist NEWLINE 
		{
		String function=$f.text;
		 String onto=$o.text;  
		 String target2=$v1.text;
		 ArrayList parameters=$paramlist.params; 
		/*UNCOMMENT languageProxy.perfomTranslationAndSaveTo(function,onto,target2,parameters,true); UNCOMMENT*/
		}
	
	|	 'add' 'rules' rules=VARNAME 'to' myVar2=VARNAME   NEWLINE 
		{
		String rulesString=$rules.text;
		 String varString=$myVar2.text;  
		 /*UNCOMMENT  languageProxy.applyRules(rulesString,varString); UNCOMMENT*/
		
		}
	|	NEWLINE
	;
	
	
	
paramlist returns [ArrayList params]
	:	{params=new ArrayList();}
		( VARNAME '=' uriorvar {String[] localParam=new String[2]; 
		localParam[0]=$VARNAME.text; 
		localParam[1]=$uriorvar.text; 
		params.add(localParam);} 
		)*
	|	'none'
	;
varList returns [ArrayList vars]
	:	{vars=new ArrayList();}
		( VARNAME  {
		vars.add($VARNAME.text);} 
		)*
	;		
uriorvar returns [String text]	
	:	URI {$text=$URI.text;}
	| 	VARNAME {$text=$VARNAME.text;}
	|	INT{$text=$INT.text;}
	;		 
item	:	'classes'
	|	'individuals'
	|	'properties'
	|	'datatypeproperties'
	|	'objectproperties'
	|	'annotationproperties'
	|	'all'
	;	                

querylanguagehead 
	:	'rdql'
	|	'sparql'
	;
rulelanguagehead 
	:	'jena'
	|	'swrl'
	;
fileformat 
	:	'.txt'
	|	'.cell'
	|	'.n3'
	|	'.rdf-xml'
	|	'.rdf-xml-abbv'
	|	'.cowl'
	;	

reasoningType
	:	'RDFS'
	|	'OWL'
	|	'trans'
	|	'none'
	;
querytype	:	'select'
	|	'construct'
	;	
endWord	:	'end'
	|	'exit'
	|	'quit'
	|	'bye'
	|	'ciao'
	|	'sayonara'
	|	'kenavo'
	;

VARNAME  	:   	('a'..'z'|'A'..'Z'|'-'|'_')+('0'..'9')* ;
FILENAME	: 	('a'..'z'|'A'..'Z'|'-'|'_'|'0'..'9'|'/'|'\\')+'.'('a'..'z'|'A'..'Z'|'-'|'0'..'9')+ ;
QUERYORRULE	:	'""'('a'..'z'|'A'..'Z'|'-'|'_'|' '|'0'..'9'|':'|'%'|'/'|'<'|'>'|'{'|'}'|'?'|'('|')'|'#'|'.'|'\n'|'\r'|'\\'|'~'|'*')+'""' ;
PATTERNVAR	:	'?'('a'..'z'|'A'..'Z'|'-'|'_'|'0'..'9')+ ;	
URI	: 	('a'..'z')+':'('a'..'z'|'A'..'Z'|'-'|'_'|'0'..'9'|':'|'%'|'/'|'#'|'.'|'?')+ ;
INT 	:   	'0'..'9'+ ;
NEWLINE	:	('\r'? '\n'|';') ;
WS  	:   	(' '|'\t')+ {skip();} ;
