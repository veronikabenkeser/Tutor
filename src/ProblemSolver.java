import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProblemSolver {
	private Permutations permutations;
	//private AutomaticRules rules;
	private ArrayList<Integer> nodes;
	private HashMap<Integer, Integer> mMap;

	public ProblemSolver(ArrayList<Integer> nodes, Permutations permutations, ArrayList<String>  participants) {
		this.permutations = permutations;
		//this.rules = rules;
		this.nodes = nodes;
		mParticipants = participants;
	}

	/*
	 * This method finds all the permutations that fit the rules outlined in the
	 * AutomaticRules class.
	 */
	public void findSolution(ArrayList<Integer> nodes) {
		AutomaticRules rules = new AutomaticRules();
		
		while (permutations.hasPerms()) {
			
			if (rules.getIsValid(nodes)) {
				solutionsArr.add(new ArrayList<Integer>(nodes)); //we must add the nodes this way in order for the solutionsArr to populate correctly.
			}
			nodes = permutations.nextPerm();
		}
		System.out.println("Solutions array : " + solutionsArr);
	}
	
	private boolean oneSolExists(){
		boolean result = false;
		if (solutionsArr.size() == 1) {
			solutionsArrMBT.addAll(solutionsArr.get(0));
			result = true;
		}
		return result;
	}
	
	private HashMap<Integer, Integer> determineIndivMBT(ArrayList<ArrayList<Integer>> solutionsArr){
		mMap = new HashMap<Integer, Integer>();
		
		int k = 0;
		if (k < nodes.size()) {

			//if (solutionsArr.size() == 1) {
			//	solutionsArrMBT.addAll(solutionsArr.get(0));
			//}

			for (int i = 0; i < solutionsArr.size() - 1; i++) {
				if (solutionsArr.get(i).get(k) == solutionsArr.get(i + 1)
						.get(k)) {
					// System.out.println("i" + i);
					// System.out.println(solutionsArr.size()-2);
					if (i == solutionsArr.size() - 2) {
						
						System.out.println("TRUE"); //found a repeated number/letter

						mMap.put(k, solutionsArr.get(i).get(k));
						System.out.println("mMap : " + mMap);
						k++;
						if (k == nodes.size())
							break;
					}
				} else {
					k++;
					i--;
					// System.out.println("i is " + i);
					// System.out.println("k is "+k);
					if (k == nodes.size())
						break;
				}
			}
		}
		return mMap;
	}
	
	public void getAllAnswers(ArrayList<Integer> nodes){
		findSolution(nodes);//updates the solutionsArr
		
		CoverterToLetters conv = new CoverterToLetters(mParticipants);
		conv.updateletterIndexMap();
		
		if (oneSolExists()){
			System.out.println("Answer: " + solutionsArrMBT);
			conv.mapLettersToNumbers(solutionsArrMBT);
			
		} else {
			mMap =determineIndivMBT(solutionsArr);
			if(!mMap.isEmpty()){
				conv.mapLettersToNumbers(mMap);
			}
		}
	}
	
	private ArrayList<Integer> calcSingleSol() {
		int k = 0;
		if (k < nodes.size()) {

			//if (solutionsArr.size() == 1) {
			//	solutionsArrMBT.addAll(solutionsArr.get(0));
			//}

			for (int i = 0; i < solutionsArr.size() - 1; i++) {
				if (solutionsArr.get(i).get(k) == solutionsArr.get(i + 1)
						.get(k)) {
					// System.out.println("i" + i);
					// System.out.println(solutionsArr.size()-2);
					if (i == solutionsArr.size() - 2) {
						System.out.println("TRUE");
						k++;
						if (k == nodes.size())
							break;
					}
				} else {
					k++;
					i--;
					// System.out.println("i is " + i);
					// System.out.println("k is "+k);
					if (k == nodes.size())
						break;
				}
			}
		}
		if (solutionsArrMBT.isEmpty()) {
			System.out
					.println("There is more than one solution to this problem.");
		} else {
			System.out.println("solutions arr for MBT: " + solutionsArrMBT);
		}
		return solutionsArrMBT;
	}
	

	public ArrayList<Integer> getSingleSolutionMBT() {
		return calcSingleSol();
	}

	public void getSolution(ArrayList<Integer> nodes) {
		findSolution(nodes);
	}

	public ArrayList<Integer> getSolutionsArrMBT() {
		return solutionsArrMBT;
	}

	//private HashMap<Integer, Integer> mMap = new HashMap<Integer, Integer>();
	private ArrayList<Integer> solutionsArrMBT = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> solutionsArr = new ArrayList<ArrayList<Integer>>();
	private ArrayList<String> mParticipants = new ArrayList<String>();

}
