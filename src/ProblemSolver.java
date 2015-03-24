import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProblemSolver {
	private Permutations permutations;
	private AutomaticRules rules;
	private ArrayList<Integer> nodes;

	public ProblemSolver(ArrayList<Integer> nodes, Permutations permutations,
			AutomaticRules rules) {
		this.permutations = permutations;
		this.rules = rules;
		this.nodes = nodes;
	}

	/*
	 * This method finds all the permutations that fit the rules outlined in the
	 * Rules class.
	 */
	private void findSolution(ArrayList<Integer> nodes) {
		while (permutations.hasPerms()) {
			if (rules.getIsValid(nodes)) {
				ArrayList<Integer> nodesHolder = new ArrayList<Integer>();
				nodesHolder = (ArrayList<Integer>) nodes.clone();
				solutionsArr.add(nodesHolder);
			}
			nodes = permutations.nextPerm();
		}
		System.out.println("SOLUTIONS ARR" + solutionsArr);
	}

	private ArrayList<Integer> calcSingleSol() {
		int k = 0;
		if (k < nodes.size()) {

			if (solutionsArr.size() == 1) {
				solutionsArrMBT.addAll(solutionsArr.get(0));
			}

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
		System.out
				.println("Getting value of ProblemSolver.getSOlutionsArrMBT()");
		return this.solutionsArrMBT;
	}

	private HashMap<Integer, String> myMap = new HashMap<Integer, String>();
	private ArrayList<ArrayList<Integer>> solutionsArr = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Integer> solutionsArrMBT = new ArrayList<Integer>();
}
