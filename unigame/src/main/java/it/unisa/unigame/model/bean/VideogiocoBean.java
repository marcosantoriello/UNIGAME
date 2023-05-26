package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class VideogiocoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private float prezzo;
	private int quantità;
	private boolean disponibile;
	public enum Pegi{tre, sette, dodici, sedici ,diciotto};
	private Pegi pegi;
	private int anno_produzione;
	private String produttore;
	
	public VideogiocoBean() {
		this.id=0;
		this.nome=null;
		this.prezzo=0;
		this.quantità=0;
		this.disponibile=false;
		this.pegi=null;
		this.anno_produzione=0;
		this.produttore=null;
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
	
	public void setPegi(Pegi p) {
		this.pegi=p;
	}
	
	public Pegi getPegi() {
		return this.pegi;
	}
	
	public void setAnno_produzione(int year) {
		this.anno_produzione=year;
	}
	
	public int getAnno_produzione() {
		return this.anno_produzione;
	}
	
	public void setProduttore(String produttore) {
		this.produttore=produttore;
	}
	
	public String getProduttore() {
		return this.produttore;
	}

	@Override
	public String toString() {
		return "VideogiocoBean [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + ", quantità=" + quantità
				+ ", disponibile=" + disponibile + ", pegi=" + pegi + ", annoProduzione=" + anno_produzione
				+ ", produttore=" + produttore + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof VideogiocoBean)) {
			return false;
		}
		VideogiocoBean other = (VideogiocoBean) obj;
		return anno_produzione == other.anno_produzione && disponibile == other.disponibile && id == other.id
				&& Objects.equals(nome, other.nome) && pegi == other.pegi
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo)
				&& Objects.equals(produttore, other.produttore) && quantità == other.quantità;
	}	
}