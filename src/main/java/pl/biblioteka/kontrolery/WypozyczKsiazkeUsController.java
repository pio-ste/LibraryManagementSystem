package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.Egzemplarz;
import pl.biblioteka.DBClass.Ksiazka;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WypozyczKsiazkeUsController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

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
    private TextField idEgzemplarz;
    @FXML
    private TextField numerEgzemplarz;
    @FXML
    private TextField rodzajEgzemplarz;
    @FXML
    private TextField idKsiazki2;
    @FXML
    private TextField nrKartyBib;

    @FXML
    private TableView<Ksiazka> TableUs;
    @FXML
    private TableColumn<Ksiazka,Integer> idKsiazkiCol;
    @FXML
    private TableColumn<Ksiazka,String> autorCol;
    @FXML
    private TableColumn<Ksiazka,String> tytulCol;
    @FXML
    private TableColumn<Ksiazka,String> wydCol;
    @FXML
    private TableColumn<Ksiazka,String> katCol;
    @FXML
    private TableColumn<Ksiazka,String> jezykCol;
    @FXML
    private TableColumn<Ksiazka, LocalDate> dataCol;

    @FXML
    private TableView<Egzemplarz> TableEgz;
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

    private ObservableList<Egzemplarz> Egzempl;

    private ObservableList<Ksiazka>data;
    ConnectionDB connectionDB=new ConnectionDB(); //tworzenie połączenia z bazą
    Connection connection=connectionDB.getConnection();

    public void showOnClick() {
        try
        {
            Ksiazka ksiazka=(Ksiazka)TableUs.getSelectionModel().getSelectedItem();
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
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void wyswietlBib() {
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

        TableUs.setItems(data);
    }

    public void showOnClickEgz() {
        try
        {
            Egzemplarz egzemplarz=(Egzemplarz) TableEgz.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM ksiazka";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            idEgzemplarz.setText(String.valueOf(egzemplarz.getIdEgzemplarz()));
            numerEgzemplarz.setText(egzemplarz.getNumer());
            rodzajEgzemplarz.setText(egzemplarz.getRodzaj());
            idKsiazki2.setText(String.valueOf(egzemplarz.getKsiazka_idKsiazka()));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void wypozyczUs() throws SQLException {
        String insert = "insert into wypozyczenie values(null,null,?,?)";;
        String update = "UPDATE egzemplarz SET stan=? WHERE idEgzemplarz = '"+idEgzemplarz.getText()+"'";
        String query = "SELECT idCzytelnik FROM czytelnik WHERE Nr_kartyBib = '"+nrKartyBib.getText()+"'";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        while(resultSet.next()){
            int id = resultSet.getInt(1);
        try{
            PreparedStatement preparedStatement2 = connection.prepareStatement(insert);
            preparedStatement2.setString(1, String.valueOf(id));
            preparedStatement2.setString(2, idEgzemplarz.getText());
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setString(1,"Wypozyczony");
            preparedStatement2.execute();
            preparedStatement1.execute();
            preparedStatement2.close();
            preparedStatement1.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
    }

    public void wyswietlEgzemplarz() {
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
        TableEgz.setItems(Egzempl);
    }
}
