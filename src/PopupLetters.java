import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/*
 * This class provides a PopupLetters constructor and implements an Action Listener.
 * This class creates the menu objects and then listens to the menu objects to see which value the user has selected
 * from the pop-up box.
 */

public class PopupLetters implements ActionListener{
	private JMenuItem menuItem;
	private JPopupMenu popup;
	
	public PopupLetters(){
		popup = new JPopupMenu();
		menuItem = new JMenuItem("A");
		menuItem.addActionListener(this);
		popup.add(menuItem);
		
		menuItem = new JMenuItem("B");
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
	}
}