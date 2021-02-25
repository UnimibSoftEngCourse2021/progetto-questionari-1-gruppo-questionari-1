package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;  


@Controller
public class UtenteNotLoggedController{

    @RequestMapping("/accedi") // Manages Accedi events
    public String LoginView() {
            System.out.println("Show Accedi");
            return "accedi";
       /* @RequestMapping
        public String getView() { // Mostra la pagina Accedi

        }

        @PostMapping("/login") // Esegue il login 
        public String makeLogin(@RequestParam("Email") String Username, @RequestParam("Password") String Password) {
            if(Username != null && Password != null){ // Se Username e Password hanno dei valori validi allora richiamo il metodo Login
                boolean res = login(Username, Password);
                System.out.println("Eseguito il login : " + res);
            }else{ // Se i valori di login non sono validi restituisco un Errore
                System.out.println("Errore nel login Username o Password NULL");
            }

            return "Redirect:/";
        }*/
    }



// ------------->
/*
    public boolean login(String Email, String Password){

    }

    public boolean registrazioneUtente() {

    }*/
}