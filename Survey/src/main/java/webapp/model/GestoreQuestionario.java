package webapp.model;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.*;

import com.mysql.cj.xdevapi.JsonParser;

import webapp.services.*;

@Service
public class GestoreQuestionario {

    private UserDataMapper udm = new UserDataMapper();
    private QuestionarioDataMapper qdm = new QuestionarioDataMapper();
    private DomandaDataMapper ddm = new DomandaDataMapper();
    private CompilazioneDataMapper cdm = new CompilazioneDataMapper();

    public Questionario creaQuestionario(UtenteRegistrato creatore, String nome, String categoria){
    	Questionario newQuestionario = new Questionario(nome, categoria, creatore);
        System.out.println("Aggiungendo al database il questionario appena generato..");
        qdm.insert(newQuestionario);
        return newQuestionario;
    }

    public boolean addDomanda(Domanda domanda, String id){
        return qdm.addDomanda(id, domanda);
    }

    public boolean removeDomanda(Domanda domanda, String id){
        return qdm.removeDomanda(id, domanda);
    }

    public Questionario getQuestionarioById(String id){
        return qdm.findByID(id);
    }

    public List<Questionario> getQuestionarioByName(String name){
        return qdm.findByName(name);
    }

    public List<Questionario> getQuestionarioByWord(String word){
        //Cerco nel db tutte le domande che hanno al suo interno la stringa word
        List<Domanda> domande = ddm.findByWord(word);
        //HashSet per salvare i qeustionari evitando rindondanze
        HashSet<Questionario> questionariCercati = new HashSet<>();
        for(Domanda domandaIter : domande ){
            Set<Questionario> questionari = domandaIter.getQuestionari();
            for(Questionario questionarioIter : questionari){
                questionariCercati.add(questionarioIter);
            }
        }
        return new ArrayList<>(questionariCercati);
    }

    public List<Questionario> getQuestionarioByUtente(String email) {
        UtenteRegistrato creatore = udm.find(email);
        return qdm.questionariUtene(creatore);
    }

    public boolean eliminaQuestionario(String id){
        System.out.println("Eliminando il questionario " + id);
        return qdm.remove(id);
    }

    public boolean modificaQuestionario(String id, String nome,  String categoria, String email){
        UtenteRegistrato creatore = udm.find(email);
        Questionario questionarioModificato = new Questionario(nome, categoria, creatore);
        qdm.remove(id);
        qdm.insert(questionarioModificato);
        return true;
    }



/* la lista di risposte passate come parametro sono una liata di json che contengono rispettivamente sotto la voce "id" l'id della domanda a cui si 
riferisce la risposta, e immegiatamente sotto la voce "risposta" che si riferisce alla risposta alla suddetta domanda. Questi due dati vengono utilizzati
per creare vari oggetti di tipo CompilazioneDomanda. */
    
    public Compilazione aggiungiCompilazione(String email, String ID, List<String> risposte) { //prende in input la mail dell'utente che ha compilato il questionario, lid del questionario compilato e una lista di risposte
        JsonParser parser = new JsonParser();    
        Questionario questionarioCompilato = qdm.findByID(ID);
        UtenteRegistrato utente = udm.find(email);
        Compilazione compilazione = new Compilazione(questionarioCompilato, utente);
        for (String e : risposte) {
            JSONObject json = (JSONObject) parser.parseDoc(e);
            Domanda domanda = ddm.findByID(json.getInt("id"));
            compilazione.getDomande().add(new CompilazioneDomanda(domanda, compilazione, json.getString("risposta")));
        }
        cdm.insert(compilazione);
        return compilazione;
    }

    public boolean rimuoviCompilazione(String idCompilazione) {
        return cdm.remove(idCompilazione);
    }

}