package parser;
//$ANTLR 3.1.1 /home/andrea/workspace/beat/simpleOntoMatcher.g 2009-02-18 07:09:59

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class simpleOntoMatcherLexer extends Lexer {
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__29=29;
    public static final int T__64=64;
    public static final int T__28=28;
    public static final int T__65=65;
    public static final int T__27=27;
    public static final int T__62=62;
    public static final int T__26=26;
    public static final int T__63=63;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int T__61=61;
    public static final int EOF=-1;
    public static final int T__60=60;
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
    public static final int T__46=46;
    public static final int T__80=80;
    public static final int T__47=47;
    public static final int T__81=81;
    public static final int T__44=44;
    public static final int T__82=82;
    public static final int T__45=45;
    public static final int T__83=83;
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
    public static final int T__33=33;
    public static final int T__71=71;
    public static final int WS=11;
    public static final int T__34=34;
    public static final int T__72=72;
    public static final int T__35=35;
    public static final int NEWLINE=5;
    public static final int T__36=36;
    public static final int T__70=70;
    public static final int QUERYORRULE=9;
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

    // delegates
    // delegators

    public simpleOntoMatcherLexer() {;} 
    public simpleOntoMatcherLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public simpleOntoMatcherLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/andrea/workspace/beat/simpleOntoMatcher.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:3:7: ( 'verbose' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:3:9: 'verbose'
            {
            match("verbose"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:4:7: ( 'help' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:4:9: 'help'
            {
            match("help"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:5:7: ( 'exec' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:5:9: 'exec'
            {
            match("exec"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:6:7: ( '(' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:6:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:7:7: ( ')' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:7:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:8:7: ( 'load' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:8:9: 'load'
            {
            match("load"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:9:7: ( 'publish' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:9:9: 'publish'
            {
            match("publish"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:10:7: ( 'track' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:10:9: 'track'
            {
            match("track"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:11:7: ( 'shutdown' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:11:9: 'shutdown'
            {
            match("shutdown"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:12:7: ( 'list' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:12:9: 'list'
            {
            match("list"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:13:7: ( 'functions' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:13:9: 'functions'
            {
            match("functions"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:14:7: ( 'variables' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:14:9: 'variables'
            {
            match("variables"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:15:7: ( 'describe' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:15:9: 'describe'
            {
            match("describe"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:16:7: ( 'of' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:16:9: 'of'
            {
            match("of"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:17:7: ( 'delete' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:17:9: 'delete'
            {
            match("delete"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:18:7: ( 'make' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:18:9: 'make'
            {
            match("make"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:19:7: ( 'persistent' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:19:9: 'persistent'
            {
            match("persistent"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:20:7: ( 'hard' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:20:9: 'hard'
            {
            match("hard"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:21:7: ( '=' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:21:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:22:7: ( 'read' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:22:9: 'read'
            {
            match("read"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:23:7: ( 'ontology' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:23:9: 'ontology'
            {
            match("ontology"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:24:7: ( 'directly' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:24:9: 'directly'
            {
            match("directly"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:25:7: ( 'in' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:25:9: 'in'
            {
            match("in"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:26:7: ( 'db' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:26:9: 'db'
            {
            match("db"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:27:7: ( 'alignment' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:27:9: 'alignment'
            {
            match("alignment"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:28:7: ( 'with' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:28:9: 'with'
            {
            match("with"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:29:7: ( 'format' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:29:9: 'format'
            {
            match("format"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:30:7: ( 'export' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:30:9: 'export'
            {
            match("export"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:31:7: ( 'to' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:31:9: 'to'
            {
            match("to"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:32:7: ( 'query' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:32:9: 'query'
            {
            match("query"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:33:7: ( 'rules' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:33:9: 'rules'
            {
            match("rules"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:34:7: ( 'add' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:34:9: 'add'
            {
            match("add"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:35:7: ( 'comment' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:35:9: 'comment'
            {
            match("comment"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:36:7: ( 'on' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:36:9: 'on'
            {
            match("on"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:37:7: ( 'extract' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:37:9: 'extract'
            {
            match("extract"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:38:7: ( 'from' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:38:9: 'from'
            {
            match("from"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:39:7: ( 'search' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:39:9: 'search'
            {
            match("search"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:40:7: ( 'union' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:40:9: 'union'
            {
            match("union"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:41:7: ( 'intersection' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:41:9: 'intersection'
            {
            match("intersection"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:42:7: ( 'set' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:42:9: 'set'
            {
            match("set"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:43:7: ( 'prefix' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:43:9: 'prefix'
            {
            match("prefix"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:44:7: ( 'for' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:44:9: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:45:7: ( ',' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:45:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:46:7: ( 'params' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:46:9: 'params'
            {
            match("params"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:47:7: ( '&' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:47:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:48:7: ( 'apply ' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:48:9: 'apply '
            {
            match("apply "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:49:7: ( 'apply&' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:49:9: 'apply&'
            {
            match("apply&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:50:7: ( 'none' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:50:9: 'none'
            {
            match("none"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:51:7: ( 'classes' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:51:9: 'classes'
            {
            match("classes"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:52:7: ( 'individuals' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:52:9: 'individuals'
            {
            match("individuals"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:53:7: ( 'properties' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:53:9: 'properties'
            {
            match("properties"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:54:7: ( 'datatypeproperties' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:54:9: 'datatypeproperties'
            {
            match("datatypeproperties"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:55:7: ( 'objectproperties' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:55:9: 'objectproperties'
            {
            match("objectproperties"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:56:7: ( 'annotationproperties' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:56:9: 'annotationproperties'
            {
            match("annotationproperties"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:57:7: ( 'all' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:57:9: 'all'
            {
            match("all"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:58:7: ( 'rdql' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:58:9: 'rdql'
            {
            match("rdql"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:59:7: ( 'sparql' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:59:9: 'sparql'
            {
            match("sparql"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:60:7: ( 'jena' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:60:9: 'jena'
            {
            match("jena"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:61:7: ( 'swrl' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:61:9: 'swrl'
            {
            match("swrl"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:62:7: ( '.txt' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:62:9: '.txt'
            {
            match(".txt"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:63:7: ( '.cell' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:63:9: '.cell'
            {
            match(".cell"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:64:7: ( '.n3' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:64:9: '.n3'
            {
            match(".n3"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:65:7: ( '.rdf-xml' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:65:9: '.rdf-xml'
            {
            match(".rdf-xml"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:66:7: ( '.rdf-xml-abbv' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:66:9: '.rdf-xml-abbv'
            {
            match(".rdf-xml-abbv"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:67:7: ( '.cowl' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:67:9: '.cowl'
            {
            match(".cowl"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:68:7: ( 'RDFS' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:68:9: 'RDFS'
            {
            match("RDFS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:69:7: ( 'OWL' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:69:9: 'OWL'
            {
            match("OWL"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:70:7: ( 'trans' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:70:9: 'trans'
            {
            match("trans"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:71:7: ( 'select' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:71:9: 'select'
            {
            match("select"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:72:7: ( 'construct' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:72:9: 'construct'
            {
            match("construct"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:73:7: ( 'end' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:73:9: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:74:7: ( 'exit' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:74:9: 'exit'
            {
            match("exit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:75:7: ( 'quit' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:75:9: 'quit'
            {
            match("quit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:76:7: ( 'bye' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:76:9: 'bye'
            {
            match("bye"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:77:7: ( 'ciao' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:77:9: 'ciao'
            {
            match("ciao"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:78:7: ( 'sayonara' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:78:9: 'sayonara'
            {
            match("sayonara"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:79:7: ( 'kenavo' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:79:9: 'kenavo'
            {
            match("kenavo"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "VARNAME"
    public final void mVARNAME() throws RecognitionException {
        try {
            int _type = VARNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:252:11: ( ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' )+ ( '0' .. '9' )* )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:252:16: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' )+ ( '0' .. '9' )*
            {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:252:16: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='-'||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // /home/andrea/workspace/beat/simpleOntoMatcher.g:252:44: ( '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:252:45: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARNAME"

    // $ANTLR start "FILENAME"
    public final void mFILENAME() throws RecognitionException {
        try {
            int _type = FILENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:253:10: ( ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '/' | '\\\\' )+ '.' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '0' .. '9' )+ )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:253:13: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '/' | '\\\\' )+ '.' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '0' .. '9' )+
            {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:253:13: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '/' | '\\\\' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='-'||(LA3_0>='/' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='\\'||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='/' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='\\'||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match('.'); 
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:253:62: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='-'||(LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FILENAME"

    // $ANTLR start "QUERYORRULE"
    public final void mQUERYORRULE() throws RecognitionException {
        try {
            int _type = QUERYORRULE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:254:13: ( '\"\"' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | ' ' | '0' .. '9' | ':' | '%' | '/' | '<' | '>' | '{' | '}' | '?' | '(' | ')' | '#' | '.' | '\\n' | '\\r' | '\\\\' | '~' | '*' )+ '\"\"' )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:254:15: '\"\"' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | ' ' | '0' .. '9' | ':' | '%' | '/' | '<' | '>' | '{' | '}' | '?' | '(' | ')' | '#' | '.' | '\\n' | '\\r' | '\\\\' | '~' | '*' )+ '\"\"'
            {
            match("\"\""); 

            // /home/andrea/workspace/beat/simpleOntoMatcher.g:254:19: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | ' ' | '0' .. '9' | ':' | '%' | '/' | '<' | '>' | '{' | '}' | '?' | '(' | ')' | '#' | '.' | '\\n' | '\\r' | '\\\\' | '~' | '*' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\n'||LA5_0=='\r'||LA5_0==' '||LA5_0=='#'||LA5_0=='%'||(LA5_0>='(' && LA5_0<='*')||(LA5_0>='-' && LA5_0<=':')||LA5_0=='<'||(LA5_0>='>' && LA5_0<='?')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='\\'||LA5_0=='_'||(LA5_0>='a' && LA5_0<='{')||(LA5_0>='}' && LA5_0<='~')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            	    {
            	    if ( input.LA(1)=='\n'||input.LA(1)=='\r'||input.LA(1)==' '||input.LA(1)=='#'||input.LA(1)=='%'||(input.LA(1)>='(' && input.LA(1)<='*')||(input.LA(1)>='-' && input.LA(1)<=':')||input.LA(1)=='<'||(input.LA(1)>='>' && input.LA(1)<='?')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='\\'||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='{')||(input.LA(1)>='}' && input.LA(1)<='~') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            match("\"\""); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUERYORRULE"

    // $ANTLR start "PATTERNVAR"
    public final void mPATTERNVAR() throws RecognitionException {
        try {
            int _type = PATTERNVAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:255:12: ( '?' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' )+ )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:255:14: '?' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' )+
            {
            match('?'); 
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:255:17: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='-'||(LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PATTERNVAR"

    // $ANTLR start "URI"
    public final void mURI() throws RecognitionException {
        try {
            int _type = URI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:256:5: ( ( 'a' .. 'z' )+ ':' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | ':' | '%' | '/' | '#' | '.' | '?' )+ )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:256:8: ( 'a' .. 'z' )+ ':' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | ':' | '%' | '/' | '#' | '.' | '?' )+
            {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:256:8: ( 'a' .. 'z' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:256:9: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            match(':'); 
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:256:22: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | ':' | '%' | '/' | '#' | '.' | '?' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='#'||LA8_0=='%'||(LA8_0>='-' && LA8_0<=':')||LA8_0=='?'||(LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            	    {
            	    if ( input.LA(1)=='#'||input.LA(1)=='%'||(input.LA(1)>='-' && input.LA(1)<=':')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "URI"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:257:6: ( ( '0' .. '9' )+ )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:257:11: ( '0' .. '9' )+
            {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:257:11: ( '0' .. '9' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:257:11: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:258:9: ( ( ( '\\r' )? '\\n' | ';' ) )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:258:11: ( ( '\\r' )? '\\n' | ';' )
            {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:258:11: ( ( '\\r' )? '\\n' | ';' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\n'||LA11_0=='\r') ) {
                alt11=1;
            }
            else if ( (LA11_0==';') ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:258:12: ( '\\r' )? '\\n'
                    {
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:258:12: ( '\\r' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\r') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // /home/andrea/workspace/beat/simpleOntoMatcher.g:258:12: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;
                case 2 :
                    // /home/andrea/workspace/beat/simpleOntoMatcher.g:258:23: ';'
                    {
                    match(';'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:259:6: ( ( ' ' | '\\t' )+ )
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:259:11: ( ' ' | '\\t' )+
            {
            // /home/andrea/workspace/beat/simpleOntoMatcher.g:259:11: ( ' ' | '\\t' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='\t'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/andrea/workspace/beat/simpleOntoMatcher.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | VARNAME | FILENAME | QUERYORRULE | PATTERNVAR | URI | INT | NEWLINE | WS )
        int alt13=85;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:112: T__29
                {
                mT__29(); 

                }
                break;
            case 19 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:118: T__30
                {
                mT__30(); 

                }
                break;
            case 20 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:124: T__31
                {
                mT__31(); 

                }
                break;
            case 21 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:130: T__32
                {
                mT__32(); 

                }
                break;
            case 22 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:136: T__33
                {
                mT__33(); 

                }
                break;
            case 23 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:142: T__34
                {
                mT__34(); 

                }
                break;
            case 24 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:148: T__35
                {
                mT__35(); 

                }
                break;
            case 25 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:154: T__36
                {
                mT__36(); 

                }
                break;
            case 26 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:160: T__37
                {
                mT__37(); 

                }
                break;
            case 27 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:166: T__38
                {
                mT__38(); 

                }
                break;
            case 28 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:172: T__39
                {
                mT__39(); 

                }
                break;
            case 29 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:178: T__40
                {
                mT__40(); 

                }
                break;
            case 30 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:184: T__41
                {
                mT__41(); 

                }
                break;
            case 31 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:190: T__42
                {
                mT__42(); 

                }
                break;
            case 32 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:196: T__43
                {
                mT__43(); 

                }
                break;
            case 33 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:202: T__44
                {
                mT__44(); 

                }
                break;
            case 34 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:208: T__45
                {
                mT__45(); 

                }
                break;
            case 35 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:214: T__46
                {
                mT__46(); 

                }
                break;
            case 36 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:220: T__47
                {
                mT__47(); 

                }
                break;
            case 37 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:226: T__48
                {
                mT__48(); 

                }
                break;
            case 38 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:232: T__49
                {
                mT__49(); 

                }
                break;
            case 39 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:238: T__50
                {
                mT__50(); 

                }
                break;
            case 40 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:244: T__51
                {
                mT__51(); 

                }
                break;
            case 41 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:250: T__52
                {
                mT__52(); 

                }
                break;
            case 42 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:256: T__53
                {
                mT__53(); 

                }
                break;
            case 43 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:262: T__54
                {
                mT__54(); 

                }
                break;
            case 44 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:268: T__55
                {
                mT__55(); 

                }
                break;
            case 45 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:274: T__56
                {
                mT__56(); 

                }
                break;
            case 46 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:280: T__57
                {
                mT__57(); 

                }
                break;
            case 47 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:286: T__58
                {
                mT__58(); 

                }
                break;
            case 48 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:292: T__59
                {
                mT__59(); 

                }
                break;
            case 49 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:298: T__60
                {
                mT__60(); 

                }
                break;
            case 50 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:304: T__61
                {
                mT__61(); 

                }
                break;
            case 51 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:310: T__62
                {
                mT__62(); 

                }
                break;
            case 52 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:316: T__63
                {
                mT__63(); 

                }
                break;
            case 53 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:322: T__64
                {
                mT__64(); 

                }
                break;
            case 54 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:328: T__65
                {
                mT__65(); 

                }
                break;
            case 55 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:334: T__66
                {
                mT__66(); 

                }
                break;
            case 56 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:340: T__67
                {
                mT__67(); 

                }
                break;
            case 57 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:346: T__68
                {
                mT__68(); 

                }
                break;
            case 58 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:352: T__69
                {
                mT__69(); 

                }
                break;
            case 59 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:358: T__70
                {
                mT__70(); 

                }
                break;
            case 60 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:364: T__71
                {
                mT__71(); 

                }
                break;
            case 61 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:370: T__72
                {
                mT__72(); 

                }
                break;
            case 62 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:376: T__73
                {
                mT__73(); 

                }
                break;
            case 63 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:382: T__74
                {
                mT__74(); 

                }
                break;
            case 64 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:388: T__75
                {
                mT__75(); 

                }
                break;
            case 65 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:394: T__76
                {
                mT__76(); 

                }
                break;
            case 66 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:400: T__77
                {
                mT__77(); 

                }
                break;
            case 67 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:406: T__78
                {
                mT__78(); 

                }
                break;
            case 68 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:412: T__79
                {
                mT__79(); 

                }
                break;
            case 69 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:418: T__80
                {
                mT__80(); 

                }
                break;
            case 70 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:424: T__81
                {
                mT__81(); 

                }
                break;
            case 71 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:430: T__82
                {
                mT__82(); 

                }
                break;
            case 72 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:436: T__83
                {
                mT__83(); 

                }
                break;
            case 73 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:442: T__84
                {
                mT__84(); 

                }
                break;
            case 74 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:448: T__85
                {
                mT__85(); 

                }
                break;
            case 75 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:454: T__86
                {
                mT__86(); 

                }
                break;
            case 76 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:460: T__87
                {
                mT__87(); 

                }
                break;
            case 77 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:466: T__88
                {
                mT__88(); 

                }
                break;
            case 78 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:472: VARNAME
                {
                mVARNAME(); 

                }
                break;
            case 79 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:480: FILENAME
                {
                mFILENAME(); 

                }
                break;
            case 80 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:489: QUERYORRULE
                {
                mQUERYORRULE(); 

                }
                break;
            case 81 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:501: PATTERNVAR
                {
                mPATTERNVAR(); 

                }
                break;
            case 82 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:512: URI
                {
                mURI(); 

                }
                break;
            case 83 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:516: INT
                {
                mINT(); 

                }
                break;
            case 84 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:520: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 85 :
                // /home/andrea/workspace/beat/simpleOntoMatcher.g:1:528: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\3\52\2\uffff\10\52\1\uffff\7\52\2\uffff\2\52\1\uffff\5"+
        "\52\1\140\2\uffff\1\52\3\uffff\3\52\2\uffff\13\52\1\162\12\52\1"+
        "\u0080\1\52\1\u0082\1\u0084\5\52\1\u008c\14\52\4\uffff\4\52\1\uffff"+
        "\10\52\1\u00ab\10\52\1\uffff\2\52\1\u00b7\5\52\1\u00be\4\52\1\uffff"+
        "\1\52\1\uffff\1\52\1\uffff\7\52\1\uffff\1\52\1\u00cd\1\u00ce\14"+
        "\52\3\uffff\1\52\1\u00dd\1\u00de\3\52\1\u00e2\1\u00e3\1\u00e4\2"+
        "\52\1\u00e7\1\uffff\1\u00e8\1\u00e9\11\52\1\uffff\2\52\1\u00f5\3"+
        "\52\1\uffff\1\u00f9\6\52\1\u0100\1\u0101\1\52\1\u0103\3\52\2\uffff"+
        "\2\52\1\u0109\1\52\1\u010b\3\52\1\u010f\1\52\1\u0111\1\u0112\1\uffff"+
        "\1\u0114\2\uffff\3\52\3\uffff\2\52\3\uffff\5\52\1\u011f\1\u0120"+
        "\4\52\1\uffff\3\52\1\uffff\6\52\2\uffff\1\u012e\1\uffff\5\52\1\uffff"+
        "\1\u0135\1\uffff\3\52\1\uffff\1\u0139\4\uffff\3\52\1\u013e\3\52"+
        "\1\u0142\1\52\1\u0144\2\uffff\1\52\1\u0146\1\u0147\1\u0148\2\52"+
        "\1\u014b\1\52\1\u014d\4\52\1\uffff\3\52\2\uffff\1\52\1\uffff\3\52"+
        "\2\uffff\1\u015a\1\u015b\1\52\1\uffff\1\u015d\1\u015e\1\52\1\uffff"+
        "\1\52\1\uffff\1\52\3\uffff\2\52\1\uffff\1\52\1\uffff\10\52\1\u016d"+
        "\1\52\1\u016f\3\uffff\1\52\2\uffff\2\52\1\u0174\1\u0175\1\52\1\u0177"+
        "\1\u0178\1\52\1\u017a\5\52\1\uffff\1\52\1\uffff\1\u0182\1\u0183"+
        "\2\52\2\uffff\1\u0186\2\uffff\1\52\1\uffff\3\52\1\u018b\1\52\1\u018d"+
        "\3\uffff\1\u018e\1\u018f\1\uffff\4\52\1\uffff\1\52\3\uffff\3\52"+
        "\1\u0198\3\52\1\u019c\1\uffff\3\52\1\uffff\10\52\1\u01a8\2\52\1"+
        "\uffff\1\52\1\u01ac\1\52\1\uffff\1\52\1\u01af\1\uffff";
    static final String DFA13_eofS =
        "\u01b0\uffff";
    static final String DFA13_minS =
        "\1\11\3\55\2\uffff\10\55\1\uffff\7\55\2\uffff\2\55\1\143\6\55\2"+
        "\uffff\1\55\3\uffff\3\55\2\uffff\54\55\1\uffff\1\145\1\uffff\1\144"+
        "\4\55\1\uffff\21\55\1\uffff\15\55\1\uffff\1\55\1\uffff\1\55\1\uffff"+
        "\7\55\1\uffff\17\55\2\uffff\1\146\14\55\1\uffff\13\55\1\uffff\6"+
        "\55\1\uffff\16\55\2\uffff\16\55\2\uffff\3\55\3\uffff\2\55\3\uffff"+
        "\13\55\1\uffff\3\55\1\uffff\6\55\2\uffff\1\55\1\uffff\3\55\1\40"+
        "\1\55\1\uffff\1\55\1\uffff\3\55\1\uffff\1\55\2\uffff\1\170\1\uffff"+
        "\12\55\2\uffff\15\55\1\uffff\3\55\2\uffff\1\55\1\uffff\3\55\1\uffff"+
        "\1\155\3\55\1\uffff\3\55\1\uffff\1\55\1\uffff\1\55\3\uffff\2\55"+
        "\1\uffff\1\55\1\uffff\13\55\1\154\2\uffff\1\55\2\uffff\16\55\1\uffff"+
        "\1\55\1\uffff\4\55\2\uffff\1\55\2\uffff\1\55\1\uffff\6\55\3\uffff"+
        "\2\55\1\uffff\4\55\1\uffff\1\55\3\uffff\10\55\1\uffff\3\55\1\uffff"+
        "\13\55\1\uffff\3\55\1\uffff\2\55\1\uffff";
    static final String DFA13_maxS =
        "\4\172\2\uffff\10\172\1\uffff\7\172\2\uffff\2\172\1\164\6\172\2"+
        "\uffff\1\172\3\uffff\3\172\2\uffff\54\172\1\uffff\1\157\1\uffff"+
        "\1\144\4\172\1\uffff\21\172\1\uffff\15\172\1\uffff\1\172\1\uffff"+
        "\1\172\1\uffff\7\172\1\uffff\17\172\2\uffff\1\146\14\172\1\uffff"+
        "\13\172\1\uffff\6\172\1\uffff\16\172\2\uffff\14\172\1\55\1\172\2"+
        "\uffff\3\172\3\uffff\2\172\3\uffff\13\172\1\uffff\3\172\1\uffff"+
        "\6\172\2\uffff\1\172\1\uffff\5\172\1\uffff\1\172\1\uffff\3\172\1"+
        "\uffff\1\172\2\uffff\1\170\1\uffff\12\172\2\uffff\15\172\1\uffff"+
        "\3\172\2\uffff\1\172\1\uffff\3\172\1\uffff\1\155\3\172\1\uffff\3"+
        "\172\1\uffff\1\172\1\uffff\1\172\3\uffff\2\172\1\uffff\1\172\1\uffff"+
        "\13\172\1\154\2\uffff\1\172\2\uffff\16\172\1\uffff\1\172\1\uffff"+
        "\1\55\3\172\2\uffff\1\172\2\uffff\1\172\1\uffff\6\172\3\uffff\2"+
        "\172\1\uffff\4\172\1\uffff\1\172\3\uffff\10\172\1\uffff\3\172\1"+
        "\uffff\13\172\1\uffff\3\172\1\uffff\2\172\1\uffff";
    static final String DFA13_acceptS =
        "\4\uffff\1\4\1\5\10\uffff\1\23\7\uffff\1\53\1\55\11\uffff\1\120"+
        "\1\121\1\uffff\1\117\1\124\1\125\3\uffff\1\116\1\122\54\uffff\1"+
        "\74\1\uffff\1\76\5\uffff\1\123\21\uffff\1\35\15\uffff\1\30\1\uffff"+
        "\1\16\1\uffff\1\42\7\uffff\1\27\17\uffff\1\75\1\101\15\uffff\1\107"+
        "\13\uffff\1\50\6\uffff\1\52\16\uffff\1\67\1\40\16\uffff\1\103\1"+
        "\112\3\uffff\1\2\1\22\1\3\2\uffff\1\110\1\6\1\12\13\uffff\1\73\3"+
        "\uffff\1\44\6\uffff\1\20\1\24\1\uffff\1\70\5\uffff\1\32\1\uffff"+
        "\1\111\3\uffff\1\113\1\uffff\1\60\1\72\1\uffff\1\102\12\uffff\1"+
        "\10\1\104\15\uffff\1\37\3\uffff\1\56\1\57\1\uffff\1\36\3\uffff\1"+
        "\46\4\uffff\1\34\3\uffff\1\51\1\uffff\1\54\1\uffff\1\45\1\105\1"+
        "\71\2\uffff\1\33\1\uffff\1\17\14\uffff\1\115\1\1\1\uffff\1\43\1"+
        "\7\16\uffff\1\41\1\uffff\1\61\4\uffff\1\11\1\114\1\uffff\1\15\1"+
        "\26\1\uffff\1\25\6\uffff\1\100\1\77\1\14\2\uffff\1\13\4\uffff\1"+
        "\31\1\uffff\1\106\1\21\1\63\10\uffff\1\62\3\uffff\1\47\13\uffff"+
        "\1\65\3\uffff\1\64\2\uffff\1\66";
    static final String DFA13_specialS =
        "\u01b0\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\46\1\45\2\uffff\1\45\22\uffff\1\46\1\uffff\1\41\3\uffff\1"+
            "\27\1\uffff\1\4\1\5\2\uffff\1\26\1\43\1\32\1\44\12\40\1\uffff"+
            "\1\45\1\uffff\1\16\1\uffff\1\42\1\uffff\16\43\1\34\2\43\1\33"+
            "\10\43\1\uffff\1\44\2\uffff\1\43\1\uffff\1\21\1\35\1\24\1\13"+
            "\1\3\1\12\1\37\1\2\1\20\1\31\1\36\1\6\1\15\1\30\1\14\1\7\1\23"+
            "\1\17\1\11\1\10\1\25\1\1\1\22\3\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\50\3\37\1\47\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\55\3\37\1\54\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\57\11\37\1\56\2\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\61\5\37\1\60\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\65\3\37\1\63\14\37\1\64\2\37\1\62\5\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\67\2\37\1\66\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\74\3\37\1\71\2\37\1\70\7\37\1\72\6\37\1\73\3\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\76\2\37\1\77\2\37\1\75\5\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\103\1\102\2\37\1\100\3\37\1\101\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\37\1\106\3\37\1\104\7\37\1\105\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\107\31\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\112\1\110\17\37\1\111\5\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\113\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\115\7\37\1\114\1\37\1\117\1\37\1\116\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\120\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\24\37\1\121\5\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\124\2\37\1\123\2\37\1\122\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\125\14\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\126\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\127\25\37",
            "\1\131\12\uffff\1\132\3\uffff\1\133\1\uffff\1\130",
            "\1\43\2\44\12\51\7\uffff\3\43\1\134\26\43\1\uffff\1\44\2\uffff"+
            "\1\43\1\uffff\32\43",
            "\1\43\2\44\12\51\7\uffff\26\43\1\135\3\43\1\uffff\1\44\2\uffff"+
            "\1\43\1\uffff\32\43",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\30\37\1\136\1\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\137\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\3\44\12\40\7\uffff\32\44\1\uffff\1\44\2\uffff\1\44\1\uffff"+
            "\32\44",
            "",
            "",
            "\1\43\2\44\12\51\7\uffff\32\43\1\uffff\1\44\2\uffff\1\43\1"+
            "\uffff\32\43",
            "",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\141\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\142\10\37",
            "\3\44\12\51\7\uffff\32\44\1\uffff\1\44\2\uffff\1\44\1\uffff"+
            "\32\44",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\143\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\144\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\145\3\37\1\150\6\37\1\146\3\37\1\147\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\151\26\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\152\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\153\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\37\1\154\30\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\155\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\156\11\37\1\157\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\160\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\161\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\24\37\1\163\5\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\164\12\37\1\166\7\37\1\165\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\167\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\170\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\30\37\1\171\1\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\172\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\173\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\174\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\176\6\37\1\175\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\177\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0081\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0083\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\11\37\1\u0085\20\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\12\37\1\u0086\17\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u0087\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u0088\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\20\37\1\u0089\11\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\u008b\17\37\1\u008a\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u008d\2\37\1\u008e\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\u008f\26\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u0090\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0091\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0092\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0093\3\37\1\u0094\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\14\37\1\u0095\1\u0096\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u0097\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u0098\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u0099\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u009a\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u009b\14\37",
            "",
            "\1\u009c\11\uffff\1\u009d",
            "",
            "\1\u009e",
            "\1\43\2\44\12\51\7\uffff\5\43\1\u009f\24\43\1\uffff\1\44\2"+
            "\uffff\1\43\1\uffff\32\43",
            "\1\43\2\44\12\51\7\uffff\13\43\1\u00a0\16\43\1\uffff\1\44\2"+
            "\uffff\1\43\1\uffff\32\43",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00a1\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u00a2\14\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\37\1\u00a3\30\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u00a4\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u00a5\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\u00a6\26\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00a7\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u00a8\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u00a9\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u00aa\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\u00ac\26\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u00ad\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u00ae\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u00af\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\5\37\1\u00b0\24\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u00b1\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u00b2\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00b3\12\37\1\u00b4\14\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u00b5\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u00b6\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00b8\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u00b9\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u00ba\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u00bb\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00bc\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\14\37\1\u00bd\15\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\14\37\1\u00bf\15\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00c0\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00c1\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00c2\25\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u00c3\31\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u00c4\13\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00c5\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00c6\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\u00c7\26\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00c8\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u00c9\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00ca\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u00cb\21\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\6\37\1\u00cc\23\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u00cf\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u00d0\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\7\37\1\u00d1\22\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u00d2\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u00d3\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\14\37\1\u00d4\15\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u00d5\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u00d6\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u00d7\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u00d8\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00d9\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u00da\31\37",
            "",
            "",
            "\1\u00db",
            "\1\43\2\44\12\51\7\uffff\22\43\1\u00dc\7\43\1\uffff\1\44\2"+
            "\uffff\1\43\1\uffff\32\43",
            "\1\43\2\44\12\51\7\uffff\32\43\1\uffff\1\44\2\uffff\1\43\1"+
            "\uffff\32\43",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u00df\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u00e0\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u00e1\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u00e5\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u00e6\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u00ea\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u00eb\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u00ec\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u00ed\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\14\37\1\u00ee\15\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\12\37\1\u00ef\17\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u00f0\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\u00f1\26\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00f2\27\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00f3\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\20\37\1\u00f4\11\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u00f6\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u00f7\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u00f8\31\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u00fa\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u00fb\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00fc\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u00fd\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u00fe\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u00ff\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u0102\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u0104\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\25\37\1\u0105\4\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0106\14\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\30\37\1\u0107\1\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0108\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\30\37\1\u010a\1\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u010c\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u010d\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u010e\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0110\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\u0113",
            "\1\43\2\44\12\51\7\uffff\32\43\1\uffff\1\44\2\uffff\1\43\1"+
            "\uffff\32\43",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\25\37\1\u0115\4\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u0116\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\37\1\u0117\30\37",
            "",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0118\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u0119\27\37",
            "",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u011a\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u011b\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\27\37\1\u011c\2\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u011d\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u011e\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u0121\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\7\37\1\u0122\22\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0123\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u0124\16\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u0125\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u0126\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0127\6\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u0128\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0129\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u012a\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\30\37\1\u012b\1\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u012c\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u012d\6\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u012f\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u0130\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\14\37\1\u0131\15\37",
            "\1\u0132\5\uffff\1\u0133\6\uffff\1\43\2\44\12\51\1\53\6\uffff"+
            "\32\43\1\uffff\1\44\2\uffff\1\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u0134\31\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0136\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u0137\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0138\25\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "",
            "\1\u013a",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u013b\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u013c\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u013d\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u013f\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\7\37\1\u0140\22\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0141\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0143\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\26\37\1\u0145\3\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u0149\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u014a\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\37\1\u014c\30\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u014e\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u014f\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\6\37\1\u0150\23\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u0151\12\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0152\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\3\37\1\u0153\26\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0154\25\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0155\6\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0156\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\24\37\1\u0157\5\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u0158\7\37",
            "",
            "\1\u0159",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u015c\25\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u015f\25\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u0160\21\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0161\14\37",
            "",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u0162\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0163\14\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0164\25\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\30\37\1\u0165\1\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0166\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\30\37\1\u0167\1\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u0168\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u0169\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\24\37\1\u016a\5\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u016b\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u016c\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\2\37\1\u016e\27\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\u0170",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u0171\7\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0172\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0173\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u0176\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u0179\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u017b\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u017c\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\1\u017d\31\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u017e\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u017f\13\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0180\6\37",
            "",
            "\1\u0181",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u0184\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u0185\7\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u0187\10\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u0188\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u0189\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\13\37\1\u018a\16\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u018c\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u0190\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u0191\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u0192\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u0193\7\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u0194\12\37",
            "",
            "",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u0195\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u0196\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\15\37\1\u0197\14\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u0199\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u019a\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u019b\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\16\37\1\u019d\13\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u019e\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u019f\21\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\17\37\1\u01a0\12\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u01a1\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u01a2\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u01a3\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u01a4\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u01a5\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\21\37\1\u01a6\10\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u01a7\25\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\23\37\1\u01a9\6\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u01aa\7\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\10\37\1\u01ab\21\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\4\37\1\u01ad\25\37",
            "",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\22\37\1\u01ae\7\37",
            "\1\43\2\44\12\51\1\53\6\uffff\32\43\1\uffff\1\44\2\uffff\1"+
            "\43\1\uffff\32\37",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | VARNAME | FILENAME | QUERYORRULE | PATTERNVAR | URI | INT | NEWLINE | WS );";
        }
    }
 

}