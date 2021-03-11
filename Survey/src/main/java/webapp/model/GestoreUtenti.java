
package webapp.model;

import org.springframework.stereotype.Service;

import webapp.services.*;

@Service
public class GestoreUtenti {
	
    UserDataMapper userDataMapper = new UserDataMapper();
    
    public boolean creaUtente(String email, String nome, String cognome, String password){
        System.out.println("Creazione di un utente...");
        UtenteRegistrato utente = new UtenteRegistrato(email, nome, cognome, password);
        if (userDataMapper.insert(utente)){
        	return true;
        }
        else return false;
    }

    public boolean login(String email, String password) {
        System.out.println("Login di un utente...");
        UtenteRegistrato utenteRegistrato = userDataMapper.find(email);
        System.out.println("E-mail:" + utenteRegistrato.getMail());
        System.out.println("Passwrord:" + utenteRegistrato.getPassword());
        if(utenteRegistrato.getPassword().equals(password)) { // controlla se la password della suddetta mail corrisponde a quella passata via parametri
        	return true;
        } else {
            return false;
        }
        
    }

    public UtenteRegistrato getUtenteByMail(String mail) {
        return userDataMapper.find(mail);
    }
}