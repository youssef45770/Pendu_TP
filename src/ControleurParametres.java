import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;


/**
 * Contrôleur à activer lorsque l'on clique sur le bouton Accueil
 */
public class ControleurParametres implements EventHandler<ActionEvent> {

    /**
     * vue du jeu
     **/
    private Pendu vuePendu;

    /**
     * @param vuePendu vue du jeu
     */
    public ControleurParametres(Pendu vuePendu) {
        this.vuePendu = vuePendu;
    }

    /**
     * L'action consiste à retourner sur la page d'accueil. Il faut vérifier qu'il n'y avait pas une partie en cours
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Optional<ButtonType> result = vuePendu.popUpMessageEntrerParametre().showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.YES)) {
            vuePendu.modeParametres();
        }
        else {
            vuePendu.modeAccueil();
        }
    }
    
}