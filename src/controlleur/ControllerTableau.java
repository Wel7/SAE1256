package controlleur;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modeles.ModeleTableau;
import vue.VueTableau;
import vue.style.ButtonBeauty;
import vue.style.StyleGeneral;

/**
 * Cette classe représente le contrôleur qui lie VueTableau et ModeleTableau.
 *
 * @since 2023-05-19
 * @author Hugo Hergat
 */
public class ControllerTableau extends Controlleur implements StyleGeneral {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 700026826096504071L;

	/**
	 * String ajouter
	 */
	public static final String ACTION_AJOUTER = "Ajouter";

	/**
	 * Modele du tableau
	 */
	private ModeleTableau modele;
	/**
	 * Modele de la vue
	 */
	private VueTableau vue;

	/**
	 * Bouton qui permet d'ajouter une liste
	 */
	private ButtonBeauty ajouterListe;

	/**
	 * Constructeur du contrôleur ControllerTableau.
	 *
	 * @param vueTableau    La vue associée au tableau.
	 * @param modeleTableau Le modèle du tableau.
	 */
	public ControllerTableau(VueTableau vueTableau, ModeleTableau modeleTableau) {
		this.vue = vueTableau;
		this.modele = modeleTableau;

		// Si le tableau est archivé, on ne fait rien
		if (modeleTableau.isArchiver()) {
			return;
		}
		ajouterListe = new ButtonBeauty("Ajouter liste");
		ajouterListe.setActionCommand(ACTION_AJOUTER);
		ajouterListe.addActionListener(this);
		add(ajouterListe);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ACTION_AJOUTER)) {
			String nomListe = JOptionPane.showInputDialog(new JFrame(), "Entrer le nom de la liste", "Créer une liste",
					JOptionPane.QUESTION_MESSAGE);
			if (nomListe == null) {
				return;
			}
			if (nomListe.isBlank()) {
				JOptionPane.showMessageDialog(new JFrame(), "Vous ne pouvez pas créer une liste qui n'a pas de nom !",
						"Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for(String nom: modele.getNomsListes()) {
				if(nom.equals(nomListe)) {
					JOptionPane.showMessageDialog(new JFrame(), "Une liste à déjà ce nom !",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			modele.addListe(nomListe);
			vue.rafraichir(modele);
		}
	}

	@Override
	public int getBaseline(int width, int height) {
		return 0;
	}
}
