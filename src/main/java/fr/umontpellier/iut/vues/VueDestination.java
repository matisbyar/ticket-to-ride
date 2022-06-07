package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IRoute;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.Locale;

/**
 * Cette classe représente la vue d'une carte Destination.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueDestination extends Button {

    private IDestination destination;
    private Label nom;

    public VueDestination(IDestination destination) {
        this.destination = destination;
        String[] villes = destination.toString().split(" ");
        ImageView image = new ImageView(new Image("file:ressources/images/missions/eu-".concat(villes[0].toLowerCase().concat(villes[1].toLowerCase()).concat(villes[2].toLowerCase())).concat(".png")));
        this.setGraphic(image);
        image.setFitWidth(124);
        image.setFitHeight(80);
        image.setPreserveRatio(true);
        this.setId(destination.getNom());

        this.setOnAction(actionEvent -> {
            System.out.println(destination + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneDestinationAEteChoisie(destination.getNom());
        });
    }

    public VueDestination(String selec) {
        ImageView image = new ImageView(new Image("file:ressources/images/images/destination.jpg"));
        this.setGraphic(image);
        image.setFitWidth(124);
        image.setFitHeight(80);
        image.setPreserveRatio(true);
        this.setId(selec);

        this.setOnAction(actionEvent -> {
            System.out.println("La pioche de destinations a été choisie");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneDestinationAEtePiochee();
        });
    }

    public IDestination getDestination() {
        return destination;
    }

    /**
     * @param route une route
     * @return Renvoie le format de l'image de nommage d'une route
     */
    public String getImageFromDestination(IRoute route) {
        return "eu" + route.getVille1() + "-" + route.getVille2() + ".png";
    }

}
