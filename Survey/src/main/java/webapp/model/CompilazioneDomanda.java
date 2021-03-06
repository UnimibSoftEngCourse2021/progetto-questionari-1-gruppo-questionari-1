package webapp.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CompilazioneDomanda{
    
    @EmbeddedId
    CompilazioneDomandaKeys cdk;


    @ManyToOne
    @MapsId("Domanda_ID")
    @JoinColumn(name = "Domanda_ID")
    private Domanda Domanda_ID;

    @ManyToOne
    @MapsId("Compilazione_ID")
    @JoinColumn(name = "Compilazione_ID")
    private Compilazione Compilazione_ID;

    @Column(name="Risposta")
    private String risposta;

    public String getRisposta() {
        return this.risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }
}
