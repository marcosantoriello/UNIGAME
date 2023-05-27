package it.unisa.unigame.model.bean;

import java.io.Serializable;

public class ComprendeOPBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int prodotto;
	private int ordine;
	
	public ComprendeOPBean(){
		this.prodotto=0;
		this.ordine=0;
	}
	
	public void setProdotto(int id) {
		this.prodotto=id;
	}
	
	public int getProdotto() {
		return this.prodotto;
	}
	
	public void setOrdine(int id) {
		this.ordine=id;
	}
	
	public int getOrdine() {
		return this.ordine;
	}

	@Override
	public String toString() {
		return "ComprendeOPBean [prodotto=" + prodotto + ", ordine=" + ordine + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ComprendeOPBean)) {
			return false;
		}
		ComprendeOPBean other = (ComprendeOPBean) obj;
		return ordine == other.ordine && prodotto == other.prodotto;
	}	
}