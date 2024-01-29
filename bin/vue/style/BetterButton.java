package vue.style;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Cette classe a été très lourdement inspiré par le youtuber "Ra Ven" avec la vidéo: https://www.youtube.com/watch?v=FEb2Pt9ymes et le git: https://github.com/DJ-Raven/java-swing-suffix-predix-con
/**
 * @author Noé Chrétien
 *
 */
public class BetterButton extends JButton implements StyleGeneral{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1903352397997338788L;
	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 0;
	
	/**
	 * @param txt texte du bouton
	 */
	public BetterButton(String txt) {

		super(txt);
		color = BUTTON_COLOR;
		setBackground(color);
		colorOver = BUTTON_HOVER;
		colorClick = new Color(152, 184, 144);
		borderColor = BUTTON_BORDER_COLOR ;
		

		
		//  Add event mouse
		addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent me) {
				setBackground(colorOver);
				over = true;
			}


			public void mouseExited(MouseEvent me) {
				setBackground(color);
				over = false;

			}

			public void mousePressed(MouseEvent me) {
				setBackground(colorClick);
			}

			public void mouseReleased(MouseEvent me) {
				if (over) {
					setBackground(colorOver);
				} else {
					setBackground(color);
				}
			}
		});
	}

	public Boolean isOver() {
		return over;
	}
	
	//Création des getters et setters
	public void setOver(boolean over) {
		this.over = over;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	public Color getColorOver() {
		return colorOver;
	}

	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}

	public Color getColorClick() {
		return colorClick;
	}

	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}


	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//  Paint Border
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		g2.setColor(getBackground());
		//  Border set 2 Pix
		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
		super.paintComponent(grphcs);
	}
}