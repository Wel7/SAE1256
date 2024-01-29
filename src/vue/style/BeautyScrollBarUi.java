package vue.style;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;
/**
 * cette classe est un UI  qui change le look des scrollBar
 *
 * @since 2023-05-30
 * @author Kafagy ahmed
 */
//Se Code est inspiré de cette vidéo https://www.youtube.com/watch?v=gxhTjQMqjJY&pp=ugMICgJmchABGAHKBRJyYSB2ZW4gamF2YSBzY3JvbGw%3D 
//git du code source:https://github.com/DJ-Raven/raven-project
public class BeautyScrollBarUi extends BasicScrollBarUI{
	int x;
	int y;
	int width;
	int height;
	private final int THUMB_SIZE = 35;


	@Override
	protected JButton createDecreaseButton(int orientation) {
		JButton j = new JButton();
		j.setBackground(Color.WHITE);
		j.setPreferredSize(new Dimension(0,0));
		return j ;

	}
	@Override
	protected JButton createIncreaseButton(int orientation) {
		JButton j = new JButton();
		j.setBackground(Color.WHITE);
		j.setPreferredSize(new Dimension(0,0));
		return j ;
	}
	protected Dimension getMaximumThumbSize() {
		if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			return new Dimension(0, THUMB_SIZE);
		} else {
			return new Dimension(THUMB_SIZE, 0);
		}
	}

	@Override
	protected Dimension getMinimumThumbSize() {
		if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			return new Dimension(0, THUMB_SIZE);
		} else {
			return new Dimension(THUMB_SIZE, 0);
		}
	}

	//------------------------------------------------------------------------
	//----------------------Trainer du thumb ---------------------------------
	//------------------------------------------------------------------------
	@Override
	protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillRect(x, y, width, height);
	}

	//------------------------------------------------------------------------
	//----------------------Personalisation du Thumb--------------------------
	//------------------------------------------------------------------------
	protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		x = rctngl.x;
		y = rctngl.y;
		width = rctngl.width;
		height = rctngl.height;
		Color color = Color.WHITE;
		if(isDragging) {
		      color = Color.BLUE;
		    } else if (isThumbRollover()) {
		      color = new Color(82,84,85);
		    } else {
		      color = new Color(82,84,85);
		    }
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 10, 10);
	}


}

