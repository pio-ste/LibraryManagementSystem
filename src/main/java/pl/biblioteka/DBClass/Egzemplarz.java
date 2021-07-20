package pl.biblioteka.DBClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Egzemplarz {


    private final IntegerProperty idEgzemplarz;
    private final StringProperty Numer;
    private final StringProperty Stan;
    private final StringProperty Rodzaj;
    private final IntegerProperty Ksiazka_idKsiazka;

    public Egzemplarz(Integer idEgzemplarz, String Numer, String Stan, String Rodzaj, Integer Ksiazka_idKsiazka) //Knstruktor
    {
        this.idEgzemplarz = new SimpleIntegerProperty(idEgzemplarz);
        this.Numer = new SimpleStringProperty(Numer);
        this.Stan = new SimpleStringProperty(Stan);
        this.Rodzaj = new SimpleStringProperty(Rodzaj);
        this.Ksiazka_idKsiazka = new SimpleIntegerProperty(Ksiazka_idKsiazka);
    }
        //Gettery settery
    public int getIdEgzemplarz() {
        return idEgzemplarz.get();
    }

    public IntegerProperty idEgzemplarzProperty() {
        return idEgzemplarz;
    }

    public void setIdEgzemplarz(int idEgzemplarz) {
        this.idEgzemplarz.set(idEgzemplarz);
    }

    public String getNumer() {
        return Numer.get();
    }

    public StringProperty numerProperty() {
        return Numer;
    }

    public void setNumer(String numer) {
        this.Numer.set(numer);
    }

    public String getStan() {
        return Stan.get();
    }

    public StringProperty stanProperty() {
        return Stan;
    }

    public void setStan(String stan) {
        this.Stan.set(stan);
    }

    public String getRodzaj() {
        return Rodzaj.get();
    }

    public StringProperty rodzajProperty() {
        return Rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.Rodzaj.set(rodzaj);
    }

    public int getKsiazka_idKsiazka() {
        return Ksiazka_idKsiazka.get();
    }

    public IntegerProperty ksiazka_idKsiazkaProperty() {
        return Ksiazka_idKsiazka;
    }

    public void setKsiazka_idKsiazka(int ksiazka_idKsiazka) {
        this.Ksiazka_idKsiazka.set(ksiazka_idKsiazka);
    }
}
