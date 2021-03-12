package webapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

import webapp.model.*;


//public static final String questionario = "questionari";

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
	public String gestCercaDomanda(@RequestParam("categoria") String categoria, Model model, HttpSession utente)
	{
		System.out.println("cerca per:"+categoria);
		List<Domanda> listaDomande = cercaDomanda(categoria, utente);
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
	
	@GetMapping(value="/togliDomandaQuestionario")
	public String gestTogliDomanda(  HttpSession utente,
									 @RequestParam("id") int id,
										Model model) {
		rimuoviDomandaDaQuestionario(id, utente, model);
		return "aggiungiDomande";
	}
	
	@GetMapping(value="/cercaDomandaQuestionario")
	public String gestCercaDomandaQuestionario(@RequestParam("categoria") String categoria, Model model, HttpSession utente)
	{
		System.out.println("cerca per:"+categoria);
		List<Domanda> listaDomande = cercaDomanda(categoria, utente);
		model.addAttribute("listaDomande",listaDomande);
		return "popUpViewQuestion";
	}
	
	@GetMapping(value="/salvaQuestionario")
	public String gestSalvaQuestionario(HttpSession utente) {
		Questionario q = (Questionario) utente.getAttribute("questionario");
		salvaQuestionario(q);
		utente.setAttribute("questionario", null);
		return "redirect:/ricercaQuestionario?id="+q.getCategoria();
	}
	
	@GetMapping(value="/cercaQuestionario")
	public String gestCercaQuestionario(@RequestParam("categoria") String categoria,  Model model)
	{
		System.out.println("cerca Questionario");
		List<Questionario> listaQuestionario = cercaQuestionarioByCategory(categoria);  
		model.addAttribute("questionario",listaQuestionario);
		return "searchResult";
	}

	@GetMapping(value="/questionari")
	public String visualizzaQuestionari(Model model, HttpSession utente){
		System.out.println("Sto recuperando i questionari dell'utente");
		List<Questionario> questionariCreati = getQuestionariCreati(utente);
		if(!(questionariCreati.isEmpty()))
			model.addAttribute("questionariCreati", questionariCreati);
		return "questionari";
	}
	
	@GetMapping(value="/eliminaQuestionario/{id}")
	public String eliminaQuest(Model model, @PathVariable int id){
		boolean check = eliminaQuestionario(id);
		System.out.println("Eliminato il questionario: " + check);
		return "redirect:/questionari";
	}

	@GetMapping(value="/modificaQuestionario")
	public String modQuestionario(Model model, HttpSession utente){

	boolean check = modificaQuestionario((int) model.getAttribute("id"), 
										 (String) model.getAttribute("nome"),
										 (String) model.getAttribute("categoria"), 
										 utente);
	System.out.println("Modificato il questionario: "+ check);
	return "questionari";
	}
	
	@GetMapping(value="/questionariCompilati")
	public String questionariCompilati(Model model, HttpSession utente){
		List<Compilazione> listaCompilazioni = visualizzaQuestionariCompilati(utente);
		model.addAttribute("listaCompilazioni",listaCompilazioni);
		return "surveyCompilati";
	}
	
	@GetMapping(value="/elimina")
	public String eliminaCompilazione(@RequestParam("id") String idCompilazione) {
		eliminaQuestionarioCompilato(idCompilazione);
		return "redirect:/questionariCompilati";
	}
	
	//visualizza le compilazioni dei questionari creati dall'utente
	@GetMapping(value="/visualizzaCompilazioni/{idQuestionario}")
	public String visualizzaCompilazioni(@PathVariable("idQuestionario") int idQuestionario, Model model) {
		Questionario q = cercaQuestionarioByID(idQuestionario);
		model.addAttribute("questionario", q);
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

	private List<Domanda> cercaDomanda(String categoria, HttpSession utente) { // Cerca una lista di domande in base ad una categoria
		System.out.println("Controller : cercando la domanda");
		UtenteRegistrato u = getUtenteSession(utente);
		List<Domanda> listaDomandeCercate = gestoreDomande.getDomandaByCategoria(categoria, u);
		
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
	
	private boolean rimuoviDomandaDaQuestionario(int id, HttpSession utente, Model model){ // Questa funzione toglie dal questionario in creazione una domanda
		System.out.println("Controller : eliminando la domanda");
		Questionario q = (Questionario) utente.getAttribute("questionario");
		Iterator<Domanda> i = q.getDomande().iterator();
		while(i.hasNext())
		{
			Domanda c = (Domanda) i.next();
			if(c.getId()==id) {
				q.getDomande().remove(c);
			}
		}
		System.out.println(q.getDomande().toString());
		utente.setAttribute("questionario", q);
		model.addAttribute("listaDomande", q.getDomande());
		return true;
	}

	private Questionario creaQuestionario(String nome, String categoria, HttpSession utente){ // Qui si crea un nuovo questionario e lo si restituisce al chiamante
		System.out.println("Controller : creando un questionario");
		UtenteRegistrato u = getUtenteSession(utente);
		Questionario newQuestionario = gestoreQuestionario.creaQuestionario(u, nome, categoria);
		return newQuestionario;
	}

	private boolean modificaQuestionario(int id, String nome, String categoria, HttpSession utente){ // Qui si modifica un questionario : ancora da sistemare, o meglio, ancora da fare 
		System.out.println("Controller : modificando un qustionario");
		UtenteRegistrato u = getUtenteSession(utente);
		gestoreQuestionario.modificaQuestionario(id, nome ,categoria, u);
		return true;
	}

	private Questionario cercaQuestionarioByID(int ID) { // Questo metodo cerca all'interno del databse un questionario in base al sui ID
		return  gestoreQuestionario.getQuestionarioById(ID);
	}

	
	private List<Questionario> cercaQuestionarioByCategory (String categoria) { // Questo metodo cerca all'interno del databse tutti i questionari con all'interno la parola word
		System.out.println("Controller : cercando un questionario in base ad una parola");
		List<Questionario> questionariTrovati = gestoreQuestionario.getQuestionarioByCategory(categoria);
		return questionariTrovati;
	}
	
	private Set<Compilazione> visualizzaDatiSulleRisposte(int id) {
		Set<Compilazione> listaCompilazioni = gestoreQuestionario.cercaCompilazioni(id);
		return listaCompilazioni;
	}

	private boolean eliminaQuestionario(int id){ // Questo metodo elimina un questionario con id ID dal database
		System.out.println("Controller : eliminando un questionario dal database");
		gestoreQuestionario.eliminaQuestionario(id);
		return true;
	}

	private List<Questionario> getQuestionariCreati(HttpSession utente) {
		String email = (String) utente.getAttribute("email");
		System.out.println("Controller : cercando tutti i questiornari creati da un utente con mail " + email);
		return gestoreQuestionario.getQuestionarioByUtente(email);
	}

	private List<Compilazione> visualizzaQuestionariCompilati(HttpSession utente) {
		UtenteRegistrato u = getUtenteSession(utente);
		return gestoreQuestionario.cercaCompilazioniUtente(u);
	}

	private boolean eliminaQuestionarioCompilato(String id) {
		System.out.println("eliminando una compilazione di un questionario");
		Compilazione c = gestoreQuestionario.cercaCompilazione(id);
		return gestoreQuestionario.rimuoviCompilazione(c);
	}
	
	private void salvaQuestionario(Questionario questionario) {
		gestoreQuestionario.salvaQuestionario(questionario);
	}
	
	private UtenteRegistrato getUtenteSession(HttpSession utente) {
		return gestoreUtente.getUtenteByMail((String) utente.getAttribute("email"));
	}

	//-----------------------> Fine funzioni Controller


}