package pl.biblioteka.DBClass;

import pl.biblioteka.Interfejsy.Osoby;
import pl.biblioteka.kontrolery.BibliotekarzController;
import pl.biblioteka.kontrolery.KierownikController;
import pl.biblioteka.kontrolery.UserController;

public class Fabryka {

    public Osoby getStanowisko(String stanowisko){
        if (stanowisko == null){
            return null;
        }else if(stanowisko.equals("CZYTELNIK")){
            return new UserController();

        } else if(stanowisko.equals("BIBLIOTEKARZ")){
            return new BibliotekarzController();

        } else if(stanowisko.equals("KIEROWNIK")){
            return new KierownikController();
        }
        return null;
    }
}