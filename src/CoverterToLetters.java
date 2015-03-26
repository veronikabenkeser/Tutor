import java.util.ArrayList; 
import java.util.HashMap;

public class CoverterToLetters {
	private ProblemSolver problemSolver;
	
	//public CoverterToLetters(ProblemSolver problemSolver, ArrayList<String> aa){
		//this.problemSolver = problemSolver;
		//mAllParticipants = aa;
	//}
	
	public CoverterToLetters(ArrayList<String> aa){
		mAllParticipants = aa;
	}

	//private void updateSingleSolMBTArr(){
	//	singleSolutionMBT = problemSolver.getSolutionsArrMBT();
	//}
	
	public void updateletterIndexMap(){
		//getParticipants();
		for(int i =0; i<mAllParticipants.size(); i++){
			letterIndexMap.put(i, mAllParticipants.get(i));
		}
		System.out.println(letterIndexMap);
	}

	public void mapLettersToNumbers(ArrayList<Integer> solutionsArrMBT) {
		//updateletterIndexMap();
		// updateSingleSolMBTArr();
		for (int i = 0; i < solutionsArrMBT.size(); i++) {
			singleSolutionMBTLettersMap.put(solutionsArrMBT.get(i),
					letterIndexMap.get(i));
		}
		System.out.println("singleSolutionMBTLettersMap "
				+ singleSolutionMBTLettersMap);

		for (int i = 1; i <= letterIndexMap.size(); i++) {
			singleSolutionMBTLettersArr.add(singleSolutionMBTLettersMap.get(i));
		}
		System.out.println("singleSolutionMBTLettersArr"
				+ singleSolutionMBTLettersArr);
	}

	public void mapLettersToNumbers(HashMap<Integer, Integer> mMap) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < letterIndexMap.size(); i++) {
			if(mMap.containsKey(i)){
				sb.append(letterIndexMap.get(i));
				sb.append(" is ");
				sb.append(mMap.get(i));
			}
		}
		System.out.println(sb.toString());
	}
	//public void getPublicSolInLetters(){
	//	mapLettersToNumbers();
	//	if (!singleSolutionMBTLettersArr.isEmpty()){
	//		System.out.println("HERE IS THE SOL IN LETTERS: " + singleSolutionMBTLettersArr );
	//	} else {
			
	//	}
		
		
	//}
	
	private ArrayList<Integer>singleSolutionMBT = new ArrayList<Integer>();
	private ArrayList<String> singleSolutionMBTLettersArr = new ArrayList<String>();
	private ArrayList<String> mAllParticipants = new ArrayList<String>();
	private HashMap<Integer, String> letterIndexMap = new HashMap<Integer, String>();
	private HashMap<Integer, String> singleSolutionMBTLettersMap = new HashMap<Integer, String>();
}



