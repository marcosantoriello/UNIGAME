package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codice_fiscale;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
	private final String ruolo = "cliente";
	private String ind_fatturazione;
	private LocalDate data_di_nascita;
	private int conta_ordine_rel;
	private boolean sospeso;
	
	public ClienteBean() {
		this.codice_fiscale = null;
		this.nome = null;
		this.cognome = null;
		this.username = null;
		this.email = null;
		this.password = null;
		this.ind_fatturazione = null;
		this.data_di_nascita = null;
		this.conta_ordine_rel = 0;
		this.sospeso = false;	
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

	public String getInd_fatturazione() {
		return ind_fatturazione;
	}

	public void setInd_fatturazione(String ind_fatturazione) {
		this.ind_fatturazione = ind_fatturazione;
	}

	public LocalDate getData_di_nascita() {
		return data_di_nascita;
	}

	public void setData_di_nascita(LocalDate data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}

	public int getConta_ordine_rel() {
		return conta_ordine_rel;
	}

	public void setConta_ordine_rel(int conta_ordine_rel) {
		this.conta_ordine_rel = conta_ordine_rel;
	}

	public boolean isSospeso() {
		return sospeso;
	}

	public void setSospeso(boolean sospeso) {
		this.sospeso = sospeso;
	}

	public String getRuolo() {
		return ruolo;
	}

	@Override
	public String toString() {
		return "ClienteBean [codice_fiscale=" + codice_fiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", email=" + email + ", password=" + password + ", ruolo=" + ruolo
				+ ", ind_fatturazione=" + ind_fatturazione + ", data_di_nascita=" + data_di_nascita
				+ ", conta_ordine_rel=" + conta_ordine_rel + ", sospeso=" + sospeso + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteBean other = (ClienteBean) obj;
		return Objects.equals(codice_fiscale, other.codice_fiscale) && Objects.equals(cognome, other.cognome)
				&& conta_ordine_rel == other.conta_ordine_rel && Objects.equals(data_di_nascita, other.data_di_nascita)
				&& Objects.equals(email, other.email) && Objects.equals(ind_fatturazione, other.ind_fatturazione)
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password)
				&& Objects.equals(ruolo, other.ruolo) && sospeso == other.sospeso
				&& Objects.equals(username, other.username);
	}
	
	
	
	
}
