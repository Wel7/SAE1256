package vue.style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
/**
 * @author ahmed kafagy
 *@since 31-05-2023
 */
public class BeautyComboBoxRenderer extends DefaultListCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8070614347968282297L;

	/**
	 * modification de la couleur des labels ainsi que lorsque on passe la souris par dessus
	 *@since 23-05-2023
	 */
	public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		label.setBackground(new Color(155,155,155));
		if (isSelected) {
            label.setBackground(new Color(155,155,155));
        } else {
            label.setBackground(UIManager.getColor("ComboBox.background"));
        }
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		return label;
	}


}
