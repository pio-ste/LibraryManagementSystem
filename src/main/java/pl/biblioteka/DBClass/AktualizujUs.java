package pl.biblioteka.DBClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AktualizujUs {

    private final StringProperty Imie;
    private final StringProperty Nazwisko;
    private final StringProperty telefon;
    private final StringProperty email;
    private final StringProperty haslo;

    public AktualizujUs(String Imie, String Nazwisko, String telefon, String email, String haslo){
        this.Imie=new SimpleStringProperty(Imie);
        this.Nazwisko=new SimpleStringProperty(Nazwisko);
        this.telefon=new SimpleStringProperty(telefon);
        this.email=new SimpleStringProperty(email);
        this.haslo=new SimpleStringProperty(haslo);
    }

    public String getImie() {
        return Imie.get();
    }

    public StringProperty imieProperty() {
        return Imie;
    }

    public void setImie(String imie) {
        this.Imie.set(imie);
    }

    public String getNazwisko() {
        return Nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.Nazwisko.set(nazwisko);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public StringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getHaslo() {
        return haslo.get();
    }

    public StringProperty hasloProperty() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo.set(haslo);
    }
}
