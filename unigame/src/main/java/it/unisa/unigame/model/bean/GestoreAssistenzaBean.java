package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class GestoreAssistenzaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String codice_fiscale;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
	private final String ruolo = "gestAssist";
	private int retribuzione_annuale;
	
	public GestoreAssistenzaBean() {
		this.codice_fiscale = null;
		this.nome = null;
		this.cognome = null;
		this.username = null;
		this.email = null;
		this.password = null;
		this.retribuzione_annuale = -1;
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRetribuzione_annuale() {
		return retribuzione_annuale;
	}

	public void setRetribuzione_annuale(int retribuzione_annuale) {
		this.retribuzione_annuale = retribuzione_annuale;
	}

	public String getRuolo() {
		return ruolo;
	}

	@Override
	public String toString() {
		return "GestoreAssistenzaBean [codice_fiscale=" + codice_fiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", email=" + email + ", password=" + password + ", ruolo=" + ruolo
				+ ", retribuzione_annuale=" + retribuzione_annuale + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GestoreAssistenzaBean other = (GestoreAssistenzaBean) obj;
		return Objects.equals(codice_fiscale, other.codice_fiscale) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(email, other.email) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && retribuzione_annuale == other.retribuzione_annuale
				&& Objects.equals(ruolo, other.ruolo) && Objects.equals(username, other.username);
	}
	
	
	
	
}