package webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp.model.*;
import webapp.services.emailSender;




@Controller 
public class UtenteController {

    @Autowired
	GestoreUtenti gestoreUtente; 
	
    @Autowired 
    GestoreDomande gestoreDomande;

	@Autowired
	GestoreQuestionario gestoreQuestionario;

    @GetMapping(value="/")
    public String getHome() { //gestisce gli accessi alla pagina home
        System.out.println("Show Home Page");
        return "index";
    }
    /*
    @GetMapping(value = "ricercaQuestionario")
    public String getSurvey(Model model, @RequestParam("id") int id) {
        System.out.println("Show Survey by id" + id);
        Questionario questionario = gestoreQuestionario.getQuestionarioById(id);
        model.addAttribute("questionarioTrovato", questionario);
        model.addAttribute("idQuestionario", questionario.getID());
        System.out.println("nome : " + questionario.getNome());
        return "searchResult";
    }
    */
    @GetMapping(value = "ricercaQuestionario")
    public String getSurvey(Model model, @RequestParam("id") String id) {
        boolean isIdANum = true;
        System.out.println("Show Survey by id or category : " + id);
        Questionario questionario = null;
        try{
            questionario = gestoreQuestionario.getQuestionarioById(Integer.parseInt(id));
        }catch(NumberFormatException e){
            isIdANum = false;
        }
        List<Questionario> listaQuestionari = gestoreQuestionario.getQuestionarioByCategory(id);
        if(isIdANum)
            listaQuestionari.add(questionario);
        model.addAttribute("listaQuestionari", listaQuestionari);
        return "searchResult";
    }
    
    @GetMapping(value = "surveyToCompile")
    public String getSurveyToCompile(Model model, @RequestParam("id") int idQuestionario) {
        Questionario questionarioDaCompilare = gestoreQuestionario.getQuestionarioById(idQuestionario);
        System.out.println("dimensione : " + questionarioDaCompilare.getDomande().size());
        model.addAttribute("questionario", questionarioDaCompilare);
        model.addAttribute("idQuestionario", questionarioDaCompilare.getID());
        return "compilazioneQuestionario";
    }

    @PostMapping(value = "compilaQuestionario")
    public String compilaQuestionario(HttpSession sessione, @RequestParam("id") int idQuestionarioCompilato, HttpServletRequest request, Model model) {
    	Questionario questionarioCompilato = gestoreQuestionario.getQuestionarioById(idQuestionarioCompilato);
        List<String> listaRisposte = new ArrayList<>();
        for (Domanda domanda : questionarioCompilato.getDomande()) {
            String risposta = request.getParameter(domanda.getId()+"");
            listaRisposte.add("{\"id\":\"" + domanda.getId() + "\",\"risposta\":\"" + risposta + "\"}");
        }
        //visualizza codice compilazione
        Compilazione c;
        if(sessione.getAttribute("email") == null)
            c = this.compilaQuestionario(idQuestionarioCompilato, listaRisposte, "unknown");
        else
        	c = this.compilaQuestionario(idQuestionarioCompilato, listaRisposte, (String)sessione.getAttribute("email"));
        model.addAttribute("idCompilazione", c.getID());
        //send email
        System.out.println(questionarioCompilato.getCreatore().getMail());
        emailSender sender = new emailSender();
        sender.sendEmail(questionarioCompilato.getCreatore().getMail(), questionarioCompilato.getNome(), c.getID());
        return "codiceCompilazione";
    }
    

    //---------------------> funzioni 


    public Questionario ricercaQuestionari(int id){ //cerca un questionario nel database 
        System.out.println("Controller : cercando un questionario in base all'id");
		Questionario questionario = gestoreQuestionario.getQuestionarioById(id);
		return questionario;
    } 
     
    public boolean eliminazioneQuestionarioCompilato(Compilazione c) {
        return gestoreQuestionario.rimuoviCompilazione(c);
    }
    
    public boolean modificaQuestionarioCompilato(String compId, List<String> risposte) {
        return gestoreQuestionario.modificaCompilaizone(compId, risposte);
    }

    public Compilazione compilaQuestionario(int id, List<String> risposte, String email) {
        UtenteRegistrato u = gestoreUtente.getUtenteByMail(email);
    	return gestoreQuestionario.aggiungiCompilazione(u, id, risposte);
    }

    public Compilazione getQuestionarioCompilato(String id) {
        return gestoreQuestionario.cercaCompilazione(id);
    }

}