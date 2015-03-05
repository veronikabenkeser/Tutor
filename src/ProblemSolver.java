import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ProblemSolver {
	Permutations permutations;
	Rules rules;
	List<Integer> nodes;
	
	ProblemSolver(List<Integer>	nodes, Permutations permutations, Rules rules){
		this.permutations = permutations;
		this.rules = rules;
		this.nodes = nodes;	
	}
	
	void findSolution(){
		while (permutations.hasPerms()) {
			if (rules.isValid(nodes)) {
				System.out.println(nodes);
			}
			nodes = permutations.nextPerm();
		}
	}
 
	
	/*
	 * This method takes nodes and names as its parameters and creates a hashmap where values 1 through 
	 * nodes.length are the keys and letters listed in the Rules class are the corresponding values.
	 * @param nodes 
	 * @param names
	 */
	private void mapNumbersToLetters(int[] nodes, String[] names){
		for (int i=0; i<names.length; i++){
			myMap.put(nodes[i], names[i]);
		}
	}
	
	private HashMap<Integer, String> myMap = new HashMap<Integer, String>();
}
