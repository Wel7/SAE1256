package vue;

import modeles.ModeleCarte;
import modeles.ModeleListe;
import javax.swing.*;

import controlleur.ControllerListe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;

/**
 * Cette classe représente la vue d'une liste. Elle affiche les cartes associées
 * à la liste.
 *
 * @since 2023-05-18
 * 
 * @author Noe Chretien, Hugo Hergat
 */
//Noe a fait la majorité, Hugo a paufiné pour certaine partie de l'affichage

public class VueListe extends Vue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2316119461494501539L;
	private int hauteur = VueCarte.taille / 2;
	private JLabel titre;
	private JPanel cartes;
	private ModeleListe modele;

	/**
	 * Constructeur de la vue d'une liste.
	 *
	 * @param modeleListe Le modèle de liste associé à la vue.
	 */
	public VueListe(ModeleListe modeleListe) {
		modele = modeleListe;
		titre = new JLabel(modele.getNom(), SwingConstants.CENTER);
		cartes = new JPanel();
		setPreferredSize(new Dimension(LONGUEUR / 6, hauteur));
		setLayout(new BorderLayout());
		cartes.setLayout(new FlowLayout());
		add(titre, BorderLayout.NORTH);
		setBorder(BorderFactory.createLineBorder(Color.black));
		afficherCartes();
		add(cartes, BorderLayout.CENTER);
	}



	/**
	 * Rafraîchit la vue de la liste en utilisant un nouveau modèle de liste.
	 *
	 * @param modeleListe Le nouveau modèle de liste.
	 */
	public void rafraichir(ModeleListe modeleListe) {
		modele = modeleListe;
		this.setVisible(false);
		afficherCartes();
		this.setVisible(true);
	}

	/**
	 * Affiche les cartes associées à la liste en créant les vues correspondantes.
	 */
	public void afficherCartes() {
		cartes.removeAll();
		hauteur = VueCarte.taille / 2;
		for (ModeleCarte carte : modele.getCartes()) {
			VueCarte vueCarte = new VueCarte(carte);
			cartes.add(Box.createHorizontalStrut(5));
			cartes.add(vueCarte);
			hauteur += VueCarte.taille;
		}

		try {
			cartes.add(new ControllerListe(this, modele));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.setPreferredSize(new Dimension(LONGUEUR / 6, hauteur));
	}

	// Permet l'affichage correcte des listes sans qu'elle ce centre verticalement
	// par rapport à la première liste créé -Hugo Hergat
	@Override
	public int getBaseline(int width, int height) {
		return 0;
	}
}
