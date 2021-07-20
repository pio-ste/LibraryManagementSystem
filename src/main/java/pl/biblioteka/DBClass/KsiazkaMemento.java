package pl.biblioteka.DBClass;

import javafx.beans.property.*;

import java.time.LocalDate;

public class KsiazkaMemento {

    private final IntegerProperty IDksiazki;
    private final StringProperty Autor;
    private final StringProperty Tytul;
    private final StringProperty Wydawnictwo;
    private final StringProperty Kategoria;
    private final StringProperty Jezyk;
    private final ObjectProperty<LocalDate> Data_wydania;

    public KsiazkaMemento(IntegerProperty IDksiazki, StringProperty Autor, StringProperty Tytul, StringProperty Wydawnictwo, StringProperty Kategoria, StringProperty Jezyk, ObjectProperty<LocalDate> Data_wydania) {
        this.IDksiazki = IDksiazki;
        this.Autor = Autor;
        this.Tytul = Tytul;
        this.Wydawnictwo = Wydawnictwo;
        this.Kategoria = Kategoria;
        this.Jezyk = Jezyk;
        this.Data_wydania = Data_wydania;
    }

    public int getIDksiazki() {
        return IDksiazki.get();
    }

    public IntegerProperty IDksiazkiProperty() {
        return IDksiazki;
    }

    public String getAutor() {
        return Autor.get();
    }

    public StringProperty autorProperty() {
        return Autor;
    }

    public String getTytul() {
        return Tytul.get();
    }

    public StringProperty tytulProperty() {
        return Tytul;
    }

    public String getWydawnictwo() {
        return Wydawnictwo.get();
    }

    public StringProperty wydawnictwoProperty() {
        return Wydawnictwo;
    }

    public String getKategoria() {
        return Kategoria.get();
    }

    public StringProperty kategoriaProperty() {
        return Kategoria;
    }

    public String getJezyk() {
        return Jezyk.get();
    }

    public StringProperty jezykProperty() {
        return Jezyk;
    }

    public LocalDate getData_wydania() {
        return Data_wydania.get();
    }

    public ObjectProperty<LocalDate> data_wydaniaProperty() {
        return Data_wydania;
    }
}
