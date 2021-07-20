package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.Czytelnik;
import pl.biblioteka.DBClass.DodajCzytelnik;

import java.io.IOException;
import java.sql.*;

public class CzytelnicyBibController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }
//Deklaracje textfield, tableview orac table column
    @FXML
    private TextField idOsoby;
    @FXML
    private TextField idOsoby1;
    @FXML
    private TextField imieUs;
    @FXML
    private TextField nazwiskoUs;
    @FXML
    private TextField telefonUs;
    @FXML
    private TextField LoginUs;
    @FXML
    private TextField hasloUs;
    @FXML
    private TextField mailUs;
    @FXML
    private TextField numerKartyUs;
    @FXML
    private TableView<DodajCzytelnik> TableAddUser;
    @FXML
    private TableView<Czytelnik> TableAddNrKarty;
    @FXML
    private TableColumn<DodajCzytelnik, Integer> idOsobyCol;
    @FXML
    private TableColumn<Czytelnik, String> imieUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> nazwiskoUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> telefonUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> loginUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> hasloUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> mailUsCol;
    @FXML
    private TableColumn<Czytelnik,String> NumerKartyKol;
    @FXML
    private TableColumn<Czytelnik, Integer> idOsobyCol2;

    private ObservableList<DodajCzytelnik> czytelnikInfo;

    private ObservableList<Czytelnik> nrKarty;

    ConnectionDB connectionDB=new ConnectionDB();  // łączenie z bazą danych
    Connection connection=connectionDB.getConnection();

    public void executeQuery(String query) {
        Connection connection=connectionDB.getConnection();
        Statement st;
        try {
            st=connection.createStatement();
            st.executeUpdate(query);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doadajUs() {  //dodawanie czytelników
        String insertOsoba = "insert into osoba values(null,?,?,?,?,?,?,?)";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertOsoba);
            preparedStatement.setString(1, imieUs.getText());
            preparedStatement.setString(2, nazwiskoUs.getText());
            preparedStatement.setString(3, loginUsCol.getText());
            preparedStatement.setString(4, hasloUs.getText());
            preparedStatement.setString(5, mailUs.getText());
            preparedStatement.setString(6, telefonUs.getText());
            preparedStatement.setString(7,"Czytelnik");
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlUs();

    }

    @FXML
    public void szukajUs() {
        czytelnikInfo= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT idOsoba,Imie,Nazwisko,login,haslo,email,telefon FROM osoba WHERE rodzaj='Czytelnik' AND ( idOsoba = '"+idOsoby1.getText()+"' or Imie = '"+imieUs.getText()+"' or Nazwisko = '"+nazwiskoUs.getText()+"' or login = '"+LoginUs.getText()+"' or haslo = '"+hasloUs.getText()+"' or email = '"+mailUs.getText()+"' or telefon = '"+telefonUs.getText()+"');");
            while (rs.next()){
                czytelnikInfo.add(new DodajCzytelnik(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idOsobyCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, Integer>("idOsoba"));  //wyświetlenie danych w odpowiedniej kolumnie
        imieUsCol.setCellValueFactory(new PropertyValueFactory<Czytelnik, String>("Imie"));
        nazwiskoUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Nazwisko"));
        loginUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Login"));
        hasloUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Haslo"));
        mailUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("email"));
        telefonUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Telefon"));
        TableAddUser.setItems(czytelnikInfo);
    }

    @FXML
    public void powrotBib() {  //powrót do poprzedniego ekranu
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Bibliotekarz.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BibliotekarzController bibliotekarzController = loader.getController();
        bibliotekarzController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void aktualizujUs() { //aktualizowanie danych w bazie danych
        String update = "UPDATE osoba SET Imie=?,Nazwisko=?,login=?,haslo=?,email=?, telefon=? where idOsoba = '"+idOsoby1.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, imieUs.getText());   //pobieranie danych z textfield i wstawianie ich do bazy danych
            preparedStatement.setString(2, nazwiskoUs.getText());
            preparedStatement.setString(3, LoginUs.getText());
            preparedStatement.setString(4, hasloUs.getText());
            preparedStatement.setString(5, mailUs.getText());
            preparedStatement.setString(6, telefonUs.getText());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void usunUSER() {
        String query = "DELETE FROM osoba WHERE idOsoba=?";

        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try
        {
            DodajCzytelnik dodajCzytelnik=(DodajCzytelnik) TableAddUser.getSelectionModel().getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dodajCzytelnik.getIdOsoba());
            preparedStatement.executeUpdate();
            wyswietlUs();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void wyswietlUs() {  //wyświetlanie danych w tabeli
        czytelnikInfo= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT idOsoba,Imie,Nazwisko,login,haslo,email,telefon FROM osoba WHERE rodzaj='Czytelnik';");
            while (rs.next()){
                czytelnikInfo.add(new DodajCzytelnik(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idOsobyCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, Integer>("idOsoba"));  //wyświetlenie danych w odpowiedniej kolumnie
        imieUsCol.setCellValueFactory(new PropertyValueFactory<Czytelnik, String>("Imie"));
        nazwiskoUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Nazwisko"));
        loginUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Login"));
        hasloUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Haslo"));
        mailUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("email"));
        telefonUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Telefon"));
        TableAddUser.setItems(czytelnikInfo);
    }

    public void OnMauseClick() {  //funkcja która po kliknięciu w wiersz tabeli wyświetla dane w textfield
        try
        {
            DodajCzytelnik dodajCzytelnik=(DodajCzytelnik) TableAddUser.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM osoba";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            idOsoby1.setText(String.valueOf(dodajCzytelnik.getIdOsoba()));
            imieUs.setText(String.valueOf(dodajCzytelnik.getImie()));
            nazwiskoUs.setText(dodajCzytelnik.getNazwisko());
            telefonUs.setText(dodajCzytelnik.getTelefon());
            LoginUs.setText(dodajCzytelnik.getLogin());
            hasloUs.setText(dodajCzytelnik.getHaslo());
            mailUs.setText(dodajCzytelnik.getEmail());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doadajKarte(ActionEvent actionEvent) {
        String insertKarta = "insert into czytelnik values(null,?,?)";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertKarta);
            preparedStatement.setString(1, idOsoby.getText());
            preparedStatement.setString(2, numerKartyUs.getText());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aktualizujKarte(ActionEvent actionEvent) {
        String updateKarta = "UPDATE czytelnik SET  Nr_kartyBib=? where Osoba_idOsoba = '"+idOsoby1.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateKarta);
            preparedStatement.setString(1, numerKartyUs.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void usunKarte(ActionEvent actionEvent) {
        String query = "DELETE FROM czytelnik WHERE idOsoba=?";

        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try
        {
            Czytelnik czytelnik=(Czytelnik) TableAddNrKarty.getSelectionModel().getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, czytelnik.getOsoba_idOsoba());
            preparedStatement.executeUpdate();
            wyswietlUs();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void szukajKarte(ActionEvent actionEvent) {
        nrKarty= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT Osoba_idOsoba,Nr_kartyBib FROM czytelnik WHERE Osoba_idOsoba= '"+idOsoby.getText()+"' or Nr_kartyBib='"+numerKartyUs.getText()+"';");
            while (rs.next()){
                nrKarty.add(new Czytelnik(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idOsobyCol2.setCellValueFactory(new PropertyValueFactory<Czytelnik, Integer>("Osoba_idOsoba"));  //wyświetlenie danych w odpowiedniej kolumnie
        NumerKartyKol.setCellValueFactory(new PropertyValueFactory<Czytelnik, String>("Nr_kartyBib"));
        TableAddNrKarty.setItems(nrKarty);
    }

    public void wyswietlKarte(ActionEvent actionEvent) {
        nrKarty= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT Osoba_idOsoba,Nr_kartyBib FROM czytelnik;");
            while (rs.next()){
                nrKarty.add(new Czytelnik(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idOsobyCol2.setCellValueFactory(new PropertyValueFactory<Czytelnik, Integer>("Osoba_idOsoba"));  //wyświetlenie danych w odpowiedniej kolumnie
        NumerKartyKol.setCellValueFactory(new PropertyValueFactory<Czytelnik, String>("Nr_kartyBib"));
        TableAddNrKarty.setItems(nrKarty);
    }

    public void showOnClickKarta(MouseEvent mouseEvent) {
        try
        {
            Czytelnik czytelnik=(Czytelnik) TableAddNrKarty.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM czytelnik";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            idOsoby.setText(String.valueOf(czytelnik.getOsoba_idOsoba()));
            numerKartyUs.setText(String.valueOf(czytelnik.getNr_kartyBib()));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
