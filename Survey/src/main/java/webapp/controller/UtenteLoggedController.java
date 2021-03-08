package webapp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


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





	/* codice chiara
    @GetMapping(value = "/creaDomanda")
	public String getCreaDomanda(Model model)
	{
    	System.out.println("Show CreaDomanda");
    	Domanda domanda = new Domanda();
    	
		model.addAttribute("newDomanda", domanda);
		
		return "creaDomanda";
	}

	@PostMapping(value="/creaDomanda")
	public String GestRegistraDomanda(@ModelAttribute("newDomanda") Domanda domanda, @RequestParam(required = false, name="opzioni") ArrayList<String> listaOpzioni, Model model, BindingResult result)
	{
		System.out.println("nuova domanda:"+ domanda.getCategoria());
		domanda.setCreatore(gestoreUtente.getIdUtente());
		registraNuovaDomanda(domanda);
		if(domanda.isDomandaChiusa())
		{
			Opzione o = new Opzione();
			for(int i=0;i < listaOpzioni.size() ;i++) {
				o.setDescrizioneOpzione(listaOpzioni.get(i));
				registraNuovaOpzione(o);
			}
		}
		return "redirect:/";
	}
	*/

	//---------------------> Funzioni Controller
	

	public Domanda creaDomanda(String testo, String Immagine, String categoria, boolean domandaChiusa, String creatore, List<String> opzioni) { //funzione che crea una domanda e la carica nel database
		Set<Opzione> listaOpzioni = new HashSet<Opzione>(); 
		System.out.println("Controller : creando la domanda");
		if(domandaChiusa)
			for (String opzione : opzioni) { //creo una lista di opzioni e le aggiungo alla listaOpzioni
				listaOpzioni.add(gestoreDomande.creaOpzione(opzione));
			}
		return gestoreDomande.creaDomanda(testo, Immagine, categoria, domandaChiusa, creatore, listaOpzioni); //creo la domanda e gli passo come parametri tutte le informazioni e la lista delle opzioni 
	}

	public boolean modificaDomanda(String testo, String Immagine, String categoria, boolean domandaChiusa, String creatore, List<String> opzioni){
		System.out.println("Controller : modificando la domanda");
		//TODO
		return true;
	}

	public List<Domanda> cercaDomanda(String categoria) { // Cerca una lista di domande in base ad una categoria
		System.out.println("Controller : cercando la domanda");
		List<Domanda> listaDomandeCercate = gestoreDomande.getDomandaByCategoria(categoria);
		return listaDomandeCercate;
	} 

	public boolean aggiungiDomanda(String IdQuestionario, int IdDomanda) { 
		// Aggiunge una domanda esistente ad un questionario
		System.out.println("Controller : aggiungendo la domanda esistente al questionario");
		Domanda d = gestoreDomande.getDomandaByID(IdDomanda);
		gestoreQuestionario.addDomanda(d, IdQuestionario);
		return true;
	}

	public boolean aggiungiDomanda(String IdQuestionario, String testo, String Immagine, String categoria, boolean domandaChiusa, String creatore, List<String> opzioni) {
		// Aggiunge una domanda al qustionario IdQuestionario subito dopo averla creata 
		System.out.println("Controller : creando la domanda e aggiungendola al questionario");
		Domanda d = this.creaDomanda(testo, Immagine, categoria, domandaChiusa, creatore, opzioni); 
		gestoreQuestionario.addDomanda(d, IdQuestionario);
		return true;
	}

	public boolean eliminaDomanda(int IdDomanda){ // Questa funzione elimina una domanda con id IdDomanda dal database
		System.out.println("Controller : eliminando la domanda");
		gestoreDomande.rimuoviDomanda(IdDomanda);
		return true;
	}

	public Questionario creaQuestionario(String email, String nome, String categoria){ // Qui si crea un nuovo questionario e lo si restituisce al chiamante
		System.out.println("Controller : creando un questionario");
		Questionario newQuestionario = gestoreQuestionario.creaQuestionario(email, nome, categoria);
		return newQuestionario;
	}

	public boolean modificaQuestionario(String ID){ // Qui si modifica un questionario : ancora da sistemare, o meglio, ancora da fare 
		System.out.println("Controller : modificando un qustionario");
		gestoreQuestionario.modificaQuestionario(ID);
		return true;
	}

	public Questionario cercaQuestionarioByID(String ID) { // Questo metodo cerca all'interno del databse un questionario in base al sui ID
		System.out.println("Controller : cercando un questionario in base all'id");
		Questionario questionario = gestoreQuestionario.getQuestionarioById(ID);
		return questionario;
	}

	public List<Questionario> cercaQuestionarioByWord (String word) { // Questo metodo cerca all'interno del databse tutti i questionari con all'interno la parola word
	System.out.println("Controller : cercando un questionario in base ad una parola");
		List<Questionario> questionariTrovati = gestoreQuestionario.getQuestionarioByWord(word);
		return questionariTrovati;
	}

	//TODO : visualizza dati sulle riposte metodo

	public boolean eliminaQuestionario(String ID){ // Questo metodo elimina un questionario con id ID dal database
		System.out.println("Controller : eliminando un questionario dal database");
		gestoreQuestionario.eliminaQuestionario(ID);
		return true;
	}

	//TODO : visualizza questionari compilati

	//TODO : eliminaQuestionariCompilati

	//TODO : modifica questionari compilati

	//TODO : pensare se fare un gestore delle compilazioni





	/* codice di chiara
	private boolean registraNuovaOpzione(Opzione o) {
		boolean ris = gestoreDomande.creaOpzione(o);
		if(ris)
			{return true;}
		else {
			System.out.println("~creazione opzione non andata a buon fine");
			return false;
		}
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
	*/
	
	//-----------------------> Fine funzioni Controller
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder)
	{
		binder.setAllowedFields("testo", "immagine", "categoria", "domandaChiusa");

	}


}