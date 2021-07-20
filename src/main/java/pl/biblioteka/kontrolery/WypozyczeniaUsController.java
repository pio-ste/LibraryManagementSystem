package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WypozyczeniaUsController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    private TextField numerKartyText;
    @FXML
    private TextField numerEgzemplarzaText;
    @FXML
    private TableView<WypozyczeniaUs> wypozyczeniaTable;
    @FXML
    private TableColumn<WypozyczeniaUs, LocalDate> dataCol;
    @FXML
    private TableColumn<WypozyczeniaUs,String> numerEgzCol;
    @FXML
    private TableView<KsiazkaWypozyczenie> wypozyczeniaKsiazkiTable;
    @FXML
    private TableColumn<KsiazkaWypozyczenie,String> autorCol;
    @FXML
    private TableColumn<KsiazkaWypozyczenie,String> tytulCol;
    @FXML
    private TableColumn<KsiazkaWypozyczenie,String> wydawnictwoCol;
    @FXML
    private TableColumn<KsiazkaWypozyczenie,String> JezykCol;

    private ObservableList<WypozyczeniaUs> wypozyczeniaList;

    private ObservableList<KsiazkaWypozyczenie> ksiazkaList;

    ConnectionDB connectionDB=new ConnectionDB(); //tworzenie połączenia z bazą
    Connection connection=connectionDB.getConnection();

    @FXML
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

    @FXML
    public void odswiezWypUs() throws SQLException {
        wypozyczeniaList= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT Data_wypozyczenia,NumerEgzemplarza FROM wypozyczenie where NumerKarty = '"+numerKartyText.getText()+"' ;");
            while (rs.next()){
                wypozyczeniaList.add(new WypozyczeniaUs(rs.getDate(1).toLocalDate(),rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        dataCol.setCellValueFactory(cellData -> cellData.getValue().data_wypozyczeniaProperty());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dataCol.setCellFactory(column -> {
            return new TableCell<WypozyczeniaUs, LocalDate>(){
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
        numerEgzCol.setCellValueFactory(new PropertyValueFactory<WypozyczeniaUs, String>("NumerEgzemplarza"));
        wypozyczeniaTable.setItems(wypozyczeniaList);
    }

    public void WyswietlNumerEgz(MouseEvent mouseEvent) {
        try
        {
            WypozyczeniaUs wypozyczeniaUs=(WypozyczeniaUs)wypozyczeniaTable.getSelectionModel().getSelectedItem();
            String query = "SELECT NumerEgzemplarza FROM wypozyczenie";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            numerEgzemplarzaText.setText(String.valueOf(wypozyczeniaUs.getNumerEgzemplarza()));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void znajdzKsiazke(ActionEvent actionEvent) {
            ksiazkaList=FXCollections.observableArrayList();
            try {
                ResultSet rs=connection.createStatement().executeQuery("SELECT Autor,Tytul,Wydawnictwo,Jezyk FROM ksiazka WHERE idKsiazka='1';");
                while (rs.next()){
                    ksiazkaList.add(new KsiazkaWypozyczenie(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4)));
                }
            } catch (SQLException e) {
                System.out.println("Blad" +e);
            }
            autorCol.setCellValueFactory(new PropertyValueFactory<KsiazkaWypozyczenie, String>("Autor"));
            tytulCol.setCellValueFactory(new PropertyValueFactory<KsiazkaWypozyczenie, String>("Tytul"));
            wydawnictwoCol.setCellValueFactory(new PropertyValueFactory<KsiazkaWypozyczenie, String>("Wydawnictwo"));
            JezykCol.setCellValueFactory(new PropertyValueFactory<KsiazkaWypozyczenie, String>("Jezyk"));
            wypozyczeniaKsiazkiTable.setItems(ksiazkaList);
    }
}
