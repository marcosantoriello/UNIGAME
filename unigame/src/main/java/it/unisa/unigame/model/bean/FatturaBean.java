package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FatturaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int ordine;
	private float importo_totale;
	private LocalDateTime data_e_ora;
	
	public FatturaBean() {
		this.id=0;
		this.ordine=0;
		this.importo_totale=0;
		this.data_e_ora=null;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrdine() {
		return this.ordine;
	}

	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}

	public float getImporto_totale() {
		return this.importo_totale;
	}

	public void setImporto_totale(float importo_totale) {
		this.importo_totale = importo_totale;
	}

	public LocalDateTime getData_e_ora() {
		return this.data_e_ora;
	}
	
	public void setData_e_ora(LocalDateTime data_e_ora) {
		this.data_e_ora = data_e_ora;
	}

	@Override
	public String toString() {
		return "FatturaBean [id=" + id + ", ordine=" + ordine + ", importo_totale=" + importo_totale + ", data_e_ora="
				+ data_e_ora + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FatturaBean)) {
			return false;
		}
		FatturaBean other = (FatturaBean) obj;
		return Objects.equals(data_e_ora, other.data_e_ora) && id == other.id
				&& Float.floatToIntBits(importo_totale) == Float.floatToIntBits(other.importo_totale)
				&& ordine == other.ordine;
	}
	
}