package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.rails.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VueAutresJoueursMini extends Pane {

    @FXML
    private Label nom;

    public VueAutresJoueursMini(Joueur j) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/autresJoueursMini.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nom = new Label();
        nom.setText(j.getNom());
    }
}
