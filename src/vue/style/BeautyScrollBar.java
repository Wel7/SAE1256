package vue.style;
import java.awt.Color;
//http://www.java2s.com/Tutorials/Java/Swing_How_to/JScrollPane/Create_custom_JScrollBar_for_JScrollPane.htm
import java.awt.Dimension;
import javax.swing.JScrollBar;


/**
 * @author Ahmed Kafagy
 * 
 * Scroll bar personnalisé
 *
 */
public class BeautyScrollBar extends JScrollBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6099090637417099679L;

	/**
	 * @param orientation
	 * @param value
	 * @param extent
	 * @param min
	 * @param max
	 */
	public BeautyScrollBar(int orientation , int value ,int extent ,int min , int max  ) {
		super(orientation, value, extent, min, max);
		this.setUI(new BeautyScrollBarUi());
		setBackground(Color.white);
		setPreferredSize(new Dimension(9, 9));
	}
}
