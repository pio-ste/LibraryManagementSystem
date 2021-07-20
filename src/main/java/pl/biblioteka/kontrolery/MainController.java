package pl.biblioteka.kontrolery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    public void initialize()  {
        loadLoginScreen();
    }

    @FXML
    public void loadLoginScreen() {   //ładowanie okna logowania
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Login.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        LoginController loginController = loader.getController();
        loginController.setMainController(this);
        setScreen(pane);
    }


    public void setScreen(Pane pane) {  //metoda czyści poprzednie okno
        mainBorderPane.getChildren().clear();
        mainBorderPane.getChildren().add(pane);
    }

}
