package okienkowe_narzedzie_do_modelowania_bazy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainPageController {


    @FXML
    private Pane mainPane;
    private TabelaController tabelaController;

    public void AddTable(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(TabelaController.class.getResource("/fxml/Tabela.fxml"));
        mainPane.getChildren().add(loader.load());
        tabelaController = loader.getController();
        tabelaController.enableDrag();
    }


    public void Exit() {
    System.exit(0);

    }

}
