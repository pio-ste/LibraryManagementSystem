package pl.biblioteka.DBClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Zamowienia {

    private final IntegerProperty idZamowienia;
    private final StringProperty Nazwa_zamowienia;
    private final IntegerProperty Ilosc;
    private final StringProperty Cena;

    public Zamowienia(Integer idZamowienia, String Nazwa_zamowienia, Integer Ilosc, String Cena)
    {
        this.idZamowienia = new SimpleIntegerProperty(idZamowienia);
        this.Nazwa_zamowienia = new SimpleStringProperty(Nazwa_zamowienia);
        this.Ilosc = new SimpleIntegerProperty(Ilosc);
        this.Cena = new SimpleStringProperty(Cena);
    }

    public int getIdZamowienia() {
        return idZamowienia.get();
    }

    public IntegerProperty idZamowieniaProperty() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia.set(idZamowienia);
    }

    public String getNazwa_zamowienia() {
        return Nazwa_zamowienia.get();
    }

    public StringProperty nazwa_zamowieniaProperty() {
        return Nazwa_zamowienia;
    }

    public void setNazwa_zamowienia(String nazwa_zamowienia) {
        this.Nazwa_zamowienia.set(nazwa_zamowienia);
    }

    public int getIlosc() {
        return Ilosc.get();
    }

    public IntegerProperty iloscProperty() {
        return Ilosc;
    }

    public void setIlosc(int ilosc) {
        this.Ilosc.set(ilosc);
    }

    public String getCena() {
        return Cena.get();
    }

    public StringProperty cenaProperty() {
        return Cena;
    }

    public void setCena(String cena) {
        this.Cena.set(cena);
    }
}
