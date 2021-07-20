package pl.biblioteka.DBClass;

import javafx.beans.property.*;

import java.time.LocalDate;


public class KsiazkaWypozyczenie {

    private final StringProperty Autor;
    private final StringProperty Tytul;
    private final StringProperty Wydawnictwo;
    private final StringProperty Jezyk;

    public KsiazkaWypozyczenie(String Autor, String Tytul, String Wydawnictwo, String Jezyk){
        this.Autor=new SimpleStringProperty(Autor);
        this.Tytul=new SimpleStringProperty(Tytul);
        this.Wydawnictwo=new SimpleStringProperty(Wydawnictwo);
        this.Jezyk=new SimpleStringProperty(Jezyk);
    }

    public String getAutor() {
        return Autor.get();
    }

    public StringProperty autorProperty() {
        return Autor;
    }

    public void setAutor(String autor) {
        this.Autor.set(autor);
    }

    public String getTytul() {
        return Tytul.get();
    }

    public StringProperty tytulProperty() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        this.Tytul.set(tytul);
    }

    public String getWydawnictwo() {
        return Wydawnictwo.get();
    }

    public StringProperty wydawnictwoProperty() {
        return Wydawnictwo;
    }

    public void setWydawnictwo(String wydawnictwo) {
        this.Wydawnictwo.set(wydawnictwo);
    }

    public String getJezyk() {
        return Jezyk.get();
    }

    public StringProperty jezykProperty() {
        return Jezyk;
    }

    public void setJezyk(String jezyk) {
        this.Jezyk.set(jezyk);
    }
}

