import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;


/**
 * Contrôleur permettant de générer un nouveau mot mystère et de relancer une partie.
 */
public class ControleurNouvellePartie implements EventHandler<ActionEvent> {

    /**
     * Référence vers le modèle du jeu du pendu.
     */
    private MotMystere modelePendu;
    /**
     * Référence vers l'instance de la classe principale du jeu du pendu.
     */
    private Pendu vuePendu;

    /**
     * Constructeur du contrôleur.
     * 
     * @param modelePendu Référence vers le modèle du jeu du pendu.
     * @param vuePendu    Référence vers l'instance de la classe principale du jeu du pendu.
     */
    public ControleurNouvellePartie(MotMystere modelePendu, Pendu vuePendu) {
        this.modelePendu = modelePendu;
        this.vuePendu = vuePendu;
    }

    /**
     * Génère un nouveau mot mystère et relance une nouvelle partie.
     * 
     * @param event Événement déclenché par l'action sur le bouton "Nouveau mot".
     */
    @Override
    public void handle(ActionEvent event) {        
        Optional<ButtonType> result = vuePendu.popUpLancerNouvellePartie().showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.YES)) {
            modelePendu.setMotATrouver();
            vuePendu.lancePartie();
        }
    }
    
}