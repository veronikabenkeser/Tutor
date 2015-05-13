import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemSolver {

private ArrayList<String> people;
private ArrayList<Rule> rules;
private Permutations permutations;
private ArrayList<Integer> nodes;
private Parser parser;
private ArrayList<String> allParticipants;
HashMap<String, Integer>playerAssignment;

public ProblemSolver(ArrayList<String> people, Parser parser, Permutations permutations) {
	this.permutations = permutations;
	this.parser = parser;
	this.people=people;
	rules = new ArrayList<Rule>();
	nodes=permutations.getNodes();
	
	for (String ruleUnmodified: parser.setFinalRules()){
		addRule(ruleUnmodified);
	}
}

/*
 * @param text individual rule derived from parser.setFinalRules()
 * This method creates an instance of an anonymous class for each derived rule. That instance
 * implement the Rule interface and is stores in the rules array
 */
public void addRule(String text) {
	
	final String cleanText = text.replaceAll("\\s+","");
	
	/*
	 * Creating an anonymous inner class that implements the Rule interface.
	 */
	Rule rule = new Rule() { // defining and instantiating an inner class at the same time
		@Override
		public boolean evaluate(ArrayList<Integer> nodes) { //implementation of the evaluate(nodes) method outlined in the Rule interface
			String copyOfCleanText = cleanText;
			
			copyOfCleanText = evaluateSimple(copyOfCleanText, playerAssignment, nodes);
			System.out.println("copyOfCleanText "+copyOfCleanText);
			
			if (!copyOfCleanText.equals("true") && !copyOfCleanText.equals("false")){
				copyOfCleanText = evaluateComplex(copyOfCleanText);
				copyOfCleanText = evaluateComplex2(copyOfCleanText);
				boolean solution = solve(copyOfCleanText);
				return solution;
			}
			return Boolean.parseBoolean(copyOfCleanText);
		}
		
		/*
		 * This method finds the name variable, relationship(== or !=), and the required position of the variable.  
		 */
		private String evaluateSimple(String text, HashMap<String, Integer> playerAssignment, ArrayList<Integer> nodes){
			Pattern pattern = Pattern.compile("nodes\\.get\\((\\w{1})\\)(==|\\!=)(\\d)");
			Matcher matcher = pattern.matcher(text);
			System.out.println("text "  +text);
			while(matcher.find()){
				String name = matcher.group(1);
				System.out.println("name " + name);
				String operator = matcher.group(2);
				System.out.println(operator);
				String value = matcher.group(3);
				System.out.println(value);
				text=matcher.replaceFirst(""+ scenarioFitsRule(name, operator, value, playerAssignment, nodes)); //"" is making it into a String
				System.out.println("updating " + text);
				matcher = pattern.matcher(text);//do we need this??
			}
			System.out.println("simple: " + text);
			return text;
		}
		
		private String evaluateComplex(String text){
			Pattern pattern = Pattern.compile("if\\((.*)\\)");
			Matcher matcher = pattern.matcher(text);
				while(matcher.find()){
					String condition  = matcher.group(1);
					if (!condition.equals("true") && !condition.equals("false")){
						//simplify the conditions until we get 1 value -- either "true" or "false"
						condition = simplifyConditions(condition);
					}
					text = matcher.replaceFirst(condition);
				}		
			System.out.println("evaluate Complex: " + text);
			return text;
		}
		
		private String evaluateComplex2(String text){
			Pattern pattern = Pattern.compile("\\{return\\((.*)\\)");
			Matcher matcher = pattern.matcher(text);
			
			while(matcher.find()){
				String condition  = matcher.group(1);
				if (!condition.equals("true") && !condition.equals("false")){
					//simplify the conditions until we get 1 value -- either "true" or "false"
					condition = simplifyConditions(condition);
				}
				text = matcher.replaceFirst(condition);
			}		
			System.out.println("evaluate Complex2: " + text);
			return text;
		}
	};
	System.out.println("HELLO WORLD!");
	rules.add(rule);
	
	//System.out.println(""+rules.get(0).evaluate(nodes));
}
		
private String simplifyConditions(String condition){
	Pattern pattern2= Pattern.compile("(\\w+)(&&|\\|\\|)(\\w+)");
	Matcher matcher2= pattern2.matcher(condition);
	
	while (matcher2.find()){
		String operator = matcher2.group(2);
		//findPartOnTheLeft
		
		boolean left = Boolean.parseBoolean(matcher2.group(1));
		boolean right = Boolean.parseBoolean(matcher2.group(3));
		condition = matcher2.replaceFirst(evBoolean(left, operator, right));
		matcher2= pattern2.matcher(condition);//do we need this??
	}
	
	return condition;
}		

/*
 * @param text. String of the type true&&true.
 * The method determines whether the value of the entire stire string is true or false.
 */

private String evBoolean(boolean left, String operator, boolean right){
	boolean result=false;
	if (operator.equals("&&")){
			result = left&&right;
	} else {
		result = left||right;
	}
	return (""+result);
}

private boolean solve(String text){ 
	Pattern pattern = Pattern.compile("(true|false).*(true|false)");
	Matcher matcher = pattern.matcher(text);
	
	while(matcher.find()){
		String premise = matcher.group(1);
		String conclusion = matcher.group(2);
		
		if (premise.equals("true") && conclusion.equals("false")){
			return false;
		}
	}
	return true;
}
	
/*
 * This method evaluates whether the digit at position X in the nodes array 
 * is the one required by the rules of the particular problem. If so, the method returns 
 * true. Otherwise, the method returns false. Each part of the expression is evaluated one by one.
 * For example,
 * There are three players -- X, Y, and Z -- who are assigned values 0, 1, and 2 respectively;
 * the nodes are 1, 2, and 3; and the only rule we have is that X is not second, if Z is first. 
 * First, the method will evaluate whether "Z is first" under a particular arrangement of nodes. Then,
 * the method will evaluate whether "X is not second" is true under the same arrangement of nodes.
 * The method returns "true" or "false", which will then be substituted for the evaluated text, making
 * one of the resulting expressions look like this: "if(true){false;}"
 */

//the "name" is required to have position "value"
private boolean scenarioFitsRule(String name, String operator, String value, HashMap<String, Integer> playerAssignment, ArrayList<Integer> nodes) {
	if (operator.equals("==")) {
		return nodes.get(playerAssignment.get(name)) == Integer.parseInt(value);
	} else if (operator.equals("!=")) {
		return nodes.get(playerAssignment.get(name)) != Integer.parseInt(value);
	}
	
	return false;	
}

private ArrayList<String[]> getAllPossibleSolutions(){
	ArrayList<String[]> ultimateSol = new ArrayList<String[]>();

	//Assign a number to each player. This assignment remains constant.
	playerAssignment = createPlayerAssignment(people);
	System.out.println("playerAssignment" + playerAssignment);
	
	//Check each permutation against all of the rules.
	while (permutations.hasPerms()) {
		
		if (getIsValid(nodes)) {
			
			solutionsArr.add(new ArrayList<Integer>(nodes)); //we must add the nodes this way in order for the solutionsArr to populate correctly.
		}
		nodes = permutations.nextPerm();
	}
	
	//Return solutions to the game, where each player is in his correct position.
	//TODO:
	//System.out.println("solutions array HERE : "  + solutionsArr);
	for(ArrayList<Integer>sol: solutionsArr){
		String[] temp = new String[people.size()];
		
		for(Map.Entry<String, Integer> entry: playerAssignment.entrySet()){
			int positionOfKey = sol.get(entry.getValue());
			positionOfKey-=1;
			temp[positionOfKey] = entry.getKey();
		}
		ultimateSol.add(temp);
	}
	
	//System.out.println("Solutions array : " + solutionsArr);
	//System.out.println("Solutions array in letters: "+ ultimateSol);
	for (String[] a: ultimateSol){
		System.out.println("SOLUTION " + Arrays.deepToString(a));
	}
	
	//System.out.println("corresponding solutionsArr"  + solutionsArr);
	
	
	return ultimateSol;
}

public ArrayList<String[]> setAllPossibleSolutions(){
	return getAllPossibleSolutions();
}

private HashMap<Integer, String> convertToLetters(){
	HashMap<Integer, String>numToLetterMap = createNumToLetterMap();
	for(ArrayList<Integer> sol: solutionsArr){
		for (int i=0; i<people.size(); i++){
			int positionOfLetter = sol.get(i);
		}
	}
	return numToLetterMap;
}

public HashMap<Integer, String> createNumToLetterMap(){
	HashMap<Integer, String> numToLetterMap = new HashMap<Integer, String>();
	for(int i =0; i<people.size(); i++){
		numToLetterMap.put(i, people.get(i));
	}
	return numToLetterMap;
}


/*
 * @param nodes returned from the Permutations class upon nth permutation
 * This method checks each rule to see whether this particular permutation of
 * nodes violates at least 1 rule. If a rule is violated, we return "false,"
 * signaling that this particular permutation will not be one of the possible
 * solutions.
 */
 public boolean getIsValid(final ArrayList<Integer> nodes){ 
   for (Rule rule : this.rules) {
	   if(!rule.evaluate(nodes)){
		   return false;
	   }
   }
   return true;
 }
 
 /*
  * This method maps players to numbers from 0 to the number of players - 1. This assignment always stays constant.
  */
 private HashMap<String, Integer> createPlayerAssignment(ArrayList<String> people) {
	 playerAssignment  = new HashMap<String, Integer>();
	 
	 for (int i = 0; i < people.size(); i++) {
		 playerAssignment.put(people.get(i), i);
	 }
	 
	 return playerAssignment;
 }
 
 /*
  * @param scenarios      all the possible solutions that meet all of the rules provided by the problem.
  * This method finds all MBT conditions across all of the solutions. For example, if player A is always 1st,
  * player A will be mapped to number 1.
  */
 public HashMap<String, Integer> findMBT(ArrayList<String[]> scenarios){
	 HashMap<String, Integer>myMap = new HashMap<String, Integer>();
	 
	 /*
	  * If there is more than one way to arrange the letters to meet the given rules, start by examining the first letter of every
		scenario to see whether that letter always holds that position. If it does not, go back to the first member of the scenarios 
	    array and examine the next letter in the same way.
	 */
	 
	 if (scenarios.size()>1){	
 		int k = 0;
 		if (k < people.size()) {
 			for (int i = 0; i < scenarios.size() - 1; i++) {
 				String[] element = scenarios.get(i);
 				String[] element2 = scenarios.get(i+1);
 				
 				if (element[k] == element2[k]){
 					// System.out.println("i" + i);
 					// System.out.println(solutionsArr.size()-2);
 					if (i == scenarios.size() - 2) {
 						
 						//System.out.println("TRUE"); //found a repeated number/letter

 						myMap.put(element[k], k+1);
 						System.out.println("myMap : " + myMap);
 						//start checking the next letter
 						k++;
 						if (k == people.size()) break;
 					}
 				} else {
 					//Go back to the beginning and test the next letter
 					k++;
 					i=0;
 					if (k == people.size())
 						break;
 				}
 			}
 		}
	} else {
		//Go through all the letters in the solution and create a map where keys are letters and values are the positions 
		//of those letters
		String[] singleSolution = scenarios.get(0);
		for(int i =1; i<=singleSolution.length; i++){
			myMap.put(singleSolution[i-1], i);
		}
	}
	System.out.println("myMap : " + myMap);
	return myMap;
 }
 
 private ArrayList<ArrayList<Integer>>solutionsArr = new ArrayList<ArrayList<Integer>>();
 
}