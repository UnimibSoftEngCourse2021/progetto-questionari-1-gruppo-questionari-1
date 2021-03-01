
package webapp.model;

import webapp.services.*;


public class GestoreUtenti {


    public static boolean creaUtente(String nome, String cognome, String email, String password){
        UtenteRegistrato u = new UtenteRegistrato(nome, cognome, email, password);
        UserDB.insert(u);
        // aggiunta dell'utente al database
        return true;

    }

    public static void getUtente(String email){

        // ricerca di un utente nel database in base alla mail e e stampa i dati


    }

    public static boolean login(String email, String password) {
        UtenteRegistrato u = UserDB.find(email); // cerca nel database un utente con la stessa mail passata per parametri
        if(u.getPassword().equals(password)) { // controlla se la password della suddetta mail corrisponde a quella passata via parametri
            return true;
        } else {
            return false;
        }
    }
}