
package webapp.model;

import webapp.services.*;


public class GestoreUtenti {


    public static boolean creaUtete(String nome, String cognome, String email, String password){
        UtenteRegistrato u = new UtenteRegistrato(nome, cognome, email, password);
        UserDataMapper.insert(u);
        // aggiunta dell'utente al database
        return true;

    }

    public static void getUtente(String email){

        // ricerca di un utente nel database in base alla mail e e stampa i dati


    }

    public static boolean login(String email, String password) {

        // ricerca nel databse un untente con stessa mail e password
        return true;
    }
}