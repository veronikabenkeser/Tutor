import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.sound.sampled.Line;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class DiagramArea extends JComponent implements MouseListener{
	private Line2D simpleLine;
	private Reader reader;
	//private PopupLetters popupLetters;
	private int SPACEBETWEENLINES = 25;
	private int SLOTX = 20;
	private int SLOTY= 60;
	private int YMARGIN = 60;
	private int XMARGIN = 9;
	private Polygon pol;
	private ArrayList<Polygon> polList;
	private int n;
	private PL popupLetters;
	private String negation="";
	private int lastYCoord=0;

	// Image in which we're going to draw
	//private Image image;

	// Graphics 2D object --> used to draw on
	private Graphics2D g2;
	
	/*
	 * This default dimension of DiagramArea will be 0 if we don't set a PreferredSize and it's not added to the 
	 * frame directly and is instead added to a JPanel, for example.
	 */

	public DiagramArea(Reader reader) {
		this.reader = reader;
		setPreferredSize(new Dimension(550, 300));
		setDoubleBuffered(false);
		addMouseListener(this);
	}
	

	public void updateDiagram(){
		n= reader.setParticipants().size();
		letters.clear();
		repaint();	
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSlots(g);

		if (!letters.isEmpty()){
			for(Letter l: letters){
				String str = l.getStr();
				int x = l.retX();
				int y = l.retY();
				
				g.drawString(str, x, y);
				g.drawString(l.getNegation(), x-5, y);
			}
		}
	}
	
private void drawSlots(Graphics g){
	int x = SLOTX;
	int y= SLOTY;
	String negation = "";
	polList= new ArrayList<Polygon>();
	for(int i=0; i<n; i++){
			
		//Line line = new Line2D.Double(x, y, x+SPACEBETWEENLINES, y);
		g.drawLine(x, y, x+SPACEBETWEENLINES, y);
		//g.draw(line);
		
		//g2.draw(new Line2D.Double(x, y, x+SPACEBETWEENLINES, y));
		int polygonX[] = {x-XMARGIN, x+SPACEBETWEENLINES+XMARGIN, x+SPACEBETWEENLINES+XMARGIN, x-XMARGIN};
		int polygonY[]= {y-YMARGIN, y-YMARGIN, y+YMARGIN, y+YMARGIN };
		pol = new Polygon(polygonX, polygonY, polygonX.length);
		polList.add(pol);
		
		g.drawString(Integer.toString(i+1), x+8, y+14);
		//g2.drawString(Integer.toString(i+1), x+8, y+14);
		x=x+2*SPACEBETWEENLINES;
	}
}

	public void clear() {
		letters.clear();
		lastYCoord=0;
		repaint();
	}
	
	public void changeNegation() {
		if (negation.equals("")){
			negation = "~";
		} else {
			negation ="";
		}
		repaint();	
	}
	
	public String getThisNegation(){
		return negation;
	}

	public void drawSimpleLine() {
		g2.setPaint(Color.red);
		simpleLine = new Line2D.Double(getWidth() - 80, 20, getWidth() - 60, 20);
		g2.draw(simpleLine);
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		

		for(Polygon pol: polList){
			if (pol.contains(e.getX(), e.getY())){
				System.out.println("line clicked");
				popupLetters = new PL(reader.setParticipants());
				//MouseListener popupListener = new PopupListener(popupLetters.getNameOfPopup());
				//this.addMouseListener(popupListener);
				popupLetters.showPopup(e);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		}


	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		
	}
	
	private class PL extends MouseAdapter implements ActionListener {
		JMenuItem menuItem;
		JPopupMenu popup;
		int x;
		int y;
		String l;
		String negation;
		int initYCoord=85;
		
		
		public PL(ArrayList<String> playerNames){
			popup = new JPopupMenu();
			System.out.println("just created  a new diagramArea inside of PopupLetters");
			
			for (String s: playerNames){
				menuItem = new JMenuItem(s);
				menuItem.addActionListener(this);
				popup.add(menuItem);
			}
			
			menuItem = new JMenuItem("/");
			menuItem.addActionListener(this);
			popup.add(menuItem);
		}

		public JPopupMenu getNameOfPopup(){
			return popup;
		}

		/*
		 * This method is used to determine which thing was selected from the pop-up menu.
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */


		public void actionPerformed(ActionEvent e){
			
			JMenuItem source = (JMenuItem)(e.getSource());
			System.out.println("pop-upmenu request event detected.");
			System.out.println("source is " + source);
			System.out.println(source.getText());
			
			if (y>SLOTY){
				if(lastYCoord !=0){
					y=lastYCoord+10;
					lastYCoord=y;
				} else {
					y=initYCoord;
					lastYCoord=y;
				}
			} else if (y<=SLOTY){
				y=SLOTY;
			}
	
			letters.add(new Letter(x, y, source.getText(), getThisNegation()));
			
			repaint();
		}

		public JPopupMenu getPopup(){
			return popup;
		}
		public void showPopup(MouseEvent e){
			//if (e.isPopupTrigger()){
				popup.show(e.getComponent(), e.getX(), e.getY());	
				x=e.getX();
				y=e.getY();
			//}
		}
	}
	
	private class Letter{
		int xp;
		int yp;
		String l;
		String negation;
		
		public Letter(int x, int y, String l, String negation){
			this.xp=x;
			this.yp=y;
			this.l=l;
			this.negation = negation;
			
		}

		public int retX() {
			return xp;
		}
		
		public int retY() {
			return yp;
		}

		public String getStr() {
			return l;
		}
		
		public String getNegation(){
			return negation;
		}
	}

	private ArrayList<Letter> letters = new ArrayList<Letter>();

}
