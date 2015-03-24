import java.util.ArrayList;
import java.util.HashMap;


public class CoverterToLetters {
	private ProblemSolver problemSolver;
	private Reader reader;
	
	public CoverterToLetters(ProblemSolver problemSolver, Reader reader){
		this.problemSolver = problemSolver;
		this.reader = reader;
	}

	private void updateSingleSolMBTArr(){
		singleSolutionMBT = problemSolver.getSingleSolutionMBT();
		//return singleSolutionMBT;
	}
	private void getParticipants(){
		mAllParticipants =  reader.setParticipants();
		//return mAllParticipants;
	}
	
	private void updateletterIndexMap(){
		getParticipants();
		for(int i =0; i<mAllParticipants.size(); i++){
			letterIndexMap.put(i, mAllParticipants.get(i));
		}
		
	}
	
	private void mapLettersToNumbers(){
		updateletterIndexMap();
		updateSingleSolMBTArr();
		
		for(int i=0; i<singleSolutionMBT.size(); i++){
			letterIndexMap.put(singleSolutionMBT.get(i), letterIndexMap.get(i));
		}
		System.out.println(letterIndexMap);
		//getSingleSol();
	}
	
	/*
	private void mapLettersToNumbers(ArrayList<Integer> singleSolutionMBT, HashMap<Integer, String> letterIndex){
		for(int i=0; i<singleSolutionMBT.size(); i++){
			singleSolutionMBTLettersMap.put(singleSolutionMBT.get(i), letterIndex.get(i));
		}
		System.out.println(singleSolutionMBTLettersMap);
		//getSingleSol();
	}
	
	private ArrayList<String> singleSol(){
		for(int i=0; i<letterIndexMap.size(); i++){
			singleSolutionMBTLettersArr.add(i, singleSolutionMBTLettersMap.get(i));
		}
		return singleSolutionMBTLettersArr;	
	}
	
	public ArrayList<String> getSingleSol(ArrayList<Integer> singleSolutionMBT, HashMap<Integer, String> letterIndex){
		mapLettersToNumbers(singleSolutionMBT, letterIndex);
		return singleSol();
	}
	
	*/

	private ArrayList<Integer>singleSolutionMBT = new ArrayList<Integer>();
	private ArrayList<String> singleSolutionMBTLettersArr = new ArrayList<String>();
	private ArrayList<String> mAllParticipants = new ArrayList<String>();
	private HashMap<Integer, String> letterIndexMap = new HashMap<Integer, String>();

}

