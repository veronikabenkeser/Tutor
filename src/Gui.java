import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
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

public class Gui {
	final JFrame frame = new JFrame("COACH");
	private String a;
	JTextArea p = new JTextArea();
	JTextArea q = new JTextArea();
	// private GUIControl control;
	private DiagramArea diagramArea = new DiagramArea();
	private Reader reader;

	public Gui(Reader reader) {
		this.reader = reader;
		a = "P1";
		loadNewGame(a);
	}

	private void setCurrentGame(String a) {
		reader.setFile(a);
	}
	
	public String setSelectedG(){
		return a;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("PrepTest");
		JMenu menu2 = new JMenu("Timer");
		JMenu menu3 = new JMenu("Hints");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);

		JRadioButtonMenuItem P1 = new JRadioButtonMenuItem();
		JRadioButtonMenuItem P2 = new JRadioButtonMenuItem();
		
		P1.addActionListener(fileSelectionListener);
		P2.addActionListener(fileSelectionListener);
		P1.setText("P1");
		P2.setText("P2");
		menu1.add(P1);
		menu1.add(P2);
		
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

	ActionListener fileSelectionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JRadioButtonMenuItem source = (JRadioButtonMenuItem)e.getSource();
			String a = source.getText();
			loadNewGame(a);
		}
	};
	
	private void loadNewGame(String a) {
		setCurrentGame(a);
		reader.readProblem(a);
		String newline = "\n";
		p.setText(reader.showPrompt());
		//*** q.setText(reader.showQuestion());
	}

	private Container createContentPane() {
		JPanel contentPane = new JPanel(new GridBagLayout()) {
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

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		contentPane.add(loadPrompt(), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		contentPane.add(createPanel1(), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.gridheight = 2;

		contentPane.add(loadQuestion(), c);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setBackground(Color.BLUE);
		contentPane.setOpaque(false);

		return contentPane;
	}

	private JTextArea loadQuestion() {
		q.setEditable(false);
		q.setLineWrap(true);
		q.setWrapStyleWord(true);
		return q;
	}

	private JTextArea loadPrompt() {
		p.setEditable(false);
		p.setLineWrap(true);
		p.setWrapStyleWord(true);
		return p;
	}

	/*
	 * Create new panel with 2 rows and 1 column. The vertical space between
	 * components is 0. The horizontal space between components is 16.
	 */
	
	/*
	 * 
	 * private JPanel createPanel2(){ JPanel panel2 = new JPanel(new
	 * GridLayout(2, 1, 0, 16)); GridBagConstraints gbc = new
	 * GridBagConstraints();
	 * 
	 * panel2.setOpaque(false); gbc.gridx = 0; gbc.gridy = 0;
	 * panel2.add(loadPrompt(), gbc);
	 * 
	 * gbc.gridx = 0; gbc.gridy = 1; panel2.add(createPanel1(), gbc);
	 * 
	 * return panel2; }
	 
*/
	
	private JPanel createPanel1() {

		JPanel panel1 = new JPanel(new GridBagLayout());
		panel1.setPreferredSize(new Dimension(700, 300));
		// panel1.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		String title = "Master Diagram";
		diagramArea.setBorder(BorderFactory.createTitledBorder(title));

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel1.add(diagramArea, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		panel1.add(createControls());

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
		controls.setPreferredSize(new Dimension(150, 300));
		controls.setLayout(new GridBagLayout());

		/*
		 * I want the gaps between the buttons to be: 0 on the left; 5 on the
		 * right; 5 on the bottom; 5 on the top;
		 */
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);

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

	public void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Gui t = new Gui();
		frame.setJMenuBar(this.createMenuBar());
		frame.setContentPane(this.createContentPane());
		// frame.setSize(new Dimension(1200,800));
		frame.setPreferredSize(new Dimension(1100, 700));
		frame.pack();
		frame.setLocationByPlatform(true);

		// TO DO: maybe a component listener instead of setMinimumSize for
		// greater reliability.
		// frame.setMinimumSize(new Dimension(400, 400));
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	void setVisible(boolean visible){
		frame.setVisible(true);
	}

	/*
	 * TO DO : private void setGUIControl(GUIControl guiControl){ this.control =
	 * control; }
	 */

	/*
	 * We will be implementing the invokeLater method because all interactions
	 * in Swing must happen on the Event Dispatch Thread. If we're not currently
	 * in the Event Dispatch Thread (the actionPerformed methods are always
	 * called by the EDT) and we want to update the GUI, we have to schedule an
	 * update that will be performed by the EDT. info
	 * at:http://stackoverflow.com/questions/7196889/swingutilities-invokelater
	 */
}