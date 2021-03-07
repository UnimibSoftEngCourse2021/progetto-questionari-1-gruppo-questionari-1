

package webapp.model;

import javax.persistence.Embeddable;

@Embeddable
public class CompilazioneDomandaKeys{

    private Domanda domandaId;
    private Compilazione compilazioneId;

    public CompilazioneDomandaKeys(Domanda domandaId, Compilazione compilazioneId){
        this.compilazioneId = compilazioneId;
        this.domandaId = domandaId;
    }
    
}