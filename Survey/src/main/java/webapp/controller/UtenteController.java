package webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
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


    public Questionario ricercaQuestionari(String id){ //cerca un questionario nel database 
        System.out.println("Controller : cercando un questionario in base all'id");
		Questionario questionario = gestoreQuestionario.getQuestionarioById(id);
		return questionario;
    }

    // TODO : gestore delle compilazione magari all'interno del GestoreQuestionari e Un 
     
    public boolean eliminazioneQuestionarioCompilato(String compilazioneId) {
        return gestoreQuestionario.rimuoviCompilazione(compilazioneId);
    }
    
    public void modificaQuestionarioCompilato(String id, String email) {

    }

    public Compilazione compilaQuestionario(String id, List<String> risposte, String email) {
        return gestoreQuestionario.aggiungiCompilazione(email, id, risposte);
    }

    public void getQuestionarioCompilato(String id) {
        
    }


}