package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.AktualizujUs;

import javax.xml.soap.Text;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;

public class ProfilUsController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    private TextField imieText;
    @FXML
    private TextField nazwiskoText;
    @FXML
    private TextField telefonText;
    @FXML
    private TextField mailText;
    @FXML
    private TextField noweHasloText;
    @FXML
    private TextField hasloText;
    @FXML
    private TableView<AktualizujUs> daneTable;
    @FXML
    private TableColumn<AktualizujUs,String> imieCol;
    @FXML
    private TableColumn<AktualizujUs,String> nazwiskoCol;
    @FXML
    private TableColumn<AktualizujUs,String> TelefonCol;
    @FXML
    private TableColumn<AktualizujUs,String> mailCol;
    @FXML
    private TableColumn<AktualizujUs,String> hasloCol;

    private ObservableList<AktualizujUs> aktualizujList;

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
    public void aktualizujProfilUs() {
        String update ="UPDATE osoba SET Imie=?, Nazwisko=?, telefon=?,email=?,haslo=? WHERE haslo = '"+hasloText.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setString(1, String.valueOf(imieText));
            preparedStatement1.setString(2, String.valueOf(nazwiskoText));
            preparedStatement1.setString(3, String.valueOf(telefonText));
            preparedStatement1.setString(4, String.valueOf(mailText));
            preparedStatement1.setString(5, String.valueOf(noweHasloText));
            preparedStatement1.execute();
            preparedStatement1.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void odswiezDaneUs() {
        aktualizujList= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT Imie,Nazwisko,telefon,email,haslo FROM osoba where haslo='"+hasloText.getText()+"';");
            while (rs.next()){
                aktualizujList.add(new AktualizujUs(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        imieCol.setCellValueFactory(new PropertyValueFactory<AktualizujUs, String>("Imie"));  //wyświetlanie danych w odpwiednich kolumnach
        nazwiskoCol.setCellValueFactory(new PropertyValueFactory<AktualizujUs, String>("Nazwisko"));
        TelefonCol.setCellValueFactory(new PropertyValueFactory<AktualizujUs, String>("telefon"));
        mailCol.setCellValueFactory(new PropertyValueFactory<AktualizujUs, String>("email"));
        hasloCol.setCellValueFactory(new PropertyValueFactory<AktualizujUs, String>("haslo"));
        daneTable.setItems(aktualizujList);
    }

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
}
