package pl.biblioteka.DBClass;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class WypozycznieBib {

    private final StringProperty NumerKarty;
    private final StringProperty NumerEgzemplarza;
    private final ObjectProperty<LocalDate> Data_wypozyczenia;

    public WypozycznieBib(String NumerKarty, String NumerEgzemlarza, LocalDate Data_wypozyczenia){
        this.NumerKarty=new SimpleStringProperty(NumerKarty);
        this.NumerEgzemplarza= new SimpleStringProperty(NumerEgzemlarza);
        this.Data_wypozyczenia=new SimpleObjectProperty<LocalDate>(Data_wypozyczenia);
    }

    public String getNumerEgzemplarza() {
        return NumerEgzemplarza.get();
    }

    public StringProperty numerEgzemplarzaProperty() {
        return NumerEgzemplarza;
    }

    public void setNumerEgzemplarza(String numerEgzemplarza) {
        this.NumerEgzemplarza.set(numerEgzemplarza);
    }

    public String getNumerKarty() {
        return NumerKarty.get();
    }

    public StringProperty numerKartyProperty() {
        return NumerKarty;
    }

    public void setNumerKarty(String numerKarty) {
        this.NumerKarty.set(numerKarty);
    }

    public LocalDate getData_wypozyczenia() {
        return Data_wypozyczenia.get();
    }

    public ObjectProperty<LocalDate> data_wypozyczeniaProperty() {
        return Data_wypozyczenia;
    }

    public void setData_wypozyczenia(LocalDate data_wypozyczenia) {
        this.Data_wypozyczenia.set(data_wypozyczenia);
    }
}
