import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * It is necessary to have as many letters as there are nodes in order for the program to work.
 */
public class Main {
	
	public static void main(final String[] args) {
		Rules rules = new Rules();
		List<Integer>nodes = new ArrayList<Integer>();
		nodes = rules.getNodes();
		Permutations permutations = new Permutations(nodes);
		ProblemSolver solver= new ProblemSolver(nodes, permutations, rules);
		solver.findSolution();
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
private int[] numbOfPlayers= new int[] { 1, 2, 3, 4, 5, 6};
private  ArrayList<String> variations;

}