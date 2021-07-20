package pl.biblioteka.kontrolery;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import pl.biblioteka.Interfejsy.Osoby;

import java.io.IOException;

public class UserController implements Osoby {

    @FXML
    public MainController mainController;

    @Override
    public void wyswietl() {
        System.out.println("Czytelnik");
    }



    /*@FXML
    public void wyswietll()  {
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
    }*/

    @FXML
    public void wyloguj() {
        mainController.loadLoginScreen();
    } //wylogowanie


    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }


    @FXML
    public void wiadomosciUs() {
    }

    @FXML
    public void wypozyczeniaUs() {  //wyświetlenie okna z aktualnymi wypozyczeniami przez czytelnika
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/WypozyczeniaUs.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WypozyczeniaUsController wypozyczeniaUsController = loader.getController();
        wypozyczeniaUsController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void ebookUs() {  //okno z wypozyczonymi ebook'ami
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/EbookUs.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EbookUsController ebookUsController = loader.getController();
        ebookUsController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void oplatyUs() { //wykonywanie opłat
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/OplatyUs.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OplatyUsController oplatyUsController = loader.getController();
        oplatyUsController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void profilUs() {  //otwieranie okna do edycji danych
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ProfilUs.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProfilUsController profilUsController = loader.getController();
        profilUsController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void wypozyczKsiazkeUs() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/WypozyczKsiazkeUS.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WypozyczKsiazkeUsController wypozyczKsiazkeUsController = loader.getController();
        wypozyczKsiazkeUsController.setMainController(mainController);
        mainController.setScreen(pane);
    }
}
