package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

/**
 * Controlleur est la classe abstraite représentant tous les contrôleurs.
 *
 * @since 2023-05-18
 * @author Hugo HERGAT
 */
public abstract class Controlleur extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8618902266462877432L;

	/**
	 * Méthode abstraite qui définit le comportement lorsqu'une action est
	 * effectuée.
	 *
	 * @param e L'événement d'action.
	 */
	public abstract void actionPerformed(ActionEvent e);

	public boolean verifDate(String date) {
		// Vérifaction que la date de début soit une vrai date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			dateFormat.setLenient(false);
			// On essaie de transformer inputDateDebut en Date, si la transformation réussi,
			// alors inputDateDebut est une vrai date
			dateFormat.parse(date);
		} catch (IllegalArgumentException | ParseException VerifDate) {
			return false;
		}
		return true;
	}

	public boolean verifDate(String dateDebut, String dateFin) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		try {
			return !dateFormat.parse(dateDebut).after(dateFormat.parse(dateFin));
		} catch (ParseException e) {
			return false;
		}
	}
}
