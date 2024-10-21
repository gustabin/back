/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities;

import java.util.List;

/**
 * The Class LimiteDebito.
 */
public class LimiteDebito {

	/** The clave. */
	private String clave;

	/** The valores. */
	private List<LimiteClase> valores;

	/**
	 * Instantiates a new limite debito.
	 */
	public LimiteDebito() {
		super();
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave
	 *            the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the valores.
	 *
	 * @return the valores
	 */
	public List<LimiteClase> getValores() {
		return valores;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valores
	 *            the new valor
	 */
	public void setValor(List<LimiteClase> valores) {
		this.valores = valores;
	}

}
