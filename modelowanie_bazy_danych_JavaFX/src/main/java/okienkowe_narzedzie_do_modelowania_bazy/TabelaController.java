package okienkowe_narzedzie_do_modelowania_bazy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Window;

public class TabelaController{

    @FXML
    public VBox kolumnyVbox;
    @FXML
    private VBox tabela;
    @FXML
    private ToggleButton expandConstrainsOptions1;
    @FXML
    private ToggleButton expandConstrainsOptions2;

    private Line line = null;
    private Integer numerKolumny = 2;
    private double x, y;
    private MainPageController mainPageController;
    private boolean lineStart;
    private boolean lineEnd;


    public void initialize() {

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
                if(line != null && lineStart){
                    line.setStartX(mouseEvent.getSceneX() + x);
                    line.setStartY(mouseEvent.getSceneY() + y);
                } else {
                    assert line != null;
                    line.setEndX(mouseEvent.getSceneX() + x);
                    line.setEndY(mouseEvent.getSceneY() + y);
                }
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

    public void addColumn(ActionEvent event) {

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER_LEFT);

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

            ToggleButton expandable = new ToggleButton();
            expandable.setText("...");
            expandable.setFocusTraversable(false);
            expandable.setOnAction(this::expand);

            hBox.getChildren().add(label);
            hBox.getChildren().add(textField);
            hBox.getChildren().add(expandable);
            vBox.getChildren().add(hBox);

            kolumnyVbox.getChildren().add(vBox);
            kolumnyVbox.getChildren().add(separator);

    }

    public void DrawLine(MouseEvent mouseEvent) {

        x = tabela.getLayoutX() - mouseEvent.getSceneX();
        y = tabela.getLayoutY() - mouseEvent.getSceneY();

        if (!mainPageController.drawLine) {
            line = new Line();
            line.setStartX(mouseEvent.getSceneX() + x);
            line.setStartY(mouseEvent.getSceneY() + y);
            line.setEndX(mouseEvent.getSceneX() + x);
            line.setEndY(mouseEvent.getSceneY() + y);
            //line.setStartX(mouseEvent.getSceneX());
            //line.setStartY(mouseEvent.getSceneY());
            mainPageController.mainPane.getChildren().add(line);
            mainPageController.drawLine = true;
            mainPageController.line = line;
            lineStart = true;
        } else {
            line = mainPageController.line;
            line.setEndX(mouseEvent.getSceneX() + x);
            line.setEndY(mouseEvent.getSceneY() + y);
            //line.setEndX(tabela.getLayoutX() - mouseEvent.getSceneX());
            //line.setEndY(tabela.getLayoutY() - mouseEvent.getSceneY());
            //mainPageController.mainPane.getChildren().add(line);
            mainPageController.drawLine = false;
            mainPageController.line = line;
            lineEnd = true;
        }

    }

    public void setMainPageController(MainPageController mainPageController){
        this.mainPageController = mainPageController;
    }

    public void expand(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Node parent = source.getParent().getParent();

        VBox parnt = (VBox) parent;
        System.out.println(parnt);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);

        CheckBox kluczGlowny = new CheckBox();
        kluczGlowny.setText("KG");
        CheckBox kluczObcy = new CheckBox();
        kluczObcy.setText("KO");
        CheckBox unikat = new CheckBox();
        unikat.setText("U");
        CheckBox nieNull = new CheckBox();
        nieNull.setText("NN");

        ComboBox comboBoxGeneric= new ComboBox();
        comboBoxGeneric.getItems().removeAll();
        comboBoxGeneric.getItems().addAll("INTEGER", "VARCHAR", "DATE");
        comboBoxGeneric.getSelectionModel().select("Typ");
        comboBoxGeneric.setFocusTraversable(false);

        Label label = new Label();
        label.setText("   ");

        hBox.getChildren().add(label);
        hBox.getChildren().add(comboBoxGeneric);
        hBox.getChildren().add(kluczGlowny);
        hBox.getChildren().add(kluczObcy);
        hBox.getChildren().add(unikat);
        hBox.getChildren().add(nieNull);
        parnt.getChildren().add(hBox);
    }
}
