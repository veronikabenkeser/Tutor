import java.util.ArrayList;

import javax.swing.SwingUtilities;


public class Tutor {
	private Reader reader;
	private Gui gui;
	private AutomaticRulesWriter autoRulesWriter;
	private Permutations permutations;
	//private ProblemSolver problemSolver;
	private Parser parser;
	private ArrayList<String> sentenceArr;
	
	public Tutor(){
		initComponents();
	}
	
	private void initComponents() {
		reader = new Reader();
		gui = new Gui(reader);
		gui.createAndShowGUI();		
		gui.setVisible(true);	
	}

	public static void main(final String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Tutor();
			}
		});
	}	
}
