package parser;

//$ANTLR 3.1.1 /home/andrea/workspace/beat/simpleOntoMatcher.g 2009-02-18 07:09:58

/*UNCOMMENT
package parser;
import commands.*;

import system.CoreComponents;
UNCOMMENT*/


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class simpleOntoMatcherParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INT", "NEWLINE", "URI", "VARNAME", "FILENAME", "QUERYORRULE", "PATTERNVAR", "WS", "'verbose'", "'help'", "'exec'", "'('", "')'", "'load'", "'publish'", "'track'", "'shutdown'", "'list'", "'functions'", "'variables'", "'describe'", "'of'", "'delete'", "'make'", "'persistent'", "'hard'", "'='", "'read'", "'ontology'", "'directly'", "'in'", "'db'", "'alignment'", "'with'", "'format'", "'export'", "'to'", "'query'", "'rules'", "'add'", "'comment'", "'on'", "'extract'", "'from'", "'search'", "'union'", "'intersection'", "'set'", "'prefix'", "'for'", "','", "'params'", "'&'", "'apply '", "'apply&'", "'none'", "'classes'", "'individuals'", "'properties'", "'datatypeproperties'", "'objectproperties'", "'annotationproperties'", "'all'", "'rdql'", "'sparql'", "'jena'", "'swrl'", "'.txt'", "'.cell'", "'.n3'", "'.rdf-xml'", "'.rdf-xml-abbv'", "'.cowl'", "'RDFS'", "'OWL'", "'trans'", "'select'", "'construct'", "'end'", "'exit'", "'quit'", "'bye'", "'ciao'", "'sayonara'", "'kenavo'"
    };
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__29=29;
    public static final int T__65=65;
    public static final int T__28=28;
    public static final int T__62=62;
    public static final int T__27=27;
    public static final int T__63=63;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int EOF=-1;
    public static final int VARNAME=7;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__19=19;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__16=16;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__15=15;
    public static final int T__53=53;
    public static final int T__18=18;
    public static final int T__54=54;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int FILENAME=8;
    public static final int PATTERNVAR=10;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__59=59;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__80=80;
    public static final int T__46=46;
    public static final int T__81=81;
    public static final int T__47=47;
    public static final int T__82=82;
    public static final int T__44=44;
    public static final int T__83=83;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int INT=4;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__88=88;
    public static final int URI=6;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int WS=11;
    public static final int T__71=71;
    public static final int T__33=33;
    public static final int T__72=72;
    public static final int T__34=34;
    public static final int NEWLINE=5;
    public static final int T__35=35;
    public static final int QUERYORRULE=9;
    public static final int T__70=70;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    LanguageProxy languageProxy=new LanguageProxy();

    // delegates
    // delegators


        public simpleOntoMatcherParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public simpleOntoMatcherParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return simpleOntoMatcherParser.tokenNames; }
    public String getGrammarFileName() { return "/home/andrea/workspace/beat/simpleOntoMatcher.g"; }



    // $ANTLR start "prog"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:12:1: prog : stat ;
    public final void prog() throws RecognitionException {
        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:12:5: ( stat )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:12:9: stat
            {
            pushFollow(FOLLOW_stat_in_prog17);
            stat();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "prog"


    // $ANTLR start "stat"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:14:1: stat : ( 'verbose' v1= INT NEWLINE | 'help' NEWLINE | endWord NEWLINE | 'exec' '(' URI ')' NEWLINE | 'load' '(' URI ')' NEWLINE | 'publish' VARNAME NEWLINE | 'track' VARNAME NEWLINE | 'shutdown' NEWLINE | 'list' 'functions' NEWLINE | 'list' 'variables' NEWLINE | 'describe' VARNAME NEWLINE | 'list' vi= item 'of' VARNAME NEWLINE | 'delete' VARNAME NEWLINE | 'make' VARNAME 'persistent' NEWLINE | 'make' VARNAME 'hard' | VARNAME '=' 'read' 'ontology' '(' URI ')' NEWLINE | VARNAME '=' 'read' 'ontology' '(' URI ')' 'directly' 'in' 'db' NEWLINE | VARNAME '=' 'read' 'alignment' '(' URI ')' 'with' 'format' fileformat NEWLINE | 'export' VARNAME 'to' FILENAME 'with' 'format' fileformat NEWLINE | v1= VARNAME '=' v2= VARNAME NEWLINE | VARNAME '=' lang1= querylanguagehead 'query' query= QUERYORRULE | VARNAME '=' lang2= rulelanguagehead 'rules' rules= QUERYORRULE | 'add' 'comment' QUERYORRULE 'to' VARNAME NEWLINE | result= VARNAME '=' 'make' qt= querytype 'query' query= VARNAME 'on' target= VARNAME NEWLINE | target= VARNAME '=' 'extract' what= item 'from' source= VARNAME NEWLINE | 'search' search= QUERYORRULE 'in' target= VARNAME NEWLINE | 'make' reasoningType VARNAME NEWLINE | VARNAME '=' 'union' '(' varList ')' NEWLINE | VARNAME '=' 'intersection' '(' varList ')' NEWLINE | 'set' 'prefix' pre= uriorvar 'for' postfix= uriorvar 'in' varn= VARNAME NEWLINE | v1= VARNAME '=' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' '&' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE | 'apply ' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE | 'apply&' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' '&' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE | 'add' 'rules' rules= VARNAME 'to' myVar2= VARNAME NEWLINE | NEWLINE );
    public final void stat() throws RecognitionException {
        Token v1=null;
        Token v2=null;
        Token query=null;
        Token rules=null;
        Token result=null;
        Token target=null;
        Token source=null;
        Token search=null;
        Token varn=null;
        Token f=null;
        Token o1=null;
        Token o2=null;
        Token o=null;
        Token myVar2=null;
        Token URI1=null;
        Token URI2=null;
        Token VARNAME3=null;
        Token VARNAME4=null;
        Token VARNAME5=null;
        Token VARNAME6=null;
        Token VARNAME7=null;
        Token VARNAME8=null;
        Token VARNAME9=null;
        Token VARNAME10=null;
        Token URI11=null;
        Token VARNAME12=null;
        Token URI13=null;
        Token VARNAME14=null;
        Token URI15=null;
        Token VARNAME17=null;
        Token FILENAME18=null;
        Token VARNAME20=null;
        Token VARNAME21=null;
        Token QUERYORRULE22=null;
        Token VARNAME23=null;
        Token VARNAME25=null;
        Token VARNAME26=null;
        Token VARNAME28=null;
        simpleOntoMatcherParser.item_return vi = null;

        simpleOntoMatcherParser.querylanguagehead_return lang1 = null;

        simpleOntoMatcherParser.rulelanguagehead_return lang2 = null;

        simpleOntoMatcherParser.querytype_return qt = null;

        simpleOntoMatcherParser.item_return what = null;

        String pre = null;

        String postfix = null;

        simpleOntoMatcherParser.fileformat_return fileformat16 = null;

        simpleOntoMatcherParser.fileformat_return fileformat19 = null;

        simpleOntoMatcherParser.reasoningType_return reasoningType24 = null;

        ArrayList varList27 = null;

        ArrayList varList29 = null;

        ArrayList paramlist30 = null;

        ArrayList paramlist31 = null;

        ArrayList paramlist32 = null;

        ArrayList paramlist33 = null;

        ArrayList paramlist34 = null;

        ArrayList paramlist35 = null;


        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:14:6: ( 'verbose' v1= INT NEWLINE | 'help' NEWLINE | endWord NEWLINE | 'exec' '(' URI ')' NEWLINE | 'load' '(' URI ')' NEWLINE | 'publish' VARNAME NEWLINE | 'track' VARNAME NEWLINE | 'shutdown' NEWLINE | 'list' 'functions' NEWLINE | 'list' 'variables' NEWLINE | 'describe' VARNAME NEWLINE | 'list' vi= item 'of' VARNAME NEWLINE | 'delete' VARNAME NEWLINE | 'make' VARNAME 'persistent' NEWLINE | 'make' VARNAME 'hard' | VARNAME '=' 'read' 'ontology' '(' URI ')' NEWLINE | VARNAME '=' 'read' 'ontology' '(' URI ')' 'directly' 'in' 'db' NEWLINE | VARNAME '=' 'read' 'alignment' '(' URI ')' 'with' 'format' fileformat NEWLINE | 'export' VARNAME 'to' FILENAME 'with' 'format' fileformat NEWLINE | v1= VARNAME '=' v2= VARNAME NEWLINE | VARNAME '=' lang1= querylanguagehead 'query' query= QUERYORRULE | VARNAME '=' lang2= rulelanguagehead 'rules' rules= QUERYORRULE | 'add' 'comment' QUERYORRULE 'to' VARNAME NEWLINE | result= VARNAME '=' 'make' qt= querytype 'query' query= VARNAME 'on' target= VARNAME NEWLINE | target= VARNAME '=' 'extract' what= item 'from' source= VARNAME NEWLINE | 'search' search= QUERYORRULE 'in' target= VARNAME NEWLINE | 'make' reasoningType VARNAME NEWLINE | VARNAME '=' 'union' '(' varList ')' NEWLINE | VARNAME '=' 'intersection' '(' varList ')' NEWLINE | 'set' 'prefix' pre= uriorvar 'for' postfix= uriorvar 'in' varn= VARNAME NEWLINE | v1= VARNAME '=' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' '&' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE | 'apply ' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE | 'apply&' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' '&' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE | 'add' 'rules' rules= VARNAME 'to' myVar2= VARNAME NEWLINE | NEWLINE )
            int alt1=38;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:14:8: 'verbose' v1= INT NEWLINE
                    {
                    match(input,12,FOLLOW_12_in_stat27); 
                    v1=(Token)match(input,INT,FOLLOW_INT_in_stat32); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat34); 

                    				String stringLevel=(v1!=null?v1.getText():null);
                    				
                    				int level=Integer.parseInt(stringLevel);
                    				languageProxy.setVerbose(level); 
                    				

                    }
                    break;
                case 2 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:20:4: 'help' NEWLINE
                    {
                    match(input,13,FOLLOW_13_in_stat42); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat44); 
                    languageProxy.help();

                    }
                    break;
                case 3 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:21:4: endWord NEWLINE
                    {
                    pushFollow(FOLLOW_endWord_in_stat53);
                    endWord();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat55); 
                    languageProxy.endConsole();

                    }
                    break;
                case 4 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:22:4: 'exec' '(' URI ')' NEWLINE
                    {
                    match(input,14,FOLLOW_14_in_stat64); 
                    match(input,15,FOLLOW_15_in_stat66); 
                    URI1=(Token)match(input,URI,FOLLOW_URI_in_stat68); 
                    match(input,16,FOLLOW_16_in_stat70); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat72); 
                     String uri=(URI1!=null?URI1.getText():null); languageProxy.execFile(uri);    

                    }
                    break;
                case 5 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:23:4: 'load' '(' URI ')' NEWLINE
                    {
                    match(input,17,FOLLOW_17_in_stat79); 
                    match(input,15,FOLLOW_15_in_stat81); 
                    URI2=(Token)match(input,URI,FOLLOW_URI_in_stat83); 
                    match(input,16,FOLLOW_16_in_stat85); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat87); 
                     String uri=(URI2!=null?URI2.getText():null); languageProxy.readFunc(uri);    

                    }
                    break;
                case 6 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:24:4: 'publish' VARNAME NEWLINE
                    {
                    match(input,18,FOLLOW_18_in_stat94); 
                    VARNAME3=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat96); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat98); 
                     String var=(VARNAME3!=null?VARNAME3.getText():null); languageProxy.publish(var);    

                    }
                    break;
                case 7 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:25:4: 'track' VARNAME NEWLINE
                    {
                    match(input,19,FOLLOW_19_in_stat105); 
                    VARNAME4=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat107); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat109); 
                     String var=(VARNAME4!=null?VARNAME4.getText():null);  languageProxy.track(var);

                    }
                    break;
                case 8 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:26:4: 'shutdown' NEWLINE
                    {
                    match(input,20,FOLLOW_20_in_stat116); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat119); 
                     languageProxy.endSystem();

                    }
                    break;
                case 9 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:30:4: 'list' 'functions' NEWLINE
                    {
                    match(input,21,FOLLOW_21_in_stat133); 
                    match(input,22,FOLLOW_22_in_stat135); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat137); 
                    languageProxy.listfunc();

                    }
                    break;
                case 10 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:31:4: 'list' 'variables' NEWLINE
                    {
                    match(input,21,FOLLOW_21_in_stat145); 
                    match(input,23,FOLLOW_23_in_stat147); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat149); 
                    languageProxy.listvar();

                    }
                    break;
                case 11 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:32:4: 'describe' VARNAME NEWLINE
                    {
                    match(input,24,FOLLOW_24_in_stat158); 
                    VARNAME5=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat160); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat162); 
                    String itemname=(VARNAME5!=null?VARNAME5.getText():null);  languageProxy.describe(itemname);    

                    }
                    break;
                case 12 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:33:4: 'list' vi= item 'of' VARNAME NEWLINE
                    {
                    match(input,21,FOLLOW_21_in_stat169); 
                    pushFollow(FOLLOW_item_in_stat173);
                    vi=item();

                    state._fsp--;

                    match(input,25,FOLLOW_25_in_stat175); 
                    VARNAME6=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat177); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat179); 
                    	String varType=(vi!=null?input.toString(vi.start,vi.stop):null);
                    				               	String varName=(VARNAME6!=null?VARNAME6.getText():null);  
                    					
                    					languageProxy.listVarContent(varName,varType);   
                    					

                    }
                    break;
                case 13 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:42:4: 'delete' VARNAME NEWLINE
                    {
                    match(input,26,FOLLOW_26_in_stat205); 
                    VARNAME7=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat208); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat211); 
                    String varname=(VARNAME7!=null?VARNAME7.getText():null);  languageProxy.deleteVarl(varname);    

                    }
                    break;
                case 14 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:43:4: 'make' VARNAME 'persistent' NEWLINE
                    {
                    match(input,27,FOLLOW_27_in_stat218); 
                    VARNAME8=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat220); 
                    match(input,28,FOLLOW_28_in_stat223); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat226); 
                    String varname=(VARNAME8!=null?VARNAME8.getText():null);  languageProxy.makePersistent(varname);    

                    }
                    break;
                case 15 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:44:4: 'make' VARNAME 'hard'
                    {
                    match(input,27,FOLLOW_27_in_stat233); 
                    VARNAME9=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat235); 
                    match(input,29,FOLLOW_29_in_stat238); 
                    String varname=(VARNAME9!=null?VARNAME9.getText():null);  languageProxy.collapseToBaseModel(varname);    

                    }
                    break;
                case 16 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:45:4: VARNAME '=' 'read' 'ontology' '(' URI ')' NEWLINE
                    {
                    VARNAME10=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat245); 
                    match(input,30,FOLLOW_30_in_stat246); 
                    match(input,31,FOLLOW_31_in_stat248); 
                    match(input,32,FOLLOW_32_in_stat250); 
                    match(input,15,FOLLOW_15_in_stat252); 
                    URI11=(Token)match(input,URI,FOLLOW_URI_in_stat254); 
                    match(input,16,FOLLOW_16_in_stat256); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat258); 

                    			String varname=(VARNAME10!=null?VARNAME10.getText():null); 
                    			String uri=(URI11!=null?URI11.getText():null); 
                    			languageProxy.readowl(varname,uri);    
                    			

                    }
                    break;
                case 17 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:50:4: VARNAME '=' 'read' 'ontology' '(' URI ')' 'directly' 'in' 'db' NEWLINE
                    {
                    VARNAME12=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat265); 
                    match(input,30,FOLLOW_30_in_stat266); 
                    match(input,31,FOLLOW_31_in_stat268); 
                    match(input,32,FOLLOW_32_in_stat271); 
                    match(input,15,FOLLOW_15_in_stat273); 
                    URI13=(Token)match(input,URI,FOLLOW_URI_in_stat275); 
                    match(input,16,FOLLOW_16_in_stat277); 
                    match(input,33,FOLLOW_33_in_stat279); 
                    match(input,34,FOLLOW_34_in_stat280); 
                    match(input,35,FOLLOW_35_in_stat282); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat284); 

                    			String varname=(VARNAME12!=null?VARNAME12.getText():null); 
                    			String uri=(URI13!=null?URI13.getText():null); 
                    			languageProxy.readowldirect(varname,uri);    
                    			

                    }
                    break;
                case 18 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:55:4: VARNAME '=' 'read' 'alignment' '(' URI ')' 'with' 'format' fileformat NEWLINE
                    {
                    VARNAME14=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat291); 
                    match(input,30,FOLLOW_30_in_stat292); 
                    match(input,31,FOLLOW_31_in_stat294); 
                    match(input,36,FOLLOW_36_in_stat296); 
                    match(input,15,FOLLOW_15_in_stat298); 
                    URI15=(Token)match(input,URI,FOLLOW_URI_in_stat300); 
                    match(input,16,FOLLOW_16_in_stat302); 
                    match(input,37,FOLLOW_37_in_stat304); 
                    match(input,38,FOLLOW_38_in_stat306); 
                    pushFollow(FOLLOW_fileformat_in_stat308);
                    fileformat16=fileformat();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat310); 

                    			String varname=(VARNAME14!=null?VARNAME14.getText():null); 
                    			String uri=(URI15!=null?URI15.getText():null); 
                    			String format=(fileformat16!=null?input.toString(fileformat16.start,fileformat16.stop):null);
                    		//	languageProxy.readalign(varname,uri,format);    
                    			

                    }
                    break;
                case 19 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:61:4: 'export' VARNAME 'to' FILENAME 'with' 'format' fileformat NEWLINE
                    {
                    match(input,39,FOLLOW_39_in_stat318); 
                    VARNAME17=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat320); 
                    match(input,40,FOLLOW_40_in_stat322); 
                    FILENAME18=(Token)match(input,FILENAME,FOLLOW_FILENAME_in_stat324); 
                    match(input,37,FOLLOW_37_in_stat326); 
                    match(input,38,FOLLOW_38_in_stat328); 
                    pushFollow(FOLLOW_fileformat_in_stat330);
                    fileformat19=fileformat();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat332); 

                    			String var=(VARNAME17!=null?VARNAME17.getText():null);
                    			String file=(FILENAME18!=null?FILENAME18.getText():null);
                    			String format=(fileformat19!=null?input.toString(fileformat19.start,fileformat19.stop):null);
                    			 languageProxy.export(var,file,format);
                    	

                    }
                    break;
                case 20 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:67:4: v1= VARNAME '=' v2= VARNAME NEWLINE
                    {
                    v1=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat343); 
                    match(input,30,FOLLOW_30_in_stat345); 
                    v2=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat349); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat351); 
                    String var1=(v1!=null?v1.getText():null); String var2=(v2!=null?v2.getText():null); languageProxy.copy(var2,var1);

                    }
                    break;
                case 21 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:68:4: VARNAME '=' lang1= querylanguagehead 'query' query= QUERYORRULE
                    {
                    VARNAME20=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat358); 
                    match(input,30,FOLLOW_30_in_stat360); 
                    pushFollow(FOLLOW_querylanguagehead_in_stat364);
                    lang1=querylanguagehead();

                    state._fsp--;

                    match(input,41,FOLLOW_41_in_stat367); 
                    query=(Token)match(input,QUERYORRULE,FOLLOW_QUERYORRULE_in_stat371); 

                    				String queryString=(query!=null?query.getText():null); 
                    				String var=(VARNAME20!=null?VARNAME20.getText():null);
                    				String langString=(lang1!=null?input.toString(lang1.start,lang1.stop):null); 
                    				 languageProxy.recordQuery(queryString,var,langString);

                    }
                    break;
                case 22 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:73:4: VARNAME '=' lang2= rulelanguagehead 'rules' rules= QUERYORRULE
                    {
                    VARNAME21=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat378); 
                    match(input,30,FOLLOW_30_in_stat380); 
                    pushFollow(FOLLOW_rulelanguagehead_in_stat384);
                    lang2=rulelanguagehead();

                    state._fsp--;

                    match(input,42,FOLLOW_42_in_stat386); 
                    rules=(Token)match(input,QUERYORRULE,FOLLOW_QUERYORRULE_in_stat390); 

                    				String rulesString=(rules!=null?rules.getText():null); 
                    				String var=(VARNAME21!=null?VARNAME21.getText():null); 
                    				String langString=(lang2!=null?input.toString(lang2.start,lang2.stop):null); 
                    				 languageProxy.recordRules(rulesString,var,langString);

                    }
                    break;
                case 23 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:78:4: 'add' 'comment' QUERYORRULE 'to' VARNAME NEWLINE
                    {
                    match(input,43,FOLLOW_43_in_stat397); 
                    match(input,44,FOLLOW_44_in_stat399); 
                    QUERYORRULE22=(Token)match(input,QUERYORRULE,FOLLOW_QUERYORRULE_in_stat401); 
                    match(input,40,FOLLOW_40_in_stat403); 
                    VARNAME23=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat405); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat407); 

                    				String comment=(QUERYORRULE22!=null?QUERYORRULE22.getText():null);
                    				String varname=(VARNAME23!=null?VARNAME23.getText():null);
                    				// languageProxy.addComment(comment,varname);
                    	

                    }
                    break;
                case 24 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:85:4: result= VARNAME '=' 'make' qt= querytype 'query' query= VARNAME 'on' target= VARNAME NEWLINE
                    {
                    result=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat428); 
                    match(input,30,FOLLOW_30_in_stat430); 
                    match(input,27,FOLLOW_27_in_stat432); 
                    pushFollow(FOLLOW_querytype_in_stat436);
                    qt=querytype();

                    state._fsp--;

                    match(input,41,FOLLOW_41_in_stat438); 
                    query=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat442); 
                    match(input,45,FOLLOW_45_in_stat445); 
                    target=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat449); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat451); 
                    String resultString=(result!=null?result.getText():null); 
                    		String queryString=(query!=null?query.getText():null);
                    		String targetString=(target!=null?target.getText():null);
                    		String queryType=(qt!=null?input.toString(qt.start,qt.stop):null);
                    		  languageProxy.makeQuery(queryString,resultString,targetString,queryType);

                    }
                    break;
                case 25 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:91:4: target= VARNAME '=' 'extract' what= item 'from' source= VARNAME NEWLINE
                    {
                    target=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat463); 
                    match(input,30,FOLLOW_30_in_stat464); 
                    match(input,46,FOLLOW_46_in_stat466); 
                    pushFollow(FOLLOW_item_in_stat470);
                    what=item();

                    state._fsp--;

                    match(input,47,FOLLOW_47_in_stat472); 
                    source=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat476); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat478); 

                    			String targetName=(target!=null?target.getText():null);
                    			String sourceName=(source!=null?source.getText():null);
                    			String type=(what!=null?input.toString(what.start,what.stop):null);
                    			  languageProxy.extract(sourceName, targetName, type);
                    		

                    }
                    break;
                case 26 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:97:5: 'search' search= QUERYORRULE 'in' target= VARNAME NEWLINE
                    {
                    match(input,48,FOLLOW_48_in_stat486); 
                    search=(Token)match(input,QUERYORRULE,FOLLOW_QUERYORRULE_in_stat490); 
                    match(input,34,FOLLOW_34_in_stat492); 
                    target=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat496); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat498); 

                    			
                    			String searchString=(search!=null?search.getText():null);
                    			String targetString=(target!=null?target.getText():null);
                    			  languageProxy.makeSearch(searchString,targetString);
                    	

                    }
                    break;
                case 27 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:109:3: 'make' reasoningType VARNAME NEWLINE
                    {
                    match(input,27,FOLLOW_27_in_stat521); 
                    pushFollow(FOLLOW_reasoningType_in_stat523);
                    reasoningType24=reasoningType();

                    state._fsp--;

                    VARNAME25=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat526); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat529); 

                    		String resType=(reasoningType24!=null?input.toString(reasoningType24.start,reasoningType24.stop):null); 
                    		String myVar=(VARNAME25!=null?VARNAME25.getText():null); 
                    		 languageProxy.addInference(myVar,resType);

                    }
                    break;
                case 28 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:114:4: VARNAME '=' 'union' '(' varList ')' NEWLINE
                    {
                    VARNAME26=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat537); 
                    match(input,30,FOLLOW_30_in_stat538); 
                    match(input,49,FOLLOW_49_in_stat540); 
                    match(input,15,FOLLOW_15_in_stat542); 
                    pushFollow(FOLLOW_varList_in_stat544);
                    varList27=varList();

                    state._fsp--;

                    match(input,16,FOLLOW_16_in_stat546); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat548); 
                     
                    			String varTarget=(VARNAME26!=null?VARNAME26.getText():null);
                    			ArrayList vars=varList27;
                    			 languageProxy.makeUnion(vars,varTarget);

                    }
                    break;
                case 29 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:118:4: VARNAME '=' 'intersection' '(' varList ')' NEWLINE
                    {
                    VARNAME28=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat555); 
                    match(input,30,FOLLOW_30_in_stat556); 
                    match(input,50,FOLLOW_50_in_stat558); 
                    match(input,15,FOLLOW_15_in_stat560); 
                    pushFollow(FOLLOW_varList_in_stat562);
                    varList29=varList();

                    state._fsp--;

                    match(input,16,FOLLOW_16_in_stat564); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat566); 
                     
                    			String varTarget=(VARNAME28!=null?VARNAME28.getText():null);
                    			ArrayList vars=varList29;
                    			// languageProxy.makeIntersection(vars,varTarget);

                    }
                    break;
                case 30 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:122:4: 'set' 'prefix' pre= uriorvar 'for' postfix= uriorvar 'in' varn= VARNAME NEWLINE
                    {
                    match(input,51,FOLLOW_51_in_stat573); 
                    match(input,52,FOLLOW_52_in_stat575); 
                    pushFollow(FOLLOW_uriorvar_in_stat579);
                    pre=uriorvar();

                    state._fsp--;

                    match(input,53,FOLLOW_53_in_stat581); 
                    pushFollow(FOLLOW_uriorvar_in_stat585);
                    postfix=uriorvar();

                    state._fsp--;

                    match(input,34,FOLLOW_34_in_stat587); 
                    varn=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat591); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat593); 

                    			String pr=pre;
                    			String uri=postfix;
                    			String varName=(varn!=null?varn.getText():null);
                    			languageProxy.setPrefix(pr, uri, varName);
                    		

                    }
                    break;
                case 31 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:128:4: v1= VARNAME '=' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE
                    {
                    v1=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat602); 
                    match(input,30,FOLLOW_30_in_stat603); 
                    f=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat609); 
                    match(input,15,FOLLOW_15_in_stat611); 
                    o1=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat616); 
                    match(input,54,FOLLOW_54_in_stat618); 
                    o2=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat622); 
                    match(input,16,FOLLOW_16_in_stat624); 
                    match(input,37,FOLLOW_37_in_stat626); 
                    match(input,55,FOLLOW_55_in_stat628); 
                    pushFollow(FOLLOW_paramlist_in_stat630);
                    paramlist30=paramlist();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat632); 
                    String varTarget=(v1!=null?v1.getText():null); 
                    		String function=(f!=null?f.getText():null);
                    		 String onto1=(o1!=null?o1.getText():null); 
                    		 String onto2=(o2!=null?o2.getText():null); 
                    		 ArrayList parameters=paramlist30; 
                    		languageProxy.perfomMatchingAndSaveTo(function,onto1,onto2,parameters,varTarget,false);
                    		

                    }
                    break;
                case 32 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:136:4: v1= VARNAME '=' '&' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE
                    {
                    v1=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat644); 
                    match(input,30,FOLLOW_30_in_stat645); 
                    match(input,56,FOLLOW_56_in_stat647); 
                    f=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat653); 
                    match(input,15,FOLLOW_15_in_stat655); 
                    o1=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat660); 
                    match(input,54,FOLLOW_54_in_stat662); 
                    o2=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat666); 
                    match(input,16,FOLLOW_16_in_stat668); 
                    match(input,37,FOLLOW_37_in_stat670); 
                    match(input,55,FOLLOW_55_in_stat672); 
                    pushFollow(FOLLOW_paramlist_in_stat674);
                    paramlist31=paramlist();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat676); 
                    String varTarget=(v1!=null?v1.getText():null); 
                    		String function=(f!=null?f.getText():null);
                    		 String onto1=(o1!=null?o1.getText():null); 
                    		 String onto2=(o2!=null?o2.getText():null); 
                    		 ArrayList parameters=paramlist31; 
                    		languageProxy.perfomMatchingAndSaveTo(function,onto1,onto2,parameters,varTarget,true);
                    		

                    }
                    break;
                case 33 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:145:5: 'apply ' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE
                    {
                    match(input,57,FOLLOW_57_in_stat690); 
                    f=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat695); 
                    match(input,40,FOLLOW_40_in_stat697); 
                    o=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat702); 
                    match(input,37,FOLLOW_37_in_stat707); 
                    match(input,55,FOLLOW_55_in_stat709); 
                    pushFollow(FOLLOW_paramlist_in_stat711);
                    paramlist32=paramlist();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat713); 

                    		String function=(f!=null?f.getText():null);
                    		 String onto=(o!=null?o.getText():null);  
                    		 ArrayList parameters=paramlist32; 
                    		languageProxy.perfomTransformation(function,onto,parameters,false);
                    		

                    }
                    break;
                case 34 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:152:5: 'apply&' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE
                    {
                    match(input,58,FOLLOW_58_in_stat724); 
                    f=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat728); 
                    match(input,40,FOLLOW_40_in_stat730); 
                    o=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat735); 
                    match(input,37,FOLLOW_37_in_stat740); 
                    match(input,55,FOLLOW_55_in_stat742); 
                    pushFollow(FOLLOW_paramlist_in_stat744);
                    paramlist33=paramlist();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat746); 

                    		String function=(f!=null?f.getText():null);
                    		 String onto=(o!=null?o.getText():null);  
                    		 ArrayList parameters=paramlist33; 
                    		languageProxy.perfomTransformation(function,onto,parameters,true);
                    		

                    }
                    break;
                case 35 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:159:4: v1= VARNAME '=' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE
                    {
                    v1=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat758); 
                    match(input,30,FOLLOW_30_in_stat761); 
                    f=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat768); 
                    match(input,15,FOLLOW_15_in_stat770); 
                    o=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat775); 
                    match(input,16,FOLLOW_16_in_stat778); 
                    match(input,37,FOLLOW_37_in_stat781); 
                    match(input,55,FOLLOW_55_in_stat783); 
                    pushFollow(FOLLOW_paramlist_in_stat785);
                    paramlist34=paramlist();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat787); 

                    		String function=(f!=null?f.getText():null);
                    		 String onto=(o!=null?o.getText():null);  
                    		 ArrayList parameters=paramlist34;  
                    		 String target1=(v1!=null?v1.getText():null);
                    		languageProxy.perfomTranslationAndSaveTo(function,onto,target1,parameters,false);
                    		

                    }
                    break;
                case 36 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:167:4: v1= VARNAME '=' '&' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE
                    {
                    v1=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat799); 
                    match(input,30,FOLLOW_30_in_stat802); 
                    match(input,56,FOLLOW_56_in_stat805); 
                    f=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat809); 
                    match(input,15,FOLLOW_15_in_stat811); 
                    o=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat816); 
                    match(input,16,FOLLOW_16_in_stat819); 
                    match(input,37,FOLLOW_37_in_stat822); 
                    match(input,55,FOLLOW_55_in_stat824); 
                    pushFollow(FOLLOW_paramlist_in_stat826);
                    paramlist35=paramlist();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat828); 

                    		String function=(f!=null?f.getText():null);
                    		 String onto=(o!=null?o.getText():null);  
                    		 String target2=(v1!=null?v1.getText():null);
                    		 ArrayList parameters=paramlist35; 
                    		languageProxy.perfomTranslationAndSaveTo(function,onto,target2,parameters,true);
                    		

                    }
                    break;
                case 37 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:176:5: 'add' 'rules' rules= VARNAME 'to' myVar2= VARNAME NEWLINE
                    {
                    match(input,43,FOLLOW_43_in_stat841); 
                    match(input,42,FOLLOW_42_in_stat843); 
                    rules=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat847); 
                    match(input,40,FOLLOW_40_in_stat849); 
                    myVar2=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_stat853); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat857); 

                    		String rulesString=(rules!=null?rules.getText():null);
                    		 String varString=(myVar2!=null?myVar2.getText():null);  
                    //		  languageProxy.applyRules(rulesString,varString);
                    		
                    		

                    }
                    break;
                case 38 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:183:4: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat867); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "stat"


    // $ANTLR start "paramlist"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:188:1: paramlist returns [ArrayList params] : ( ( VARNAME '=' uriorvar )* | 'none' );
    public final ArrayList paramlist() throws RecognitionException {
        ArrayList params = null;

        Token VARNAME36=null;
        String uriorvar37 = null;


        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:189:2: ( ( VARNAME '=' uriorvar )* | 'none' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NEWLINE||LA3_0==VARNAME) ) {
                alt3=1;
            }
            else if ( (LA3_0==59) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:189:4: ( VARNAME '=' uriorvar )*
                    {
                    params=new ArrayList();
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:190:3: ( VARNAME '=' uriorvar )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==VARNAME) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:190:5: VARNAME '=' uriorvar
                    	    {
                    	    VARNAME36=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_paramlist893); 
                    	    match(input,30,FOLLOW_30_in_paramlist895); 
                    	    pushFollow(FOLLOW_uriorvar_in_paramlist897);
                    	    uriorvar37=uriorvar();

                    	    state._fsp--;

                    	    String[] localParam=new String[2]; 
                    	    		localParam[0]=(VARNAME36!=null?VARNAME36.getText():null); 
                    	    		localParam[1]=uriorvar37; 
                    	    		params.add(localParam);

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:195:4: 'none'
                    {
                    match(input,59,FOLLOW_59_in_paramlist910); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return params;
    }
    // $ANTLR end "paramlist"


    // $ANTLR start "varList"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:197:1: varList returns [ArrayList vars] : ( VARNAME )* ;
    public final ArrayList varList() throws RecognitionException {
        ArrayList vars = null;

        Token VARNAME38=null;

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:198:2: ( ( VARNAME )* )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:198:4: ( VARNAME )*
            {
            vars=new ArrayList();
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:199:3: ( VARNAME )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==VARNAME) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:199:5: VARNAME
            	    {
            	    VARNAME38=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_varList930); 

            	    		vars.add((VARNAME38!=null?VARNAME38.getText():null));

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return vars;
    }
    // $ANTLR end "varList"


    // $ANTLR start "uriorvar"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:203:1: uriorvar returns [String text] : ( URI | VARNAME | INT );
    public final String uriorvar() throws RecognitionException {
        String text = null;

        Token URI39=null;
        Token VARNAME40=null;
        Token INT41=null;

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:204:2: ( URI | VARNAME | INT )
            int alt5=3;
            switch ( input.LA(1) ) {
            case URI:
                {
                alt5=1;
                }
                break;
            case VARNAME:
                {
                alt5=2;
                }
                break;
            case INT:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:204:4: URI
                    {
                    URI39=(Token)match(input,URI,FOLLOW_URI_in_uriorvar956); 
                    text =(URI39!=null?URI39.getText():null);

                    }
                    break;
                case 2 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:205:5: VARNAME
                    {
                    VARNAME40=(Token)match(input,VARNAME,FOLLOW_VARNAME_in_uriorvar964); 
                    text =(VARNAME40!=null?VARNAME40.getText():null);

                    }
                    break;
                case 3 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:206:4: INT
                    {
                    INT41=(Token)match(input,INT,FOLLOW_INT_in_uriorvar971); 
                    text =(INT41!=null?INT41.getText():null);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return text;
    }
    // $ANTLR end "uriorvar"

    public static class item_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "item"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:208:1: item : ( 'classes' | 'individuals' | 'properties' | 'datatypeproperties' | 'objectproperties' | 'annotationproperties' | 'all' );
    public final simpleOntoMatcherParser.item_return item() throws RecognitionException {
        simpleOntoMatcherParser.item_return retval = new simpleOntoMatcherParser.item_return();
        retval.start = input.LT(1);

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:208:6: ( 'classes' | 'individuals' | 'properties' | 'datatypeproperties' | 'objectproperties' | 'annotationproperties' | 'all' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            {
            if ( (input.LA(1)>=60 && input.LA(1)<=66) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "item"

    public static class querylanguagehead_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "querylanguagehead"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:217:1: querylanguagehead : ( 'rdql' | 'sparql' );
    public final simpleOntoMatcherParser.querylanguagehead_return querylanguagehead() throws RecognitionException {
        simpleOntoMatcherParser.querylanguagehead_return retval = new simpleOntoMatcherParser.querylanguagehead_return();
        retval.start = input.LT(1);

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:218:2: ( 'rdql' | 'sparql' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            {
            if ( (input.LA(1)>=67 && input.LA(1)<=68) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "querylanguagehead"

    public static class rulelanguagehead_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "rulelanguagehead"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:221:1: rulelanguagehead : ( 'jena' | 'swrl' );
    public final simpleOntoMatcherParser.rulelanguagehead_return rulelanguagehead() throws RecognitionException {
        simpleOntoMatcherParser.rulelanguagehead_return retval = new simpleOntoMatcherParser.rulelanguagehead_return();
        retval.start = input.LT(1);

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:222:2: ( 'jena' | 'swrl' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            {
            if ( (input.LA(1)>=69 && input.LA(1)<=70) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rulelanguagehead"

    public static class fileformat_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "fileformat"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:225:1: fileformat : ( '.txt' | '.cell' | '.n3' | '.rdf-xml' | '.rdf-xml-abbv' | '.cowl' );
    public final simpleOntoMatcherParser.fileformat_return fileformat() throws RecognitionException {
        simpleOntoMatcherParser.fileformat_return retval = new simpleOntoMatcherParser.fileformat_return();
        retval.start = input.LT(1);

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:226:2: ( '.txt' | '.cell' | '.n3' | '.rdf-xml' | '.rdf-xml-abbv' | '.cowl' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            {
            if ( (input.LA(1)>=71 && input.LA(1)<=76) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fileformat"

    public static class reasoningType_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "reasoningType"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:234:1: reasoningType : ( 'RDFS' | 'OWL' | 'trans' | 'none' );
    public final simpleOntoMatcherParser.reasoningType_return reasoningType() throws RecognitionException {
        simpleOntoMatcherParser.reasoningType_return retval = new simpleOntoMatcherParser.reasoningType_return();
        retval.start = input.LT(1);

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:235:2: ( 'RDFS' | 'OWL' | 'trans' | 'none' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            {
            if ( input.LA(1)==59||(input.LA(1)>=77 && input.LA(1)<=79) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reasoningType"

    public static class querytype_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "querytype"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:240:1: querytype : ( 'select' | 'construct' );
    public final simpleOntoMatcherParser.querytype_return querytype() throws RecognitionException {
        simpleOntoMatcherParser.querytype_return retval = new simpleOntoMatcherParser.querytype_return();
        retval.start = input.LT(1);

        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:240:11: ( 'select' | 'construct' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            {
            if ( (input.LA(1)>=80 && input.LA(1)<=81) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "querytype"


    // $ANTLR start "endWord"
    // /home/andrea/workspace/beat/simpleOntoMatcher.g:243:1: endWord : ( 'end' | 'exit' | 'quit' | 'bye' | 'ciao' | 'sayonara' | 'kenavo' );
    public final void endWord() throws RecognitionException {
        try {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:243:9: ( 'end' | 'exit' | 'quit' | 'bye' | 'ciao' | 'sayonara' | 'kenavo' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            {
            if ( (input.LA(1)>=82 && input.LA(1)<=88) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "endWord"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    static final String DFA1_eotS =
        "\71\uffff";
    static final String DFA1_eofS =
        "\71\uffff";
    static final String DFA1_minS =
        "\1\5\10\uffff\1\26\2\uffff\1\7\1\36\1\uffff\1\52\10\uffff\1\34\1"+
        "\uffff\1\7\4\uffff\1\40\1\5\4\uffff\1\7\2\uffff\1\17\2\uffff\1\7"+
        "\1\17\1\6\1\20\1\7\1\20\2\uffff\1\20\1\5\4\uffff";
    static final String DFA1_maxS =
        "\1\130\10\uffff\1\102\2\uffff\1\117\1\36\1\uffff\1\54\10\uffff\1"+
        "\35\1\uffff\1\106\4\uffff\1\44\1\17\4\uffff\1\7\2\uffff\1\17\2\uffff"+
        "\1\7\1\17\1\6\1\66\1\7\1\20\2\uffff\1\66\1\41\4\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\uffff\1\13\1\15\2\uffff"+
        "\1\23\1\uffff\1\32\1\36\1\41\1\42\1\46\1\11\1\12\1\14\1\uffff\1"+
        "\33\1\uffff\1\27\1\45\1\16\1\17\2\uffff\1\30\1\31\1\34\1\35\1\uffff"+
        "\1\25\1\26\1\uffff\1\22\1\24\6\uffff\1\37\1\43\2\uffff\1\40\1\44"+
        "\1\20\1\21";
    static final String DFA1_specialS =
        "\71\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\24\1\uffff\1\15\4\uffff\1\1\1\2\1\4\2\uffff\1\5\1\6\1\7\1"+
            "\10\1\11\2\uffff\1\12\1\uffff\1\13\1\14\13\uffff\1\16\3\uffff"+
            "\1\17\4\uffff\1\20\2\uffff\1\21\5\uffff\1\22\1\23\27\uffff\7"+
            "\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\25\1\26\44\uffff\7\27",
            "",
            "",
            "\1\30\63\uffff\1\31\21\uffff\3\31",
            "\1\32",
            "",
            "\1\34\1\uffff\1\33",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\35\1\36",
            "",
            "\1\40\23\uffff\1\41\3\uffff\1\37\16\uffff\1\42\2\uffff\1\43"+
            "\1\44\5\uffff\1\45\12\uffff\2\46\2\47",
            "",
            "",
            "",
            "",
            "\1\50\3\uffff\1\51",
            "\1\52\11\uffff\1\53",
            "",
            "",
            "",
            "",
            "\1\54",
            "",
            "",
            "\1\55",
            "",
            "",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\62\45\uffff\1\61",
            "\1\63",
            "\1\64",
            "",
            "",
            "\1\66\45\uffff\1\65",
            "\1\67\33\uffff\1\70",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "14:1: stat : ( 'verbose' v1= INT NEWLINE | 'help' NEWLINE | endWord NEWLINE | 'exec' '(' URI ')' NEWLINE | 'load' '(' URI ')' NEWLINE | 'publish' VARNAME NEWLINE | 'track' VARNAME NEWLINE | 'shutdown' NEWLINE | 'list' 'functions' NEWLINE | 'list' 'variables' NEWLINE | 'describe' VARNAME NEWLINE | 'list' vi= item 'of' VARNAME NEWLINE | 'delete' VARNAME NEWLINE | 'make' VARNAME 'persistent' NEWLINE | 'make' VARNAME 'hard' | VARNAME '=' 'read' 'ontology' '(' URI ')' NEWLINE | VARNAME '=' 'read' 'ontology' '(' URI ')' 'directly' 'in' 'db' NEWLINE | VARNAME '=' 'read' 'alignment' '(' URI ')' 'with' 'format' fileformat NEWLINE | 'export' VARNAME 'to' FILENAME 'with' 'format' fileformat NEWLINE | v1= VARNAME '=' v2= VARNAME NEWLINE | VARNAME '=' lang1= querylanguagehead 'query' query= QUERYORRULE | VARNAME '=' lang2= rulelanguagehead 'rules' rules= QUERYORRULE | 'add' 'comment' QUERYORRULE 'to' VARNAME NEWLINE | result= VARNAME '=' 'make' qt= querytype 'query' query= VARNAME 'on' target= VARNAME NEWLINE | target= VARNAME '=' 'extract' what= item 'from' source= VARNAME NEWLINE | 'search' search= QUERYORRULE 'in' target= VARNAME NEWLINE | 'make' reasoningType VARNAME NEWLINE | VARNAME '=' 'union' '(' varList ')' NEWLINE | VARNAME '=' 'intersection' '(' varList ')' NEWLINE | 'set' 'prefix' pre= uriorvar 'for' postfix= uriorvar 'in' varn= VARNAME NEWLINE | v1= VARNAME '=' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' '&' f= VARNAME '(' o1= VARNAME ',' o2= VARNAME ')' 'with' 'params' paramlist NEWLINE | 'apply ' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE | 'apply&' f= VARNAME 'to' o= VARNAME 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE | v1= VARNAME '=' '&' f= VARNAME '(' o= VARNAME ')' 'with' 'params' paramlist NEWLINE | 'add' 'rules' rules= VARNAME 'to' myVar2= VARNAME NEWLINE | NEWLINE );";
        }
    }
 

    public static final BitSet FOLLOW_stat_in_prog17 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_stat27 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INT_in_stat32 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat34 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_stat42 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat44 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_endWord_in_stat53 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_stat64 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat66 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_URI_in_stat68 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat70 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat72 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_stat79 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat81 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_URI_in_stat83 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat85 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_stat94 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat96 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat98 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_stat105 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat107 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_stat116 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_stat133 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_stat135 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_stat145 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_stat147 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_stat158 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat160 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_stat169 = new BitSet(new long[]{0xF000000000000000L,0x0000000000000007L});
    public static final BitSet FOLLOW_item_in_stat173 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_stat175 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat177 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_stat205 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat208 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_stat218 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat220 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_stat223 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_stat233 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat235 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_stat238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat245 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat246 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_stat248 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_stat250 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat252 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_URI_in_stat254 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat256 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat265 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat266 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_stat268 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_stat271 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat273 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_URI_in_stat275 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat277 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_stat279 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_stat280 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_stat282 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat291 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat292 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_stat294 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_stat296 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat298 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_URI_in_stat300 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat302 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat304 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_stat306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001F80L});
    public static final BitSet FOLLOW_fileformat_in_stat308 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_stat318 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat320 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_stat322 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_FILENAME_in_stat324 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat326 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_stat328 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001F80L});
    public static final BitSet FOLLOW_fileformat_in_stat330 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat343 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat345 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat349 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat358 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000018L});
    public static final BitSet FOLLOW_querylanguagehead_in_stat364 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_stat367 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_QUERYORRULE_in_stat371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat378 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_rulelanguagehead_in_stat384 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_stat386 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_QUERYORRULE_in_stat390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_stat397 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_stat399 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_QUERYORRULE_in_stat401 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_stat403 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat405 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat428 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat430 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_stat432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_querytype_in_stat436 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_stat438 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat442 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_stat445 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat449 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat463 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat464 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_stat466 = new BitSet(new long[]{0xF000000000000000L,0x0000000000000007L});
    public static final BitSet FOLLOW_item_in_stat470 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_stat472 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat476 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_stat486 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_QUERYORRULE_in_stat490 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_stat492 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat496 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_stat521 = new BitSet(new long[]{0x0800000000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_reasoningType_in_stat523 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat526 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat537 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat538 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_stat540 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat542 = new BitSet(new long[]{0x0000000000010080L});
    public static final BitSet FOLLOW_varList_in_stat544 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat546 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat555 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat556 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_stat558 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat560 = new BitSet(new long[]{0x0000000000010080L});
    public static final BitSet FOLLOW_varList_in_stat562 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat564 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_stat573 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_stat575 = new BitSet(new long[]{0x00000000000000D0L});
    public static final BitSet FOLLOW_uriorvar_in_stat579 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_stat581 = new BitSet(new long[]{0x00000000000000D0L});
    public static final BitSet FOLLOW_uriorvar_in_stat585 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_stat587 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat591 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat602 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat603 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat609 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat611 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat616 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_stat618 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat622 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat624 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat626 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_stat628 = new BitSet(new long[]{0x08000000000000A0L});
    public static final BitSet FOLLOW_paramlist_in_stat630 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat644 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat645 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_stat647 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat653 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat655 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat660 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_stat662 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat666 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat668 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat670 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_stat672 = new BitSet(new long[]{0x08000000000000A0L});
    public static final BitSet FOLLOW_paramlist_in_stat674 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_stat690 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat695 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_stat697 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat702 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat707 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_stat709 = new BitSet(new long[]{0x08000000000000A0L});
    public static final BitSet FOLLOW_paramlist_in_stat711 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_stat724 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat728 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_stat730 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat735 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat740 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_stat742 = new BitSet(new long[]{0x08000000000000A0L});
    public static final BitSet FOLLOW_paramlist_in_stat744 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat758 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat761 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat768 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat770 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat775 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat778 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat781 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_stat783 = new BitSet(new long[]{0x08000000000000A0L});
    public static final BitSet FOLLOW_paramlist_in_stat785 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_stat799 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_stat802 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_stat805 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat809 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_stat811 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat816 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_stat819 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_stat822 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_stat824 = new BitSet(new long[]{0x08000000000000A0L});
    public static final BitSet FOLLOW_paramlist_in_stat826 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_stat841 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_stat843 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat847 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_stat849 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VARNAME_in_stat853 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_stat857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_stat867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_paramlist893 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_paramlist895 = new BitSet(new long[]{0x00000000000000D0L});
    public static final BitSet FOLLOW_uriorvar_in_paramlist897 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_59_in_paramlist910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_varList930 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_URI_in_uriorvar956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARNAME_in_uriorvar964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_uriorvar971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_item0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_querylanguagehead0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_rulelanguagehead0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_fileformat0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_reasoningType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_querytype0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_endWord0 = new BitSet(new long[]{0x0000000000000002L});

}