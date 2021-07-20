package pl.biblioteka.DBClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DodajCzytelnik {
    private final IntegerProperty idOsoba;
    private final StringProperty Imie;
    private final StringProperty Nazwisko;
    private final StringProperty login;
    private final StringProperty haslo;
    private final StringProperty email;
    private final StringProperty telefon;
    /*private final StringProperty rodzaj;
    private final IntegerProperty idCzytelnik;
    private final IntegerProperty Osoba_idOsoba;
    private final StringProperty Nr_kartyBib;*/

    public DodajCzytelnik (Integer idOsoba, String Imie, String Nazwisko, String login, String haslo, String email, String telefon){  //Konstruktor
        this.idOsoba = new SimpleIntegerProperty(idOsoba);
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.login = new SimpleStringProperty(login);
        this.haslo = new SimpleStringProperty(haslo);
        this.email = new SimpleStringProperty(email);
        this.telefon = new SimpleStringProperty(telefon);
        /*this.rodzaj = new SimpleStringProperty(rodzaj);
        this.idCzytelnik = new SimpleIntegerProperty(idCzytelnik);
        this.Osoba_idOsoba = new SimpleIntegerProperty(Osoba_idOsoba);
        this.Nr_kartyBib = new SimpleStringProperty(Nr_kartyBib);*/
    }

    public int getIdOsoba() {
        return idOsoba.get();
    }

    public IntegerProperty idOsobaProperty() {
        return idOsoba;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba.set(idOsoba);
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

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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

    /*public String getRodzaj() {
        return rodzaj.get();
    }

    public StringProperty rodzajProperty() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj.set(rodzaj);
    }

    public int getIdCzytelnik() {
        return idCzytelnik.get();
    }

    public IntegerProperty idCzytelnikProperty() {
        return idCzytelnik;
    }

    public void setIdCzytelnik(int idCzytelnik) {
        this.idCzytelnik.set(idCzytelnik);
    }

    public int getOsoba_idOsoba() {
        return Osoba_idOsoba.get();
    }

    public IntegerProperty osoba_idOsobaProperty() {
        return Osoba_idOsoba;
    }

    public void setOsoba_idOsoba(int osoba_idOsoba) {
        this.Osoba_idOsoba.set(osoba_idOsoba);
    }

    public String getNr_kartyBib() {
        return Nr_kartyBib.get();
    }

    public StringProperty nr_kartyBibProperty() {
        return Nr_kartyBib;
    }

    public void setNr_kartyBib(String nr_kartyBib) {
        this.Nr_kartyBib.set(nr_kartyBib);
    }*/
}
