package pl.biblioteka.Interfejsy;

import java.time.LocalDate;

public interface Interfejs_Ksiazek {
    int getIDksiazki();
    String getAutor();
    String getTytul();
    String getWydawnictwo();
    String getKategoria();
    String getJezyk();
    LocalDate getData_wydania();
}
