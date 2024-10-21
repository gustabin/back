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
public class ConsultaPrecancelablePlazoFijoInEntity {

	/** numerico*. */
	private String cuentaPlazo;

	/** numerico. */
	private String secuencia;

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

}
