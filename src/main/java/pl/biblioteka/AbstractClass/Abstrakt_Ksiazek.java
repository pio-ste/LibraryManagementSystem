package pl.biblioteka.AbstractClass;

public abstract class Abstrakt_Ksiazek {  // klasa abstrakcyjna chroniąca dostęp do niebezpiecznych metod
    protected abstract void usunksiazke();
    protected abstract void dodajksiazke();
    protected abstract void aktualizujKsiazke();
    protected abstract void dodajEgzemplarz();
    protected abstract void aktualizujEgzemplarz();
    protected abstract void usunEgzemplarz();
}
