package controlleur;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;
import modeles.ModeleCarte;
import modeles.ModeleListe;
import modeles.ModeleTableau;
import modeles.ModeleMembre;
import vue.VueCarte;
import vue.VueTableau;
import vue.style.BeautyScrollBar;
import vue.style.BetterButton;
import vue.style.StyleGeneral;
import vue.VueListe;

/**
 * @author Christiant Corciu
 *
 */
public class ControllerCarte extends Controlleur implements StyleGeneral {

	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = 1612824526535555716L;

	// Modele et vue
	/**
	 * Le modèle de carte.
	 */
	private ModeleCarte modele;

	/**
	 * La vue de carte.
	 */
	private VueCarte vue;

	/**
	 * Le modèle de tableau auquel appartient la carte (utilisé pour avoir les membres).
	 */
	private ModeleTableau tableauAppartient;

	/**
	 * Panel pour le pop-up de la carte.
	 */
	private JPanel popupCarte;

	/**
	 * Label pour le nom de la carte.
	 */
	private JLabel nomCarte;

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
	 * ComboBox pour les membres.
	 */
	private JComboBox<Object> membres;

	/**
	 * Index des membres.
	 */
	private int membresIndex = 0;

	/**
	 * Bouton pour supprimer la carte.
	 */
	private BetterButton supprimer;

	/**
	 * Bouton pour déplacer la carte.
	 */
	private BetterButton deplacer;

	/**
	 * Action pour assigner un membre.
	 */
	private static final String ACTION_ASSIGN = "Assigner";

	/**
	 * Action pour supprimer la carte.
	 */
	private static final String ACTION_SUPPRIMER = "Supprimer";

	/**
	 * Action pour déplacer la carte.
	 */
	private static final String ACTION_DEPLACER = "Deplacer";

	
	/**
	 * @author Christian Corciu, Hugo Hergat
	 * @param vueCarte
	 * @param modeleCarte
	 * @throws ParseException
	 */
	public ControllerCarte(VueCarte vueCarte, ModeleCarte modeleCarte) throws ParseException {
		vue = vueCarte;
		modele = modeleCarte;

		// Préparation de popupCreerCarte
		popupCarte = new JPanel();
		popupCarte.setPreferredSize(new Dimension(300, 400));

		// Préparation de l'input du nom
		nomCarte = new JLabel(modele.getNom().trim());
		nomCarte.setPreferredSize(new Dimension(150, 20));
		nomCarte.setFont(new java.awt.Font(FONT_TITRE, NORMAL, TEXTE_TITRE));

		// Préparation de l'input de la descriptipn
		inputDescCarte = new JTextArea(modele.getDesc());
		inputDescCarte.setLineWrap(true);
		inputDescCarte.setWrapStyleWord(true);
		scrollDescCarte = new JScrollPane(inputDescCarte);
		scrollDescCarte.setPreferredSize(new Dimension(250, 200));
		scrollDescCarte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollDescCarte.setVerticalScrollBar(new BeautyScrollBar(JScrollBar.VERTICAL, 5, 4, 0, 15));

		// Préparation de l'input des dates
		inputDateDebut = new JFormattedTextField(new MaskFormatter("##-##-####"));
		inputDateDebut.setText(modele.getDateDebut());
		inputDateDebut.setPreferredSize(new Dimension(150, 20));
		inputDateFin = new JFormattedTextField(new MaskFormatter("##-##-####"));
		inputDateFin.setText(modele.getDateFin());
		inputDateFin.setPreferredSize(new Dimension(150, 20));
		
		//Préparation de la combobox d'assignation de membre
		tableauAppartient = modele.getListeAppartient().getTableauAppartient();
		membres = new JComboBox<>();
		membres.addItem("Aucun membre assigné !");
		for (ModeleMembre membre: tableauAppartient.getMembres()){
			membres.addItem(membre.getEmail());
		}
		membres.setSelectedIndex(membresIndex);
		membres.addActionListener(this);
		membres.setActionCommand(ACTION_ASSIGN);
		membres.setPreferredSize(new Dimension(200, 25));
		
		// Préparation des différents boutons
		supprimer = new BetterButton("Supprimer");
		supprimer.addActionListener(this);
		supprimer.setActionCommand(ACTION_SUPPRIMER);
		deplacer = new BetterButton("Deplacer");
		deplacer.addActionListener(this);
		deplacer.setActionCommand(ACTION_DEPLACER);

		// Ajout de tout les éléments au pop-up
		popupCarte.add(nomCarte, Component.CENTER_ALIGNMENT);
		popupCarte.add(scrollDescCarte);
		popupCarte.add(new JLabel("Date de début :"));
		popupCarte.add(inputDateDebut);
		popupCarte.add(new JLabel("Date de fin :"));
		popupCarte.add(inputDateFin);
		popupCarte.add(new JLabel("Format date : DD-MM-YYYY"));
		popupCarte.add(membres);
		popupCarte.add(supprimer);
		popupCarte.add(deplacer);

	}

