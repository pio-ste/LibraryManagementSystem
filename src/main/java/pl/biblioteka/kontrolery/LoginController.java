package pl.biblioteka.kontrolery;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.Fabryka;
import pl.biblioteka.Interfejsy.Osoby;
import pl.biblioteka.Dialogi.Dialogi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController{

    UserController usercontroller = new UserController();
    KierownikController kierownikcontroller = new KierownikController();
    BibliotekarzController bibliotekarzcontroller = new BibliotekarzController();

    public TextField loginUSR;  //deklaracja pola do wpisania loginu
    public PasswordField passwordUSR;  //deklaracja pola do wpisania hasła
    private MainController mainController;

    @FXML
    public void Zaloguj() {
        Fabryka fabryka = new Fabryka();

        ConnectionDB connectionDB=new ConnectionDB();        //Łączenie z bazą danych
        Connection connection=connectionDB.getConnection();


        try {
            String sql = "SELECT login, haslo, rodzaj FROM osoba WHERE login = '" +loginUSR.getText()+ "' AND haslo = '" +passwordUSR.getText()+ "';";   //sprawdzanie czy hasło i login jest w bazie
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();



            if (resultSet.next() ){
                String czytelnik = "SELECT login, haslo, rodzaj FROM osoba WHERE login = '" +loginUSR.getText()+ "' AND haslo = '" +passwordUSR.getText()+ "' AND rodzaj = 'Czytelnik';";   //Sprawdzanie czy podane hasło i login należą do czytelnika
                PreparedStatement preparedStatement2=connection.prepareStatement(czytelnik);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                String bibliotekarz = "SELECT login, haslo, rodzaj FROM osoba WHERE login = '" +loginUSR.getText()+ "' AND haslo = '" +passwordUSR.getText()+ "' AND rodzaj = 'Bibliotekarz';";  //Sprawdzanie czy podane hasło i login należą do bibliotekarza
                PreparedStatement preparedStatement3=connection.prepareStatement(bibliotekarz);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                String kierownik = "SELECT login, haslo, rodzaj FROM osoba WHERE login = '" +loginUSR.getText()+ "' AND haslo = '" +passwordUSR.getText()+ "' AND rodzaj = 'Kierownik';";   //Sprawdzanie czy podane hasło i login należą do kierownika
                PreparedStatement preparedStatement4=connection.prepareStatement(kierownik);
                ResultSet resultSet4 = preparedStatement4.executeQuery();

                if (resultSet2.next()) {
                    Osoby osoba1 = fabryka.getStanowisko("CZYTELNIK");
                    osoba1.wyswietl();
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/User.fxml"));
                    Pane pane = loader.load();
                    UserController userController = loader.getController();
                    userController.setMainController(mainController);
                    mainController.setScreen(pane);

                }if (resultSet3.next()) {
                    Osoby osoba2 = fabryka.getStanowisko("BIBLIOTEKARZ");
                    osoba2.wyswietl();
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Bibliotekarz.fxml"));
                    Pane pane = loader.load();
                    BibliotekarzController bibliotekarzController = loader.getController();
                    bibliotekarzController.setMainController(mainController);
                    mainController.setScreen(pane);


                } if (resultSet4.next()) {
                    Osoby osoba3 = fabryka.getStanowisko("KIEROWNIK");
                    osoba3.wyswietl();
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Kierownik.fxml"));
                    Pane pane = loader.load();
                    KierownikController kierownikController = loader.getController();
                    kierownikController.setMainController(mainController);
                    mainController.setScreen(pane);

            }
            }else {
                System.out.println("2");
                Dialogi.LogowanieAlert();
             }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}


