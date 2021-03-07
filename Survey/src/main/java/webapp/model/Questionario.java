package webapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import java.util.Set;
import java.util.HashSet;


@Entity(name = "questionari")
@Table(name = "questionari")
public class Questionario {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Categoria")
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "Creatore")
    private UtenteRegistrato creatore;

    @OneToMany(mappedBy="questionarioId")
    private Set<Compilazione> compilazioni = new HashSet<>();


    @ManyToMany
    @JoinTable(
        name = "domandaquestionario",
        joinColumns = @JoinColumn(name = "Questionario_ID"),
        inverseJoinColumns = @JoinColumn(name = "Domanda_ID")
    )
    private Set<Domanda> domande = new HashSet<>();
    

    //------------------> Costruttore

    public Questionario(String nome, String categoria, UtenteRegistrato creatore){
        this.setNome(nome);
        this.setCategoria(categoria);
        this.setCreatore(creatore);
    }

    //------------------> Getters & Setters
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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

    public UtenteRegistrato getCreatore() {
        return creatore;
    }

    public void setCreatore(UtenteRegistrato creatore) {
        this.creatore = creatore;
    }

    public Set<Compilazione> getCompilazioni() {
        return this.compilazioni;
    }

    public void setCompilazioni(Set<Compilazione> compilazioni) {
        this.compilazioni = compilazioni;
    }

    public Set<Domanda> getDomande() {
        return this.domande;
    }

    public void setDomande(Set<Domanda> domande) {
        this.domande = domande;
    }

}