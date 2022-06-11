package fr.umontpellier.iut.vues.VuesJoueurCourant;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.vues.VuesElementsJoueur.VueCarteWagonJoueur;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class VueCartesJoueur extends VBox {

    private IJeu jeu;
    private Label texte;
    public ChangeListener<IJoueur> changementJoueur;

    public VueCartesJoueur(IJeu jeu) {
        this.jeu = jeu;
        this.texte = new Label();

        this.getStylesheets().add("/css/stylePanneau.css");
        this.getStyleClass().add("panneau");

        getChildren().add(texte);

        creerBindings();

        styliser();
    }

    public void creerBindings() {
        changementJoueur = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    texte.setText("Mes cartes wagon (" + nouveauJoueur.getCartesWagon().size() + ")");
                    VueCartesJoueur.super.getChildren().clear();
                    VueCartesJoueur.super.getChildren().add(texte);
                    afficheCartes(nouveauJoueur.getCartesWagon());
                });
            }
        };
        jeu.joueurCourantProperty().addListener(changementJoueur);
    }

    public void afficheCartes(List<CouleurWagon> destinations) {
        // On récupère à l'aide de la méthode compteur(), la Collection Map associant une CouleurWagon et son nombre d'exemplaires
        Map<CouleurWagon, Integer> comptage = CouleurWagon.compteur(destinations);
        // La Set va nous permettre de faire une boucle foreach, et de récupérer individuellement la Key et la Value (cf. notes de commit Affichage cartes wagon)
        Set<Map.Entry<CouleurWagon, Integer>> occurrences = comptage.entrySet();

        for (Map.Entry<CouleurWagon, Integer> occurrence: occurrences) {
            CouleurWagon couleur = occurrence.getKey();
            int quantite = occurrence.getValue();

            if (quantite != 0 && couleur != CouleurWagon.GRIS) this.getChildren().add(new VueCarteWagonJoueur(couleur, quantite));
        }
    }

    private void styliser() {
        // Alignement
        this.setAlignment(Pos.TOP_CENTER);

        // Padding
        this.setPadding(new Insets(5, 5, 20, 20));
        this.setSpacing(5);

        // Font size
        texte.setStyle("-fx-font-size: 16px");
    }
}
