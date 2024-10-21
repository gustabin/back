/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * The Class FinalizarTransferenciaFondoInView.
 */
public class FinalizarTransferenciaFondoInView {

	/** The codigo fondo. */
	private String codigoFondo;

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The codigo destino. */
	private String codigoFondoDest;

	/** The importe neto. */
	private String importeNeto;

	/** The moneda. */
	private String moneda;

	/** The dentro del perfil. */
	private String dentroDelPerfil;

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the cuentaTitulo to set
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the codigo fondo dest.
	 *
	 * @return the codigoFondoDest
	 */
	public String getCodigoFondoDest() {
		return codigoFondoDest;
	}

	/**
	 * Sets the codigo fondo dest.
	 *
	 * @param codigoFondoDest
	 *            the codigoFondoDest to set
	 */
	public void setCodigoFondoDest(String codigoFondoDest) {
		this.codigoFondoDest = codigoFondoDest;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importeNeto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the importeNeto to set
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the dentro del perfil.
	 *
	 * @return the dentroDelPerfil
	 */
	public String getDentroDelPerfil() {
		return dentroDelPerfil;
	}

	/**
	 * Sets the dentro del perfil.
	 *
	 * @param dentroDelPerfil
	 *            the dentroDelPerfil to set
	 */
	public void setDentroDelPerfil(String dentroDelPerfil) {
		this.dentroDelPerfil = dentroDelPerfil;
	}

}
