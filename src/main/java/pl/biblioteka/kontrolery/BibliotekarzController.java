package pl.biblioteka.kontrolery;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import pl.biblioteka.Interfejsy.Osoby;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class BibliotekarzController implements Osoby{


    public MainController mainController;

    @Override
    public void wyswietl() {
        System.out.println("Bibliotekarz");
    }

    /*@FXML
    public void wyswietl() throws IOException {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Bibliotekarz.fxml"));
    Pane pane = loader.load();
    BibliotekarzController bibliotekarzController = loader.getController();
    bibliotekarzController.setMainController(mainController);
    mainController.setScreen(pane);
    }*/

    @FXML
    public void wyloguj(){  //wylogowanie
        mainController.loadLoginScreen();
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void oplatyBib() {    //ładowanie ekranu do opłat
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/OplatyBib.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OplatyBibController oplatyBibController = loader.getController();
        oplatyBibController.setMainController(mainController);
        mainController.setScreen(pane);
    }


    @FXML
    public void listaksiazekBib() {  //ładowanie okna z listą ksiązek
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ListaKsiazekBib.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListaKsiazekBibController listaKsiazekBibController = loader.getController();
        listaKsiazekBibController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void czytelnicyBib() { //ładowanie okna z listą użytkowników
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CzytelnicyBib.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CzytelnicyBibController czytelnicyBibController = loader.getController();
        czytelnicyBibController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void wypozyczeniaBib() {  //ładowanie okna z listą wypożyczeń
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/WypozyczeniaBib.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WypozyczeniaBibController wypozyczeniaBibController = loader.getController();
        wypozyczeniaBibController.setMainController(mainController);
        mainController.setScreen(pane);

    }

    @FXML
    public void powiadomieniaBib() {
    }
}
