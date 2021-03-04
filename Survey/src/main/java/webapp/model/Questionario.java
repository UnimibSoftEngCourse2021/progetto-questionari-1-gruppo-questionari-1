package webapp.model;

import javax.persistence.*;


@Entity(name = "Questionario")
@Table(name = "Questionario")
public class Questionario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private String ID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    @ManyToOne
    private Utente creatore;

    @OneToMany(mappedBy="...")
    private set<Compilazione> compilazioni = new HashSet<Compilazioni>();

    //------------------> Costruttore

    public Questionario(String nome, String categoria, Utente creatore){
        this.setNome(nome);
        this.setCategoria(categoria);
        this.setCreatore(creatore);
    }

    //------------------> Getters & Setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Utente getCreatore() {
        return creatore;
    }

    public void setCreatore(Utente creatore) {
        this.creatore = creatore;
    }



}