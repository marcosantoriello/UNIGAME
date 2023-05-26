package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrdineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private LocalDateTime data_e_ora;
	private long num_carta;
	private float importo_totale;
	private String cf_cliente;
	
	public OrdineBean() {
		this.id=0;
		this.data_e_ora=null;
		this.num_carta=0;
		this.importo_totale=0;
		this.cf_cliente=null;
	}
	
	public String getCodice_fiscale() {
		return cf_cliente;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.cf_cliente = codice_fiscale;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getData_e_ora() {
		return data_e_ora;
	}

	public void setData_e_ora(LocalDateTime dataEOra) {
		this.data_e_ora= dataEOra;
	}
	
	public long getNum_carta() {
		return num_carta;
	}

	public void setNum_carta(long carta) {
		this.num_carta=carta;
	}
	
	public void setCodice_fiscale(long carta) {
		this.num_carta = carta;
	}
	
	public float getImporto_totale() {
		return importo_totale;
	}

	public void setImporto_totale(float importo) {
		this.importo_totale = importo;
	}

	@Override
	public String toString() {
		return "OrdineBean [id=" + id + ", data_e_ora=" + data_e_ora + ", num_carta=" + num_carta + ", importo_totale="
				+ importo_totale + ", cf_cliente=" + cf_cliente + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdineBean other = (OrdineBean) obj;
		return Objects.equals(cf_cliente, other.cf_cliente) && Objects.equals(data_e_ora, other.data_e_ora)
				&& id == other.id && Float.floatToIntBits(importo_totale) == Float.floatToIntBits(other.importo_totale)
				&& num_carta == other.num_carta;
	}
	
}