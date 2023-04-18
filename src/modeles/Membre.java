import java.util.*;

public class Membre {

	protected String nom;
	protected String prenom;
	protected String pseudo;
	protected String email;
	ArrayList<AccesTabPerm> accesTabPerms;

	/**
	 * 
	 * @param nom
	 * @param prenom
	 * @param pseudo
	 * @param email
	 */
	public Membre(String nom, String prenom, String pseudo, String email) {
		// TODO - implement Membre.Membre
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param membre
	 * @param tab
	 */
	public void addMembreTableau(Membre membre, Tableau tab) {
		// TODO - implement Membre.addMembreTableau
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param membre
	 * @param tab
	 */
	public void delMembreTableau(Membre membre, Tableau tab) {
		// TODO - implement Membre.delMembreTableau
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nom
	 * @param fond
	 */
	public void createTableau(String nom, Fond fond) {
		// TODO - implement Membre.createTableau
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param membre
	 * @param perm
	 * @param tab
	 */
	public void modifPerm(Membre membre, Permission perm, Tableau tab) {

	}

	/**
	 * 
	 * @param nom
	 */
	public void archiverTableau(String nom) {
		// TODO - implement Membre.archiverTableau
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nom
	 */
	public void rechercheTab(String nom) {
		// TODO - implement Membre.rechercheTab
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomTab
	 * @param nomListe
	 * @param nomCarte
	 * @param desc
	 * @param dateDebut
	 * @param dateFin
	 */
	public void addCarte(String nomTab, String nomListe, String nomCarte, String desc, String dateDebut, String dateFin) {
		// TODO - implement Membre.addCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomTab
	 * @param nomListe
	 * @param nomCarte
	 */
	public void delCarte(String nomTab, String nomListe, String nomCarte) {
		// TODO - implement Membre.delCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomTab
	 * @param nomListe
	 * @param nomCarte
	 * @param desc
	 * @param dateDebut
	 * @param dateFin
	 * @param nomEtiquette
	 * @param rgb
	 * @param forme
	 */
	public void addCarte(String nomTab, String nomListe, String nomCarte, String desc, String dateDebut, String dateFin, String nomEtiquette, String rgb, short forme) {
		// TODO - implement Membre.addCarte
		throw new UnsupportedOperationException();
	}

	public void operation() {
		// TODO - implement Membre.operation
		throw new UnsupportedOperationException();
	}

}