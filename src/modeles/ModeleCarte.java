package modeles;

/**
 * Cette classe représente une carte. Une carte est une entité qui contient des
 * informations telles que le nom, la description, les dates de début et de fin,
 * les membres assignés, les étiquettes et les commentaires.
 * 
 * @since 2023-05-21
 * @author Christian Corciu
 */
public class ModeleCarte implements java.io.Serializable {

	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = -8134558116807284977L;

	/**
	 * Le membre assigné à la carte.
	 */
	private ModeleMembre membreAssigne;

	/**
	 * L'identifiant de la carte.
	 */
	private int id;

	/**
	 * Le nom de la carte.
	 */
	private String nom;

	/**
	 * La description de la carte.
	 */
	private String desc;

	/**
	 * La date de début de la carte.
	 */
	private String dateDebut;

	/**
	 * La date de fin de la carte.
	 */
	private String dateFin;

	/**
	 * Le modèle de liste auquel la carte appartient.
	 */
	private ModeleListe listeAppartient;



	/**
	 * Constructeur de la classe ModeleCarte.
	 * 
	 * @param nom       Le nom de la carte.
	 * @param desc      La description de la carte.
	 * @param dateDebut La date de début de la carte.
	 * @param dateFin   La date de fin de la carte.
	 */
	public ModeleCarte(String nom, String desc, String dateDebut, String dateFin, ModeleListe listeAppartient) {
		this.nom = nom;
		this.desc = desc;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.listeAppartient = listeAppartient;
	}

	/**
	 * Ajoute un commentaire à la carte.
	 * 
	 * @param commentaire Le contenu du commentaire.
	 * @param membre      Le membre qui a ajouté le commentaire.
	 */
	public void addCommentaire(String commentaire, ModeleMembre membre) {
		// TODO: Implémenter la logique pour ajouter un commentaire à la carte
	}

	/**
	 * Supprime un commentaire de la carte.
	 * 
	 * @param id L'identifiant du commentaire à supprimer.
	 */
	public void delCommentaire(int id) {
		// TODO: Implémenter la logique pour supprimer un commentaire de la carte
	}

	/**
	 * Ajoute une étiquette à la carte.
	 * 
	 * @param nom   Le nom de l'étiquette.
	 * @param forme La forme de l'étiquette.
	 * @param rgb   La couleur de l'étiquette en format RGB.
	 */
	public void addEtiquette(String nom, Short forme, String rgb) {
		// TODO: Implémenter la logique pour ajouter une étiquette à la carte
	}

	/**
	 * Retourne l'identifiant de la carte.
	 * 
	 * @return L'identifiant de la carte.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retourne le nom de la carte.
	 * 
	 * @return Le nom de la carte.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom de la carte.
	 * 
	 * @param nom Le nouveau nom de la carte.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne la description de la carte.
	 * 
	 * @return La description de la carte.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Modifie la description de la carte.
	 * 
	 * @param desc La nouvelle description de la carte.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Retourne la date de début de la carte.
	 * 
	 * @return La date de début de la carte.
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * Modifie la date de début de la carte.
	 * 
	 * @param dateDebut La nouvelle date de début de la carte.
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Retourne la date de fin de la carte.
	 * 
	 * @return La date de fin de la carte.
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * Modifie la date de fin de la carte.
	 * 
	 * @param dateFin La nouvelle date de fin de la carte.
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Modifie l'identifiant de la carte.
	 * 
	 * @param id Le nouvel identifiant de la carte.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public ModeleMembre getMembreAssigne() {
		return membreAssigne;
	}

	public void setMembreAssigne(ModeleMembre membreAssigne) {
		this.membreAssigne = membreAssigne;
	}

	public ModeleListe getListeAppartient() {
		return listeAppartient;
	}

	public void setListeAppartient(ModeleListe listeAppartient) {
		this.listeAppartient = listeAppartient;
	}


}
