package webapp.model;

import org.springframework.stereotype.Service;
import java.util.*;
import webapp.services.*;

@Service
public class GestoreQuestionario{

    UserDataMapper udm = new UserDataMapper();
    QuestionarioDataMapper qdm = new QuestionarioDataMapper();

    public Questionario creaQuestionario(String email, String nome, String categoria){
        UtenteRegistrato creatore = udm.find(email);
        System.out.println("Generando Il Questionario da aggiungere..");
        Questionario newQuestionario = new Questionario(nome, categoria, creatore);
        System.out.println("Aggiungendo al database il questionario appena generato..");
        qdm.insert(newQuestionario);
        return newQuestionario;
    }

    public boolean aggiungiDomanda(Domanda domanda, String ID){
        //TODO : aggiugiDomanda
        return true;
    }

    public Questionario getQuestionarioById(String ID){

        return qdm.findByID(ID);
    }

    public List<Questionario> getQuestionarioByName(String name){
        return qdm.findByName(name);
    }

    public List<Questionario> getQuestionarioByWord(String word){
        // TODO : getQuestionarioByWord
        return null;
    }

    public boolean eliminaQuestionario(String ID){
        System.out.println("Eliminando il questionario " + ID);
        return qdm.remove(ID);
    }

    public boolean modificaQuestionario(String ID){
        // TODO : modificaQuestionario
        return true;
    }
}