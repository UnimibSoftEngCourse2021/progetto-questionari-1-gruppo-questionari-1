
package webapp.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.services.*;

@Service
public class GestoreUtenti {
	
	@Autowired
	UserDB userDB;
	
    public boolean creaUtente(UtenteRegistrato utente){
        System.out.println("sono nel gestore utenti");
        // aggiunta dell'utente al database
        userDB.insert(utente);
        return true;
    }

    public static void getUtente(String email){

        // ricerca di un utente nel database in base alla mail e e stampa i dati


    }

    public boolean login(String email, String password) {
        UtenteRegistrato u = userDB.find(email); // cerca nel database un utente con la stessa mail passata per parametri
        if(u.getPassword().equals(password)) { // controlla se la password della suddetta mail corrisponde a quella passata via parametri
            return true;
        } else {
            return false;
        }
    }
}