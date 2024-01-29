package vue;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import controlleur.ControlleurTableauManager;
import modeles.ModeleApplication;
import modeles.ModeleTableau;

/**
 * Cette classe représente ce que l'utilisateur voit pour la sélection d'un
 * tableau.
 *
 * @since 2023-05-18
 * @author Hugo Hergat
 */
public class VueTableauManager extends Vue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5138253432364711326L;
	private ModeleApplication modele;
	private VueApplication vue;

	/**
	 * Constructeur de la vue de gestion des tableaux.
	 *
	 * @param modeleApplication Le modèle de l'application.
	 * @param vueApplication    La vue de l'application afin d'établir un lien pour
	 *                          rafraîchir le tableau lors d'un changement.
	 */
	public VueTableauManager(ModeleApplication modeleApplication, VueApplication vueApplication) {
		this.modele = modeleApplication;
		this.vue = vueApplication;

		setPreferredSize(new Dimension(LONGUEUR / 6, HAUTEUR));
		setBackground(COULEUR_FOND);

		FlowLayout layout = new FlowLayout();
		layout.setVgap(HAUTEUR / 12);
		setLayout(layout);

		JLabel titre = new JLabel("Trello Lite +");
		titre.setFont(new java.awt.Font(FONT_TITRE, GRAS, TEXTE_TITRE));

		ControlleurTableauManager controlleur = new ControlleurTableauManager(this, modele);

		add(titre);
		add(controlleur);
	}

	/**
	 * Rafraîchit les vues pour afficher un nouveau tableau.
	 *
	 * @param modeleTableau Le modèle du nouveau tableau à afficher.
	 */
	public void rafraichir(ModeleTableau modeleTableau) {
		vue.rafraichir(modeleTableau);
	}

	public void rafraichir(String texte) {
		vue.rafraichir(texte);
	}
	
	public VueTableauInfo getVueTabInfo() {
		return vue.getVueTabInfo();
	}
}
