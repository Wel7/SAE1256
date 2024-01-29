package modeles;

import java.util.ArrayList;

/**
 * Cette classe représente un tableau dans l'application. Un tableau est une
 * collection de listes contenant des cartes.
 * 
 * @since 2023-05-18
 * @author Hugo Hergat
 */
public class ModeleTableau implements java.io.Serializable {

	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = -2344003421068282662L;

	/**
	 * ID suivant pour la création d'une nouvelle instance de la classe.
	 */
	private static int nextID = 0;

	/**
	 * ID de l'instance de la classe.
	 */
	private int id;

	/**
	 * Nom de l'instance de la classe.
	 */
	private String nom;

	/**
	 * Liste des listes associées à l'instance de la classe.
	 */
	private ArrayList<ModeleListe> listes;

	/**
	 * Liste des membres associés à l'instance de la classe.
	 */
	private ArrayList<ModeleMembre> listesMembre;

	/**
	 * Indique si l'instance de la classe est archivée ou non.
	 */
	private boolean archiver;


	/**
	 * Constructeur d'un tableau avec un nom.
	 * 
	 * @since 2023-05-18
	 * @param nom Le nom du tableau.
	 */
	public ModeleTableau(String nom) {
		id = nextID;
		nextID++;
		this.nom = nom;
		listes = new ArrayList<ModeleListe>();
		listesMembre = new ArrayList<ModeleMembre>();
	}
	
	/**
	 * Constructeur d'un tableau avec un nom.
	 * 
	 * @since 2023-05-18
	 * @param nom Le nom du tableau.
	 */
	public ModeleTableau(String nom, int id) {
		this.id = id;
		this.nom = nom;
		listes = new ArrayList<ModeleListe>();
		listesMembre = new ArrayList<ModeleMembre>();
		archiver = true;
	}

	/**
	 * Constructeur d'un tableau sans nom.
	 * 
	 * @since 2023-05-18
	 */
	public ModeleTableau() {
		id = nextID;
		nextID++;
		this.nom = "Tableau " + id;
		listes = new ArrayList<ModeleListe>();
		listesMembre = new ArrayList<ModeleMembre>();
	}

	/**
	 * Supprime une liste du tableau en utilisant son identifiant.
	 * 
	 * @param id L'identifiant de la liste à supprimer.
	 */
	public void delListe(int id) {
		// TODO - implement Tableau.delListe
		throw new UnsupportedOperationException();
	}

	/**
	 * Ajoute une liste au tableau avec le nom spécifié.
	 * 
	 * @param nom Le nom de la liste à ajouter.
	 */
	public void addListe(String nom) {
		listes.add(new ModeleListe(nom, this));
	}

	/**
	 * Archive le tableau.
	 */
	public void archiverTab() {
		archiver = true;
	}

	/**
	 * Retourne l'identifiant du tableau.
	 * 
	 * @return L'identifiant du tableau.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant du tableau.
	 * 
	 * @param id Le nouvel identifiant du tableau.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retourne le nom du tableau.
	 * 
	 * @return Le nom du tableau.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du tableau.
	 * 
	 * @param nom Le nouveau nom du tableau.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne la liste des listes du tableau.
	 * 
	 * @return La liste des listes du tableau.
	 */
	public ArrayList<ModeleListe> getListes() {
		return listes;
	}
	
	public ArrayList<String> getNomsListes() {
		ArrayList<String> nomsListes = new ArrayList<String>();
		for(ModeleListe liste: listes) {
			nomsListes.add(liste.getNom());
		}
		return nomsListes;
	}

	/**
	 * Modifie la liste des listes du tableau.
	 * 
	 * @param listes La nouvelle liste des listes du tableau.
	 */
	public void setListes(ArrayList<ModeleListe> listes) {
		this.listes = listes;
	}

	/**
	 * Vérifie si le tableau est archivé.
	 * 
	 * @return true si le tableau est archivé, false sinon.
	 */
	public boolean isArchiver() {
		return archiver;
	}

	/**
	 * Modifie l'état d'archivage du tableau.
	 * 
	 * @param archiver true pour archiver le tableau, false pour le désarchiver.
	 */
	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}

	public void addMembre(ModeleMembre membre) {
		listesMembre.add(membre);
	}
	
	public ArrayList<ModeleMembre> getMembres(){
		return listesMembre;
	}
	
	public void delMembre(ModeleMembre membre) {
		listesMembre.remove(membre);
	}
	


}
