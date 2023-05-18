package it.unisa.unigame.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class TelefonoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int numero;
	private String cliente_cf;
	
	public TelefonoBean() {
		numero = -1;
		cliente_cf = "";
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCliente_cf() {
		return cliente_cf;
	}

	public void setCliente_cf(String cliente_cf) {
		this.cliente_cf = cliente_cf;
	}

	@Override
	public String toString() {
		return "TelefonoBean [numero=" + numero + ", cliente_cf=" + cliente_cf + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TelefonoBean other = (TelefonoBean) obj;
		return Objects.equals(cliente_cf, other.cliente_cf) && numero == other.numero;
	}
	
	
	
}
