package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SoftwareHouseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String locazione;
	private LocalDate fondazione;
	
	public SoftwareHouseBean() {
		nome = "";
		locazione = "";
		fondazione = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocazione() {
		return locazione;
	}

	public void setLocazione(String locazione) {
		this.locazione = locazione;
	}

	public LocalDate getFondazione() {
		return fondazione;
	}

	public void setFondazione(LocalDate fondazione) {
		this.fondazione = fondazione;
	}

	@Override
	public String toString() {
		return "SoftwareHouseBean [nome=" + nome + ", locazione=" + locazione + ", fondazione=" + fondazione + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoftwareHouseBean other = (SoftwareHouseBean) obj;
		return Objects.equals(fondazione, other.fondazione) && Objects.equals(locazione, other.locazione)
				&& Objects.equals(nome, other.nome);
	}
	
	
}
