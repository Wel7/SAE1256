package controlleur;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import modeles.ModeleMembre;
import vue.VueLogoMembre;
import vue.VueMembre;
import vue.VueTableauInfo;
import vue.style.BetterButton;
import vue.style.StyleGeneral;
import vue.style.BeautyComboBox;

/**
 * @author ahmed kafagy
 *@since 23-05-2023
 */
public class ControllerInfoMembre extends Controlleur implements StyleGeneral {
	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = 4271398148445038251L;

	//-------------------------------------
	//---Déclaration attributs de classe----
	//-------------------------------------

	/**
	 * Le modèle de membre.
	 */
	private ModeleMembre membre;

	/**
	 * La vue du logo membre.
	 */
	private VueLogoMembre vueLogoMembre;

	/**
	 * La vue du membre.
	 */
	private VueMembre vueMembre;

	/**
	 * Action pour la combobox.
	 */
	private static final String ACTION_COMBOBOX = "SETPERM";

	/**
	 * Action pour supprimer.
	 */
	private static final String ACTION_SUPPRIMER = "SUPPRIMER";

	/**
	 * Combobox de beauté pour la permission.
	 */
	private BeautyComboBox<Object> comboPerm;

	/**
	 * Panel conteneur.
	 */
	private JPanel containeur = new JPanel();

	/**
	 * Bouton pour supprimer.
	 */
	private BetterButton supprimer;

	/**
	 * Liste des permissions.
	 */
	private ArrayList<String> permission = new ArrayList<String>();


	//-------------------------------------
	//---Constructeur ControllerInfoMembre-
	//-------------------------------------	
	/**
	 * @author Ahmed Kafagy
	 * @param vueLogo
	 * @param membre
	 */
	public ControllerInfoMembre (VueLogoMembre vueLogo , ModeleMembre membre ) {
		this.membre = membre; //assignation du modele
		vueMembre = new VueMembre(membre); //creation de la vue de modele
		vueLogoMembre = vueLogo;
		permission.add("LECTEUR");//---
		permission.add("COMMENTATEUR");//ajout des permission a <permission>
		permission.add("EDITEUR");//---
		comboPerm = new BeautyComboBox<Object>(permission.toArray());//declaration de l'objet comboPerm avec permission
		containeur.setLayout(new BorderLayout());				
		comboPerm.addActionListener(this);
		comboPerm.setActionCommand(ACTION_COMBOBOX);
		supprimer = new BetterButton("Supprimer");
		supprimer.addActionListener(this);
		supprimer.setActionCommand(ACTION_SUPPRIMER);
		containeur.add(comboPerm , BorderLayout.CENTER);
		containeur.add(vueMembre , BorderLayout.NORTH);
		vueMembre.setSize(WIDTH, containeur.getHeight());

}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ACTION_COMBOBOX)) {
			membre.setPerm(comboPerm.getSelectedItem().toString());
			vueMembre.rafraichir(membre);
		}
		else if(e.getActionCommand().equals(ACTION_SUPPRIMER)) {
			//Il y a 4 getParent car vueLogoMembre se trouve dans un JPanel qui se trouver dans un JScrollPane qui se trouve dans VueTabInfo
			//JScrollPane compte pour 2 getParent car il utilise un autre containeur
			Container parent = vueLogoMembre.getParent().getParent().getParent().getParent();
			if(parent instanceof VueTableauInfo) {
				VueTableauInfo vti = (VueTableauInfo) parent;
				vti.getModele().delMembre(membre);
				vti.rafraichir(vti.getModele());
				SwingUtilities.getWindowAncestor(supprimer.getParent()).dispose();
			}
		}
	}
	
	/**
	 * @author Ahmed Kafagy
	 * 
	 * Cette fonction gère ce qu'il se passe quand on fait un clique gauche sur un membre
	 */
	public void clickGauche() {
		@SuppressWarnings("unused")
		int temp = JOptionPane.showOptionDialog(null, containeur, membre.getPseudo(), 
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] {supprimer}, null);
	}
}
