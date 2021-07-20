package pl.biblioteka.kontrolery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class OplatyUsController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    public void odswiezOplatyUs() {
    }

    @FXML
    public void powrotUs() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/User.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserController userController = loader.getController();
        userController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void wykonajPrzelew() {
    }
}
