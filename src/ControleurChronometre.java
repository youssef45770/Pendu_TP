import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * Contrôleur du chronomètre
 */
public class ControleurChronometre implements EventHandler<ActionEvent> {

    /**
     * temps enregistré lors du dernier événement
     */
    private long tempsPrec;
    /**
     * temps écoulé depuis le début de la mesure
     */
    private long tempsEcoule;
    /**
     * Vue du chronomètre
     */
    private Chronometre chrono;

    /**
     * Constructeur du contrôleur du chronomètre
     * noter que le modèle du chronomètre est tellement simple
     * qu'il est inclus dans le contrôleur
     * @param chrono Vue du chronomètre
     */
    public ControleurChronometre(Chronometre chrono) {
        this.tempsPrec = -1;
        this.tempsEcoule = 0;
        this.chrono = chrono;
    }

    /**
     * Actions à effectuer à chaque pas de temps
     * essentiellement mesurer le temps écoulé depuis la dernière mesure
     * et mise à jour de la durée totale
     * @param actionEvent événement Action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        long heureDuSysteme = System.currentTimeMillis();
        if (this.tempsPrec != -1) {
            long temps = heureDuSysteme - this.tempsPrec;
            this.tempsEcoule += temps;
            this.chrono.setTime(tempsEcoule);
        }
        this.tempsPrec = heureDuSysteme;
    }
    
    /**
     * Remet la durée à 0
     */
    public void reset() {
        this.tempsEcoule = 0;
        this.tempsPrec = -1;
    }
    
}