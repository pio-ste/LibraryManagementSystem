package pl.biblioteka.DBClass;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Zwrot {

    private final IntegerProperty idZwrot;
    private final ObjectProperty<LocalDate> Data_zwrotu;
    private final StringProperty NumerEgzemplarza;

    public Zwrot (Integer idZwrot, LocalDate Data_zwrotu, String NumerEgzemplarza){
        this.idZwrot = new SimpleIntegerProperty(idZwrot);
        this.Data_zwrotu = new SimpleObjectProperty<LocalDate>(Data_zwrotu);
        this.NumerEgzemplarza = new SimpleStringProperty(NumerEgzemplarza);
    }

    public int getIdZwrot() {
        return idZwrot.get();
    }

    public IntegerProperty idZwrotProperty() {
        return idZwrot;
    }

    public void setIdZwrot(int idZwrot) {
        this.idZwrot.set(idZwrot);
    }

    public LocalDate getData_zwrotu() {
        return Data_zwrotu.get();
    }

    public ObjectProperty<LocalDate> data_zwrotuProperty() {
        return Data_zwrotu;
    }

    public void setData_zwrotu(LocalDate data_zwrotu) {
        this.Data_zwrotu.set(data_zwrotu);
    }

    public String getKara() {
        return NumerEgzemplarza.get();
    }

    public StringProperty karaProperty() {
        return NumerEgzemplarza;
    }

    public void setKara(String kara) {
        this.NumerEgzemplarza.set(kara);
    }
}
