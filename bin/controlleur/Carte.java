import java.util.*;

public class Carte {

	ArrayList<Membre> membreAssign;
	protected int id;
	protected String nom;
	protected String desc;
	protected String dateDebut;
	protected String dateFin;
	ArrayList<Etiquette> etiquettes;
	Liste carteAppartient;
	ArrayList<Commentaire> commentaires;

	/**
	 * 
	 * @param nom
	 * @param desc
	 * @param dateDebut
	 * @param dateFin
	 */
	public Carte(String nom, String desc, String dateDebut, String dateFin) {
		// TODO - implement Carte.Carte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param etiquette
	 */
	public void addEtiquette(Etiquette etiquette) {
		// TODO - implement Carte.addEtiquette
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param commentaire
	 * @param membre
	 */
	public void addCommentaire(String commentaire, Membre membre) {
		// TODO - implement Carte.addCommentaire
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void delCommentaire(Int id) {
		// TODO - implement Carte.delCommentaire
		throw new UnsupportedOperationException();
	}

	public void getID() {
		// TODO - implement Carte.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nom
	 * @param forme
	 * @param rgb
	 */
	public void addEtiquette(String nom, Short forme, String rgb) {
		// TODO - implement Carte.addEtiquette
		throw new UnsupportedOperationException();
	}

}