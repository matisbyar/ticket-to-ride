package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.rails.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends VBox {

    public VueAutresJoueurs(List<Joueur> listeJoueurs) {
        for (Joueur joueur : listeJoueurs) getChildren().add(new VueAutresJoueursMini(joueur));
    }
}
