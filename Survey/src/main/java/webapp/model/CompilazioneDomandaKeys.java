

package webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompilazioneDomandaKeys implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "Domanda_ID")
    private Domanda IdDomanda;

    @Column(name = "Compilazione_ID")
    private Compilazione IdCompilazione;
    
}