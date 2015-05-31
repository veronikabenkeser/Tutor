import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/*
 * This class listens for right clicks via MouseAdapter.
 * The class specifies what happens once it hears a right click: a pop-up menu appears.
 * The pop-up menu itself then listens to which item in the menu the user selects, but that is outlines in the PopupLetter
 * class.
 * 
 * I'm using MouseAdapter as a convenience for creating listener objects. This abstract class defines null methods for 
 * MousePressed/released/dragged, etc; the only methods that have to be defined are methods that belong
 * to events we care about.
 */
/*
public class PopupListener extends MouseAdapter{
	private JPopupMenu popup;
	
	PopupListener(JPopupMenu popupMenu){
		popup = popupMenu;	
		
	}
	
	public void mousePressed(MouseEvent e){
//		showPopup(e);
		if (e.isPopupTrigger()){
			showPopup(e);
		}
	}

	
	public void mouseReleased(MouseEvent e){
		showPopup(e);
	}
	
	/*
	 * This method shows the pop-up menu.The pop-up menu has its own properties that are 
	 * outlined in the PopupLetters class.
	 */

/*
	private void showPopup(MouseEvent e){
		if (e.isPopupTrigger()){
			popup.show(e.getComponent(), e.getX(), e.getY());	
			
		}
	}
}
*/