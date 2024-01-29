package modeles;

/**
 * Cette classe repr�sente l'utilisateur. Elle gere les valeur de l'utilisateur.
 * 
 * @since 2023-05-23
 * @author Ahmed Kafagy
 */
public class ModeleUtilisateur implements java.io.Serializable {

	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = -5860064535588882637L;

	/**
	 * Nom de la personne.
	 */
	protected String nom;

	/**
	 * Prénom de la personne.
	 */
	protected String prenom;

	/**
	 * Adresse email de la personne.
	 */
	protected String email;

	/**
	 * Pseudo de la personne.
	 */
	protected String pseudo;


	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param pseudo
	 */
	public ModeleUtilisateur(String nom, String prenom, String email, String pseudo) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.pseudo = pseudo;
	}

	/**
	 * @return nom le nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom 
	 * le nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom 
	 * le prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email 
	 * l'email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo 
	 * un String qui réprésente le pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}
