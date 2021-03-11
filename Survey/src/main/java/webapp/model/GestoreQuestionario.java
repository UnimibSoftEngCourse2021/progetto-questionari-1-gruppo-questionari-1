package webapp.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.*;

import com.mysql.cj.xdevapi.JsonParser;

import webapp.services.*;

@Service
public class GestoreQuestionario {
	
    private QuestionarioDataMapper qdm = new QuestionarioDataMapper();
    private CompilazioneDataMapper cdm = new CompilazioneDataMapper();
    private DomandaDataMapper ddm = new DomandaDataMapper();

    public Questionario creaQuestionario(UtenteRegistrato creatore, String nome, String categoria){
    	Questionario newQuestionario = new Questionario(nome, categoria, creatore);
        return newQuestionario;
    }
    
    //aggiunge alla lista di domande nel questionario una nuova domanda
    public void addDomanda(Questionario q, Domanda domanda) {
    	q.getDomande().add(domanda);
    }

    public boolean addDomanda(int id_quest, Domanda domanda){
        
    	return qdm.addDomanda(id_quest, domanda);
    }

    public boolean removeDomanda(Domanda domanda, int id){
        return qdm.removeDomanda(id, domanda);
    }

    public Questionario getQuestionarioById(int id){
        return qdm.findByID(id);
    }

    public List<Questionario> getQuestionarioByName(String name){
        return qdm.findByName(name);
    }

    public List<Questionario> getQuestionarioByWord(List<Domanda> domande){
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
        return qdm.questionariUtente(email);
    }

    public boolean eliminaQuestionario(int id){
        System.out.println("Eliminando il questionario " + id);
        return qdm.remove(id);
    }

    public boolean modificaQuestionario(int id, String nome,  String categoria, UtenteRegistrato creatore){
        Questionario questionarioModificato = new Questionario(nome, categoria, creatore);
        questionarioModificato.setID(id);
        qdm.remove(id);
        qdm.insert(questionarioModificato);
        return true;
    }

/* la lista di risposte passate come parametro sono una liata di json che contengono rispettivamente sotto la voce "id" l'id della domanda a cui si 
riferisce la risposta, e immegiatamente sotto la voce "risposta" che si riferisce alla risposta alla suddetta domanda. Questi due dati vengono utilizzati
per creare vari oggetti di tipo CompilazioneDomanda. */
    
    public Compilazione aggiungiCompilazione(UtenteRegistrato utente, int id, List<String> risposte) { //prende in input la mail dell'utente che ha compilato il questionario, lid del questionario compilato e una lista di risposte
        Compilazione compilazione = creaCompilazione(utente, id);
        List<CompilazioneDomanda> listaRisposte = creaRisposteCompilazione(compilazione, risposte);
        cdm.insert(compilazione, listaRisposte);
        return compilazione;
    }

    public Compilazione creaCompilazione(UtenteRegistrato utente, int id){
        Questionario questionarioCompilato = qdm.findByID(id);
        Compilazione compilazione = new Compilazione(this.generatoreCompilazioneKey(questionarioCompilato.getID()), questionarioCompilato, utente);
        System.out.println(compilazione);
        return compilazione;
    }


    public List<CompilazioneDomanda> creaRisposteCompilazione(Compilazione compilazione, List<String> risposte) {
        String risp = "";
        List<CompilazioneDomanda> listaRisposte = new ArrayList<>();
        
        for (int r = 0; r < risposte.size(); r++) {
            if(r == risposte.size() - 1)
                risp = risp + risposte.get(r);
            else
                risp = risp + risposte.get(r) + ",";
        }

        System.out.println("aggiungendo le varie risposte al json : " + risp);
        risp = "[" + risp + "]";

        JSONArray array = new JSONArray(risp);
        for (int i=0; i<array.length();i++) {
            System.out.println("aggiungendo : " + array.get(i));
            JSONObject object = array.getJSONObject(i);  
            Domanda domanda = ddm.findByID(object.getInt("id"));
            listaRisposte.add(new CompilazioneDomanda(domanda, compilazione, object.getString("risposta")));
        }

        return listaRisposte;
    }


    public String generatoreCompilazioneKey(int ID){
        return ID + "-" + Math.floor(Math.random() * Math.floor(100000));
    }

    public String generatoreCompilazioneDomandaKey(){
        return Math.floor(Math.random() * Math.floor(100000)) + "";
    }

    public boolean rimuoviCompilazione(String idCompilazione) {
        return cdm.remove(idCompilazione);
    }

    public Compilazione cercaCompilazione(int id){
        return cdm.findByID(id);
    }

    public boolean modificaCompilaizone(int compilazioneId, List<String> risposte){
        Compilazione compVecchia = cdm.findByID(compilazioneId);
        Compilazione compModificata = creaCompilazione(compVecchia.getCompilatore(), compVecchia.getQuestionarioId().getID());
        List<CompilazioneDomanda> listaRisposteModificate = creaRisposteCompilazione(compModificata, risposte);
        cdm.remove(compilazioneId);
        cdm.insert(compModificata, listaRisposteModificate);
        return true;
    }

	public void salvaQuestionario(Questionario questionario) {
		System.out.println("Aggiungendo al database il questionario appena generato..");
		qdm.insert(questionario);
	}
}