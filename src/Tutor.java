import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
	
public class Tutor {
	private String a;
	JTextArea p = new JTextArea();
	//private GUIControl control;
	private DiagramArea diagramArea = new DiagramArea();
	private Reader reader = new Reader();
	
	public Tutor(){
		a = "P1";
		setCurrentGame(a);
		reader.readProblem(a);
		p.setText(reader.showPrompt());
	}
	
	private void setCurrentGame(String a){
		reader.setFile(a);
	}
	
	private JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("PrepTest");
		JMenu menu2 = new JMenu("Timer");
		JMenu menu3 = new JMenu("Hints");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
	
		JRadioButtonMenuItem i1 = new JRadioButtonMenuItem("P1");
		JRadioButtonMenuItem i2 = new JRadioButtonMenuItem("Game #2");
	
		
		
		menu1.add(i1);
		menu1.add(i2);
		makeOnOffButtons(menu2);
		makeOnOffButtons(menu3);
		
		return menuBar;
	}
	
	private void makeOnOffButtons(JMenu menu) {
		JRadioButtonMenuItem i3 = new JRadioButtonMenuItem("On");
		JRadioButtonMenuItem i4 = new JRadioButtonMenuItem("Off");
		menu.add(i3);
		menu.add(i4);
	}
	private Container createContentPane(){
		JPanel contentPane = new JPanel(new BorderLayout(16, 16)) {
			@Override
			protected void paintComponent(Graphics graphics) {
				Graphics2D g2d = (Graphics2D) graphics;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
	
				GradientPaint gp = new GradientPaint(0, 0, getBackground()
						.brighter().brighter(), 0, getHeight(), getBackground()
						.darker().darker());
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
	
				super.paintComponent(graphics);
			}
		};
		
		contentPane.add(createPanel2(), BorderLayout.CENTER);
		contentPane.add(loadQuestion(), BorderLayout.EAST);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setBackground(Color.BLUE);
		contentPane.setOpaque(false);
		
		return contentPane;
	}
	
	private JTextArea loadQuestion(){
		JTextArea q = new JTextArea("Question 1 goes here....", 30, 30);
		//q.setLineWrap(true);
		//q.setWrapStyleWord(true);
		return q;
	}
	
	private JTextArea loadPrompt(){
		//JTextArea wordProblem = new JTextArea("Word problem goes here....", 100,
			//100);
		p.setEditable(false);
		p.setLineWrap(true);
		p.setWrapStyleWord(true);
		return p;
	}
	
	/*
	 * Create new panel with 2 rows and 1 column. The
	 * vertical space between components is 0. The horizontal space between
	 * components is 16.
	 */
	private JPanel createPanel2(){
		JPanel panel2 = new JPanel(new GridLayout(2, 1, 0, 16));
		GridBagConstraints gbc = new GridBagConstraints();
	
		panel2.setOpaque(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel2.add(loadPrompt(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel2.add(createPanel1(), gbc);
		
		return panel2;
	}
	
	private JPanel createPanel1(){
		
		JPanel panel1 = new JPanel(new BorderLayout(0, 0));
		panel1.setOpaque(false);
		panel1.add(diagramArea, BorderLayout.CENTER);
		panel1.add(createControls(), BorderLayout.EAST);
		
		return panel1;
	}

	private JPanel createControls() {
		JButton checkBtn = new JButton("Check Diagram");
		JButton submitBtn = new JButton("Submit");
		JButton letterBtn = new JButton("Letter");
		// letterBtn.addActionListener(actionListener);
		JButton clearAllBtn = new JButton("Clear All");
		// clearAllBtn.addActionListener(actionListener);
		JButton lineBtn = new JButton("-");
		// lineBtn.addActionListener(actionListener);
		JButton downSlopedLineBtn = new JButton("/");
		// downSlopedLineBtn.addActionListener(actionListener);
		JButton upSlopedLineBtn = new JButton("/");
		// upSlopedLineBtn.addActionListener(actionListener);

		JPanel controls = new JPanel();
		controls.setLayout(new GridBagLayout());

		/*
		 * I want the gaps between the buttons to be: 0 on the left; 5 on the
		 * right; 5 on the bottom; 5 on the top;
		 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 5);

		// the way the component should be glued to the container
		// gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// add the buttons to the controls panel
		gbc.gridx = 0;
		gbc.gridy = 0;

		controls.add(letterBtn, gbc);
		// controls.add(Box.createRigidArea(new Dimension (0,5)));

		gbc.gridx = 0;
		gbc.gridy = 1;

		controls.add(downSlopedLineBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;

		controls.add(upSlopedLineBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;

		controls.add(clearAllBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		controls.add(lineBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		controls.add(checkBtn, gbc);

		return controls;
	}
	
	
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("COACH");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Tutor t = new Tutor();
		frame.setJMenuBar(t.createMenuBar());
		frame.setContentPane(t.createContentPane());

		frame.setLocationByPlatform(true);
		frame.setPreferredSize(new Dimension(600, 600));
		//TO DO: maybe a component listener instead of setMinimumSize for greater reliability.
		frame.setMinimumSize(new Dimension(400, 400));
		// frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setVisible(true);
	}
	
	/*
	 * TO DO :
	private void setGUIControl(GUIControl guiControl){
		this.control = control;
	}
	*/
	
	/*
	 * We will be implementing the invokeLater method because all interactions
	 * in Swing must happen on the Event Dispatch Thread. If we're not currently
	 * in the Event Dispatch Thread (the actionPerformed methods are always
	 * called by the EDT) and we want to update the GUI, we have to schedule an
	 * update that will be performed by the EDT. info
	 * at:http://stackoverflow.com/questions/7196889/swingutilities-invokelater
	 */
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() { // method of the anonymous class that
								// implements the Runnable interface above
				createAndShowGUI();
			}
		});
	}
}
	