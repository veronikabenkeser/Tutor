import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionSolver3 {

private ProblemSolver ps;
private String logicPrompt;
private String textPrompt;
private String[] logicAnswerChoices;
private String[] textAnswerChoices;
private ArrayList<QuestionInterface> myQuestions;
private ArrayList<String[]> allSolutions;
private ArrayList<Question> finalQs;

public QuestionSolver3(ProblemSolver ps, FinalQuestion objQA) {
	this.ps = ps;
	allSolutions = ps.getAllPossibleSolutions();
	textPrompt = objQA.getQuestionToDisplay();
	textAnswerChoices = objQA.getAnswerChoicesToDisplay();
	logicPrompt = objQA.getModifiedQuestion();
	logicAnswerChoices = objQA.getModifiedAnswerChoices();
	myQuestions = new ArrayList<QuestionInterface>();
	
	System.out.println("step 1B");
	addQuestion(textPrompt, logicPrompt, textAnswerChoices, logicAnswerChoices, allSolutions);
	}


public Question doAction(){
	Question finalQ = null;
//	finalQs = new ArrayList<Question>();
	for(QuestionInterface q: myQuestions){
		int answer = q.eval(allSolutions);
		String prompt = q.getTextPrompt();
		String[] answerChoices = q.getTextAnswerChoices();
		finalQ = new Question(prompt, answerChoices, answer);
		System.out.println("AUTUMN CAT ANSWER: " + answer);
		//finalQs.add(finalQ);
	}
	
	System.out.println("This is the final step in the doAction()method in the QuestionSolver3 class. " );
	
	return finalQ;
}

public void addQuestion(String textPrompt0, String logicPrompt0, String[] textAnswerChoices0, String[] logicAnswerChoices0, ArrayList<String[]> allsolutions) {
	System.out.println("addQuestion(...) method right now");
	final String textPrompt = textPrompt0;
	final String logicPrompt = logicPrompt0;
	final String[] textAnswerChoices = textAnswerChoices0;
	final String[] logicAnswerChoices = logicAnswerChoices0;
	
	QuestionInterface qu = new QuestionInterface() { // defining and instantiating an inner class at the same time
		
		String copyTextPrompt = textPrompt;
		String copyLogicPrompt =logicPrompt;
		String[] copyLogicAnswerChoices= logicAnswerChoices;
		String[] copyTextAnswerChoices = textAnswerChoices;
		
		@Override

		public int eval(ArrayList<String[]> allSolutions) {
			
			for (String s: copyLogicAnswerChoices){
				s= s.replace("\\s+", "");
			}
			
			
			if (copyLogicPrompt.contains("MBT")){
//				ArrayList<String[]> relScenarios = evalMBT(copyLogicPrompt, copyLogicAnswerChoices, allSolutions);
//				System.out.println("SCENARIOS" + relScenarios);
//				// examine relevant scenarios to find a MBT answer choice
//				answer = findSolution(copyLogicAnswerChoices, relScenarios); // Answer as an int?? 
//				System.out.println("and here relSc size is " + relScenarios.size());
//			}
//			return answer;
				System.out.println("ABOUT TO PRINT THE ANSWER BELOW>>>");
				return evalMBT(copyLogicPrompt, copyLogicAnswerChoices, allSolutions);
			}
			return -1;
		}
		
		public String getTextPrompt(){
			return copyTextPrompt;
		}
		
		public String[] getTextAnswerChoices(){
			return copyTextAnswerChoices;
		}
	};
	System.out.println("addQuestion(...) method right now");
	System.out.println("qu.getTextAnswerChoices(): "+qu.getTextAnswerChoices());
	myQuestions.add(qu);
}

//private int findSolution(String[] copyLogicAnswerChoices, ArrayList<String[]> relScenarios) {
//	int count=1;
//	for(int i=0; i < copyLogicAnswerChoices.length; i++){
//		String text = copyLogicAnswerChoices[i];
//		ArrayList<String[]> result = getAllScenariosThatFitExtraCond(text, relScenarios);
//		if (result.size() == relScenarios.size()){
//			System.out.println("prior to result: " + result.size());
//			System.out.println(relScenarios.size());
//			return count;
//		}
//	}
//	return -1; //CHANGE THIS.
//}

public int evalMBT(String copyLogicPrompt, String[] copyLogicAnswerChoices, ArrayList<String[]> allSolutions){
	System.out.println("Step1 of evalMBT");
	ArrayList<String[]> allRelScenarios = getAllScenariosThatFitExtraCond(copyLogicPrompt, allSolutions);
	System.out.println("Step2 of evalMBT");
	for(int i=0; i <copyLogicAnswerChoices.length; i++){
		if (fitsAllScenarios(copyLogicAnswerChoices[i], allRelScenarios)){
			return i+1;
		}
	}
	return -1; //CHANGE THIS.
}

private boolean fitsAllScenarios(String text, ArrayList<String[]> allSolutions){
	String value="";
	String operator="";
	String name="";
	
	Pattern pattern = Pattern.compile(".*\\({0,1}\\s*nodes\\.get\\((\\w{1})\\)\\s*(==|\\!=)\\s*(\\d).*");
	Matcher matcher = pattern.matcher(text);
	System.out.println("text "  +text);
	while(matcher.find()){  //NEED TI FIX THIs WHILE TO FIT CONJUNCTION CASES
		System.out.println("Im in the finder here.");
		name = matcher.group(1);
		System.out.println("name " + name);
		operator = matcher.group(2);
		System.out.println(operator);
		value = matcher.group(3);
		System.out.println(value);
		
	
	int intValue= Integer.parseInt(value);
	if (operator.equals("==")) {
		for(String[] s: allSolutions){
			if(!s[intValue-1].equals(name)){
				return false;
			}
		}
	} else if (operator.equals("!=")) {
		for(String[] s: allSolutions){
			if(s[intValue-1].equals(name)){
				return false;
			}
		}
	}
	return true;
	}
	return false;
}

private ArrayList<String[]> getAllScenariosThatFitExtraCond(String text, ArrayList<String[]> allSolutions){
	ArrayList<String[]> relevantScenarios = new ArrayList<String[]>();
	
	 	HashMap<String, Integer> solutionsMap = new HashMap<String, Integer>();
	 	
		Pattern pattern = Pattern.compile(".*\\({0,1}\\s*nodes\\.get\\((\\w{1})\\)\\s*(==|\\!=)\\s*(\\d).*");
		Matcher matcher = pattern.matcher(text);
		System.out.println("text "  +text);
		while(matcher.find()){  
			System.out.println("Im in the finder here.");
			String name = matcher.group(1);
			System.out.println("name " + name);
			String operator = matcher.group(2);
			System.out.println(operator);
			String value = matcher.group(3);
			System.out.println(value);
			
			relevantScenarios = getQSolutions(name, operator, value, allSolutions);

			for(String[] a : relevantScenarios){
				System.out.println("ans: "+ Arrays.toString(a));
			}
		
		}
		return relevantScenarios;
}
/*
 * @ param name             Name of the player
 * @ param operator         Equality relationship/ whether the player the player's position is or is not X
 * @ param allSolutions     ArrayList of an array of Strings originally produced by ProblemSolver 
 * 
 * This method examines the condition given in the questions and goes through all of the solutions that satisfied 
 * the original rules of the problem to pick out the scenarios that also meet this condition. All such scenarios 
 * are added to an ArrayList of arrays of Strings called qSolutions.
 */
private ArrayList<String[]> getQSolutions(String name, String operator, String value, ArrayList<String[]> allSolutions) {
	int intValue= Integer.parseInt(value);
	if (operator.equals("==")) {
		for(String[] s: allSolutions){
			if(s[intValue-1].equals(name)){
				qSolutions.add(s);
			}
		}
	} else if (operator.equals("!=")) {
		for(String[] s: allSolutions){
			if(!s[intValue-1].equals(name)){
				qSolutions.add(s);
			}
		}
	}
	return qSolutions;
}
			


private ArrayList<String[]> qSolutions = new  ArrayList<String[]>();
private ArrayList<Question> questions = new ArrayList<Question>();
}