package pl.biblioteka.DBClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AktualneWypozyczeniaUs {
    private final StringProperty Autor;
    private final StringProperty Tytul;
    private final StringProperty Wydawnictwo;
    private final StringProperty NrEgzemplarza;

    public AktualneWypozyczeniaUs(String Autor,String Tytul,String Wydawnictwo, String NrEgzemplarza){
        this.Autor = new SimpleStringProperty(Autor);
        this.Tytul = new SimpleStringProperty(Tytul);
        this.Wydawnictwo =new SimpleStringProperty(Wydawnictwo);
        this.NrEgzemplarza = new SimpleStringProperty(NrEgzemplarza);
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

    public String getNrEgzemplarza() {
        return NrEgzemplarza.get();
    }

    public StringProperty nrEgzemplarzaProperty() {
        return NrEgzemplarza;
    }

    public void setNrEgzemplarza(String nrEgzemplarza) {
        this.NrEgzemplarza.set(nrEgzemplarza);
    }
}
