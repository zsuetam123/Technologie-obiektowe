package okienkowe_narzedzie_do_modelowania_bazy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TabelaController {

    @FXML
    public VBox kolumnyVbox;
    @FXML
    private VBox tabela;
    @FXML
    private ComboBox comboBox1;
    @FXML
    private ComboBox comboBox2;
    @FXML
    private ComboBox connectionTypeComboBox;
    private Integer numerKolumny = 2;
    private double x, y;

    public void initialize() {
        comboBox1.getItems().removeAll(comboBox1.getItems());
        comboBox1.getItems().addAll("INTEGER", "VARCHAR", "DATE");
        comboBox1.getSelectionModel().select("Typ");

        comboBox2.getItems().removeAll(comboBox2.getItems());
        comboBox2.getItems().addAll("INTEGER", "VARCHAR", "DATE");
        comboBox2.getSelectionModel().select("Typ");

        connectionTypeComboBox.getItems().removeAll(connectionTypeComboBox.getItems());
        connectionTypeComboBox.getItems().addAll("∞", "1");
        connectionTypeComboBox.getSelectionModel().select("");
    }

        public void enableDrag () {

            tabela.setLayoutX(500);
            tabela.setLayoutY(500);

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

    public void addColumn(ActionEvent actionEvent) {

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);

            Separator separator = new Separator();

            Label label = new Label();
            numerKolumny+=1;
            label.setText(numerKolumny.toString() + ".");

            TextField textField = new TextField();
            textField.setFocusTraversable(false);
            textField.setPromptText("Nazwa kolumny");
            textField.setAlignment(Pos.CENTER);

            ComboBox comboBoxGeneric= new ComboBox();
            comboBoxGeneric.getItems().removeAll(comboBox1.getItems());
            comboBoxGeneric.getItems().addAll("INTEGER", "VARCHAR", "DATE");
            comboBoxGeneric.getSelectionModel().select("Typ");
            comboBoxGeneric.setFocusTraversable(false);

            hBox.getChildren().add(label);
            hBox.getChildren().add(textField);
            hBox.getChildren().add(comboBoxGeneric);

            kolumnyVbox.getChildren().add(hBox);
            kolumnyVbox.getChildren().add(separator);

    }
}
