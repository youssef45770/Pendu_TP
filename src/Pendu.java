import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ButtonBar.ButtonData;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;


/**
 * Vue du jeu du pendu
 */
public class Pendu extends Application {

    /**
     * Modèle du jeu
     */
    private MotMystere modelePendu;
    
    /**
     * Liste qui contient les images du jeu
     */
    private ArrayList<Image> lesImages;
    
    /**
     * Liste qui contient les noms des niveaux
     */
    public List<String> niveaux;

    // Les différents contrôles qui seront mis à jour ou consultés pour l'affichage
    /**
     * Le dessin du pendu
     */
    private ImageView dessin;
    
    /**
     * Le mot à trouver avec les lettres déjà trouvées
     */
    private Label motCrypte;
    
    /**
     * La barre de progression qui indique le nombre de tentatives
     */
    private ProgressBar pg;
    
    /**
     * Le clavier qui sera géré par une classe à implémenter
     */
    private Clavier clavier;
    
    /**
     * Le texte qui indique le niveau de difficulté
     */
    private Label leNiveau;
    
    /**
     * Le chronomètre qui sera géré par une classe à implémenter
     */
    private Chronometre chrono;
    
    /**
     * Le panel central qui pourra être modifié selon le mode (accueil ou jeu)
     */
    private BorderPane panelCentral;
    
    /**
     * Le bouton Paramètre / Engrenage
     */
    private Button boutonParametres;
    
    /**
     * Le bouton Accueil / Maison
     */
    private Button boutonMaison;
    
    /**
     * Le bouton Info
     */
    private Button boutonInfo;
    
    /**
     * Le bouton qui permet de lancer ou relancer une partie
     */
    private Button bJouer;

    /**
     * Initialise les attributs (créer le modèle, charge les images, crée le chrono ...)
     */
    @Override
    public void init() {
        this.modelePendu = new MotMystere("/usr/share/dict/french", 3, 10, MotMystere.FACILE, 10);
        this.lesImages = new ArrayList<Image>();
        this.chargerImages("./img");
        this.clavier = new Clavier("ABCDEFGHIJKLMNOPQRSTUVWXYZ-", new ControleurLettres(this.modelePendu, this), 10);
        this.chrono = new Chronometre();
        niveaux = List.of("Facile", "Medium", "Difficile", "Expert");
    }

