import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;


/**
 * La classe Clavier représente un clavier personnalisé avec des boutons circulaires
 * disposés sur une grille
 */
public class Clavier extends GridPane {
    
    /**
     * Liste contenant les boutons du clavier
     */
    private List<Button> clavier;
    
    /**
     * Gestionnaire d'événements pour les actions sur les touches du clavier
     */
    private EventHandler<ActionEvent> actionTouches;

    /**
     * Constructeur pour la classe Clavier
     * Crée un clavier avec les touches spécifiées et dispose les boutons dans une grille
     * @param touches chaîne de caractères représentant les touches du clavier
     * @param actionTouches gestionnaire d'événements pour les actions sur les touches
     * @param tailleLigne nombre de boutons par ligne
     */
    public Clavier(String touches, EventHandler<ActionEvent> actionTouches, int tailleLigne) {
        this.actionTouches = actionTouches;

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        this.setHgap(5);
        this.setVgap(5);

        clavier = new ArrayList<>();

        int col = 0;
        int row = 0;

        for (char c : touches.toCharArray()) {
            Button bouton = new Button(Character.toString(c));
            bouton.setOnAction(actionTouches);

            bouton.setMinSize(40, 40);
            bouton.setMaxSize(40, 40);
            bouton.setStyle("-fx-background-radius: 20em;");

            this.add(bouton, col, row);
            clavier.add(bouton);

            col++;
            
            if (col == tailleLigne) {
                col = 0;
                row++;
            }
        }
    }

    /**
     * Réinitialise l'état des touches du clavier en activant tous les boutons
     */
    public void resetTouches() {
        for (Button bouton : clavier) {
            bouton.setDisable(false); 
        }
    }

}