package modeles;

/**
 * Cette classe contient les information et la déclaration d'un membre 
 *
 * @since 2023-05-25
 * @author Kafagy Ahmed
 */

public class ModeleMembre extends ModeleUtilisateur implements java.io.Serializable {
	/**
	 * 
	 */
	/**
	 * Numéro de série de la classe.
	 */
	private static final long serialVersionUID = 1123997211807974341L;

	/**
	 * Les différentes permissions disponibles.
	 */
	private static final String[] PERMISSIONS = {"LECTEUR" , "COMMENTATEUR" , "EDITEUR"};

	/**
	 * La permission attribuée.
	 */
	private String permission;


	//-------------------------------------
	//-------Constructeur ModeleMembre-----
	//-------------------------------------	
	public ModeleMembre(String nom , String prenom , String email , String pseudo ) {
		super(nom,prenom,email,pseudo);
		this.permission = PERMISSIONS[0];
	}

	//-----------------------------------------------
	//-------setPerm pour changer les permission-----
	//-----------------------------------------------
	public void setPerm(String permission) {
		this.permission=permission;
	}

	//-----------------------------------------------
	//-------getPerm pour obtenir les permission-----
	//-----------------------------------------------
	public String getPerm() {return this.permission;};

}

