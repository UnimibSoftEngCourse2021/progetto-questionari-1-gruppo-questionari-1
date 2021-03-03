package webapp.model;

import org.springframework.stereotype.Service;

import webapp.services.*;

@Service
public class GestoreUtenti {

	private UtenteRegistrato utenteLoggato = null;
	
    UserDataMapper userDataMapper = new UserDataMapper();

    public boolean creaUtente(UtenteRegistrato utente){
        System.out.println("Creazione di un utente...");
        if (userDataMapper.insert(utente)){
        	this.utenteLoggato = utente;
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
        	this.utenteLoggato = utenteRegistrato;
        	return true;
        } else {
            return false;
        }

    }

    public String getIdUtente() {
    	return this.utenteLoggato.getMail();
    }

}
