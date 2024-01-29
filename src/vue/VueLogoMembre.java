package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlleur.ControllerInfoMembre;

public class VueLogoMembre extends Vue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7717425397589991495L;
	private ImageIcon icone = new ImageIcon(getClass().getResource("res/images/userImage.png"));
	private JLabel icon;
	private JLabel pseudo;
	JPanel container;
	
	/**
	 * @author Ahmed Kafagy
	 * @param membre
	 */
	public VueLogoMembre(modeles.ModeleMembre membre){
		ControllerInfoMembre controller = new ControllerInfoMembre(this,membre);
		
		
		icon = new JLabel(icone , JLabel.CENTER);
		pseudo = new JLabel(membre.getPseudo(), JLabel.CENTER);
		pseudo.setFont(getFont());
		this.setBackground(COULEUR_FOND);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
		setLayout(new BorderLayout());
		add(icon,BorderLayout.CENTER);
		add(pseudo , BorderLayout.NORTH); 
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if(e.getButton() == MouseEvent.BUTTON1) {
					controller.clickGauche();
				}
			}
		});
	}

}
