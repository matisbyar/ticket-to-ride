package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.rails.Destinations;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VueCartesWagonJoueur extends VBox {
    public ArrayList<VueCarteWagon> cartesWagon;

    private ScrollPane scrollPane;
    private Label affichage;

    public VueCartesWagonJoueur(List<Destinations> listeCouleurWagon) {
        this.scrollPane = new ScrollPane();
        cartesWagon = new ArrayList<>();
        for (Destinations couleurWagon: listeCouleurWagon) {
            cartesWagon.add(new VueCarteWagon(couleurWagon));
        }

        affichage = new Label("Mes cartes wagons (" + cartesWagon.size() + ")");
        this.getChildren().add(affichage);
        for (VueCarteWagon vueCarte: cartesWagon) {
            this.getChildren().add(vueCarte);
        }
    }

    public ArrayList<VueCarteWagon> getCartesWagon() {
        return cartesWagon;
    }
}
