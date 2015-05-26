import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuestionParser3{
	private HashMap<String, String[]> questionAndAnswChoicesMap;
	
	public QuestionParser3(){
	}
	
	public String doVisibleActions(String text){
		initializemParselets();
		
		String result = segment(createTokens(text));
			
		
			
			//finalMap.put(newKey, value);
//		}
//		System.out.println("here is the final map: " + finalMap);
//		
//		
//		for (String[] s: finalMap.values()){
//			for (String ss: s){
//				System.out.println(Arrays.toString(s));
//			}
//		}
//		return finalMap;
		return result;
	}
	
//		
//		while(!questionAndAnswMap.isEmpty()){
//			parseInAccordanceWithParselet(createTokens(questionAndAnswMap));
//		}
//		
//		for(Map.Entry<String, String[]> entry: finalMap.entrySet()){	
//			String text=entry.getValue();
//			String[] value=entry.getKey();
//			questionAndAnswMap.put(text, value);
//		}
//		
//		finalMap.clear();
//		
//		while(!questionAndAnswMap.isEmpty()){				
//				parseInAccordanceWithParselet(createTokens(questionAndAnswMap));	
//		}
//		
//		System.out.println("Resulting interpretation of the questions: " + finalMap);
//		return finalMap;	
//	}
//	
	public QuestionToken createTokens(String text){
	
//		String[] secondPart;
//		String text = "";
//		
//		for(Entry<String, String[]> entry: map.entrySet()){	
//				text=entry.getKey();
//				secondPart=entry.getValue();
		
			System.out.println("text to examine: "  + text);
			
			Parselet parselet = null;

			Pattern pattern0 = Pattern.compile("\\b[A-Z]\\b[^==+-]+?\\d");
			Matcher matcher0 = pattern0.matcher(text);
			
			if(text.matches("([a-zA-Z\\s]*).*(which 1|who|which|what)(.*)") && !text.contains("MBT") &&
					!text.contains("MBF") && !text.contains("MayBT") && !text.contains("MayBF")){
				parselet =  mParselets.get("questCond");
				System.out.println("Getting the value associated with the questCond key");		
			} else if (text.matches(".*(if|If)\\s*[^(){}]+")) {
				parselet = mParselets.get("questIf");
				System.out.println("Getting the value associated with the questIf key");
			} else if (text.matches(".*\\b(Either|either)\\b.*(or).*")) {
				parselet = mParselets.get("either");
				System.out
						.println("Getting the value associated with the either key");
	
			} else if (text.matches(".*\\b(Neither|neither)\\b.*(nor).*")) {
				parselet = mParselets.get("neither");
				System.out
						.println("Getting the value associated with the neither key"); // !
			} else if (text.contains("and")) {
				parselet = mParselets.get("conjunction");
				System.out
						.println("Getting the value associated with the conjunction key");
			} else if (text.matches(".*\\s*immediately\\s*before\\s*.*") || text.matches(".*\\s*immediately\\s*after\\s*.*")){
				parselet = mParselets.get("relationship");
				System.out.println("Getting the value associated with the relationship key");
				
			} else if (matcher0.find()) {
				parselet = mParselets.get("name");
				System.out
						.println("Getting the value associated with the name key");
			} else {
				parselet = mParselets.get("cleanup");
				System.out
						.println("Getting the value associated with the cleanup key");
			}
			QuestionToken t = new QuestionToken(text, parselet);
			//allTokens.add(t);
		
		//map.clear();
		//return allTokens;	
			return t;
	}
	//}
			
//	public void parseInAccordanceWithParselet(ArrayList<QuestionToken> allTokens) {
//		for (QuestionToken t : allTokens) {
//			if(t.getParselet()!=null) segment(t);
//		}
//		allTokens.removeAll(allTokens);
//	}
	

	public String segment(QuestionToken token) {

		String text = token.getText();
		Parselet parselet = token.getParselet();

		Parselet a = parselet;
		String newString = a.parse(text);

//		if (a.getClass() == CodeCleanUpParselet.class) {
//			finalMap.put(newString, token.getValue());
//		} else {
//			questionAndAnswChoicesMap.put(newString, token.getValue());
//		}
		
		if (a.getClass() == CodeCleanUpParselet.class) {
			return newString;
		} else {
			return segment(createTokens(newString));
		}
	}
	
	public void initializemParselets() {
		mParselets.put("questCond", new QuestionConditionParselet());
		mParselets.put("questIf", new QuestionIfParselet());
		mParselets.put("name", new NameParselet());
		mParselets.put("either", new EitherOrParselet());
		mParselets.put("conjunction", new ConjunctionParselet());
		mParselets.put("relationship", new RelationshipParselet());
		mParselets.put("cleanup", new CodeCleanUpParselet());
	}
	private HashMap<String, Parselet> mParselets = new HashMap<String, Parselet>();
	private ArrayList<QuestionToken> allTokens = new ArrayList<QuestionToken>();
	private HashMap<String, String[]>finalMap = new HashMap<String, String[]>();
}
