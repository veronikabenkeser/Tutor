import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * This program solves logic puzzles by doing permutations until permutations that fit that logic rules specified in the Rules class are found.
 * The answers are then saved into an ArrayList of arrayslists of integers. In order to determine a master diagram that would be the base for
 * all of the solutions, the program tests all the permutations in the ArrayList discussed above. For example, suppose that we have the following
 * index to hold a particular value:
 * 
	private static final int G = 0; G is always at index 0
	private static final int J = 1; J is always at index 1
	private static final int L = 2;
	private static final int M = 3;
	private static final int P = 4;
	private static final int V = 5;
 * 
 * In addition, suppose that the solution to our problem is calculated to be nodes = 2 3 6 5 4 1. (It is important to remember that it is 
 * necessary to have as many letters as there are nodes in order for the program to work.) The solution is then decoded back into letters:
 * 
 *  the letter at index 0 is 2nd;
 *  the letter at index 1 is 3rd;
 *  the letter at index 2 is 6th;
 *  the letter at index 3 is 5th;
 *  the letter at index 4 is 4th;
 *  the letter at index 5 is 1st.
 */
public class Main {
	
	public static void main(final String[] args) {
		Rules rules = new Rules();
		ArrayList<Integer>nodes = new ArrayList<Integer>();
		nodes = rules.getNodes();
		Permutations permutations = new Permutations(nodes);
		ProblemSolver solver= new ProblemSolver(nodes, permutations, rules);
		solver.findSolution(nodes);
		solver.MBT();
	}
		
		
				/*		
				graph.getArrFromMap();
				System.out.println(graph.getArrFromMap());
				graph.myMap.clear();
				// graph.arrUpdated= new ArrayList<String>();
			
    
    */
    //String[] arrS = new String[6];
    //Iterator<String> it = arr.iterator();
   
    /*
    private ArrayList<String> getArrFromMap(){
    	ArrayList<String> arrUpdated= new ArrayList<String>();
    	for (int i=1; i<=names.length; i++){
    		arrUpdated.add(myMap.get(i));
    		variations.add(arrUpdated);
    	}
    	return arrUpdated;	    	
    }
    
   
    
    private ArrayList<String>  getMBT(){
        System.out.println(variations);

    }
    */
    
    /*Private instance variables*/
private  ArrayList<String> variations;

}