package webapp.model;

import org.springframework.stereotype.Service;
import webapp.services.*;

@Service
public class GestioneQuestionario{

    UserDataMapper udm = new UserDataMapper();
    QuestionarioDataMapper qdm = new QuestionarioDataMapper();

    public boolean creaQuestionario(String email, String nome, String categoria){
        UtenteRegistrato creatore = udm.find(email);
        System.out.println("Generando Il Questionario da aggiungere..");
        Questionario newQuestionario = new Questionario(nome, categoria, creatore);
        System.out.println("Aggiungendo al database il questionario appena generato..");
        return qdm.insert(newQuestionario);
    }

    public boolean aggiungiDomanda(Domanda domanda, String ID){
        //TTTTTTTTTTTTTTODO
    }

    public Questionario getQuestionarioById(String ID){
        return qdm.findByID(ID);
    }

    public Questionario getQuestionarioByName(String name){
        return qdm.findByName(name);
    }

    public Questionario getQuestionarioByWord(String word){
        // TTTTTTTTTTTTTODO
    }

    public boolean eliminaQuestionario(String ID){
        System.out.println("Eliminando il questionario " + ID);
        retrun qdm.remove(ID);
    }

    public boolean modificaQuestionario(String ID){
        // TTTTTTTTTTODO
    }
}