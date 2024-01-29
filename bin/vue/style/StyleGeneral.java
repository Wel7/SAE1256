package vue.style;

import java.awt.Color;
import java.awt.Font;

/**
 * Cette interface contient tout les constants en rapport avec le style de
 * l'application
 * 
 * 
 * 
 * @author Hugo Hergat
 */

public interface StyleGeneral {

	// Constantes

	// Couleurs
	Color COULEUR_FOND = new Color(106, 137, 189);
	Color TEXT_COLOR = new Color(199, 200, 216);
	Color BUTTON_COLOR = new Color( 170, 173, 171);
	Color BUTTON_BORDER_COLOR = new Color(30, 136, 56);
	Color BUTTON_HOVER = new Color(179, 250, 160);

	// Texte
	String FONT_NORMAL = "Serif";
	String FONT_TITRE = "Monospaced";
	int TEXTE_TITRE = 25;
	int TEXTE_SOUS_TITRE = 20;
	int TEXTE_NORMAL = 15;
	int GRAS = Font.BOLD;
	int ITALIQUE = Font.ITALIC;
	int NORMAL = Font.PLAIN;

	// Taille de l'application
	int LONGUEUR = 1200;
	int HAUTEUR = 700;

}
