package controlleur;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import modeles.*;
import vue.*;
import vue.style.BeautyScrollBar;
import vue.style.BetterButton;
import vue.style.StyleGeneral;

/**
 * Cette classe représente le controlleur qui lie VueListe et ModeleListe
 * 
 * 
 * @since 2023-05-19
 * 
 * @author Noé Chrétien
 */
public class ControllerListe extends Controlleur implements StyleGeneral {

	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = 2848563559276211166L;

	/**
	 * Action pour ajouter une carte.
	 */
	public static final String ACTION_AJOUTER = "Ajouter";

	/**
	 * Action pour supprimer une liste.
	 */
	public static final String ACTION_SUPPRIMER = "Supprimer";

	// Modele et vue
	/**
	 * Le modèle de liste.
	 */
	private ModeleListe modele;

	/**
	 * La vue de liste.
	 */
	private VueListe vue;

	// Attributs
	/**
	 * Bouton pour ajouter une carte.
	 */
	private BetterButton ajouterCarte;

	/**
	 * Bouton pour supprimer une liste.
	 */
	private BetterButton supprimerListe;

	// Attributs nécessaires pour le pop-up de création d'une carte
	/**
	 * Panel pour le pop-up de création d'une carte.
	 */
	private JPanel popupCreerCarte;

	/**
	 * Panel pour le pop-up de suppression d'une liste.
	 */
	private JPanel popupSupprimerListe;

	/**
	 * Nom de la carte.
	 */
	private String nomCarte;

	/**
	 * Description de la carte.
	 */
	private String descCarte;

	/**
	 * Date de début de la carte.
	 */
	private String dateDebut;

	/**
	 * Date de fin de la carte.
	 */
	private String dateFin;

	/**
	 * Champ de texte pour le nom de la carte.
	 */
	private JFormattedTextField inputNomCarte;

	/**
	 * Champ de texte pour la description de la carte.
	 */
	private JTextArea inputDescCarte;

	/**
	 * Scroll pane pour la zone de texte de la description de la carte.
	 */
	private JScrollPane scrollDescCarte;

	/**
	 * Champ de texte pour la date de début de la carte.
	 */
	private JFormattedTextField inputDateDebut;

	/**
	 * Champ de texte pour la date de fin de la carte.
	 */
	private JFormattedTextField inputDateFin;


