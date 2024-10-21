/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author juan.pablo.picate
 *
 */
public class PrecancelacionPlazoFijoInEntity {

	/** The opcion. */
	private String opcion;

	/** numerico. */
	private String cuentaPlazo;

	/** numerico. */
	private String secuencia;

	/** numerico. */
	private String tipoCuentaCredito;

	/** numerico. */
	private String sucursalCuentaCredito;

	/** numerico. */
	private String nroCuentaCredito;

	/** The divisa cuenta credito. */
	private String divisaCuentaCredito;

	/**
	 * Gets the opcion.
	 *
	 * @return the opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * Sets the opcion.
	 *
	 * @param opcion
	 *            the opcion to set
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuentaPlazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the cuentaPlazo to set
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the tipo cuenta credito.
	 *
	 * @return the tipoCuentaCredito
	 */
	public String getTipoCuentaCredito() {
		return tipoCuentaCredito;
	}

	/**
	 * Sets the tipo cuenta credito.
	 *
	 * @param tipoCuentaCredito
	 *            the tipoCuentaCredito to set
	 */
	public void setTipoCuentaCredito(String tipoCuentaCredito) {
		this.tipoCuentaCredito = tipoCuentaCredito;
	}

	/**
	 * Gets the sucursal cuenta credito.
	 *
	 * @return the sucursalCuentaCredito
	 */
	public String getSucursalCuentaCredito() {
		return sucursalCuentaCredito;
	}

	/**
	 * Sets the sucursal cuenta credito.
	 *
	 * @param sucursalCuentaCredito
	 *            the sucursalCuentaCredito to set
	 */
	public void setSucursalCuentaCredito(String sucursalCuentaCredito) {
		this.sucursalCuentaCredito = sucursalCuentaCredito;
	}

	/**
	 * Gets the nro cuenta credito.
	 *
	 * @return the nroCuentaCredito
	 */
	public String getNroCuentaCredito() {
		return nroCuentaCredito;
	}

	/**
	 * Sets the nro cuenta credito.
	 *
	 * @param nroCuentaCredito
	 *            the nroCuentaCredito to set
	 */
	public void setNroCuentaCredito(String nroCuentaCredito) {
		this.nroCuentaCredito = nroCuentaCredito;
	}

	/**
	 * Gets the divisa cuenta credito.
	 *
	 * @return the divisaCuentaCredito
	 */
	public String getDivisaCuentaCredito() {
		return divisaCuentaCredito;
	}

	/**
	 * Sets the divisa cuenta credito.
	 *
	 * @param divisaCuentaCredito
	 *            the divisaCuentaCredito to set
	 */
	public void setDivisaCuentaCredito(String divisaCuentaCredito) {
		this.divisaCuentaCredito = divisaCuentaCredito;
	}

}
