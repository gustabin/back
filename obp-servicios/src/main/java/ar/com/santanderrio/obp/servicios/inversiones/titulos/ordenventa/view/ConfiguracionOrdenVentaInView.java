/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

/**
 * The Class ConfiguracionOrdenVentaInView.
 */
public class ConfiguracionOrdenVentaInView {

	/** The codigo rossi. */
	private String codigoRossi;

	/** The numero cuenta titulo. */
	private String numeroCuentaTitulo;
	
	/** The numero cuenta operativa. */
	private String numeroCuentaOperativa;

	/** The plazo. */
	private String plazo;

	/** The tipo especie. */
	private String tipoEspecie;

	/** The reintentar. */
	private Boolean reintentar = false;

	/** The ingreso desde grilla. */
	private Boolean ingresoDesdeGrilla = false;

	/** The es banca privada. */
	private Boolean esBancaPrivada = false;

	/**
	 * Gets the codigo rossi.
	 *
	 * @return the codigoRossi
	 */
	public String getCodigoRossi() {
		return codigoRossi;
	}

	/**
	 * Sets the codigo rossi.
	 *
	 * @param codigoRossi
	 *            the codigoRossi to set
	 */
	public void setCodigoRossi(String codigoRossi) {
		this.codigoRossi = codigoRossi;
	}

	/**
	 * Gets the numero cuenta titulo.
	 *
	 * @return the numeroCuentaTitulo
	 */
	public String getNumeroCuentaTitulo() {
		return numeroCuentaTitulo;
	}

	/**
	 * Sets the numero cuenta titulo.
	 *
	 * @param numeroCuentaTitulo
	 *            the numeroCuentaTitulo to set
	 */
	public void setNumeroCuentaTitulo(String numeroCuentaTitulo) {
		this.numeroCuentaTitulo = numeroCuentaTitulo;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipo especie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the new tipo especie
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public Boolean getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(Boolean reintentar) {
		this.reintentar = reintentar;
	}

	/**
	 * Gets the ingreso desde grilla.
	 *
	 * @return the ingreso desde grilla
	 */
	public Boolean getIngresoDesdeGrilla() {
		return ingresoDesdeGrilla;
	}

	/**
	 * Sets the ingreso desde grilla.
	 *
	 * @param ingresoDesdeGrilla
	 *            the new ingreso desde grilla
	 */
	public void setIngresoDesdeGrilla(Boolean ingresoDesdeGrilla) {
		this.ingresoDesdeGrilla = ingresoDesdeGrilla;
	}

	/**
	 * Gets the es banca privada.
	 *
	 * @return the esBancaPrivada
	 */
	public Boolean getEsBancaPrivada() {
		return esBancaPrivada;
	}

	/**
	 * Sets the es banca privada.
	 *
	 * @param esBancaPrivada
	 *            the esBancaPrivada to set
	 */
	public void setEsBancaPrivada(Boolean esBancaPrivada) {
		this.esBancaPrivada = esBancaPrivada;
	}

	/**
	 * Gets the numero cuenta operativa.
	 *
	 * @return the numeroCuentaOperativa
	 */
	public String getNumeroCuentaOperativa() {
		return numeroCuentaOperativa;
	}

	/**
	 * Sets the numero cuenta operativa.
	 *
	 * @param numeroCuentaOperativa
	 *            the numeroCuentaOperativa to set
	 */
	public void setNumeroCuentaOperativa(String numeroCuentaOperativa) {
		this.numeroCuentaOperativa = numeroCuentaOperativa;
	}

}
