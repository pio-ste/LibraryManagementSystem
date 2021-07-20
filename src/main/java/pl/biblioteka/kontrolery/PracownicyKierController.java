package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.*;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownicyKierController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    private TextField IDOsoby;
    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField telefon;
    @FXML
    private TextField mail;
    @FXML
    private TextField login;
    @FXML
    private TextField haslo;
    @FXML
    private TextField stanowisko;
    @FXML
    private TextField idOsoby;
    @FXML
    private TextField zarobki;

    @FXML
    private TableView<Osoba> TableOsoby;
    @FXML
    private TableColumn<Osoba,Integer> idOsobyCol;
    @FXML
    private TableColumn<Osoba,String> imieCol;
    @FXML
    private TableColumn<Osoba,String> nazwiskoCol;
    @FXML
    private TableColumn<Osoba,String> telefonCol;
    @FXML
    private TableColumn<Osoba,String> mailCol;
    @FXML
    private TableColumn<Osoba,String> loginCol;
    @FXML
    private TableColumn<Osoba,String> hasloCol;
    @FXML
    private TableColumn<Osoba,String> stanowiskoaCol;
    @FXML
    private TableView<Pracownik> TablePracownicy;
    @FXML
    private TableColumn<Pracownik,Integer> idPracowanikaCol;
    @FXML
    private TableColumn<Pracownik,Integer> zarobkiCol;
    @FXML
    private TableColumn<Pracownik,Integer> idOsobyColumn;

    private ObservableList<Osoba> osoba1;

    private ObservableList<Pracownik> pracownik;

    ConnectionDB connectionDB=new ConnectionDB(); //tworzenie połączenia z bazą
    Connection connection=connectionDB.getConnection();

    public void wyswietlOsobe() {
        osoba1= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM osoba Where rodzaj='Bibliotekarz' or rodzaj = 'Kierownik';");
            while (rs.next()){
                osoba1.add(new Osoba(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idOsobyCol.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("idOsoba"));  //wyświetlanie danych w odpwiednich kolumnach
        imieCol.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Imie"));
        nazwiskoCol.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Nazwisko"));
        telefonCol.setCellValueFactory(new PropertyValueFactory<Osoba, String>("telefon"));
        mailCol.setCellValueFactory(new PropertyValueFactory<Osoba, String>("email"));
        loginCol.setCellValueFactory(new PropertyValueFactory<Osoba, String>("login"));
        hasloCol.setCellValueFactory(new PropertyValueFactory<Osoba, String>("haslo"));
        stanowiskoaCol.setCellValueFactory(new PropertyValueFactory<Osoba, String>("rodzaj"));
        TableOsoby.setItems(osoba1);
    }

    public void zatrudnijKier() {
        String insertOsoba = "insert into pracownik values(null,?,null,?,null)";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertOsoba);
            preparedStatement.setString(1, zarobki.getText());
            preparedStatement.setString(2, idOsoby.getText());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void zwolnijKier() {
        String query = "DELETE FROM pracownik WHERE Osoba_idOsoba=?";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try
        {
            Pracownik pracownik=(Pracownik) TablePracownicy.getSelectionModel().getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pracownik.getOsoba_idOsoba());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlPracownika();
    }

    public void aktualDaneKier() {
        String update = "UPDATE pracownik SET Zarobki=? WHERE Osoba_idOsoba = '"+idOsoby.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, zarobki.getText());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void OnMouseClickOsoby(MouseEvent mouseEvent) {
        try
        {
            Osoba osoba=(Osoba) TableOsoby.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM osoba,czytelnik";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            IDOsoby.setText(String.valueOf(osoba.getIdOsoba()));
            imie.setText(String.valueOf(osoba.getImie()));
            nazwisko.setText(osoba.getNazwisko());
            telefon.setText(osoba.getTelefon());
            login.setText(osoba.getLogin());
            haslo.setText(osoba.getHaslo());
            mail.setText(osoba.getEmail());
            stanowisko.setText(osoba.getRodzaj());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dodajOsobe(ActionEvent actionEvent) {
        String insertOsoba = "insert into osoba values(null,?,?,?,?,?,?,?)";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertOsoba);
            preparedStatement.setString(1, imie.getText());
            preparedStatement.setString(2, nazwisko.getText());
            preparedStatement.setString(3, login.getText());
            preparedStatement.setString(4, haslo.getText());
            preparedStatement.setString(5, mail.getText());
            preparedStatement.setString(6, telefon.getText());
            preparedStatement.setString(7,stanowisko.getText());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlOsobe();
    }

    public void usunOsobe(ActionEvent actionEvent) {
        String query = "DELETE FROM osoba WHERE idOsoba=?";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try
        {
            Osoba osoba=(Osoba) TableOsoby.getSelectionModel().getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, osoba.getIdOsoba());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlOsobe();
    }

    public void aktualizujDane(ActionEvent actionEvent) {
        String update = "UPDATE osoba SET Imie=?,Nazwisko=?,login=?,haslo=?,email=?,telefon=?,rodzaj=? WHERE idOsoba = '"+IDOsoby.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, imie.getText());
            preparedStatement.setString(2, nazwisko.getText());
            preparedStatement.setString(3, login.getText());
            preparedStatement.setString(4, haslo.getText());
            preparedStatement.setString(5, mail.getText());
            preparedStatement.setString(6, telefon.getText());
            preparedStatement.setString(7, stanowisko.getText());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void OnMouseClickPrac() {
        try
        {
            Pracownik pracownik=(Pracownik) TablePracownicy.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM pracownik";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            zarobki.setText(String.valueOf(pracownik.getZarobki()));
            idOsoby.setText(String.valueOf(pracownik.getOsoba_idOsoba()));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void wyswietlPracownika() {
        pracownik= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT idPracownik,Zarobki, Osoba_idOsoba FROM pracownik;");
            while (rs.next()){
                pracownik.add(new Pracownik(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idPracowanikaCol.setCellValueFactory(new PropertyValueFactory<Pracownik, Integer>("idPracownik"));  //wyświetlanie danych w odpwiednich kolumnach
        zarobkiCol.setCellValueFactory(new PropertyValueFactory<Pracownik, Integer>("Zarobki"));
        idOsobyColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, Integer>("Osoba_idOsoba"));
        TablePracownicy.setItems(pracownik);
    }

    public void wyswietlDaneKier(MouseEvent mouseEvent) {

    }
}
