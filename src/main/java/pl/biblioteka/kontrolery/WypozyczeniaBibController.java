package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.Egzemplarz;
import pl.biblioteka.DBClass.Ksiazka;
import pl.biblioteka.DBClass.WypozycznieBib;
import pl.biblioteka.DBClass.Zwrot;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class WypozyczeniaBibController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    private TextField NrKarty;
    @FXML
    private TextField NrEgzemplarza;
    @FXML
    private TextField idKsiazkiText;
    @FXML
    private TextField AutorText;
    @FXML
    private TextField TytulText;
    @FXML
    private TextField WydawnictwoText;
    @FXML
    private TextField KategoriaText;
    @FXML
    private TextField idKsiazkiText2;
    @FXML
    private DatePicker DataWypZwr;

    @FXML
    private TableView<Ksiazka> KsiazkaTable;
    @FXML
    private TableColumn<Ksiazka,Integer> idKsiazkiCol;
    @FXML
    private TableColumn<Ksiazka,String> AutorCol;
    @FXML
    private TableColumn<Ksiazka,String> TytulCol;
    @FXML
    private TableColumn<Ksiazka,String> WydawCol;
    @FXML
    private TableColumn<Ksiazka,String> KategCol;
    @FXML
    private TableColumn<Ksiazka,String> JezykCol;
    @FXML
    private TableColumn<Ksiazka,LocalDate> DataWydCol;

    @FXML
    private TableView<Egzemplarz> EgzemplarzeTable;
    @FXML
    private TableColumn<Egzemplarz, Integer> idEgzemplarzaCol;
    @FXML
    private TableColumn<Egzemplarz, String> NumerEgzCol;
    @FXML
    private TableColumn<Egzemplarz, String> StanEgzCol;
    @FXML
    private TableColumn<Egzemplarz, String> RodzajCol;
    @FXML
    private TableColumn<Egzemplarz, Integer> idKsiazkiEgzCol;
    @FXML
    private TableView<WypozycznieBib> WypozyczeniaTable;
    @FXML
    private TableColumn<WypozycznieBib, String> NumerKartCol;
    @FXML
    private TableColumn<WypozycznieBib, LocalDate> DataWypCol;
    @FXML
    private TableColumn<WypozycznieBib, String> NumerEgzCol1;

    private ObservableList<Ksiazka> ksiazkaList;

    private ObservableList<Egzemplarz> egzemplarzList;

    private ObservableList<WypozycznieBib> wypozyczeniaList;

    private ObservableList<Zwrot> zwrotList;

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
    public void wypozyczenie() {
        String insert = "insert into wypozyczenie values(null,?,?,?)";
        String update ="UPDATE egzemplarz SET stan=? WHERE Numer = '"+NrEgzemplarza.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, NrKarty.getText());
            preparedStatement.setString(1, NrEgzemplarza.getText());
            java.util.Date date = java.util.Date.from(DataWypZwr.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate = new Date(date.getTime());
            preparedStatement.setDate(6, sqlDate);
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setString(1,"Zwrocony");
            preparedStatement.executeUpdate();
            preparedStatement1.execute();
            preparedStatement.close();
            preparedStatement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void zwrot() {
        String zwrot = "insert into zwrot values(null,?,?,?)";
        String usun = "delete from wypozycznie where NumerKarty= '"+NrKarty.getText()+"' and NumerEgzemplarza = '"+NrEgzemplarza.getText()+"'";
        String update ="UPDATE egzemplarz SET stan=? WHERE Numer = '"+NrEgzemplarza.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(zwrot);
            preparedStatement.setString(1, NrKarty.getText());
            preparedStatement.setString(2, NrEgzemplarza.getText());
            java.util.Date date = java.util.Date.from(DataWypZwr.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate = new Date(date.getTime());
            preparedStatement.setDate(3, sqlDate);
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setString(1,"Zwrocony");
            PreparedStatement preperedStatement3 = connection.prepareStatement(usun);
            preparedStatement.executeUpdate();
            preparedStatement1.execute();
            preperedStatement3.executeUpdate();
            preparedStatement1.close();
            preperedStatement3.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void szukajKsiazke() {
        ksiazkaList= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM ksiazka where Autor='"+AutorText.getText()+"' or Tytul = '"+TytulText.getText()+"' or Wydawnictwo='"+WydawnictwoText.getText()+"' or Kategoria='"+KategoriaText.getText()+"';");
            while (rs.next()){
                ksiazkaList.add(new Ksiazka(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getDate(7).toLocalDate()));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idKsiazkiCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, Integer>("IDksiazki"));  //wyświetlanie danych w odpwiednich kolumnach
        AutorCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Autor"));
        TytulCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Tytul"));
        WydawCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Wydawnictwo"));
        KategCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Kategoria"));
        JezykCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Jezyk"));
        DataWydCol.setCellValueFactory(cellData -> cellData.getValue().Data_wydaniaProperty());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DataWydCol.setCellFactory(column -> {
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

        KsiazkaTable.setItems(ksiazkaList);
    }

    @FXML
    public void powrotBib() {
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

    public void szukajEgzemplarz(ActionEvent actionEvent) {
        egzemplarzList= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM egzemplarz where Ksiazka_idKsiazka = '"+idKsiazkiText2.getText()+"' ;");
            while (rs.next()){
                egzemplarzList.add(new Egzemplarz(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idEgzemplarzaCol.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Integer>("idEgzemplarz"));  //wyświetlanie danych w odpwiednich kolumnach
        NumerEgzCol.setCellValueFactory(new PropertyValueFactory<Egzemplarz, String>("Numer"));
        StanEgzCol.setCellValueFactory(new PropertyValueFactory<Egzemplarz, String>("Stan"));
        RodzajCol.setCellValueFactory(new PropertyValueFactory<Egzemplarz, String>("Rodzaj"));
        idKsiazkiEgzCol.setCellValueFactory(new PropertyValueFactory<Egzemplarz, Integer>("Ksiazka_idKsiazka"));
        EgzemplarzeTable.setItems(egzemplarzList);
    }

    public void WyswietlWypozyczenia(ActionEvent actionEvent) {
        wypozyczeniaList= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM wypozyczenie where NumerKarty = '"+NrKarty.getText()+"' or NumerEgzemplarza = '"+NrEgzemplarza.getText()+"' ;");
            while (rs.next()){
                wypozyczeniaList.add(new WypozycznieBib(rs.getString(1),rs.getString(2),rs.getDate(3).toLocalDate()));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        NumerKartCol.setCellValueFactory(new PropertyValueFactory<WypozycznieBib, String>("NumerKarty"));  //wyświetlanie danych w odpwiednich kolumnach
        DataWypCol.setCellValueFactory(cellData -> cellData.getValue().data_wypozyczeniaProperty());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DataWypCol.setCellFactory(column -> {
            return new TableCell<WypozycznieBib, LocalDate>(){
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
        NumerEgzCol1.setCellValueFactory(new PropertyValueFactory<WypozycznieBib, String>("NumerEgzemplarza"));
        WypozyczeniaTable.setItems(wypozyczeniaList);
    }

    public void WyswietlWszystko(ActionEvent actionEvent) {
        ksiazkaList= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM ksiazka ;");
                while (rs.next()){
                ksiazkaList.add(new Ksiazka(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getDate(7).toLocalDate()));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idKsiazkiCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, Integer>("IDksiazki"));  //wyświetlanie danych w odpwiednich kolumnach
        AutorCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Autor"));
        TytulCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Tytul"));
        WydawCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Wydawnictwo"));
        KategCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Kategoria"));
        JezykCol.setCellValueFactory(new PropertyValueFactory<Ksiazka, String>("Jezyk"));
        DataWydCol.setCellValueFactory(cellData -> cellData.getValue().Data_wydaniaProperty());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DataWydCol.setCellFactory(column -> {
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

        KsiazkaTable.setItems(ksiazkaList);
    }
}
