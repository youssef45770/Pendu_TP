import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;


/**
 * Controleur des radio boutons gérant le niveau
 */
public class ControleurNiveau implements EventHandler<ActionEvent> {

    /**
     * modèle du jeu
     */
    private MotMystere modelePendu;
    /**
     * vue du jeu
     **/
    private Pendu vuePendu;


    /**
     * @param modelePendu modèle du jeu
     */
    public ControleurNiveau(MotMystere modelePendu, Pendu vuePendu) {
        this.modelePendu = modelePendu;
        this.vuePendu = vuePendu;
    }

    /**
     * gère le changement de niveau
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        RadioButton radiobouton = (RadioButton) actionEvent.getTarget();
        String nomDuRadiobouton = radiobouton.getText();
        System.out.println(nomDuRadiobouton);

        switch (nomDuRadiobouton) {
            case "Facile":
                this.modelePendu.setNiveau(MotMystere.FACILE);
                break;
            case "Medium":
                this.modelePendu.setNiveau(MotMystere.MOYEN);
                break;
            case "Difficile":
                this.modelePendu.setNiveau(MotMystere.DIFFICILE);
                break;
            case "Expert":
                this.modelePendu.setNiveau(MotMystere.EXPERT);
                break;
        }

        this.vuePendu.activerbJouer();
        this.modelePendu.setMotATrouver();
        System.out.println(this.modelePendu.toString());
    }
    
}