package modeles;

import java.util.ArrayList;

/**
 * Cette classe représente une liste dans un tableau. Une liste est une
 * collection de cartes associées à un tableau.
 * 
 * @author Noe Chretien
 * @since 2023-05-21
 */
public class ModeleListe implements java.io.Serializable {

	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = -8731292987127409656L;

	/**
	 * Le modèle de tableau auquel appartient la carte.
	 */
	private ModeleTableau tableauAppartient;

	/**
	 * Le nom de la carte.
	 */
	private String nom;

	/**
	 * La liste des cartes associées.
	 */
	private ArrayList<ModeleCarte> cartes;


	/**
	 * Constructeur de la classe ModeleListe.
	 * 
	 * @param nom Le nom de la liste.
	 */
	public ModeleListe(String nom, ModeleTableau tableauAppartient) {
		this.tableauAppartient = tableauAppartient;
		this.nom = nom;
		this.cartes = new ArrayList<ModeleCarte>();
	}

	/**
	 * Supprime une carte de la liste en utilisant son identifiant.
	 * 
	 * @param id L'identifiant de la carte à supprimer.
	 */
	public void delCarte(ModeleCarte carte) {
		cartes.remove(carte);
	}

	/**
	 * Ajoute une carte à la liste.
	 * 
	 * @param carte La carte à ajouter.
	 */
	public void addCarte(ModeleCarte carte) {
		carte.setListeAppartient(this);
		cartes.add(carte);
	}

	/**
	 * Retourne la liste des cartes de la liste.
	 * 
	 * @return La liste des cartes de la liste.
	 */
	public ArrayList<ModeleCarte> getCartes() {
		return cartes;
	}

	/**
	 * Retourne le nom de la liste.
	 * 
	 * @return Le nom de la liste.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne la tableau auquelle appartient la liste.
	 * 
	 * @return Le tableau auquelle appartient la liste.
	 */
	public ModeleTableau getTableauAppartient() {
		return tableauAppartient;
	}

	public void delListe() {
		if (tableauAppartient != null) {
			tableauAppartient.getListes().remove(this);
			System.out.println("Liste supprimée : " + nom);
		} else {
			System.out.println("La liste n'appartient à aucun tableau.");
		}
	}

}
