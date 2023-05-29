package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class TicketBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int num_ticket;
	private LocalDateTime data_e_ora;
	private String cliente;
	private String gestore;
	public enum Categoria{account, pagamento, ordine, altro};
	private Categoria category;
	private String messaggio;
	private boolean risolto;
	
	public TicketBean() {
		this.num_ticket=0;
		this.data_e_ora=null;
		this.cliente=null;
		this.gestore=null;
		this.category=null;
		this.risolto=false;
	}

	public int getNum_ticket() {
		return this.num_ticket;
	}
	
	public void setNum_ticket(int num_ticket) {
		this.num_ticket = num_ticket;
	}

	public LocalDateTime getData_e_ora() {
		return this.data_e_ora;
	}

	public void setData_e_ora(LocalDateTime data_e_ora) {
		this.data_e_ora = data_e_ora;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getGestore() {
		return this.gestore;
	}

	public void setGestore(String gestore) {
		this.gestore = gestore;
	}

	public Categoria getCategory() {
		return this.category;
	}

	public void setCategory(Categoria category) {
		this.category = category;
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public boolean isRisolto() {
		return this.risolto;
	}

	public void setRisolto(boolean risolto) {
		this.risolto = risolto;
	}

	@Override
	public String toString() {
		return "TicketBean [num_ticket=" + num_ticket + ", data_e_ora=" + data_e_ora + ", cliente=" + cliente
				+ ", gestore=" + gestore + ", category=" + category + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TicketBean)) {
			return false;
		}
		TicketBean other = (TicketBean) obj;
		return category == other.category && Objects.equals(cliente, other.cliente)
				&& Objects.equals(data_e_ora, other.data_e_ora) && Objects.equals(gestore, other.gestore)
				&& num_ticket == other.num_ticket;
	}

	
	
}