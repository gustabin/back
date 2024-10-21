/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

/**
 * The Class ResultadoCarteraCliente.
 */
public class ResultadoCarteraCliente {
		
	/** The codigo cartera. */
	private String codigoCartera;
	
	/** The descripcion cartera. */
	private String descripcionCartera;
	
	/** The cuenta. */
	private CuentaACView cuenta;
	
	/** The por defecto. */
	private Boolean porDefecto = Boolean.FALSE;

	/**
	 * Gets the codigo cartera.
	 *
	 * @return the codigo cartera
	 */
	public String getCodigoCartera() {
		return codigoCartera;
	}

	/**
	 * Sets the codigo cartera.
	 *
	 * @param codigoCartera
	 *            the new codigo cartera
	 */
	public void setCodigoCartera(String codigoCartera) {
		this.codigoCartera = codigoCartera;
	}

	/**
	 * Gets the descripcion cartera.
	 *
	 * @return the descripcion cartera
	 */
	public String getDescripcionCartera() {
		return descripcionCartera;
	}

	/**
	 * Sets the descripcion cartera.
	 *
	 * @param descripcionCartera
	 *            the new descripcion cartera
	 */
	public void setDescripcionCartera(String descripcionCartera) {
		this.descripcionCartera = descripcionCartera;
	}

	/**
	 * Gets the por defecto.
	 *
	 * @return the por defecto
	 */
	public Boolean getPorDefecto() {
		return porDefecto;
	}

	/**
	 * Sets the por defecto.
	 *
	 * @param porDefecto
	 *            the new por defecto
	 */
	public void setPorDefecto(Boolean porDefecto) {
		this.porDefecto = porDefecto;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public CuentaACView getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(CuentaACView cuenta) {
		this.cuenta = cuenta;
	}
	
}