	/**
	 * Constructeur du contrôleur ControllerListe.
	 * 
	 * @since 2023-05-19
	 * 
	 * @author Noé Chrétien
	 * 
	 * @param VueListe
	 * @param ModeleListe
	 * @throws ParseException
	 */
	public ControllerListe(VueListe VueListe, ModeleListe ModeleListe) throws ParseException {
		this.vue = VueListe;
		this.modele = ModeleListe;

		// Si le tableau est archivé
		if (ModeleListe.getTableauAppartient().isArchiver()) {

			// Cr�e un bouton supprimer
			supprimerListe = new BetterButton("Supprimer Liste");
			supprimerListe.setActionCommand(ACTION_SUPPRIMER);
			supprimerListe.addActionListener(this);
			add(supprimerListe);

			// pr�pare une pop Up supprimer
			popupSupprimerListe = new JPanel();
			popupSupprimerListe.setPreferredSize(new Dimension(250, 50));

			// Ajout de l'element voulu
			popupSupprimerListe.add(new JLabel("Etes-vous sur de vouloir supprimer la liste?"));

			return;
		}

		// Préparation de popupCreerCarte
		popupCreerCarte = new JPanel();
		popupCreerCarte.setPreferredSize(new Dimension(300, 350));

		// Préparation de l'input du nom
		MaskFormatter formatter = new MaskFormatter("********************************");
		inputNomCarte = new JFormattedTextField(formatter);
		inputNomCarte.setPreferredSize(new Dimension(150, 20));

		// Préparation de l'input de la descriptipn
		inputDescCarte = new JTextArea("Description de la carte...");
		inputDescCarte.setLineWrap(true);
		inputDescCarte.setWrapStyleWord(true);
		inputDescCarte.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(inputDescCarte.getText().equals("Description de la carte...")) {
					inputDescCarte.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Do nothing
			}
		});
		
		scrollDescCarte = new JScrollPane(inputDescCarte);
		scrollDescCarte.setPreferredSize(new Dimension(250, 200));
		scrollDescCarte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollDescCarte.setVerticalScrollBar(new BeautyScrollBar(JScrollBar.VERTICAL, 5, 4, 0, 15));

		// Préparation de l'input des dates
		inputDateDebut = new JFormattedTextField(new MaskFormatter("##-##-####"));
		inputDateDebut.setPreferredSize(new Dimension(150, 20));
		inputDateFin = new JFormattedTextField(new MaskFormatter("##-##-####"));
		inputDateFin.setPreferredSize(new Dimension(150, 20));

		// Ajout de tout les éléments au pop-up
		popupCreerCarte.add(new JLabel("Nom de la carte :"));
		popupCreerCarte.add(inputNomCarte);
		popupCreerCarte.add(scrollDescCarte);
		popupCreerCarte.add(new JLabel("Date de début :"));
		popupCreerCarte.add(inputDateDebut);
		popupCreerCarte.add(new JLabel("Date de fin :"));
		popupCreerCarte.add(inputDateFin);
		popupCreerCarte.add(new JLabel("Format date : DD-MM-YYYY"));

		// Création du bouton
		ajouterCarte = new BetterButton("Ajouter Carte");
		ajouterCarte.setActionCommand(ACTION_AJOUTER);
		ajouterCarte.addActionListener(this);
		add(ajouterCarte);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Si on ajoute une carte
		if (e.getActionCommand().equals(ACTION_AJOUTER)) {
			// On affiche un pop-up qui contient de quoi remplir toutes les infos
			// nécessaires à la création d'une carte
			int option = JOptionPane.showOptionDialog(null, popupCreerCarte, "Remplir les champs",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			// Si on appuie sur le bouton OK
			if (option == JOptionPane.OK_OPTION) {
				// On vérifie que tout les élements sont bons
				try {
					// Vérification du nom
					if (inputNomCarte.getText().isBlank()) {
						throw new IllegalArgumentException("La carte n'a pas de nom !");
					}
					// Vérification de la description
					else if (inputDescCarte.getText().isBlank()) {
						throw new IllegalArgumentException("La carte n'a pas de description !");
					}
					// Vérification du format de la date de début
					else if (!inputDateDebut.isEditValid()) {
						inputDateDebut.setText("");
						throw new IllegalArgumentException("Votre date de début n'est pas bonne");
					}
					// Vérification du format date de fin
					else if (!inputDateFin.isEditValid()) {
						throw new IllegalArgumentException("Votre date de fin n'est pas bonne");
					}
				} catch (IllegalArgumentException verifInput) {
					JOptionPane.showMessageDialog(null, verifInput.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Vérifaction que la date de début soit une vrai date
				if (!verifDate(inputDateDebut.getText())) {
					JOptionPane.showMessageDialog(null, "La date de début n'est pas bonne !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Vérifation que la date de fin soit une vrai date
				if (!verifDate(inputDateFin.getText())) {
					JOptionPane.showMessageDialog(null, "La date de fin n'est pas bonne !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Vérification que la date de fin soit avant la date de début
				if (!verifDate(inputDateDebut.getText(), inputDateFin.getText())) {
					JOptionPane.showMessageDialog(null, "La date de début doit être avant la date de fin !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// S'il n'y a eu aucune erreur, alors on peut créer la carte voulue
				nomCarte = inputNomCarte.getText().trim();
				descCarte = inputDescCarte.getText().trim();
				dateDebut = inputDateDebut.getText();
				dateFin = inputDateFin.getText();

				// Puie on l'ajoute à la liste, et on rafraichit la vue !
				modele.addCarte(new ModeleCarte(nomCarte, descCarte, dateDebut, dateFin, modele));
				vue.rafraichir(modele);
				

			} else {
				if(inputDescCarte.getText().isBlank()) {
					inputDescCarte.setText("Description de la carte...");
				}
			}
		}
		// si on supprime une liste
		else if (e.getActionCommand().equals(ACTION_SUPPRIMER)) {
			// on affiche une pop up de validation
			int popUp = JOptionPane.showOptionDialog(null, popupSupprimerListe, "Supprimer",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			// Si on appuie sur le bouton ok
			if (popUp == JOptionPane.OK_OPTION) {
				// On supprime la liste de cartes
				modele.delListe();
				// Récupérer le parent de la vueListe
				Container parent = vue.getParent();
				if (parent instanceof JPanel) {
					JPanel panelParent = (JPanel) parent;
					panelParent.remove(vue);
					panelParent.revalidate();
					panelParent.repaint();

					// Supprimer la VueListe du tableau
					if (panelParent instanceof VueTableau) {
						VueTableau vueTableau = (VueTableau) panelParent;
						vueTableau.supprimerVueListe(vue);
					}
				}

				// On rafraîchit la vue
				vue.rafraichir(modele);

			}
		}

	}
}
