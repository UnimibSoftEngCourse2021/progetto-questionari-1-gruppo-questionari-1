package webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compilazioni")
public class Compilazione {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "Questionario_ID")
    private Questionario questionarioId;

    @ManyToOne
    @JoinColumn(name = "Compilatore")
    private UtenteRegistrato compilatore;

    @OneToMany(mappedBy = "compilazioneId")
    private Set<CompilazioneDomanda> domande = new HashSet<>();

    public Compilazione() {
    	super();
    }
    
    public Compilazione(Questionario questionario, UtenteRegistrato compilatore) {
        this.setQuestionarioId(questionario);
        this.setCompilatore(compilatore);
    }

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public Questionario getQuestionarioId() {
        return this.questionarioId;
    }

    public void setQuestionarioId(Questionario questionarioId) {
        this.questionarioId = questionarioId;
    }

    public UtenteRegistrato getCompilatore() {
        return this.compilatore;
    }

    public void setCompilatore(UtenteRegistrato compilatore) {
        this.compilatore = compilatore;
    }

    public Set<CompilazioneDomanda> getDomande() {
        return this.domande;
    }

}