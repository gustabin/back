/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

/**
 * The Class SolicitarPrecancelarInView.
 */
public class SolicitarPrecancelarInView {

	/** The cuenta plazo. */
	private String cuentaPlazo;

	/** The numero secuencia. */
	private String numeroSecuencia;

	/** The fecha constitucion. */
	private String fechaConstitucion;

	/** The uva. */
	private boolean uva;

	public SolicitarPrecancelarInView() {
	}

	public SolicitarPrecancelarInView(SimularPrecancelarUVAInView precancelarUvaInView) {
		super();
		this.cuentaPlazo = precancelarUvaInView.getCuentaPlazo();
		this.numeroSecuencia = precancelarUvaInView.getNumeroSecuencia();
		this.fechaConstitucion = precancelarUvaInView.getFechaConstitucion();
		this.setUva(true);
	}

	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuenta plazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo the new cuenta plazo
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the numero secuencia.
	 *
	 * @return the numero secuencia
	 */
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	/**
	 * Sets the numero secuencia.
	 *
	 * @param numeroSecuencia the new numero secuencia
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	/**
	 * Gets the fecha constitucion.
	 *
	 * @return the fecha constitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * Sets the fecha constitucion.
	 *
	 * @param fechaConstitucion the new fecha constitucion
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	public boolean isUva() {
		return uva;
	}

	public void setUva(boolean uva) {
		this.uva = uva;
	}

}
