package it.unisa.unigame.model.bean;

import java.io.Serializable;

public class ComprendeOVBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int videogioco;
	private int ordine;
	
	public ComprendeOVBean(){
		this.videogioco=0;
		this.ordine=0;
	}
	
	public void setVideogioco(int id) {
		this.videogioco=id;
	}
	
	public int getVideogioco() {
		return this.videogioco;
	}
	
	public void setOrdine(int id) {
		this.ordine=id;
	}
	
	public int getOrdine() {
		return this.ordine;
	}

	@Override
	public String toString() {
		return "ComprendeOPBean [videogioco=" + videogioco + ", ordine=" + ordine + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ComprendeOVBean)) {
			return false;
		}
		ComprendeOVBean other = (ComprendeOVBean) obj;
		return ordine == other.ordine && videogioco == other.videogioco;
	}		
}