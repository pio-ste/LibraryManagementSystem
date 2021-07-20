package pl.biblioteka.Dialogi;

import javafx.scene.control.Alert;

public class Dialogi {

    public static  void LogowanieAlert(){  //metoda odpowiedzialna za stworzenie alertbox przy błędnym wpisaniu danych
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Błąd logowania");
        informationAlert.setHeaderText("Źle wpisane dane.");
        informationAlert.setContentText("Upewnij się żę dobrze wpisałeś login i hasło.");
        informationAlert.showAndWait();
    }

    public static  void UsuwanieKsiazkiAlert(){ //metoda odpowiedzialna za stworzenie alertbox kiedy chcemy usunąć książkę z bazy która jest aktualnie wypożyczona
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Błąd usuwania ksiazki");
        informationAlert.setHeaderText("Nie można usunąć ksiązki do której przypisany jest egzemplarz");
        informationAlert.showAndWait();
    }


}

