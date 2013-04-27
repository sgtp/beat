package types;

public class FrameworkTypes {
	
	public static final int ABSTRACT=1;
	public static final int RDFNETWORK=2;
	public static final int OWLONTOLOGY=3;
	public static final int ALIGNMENT=4;
	public static final int QUERY=5;
	public static final int RULES=6;
	public static final int MATRIX=7;
	public static final int ARRAY=9;
	public static final int INTVALUE=10;
	public static final int TRANSLATOR = 20;
	public static final int MATCHER = 21;
	public static final int ENRICHER = 22;
	public static String translate(int type) {
		switch (type) {
		case RDFNETWORK:
			return "RDF network";
		
		case OWLONTOLOGY:
			return "OWL ontology";
			
		
		case ALIGNMENT:
			return "Alignment";
			
		case QUERY:
			return "Query";
			
		case RULES:
			return "Rules";
			
		case MATRIX:
			return "Matrix";
			
		case ARRAY:
			return "Array";
		
		case INTVALUE:
			return "Int value";
			
		case TRANSLATOR:
			return "Translator";
			
		case MATCHER:
			return "Matcher";
			
		case ENRICHER:
			return "Enricher";
			
		

		default:
			break;
		}
		return null;
	}
	
}
