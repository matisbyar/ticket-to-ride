package fr.umontpellier.iut.vues.VuesElementsJoueur;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Joueur;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends VBox {

    public VueAutresJoueurs(IJeu jeu, List<Joueur> listeJoueurs) {
        for (Joueur joueur : listeJoueurs) getChildren().add(new VueAutresJoueursMini(jeu, joueur));

        this.setSpacing(20.0);
        this.setPadding(new Insets(20.0));
    }

    public void miseAJourInfosJoueurs() {
        for (Node joueur: getChildren()) {
            ((VueAutresJoueursMini) joueur).miseAJourInfos();
        }
    }
}
