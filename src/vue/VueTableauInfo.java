package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controlleur.ControllerMembre;
import modeles.ModeleMembre;
import modeles.ModeleTableau;
import vue.style.BeautyScrollBar;

/**
 * Cette classe représente ce que l'utilisateur voit des informations
 * disponibles d'un tableau.
 *
 * @since 2023-05-18
 * @author Hugo Hergat
 */
public class VueTableauInfo extends Vue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6491858477276817322L;
	private ModeleTableau modele;
	private JLabel nomTableau;
	private ControllerMembre boutonMembre;
	private JPanel membres;
	private JScrollPane scrollMembres;

	/**
	 * Constructeur de la vue des informations d'un tableau.
	 *
	 * @param modeleTableau Le modèle du tableau associé à cette vue.
	 */
	public VueTableauInfo(ModeleTableau modeleTableau) {
		this.modele = modeleTableau;

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(LONGUEUR, HAUTEUR / 12));
		setBackground(COULEUR_FOND);

		membres = new JPanel(new FlowLayout(FlowLayout.LEFT));
		membres.setBackground(getBackground());

		nomTableau = new JLabel(this.modele.getNom());
		nomTableau.setFont(new java.awt.Font(FONT_TITRE, GRAS, TEXTE_TITRE));
		nomTableau.setPreferredSize(new Dimension(LONGUEUR / 4, HAUTEUR / 12));
		add(nomTableau, BorderLayout.WEST);

		boutonMembre = new ControllerMembre(this, modele);

		add(boutonMembre, BorderLayout.EAST);

		scrollMembres = new JScrollPane(membres, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollMembres.setHorizontalScrollBar(new BeautyScrollBar(JScrollBar.HORIZONTAL, 5, 4, 0, 15));
		// scrollMembres.setPreferredSize(new Dimension(LONGUEUR, HAUTEUR / 12));
		scrollMembres.setBorder(BorderFactory.createEmptyBorder());

		for (ModeleMembre valeur : modeleTableau.getMembres()) {
			membres.add(new VueLogoMembre(valeur));
		}
		add(scrollMembres, BorderLayout.CENTER);
	}

	/**
	 * Met à jour la vue avec un nouveau modèle de tableau.
	 *
	 * @param modeleTableau Le nouveau modèle de tableau.
	 */
	public void rafraichir(ModeleTableau modeleTableau) {
		this.setVisible(false);
		this.removeAll();
		membres.removeAll();
		this.modele = modeleTableau;

		nomTableau.setText(modele.getNom());

		for (ModeleMembre valeur : modeleTableau.getMembres()) {
			membres.add(new VueLogoMembre(valeur));
		}

		add(nomTableau, BorderLayout.WEST);
		scrollMembres.setHorizontalScrollBar(new BeautyScrollBar(JScrollBar.HORIZONTAL, 5, 4, 0, 15));
		add(scrollMembres, BorderLayout.CENTER);

		if (!modele.isArchiver()) {
			boutonMembre = new ControllerMembre(this, modele);
			add(boutonMembre, BorderLayout.EAST);
		}
		this.setVisible(true);
	}

	public void rafraichir(String texte) {
		this.removeAll();
		add(nomTableau, BorderLayout.WEST);
		nomTableau.setText(texte);
	}

	public ModeleTableau getModele() {
		return modele;
	}

	public void rafraichir(Color background) {
		this.setBackground(background);
		membres.setBackground(background);
		boutonMembre.setBackground(background);
	}
}
