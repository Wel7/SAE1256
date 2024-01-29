package vue;

import modeles.ModeleCarte;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.*;

import controlleur.ControllerCarte;

/**
 * Cette classe représente la vue d'une carte. Elle affiche les informations de
 * la carte, telles que le nom, la description et les dates.
 *
 * @since 2023-05-18
 * 
 * @author Cristian Corciu, Hugo Hergat
 */
//Christiant a fait la majorité, Hugo a peaufiné et a ajouté le controlleur
public class VueCarte extends Vue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274306903023995443L;

	public static final int taille = 200;

	private ModeleCarte modele;
	private JLabel nom;
	private JLabel description;
	private JLabel dateDebut;
	private JLabel dateFin;
	private JPanel date;

	private ControllerCarte ControllerCarte;

	/**
	 * Constructeur de la vue d'une carte.
	 *
	 * @param modeleCarte Le modèle de carte associé à la vue.
	 */
	public VueCarte(ModeleCarte modeleCarte) {
		this.modele = modeleCarte;

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		nom = new JLabel(modele.getNom(), JLabel.CENTER);
		nom.setFont(new java.awt.Font(FONT_TITRE, GRAS, TEXTE_TITRE));
		description = new JLabel(modele.getDesc());
		description.setFont(new java.awt.Font(FONT_NORMAL, NORMAL, TEXTE_NORMAL));
		description.setPreferredSize(new Dimension(taille, taille - 50));

		dateDebut = new JLabel(modele.getDateDebut());
		dateFin = new JLabel(modele.getDateFin());
		date = new JPanel();

		date.setLayout(new GridLayout(1, 2));
		date.add(dateDebut);
		date.add(dateFin);
		date.setBackground(getBackground());

		setPreferredSize(new Dimension(taille, taille));

		add(nom, BorderLayout.NORTH);
		add(description, BorderLayout.CENTER);
		add(date, BorderLayout.SOUTH);

		try {
			ControllerCarte = new ControllerCarte(this, modele);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// On ajout le MouseListener ici car il pose un problème d'afficher si on
		// l'ajoute via ControllerCarte
		// Cependant, les actions de ces cliques sont gérés dans ControllerCarte
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Vérifie si le bouton de la souris est le bouton droit
				if (e.getButton() == MouseEvent.BUTTON1) {
					ControllerCarte.cliqueGauche();
				}
			}
		});
	}

	public void rafraichir(ModeleCarte modele) {
		this.setVisible(false);
		description.setText(modele.getDesc());
		dateDebut.setText(modele.getDateDebut());
		dateFin.setText(modele.getDateFin());
		this.setVisible(true);
	}
}
