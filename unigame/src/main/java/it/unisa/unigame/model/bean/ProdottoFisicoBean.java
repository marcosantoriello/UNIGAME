package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class ProdottoFisicoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private float prezzo;
	private int quantità;
	private boolean disponibile;

	public ProdottoFisicoBean() {
		this.id=0;
		this.nome=null;
		this.prezzo=0;
		this.quantità=0;
		this.disponibile=false;
	}

	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setNome(String name) {
		this.nome=name;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setPrezzo(float price) {
		this.prezzo=price;
	}
	
	public float getPrezzo() {
		return this.prezzo;
	}
	
	public void setQuantità(int qta) {
		this.quantità=qta;
	}
	
	public int getQuantità() {
		return this.quantità;
	}
	
	public void setDisponibilità(Boolean d) {
		this.disponibile=d;
	}
	
	public boolean isDisponibile() {
		return this.disponibile;
	}
	
	@Override
	public String toString() {
		return "ProdottoFisicoBean [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + ", quantità=" + quantità
				+ ", disponibile=" + disponibile + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProdottoFisicoBean)) {
			return false;
		}
		ProdottoFisicoBean other = (ProdottoFisicoBean) obj;
		return disponibile == other.disponibile && id == other.id && Objects.equals(nome, other.nome)
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo) && quantità == other.quantità;
	}
	
	
	
	
	
}