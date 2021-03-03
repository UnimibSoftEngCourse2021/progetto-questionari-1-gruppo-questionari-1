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

import webapp.model.Domanda;
import webapp.model.GestoreDomande;
import webapp.model.GestoreUtenti;


@Controller
public class UtenteLoggedController{
	
	//necessario per conoscere l'utente attualmente loggato, potremmo fondere i controller utenteLogged e utenteNotLogged
	@Autowired
	GestoreUtenti gestoreUtente; 
	
    @Autowired 
    GestoreDomande gestoreDomande;

    @GetMapping(value = "/creaDomanda")
	public String getCreaDomanda(Model model)
	{
    	System.out.println("Show CreaDomanda");
    	Domanda domanda = new Domanda();
    	
		model.addAttribute("newDomanda", domanda);

		return "creaDomanda";
	}

	@PostMapping(value="/creaDomanda")
	public String GestRegistraDomanda(@ModelAttribute("newDomanda") Domanda domanda, BindingResult result)
	{
		System.out.println("nuova domanda:"+ domanda.getCategoria());
		domanda.setCreatore(gestoreUtente.getIdUtente());
		registraNuovaDomanda(domanda);
		
		return "redirect:/";
	}
	
	private boolean registraNuovaDomanda(Domanda domanda) {
		boolean ris = gestoreDomande.creaDomanda(domanda);
		if(ris)
			{return true;}
		else {
			System.out.println("~creazione domanda non andata a buon fine");
			return false;
		}
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder)
	{
		binder.setAllowedFields("testo", "immagine", "categoria", "domandaChiusa");

	}


}