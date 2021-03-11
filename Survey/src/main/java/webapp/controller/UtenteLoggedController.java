package webapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	//---------------------> inizio creazione e ricerca domande
	
	@GetMapping(value = "/gestisciDomande")
	public String VisualizzaCreaDomanda(Model model){
		model.addAttribute("testo", " ");
		return "questions";
	}
	@GetMapping(value="/creaDomanda")
	public String gestRegistraDomanda(  @RequestParam("testo") String testo,
										//@RequestParam(name="immagine") byte[] immagine,
										@RequestParam("categoria") String categoria,
										@RequestParam("opzioni") String listaOpzioni,
										Model model, HttpSession utente)
	{
		System.out.println("nuova domanda:"+testo);
		Domanda d = creaDomanda(testo, /*immagine,*/ categoria, listaOpzioni, utente);
		List<Domanda> listaDomande = new ArrayList<Domanda>();
		listaDomande.add(d);
		model.addAttribute("listaDomande", listaDomande);
		return "questions";
	}
	
	@GetMapping(value="/cercaDomanda")
	public String gestCercaDomanda(@RequestParam("categoria") String categoria, Model model)
	{
		System.out.println("cerca per:"+categoria);
		List<Domanda> listaDomande = cercaDomanda(categoria);
		model.addAttribute("listaDomande",listaDomande);
		return "questions";
	}
	
	//---------------------> fine creazione e ricerca domande
	
	//---------------------> inizio creazione questionari
	
	@GetMapping(value="/nuovoQuestionario")
	public String visualizzaCreaQuestionario() {
		return "createSurvey";
	}
	
	@GetMapping(value="/creaQuestionario")
	public String gestRegistraQuestionario(@RequestParam("categoria") String categoria, 
										   @RequestParam("nome") String nome,
										   Model model, HttpSession utente)
	{
		System.out.println("crea Questionario vuoto");
		Questionario q = creaQuestionario(nome, categoria, utente);
		utente.setAttribute("questionario", q);
		return "aggiungiDomande";
	}
	
	@GetMapping(value="/aggiungiDomanda")
	public String gestAggiungiDomanda(@RequestParam("domandaScelta") int idDomanda, HttpSession utente, Model model) {
		Questionario q = (Questionario) utente.getAttribute("questionario");
		System.out.println(idDomanda);
		aggiungiDomanda(q, idDomanda);
		model.addAttribute("listaDomande", q.getDomande());
		return "aggiungiDomande";
	}
	
	@GetMapping(value="/aggiungiDomandaNuova")
	public String gestAggiungiDomanda(  HttpSession utente,
										@RequestParam("testo") String testo,
										//@RequestParam(name="immagine") byte[] immagine,
										@RequestParam("categoria") String categoria,
										@RequestParam("opzioni") String listaOpzioni,
										Model model) {
		System.out.println("nuova domanda da aggiungere:"+testo);
		Questionario q = (Questionario) utente.getAttribute("questionario");
		System.out.println(q.getID()+" ");
		Domanda d = creaDomanda(testo, /*immagine,*/ categoria, listaOpzioni, utente);
		aggiungiDomanda(q, d.getId());
		model.addAttribute("listaDomande", q.getDomande());
		return "aggiungiDomande";
	}
	
	@GetMapping(value="/cercaDomandaQuestionario")
	public String gestCercaDomandaQuestionario(@RequestParam("categoria") String categoria, Model model)
	{
		System.out.println("cerca per:"+categoria);
		List<Domanda> listaDomande = cercaDomanda(categoria);
		model.addAttribute("listaDomande",listaDomande);
		return "popUpViewQuestion";
	}
	
	@GetMapping(value="/salvaQuestionario")
	public String gestSalvaQuestionario(HttpSession utente) {
		Questionario q = (Questionario) utente.getAttribute("questionario");
		salvaQuestionario(q);
		utente.setAttribute("questionario", null);
		return "redirect:/ricercaQuestionario?categoria="+q.getID();
	}
	
	@GetMapping(value="/cercaQuestionario")
	public String gestCercaQuestionario(@RequestParam("categoria") String categoria,  Model model)
	{
		System.out.println("cerca Questionario");
		List<Questionario> listaQuestionario = cercaQuestionarioByWord(categoria);  
		model.addAttribute("questionario",listaQuestionario);
		return "searchResult";
	}

	@GetMapping(value="/questionari")
	public String visualizzaQuestionari(Model model, HttpSession utente){
		System.out.println("Sto recuperando i questionari dell'utente");
		List<Questionario> questionariCreati = getQuestionariCreati(utente);
		model.addAttribute("questionariCreati", questionariCreati);
		return "questionari";
	}
	
	//crea il pdf della compilazione con id fornito dal form

	@GetMapping(value="/pdfCompilazione/{idCompilazione}")
	public String downloadCompilazioniPDF(@PathVariable("idCompilazione") String idCompilazione, Model model) {
		Compilazione c = gestoreQuestionario.cercaCompilazione(idCompilazione);
		System.out.println(c.getDomande().toArray());
		model.addAttribute("compilazione", c);
		return "compilazione";
	}
	//---------------------> fine creazione questionari
	
	//---------------------> Funzioni Controller
	

	private Domanda creaDomanda(String testo, /* byte[] immagine,*/ String categoria, String o, HttpSession utente) { //funzione che crea una domanda e la carica nel database
		HashSet<Opzione> listaOpzioni = new HashSet<Opzione>(); 
		System.out.println("Controller : creando la domanda");
		boolean domandaChiusa = false;
		if(o.length()>0)
		{ 	
			String[] opzioni = o.split(";");
			domandaChiusa = true;
			for(int i = 0; i<opzioni.length; i++) {
				listaOpzioni.add(gestoreDomande.creaOpzione(opzioni[i]));
			}
		}
		UtenteRegistrato creatore = getUtenteSession(utente);
		Domanda d = gestoreDomande.creaDomanda(testo, /*immagine,*/ categoria, domandaChiusa, creatore, listaOpzioni); //creo la domanda e gli passo come parametri tutte le informazioni e la lista delle opzioni 
		return d;
	}

	private boolean modificaDomanda(int idDomanda, String testo, byte[] Immagine, List<String> opzioni){
		System.out.println("Controller : modificando la domanda");
		gestoreDomande.modificaDomanda(idDomanda, testo, /*Immagine, */ opzioni);
		return true;
	}

	private List<Domanda> cercaDomanda(String categoria) { // Cerca una lista di domande in base ad una categoria
		System.out.println("Controller : cercando la domanda");
		List<Domanda> listaDomandeCercate = gestoreDomande.getDomandaByCategoria(categoria);
		return listaDomandeCercate;
	} 
	
	private Domanda cercaDomandaById(int id) { // Cerca una lista di domande in base ad una categoria
		System.out.println("Controller : cercando la domanda");
		Domanda d = gestoreDomande.getDomandaByID(id);
		return d;
	} 

	private void aggiungiDomanda(Questionario questionario, int idDomanda) { 
		// Aggiunge una domanda esistente ad un questionario
		System.out.println("Controller : aggiungendo la domanda esistente al questionario");
		Domanda d = gestoreDomande.getDomandaByID(idDomanda);
		gestoreQuestionario.addDomanda(questionario, d);
	}
	
	/*
	private boolean aggiungiDomanda(String IdQuestionario, String testo, byte[] Immagine, String categoria, String opzioni) {
		// Aggiunge una domanda al qustionario IdQuestionario subito dopo averla creata 
		System.out.println("Controller : creando la domanda e aggiungendola al questionario");
		//Domanda d = this.creaDomanda(testo,  Immagine, categoria, opzioni); 
		//gestoreQuestionario.addDomanda(d, IdQuestionario);
		return true;
	}
	*/
	
	private boolean eliminaDomanda(int IdDomanda){ // Questa funzione elimina una domanda con id IdDomanda dal database
		System.out.println("Controller : eliminando la domanda");
		gestoreDomande.rimuoviDomanda(IdDomanda);
		return true;
	}

	private Questionario creaQuestionario(String nome, String categoria, HttpSession utente){ // Qui si crea un nuovo questionario e lo si restituisce al chiamante
		System.out.println("Controller : creando un questionario");
		UtenteRegistrato u = getUtenteSession(utente);
		Questionario newQuestionario = gestoreQuestionario.creaQuestionario(u, nome, categoria);
		return newQuestionario;
	}

	private boolean modificaQuestionario(int ID, String nome, String categoria, HttpSession utente){ // Qui si modifica un questionario : ancora da sistemare, o meglio, ancora da fare 
		System.out.println("Controller : modificando un qustionario");
		UtenteRegistrato u = getUtenteSession(utente);
		gestoreQuestionario.modificaQuestionario(ID, nome ,categoria, u);
		return true;
	}

	private void cercaQuestionarioByID(String ID) { // Questo metodo cerca all'interno del databse un questionario in base al sui ID
		// TODO : ricerca Questionario in base all'id e all'utente
	}

	private List<Questionario> cercaQuestionarioByWord (String word) { // Questo metodo cerca all'interno del databse tutti i questionari con all'interno la parola word
		System.out.println("Controller : cercando un questionario in base ad una parola");
		List<Domanda> listaDomande = gestoreDomande.getDomandaByWord(word);
		List<Questionario> questionariTrovati = gestoreQuestionario.getQuestionarioByWord(listaDomande);
		return questionariTrovati;
	}

	private List<Compilazione> visualizzaDatiSulleRisposte(int id) {
		List<Compilazione> listaCompilazioni = new ArrayList<>(gestoreQuestionario.getQuestionarioById(id).getCompilazioni());
		return listaCompilazioni;
	}

	private boolean eliminaQuestionario(int ID){ // Questo metodo elimina un questionario con id ID dal database
		System.out.println("Controller : eliminando un questionario dal database");
		gestoreQuestionario.eliminaQuestionario(ID);
		return true;
	}

	//TODO : visualizza questionari compilati

	private List<Questionario> getQuestionariCreati(HttpSession utente) {
		String email = (String) utente.getAttribute("email");
		System.out.println("Controller : cercando tutti i questiornari creati da un utente con mail 'email'");
		return gestoreQuestionario.getQuestionarioByUtente(email);
	}

	private List<Compilazione> visualizzaQuestionariCompilati(String email) {
		return gestoreUtente.getUtenteByMail(email).getQuestionariCompilati();
	}

	private boolean eliminaQuestionarioCompilato(String id) {
		System.out.println("eliminando una compilazione di un questionario");
		return gestoreQuestionario.rimuoviCompilazione(id);
	}
	
	private void salvaQuestionario(Questionario questionario) {
		gestoreQuestionario.salvaQuestionario(questionario);
	}
	
	private UtenteRegistrato getUtenteSession(HttpSession utente) {
		return gestoreUtente.getUtenteByMail((String) utente.getAttribute("email"));
	}

	//-----------------------> Fine funzioni Controller


}