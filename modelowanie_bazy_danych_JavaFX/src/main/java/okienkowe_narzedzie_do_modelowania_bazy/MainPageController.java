package okienkowe_narzedzie_do_modelowania_bazy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.IOException;

public class MainPageController {

    @FXML
    public Pane mainPane;
    private TabelaController tabelaController;
    public Line line = null;
    public Boolean drawLine = false;

    public void AddTable(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(TabelaController.class.getResource("/fxml/Tabela.fxml"));
        mainPane.getChildren().add(loader.load());
        tabelaController = loader.getController();
        tabelaController.setMainPageController(this);
        tabelaController.enableDrag();
    }

    public void Exit() {
    System.exit(0);

    }

}
