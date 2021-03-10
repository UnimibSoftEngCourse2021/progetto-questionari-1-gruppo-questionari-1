package webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import webapp.model.*;




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
    

    //---------------------> funzioni 


    public Questionario ricercaQuestionari(int id){ //cerca un questionario nel database 
        System.out.println("Controller : cercando un questionario in base all'id");
		Questionario questionario = gestoreQuestionario.getQuestionarioById(id);
		return questionario;
    } 
     
    public boolean eliminazioneQuestionarioCompilato(String compilazioneId) {
        return gestoreQuestionario.rimuoviCompilazione(compilazioneId);
    }
    
    public boolean modificaQuestionarioCompilato(String compId, List<String> risposte) {
        return gestoreQuestionario.modificaCompilaizone(compId, risposte);
    }

    public Compilazione compilaQuestionario(int id, List<String> risposte, String email) {
        return gestoreQuestionario.aggiungiCompilazione(email, id, risposte);
    }

    public Compilazione getQuestionarioCompilato(String id) {
        return gestoreQuestionario.cercaCompilazione(id);
    }

}