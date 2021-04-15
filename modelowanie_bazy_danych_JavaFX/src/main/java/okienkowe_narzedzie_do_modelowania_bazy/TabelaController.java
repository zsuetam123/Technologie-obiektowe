package okienkowe_narzedzie_do_modelowania_bazy;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TabelaController {

    @FXML
    private VBox tabela;

    private double x, y;

        public void enableDrag () {

            tabela.setLayoutX(500);
            tabela.setLayoutY(500);

            ObjectProperty<Point2D> mouseLocation = new SimpleObjectProperty<>();

            tabela.setOnMousePressed(mouseEvent -> {

                x = tabela.getLayoutX() - mouseEvent.getSceneX();
                y = tabela.getLayoutY() - mouseEvent.getSceneY();
                tabela.getScene().setCursor(Cursor.MOVE);
            });

            tabela.setOnMouseReleased(mouseEvent -> tabela.getScene().setCursor(Cursor.HAND));

            tabela.setOnMouseDragged(mouseEvent -> {

                tabela.setLayoutX(mouseEvent.getSceneX() + x);
                tabela.setLayoutY(mouseEvent.getSceneY() + y);
            });

            tabela.setOnMouseEntered(mouseEvent -> {

                if (!mouseEvent.isPrimaryButtonDown()) {
                    tabela.getScene().setCursor(Cursor.HAND);
                }
            });

            tabela.setOnMouseExited(mouseEvent -> {

                if (!mouseEvent.isPrimaryButtonDown()) {
                    tabela.getScene().setCursor(Cursor.DEFAULT);
                }
            });
        }



}
