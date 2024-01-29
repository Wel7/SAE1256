
package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import modeles.ModeleMembre;
import java.awt.Font;


/**
 * cette classe est la vue des information d'un membre
 *
 * @since 2023-05-31
 * @author Kafagy ahmed
 */
public class VueMembre extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8168083069223066198L;
	private modeles.ModeleMembre modele;
	private JLabel name;
	private JLabel firstName;
	private JLabel pseudo;
	private JLabel email;
	private JLabel perm;

	public VueMembre(modeles.ModeleMembre modele) {
		this.modele = modele;

		// ---------------------------------------------------------------
		// --------------------Déclaration des panel ---------------------
		// ---------------------------------------------------------------
		JPanel container = new JPanel();
		JPanel containerTop = new JPanel();
		JPanel containerCenter = new JPanel();
		container.setSize(1000, 1000);
		// --------------------------------------------------------------
		// -----------------------Mise en place des Layout --------------
		// --------------------------------------------------------------
		container.setLayout(new BorderLayout());
		containerTop.setLayout(new GridLayout(1, 2));
		containerCenter.setLayout(new GridLayout(3, 1));
		container.setSize(1000, 1000);

		// ---------------------------------------------------------------
		// ------------------------Déclaration des Label -----------------
		// ---------------------------------------------------------------
		name = new JLabel(modele.getNom() + " ", JLabel.RIGHT);
		firstName = new JLabel(modele.getPrenom(), JLabel.RIGHT);
		pseudo = new JLabel("Pseudo: " + modele.getPseudo(), JLabel.RIGHT);
		email = new JLabel("Email: " + modele.getEmail(), JLabel.RIGHT);
		perm = new JLabel("Permission: " + modele.getPerm(), JLabel.RIGHT);

		// ---------------------------------------------------------------
		// ---------------------changement de font JLABEL ----------------
		// ---------------------------------------------------------------
		name.setFont(new Font("Serif", Font.BOLD, 24));
		firstName.setFont(new Font("Serif", Font.BOLD, 24));

		// ---------------------------------------------------------------
		// ----------------------ajout des JLABEL aux panel --------------
		// ---------------------------------------------------------------
		containerTop.add(name);
		containerTop.add(firstName);
		containerCenter.add(pseudo);
		containerCenter.add(email);
		containerCenter.add(perm);

		// ---------------------------------------------------------------
		// ----------------------ajout des panel aux conteneur------------
		// ---------------------------------------------------------------
		container.add(containerTop, BorderLayout.NORTH);
		container.add(containerCenter, BorderLayout.CENTER);
		add(container);

	}

	public void rafraichir(ModeleMembre modeleMembre) {
		modele = modeleMembre;
		this.setVisible(false);
		perm.setText("Permission: " + modele.getPerm());
		this.setVisible(true);
	}

}