    /**
     * Crée et retourne le graphe de scène de la vue à partir des méthodes précédentes
     * @return la scène du jeu
     */
    private Scene laScene(){
        BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.titre());
        panelCentral = new BorderPane();
        fenetre.setCenter(this.panelCentral);
        return new Scene(fenetre, 800, 1000);
    }

    /**
     * Crée et retourne le panel contenant le titre du jeu
     * @return le panel du titre
     */
    private BorderPane titre() {
        BorderPane banniere = new BorderPane();
        banniere.setPadding(new Insets(20));

        Label titre = new Label("Jeu du Pendu");
        titre.setFont(Font.font("Arial", 30));

        this.boutonMaison = new Button();
        ImageView maison = new ImageView(new Image("file:img/home.png"));
        maison.setFitHeight(28);
        maison.setFitWidth(28);
        this.boutonMaison.setGraphic(maison);
        this.boutonMaison.setOnAction(new RetourAccueil(this.modelePendu, this));
        Tooltip tooltipMaison = new Tooltip("Accueil");
        Tooltip.install(boutonMaison, tooltipMaison);

        this.boutonParametres = new Button();
        ImageView parametres = new ImageView(new Image("file:img/parametres.png"));
        parametres.setFitHeight(28);
        parametres.setFitWidth(28);
        boutonParametres.setGraphic(parametres);
        this.boutonParametres.setOnAction(new ControleurParametres(this));
        Tooltip tooltipParametres = new Tooltip("Paramètres");
        Tooltip.install(this.boutonParametres, tooltipParametres);

        boutonInfo = new Button();
        ImageView information = new ImageView(new Image("file:img/info.png"));
        information.setFitHeight(28);
        information.setFitWidth(28);
        boutonInfo.setGraphic(information);
        boutonInfo.setOnAction(new ControleurInfos(this));
        Tooltip tooltipInfo = new Tooltip("Informations");
        Tooltip.install(boutonInfo, tooltipInfo);

        HBox boutonsADroite = new HBox();
        boutonsADroite.getChildren().addAll(boutonMaison, boutonParametres, boutonInfo);
        boutonsADroite.setAlignment(Pos.CENTER_RIGHT);
        boutonsADroite.setSpacing(10); 

        banniere.setLeft(titre);
        banniere.setRight(boutonsADroite);

        banniere.setStyle("-fx-background-color: #E6E6FA");

        return banniere;
    }

    /**
     * Crée et retourne le panel du chronomètre
     * @return le panel du chronomètre
     */
    private TitledPane leChrono() {
        TitledPane chronoPane = new TitledPane("Chronomètre", this.chrono);
        chronoPane.setCollapsible(false);
        return chronoPane;
    }

    /**
     * Charge les images à afficher en fonction des erreurs
     * @param repertoire répertoire où se trouvent les images
     */
    private void chargerImages(String repertoire){
        for (int i = 0; i < this.modelePendu.getNbErreursMax() + 1; i++){
            File file = new File(repertoire + "/pendu" + i + ".png");
            System.out.println(file.toURI().toString());
            this.lesImages.add(new Image(file.toURI().toString()));
        }
    }

    /**
     * Passe le jeu en mode accueil
     */
    public void modeAccueil(){
        panelCentral.getChildren().clear();
        boutonMaison.setDisable(true);
        boutonParametres.setDisable(false);

        VBox accueil = new VBox(20);
        accueil.setPadding(new Insets(20));
        bJouer = new Button("Lancer une partie");
        bJouer.setDisable(true);
        bJouer.setOnAction(new ControleurLancerPartie(this.modelePendu, this));

        TitledPane leniveau = new TitledPane();
        VBox lesniveauBox = new VBox();
        ToggleGroup tglgrp = new ToggleGroup();
        lesniveauBox.setSpacing(10); 

        for (String niveau : niveaux) {
            RadioButton rdb = new RadioButton(niveau);
            rdb.setToggleGroup(tglgrp);
            rdb.setOnAction(new ControleurNiveau(this.modelePendu, this));
            lesniveauBox.getChildren().add(rdb);
        }
        
        leniveau.setText("Niveau de difficulté");
        leniveau.setContent(lesniveauBox);
        leniveau.setCollapsible(false);
        accueil.getChildren().addAll(bJouer, leniveau);
        panelCentral.setCenter(accueil); 
    }

    /**
     * Passe le jeu en mode jeu
     */
    public void modeJeu(){
        panelCentral.getChildren().clear();
        boutonMaison.setDisable(false);
        boutonParametres.setDisable(true);

        HBox jeu = new HBox();
        jeu.setPadding(new Insets(20, 0, 0, 50));

        VBox gauche = new VBox();
        gauche.setSpacing(20);
        gauche.setAlignment(Pos.BASELINE_LEFT);

        motCrypte = new Label(this.modelePendu.getMotCrypte());
        motCrypte.setFont(Font.font("Arial", 25));

        HBox lemotCrypte = new HBox();
        lemotCrypte.setAlignment(Pos.CENTER);
        lemotCrypte.getChildren().add(motCrypte);
        
        dessin = new ImageView();
        dessin.setImage(lesImages.get(0));
        dessin.setFitHeight(550);
        dessin.setFitWidth(450);
    
        pg = new ProgressBar(0);
        pg.setPrefWidth(200);

        HBox pgh = new HBox();
        pgh.setAlignment(Pos.CENTER);
        pgh.getChildren().add(pg);

        HBox leclavier = new HBox();
        leclavier.setAlignment(Pos.CENTER);
        leclavier.getChildren().add(clavier);

        VBox droite = new VBox();
        droite.setSpacing(30);
        droite.setPadding(new Insets(0, 0, 0, 50));

        leNiveau = new Label(); 
        leNiveau.setText("Niveau : " + niveaux.get(modelePendu.getNiveau()));
        leNiveau.setFont(Font.font("Arial", 25));

        TitledPane chronoPane = this.leChrono();

        Button nouvMot = new Button("Nouveau mot");
        //nouvMot.setOnAction(new ControleurNouveauMot(this.modelePendu, this)); 

        gauche.getChildren().addAll(lemotCrypte, dessin, pgh, leclavier);
        droite.getChildren().addAll(leNiveau, chronoPane, nouvMot);
        jeu.getChildren().addAll(gauche, droite);
        panelCentral.setCenter(jeu);
    }  
    
    /**
     * Passe le jeu en mode paramètres
     */
    public void modeParametres() {
        panelCentral.getChildren().clear();
        boutonMaison.setDisable(false);
        boutonParametres.setDisable(true);

        Label titrePara = new Label("Aucun paramètre disponible pour le moment");
        titrePara.setStyle("-fx-font-size: 25px;");

        panelCentral.setCenter(titrePara);
    }

    /**
     * Lance une partie
     */
    public void lancePartie(){
        this.modeJeu();
        chrono.resetTime();
        chrono.start();
    }

    /**
     * Rafraîchit l'affichage selon les données du modèle
     */
    public void majAffichage() {
        motCrypte.setText(modelePendu.getMotCrypte());
    
        int nbErreurs = modelePendu.getNbErreursMax() - modelePendu.getNbErreursRestants();
        if (nbErreurs >= 0 && nbErreurs < lesImages.size()) {
            dessin.setImage(lesImages.get(nbErreurs));
        }
    
        double progress = (double) modelePendu.getNbEssais() / modelePendu.getNbErreursMax();
        pg.setProgress(progress);
    
        leNiveau.setText("Niveau : " + niveaux.get(modelePendu.getNiveau()));
    }

    /**
     * Accesseur du chronomètre (pour les contrôleurs du jeu)
     * @return le chronomètre du jeu
     */
    public Chronometre getChrono(){
        return this.chrono;
    }

    /**
     * Active le bouton Jouer
     */
    public void activerbJouer() {
        bJouer.setDisable(false);
    }

    /**
     * Crée une alerte pour indiquer qu'une partie est en cours
     * @return l'alerte de confirmation
     */
    public Alert popUpAttention(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de votre choix ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Attention");
        alert.setTitle("Jeu du pendu");
        return alert;
    }

    /**
     * Crée une alerte pour confirmer le lancement de la partie
     * @return l'alerte de confirmation
     */
    public Alert popUpLancerPartie(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir lancer la partie ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Attention");
        alert.setTitle("Jeu du pendu");
        return alert;
    }
    
    /**
     * Crée une alerte pour afficher les règles du jeu
     * @return l'alerte d'information
     */
    public Alert popUpReglesDuJeu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
        "Bienvenue dans le Jeu du Pendu !\n\n" +
        "1. Devinez le mot en proposant des lettres.\n" +
        "2. Vous avez un nombre limité de tentatives.\n" +
        "3. Chaque mauvaise lettre vous coûte une vie.\n" +
        "4. Si vous perdez toutes vos vies, vous perdez la partie.\n" +
        "5. Trouvez le mot avant d'épuiser vos vies pour gagner.\n\n" +
        "Amusez-vous bien !", ButtonType.OK);
        alert.setHeaderText("Règle du jeu");
        alert.setTitle("Jeu du pendu");
        alert.getDialogPane().setPrefWidth(650); 
        return alert;
    }
    
    /**
     * Crée une alerte pour indiquer que le joueur a gagné
     * @return l'alerte d'information
     */
    public Alert popUpMessageGagne(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bravo ! Vous avez gagné !", ButtonType.OK);
        alert.setHeaderText("Vous avez gagné :)");
        alert.setTitle("Jeu du pendu");
        return alert;
    }
    
    /**
     * Crée une alerte pour indiquer que le joueur a perdu
     * @return l'alerte d'information
     */
    public Alert popUpMessagePerdu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous avez perdu\nLe mot à trouver était " + this.modelePendu.getMotATrouve(), ButtonType.OK);
        alert.setHeaderText("Vous avez perdu :(");
        alert.setTitle("Jeu du pendu");
        return alert;
    }

    /**
     * Crée une alerte pour demander si le joueur veut entrer dans les paramètres
     * @return l'alerte de confirmation
     */
    public Alert popUpMessageEntrerParametre() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous modifier les paramètres de jeu ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Attention");
        alert.setTitle("Jeu du pendu");
        return alert;
    }

    /**
     * Crée le graphe de scène et lance le jeu
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("IUTEAM'S - La plateforme de jeux de l'IUTO");
        stage.setScene(this.laScene());
        this.modeAccueil();
        stage.show();
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    public static void main(String[] args) {
        launch(args);
    }

}