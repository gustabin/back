/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

import java.util.List;

/**
 * The Class ComprobanteAltaReimpresionTarjetas.
 */
public class ComprobanteAltaReimpresionTarjetas {

	/** The tarjeta solicitadas. */
	private List<TarjetaSolicitadaDTO> tarjetaSolicitadas;

	/** The fecha hora operacion. */
	private String fechaHoraOperacion;

	/** The mensaje descripcion. */
	private String mensajeDescripcion;

	/** The domicilio. */
	private String domicilio;

	/**
	 * Gets the tarjeta solicitadas.
	 *
	 * @return the tarjeta solicitadas
	 */
	public List<TarjetaSolicitadaDTO> getTarjetaSolicitadas() {
		return tarjetaSolicitadas;
	}

	/**
	 * Sets the tarjeta solicitadas.
	 *
	 * @param tarjetaSolicitadas
	 *            the new tarjeta solicitadas
	 */
	public void setTarjetaSolicitadas(List<TarjetaSolicitadaDTO> tarjetaSolicitadas) {
		this.tarjetaSolicitadas = tarjetaSolicitadas;
	}

	/**
	 * Gets the fecha hora operacion.
	 *
	 * @return the fecha hora operacion
	 */
	public String getFechaHoraOperacion() {
		return fechaHoraOperacion;
	}

	/**
	 * Sets the fecha hora operacion.
	 *
	 * @param fechaHoraOperacion
	 *            the new fecha hora operacion
	 */
	public void setFechaHoraOperacion(String fechaHoraOperacion) {
		this.fechaHoraOperacion = fechaHoraOperacion;
	}

	/**
	 * Gets the mensaje descripcion.
	 *
	 * @return the mensaje descripcion
	 */
	public String getMensajeDescripcion() {
		return mensajeDescripcion;
	}

	/**
	 * Sets the mensaje descripcion.
	 *
	 * @param mensajeDescripcion
	 *            the new mensaje descripcion
	 */
	public void setMensajeDescripcion(String mensajeDescripcion) {
		this.mensajeDescripcion = mensajeDescripcion;
	}

	/**
	 * Gets the domicilio.
	 *
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Sets the domicilio.
	 *
	 * @param domicilio
	 *            the new domicilio
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

}
