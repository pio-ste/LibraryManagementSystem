package pl.biblioteka.kontrolery;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.Egzemplarz;
import pl.biblioteka.DBClass.Ksiazka;
import pl.biblioteka.DBClass.KsiazkaCaretaker;
import pl.biblioteka.AbstractClass.Abstrakt_Ksiazek;
import pl.biblioteka.Dialogi.Dialogi;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ListaKsiazekBibController extends Abstrakt_Ksiazek {

    KsiazkaCaretaker caretaker = new KsiazkaCaretaker();

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    //Deklaracje textfield, tableview orac table column
    @FXML
    private TextField idKsiazka;
    @FXML
    private TextField autorKsiazka;
    @FXML
    private TextField ksiazkaTytul;
    @FXML
    private TextField ksiazkaWydawnictwo;
    @FXML
    private TextField ksiazkaKategoria;
    @FXML
    private TextField ksiazkaJezyk;
    @FXML
    private DatePicker dataWydania;
    @FXML
    private TextField idEgzemplarz;
    @FXML
    private TextField numerEgzemplarz;
    @FXML
    private TextField stanEgzemplarz;
    @FXML
    private TextField rodzajEgzemplarz;
    @FXML
    private TextField idKsiazki2;
    @FXML
    private TableView<Egzemplarz> TableEgzemplarz;
    @FXML
    private TableColumn<Egzemplarz, Integer> idEgzCol;
    @FXML
    private TableColumn<Egzemplarz, String> numerEgzCol;
    @FXML
    private TableColumn<Egzemplarz, String> stanEgzCol;
    @FXML
    private TableColumn<Egzemplarz, String> rodzajEgzCol;
    @FXML
    private TableColumn<Egzemplarz, Integer> idKsiazkiCol2;
    @FXML
    private TableView<Ksiazka> TableBib;
    @FXML
    private TableColumn<Ksiazka, Integer> idKsiazkiCol;
    @FXML
    private TableColumn<Ksiazka, String> autorCol;
    @FXML
    private TableColumn<Ksiazka, String> tytulCol;
    @FXML
    private TableColumn<Ksiazka, String> wydCol;
    @FXML
    private TableColumn<Ksiazka, String> katCol;
    @FXML
    private TableColumn<Ksiazka, String> jezykCol;
    @FXML
    private TableColumn<Ksiazka, LocalDate> dataCol;

    private IntegerProperty index = new SimpleIntegerProperty();

    private ObservableList<Egzemplarz>Egzempl;

    private ObservableList<Ksiazka>data;
    ConnectionDB connectionDB=new ConnectionDB(); //tworzenie połączenia z bazą
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
    public void dodajksiazke() { //dodawanie ksiązki do bazy danych

        String insert = "insert into ksiazka values(null,?,?,?,?,?,?)";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, autorKsiazka.getText());
            preparedStatement.setString(2, ksiazkaTytul.getText());
            preparedStatement.setString(3, ksiazkaWydawnictwo.getText());
            preparedStatement.setString(4, ksiazkaKategoria.getText());
            preparedStatement.setString(5, ksiazkaJezyk.getText());
            java.util.Date date = java.util.Date.from(dataWydania.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate = new Date(date.getTime());
            preparedStatement.setDate(6, sqlDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlBib();
    }

    @FXML
    public void usunksiazke() {  //usuwanie ksiązki z bazy
        String query = "DELETE FROM ksiazka WHERE idKsiazka=?";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try
        {
            Ksiazka ksiazka=(Ksiazka)TableBib.getSelectionModel().getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ksiazka.getIDksiazki());
            preparedStatement.executeUpdate();
            wyswietlBib();
            preparedStatement.close();
        } catch (SQLException e) {
            Dialogi.UsuwanieKsiazkiAlert();
            e.printStackTrace();
        }
    }

    @FXML
    public void aktualizujKsiazke() {  //aktualizowanie danych książek
        try {
            String update = "UPDATE biblioteka.ksiazka SET Autor=?,Tytul=?,Wydawnictwo=?,Kategoria=?,Jezyk=?, Data_wydania=? WHERE idKsiazka = '"+idKsiazka.getText()+"'";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, autorKsiazka.getText());
            preparedStatement.setString(2, ksiazkaTytul.getText());
            preparedStatement.setString(3, ksiazkaWydawnictwo.getText());
            preparedStatement.setString(4, ksiazkaKategoria.getText());
            preparedStatement.setString(5, ksiazkaJezyk.getText());
            java.util.Date date = java.util.Date.from(dataWydania.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate = new Date(date.getTime());
            preparedStatement.setDate(6, sqlDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlBib();

    }

    @FXML
    public void powrotBib() {  //metoda pozwalająca do powrotu do poprzedniego okna
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

    public void wyswietlBib() {   //wyswietlanie danych w tabeli
        data= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT idKsiazka,Autor,Tytul,Wydawnictwo,Kategoria,Jezyk,Data_wydania FROM biblioteka.ksiazka;");
            while (rs.next()){
                data.add(new Ksiazka(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate()));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idKsiazkiCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, Integer>("IDksiazki"));  //wyświetlanie danych w odpwiednich kolumnach
        autorCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Autor"));
        tytulCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Tytul"));
        wydCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Wydawnictwo"));
        katCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Kategoria"));
        jezykCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Jezyk"));
        dataCol.setCellValueFactory(cellData -> cellData.getValue().Data_wydaniaProperty());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dataCol.setCellFactory(column -> {
            return new TableCell<Ksiazka, LocalDate>(){
                @Override
                protected void updateItem(LocalDate item, boolean empty){
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(formatter.format(item));
                    }
                }
            };
        });
        //TableBib.setItems(null);

        TableBib.setItems(data);
    }

    @FXML
    public void  showOnClick()  //wyświetla z TableView Ksiazki do textField dane po kliknięcie w wiersz w TV
    {
        try
        {
            Ksiazka ksiazka=(Ksiazka)TableBib.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM ksiazka";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            idKsiazka.setText(String.valueOf(ksiazka.getIDksiazki()));
            autorKsiazka.setText(ksiazka.getAutor());
            ksiazkaTytul.setText(ksiazka.getTytul());
            ksiazkaWydawnictwo.setText(ksiazka.getWydawnictwo());
            ksiazkaKategoria.setText(ksiazka.getKategoria());
            ksiazkaJezyk.setText(ksiazka.getJezyk());
            caretaker.saveKsiazka(ksiazka);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cofnijZmiany() {
        caretaker.loadKsiazka();
    }

    public void dodajEgzemplarz() {  //dodaje dane do bazy danych
        String insertEgz = "insert into egzemplarz values(null,?,?,?,?)";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertEgz);
            preparedStatement.setString(1, numerEgzemplarz.getText());
            preparedStatement.setString(2, stanEgzemplarz.getText());
            preparedStatement.setString(3, rodzajEgzemplarz.getText());
            preparedStatement.setString(4, idKsiazki2.getText());
            preparedStatement.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aktualizujEgzemplarz() { //aktualizowanie danych w bazie
        String updateEgz = "UPDATE egzemplarz SET Numer=?, Stan=?, Rodzaj=?, Ksiazka_idKsiazka=? WHERE idEgzemplarz = '"+idEgzemplarz.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateEgz);
            preparedStatement.setString(1, numerEgzemplarz.getText());
            preparedStatement.setString(2, stanEgzemplarz.getText());
            preparedStatement.setString(3, rodzajEgzemplarz.getText());
            preparedStatement.setString(4, idKsiazki2.getText());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlBib();

    }

    public void usunEgzemplarz() { //usuwanie egzemplarza z bazy
        String query = "DELETE FROM egzemplarz WHERE idEgzemplarz=?";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try
        {
            Egzemplarz egzemplarz=(Egzemplarz) TableEgzemplarz.getSelectionModel().getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, egzemplarz.getIdEgzemplarz());
            preparedStatement.executeUpdate();
            wyswietlBib();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cofnijEgzemplarz() {
    }

    public void wyswietlEgzemplarz() {  //wyświtlanie danych w tabeli
        Egzempl= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM biblioteka.egzemplarz;");
            while (rs.next()){
                Egzempl.add(new Egzemplarz(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idEgzCol.setCellValueFactory(new PropertyValueFactory<>("idEgzemplarz"));
        numerEgzCol.setCellValueFactory(new PropertyValueFactory<>("Numer"));
        stanEgzCol.setCellValueFactory(new PropertyValueFactory<>("Stan"));
        rodzajEgzCol.setCellValueFactory(new PropertyValueFactory<>("Rodzaj"));
        idKsiazkiCol2.setCellValueFactory(new PropertyValueFactory<>("Ksiazka_idKsiazka"));
        TableEgzemplarz.setItems(Egzempl);
    }

    public void showOnClickEgz() {  //funkcja która po kliknięciu w wiersz tabeli wyświetla dane w textfield
        try
        {
            Egzemplarz egzemplarz=(Egzemplarz) TableEgzemplarz.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM ksiazka";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            idEgzemplarz.setText(String.valueOf(egzemplarz.getIdEgzemplarz()));
            numerEgzemplarz.setText(egzemplarz.getNumer());
            stanEgzemplarz.setText(egzemplarz.getStan());
            rodzajEgzemplarz.setText(egzemplarz.getRodzaj());
            idKsiazki2.setText(String.valueOf(egzemplarz.getKsiazka_idKsiazka()));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
