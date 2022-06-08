package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.vues.VuesHorsJeu.VueRegles;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe correspond à une nouvelle fenêtre permettant de choisir le nombre et les noms des joueurs de la partie.
 *
 * Sa présentation graphique peut automatiquement être actualisée chaque fois que le nombre de joueurs change.
 * Lorsque l'utilisateur a fini de saisir les noms de joueurs, il demandera à démarrer la partie.
 */
public class VueChoixJoueurs extends Stage {

    private ObservableList<String> nomsJoueurs;

    public ObservableList<String> nomsJoueursProperty() {
        return nomsJoueurs;
    }

    public static ArrayList<IJoueur.Couleur> couleursJoueurs;

    @FXML
    private TextField rose;
    @FXML
    private TextField jaune;
    @FXML
    private TextField vert;
    @FXML
    private TextField rouge;
    @FXML
    private TextField bleu;

    @FXML
    private Button regles;
    @FXML
    private Button jouer;

    @FXML
    private Label avertissement;

    public List<String> getNomsJoueurs() {
        return nomsJoueurs;
    }

    public VueChoixJoueurs() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/choixJoueurs.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nomsJoueurs = FXCollections.observableArrayList();
        couleursJoueurs = new ArrayList<>();
    }

    /**
     * Définit l'action à exécuter lorsque la liste des participants est correctement initialisée
     */
    public void setNomsDesJoueursDefinisListener(ListChangeListener<String> quandLesNomsDesJoueursSontDefinis) {
        nomsJoueurs.addListener(quandLesNomsDesJoueursSontDefinis);
    }

    /**
     * Vérifie que tous les noms des participants sont renseignés
     * et affecte la liste définitive des participants
     */
    protected void setListeDesNomsDeJoueurs() {
        ArrayList<String> tempNamesList = new ArrayList<>();
        for (int i = 1; i <= getNombreDeJoueurs(); i++) {
            String name = getJoueurParNumero(i);
            if (name == null || name.equals("")) {
                tempNamesList.clear();
                break;
            } else
                tempNamesList.add(name);
        }
        if (!tempNamesList.isEmpty()) {
            hide();
            nomsJoueurs.clear();
            nomsJoueurs.addAll(tempNamesList);
        }
    }

    /**
     * Retourne le nombre de participants à la partie que l'utilisateur a renseigné
     */
    protected int getNombreDeJoueurs() {
        int nombreDeJoueurs = 0;
        if (!rose.getText().isEmpty()) nombreDeJoueurs++;
        if (!jaune.getText().isEmpty()) nombreDeJoueurs++;
        if (!vert.getText().isEmpty()) nombreDeJoueurs++;
        if (!rouge.getText().isEmpty()) nombreDeJoueurs++;
        if (!bleu.getText().isEmpty()) nombreDeJoueurs++;
        return nombreDeJoueurs;
    }

    /**
     * Retourne le nom que l'utilisateur a renseigné pour le ième participant à la partie
     *
     * @param playerNumber : le numéro du participant
     */
    protected String getJoueurParNumero(int playerNumber) {
        return nomsJoueurs.get(playerNumber - 1);
    }

    @FXML
    public void clickJouer() {
        if (nbLabelVides() <= 2) {
            if (!rose.getText().isEmpty()) {
                couleursJoueurs.add(IJoueur.Couleur.ROSE);
                nomsJoueurs.add(rose.getText());
            }
            if (!jaune.getText().isEmpty()) {
                couleursJoueurs.add(IJoueur.Couleur.JAUNE);
                nomsJoueurs.add(jaune.getText());
            }
            if (!vert.getText().isEmpty()) {
                couleursJoueurs.add(IJoueur.Couleur.VERT);
                nomsJoueurs.add(vert.getText());
            }
            if (!rouge.getText().isEmpty()) {
                couleursJoueurs.add(IJoueur.Couleur.ROUGE);
                nomsJoueurs.add(rouge.getText());
            }
            if (!bleu.getText().isEmpty()) {
                couleursJoueurs.add(IJoueur.Couleur.BLEU);
                nomsJoueurs.add(bleu.getText());
            }
            setListeDesNomsDeJoueurs();
        } else {
            showAvertissement();
        }
    }

    @FXML
    public void clickRegles() {
        VueRegles regles = new VueRegles();
        regles.show();
    }

    /**
     * @return Le nombre de Label non renseignés
     */
    public int nbLabelVides() {
        int nb = 0;
        if (rose.getText().isEmpty()) nb++;
        if (jaune.getText().isEmpty()) nb++;
        if (vert.getText().isEmpty()) nb++;
        if (rouge.getText().isEmpty()) nb++;
        if (bleu.getText().isEmpty()) nb++;
        return nb;
    }

    /**
     * Affiche l'avertissement en cas de non-respect des consignes relatives au nombre minimal de joueurs
     */
    public void showAvertissement() {
        avertissement.setVisible(true);
    }
}
