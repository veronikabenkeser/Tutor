/*import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import javax.swing.SwingUtilities;


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
/*
public class Main {
	public Main(){
		
	}
	
	//public static void main(final String[] args) {
	/*
		public static void main(final String[] args) {
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {// method of the anonymous class that implements the Runnable interface. 
					Reader reader = new Reader();
					Gui gui = new Gui(reader);
					gui.createAndShowGUI();		
					gui.setVisible(true);
				}
			});
			
			

		
			/*
		AutomaticRulesWriter rw = new AutomaticRulesWriter();
		rw.printRulesToFile();
	
		//doneRules rules = new Rules();
		//done ArrayList<Integer>nodes  = new ArrayList<Integer>();
	   //done nodes = rules.getNodes();
	    
	    
		Permutations permutations = new Permutations(nodes);
		ProblemSolver problemSolver= new ProblemSolver(nodes, permutations, rules);
		
		
		CoverterToLetters converter = new CoverterToLetters();
		Reader reader = new Reader();
		//reader.readProblem("P1");
		//reader.getParticipants();
		//String noun = reader.getNoun();
		//String newText = reader.changeText();
		Parser parser = new Parser(reader);
		//System.out.println("IS THIS A SIMPLE GAME?" + parser.isSimpleOrderingGame());
		
		problemSolver.getSolution(nodes);
		problemSolver.getMBT();
	    System.out.println(converter.getSingleSol(problemSolver.getSolutionsArrMBT(), rules.getLetterIndex()));
	    
	    */
			/*
	}
}
*/
		