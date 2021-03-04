package webapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CompilazioneDomanda implements Serializable{
    
    
    @Id
    private int temp;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "Domanda_ID")
    private Domanda Domanda_ID;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "Compilazione_ID")
    private Compilazione Compilazione_ID;


}
