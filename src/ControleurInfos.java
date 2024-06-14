import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * Contrôleur à activer lorsque l'on clique sur le bouton info
 */
public class ControleurInfos implements EventHandler<ActionEvent> {

    /**
     * vue du jeu
     **/
    private Pendu vuePendu;

    /**
     * @param p vue du jeu
     */
    public ControleurInfos(Pendu vuePendu) {
        this.vuePendu = vuePendu;
    }

    /**
     * L'action consiste à afficher une fenêtre popup précisant les règles du jeu.
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vuePendu.popUpReglesDuJeu().showAndWait();
    }
    
}