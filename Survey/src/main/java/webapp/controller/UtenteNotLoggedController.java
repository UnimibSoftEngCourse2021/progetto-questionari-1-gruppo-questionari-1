package webapp.controller;

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
		registrazioneUtente(utente);
		System.out.println("utente registrato, nome:"+ utente.getNome());
		return "redirect:/";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder)
	{
		binder.setAllowedFields("nome", "cognome", "email", "password");

	}


//-------------------> metodi controller 

    public boolean login(String Email, String Password){
        return GestoreUtenti.login(Email, Password);
    }

    public boolean registrazioneUtente(UtenteRegistrato utente) {
        boolean ris = GestoreUtenti.creaUtente(utente);
        if(ris) {
            return true;
        } else {
            System.out.println("~registrazione non andata a buon fine");
            return false;
        }
    }
}