package fr.umontpellier.iut;

import fr.umontpellier.iut.rails.Joueur;
import fr.umontpellier.iut.rails.Ville;
import javafx.beans.property.ObjectProperty;

public interface IRoute {
    ObjectProperty<Joueur> proprietaireProperty();
    String getNom();
    Ville getVille1();
    Ville getVille2();
}