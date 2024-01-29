package vue.style;

import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
/**
 * @author ahmed kafagy
 *@since 31-05-2023
 */
public class BeautyComboBoxUI extends BasicComboBoxUI {
	
	/**
	 * modification du bouton de base
	 *@since 23-05-2023
	 */
	@Override
	protected JButton createArrowButton() {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(getClass().getResource("res/images/downArrow.png")));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return button;
	}
 
	/**
	 * modification de la scrollbar du popup
	 *@since 23-05-2023
	 */
    protected ComboPopup createPopup() {
    	 BasicComboPopup popup = (BasicComboPopup) super.createPopup();
         JScrollPane scroller = (JScrollPane) popup.getComponent(0);
         scroller.setBorder(BorderFactory.createEmptyBorder());
         scroller.getVerticalScrollBar().setUI(new BeautyScrollBarUi());
         return popup;
    }
    

}
