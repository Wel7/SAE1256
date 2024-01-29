package controlleur;

import java.awt.Dimension;
import java.awt.GridLayout;
/**

Cette classe représente ce que l'utilisateur voit lorsque il ajoute un membre au tableau .*
@since 2023-05-25
@author Corciu Christian
 */
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.ModeleMembre;
import modeles.ModeleTableau;
import vue.VueTableauInfo;
import vue.style.BetterButton;
import vue.style.StyleGeneral;

/**
 * @author Christian Corciu
 *
 */
public class ControllerMembre extends Controlleur implements StyleGeneral {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3080244092073454291L;
	//Vue et modele
	/**
	 * Vue des infos du tableau
	 */
	private VueTableauInfo vue;
	/**
	 * Modele du tableau
	 */
	private ModeleTableau modele;
	/**
	 * Panel du popup
	 */
	private JPanel popupMembre;
	/**
	 * Champ de texte pour le nom.
	 */
	private JTextField name;

	/**
	 * Champ de texte pour le prénom.
	 */
	private JTextField firstname;

	/**
	 * Champ de texte pour le pseudo.
	 */
	private JTextField pseudo;

	/**
	 * Champ de texte pour l'adresse e-mail.
	 */
	private JTextField email;

	/**
	 * Bouton d'ajout de membre.
	 */
	private BetterButton ajouterMembre;
	
	/**
	 * ActionCommand du bouton bouton d'ajout de membre.
	 */
	public static final String ACTION_AJOUTER = "Ajouter";
	// Regex fait avec l'aide de https://www.baeldung.com/java-email-validation-regex
	/**
	 * Regex d'email
	 */
	public static final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * @author Christian Corciu
	 * @param VueTableauInfo
	 * @param ModeleTableau
	 */
	public ControllerMembre(VueTableauInfo VueTableauInfo, ModeleTableau ModeleTableau) {
		vue = VueTableauInfo;
		modele = ModeleTableau;
		if(modele.isArchiver()) {
			return;
		}

		this.setBackground(vue.getBackground());
		// ---------------------------------------------------//
		// Creation et affectation du panel
		popupMembre = new JPanel();
		popupMembre.setPreferredSize(new Dimension(200, 100));
		popupMembre.setLayout(new GridLayout(5,2));
		// --------------------------------------------------//
		// ---------------------------------------------------//

		// Preparation des input de nom
		name = new JTextField("");
		name.setPreferredSize(new Dimension(150, 20));
		// name.setFont(new java.awt.Font(FONT_TITRE, NORMAL, TEXTE_TITRE));
		// ----------------------------------------------------//
		// ----------------------------------------------------//

		// Preparation des input de prenom
		// --------------------------------------------------------------//
		firstname = new JTextField("");
		firstname.setPreferredSize(new Dimension(150, 20));
		// firstname.setFont(new java.awt.Font(FONT_TITRE, NORMAL, TEXTE_TITRE));
		// ----------------------------------------------------//
		// ------------------------------------------------------//

		// Preparation des input de pseudo
		// ------------------------------------------//
		pseudo = new JTextField("");
		pseudo.setPreferredSize(new Dimension(150, 20));
		// pseudo.setFont(new java.awt.Font(FONT_TITRE, NORMAL, TEXTE_TITRE));

		// ---------------------------------------------//
		// Preparation des input de email//
		// ---------------------------------------------//
		email = new JTextField("");
		email.setPreferredSize(new Dimension(150, 20));
		// email.setFont(new java.awt.Font(FONT_TITRE, NORMAL, TEXTE_TITRE));
		ajouterMembre = new BetterButton("Ajouter membre");
		ajouterMembre.setActionCommand(ACTION_AJOUTER);
		ajouterMembre.addActionListener(this);
		add(ajouterMembre);
		// -------------------------------------------//

		// -----------------------------------------------//
		// Ajout dans le panel//
		// ------------------------------------------//
		popupMembre.add(new JLabel("Nom du membre :"));
		popupMembre.add(name);
		popupMembre.add(new JLabel("Prenom du membre :"));
		popupMembre.add(firstname);
		popupMembre.add(new JLabel("Email du membre :"));
		popupMembre.add(email);
		popupMembre.add(new JLabel("Pseudo du membre :"));
		popupMembre.add(pseudo);
		// -----------------------------------------------//

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ACTION_AJOUTER)) {
			// On affiche un pop-up qui contient de quoi remplir toutes les infos
			// nécessaires à la création d'une carte
			int option = JOptionPane.showOptionDialog(null, popupMembre, "Ajouter un membre",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (option == JOptionPane.OK_OPTION) {
				try {
					// Vérification du nom//
					if (name.getText().isBlank()) {
						throw new IllegalArgumentException("Le membre n'a pas de nom !");
					} // Vérification du prenom//
					else if (firstname.getText().isBlank()) {
						throw new IllegalArgumentException("Le membre n'a pas de prenom !");
						// Vérification de l'email//
					} else if (email.getText().isBlank()) {
						throw new IllegalArgumentException("Le membre n'a pas d'email !");
					} // Vérification du pseudo//
					else if (pseudo.getText().isBlank()) {
						throw new IllegalArgumentException("Le membre n'a pas de pseudo !");
					}
					else if(!email.getText().matches(regexEmail)) {
						throw new IllegalArgumentException("Le membre n'a pas un email valide !");
					}
					// -----------------------------------------//
					// Ajout du membre au tableau//
					ModeleMembre newMember = new ModeleMembre(name.getText(), firstname.getText(), email.getText(),
							pseudo.getText());
					
					modele.addMembre(newMember);
					vue.rafraichir(modele);
					name.setText("");
					pseudo.setText("");
					email.setText("");
					firstname.setText("");
					// ------------------------------------------//
					// -----------------------------------------//
				} catch (IllegalArgumentException verifInput) {
					JOptionPane.showMessageDialog(null, verifInput.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					return;

				}
			}

		}
	}
}
