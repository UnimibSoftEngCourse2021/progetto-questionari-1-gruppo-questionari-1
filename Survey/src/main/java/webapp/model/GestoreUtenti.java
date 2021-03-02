
package webapp.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.services.*;

@Service
public class GestoreUtenti {
	
	//@Autowired
    //UserDB userDB;
    UserDataMapper userDataMapper = new UserDataMapper();

    public boolean creaUtente(UtenteRegistrato utente){
        System.out.println("Creazione di un utente...");
        // aggiunta dell'utente al database
        //userDB.insert(utente);
        return userDataMapper.insert(utente);
    }

    public static void getUtente(String email){



    }

    public boolean login(String email, String password) {
        System.out.println("Login di un utente...");
        UtenteRegistrato utenteRegistrato = userDataMapper.find(email);
        System.out.println("E-mail:" + utenteRegistrato.getMail());
        System.out.println("Passwrord:" + utenteRegistrato.getPassword());
        // UtenteRegistrato u = userDB.find(email); // cerca nel database un utente con la stessa mail passata per parametri
        if(utenteRegistrato.getPassword().equals(password)) { // controlla se la password della suddetta mail corrisponde a quella passata via parametri
            return true;
        } else {
            return false;
        }
    }

}