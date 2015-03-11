import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * The class defines interpretation rules for the text.
 */

public class Parser {
	private Reader reader;
	private String newText;
	private String noun;
	
	
	private static String[] precedingArr = {"in front of" , "before" , "prior to", "preceded", "earlier", "preceds", "precede", "faster", "better"};
	
	private static String [] followingArr ={"behind", "after", "later" };
	
	private static String [] relational ={"than"};
	
	private static String [] neg = {"no" , "not"};
	
	private static String [] conj ={"and" , "along with"};
	
	private static String [] conjInFront = {"both"};
	
	private static String [] actionVerb = {"is", "are"};
	
	private static String[] quantifiers = {"each", "all", "either", "neither", "some", "most", "none", "no", "at least","at most"};
	
	private static String ifOnlyIfRegex = "((if)+\\s+(but|or|and)+\\s+only if)";
	
	private static String orderingGameRegex = "((\\beach\\b|\\bEach\\b)+.*?\\bstudent\\b.*?\\bexactly\\b.?\\b1\\b)";
		
	
	private static String [] boundary = {"through" , "to"};
	
	private static String [] gameClassificationKeywords = {"group" , "grouped" , "order" , "ordered"};
	
	public Parser(Reader reader){
		this.reader = reader;
	}
	
	private boolean isSimpleOrderingGame(){
		noun = reader.getNoun();
		newText = reader.getNewText();
		
		Pattern pattern= Pattern.compile(orderingGameRegex);
		Matcher m = pattern.matcher(reader.getNewText());
		return (m.find());
	}
}
