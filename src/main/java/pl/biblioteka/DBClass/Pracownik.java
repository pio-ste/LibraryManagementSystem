package pl.biblioteka.DBClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pracownik {

    private final IntegerProperty idPracownik;
    private final IntegerProperty Zarobki;
    private final IntegerProperty Osoba_idOsoba;

    public Pracownik (Integer idPracownik, Integer Zarobki, Integer Osoba_idOsoba ){
        this.idPracownik = new SimpleIntegerProperty(idPracownik);
        this.Zarobki = new SimpleIntegerProperty(Zarobki);
        this.Osoba_idOsoba = new SimpleIntegerProperty(Osoba_idOsoba);
    }

    public int getIdPracownik() {
        return idPracownik.get();
    }

    public IntegerProperty idPracownikProperty() {
        return idPracownik;
    }

    public void setIdPracownik(int idPracownik) {
        this.idPracownik.set(idPracownik);
    }

    public int getZarobki() {
        return Zarobki.get();
    }

    public IntegerProperty zarobkiProperty() {
        return Zarobki;
    }

    public void setZarobki(int zarobki) {
        this.Zarobki.set(zarobki);
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
}
