import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


/**
 * Controleur du clavier
 */
public class ControleurLettres implements EventHandler<ActionEvent> {

    /**
     * Modèle du jeu
     */
    private MotMystere modelePendu;
    /**
     * Vue du jeu
     */
    private Pendu vuePendu;

    /**
     * Constructeur du contrôleur de lettres
     * @param modelePendu Modèle du jeu
     * @param vuePendu Vue du jeu
     */
    public ControleurLettres(MotMystere modelePendu, Pendu vuePendu) {
        this.modelePendu = modelePendu;
        this.vuePendu = vuePendu;
    }

    /**
     * Actions à effectuer lors du clic sur une touche du clavier
     * Il faut donc: Essayer la lettre, mettre à jour l'affichage et vérifier si la partie est finie
     * @param actionEvent L'événement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            Button bouton = (Button) actionEvent.getSource();
            String lettre = bouton.getText();
            int nouvellesLettres = modelePendu.essaiLettre(lettre.charAt(0));
            vuePendu.majAffichage();

            if (modelePendu.gagne()) {
                Alert alert = vuePendu.popUpMessageGagne();
                alert.showAndWait();
                vuePendu.modeAccueil();
            } else if (modelePendu.perdu()) {
                Alert alert = vuePendu.popUpMessagePerdu();
                alert.showAndWait();
                vuePendu.modeAccueil();
            }
            bouton.setDisable(true);
        }
    }
}