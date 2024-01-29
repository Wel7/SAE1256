package modeles;

import java.util.ArrayList;

/**
 * Cette classe représente l'application. Elle gère les tableaux de
 * l'application et permet de les manipuler.
 * 
 * @since 2023-05-18
 * @author Hugo Hergat
 */
public class ModeleApplication implements  java.io.Serializable  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	// Attributs
	/**
	 * Liste des tableaux
	 */
	private ArrayList<ModeleTableau> Tableaux;

	/**
	 * Constructeur de la classe ModeleApplication. Initialise la liste des
	 * tableaux.
	 */
	public ModeleApplication() {
		Tableaux = new ArrayList<ModeleTableau>();
	}

	/**
	 * Ajoute un tableau à l'application.
	 * 
	 * @param tab Le tableau à ajouter.
	 */
	public void ajouterTableau(ModeleTableau tab) {
		Tableaux.add(tab);
	}

	/**
	 * Supprime un tableau de l'application.
	 * 
	 * @param tab Le tableau à ajouter.
	 */
	public void enleverTableau(ModeleTableau tab) {
		Tableaux.remove(tab);
	}

	/**
	 * Archive le tableau spécifié par son index. Le tableau archivé est marqué
	 * comme archivé et son nom est modifié.
	 * 
	 * @param index L'index du tableau à archiver.
	 */
	public void archiverTableau(int index) {
		Tableaux.get(index).setArchiver(true);
		Tableaux.get(index).setNom(Tableaux.get(index).getNom() + " - Archivé");
	}

	/**
	 * Retourne une liste des noms des tableaux de l'application.
	 * 
	 * @return Une liste des noms des tableaux.
	 */
	public ArrayList<String> getNomTableau() {
		ArrayList<String> names = new ArrayList<String>();
		for (ModeleTableau mt : Tableaux) {
			names.add(mt.getNom());
		}
		return names;
	}

	/**
	 * Retourne le tableau correspondant à l'index spécifié. Si l'index est
	 * invalide, retourne le premier tableau de la liste.
	 * 
	 * @param index L'index du tableau à récupérer.
	 * @return Le tableau correspondant à l'index.
	 */
	public ModeleTableau getTableau(int index) {
		try {
			return Tableaux.get(index);
		} catch (Exception e) {
			return Tableaux.get(0);
		}
	}

	/**
	 * Retourne la liste des tableaux de l'application.
	 * 
	 * @return La liste des tableaux de l'application.
	 */
	public ArrayList<ModeleTableau> getTableaux() {
		return Tableaux;
	}

	/**
	 * Modifie la liste des tableaux de l'application.
	 * 
	 * @param tableaux La nouvelle liste des tableaux.
	 */
	public void setTableaux(ArrayList<ModeleTableau> tableaux) {
		Tableaux = tableaux;
	}
}