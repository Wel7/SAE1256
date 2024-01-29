package controlleur;

import vue.VueTableauManager;
import vue.style.ButtonBeauty;
import vue.style.BeautyComboBox;
import vue.style.StyleGeneral;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modeles.ModeleApplication;
import modeles.ModeleTableau;

/**
 * Cette classe représente le contrôleur qui lie l'application au manager de
 * tableau.
 *
 * @since 2023-05-18
 * @author Hugo Hergat
 */
public class ControlleurTableauManager extends Controlleur implements StyleGeneral {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1410084364587559185L;
	// Constantes
	static public final String ACTION_AJOUTER = "Ajouter";
	static public final String ACTION_ARCHIVER = "Archiver";
	static public final String ACTION_CHANGER = "Changer";
	static public final String ACTION_SUPPRIMER = "Supprimer";

	// Attributs
	/**
	 * Le modele de l'application.
	 */
	private ModeleApplication modele;
	/**
	 * La vue du manager de tableau.
	 */
	private VueTableauManager vue;

	/**
	 * Le panel qui contient les boutons
	 */
	private JPanel boutons;
	/**
	 * Le bouton ajouter
	 */
	private ButtonBeauty ajouter;
	/**
	 * Le bouton archiver
	 */
	private ButtonBeauty archiver;
	/**
	 * Le bouton supprimer
	 */
	private ButtonBeauty supprimer;
	
	/**
	 * Le bouton supprimer
	 */
	private ButtonBeauty choixcouleur;
	
	/**
	 * La combobox de choix du tableau
	 */
	private BeautyComboBox<Object> cbbChoixTab;

	/**
	 * Constructeur du contrôleur TableauManager.
	 *
	 * @param VueTableauManager La vue du manager de tableau.
	 * @param ModeleApplication Le modèle de l'application.
	 */
	public ControlleurTableauManager(VueTableauManager VueTableauManager, ModeleApplication ModeleApplication) {
		this.vue = VueTableauManager;
		this.modele = ModeleApplication;

		// Prépare le panel contenant les boutons et la ComboBox
		setPreferredSize(new Dimension(LONGUEUR / 6, HAUTEUR));
		setBackground(vue.getBackground());

		// Prépare le layout de ce panel
		FlowLayout flayout = new FlowLayout();
		flayout.setVgap(25);
		setLayout(flayout);

		// Prépare un panel pour les boutons
		boutons = new JPanel();
		boutons.setPreferredSize(new Dimension(LONGUEUR / 6, HAUTEUR));

		// Création des boutons Ajouter et Archiver
		ajouter = new ButtonBeauty("Ajouter");
		archiver = new ButtonBeauty("Archiver");
		supprimer = new ButtonBeauty("Supprimer");
		choixcouleur=new ButtonBeauty("Choix Fond");

		ajouter.setActionCommand(ACTION_AJOUTER);
		archiver.setActionCommand(ACTION_ARCHIVER);
		supprimer.setActionCommand(ACTION_SUPPRIMER);
		ajouter.addActionListener(this);
		archiver.addActionListener(this);
		supprimer.addActionListener(this);
		choixcouleur.addActionListener(this);
		add(choixcouleur);
        

		// Création de la ComboBox de sélection des tableaux

		cbbChoixTab = new BeautyComboBox<Object>(this.modele.getNomTableau().toArray());
		cbbChoixTab.setPreferredSize(new Dimension(150, 30));
		cbbChoixTab.setSelectedIndex(-1);
		cbbChoixTab.setActionCommand(ACTION_CHANGER);
		cbbChoixTab.addActionListener(this);

		// Ajoute les boutons à leur panel
		boutons.add(ajouter);
		boutons.setBackground(getBackground());

		// Ajoute les éléments au panel
		add(cbbChoixTab);
		add(boutons);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Ajoute un tableau
		if (e.getActionCommand().equals(ACTION_AJOUTER)) {
			// Ajoute un nouveau tableau à l'application
			String nomTableau = JOptionPane.showInputDialog(new JFrame(), "Entrer le nom du tableau");
			if (nomTableau == null) {
				return;
			}
			if (nomTableau.isBlank()) {
				modele.ajouterTableau(new ModeleTableau());
				rafraichirComboBox(1);
				return;
			}
			modele.ajouterTableau(new ModeleTableau(nomTableau));
			// Fait apparaître le nouveau tableau dans la ComboBox
			rafraichirComboBox(1);
		}

		// Archive un tableau
		else if (e.getActionCommand().equals(ACTION_ARCHIVER)) {
			int option = JOptionPane.showOptionDialog(null, new JLabel("Etes vous sur d'archiver ce tableau?"),
					"Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			// Si l'utilisateir appuie sur Ok, on supprime
			if (option == JOptionPane.OK_OPTION) {
				modele.archiverTableau(cbbChoixTab.getSelectedIndex());
				rafraichirComboBox(0);
			}

		}

		// Change de tableau affiché
		else if (e.getActionCommand().equals(ACTION_CHANGER)) {

			if (cbbChoixTab.getSelectedIndex() == -1) {

				boutons.remove(archiver);
				boutons.remove(supprimer);
				vue.rafraichir("Vous n'avez pas créer de tableau !");
				return;
			}
			// Si le tableau est archivé, on mets le bouton supprimer à la place du bouton
			// archiver
			else if (modele.getTableau(cbbChoixTab.getSelectedIndex()).isArchiver()) {
				boutons.remove(archiver);
				boutons.add(supprimer);
			}
			// Sinon on mets le bouton archiver à la place du bouton supprimer
			else {
				boutons.remove(supprimer);
				boutons.add(archiver);

			}
			// Puis on rafraichit le tableau
			vue.rafraichir(modele.getTableau(cbbChoixTab.getSelectedIndex()));
		}

		// Supprime un tableau archivé
		else if (e.getActionCommand().equals(ACTION_SUPPRIMER)) {
			// Popup de confirmation
			int option = JOptionPane.showOptionDialog(null, new JLabel("Etes vous sur de supprimer ce tableau?"),
					"Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			// Si l'utilisateir appuie sur Ok, on supprime
			if (option == JOptionPane.OK_OPTION) {
				modele.enleverTableau(modele.getTableau(cbbChoixTab.getSelectedIndex()));

				rafraichirComboBox(-1);
				vue.rafraichir(new ModeleTableau("Vous n'avez pas crée de tableau", -1));
			}
		} else if (e.getSource() == choixcouleur) {
			Color background = JColorChooser.showDialog(null, "Choisir votre Fond", vue.getBackground());
			if (background == null) {
				background = COULEUR_FOND;
			}
			vue.setBackground(background);
			this.setBackground(background);
			boutons.setBackground(background);
			vue.getVueTabInfo().rafraichir(background);
			//TODO

		}
	}

	/**
	 * Rafraîchit la ComboBox afin d'afficher les éléments ajoutés ou supprimer les
	 * éléments supprimés.
	 * 
	 * @param indexModifier: Ajoute ce nombre à l'index sélectionné sur la ComboBox
	 */
	private void rafraichirComboBox(int indexModifier) {
		// On mets de coté l'index sélectionner et lui ajoute l'indexModifier
		int select = cbbChoixTab.getSelectedIndex() + indexModifier;

		// Supprime tous les items de la ComboBox
		cbbChoixTab.removeAllItems();
		// Remet les noms des tableaux dans la ComboBox
		for (String nom : this.modele.getNomTableau()) {
			cbbChoixTab.addItem(nom);
		}
		if (select != 0) {
			cbbChoixTab.setSelectedIndex(select);
		}

	}
}
