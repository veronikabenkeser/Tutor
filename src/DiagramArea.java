import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import javax.swing.JComponent;


public class DiagramArea extends JComponent {
	private Line2D simpleLine;
	
	private PopupLetters popupLetters;
	
	// Image in which we're going to draw
	private Image image;

	// Graphics 2D object --> used to draw on
	private Graphics2D g2;

	/*
	 * This default dimension of DiagramArea will be 0 if we don't set a PreferredSize and it's not added to the 
	 * frame directly and is instead added to a JPanel, for example.
	 */
	public DiagramArea() {
		setPreferredSize(new Dimension(550, 300));
		setDoubleBuffered(false);

		/*
		 * We're creating a popupLetters object because we will be using it in the next line, and
		 * if we don't create it using new PopupLetters(), then we'll get a
		 * NullPoint Exception.
		 */
		popupLetters = new PopupLetters();
		
		/*
		 * We're creating a listener that will show the menu/choices of letters once it's right-clicked
		 * and adding it to the diagramArea so that the pop-up menu would come up.
		 * 
		 * We need popupLetters.getNameOfPopup() as a param because PopupListener(JPopupMenu popup) uses 
		 * popup inside the showPopup(MouseEvent e) method. If we don't create popupLetters ( new PopupLetters()), we're 
		 * going to get a NullPoint Exception because the value of popup will be null , meaning the object was
		 * never created.
		 * To test for NullPoint exception: if (obj != null).
		 */
		MouseListener popupListener = new PopupListener(popupLetters.getNameOfPopup());
		this.addMouseListener(popupListener);
	}

	protected void paintComponent(Graphics g) {
		if (image == null) {
			// image to draw null ==> we create
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			// enable antialiasing
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}

		g.drawImage(image, 0, 0, null);
	}

	public void clear() {
		g2.clearRect(0, 0, getWidth(), getHeight());
		// g2.setPaint(Color.white);
		// draw white on entire draw area to clear
		// g2.fillRect(0, 0, getSize().width, getSize().height);
		// g2.setPaint(Color.black);
		repaint();
	}

	public void drawLetter() {
		g2.setPaint(Color.red);
		System.out.println("Drawing a letter now.");
		g2.setColor(Color.RED);
	}

	public void drawDownSlopedLine() {
		g2.setPaint(Color.red);
		g2.drawLine(getWidth() - 60, 30, getWidth() - 80, 20);
		repaint(); 
	}

	/*
	 * public void drawUpSlopedLine(){ g2.setPaint(Color.red); upSlopedLine =
	 * new Line2D.Double(getWidth()-80, 30, getWidth()-60, 20); repaint(); }
	 */
	public void drawSimpleLine() {
		g2.setPaint(Color.red);
		simpleLine = new Line2D.Double(getWidth() - 80, 20, getWidth() - 60, 20);
		g2.draw(simpleLine);
		// g2.draw(new Line2D.Double(getWidth()-80, 20, getWidth()-60, 20));
		repaint();
	}
	
	public void pickUpAndReDraw() {
	}
}