/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

/**
 * The Class FinalizarAdhesionPDCResponse.
 */
public class FinalizarAdhesionPDCResponse {

	/** The mensaje. */
	private String mensaje;
	
	/** The legal. */
	private String legal;
	
	/** The moneda. */
	private String moneda;
	
	/** The fecha solicitud. */
	private String fechaSolicitud;
	
	/** The fecha vigencia. */
	private String fechaVigencia;
	
	/** The comprobante. */
	private String comprobante;
	

	/** The fecha fecha comprobante. */
	private String fechaComprobante;
	
	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the fecha solicitud.
	 *
	 * @return the fecha solicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud
	 *            the new fecha solicitud
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Gets the fecha vigencia.
	 *
	 * @return the fecha vigencia
	 */
	public String getFechaVigencia() {
		return fechaVigencia;
	}

	/**
	 * Sets the fecha vigencia.
	 *
	 * @param fechaVigencia
	 *            the new fecha vigencia
	 */
	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * @return the fechaComprobante
	 */
	public String getFechaComprobante() {
		return fechaComprobante;
	}

	/**
	 * @param fechaComprobante the fechaComprobante to set
	 */
	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}
}
