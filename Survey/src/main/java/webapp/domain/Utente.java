package webapp.domain;

import java.io.Serializable;

public class Utente implements Serializable {
	
	private static final long serialVersionUID = -7193235450199779454L;
	
	private String email;
	private String nome;
	private String cognome;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
