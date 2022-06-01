package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IRoute;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
        String defaut = "C:\\Users\\Erwan\\IdeaProjects\\railsihm\\src\\main\\resources\\images\\cartesWagons\\carte-wagon-";
        //Image source = new Image(defaut.concat(destination.toString().concat(".png")));
        //ImageView image = new ImageView(source);
        //this.setGraphic(image);
        nom = new Label(destination.getNom());
        this.setText(nom.getText());
        this.setId(destination.getNom());

        this.setOnAction(actionEvent -> {
            System.out.println(destination + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneDestinationAEteChoisie(destination.getNom());

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
