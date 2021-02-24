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
import org.springframework.web.bind.annotation.RequestMapping;

import webapp.domain.Utente;
import webapp.repository.UtenteRepository;

@Controller
@RequestMapping("/")
public class HomeController 
{
	@Autowired
	private UtenteRepository utenteRepository;
	
	/* metodo per arrivare alla home
	 * http://localhost:8080/Survey/
	 */
	@RequestMapping
	public String getHome(Model model)
	{
		model.addAttribute("intestazione", "Benvenuti nel sito Survey.io");
		return "home";
	}
	
	/*metodi per la registrazione di un nuovo utente 
	 * http://localhost:8080/Survey/nuovoUtente/
	 */
	@GetMapping(value = "/nuovoUtente")
	public String InsArticoli(Model model)
	{
		Utente utente = new Utente();

		model.addAttribute("Titolo", "Registrati!");
		model.addAttribute("newUtente", utente);
		
		return "registrazioneUtente";
	}
	
	@PostMapping(value="/nuovoUtente")
	public String GestRegistraUtente(@ModelAttribute("newUtente") Utente utente, BindingResult result)
	{
		utenteRepository.registraUtente(utente);
		
		return "redirect:/";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder)
	{
		binder.setAllowedFields("nome", "cognome", "email", "password");

	}
	
}
