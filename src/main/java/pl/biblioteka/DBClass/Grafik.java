package pl.biblioteka.DBClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Grafik {

    private final ObjectProperty<LocalDate> Pierwszy_dzien;
    private final ObjectProperty<LocalDate> Ostatni_dzien;
    private final IntegerProperty Liczba_godzin;

    public Grafik (LocalDate Pierwszy_dzien, LocalDate Ostatni_dzien, Integer Liczba_godzin){
        this.Pierwszy_dzien = new SimpleObjectProperty<LocalDate>(Pierwszy_dzien);
        this.Ostatni_dzien = new SimpleObjectProperty<LocalDate>(Ostatni_dzien);
        this.Liczba_godzin = new SimpleIntegerProperty(Liczba_godzin);
    }

    public LocalDate getPierwszy_dzien() {
        return Pierwszy_dzien.get();
    }

    public ObjectProperty<LocalDate> pierwszy_dzienProperty() {
        return Pierwszy_dzien;
    }

    public void setPierwszy_dzien(LocalDate pierwszy_dzien) {
        this.Pierwszy_dzien.set(pierwszy_dzien);
    }

    public LocalDate getOstatni_dzien() {
        return Ostatni_dzien.get();
    }

    public ObjectProperty<LocalDate> ostatni_dzienProperty() {
        return Ostatni_dzien;
    }

    public void setOstatni_dzien(LocalDate ostatni_dzien) {
        this.Ostatni_dzien.set(ostatni_dzien);
    }

    public int getLiczba_godzin() {
        return Liczba_godzin.get();
    }

    public IntegerProperty liczba_godzinProperty() {
        return Liczba_godzin;
    }

    public void setLiczba_godzin(int liczba_godzin) {
        this.Liczba_godzin.set(liczba_godzin);
    }
}
