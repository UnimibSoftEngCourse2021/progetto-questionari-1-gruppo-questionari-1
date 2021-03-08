package webapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp.model.*;


@Controller
public class UtenteLoggedController{
	
	//necessario per conoscere l'utente attualmente loggato, potremmo fondere i controller utenteLogged e utenteNotLogged
	@Autowired
	GestoreUtenti gestoreUtente; 
	
    @Autowired 
    GestoreDomande gestoreDomande;

	@Autowired
	GestoreQuestionario gestoreQuestionario;

	@GetMapping(value="/creaDomanda")
	public String gestRegistraDomanda(  @RequestParam("testo") String testo,
										@RequestParam("immagine") String immagine,
										@RequestParam("categoria") String categoria,
										@RequestParam("domandaChiusa") boolean domandaChiusa,
										@RequestParam(required = false, name="opzioni") List<String> listaOpzioni,
										Model model)
	{
		System.out.println("nuova domanda:"+domandaChiusa);
		Domanda d = creaDomanda(testo, immagine, categoria, domandaChiusa, listaOpzioni);
		model.addAttribute("domanda", d);
		return "?";
	}
	
	@GetMapping(value="/cercaDomanda")
	public String gestCercaDomanda(@RequestParam("categoria") String categoria, Model model)
	{
		System.out.println("cerca per:"+categoria);
		List<Domanda> listaDomande = cercaDomanda(categoria);
		model.addAttribute("listaDomande",listaDomande);
		return "?";
	}
	
	@GetMapping(value="/creaQuestionario")
	public String gestRegistraQuestionario(@RequestParam("categoria") String categoria, 
										   @RequestParam("nome") String nome,
										   Model model)
	{
		System.out.println("crea Questionario");
		Questionario c = creaQuestionario(nome, categoria);
		model.addAttribute("questionario",c);
		return "?";
	}
	
	
	
	//---------------------> Funzioni Controller
	

	private Domanda creaDomanda(String testo, String immagine, String categoria, boolean domandaChiusa, List<String> opzioni) { //funzione che crea una domanda e la carica nel database
		Set<Opzione> listaOpzioni = new HashSet<Opzione>(); 
		System.out.println("Controller : creando la domanda");
		if(domandaChiusa)
			for (String opzione : opzioni) { //creo una lista di opzioni e le aggiungo alla listaOpzioni
				
				listaOpzioni.add(gestoreDomande.creaOpzione(opzione));
			}
		return gestoreDomande.creaDomanda(testo, immagine, categoria, domandaChiusa, gestoreUtente.getUtenteLoggato(), listaOpzioni); //creo la domanda e gli passo come parametri tutte le informazioni e la lista delle opzioni 
	}

	private boolean modificaDomanda(String testo, String Immagine, String categoria, boolean domandaChiusa, String creatore, List<String> opzioni){
		System.out.println("Controller : modificando la domanda");
		//TODO
		return true;
	}

	private List<Domanda> cercaDomanda(String categoria) { // Cerca una lista di domande in base ad una categoria
		System.out.println("Controller : cercando la domanda");
		List<Domanda> listaDomandeCercate = gestoreDomande.getDomandaByCategoria(categoria);
		return listaDomandeCercate;
	} 

	private boolean aggiungiDomanda(String IdQuestionario, int IdDomanda) { 
		// Aggiunge una domanda esistente ad un questionario
		System.out.println("Controller : aggiungendo la domanda esistente al questionario");
		Domanda d = gestoreDomande.getDomandaByID(IdDomanda);
		gestoreQuestionario.addDomanda(d, IdQuestionario);
		return true;
	}

	private boolean aggiungiDomanda(String IdQuestionario, String testo, String Immagine, String categoria, boolean domandaChiusa, List<String> opzioni) {
		// Aggiunge una domanda al qustionario IdQuestionario subito dopo averla creata 
		System.out.println("Controller : creando la domanda e aggiungendola al questionario");
		Domanda d = this.creaDomanda(testo, Immagine, categoria, domandaChiusa, opzioni); 
		gestoreQuestionario.addDomanda(d, IdQuestionario);
		return true;
	}

	private boolean eliminaDomanda(int IdDomanda){ // Questa funzione elimina una domanda con id IdDomanda dal database
		System.out.println("Controller : eliminando la domanda");
		gestoreDomande.rimuoviDomanda(IdDomanda);
		return true;
	}

	private Questionario creaQuestionario(String nome, String categoria){ // Qui si crea un nuovo questionario e lo si restituisce al chiamante
		System.out.println("Controller : creando un questionario");
		Questionario newQuestionario = gestoreQuestionario.creaQuestionario(gestoreUtente.getUtenteLoggato(), nome, categoria);
		return newQuestionario;
	}

	private boolean modificaQuestionario(String ID){ // Qui si modifica un questionario : ancora da sistemare, o meglio, ancora da fare 
		System.out.println("Controller : modificando un qustionario");
		gestoreQuestionario.modificaQuestionario(ID);
		return true;
	}

	private void cercaQuestionarioByID(String ID) { // Questo metodo cerca all'interno del databse un questionario in base al sui ID
		// TODO : ricerca Questionario in base all'id e all'utente
	}

	private List<Questionario> cercaQuestionarioByWord (String word) { // Questo metodo cerca all'interno del databse tutti i questionari con all'interno la parola word
		System.out.println("Controller : cercando un questionario in base ad una parola");
		List<Questionario> questionariTrovati = gestoreQuestionario.getQuestionarioByWord(word);
		return questionariTrovati;
	}

	//TODO : visualizza dati sulle riposte metodo

	private boolean eliminaQuestionario(String ID){ // Questo metodo elimina un questionario con id ID dal database
		System.out.println("Controller : eliminando un questionario dal database");
		gestoreQuestionario.eliminaQuestionario(ID);
		return true;
	}

	//TODO : visualizza questionari compilati

	//TODO : eliminaQuestionariCompilati

	//TODO : modifica questionari compilati

	//TODO : pensare se fare un gestore delle compilazioni


	//-----------------------> Fine funzioni Controller


}