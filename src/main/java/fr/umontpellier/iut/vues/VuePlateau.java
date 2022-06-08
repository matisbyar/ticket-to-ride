package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IRoute;
import fr.umontpellier.iut.IVille;
import fr.umontpellier.iut.rails.Route;
import fr.umontpellier.iut.rails.Ville;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Cette classe présente les routes et les villes sur le plateau.
 *
 * On y définit le listener à exécuter lorsque qu'un élément du plateau a été choisi par l'utilisateur
 * ainsi que les bindings qui mettront ?à jour le plateau après la prise d'une route ou d'une ville par un joueur
 */
public class VuePlateau extends StackPane {

    public VuePlateau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/plateau.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void choixRouteOuVille(MouseEvent event) {
        ((VueDuJeu) getScene().getRoot()).getJeu().uneVilleOuUneRouteAEteChoisie(((Node) event.getSource()).getId());
    }

    @FXML
    ImageView image;
    @FXML
    private Group villes;
    @FXML
    private Group routes;

    public void creerBindings() {
        bindRedimensionPlateau();

        for (Object ville : ((VueDuJeu) getScene().getRoot()).getJeu().getVilles()) {
            for (Node cercle : villes.getChildren()) {
                if (((IVille) ville).getNom().equals(cercle.getId())) {
                    StringBinding proprietaire = new StringBinding() {
                        {
                            this.bind(((IVille) ville).proprietaireProperty());
                        }

                        @Override
                        protected String computeValue() {
                            if (((Ville) ville).getProprietaire() == null) return "Transparent";
                            return VueDuJeu.getCouleurCourante(((IVille) ville).proprietaireProperty().get());
                        }
                    };
                    cercle.styleProperty().bind(Bindings.concat("-fx-fill: ", proprietaire));
                }
            }
        }


        for (Object route : ((VueDuJeu) getScene().getRoot()).getJeu().getRoutes()) {
            for (Node groupe : routes.getChildren()) {
                if (((IRoute) route).getNom().equals(groupe.getId())) {
                    for (Node rect : ((Group) groupe).getChildren()) {
                        StringBinding proprietaire = new StringBinding() {
                            {
                                this.bind(((IRoute) route).proprietaireProperty());
                            }

                            @Override
                            protected String computeValue() {
                                if (((Route) route).getProprietaire() == null) return "Transparent";
                                return VueDuJeu.getCouleurCourante(((IRoute) route).proprietaireProperty().get());
                            }
                        };
                        rect.styleProperty().bind(Bindings.concat("-fx-fill: ", proprietaire));
                    }
                }
            }
        }
    }

    private void bindRedimensionPlateau() {
        bindRoutes();
        bindVilles();
//        Les dimensions de l'image varient avec celle de la scène
        image.fitWidthProperty().bind(getScene().widthProperty());
        image.fitHeightProperty().bind(getScene().heightProperty());

         //image.setVisible(false);
         //this.setStyle("-fx-background-color: purple");
    }

    private void bindRectangle(Rectangle rect, double layoutX, double layoutY) {
//        Liste des propriétés à lier
//        rect.widthProperty()
        rect.widthProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.largeurRectangle * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
//        rect.heightProperty()
        rect.heightProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.hauteurRectangle * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
//        rect.layoutXProperty()
        rect.layoutXProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutX * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
//        rect.xProperty()
        rect.xProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.xInitial * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
//        rect.layoutYProperty()
        rect.layoutYProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutY * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
//        rect.yProperty()
        rect.yProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return DonneesPlateau.yInitial * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
    }

    private void bindRoutes() {
        for (Node nRoute : routes.getChildren()) {
            Group gRoute = (Group) nRoute;
            int numRect = 0;
            for (Node nRect : gRoute.getChildren()) {
                Rectangle rect = (Rectangle) nRect;
                bindRectangle(rect, DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutX(), DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutY());
                numRect++;
            }
        }
    }

    private void bindVilles() {
        for (Node nVille : villes.getChildren()) {
            Circle ville = (Circle) nVille;
            bindVille(ville, DonneesPlateau.getVille(ville.getId()).getLayoutX(), DonneesPlateau.getVille(ville.getId()).getLayoutY());
        }
    }

    private void bindVille(Circle ville, double layoutX, double layoutY) {
        ville.layoutXProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutX * image.getLayoutBounds().getWidth()/ DonneesPlateau.largeurInitialePlateau;
            }
        });
        ville.layoutYProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutY * image.getLayoutBounds().getHeight()/ DonneesPlateau.hauteurInitialePlateau;
            }
        });
        ville.radiusProperty().bind(new DoubleBinding() {
            { super.bind(image.fitWidthProperty(), image.fitHeightProperty());}
            @Override
            protected double computeValue() {
                return DonneesPlateau.rayonInitial * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
    }
}
