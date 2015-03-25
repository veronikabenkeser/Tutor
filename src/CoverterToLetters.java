import java.util.ArrayList; 
import java.util.HashMap;

public class CoverterToLetters {
	private ProblemSolver problemSolver;
	
	public CoverterToLetters(ProblemSolver problemSolver, ArrayList<String> aa){
		this.problemSolver = problemSolver;
		mAllParticipants = aa;
	}

	private void updateSingleSolMBTArr(){
		singleSolutionMBT = problemSolver.getSolutionsArrMBT();
	}
	
	private void updateletterIndexMap(){
		//getParticipants();
		for(int i =0; i<mAllParticipants.size(); i++){
			letterIndexMap.put(i, mAllParticipants.get(i));
		}
		System.out.println(letterIndexMap);
	}
	
	private void mapLettersToNumbers(){
		updateletterIndexMap();
		updateSingleSolMBTArr();
		
		for(int i=0; i<singleSolutionMBT.size(); i++){
			singleSolutionMBTLettersMap.put(singleSolutionMBT.get(i), letterIndexMap.get(i));
		}
		System.out.println("singleSolutionMBTLettersMap " + singleSolutionMBTLettersMap);
		
		for(int i=1; i<=letterIndexMap.size(); i++){
			singleSolutionMBTLettersArr.add(singleSolutionMBTLettersMap.get(i));
		}		
	}
	
	public ArrayList<String> getPublicSolInLetters(){
		mapLettersToNumbers();
		System.out.println("HERE IS THE SOL IN LETTERS: " + singleSolutionMBTLettersArr );
		return singleSolutionMBTLettersArr;
	}
	
	private ArrayList<Integer>singleSolutionMBT = new ArrayList<Integer>();
	private ArrayList<String> singleSolutionMBTLettersArr = new ArrayList<String>();
	private ArrayList<String> mAllParticipants = new ArrayList<String>();
	private HashMap<Integer, String> letterIndexMap = new HashMap<Integer, String>();
	private HashMap<Integer, String> singleSolutionMBTLettersMap = new HashMap<Integer, String>();
}



