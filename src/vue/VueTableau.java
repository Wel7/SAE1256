package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controlleur.ControllerTableau;
import modeles.ModeleListe;
import modeles.ModeleTableau;
import vue.style.BeautyScrollBar;

/**
 * Cette classe représente ce que l'utilisateur voit d'un tableau.
 *
 * @since 2023-05-18
 * @author Hugo Hergat
 */
public class VueTableau extends Vue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6475160198844383138L;

	// Modèle
	private ModeleTableau modele;

	// Attributs
	private VueTableauInfo vueTabInfo;
	private ArrayList<VueListe> listesVue = new ArrayList<VueListe>();
	private JPanel listes;
	private JScrollPane scrollListes;

	/**
	 * Constructeur de la vue d'un tableau.
	 *
	 * @param modeleTableau Le modèle du tableau associé à cette vue.
	 */
	public VueTableau(ModeleTableau modeleTableau) {
		this.modele = modeleTableau;

		setLayout(new BorderLayout());

		vueTabInfo = new VueTableauInfo(this.modele);
		add(vueTabInfo, BorderLayout.NORTH);

		listes = new JPanel();
		FlowLayout flayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
		flayout.setAlignOnBaseline(true);
		listes.setLayout(flayout);

		scrollListes = new JScrollPane(listes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollListes.setPreferredSize(new Dimension(LONGUEUR - LONGUEUR / 6, HAUTEUR - HAUTEUR / 6));
		scrollListes.setVerticalScrollBar(new BeautyScrollBar(JScrollBar.VERTICAL, 5, 4, 0, 15));
		scrollListes.setHorizontalScrollBar(new BeautyScrollBar(JScrollBar.HORIZONTAL, 5, 4, 0, 15));

		afficherListe();
		add(scrollListes);
	}

	/**
	 * Met à jour la vue avec un nouveau modèle de tableau.
	 *
	 * @param modeleTableau Le nouveau modèle de tableau.
	 */
	public void rafraichir(ModeleTableau modeleTableau) {
		this.setVisible(false);
		this.modele = modeleTableau;
		vueTabInfo.rafraichir(this.modele);
		afficherListe();
		this.setVisible(true);
	}

	/**
	 * Met à jour la vue avec un String.
	 *
	 * @param modeleTableau Le nouveau modèle de tableau.
	 */
	public void rafraichir(String texte) {
		this.setVisible(false);
		vueTabInfo.rafraichir(texte);
		this.setVisible(true);
	}

	/**
	 * Affiche les listes du tableau.
	 */
	public void afficherListe() {
		listesVue.clear();
		listes.removeAll();
		for (ModeleListe modeleListe : modele.getListes()) {
			listesVue.add(new VueListe(modeleListe));
		}
		for (VueListe vueListe : this.listesVue) {
			listes.add(vueListe);
		}
		listes.add(new ControllerTableau(this, modele));
	}

	public void supprimerVueListe(VueListe vueListe) {
		listes.remove(vueListe);
	}
	
	public VueTableauInfo getVueTabInfo() {
		return vueTabInfo;
	}

}
