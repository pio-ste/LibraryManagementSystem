package pl.biblioteka.kontrolery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ZamowieniaKierController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    public void dodajZamowienie() {
    }

    @FXML
    public void wywswietlZamowienia() {
    }

    @FXML
    public void powrotKier() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Kierownik.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KierownikController kierownikController = loader.getController();
        kierownikController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void usunZamowienie() {
    }
}
