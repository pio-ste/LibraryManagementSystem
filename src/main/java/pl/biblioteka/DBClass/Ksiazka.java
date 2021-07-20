package pl.biblioteka.DBClass;


import javafx.beans.property.*;
import pl.biblioteka.Interfejsy.Interfejs_Ksiazek;

import java.time.LocalDate;

public class Ksiazka implements Interfejs_Ksiazek {

    private final IntegerProperty IDksiazki;
    private final StringProperty Autor;
    private final StringProperty Tytul;
    private final StringProperty Wydawnictwo;
    private final StringProperty Kategoria;
    private final StringProperty Jezyk;
    private final ObjectProperty<LocalDate> Data_wydania;

    public Ksiazka(Integer IDksiazki, String Autor, String Tytul, String Wydawnictwo, String Kategoria, String Jezyk, LocalDate Data_wydania) {

        this.IDksiazki = new SimpleIntegerProperty(IDksiazki);
        this.Autor = new SimpleStringProperty(Autor);
        this.Tytul = new SimpleStringProperty(Tytul);
        this.Wydawnictwo = new SimpleStringProperty(Wydawnictwo);
        this.Kategoria = new SimpleStringProperty(Kategoria);
        this.Jezyk = new SimpleStringProperty(Jezyk);
        this.Data_wydania = new SimpleObjectProperty<LocalDate>(Data_wydania);
    }

    public static Ksiazka createKsiazka(KsiazkaMemento memento) {
        return new Ksiazka(memento.getIDksiazki(),memento.getAutor(),memento.getTytul(),memento.getWydawnictwo(),memento.getKategoria(),memento.getJezyk(),memento.getData_wydania());
    }
    //Getters

    public int getIDksiazki() {
        return IDksiazki.get();
    }

    public String getAutor() {
        return Autor.get();
    }

    public String getTytul() {
        return Tytul.get();
    }

    public String getWydawnictwo() {
        return Wydawnictwo.get();
    }

    public String getKategoria() {
        return Kategoria.get();
    }

    public String getJezyk() {
        return Jezyk.get();
    }

    public LocalDate getData_wydania() {
        return Data_wydania.get();
    }

    //Setters

    public void setIDksiazki(Integer value) {
        IDksiazki.set(value);
    }

    public void setAutor(String value) {
        Autor.set(value);
    }

    public void setTytul(String value) {
        Tytul.set(value);
    }

    public void setWydawnictwo(String value) {
        Wydawnictwo.set(value);
    }

    public void setKategoria(String value) {
        Kategoria.set(value);
    }

    public void setJezyk(String value) {
        Jezyk.set(value);
    }

    public void setData_wydania(LocalDate value) {
        Data_wydania.set(value);
    }


    public IntegerProperty IDksiazkiProperty() {
        return IDksiazki;
    }

    public StringProperty AutorProperty() {
        return Autor;
    }

    public StringProperty TytulProperty() {
        return Tytul;
    }

    public StringProperty WydawnictwoProperty() {
        return Wydawnictwo;
    }

    public StringProperty KategoriaProperty() {
        return Kategoria;
    }

    public StringProperty JezykProperty() {
        return Jezyk;
    }

    public ObjectProperty<LocalDate> Data_wydaniaProperty() {
        return Data_wydania;
    }

    public KsiazkaMemento createMemento() {
        return new KsiazkaMemento(IDksiazki,Autor,Tytul,Wydawnictwo,Kategoria,Jezyk,Data_wydania);
    }
}
