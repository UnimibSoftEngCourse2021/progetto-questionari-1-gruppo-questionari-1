package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;  

import webapp.model.*;


@Controller
public class UtenteNotLoggedController{

//-------------------> start controller LoginView
    @RequestMapping("/accedi") // Manages Accedi events
    public String getLoginView() {
            System.out.println("Show Accedi");
            return "accedi";
    }
    @PostMapping("/accedi/login") // Esegue il login 
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

        


// ------------------> metodi controller 

    public boolean login(String Email, String Password){
        return GestoreUtenti.login(Email, Password);
    }

    public boolean registrazioneUtente(String nome, String cognome, String email, String password ) {
        boolean ris = GestoreUtenti.creaUtete(nome, cognome, email, password);
        if(ris) {
            return true;
        } else {
            System.out.println("~registrazione non andata a buon fine");
            return false;
        }
    }
}