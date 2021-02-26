package webapp.services;

import webapp.model.UtenteRegistrato;

public class UserDataMapper{

    public static UtenteRegistrato find(String email){

        // cercare utente nel database e ritornarne l'istanza

        return new UtenteRegistrato("a", "b", "c", "d"); // ritorna n utente di esempio creato con dati presi casualmente
    }

    public static void insert(UtenteRegistrato u) {
        // inserisce all'interno del database l'utente u
    }

    public static boolean delete(String email) {

        // elimina dal databse l'utente con email passata per parametri

        return true;
    }
     
    public static boolean update(String email, String nome, String cognome, String password) {

        //aggiorna i dati di un utente con i dati passati per parametri

        return true;
    }

}

