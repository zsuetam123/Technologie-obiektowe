package okienkowe_narzedzie_do_modelowania_bazy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RowController {

    @FXML
    public Label numerKolumny;
    private TabelaController tabelaController;
    public Integer colNum;

    public void setColumnNumber(Integer number, TabelaController tabelaController){

        numerKolumny.setText(number.toString() + ".");
        colNum = number;

        this.tabelaController = tabelaController;

    }

    public void expand(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Node parent = source.getParent().getParent();

        VBox parnt = (VBox) parent;

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
        comboBoxGeneric.setMaxSize(90,10);
        comboBoxGeneric.getItems().addAll("BIT","DATE","TIME","TIMESTAMP","DECIMAL", "REAL","DOUBLE PRECISION", "FLOAT", "SMALLINT", "INTEGER", "INTERVAL","CHARACTER","CHARACTER VARYING", "NATIONAL CHARACTER", "NATIONAL CHARACTER VARYING");
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

    public void deleteThisColumn(ActionEvent actionEvent) {

        tabelaController.usunietoKolumne(colNum);
    }

    public void updateColumnNumber(Integer number){

        numerKolumny.setText(number.toString() + ".");
        colNum = number;

    }

}
