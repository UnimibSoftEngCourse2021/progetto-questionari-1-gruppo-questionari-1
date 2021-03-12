package webapp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Categoria")
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "Creatore")
    private UtenteRegistrato creatore;

    @OneToMany(mappedBy="questionarioId", orphanRemoval = true, fetch=FetchType.EAGER)
    private Set<Compilazione> compilazioni = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "domandequestionari",
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

    public Questionario(){

    }

    //------------------> Getters & Setters
    public int getID() {
        return id;
    }

    public void setID(int id) {
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