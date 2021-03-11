package webapp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "compilazionidomande")
public class CompilazioneDomanda{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "Domanda")
    private Domanda domandaId;

    @ManyToOne
    @JoinColumn(name = "Compilazione_ID")
    private Compilazione compilazioneId;

    @Column(name="Risposta")
    private String risposta;

    public CompilazioneDomanda() {
    	super();
    }
    
    public CompilazioneDomanda(Domanda domandaId, Compilazione compilazioneId, String risposta) {
        this.domandaId = domandaId;
        this.compilazioneId = compilazioneId;
        this.setRisposta(risposta);
    }

    public String getRisposta() {
        return this.risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }
    
    public Domanda getDomanda() {
    	return this.domandaId;
    }
}
