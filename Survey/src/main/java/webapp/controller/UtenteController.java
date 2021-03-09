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


    public Questionario ricercaQuestionari(String ID){ //cerca un questionario nel database 
        System.out.println("Controller : cercando un questionario in base all'id");
		Questionario questionario = gestoreQuestionario.getQuestionarioById(ID);
		return questionario;
    }

    // TODO : gestore delle compilazione magari all'interno del GestoreQuestionari e Un 
     
    public void eliminazioneQuestionarioCompilato(String ID, String email) {

    }
    
    public void modificaQuestionarioCompilato(String ID, String email) {

    }

    public void compilaQuestionario(String ID, List<String> risposte, String email) {

    }

    public void getQuestionarioCompilato(String ID) {

    }


}