package fr.umontpellier.iut.vues.VuesJoueurCourant;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueDestinationsJoueur extends VBox {

    private IJeu jeu;
    private Label texte, vide;
    public ChangeListener<IJoueur> changementJoueur;


    public VueDestinationsJoueur(IJeu jeu) {
        this.jeu = jeu;
        this.texte = new Label();
        this.vide = new Label("Vide");

        this.getStylesheets().add("/css/stylePanneau.css");
        this.getStyleClass().add("panneau");

        creerBindings();
        styliser();

        this.getChildren().addAll(texte, vide);
    }

    public void creerBindings() {
        changementJoueur = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    if (nouveauJoueur.getDestinations().size() != 0) {
                        vide.setManaged(false);
                        vide.setVisible(false);

                        texte.setText("Mes missions (" + nouveauJoueur.getCartesWagon().size() + ")");
                        VueDestinationsJoueur.super.getChildren().clear();
                        VueDestinationsJoueur.super.getChildren().add(texte);
                        for (IDestination carte: nouveauJoueur.getDestinations()) {
                            VueDestinationsJoueur.super.getChildren().add(new Label(carte.getNom()));
                        }
                    } else {
                        texte.setText("Mes missions");
                        vide.setManaged(true);
                        vide.setVisible(true);
                    }

                });
            }
        };
        jeu.joueurCourantProperty().addListener(changementJoueur);
    }

    private void styliser() {
        // Alignement
        this.setAlignment(Pos.TOP_CENTER);

        // Padding
        this.setPadding(new Insets(20));
        this.setSpacing(5);

        // Font size
        texte.setStyle("-fx-font-size: 16px");
        vide.setStyle("-fx-text-fill: #bbbbbb");
    }
}
