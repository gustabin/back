/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

/**
 * The Class RescateDesdeGrillaInView.
 */
public class RescateDesdeGrillaInView {

	/** The numero cuenta operativa. */
	private String numeroCuentaOperativa;

	/** The codigo fondo. */
	private String codigoFondo;
	
	private String banca;

	/**
	 * Gets the numero cuenta operativa.
	 *
	 * @return the numero cuenta operativa
	 */
	public String getNumeroCuentaOperativa() {
		return numeroCuentaOperativa;
	}

	/**
	 * Sets the numero cuenta operativa.
	 *
	 * @param numeroCuentaTitulo
	 *            the new numero cuenta operativa
	 */
	public void setNumeroCuentaOperativa(String numeroCuentaTitulo) {
		this.numeroCuentaOperativa = numeroCuentaTitulo;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}
	
}
