import java.util.ArrayList;
import java.util.HashMap;


public class CoverterToLetters {
	//private ProblemSolver problemSolver;

	private void mapLettersToNumbers(ArrayList<Integer> singleSolutionMBT, HashMap<Integer, String> letterIndex){
		for(int i=0; i<singleSolutionMBT.size(); i++){
			singleSolutionMBTLettersMap.put(singleSolutionMBT.get(i), letterIndex.get(i));
		}
		System.out.println(singleSolutionMBTLettersMap);
		//getSingleSol();
	}
	
	private ArrayList<String> singleSol(){
		for(int i=0; i<singleSolutionMBTLettersMap.size(); i++){
			singleSolutionMBTLettersArr.add(i, singleSolutionMBTLettersMap.get(i));
		}
		return singleSolutionMBTLettersArr;	
	}
	
	public ArrayList<String> getSingleSol(ArrayList<Integer> singleSolutionMBT, HashMap<Integer, String> letterIndex){
		mapLettersToNumbers(singleSolutionMBT, letterIndex);
		return singleSol();
	}

	//private ArrayList<Integer>singleSolutionMBT = new ArrayList<Integer>();
	private HashMap<Integer, String> singleSolutionMBTLettersMap= new HashMap<Integer, String>();
	private ArrayList<String> singleSolutionMBTLettersArr = new ArrayList<String>();

}

