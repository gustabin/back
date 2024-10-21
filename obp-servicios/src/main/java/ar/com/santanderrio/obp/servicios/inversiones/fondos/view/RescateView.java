/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

/**
 * The Class RescateView.
 */
public class RescateView {
	/** The tipo banca. */
	private String tipoBanca;

	/** The cuentas titulo. */
	private List<CuentaTituloView> cuentasTitulo;

	/** The mensaje informacion. */
	private String mensajeInformacion;

	/**
	 * Gets the tipo banca.
	 *
	 * @return the tipoBanca
	 */
	public String getTipoBanca() {
		return tipoBanca;
	}

	/**
	 * Sets the tipo banca.
	 *
	 * @param tipoBanca
	 *            the tipoBanca to set
	 */
	public void setTipoBanca(String tipoBanca) {
		this.tipoBanca = tipoBanca;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentasTitulo
	 */
	public List<CuentaTituloView> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the cuentasTitulo to set
	 */
	public void setCuentasTitulo(List<CuentaTituloView> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}

	/**
	 * Gets the mensaje informacion.
	 *
	 * @return the mensajeInformacion
	 */
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	/**
	 * Sets the mensaje informacion.
	 *
	 * @param mensajeInformacion
	 *            the mensajeInformacion to set
	 */
	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}

}
