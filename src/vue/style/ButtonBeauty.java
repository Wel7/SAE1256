/**
 * Ahmed Kafagy
 * 
 *cette classe est une extension personaliser de la classe j button
 * @since 2023-05-29
 */
package vue.style;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ButtonBeauty extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5826413203016113852L;
	//------------------------------------------
	//------Déclaration des attribut de classe--
	//------------------------------------------
	private int width;
	private int height;
	private float alpha = 0.3f ;
	private final Timer timer ;
	private boolean hover;
	Color color = Color.decode("#434343");
	int heightController;

	//----------------------------------------------------
	//----------------Constructeur de classe--------------
	//----------------------------------------------------
	public ButtonBeauty(String text) {
		super(text);
		setContentAreaFilled(false);//retire le remplissage en bleu des button;
		setForeground(Color.white);//changement de la couleur du nom du button
		setCursor(new Cursor(Cursor.HAND_CURSOR));//changement du curseur en main lors du passage sur le button
		setBorder(new EmptyBorder(10,20,10,20));
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setFocusPainted(false); //désactivation de la bordure du text
		addMouseListener(new MouseAdapter() {
			@Override

			public void mouseEntered(MouseEvent e) {
				hover=true;
				timer.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hover=false;
				timer.start();
			}
		});
		//---------------------------
		//----Animation Hover -------
		//---------------------------
		timer = new Timer(40, new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				if (hover) {//<- si la souris est dessus incrémentation est que alpha est est inférieur a 0.6 incrémentation
					if (alpha < 0.6f) {
						alpha+=0.5f;
						timer.stop();
						repaint();
					}
					else {
						alpha = 0.6f;
						timer.stop();
						repaint();
					}
				}else {//<- si la souris quitte rénitialisation de alpha a sa valeur initial
					if(alpha>0.3f) {
						alpha = 0.5f;
						timer.stop();
						repaint();
					}else {
						alpha=0.3f;
						timer.stop();
						repaint();
					}
				}
			}
		});


	}

	@Override
	/**
	 * Ahmed Kafagy
	 * 
	 *cette méthode overide permet de changer le designe du jbouton
	 * @since 2023-05-29
	 */
	protected void paintComponent(Graphics g) {
		width = this.getWidth();
		height = this.getHeight();
		BufferedImage picture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);//creation d'une image prenant les dimension du boutton
		Graphics2D fond=picture.createGraphics(); // creation d'un graphic 2d
		fond.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//changement du rendu pour réduire l'aliasing du graphics
		fond.setPaint(color);
		fond.fillRoundRect(0, 0, width, height, height, height);
		createStyle(fond);
		g.drawImage(picture, 0, 0, null);
		super.paintComponent(g);//appelle de la méthode de la classe mere 
	}
	/**
	 * Ahmed Kafagy
	 * Cette classe ajoute sur un graphic2d un effet de lumiedre courbé
	 * @since 2023-05-29
	 */
	private void createStyle(Graphics2D fond) {
		fond.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		width = this.getWidth();
		height = this.getHeight();
		GradientPaint g= new GradientPaint(0, 0, Color.WHITE, 0, height, new Color(255, 255, 255 ,80));
		fond.setPaint(g);
		Path2D.Float f = new Path2D.Float();
		f.moveTo(0, 0);
		heightController = height+height/2;
		f.curveTo(0, 0, width/2, heightController, width, 0);
		fond.fill(f);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(300 , 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ButtonBeauty aa = new ButtonBeauty("aadadada");
		aa.setSize(100,100);
		JPanel aaa = new JPanel();
		aaa.add(aa);

		frame.add(aaa);
		frame.setVisible(true);

	}
}

