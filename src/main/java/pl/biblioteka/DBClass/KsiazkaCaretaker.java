package pl.biblioteka.DBClass;

import java.util.ArrayList;
import java.util.List;

public class KsiazkaCaretaker {

    List<KsiazkaMemento> zapiszKsiake = new ArrayList<>();

    public Ksiazka loadKsiazka() {
        KsiazkaMemento memento = zapiszKsiake.get(zapiszKsiake.size()-1);
        return Ksiazka.createKsiazka(memento);
    }

    public void saveKsiazka(Ksiazka ksiazka) {
        KsiazkaMemento memento = ksiazka.createMemento();
        zapiszKsiake.add(memento);
    }
}
