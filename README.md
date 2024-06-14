###Jeu du Pendu

Lien git: https://github.com/youssef45770/Pendu_TP.git

##Description

Le "Jeu du Pendu" est une application JavaFX où les joueurs devinent un mot en proposant des lettres. Chaque erreur dessine une partie du pendu, et le but est de trouver le mot avant que le pendu soit complètement dessiné.

Prérequis

Avant de pouvoir lancer l'application, assurez-vous d'avoir la librairie JAVAFX 

Pour compiler :

javac --module-path /chemin/vers/javafx-sdk/lib --add-modules javafx.controls Pendu.java


Pour éxécuter :

java --module-path /chemin/vers/javafx-sdk/lib --add-modules javafx.controls Pendu


Structure du Projet

    Pendu.java: Classe principale qui lance l'application JavaFX.

    MotMystere.java: Modèle qui gère la logique du mot mystère.

    Clavier.java: Classe gérant le clavier virtuel.

    Chronometre.java: Classe gérant le chronomètre du jeu.

    ControleurLettres.java: Classe gérant les actions du joueur (sélection des lettres).

    ControleurNiveau.java: Classe gérant la sélection du niveau de difficulté.

    ControleurLancerPartie.java: Classe gérant le lancement d'une nouvelle partie.

    ControleurParametres.java: Classe gérant l'accès aux paramètres.

    ControleurInfos.java: Classe gérant l'affichage des informations.

    ControleurNouvellePartie: Classe gérant le lancement d'une nouvelle partie.

    Dictionnaire.java : Gestion des mots disponibles pour le jeu.

    ControleurChronometre.java : Contrôleur pour le chronomètre.

    ControleurNouveauMot.java : Contrôleur pour gérer le bouton "Nouveau mot".

    RetourAccueil.java : Contrôleur pour le retour à la page d'accueil

    Dossier img: Contient les images utilisées dans l'application.

    Dossier bin: Contient les fichiers .class

    Dossier doc: Contient la javadoc 