	/**
	 *@author Hugo Hergat
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Action qui assigner un membre
		if(e.getActionCommand().equals(ACTION_ASSIGN)) {
			if (membres.getSelectedIndex() == 0) {
				//Cas ou on désassigne un membre ou qu'il n'y en a aucun assigné
				modele.setMembreAssigne(null);
				membresIndex = 0;
				membres.setSelectedIndex(0);
			}
			else {
				//On assigne le membre à la carte
				modele.setMembreAssigne( tableauAppartient.getMembres().get(membres.getSelectedIndex()-1));
				membresIndex = membres.getSelectedIndex();
			}
		}
		
		// Action qui supprime une carte
		else if(e.getActionCommand().equals(ACTION_SUPPRIMER)) {
			//On supprime la carte de la liste à laquelle elle appartient
			modele.getListeAppartient().delCarte(modele);
			//On obtient la vue de liste à laquelle appartient la carte
			VueListe temp = (VueListe)vue.getParent().getParent();
			//On rafraichit la liste afin de supprimer la vue de la carte
			temp.rafraichir(modele.getListeAppartient());
			// On ferme le popup de la carte
			SwingUtilities.getWindowAncestor(supprimer.getParent()).dispose();
		}
		
		// Action qui déplace une carte
		else if(e.getActionCommand().equals(ACTION_DEPLACER)) {
			//On créer la JComboBox avec les différentes listes
			JComboBox<Object> listes = new JComboBox<>(modele.getListeAppartient().getTableauAppartient().getNomsListes().toArray());
			// On crée le popup qui contient la JComboBox
			int option = JOptionPane.showOptionDialog(null, listes, "Déplacer vers une autre liste", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			//Si on appuie sur OK, on essaie de changer de liste
			if(option == JOptionPane.OK_OPTION) {
				
				//On récupère la liste sélectionné par l'utilisateurs
				ModeleListe nouvelleListe = modele.getListeAppartient().getTableauAppartient().getListes().get(listes.getSelectedIndex());
				
				//Si la liste est celle ou la carte se trouve déjà, on ne fait rien
				if(nouvelleListe.equals(modele.getListeAppartient())) {
					return;
				}
				
				//Sinon, on supprime la carte de la liste à laquelle elle appartient
				modele.getListeAppartient().delCarte(modele);
				//Puis on l'ajoute à sa nouvelle liste
				nouvelleListe.addCarte(modele);
						
				//On récupère la VueTableau qui contient les listes
				Container temp = vue.getParent();
				while(!(temp instanceof VueTableau)) {
					temp = temp.getParent();
				}
				VueTableau vueTableau = (VueTableau)temp;
				//Et on la rafraichit
				vueTableau.rafraichir(modele.getListeAppartient().getTableauAppartient());
				
				//Enfin, on supprime les 2 popups
				SwingUtilities.getWindowAncestor(listes.getParent()).dispose();
				SwingUtilities.getWindowAncestor(deplacer.getParent()).dispose();
			}
		}
	}

	/**
	 * @author Hugo Hergat
	 * 
	 * Cette fonction gère ce qu'il se passe quand on fait un clique gauche sur une carte
	 */
	public void cliqueGauche() {
		//On enlève l'action listener afin qu'il ne soit pas trigger par les retraits/ajouts d'item
		membres.removeActionListener(this);
		//On reset la combobox au cas ou il y a de nouveau membre
		membres.removeAllItems();
		//On mets le membresIndex à -1, car s'il y a un membre sélectionné, membresIndex = i + 1; et s'il n'y a pas de membre sélectionné, membresIndex = -1 + 1 = 0
		membresIndex = -1;
		membres.addItem("Aucun membre assigné !");
		
		//On ajoute tout les membres à la combobox, et si la carte à un membre assigné, on donne le membresIndex de la combobox
		for(int i = 0; i<tableauAppartient.getMembres().size(); i++) {
			membres.addItem(tableauAppartient.getMembres().get(i).getEmail());
			if(modele.getMembreAssigne() != null && modele.getMembreAssigne().getEmail() == tableauAppartient.getMembres().get(i).getEmail()) {
				membresIndex = i;
			}
		}
		membresIndex++;
		//On mets la ComboBox sur le bon membre
		membres.setSelectedIndex(membresIndex);
		
		//On remets l'action listener
		membres.addActionListener(this);
		
		//On crée le popup
		int option = JOptionPane.showOptionDialog(null, popupCarte, "Remplir les champs", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		//Si on appuie sur OK...
		if (option == JOptionPane.OK_OPTION) {
			// On vérifie que tout les élements sont bons
			try {
				// Vérification de la description
				if (inputDescCarte.getText().isBlank()) {
					inputDescCarte.setText(modele.getDesc());
					throw new IllegalArgumentException("La carte n'a pas de description !");
				}
				// Vérification du format de la date de début
				else if (!inputDateDebut.isEditValid()) {
					inputDateDebut.setText(modele.getDateDebut());
					throw new IllegalArgumentException("Votre date de début n'est pas bonne");
				}
				// Vérification du format date de fin
				else if (!inputDateFin.isEditValid()) {
					inputDateFin.setText(modele.getDateFin());
					throw new IllegalArgumentException("Votre date de fin n'est pas bonne");
				}
			} catch (IllegalArgumentException verifInput) {
				JOptionPane.showMessageDialog(null, verifInput.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!verifDate(inputDateDebut.getText())) {
				JOptionPane.showMessageDialog(null, "La date de début n'est pas bonne !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				inputDateDebut.setText(modele.getDateDebut());
				return;
			}

			// Vérifation que la date de fin soit une vrai date
			if (!verifDate(inputDateFin.getText())) {
				JOptionPane.showMessageDialog(null, "La date de fin n'est pas bonne !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				inputDateFin.setText(modele.getDateFin());
				return;
			}

			// Vérification que la date de fin soit avant la date de début
			if (!verifDate(inputDateDebut.getText(), inputDateFin.getText())) {
				JOptionPane.showMessageDialog(null, "La date de début doit être avant la date de fin !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				inputDateDebut.setText(modele.getDateDebut());
				inputDateFin.setText(modele.getDateFin());
				return;
			}
			modele.setDesc(inputDescCarte.getText());
			modele.setDateDebut(inputDateDebut.getText());
			modele.setDateFin(inputDateFin.getText());
			vue.rafraichir(modele);
		}
	}
}
