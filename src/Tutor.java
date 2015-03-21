import java.util.ArrayList;

import javax.swing.SwingUtilities;


public class Tutor {
	private Reader reader;
	private Gui gui;
	private AutomaticRulesWriter autoRulesWriter;
	private Permutations permutations;
	private ProblemSolver problemSolver;
	private Parser parser;
	private ArrayList<String> sentenceArr;
	
	public Tutor(){
		initComponents();
		solveGame();
	}
	
	private void initComponents() {
		reader = new Reader();
		gui = new Gui(reader);
		gui.createAndShowGUI();		
		gui.setVisible(true);	
	}

	private void solveGame(){
		//rules = new Rules();
		parser = new Parser(reader);
		//parser.isSimpleOrderingGame();
		//reader.checkForErrors();
		
		parser.isSimpleOrderingGame();
		parser.totalParse();
		parser.initializemParselets();
		parser.doVisibleActions();
		
	    //nodes = rules.getNodes();
	    //permutations = new Permutations(nodes);
		//problemSolver= new ProblemSolver(nodes, permutations, rules);
		//problemSolver.getSolution(nodes);
	}
	public static void main(final String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Tutor();
			}
		});
	}
	
	private ArrayList<Integer>nodes  = new ArrayList<Integer>();
}
