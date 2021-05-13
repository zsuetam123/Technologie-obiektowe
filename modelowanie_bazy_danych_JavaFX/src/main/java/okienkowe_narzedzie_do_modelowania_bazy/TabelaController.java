package okienkowe_narzedzie_do_modelowania_bazy;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabelaController{

    @FXML
    public VBox kolumnyVbox;
    @FXML
    public Circle blueCircle;
    @FXML
    public CheckBox CheckBoxDrawLine;
    @FXML
    private VBox tabela;

    private Line line = null;
    private Integer numerKolumny = 0;
    private double x, y;
    private MainPageController mainPageController;
    private boolean lineStart;
    private boolean lineEnd;
    private RowController rowController;
    private List<RowController> listaKolumn = new ArrayList<>();

    public void initialize() throws IOException {

        numerKolumny+=1;
        FXMLLoader loader = new FXMLLoader(TabelaController.class.getResource("/fxml/Row.fxml"));
        kolumnyVbox.getChildren().add(loader.load());
        rowController = loader.getController();
        rowController.setColumnNumber(numerKolumny, this);
        listaKolumn.add(rowController);

        numerKolumny+=1;
        FXMLLoader loader2 = new FXMLLoader(TabelaController.class.getResource("/fxml/Row.fxml"));
        kolumnyVbox.getChildren().add(loader2.load());
        rowController = loader2.getController();
        rowController.setColumnNumber(numerKolumny, this);
        listaKolumn.add(rowController);

    }

    public void enableDrag2 () {

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
                    if(line !=null && lineEnd){
                    line.setEndX(mouseEvent.getSceneX() + x);
                    line.setEndY(mouseEvent.getSceneY() + y);
                }}
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

    public void addColumn(ActionEvent event) throws IOException {

        numerKolumny+=1;
        FXMLLoader loader = new FXMLLoader(TabelaController.class.getResource("/fxml/Row.fxml"));
        kolumnyVbox.getChildren().add(loader.load());
        rowController = loader.getController();
        rowController.setColumnNumber(numerKolumny, this);
        listaKolumn.add(rowController);

    }

    public void DrawLine2(MouseEvent mouseEvent) {

        Bounds bounds = CheckBoxDrawLine.localToScene(CheckBoxDrawLine.getBoundsInLocal());
        System.out.println(bounds);

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

            System.out.println(line);

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

    public void deleteColumn(ActionEvent actionEvent) {

        if(numerKolumny > 0){
        kolumnyVbox.getChildren().remove(numerKolumny-1, numerKolumny);
        listaKolumn.remove(numerKolumny-1);
        numerKolumny-=1;}

    }

    public void usunietoKolumne(Integer colNum){

        if(numerKolumny > 0){
            kolumnyVbox.getChildren().remove(colNum-1, colNum);
            numerKolumny-=1;

            listaKolumn.remove(colNum-1);

            int i = 0;

            for(RowController kolumna : listaKolumn){

                kolumna.updateColumnNumber(++i);

            }

        }

    }

    public void deleteTable(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Node parent = source.getParent().getParent().getParent().getParent();

        tabela.getScene().setCursor(Cursor.DEFAULT);
        mainPageController.mainPane.getChildren().remove(parent);

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

            Bounds bounds = blueCircle.localToScreen(blueCircle.getBoundsInLocal());

            tabela.setLayoutX(mouseEvent.getSceneX() + x);
            tabela.setLayoutY(mouseEvent.getSceneY() + y);
            if(line != null && lineStart){

                line.setStartX(bounds.getMinX()- 177);
                line.setStartY(bounds.getMinY() - 20);

               // line.setStartX(mouseEvent.getSceneX() + x);
               // line.setStartY(mouseEvent.getSceneY() + y);
            } else {
                if(line !=null && lineEnd){

                    line.setEndX(bounds.getMinX()- 177);
                    line.setEndY(bounds.getMinY() - 20);

                 //   line.setEndX(mouseEvent.getSceneX() + x);
                //    line.setEndY(mouseEvent.getSceneY() + y);
                }}
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

    public void drawLine(MouseEvent mouseEvent) {

        Bounds bounds = blueCircle.localToScreen(blueCircle.getBoundsInLocal());

        if (!mainPageController.drawLine) {
            line = new Line();
            mainPageController.mainPane.getChildren().add(line);
            line.toBack();
            line.setStartX(bounds.getMinX()- 177);
            line.setStartY(bounds.getMinY() - 20);
            line.setEndX(bounds.getMinX()- 177);
            line.setEndY(bounds.getMinY() - 20);
            mainPageController.drawLine = true;
            mainPageController.line = line;
            lineStart = true;

        } else {
            line = mainPageController.line;
            line.setEndX(bounds.getMinX()- 177);
            line.setEndY(bounds.getMinY() - 20);
            mainPageController.drawLine = false;
            mainPageController.line = line;
            lineEnd = true;
        }

    }
}
