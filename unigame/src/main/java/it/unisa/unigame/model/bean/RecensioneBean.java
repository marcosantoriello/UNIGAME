package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class RecensioneBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String cliente;
	private int prodotto;
	private int videogioco;
	private LocalDateTime data_e_ora;
	private String descrizione;
	public enum Indice_gradimento{uno, due, tre, quattro, cinque};
	private Indice_gradimento indice_di_gradimento;
	
	public RecensioneBean() {
		this.id=0;
		this.cliente=null;
		this.videogioco=0;
		this.prodotto=0;
		this.data_e_ora=null;
		this.descrizione=null;
		this.indice_di_gradimento=null;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getProdotto() {
		return this.prodotto;
	}

	public void setProdotto(int prodotto) {
		this.prodotto = prodotto;
	}

	public int getVideogioco() {
		return this.videogioco;
	}

	public void setVideogioco(int videogioco) {
		this.videogioco = videogioco;
	}

	public LocalDateTime getData_e_ora() {
		return this.data_e_ora;
	}

	public void setData_e_ora(LocalDateTime data_e_ora) {
		this.data_e_ora = data_e_ora;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Indice_gradimento getIndice_di_gradimento() {
		return this.indice_di_gradimento;
	}

	public void setIndice_di_gradimento(Indice_gradimento indice) {
		this.indice_di_gradimento = indice;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RecensioneBean)) {
			return false;
		}
		RecensioneBean other = (RecensioneBean) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(data_e_ora, other.data_e_ora)
				&& Objects.equals(descrizione, other.descrizione) && id == other.id
				&& indice_di_gradimento == other.indice_di_gradimento && prodotto == other.prodotto
				&& videogioco == other.videogioco;
	}

	@Override
	public String toString() {
		return "RecensioneBean [id=" + id + ", cliente=" + cliente + ", prodotto=" + prodotto + ", videogioco="
				+ videogioco + ", data_e_ora=" + data_e_ora + ", descrizione=" + descrizione + ", indice_di_gradimento="
				+ indice_di_gradimento + "]";
	}
	
}