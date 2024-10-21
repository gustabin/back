/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities;

/**
 * The Class LimiteClase.
 */
public class LimiteClase {

	/** The limite. */
	private String limite;

	/** The clase. */
	private String clase;

	/**
	 * Instantiates a new limite clase.
	 */
	public LimiteClase() {
		super();
	}

	/**
	 * Instantiates a new limite clase.
	 *
	 * @param limite
	 *            the limite
	 */
	public LimiteClase(String limite) {
		super();
		this.limite = limite;
	}

	/**
	 * Instantiates a new limite clase.
	 *
	 * @param limite
	 *            the limite
	 * @param clase
	 *            the clase
	 */
	public LimiteClase(String limite, String clase) {
		super();
		this.limite = limite;
		this.clase = clase;
	}

	/**
	 * Gets the limite.
	 *
	 * @return the limite
	 */
	public String getLimite() {
		return limite;
	}

	/**
	 * Sets the limite.
	 *
	 * @param limite
	 *            the new limite
	 */
	public void setLimite(String limite) {
		this.limite = limite;
	}

	/**
	 * Gets the clase.
	 *
	 * @return the clase
	 */
	public String getClase() {
		return clase;
	}

	/**
	 * Sets the clase.
	 *
	 * @param clase
	 *            the new clase
	 */
	public void setClase(String clase) {
		this.clase = clase;
	}

}
