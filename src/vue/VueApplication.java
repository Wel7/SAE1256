package vue;

import java.awt.BorderLayout;
import javax.swing.*;
import modeles.ModeleApplication;
import vue.style.StyleGeneral;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import modeles.ModeleTableau;

/**
 * Cette classe représente la vue principale de l'application. Elle affiche ce
 * que l'utilisateur voit de l'application.
 *
 * @since 2023-05-18
 * @author Hugo Hergat
 */
public class VueApplication extends JFrame implements StyleGeneral {

	/**
	 * 
	 */
	private static final long serialVersionUID = 520720473986656789L;

	private ModeleApplication modele;
	private VueTableauManager tableauManager;
	private VueTableau tableau;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	/**
	 * Constructeur de la vue principale de l'application.
	 *
	 * @param app Le modèle de l'application.
	 */
	public VueApplication(ModeleApplication modeleApplication) {

		//On essaie de récuper le fichier de sauvegarde
		try {
			final FileInputStream fichier = new FileInputStream("sauvegarde.ser");
			ois = new ObjectInputStream(fichier);
			final ModeleApplication modeleImport = (ModeleApplication) ois.readObject();
			//Si on réussi, on utilise le modele qui était sauvegardé
			this.modele = modeleImport;
			//Sinon, on prends le nouveau modele
		} catch (final java.io.IOException e) {
			//e.printStackTrace();
			modele=modeleApplication;
		} catch (final ClassNotFoundException e) {
			//e.printStackTrace();
			modele=modeleApplication;
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				//ex.printStackTrace();
				modele=modeleApplication;
			}
		}
		
		//Permet de sauvergarder le modèle à la fermeture de l'application
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				try {
					final FileOutputStream fichier = new FileOutputStream("sauvegarde.ser");
					oos = new ObjectOutputStream(fichier);
					oos.writeObject(modele);
				} catch (final java.io.IOException e) {
					//e.printStackTrace();
				} finally {
					try {
						if (oos != null) {
							oos.flush();
							oos.close();
						}
					} catch (final IOException ex) {
						//ex.printStackTrace();
					}
				}

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LONGUEUR, HAUTEUR);
		setResizable(false);
		setTitle("Trello Lite");

		JPanel fenetrePrincipal = new JPanel();
		fenetrePrincipal.setLayout(new BorderLayout());

		ModeleTableau mt = new ModeleTableau("Bonjour !", -1);
		
		tableau = new VueTableau(mt);

		tableauManager = new VueTableauManager(modele, this);

		add(tableau, BorderLayout.CENTER);
		add(tableauManager, BorderLayout.WEST);
		setVisible(true);
	}

	/**
	 * Rafraîchit la vue du tableau avec le modèle de tableau spécifié.
	 *
	 * @param mt Le modèle de tableau à afficher dans la vue.
	 */
	public void rafraichir(ModeleTableau ModeleTableau) {
		tableau.rafraichir(ModeleTableau);
	}

	public void rafraichir(String texte) {
		tableau.rafraichir(texte);
	}
	
	public VueTableauInfo getVueTabInfo() {
		return tableau.getVueTabInfo();
	}
}
