/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

/**
 * The Class ComprobanteAdhesionEmpleadoEntity.
 */
public class ComprobanteAdhesionEmpleadoEntity {

	/** The Datos Empleado Pago Haberes. */
	private DatosEmpleadoPagoHaberesEntity datosEmpleadoPagoHaberes;

	/** The nro de comprobante. */
	private String nroDeComprobante;

	/** The fecha hora. */
	private String fechaHora;

	/** The mensaje. */
	private String mensaje;

	/** The legales SEO. */
	private String legalesSEO;

	/**
	 * Gets the datos empleado pago haberes.
	 *
	 * @return the datos empleado pago haberes
	 */
	public DatosEmpleadoPagoHaberesEntity getDatosEmpleadoPagoHaberes() {
		return datosEmpleadoPagoHaberes;
	}

	/**
	 * Sets the datos empleado pago haberes.
	 *
	 * @param datosEmpleadoPagoHaberes
	 *            the new datos empleado pago haberes
	 */
	public void setDatosEmpleadoPagoHaberes(DatosEmpleadoPagoHaberesEntity datosEmpleadoPagoHaberes) {
		this.datosEmpleadoPagoHaberes = datosEmpleadoPagoHaberes;
	}

	/**
	 * Gets the nro de comprobante.
	 *
	 * @return the nroDeComprobante
	 */
	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	/**
	 * Sets the nro de comprobante.
	 *
	 * @param nroDeComprobante
	 *            the nroDeComprobante to set
	 */
	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return mensaje
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
	 * Gets the legales SEO.
	 *
	 * @return legalesSEO
	 */
	public String getLegalesSEO() {
		return legalesSEO;
	}

	/**
	 * Sets the legales SEO.
	 *
	 * @param legalesSEO
	 *            the new legales SEO
	 */
	public void setLegalesSEO(String legalesSEO) {
		this.legalesSEO = legalesSEO;
	}

}