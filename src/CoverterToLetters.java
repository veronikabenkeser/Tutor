import java.util.ArrayList;
import java.util.HashMap;


public class CoverterToLetters {
	private ProblemSolver problemSolver;
	private Rules rules;
	
	/*
	 * @param solutionsArrMBT An ArrayList of integers that shows the correct order of our letters
	 * @param names Letters listed in the rules.
	 * 
	 */
	
	public void mapLettersToNumbers(){
		singleSolutionMBT = problemSolver.getSolutionsArrMBT();
		letterIndex = rules.getLetterIndex();
		
		for(int i=0; i<singleSolutionMBT.size(); i++){
			singleSolutionMBT.get(i);
		
		
		String letter = letterIndex.get(i);
		singleSolutionMBTLetters.add(singleSolutionMBT.get(i), letter);;
		
		}
	}

	private ArrayList<Integer>singleSolutionMBT = new ArrayList<Integer>();
	private HashMap<Integer, String> letterIndex = new HashMap<Integer, String>();
	private ArrayList<String> singleSolutionMBTLetters = new ArrayList<String>();
}
