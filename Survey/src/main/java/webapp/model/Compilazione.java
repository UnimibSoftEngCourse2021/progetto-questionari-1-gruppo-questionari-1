package webapp.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "compilazione")
@Table(name = "compilazione")
public class Compilazione {

    @Id
    @Column(name = "ID")
    private String ID;
    
    @ManyToOne
    private Questionario Questionario_ID;

    @ManyToOne
    private UtenteRegistrato compilatore;

    
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}