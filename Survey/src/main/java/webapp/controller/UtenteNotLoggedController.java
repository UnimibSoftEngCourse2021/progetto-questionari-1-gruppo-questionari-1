package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;  

import webapp.model.*;


@Controller
public class UtenteNotLoggedController{

    @Autowired 
    GestoreUtenti gestoreUtenti;
	
//-------------------> start controller LoginView
    @GetMapping("/accedi") // Esegue il login 
    public String makeLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
        if(email != null && password != null){ // Se Username e Password hanno dei valori validi allora richiamo il metodo Login
            boolean res = login(email, password);
            System.out.println("Eseguito il login : " + res);
        }else{ // Se i valori di login non sono validi restituisco un Errore
            System.out.println("Errore nel login Username o Password NULL");
        }

        return "redirect:/";
    }
    
//-------------------> end controller LoginView
    
	@GetMapping(value="/registrazioneUtente")
	public String GestRegistraUtente(@RequestParam("email") String email, 
									 @RequestParam("nome") String nome, 
									 @RequestParam("cognome") String cognome, 
									 @RequestParam("password") String password)
	{
		System.out.println("utente registrato, email:"+ email);
		registrazioneUtente(email, nome, cognome, password);
		
		return "redirect:/";
	}


//-------------------> metodi controller 

    public boolean login(String Email, String Password){
    	boolean ris = gestoreUtenti.login(Email,Password);
        if(ris) {
            return true;
        } else {
            System.out.println("~login non andato a buon fine");
            return false;
        }
    }

    public boolean registrazioneUtente(String email, String nome, String cognome, String password) {
        boolean ris = gestoreUtenti.creaUtente(email, nome, cognome, password);
        if(ris) {
            return true;
        } else {
            System.out.println("~registrazione non andata a buon fine");
            return false;
        }
    }
    
}