package pl.biblioteka.kontrolery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import pl.biblioteka.Interfejsy.Osoby;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class KierownikController implements Osoby{

    public MainController mainController;

    @Override
    public void wyswietl() {
        System.out.println("Kierownik");
    }


   /* @FXML
    public void wyswietl_1() throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Kierownik.fxml"));
        Pane pane = loader.load();
        KierownikController kierownikController = loader.getController();
        kierownikController.setMainController(mainController);
        mainController.setScreen(pane);
        System.out.println("xxx");
    }*/

    @FXML
    public void wyloguj()  {
        mainController.loadLoginScreen();
    }  //metoda odpowiedzialna za wylogowanie

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    public void pracownicyKier() {  //metoda ładująca okno do zarządzania pracownikami
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/PracownicyKier.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PracownicyKierController pracownicyKierController = loader.getController();
        pracownicyKierController.setMainController(mainController);
        mainController.setScreen(pane);

    }

    @FXML
    public void grafikKier() {  //metoda ładująca okno do ustalania grafiku
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GrafikKier.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GrafikKierController grafikKierController = loader.getController();
        grafikKierController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void zamowieniaKier() {  //metoda otwierająca okno do składania zamówień
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ZamowieniaKier.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ZamowieniaKierController zamowieniaKierController = loader.getController();
        zamowieniaKierController.setMainController(mainController);
        mainController.setScreen(pane);
    }
}
