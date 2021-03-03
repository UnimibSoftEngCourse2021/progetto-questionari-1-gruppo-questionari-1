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
    @GetMapping("/accedi") // Manages Accedi events
    public String getLoginView() {
            System.out.println("Show Accedi");
            return "/accedi";
    }
   
    @PostMapping("/accedi") // Esegue il login 
    public String makeLogin(@RequestParam("Email") String Username, @RequestParam("Password") String Password) {
        if(Username != null && Password != null){ // Se Username e Password hanno dei valori validi allora richiamo il metodo Login
            boolean res = login(Username, Password);
            System.out.println("Eseguito il login : " + res);
        }else{ // Se i valori di login non sono validi restituisco un Errore
            System.out.println("Errore nel login Username o Password NULL");
        }

        return "redirect:/";
    }
    
//-------------------> end controller LoginView

    @GetMapping(value = "/registrazioneUtente")
	public String RegistraUtente(Model model)
	{
    	System.out.println("Show registrati");
    	UtenteRegistrato utente = new UtenteRegistrato();

		model.addAttribute("Titolo", "Registrati!");
		model.addAttribute("newUtente", utente);

		return "registrazioneUtente";
	}

	@PostMapping(value="/registrazioneUtente")
	public String GestRegistraUtente(@ModelAttribute("newUtente") UtenteRegistrato utente, BindingResult result)
	{
		System.out.println("utente registrato, email:"+ utente.getMail());
		registrazioneUtente(utente);
		
		return "redirect:/";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder)
	{
		binder.setAllowedFields("nome", "cognome", "mail", "password");

	}


//-------------------> metodi controller 

    public boolean login(String Email, String Password){
        return gestoreUtenti.login(Email, Password);
        
    }

    public boolean registrazioneUtente(UtenteRegistrato utente) {
        boolean ris = gestoreUtenti.creaUtente(utente);
        if(ris) {
            return true;
        } else {
            System.out.println("~registrazione non andata a buon fine");
            return false;
        }
    }
    
}