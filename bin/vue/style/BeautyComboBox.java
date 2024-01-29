package vue.style;

import javax.swing.JComboBox;

/**
 * @author ahmed kafagy
 *@since 31-05-2023
 */
public class BeautyComboBox <E> extends JComboBox<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5490876927829010606L;

	/**
	 * constructeur de beautyComboBox
	 *@since 31-05-2023
	 *@param E[] items listes de items que l'on veut dans la combobox
	 */
public BeautyComboBox(E[] items) {
	super(items);
	
	setUI(new BeautyComboBoxUI());
	setRenderer(new BeautyComboBoxRenderer());
	setAlignmentX(CENTER_ALIGNMENT);
	setAlignmentY(CENTER_ALIGNMENT);
	
}

}
