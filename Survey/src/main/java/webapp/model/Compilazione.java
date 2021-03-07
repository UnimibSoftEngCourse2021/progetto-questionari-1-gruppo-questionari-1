package webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compilazioni")
public class Compilazione {

    @Id
    @Column(name = "ID")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "Questionario_ID")
    private Questionario questionarioId;

    @ManyToOne
    @JoinColumn(name = "Compilatore")
    private UtenteRegistrato compilatore;

    @OneToMany(mappedBy = "Compilazione_ID")
    private Set<CompilazioneDomanda> domande = new HashSet<>();

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }

}