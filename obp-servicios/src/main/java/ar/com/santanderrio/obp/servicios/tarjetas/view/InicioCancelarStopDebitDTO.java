/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

/**
 * The Class InicioCancelarStopDebitDTO.
 */
public class InicioCancelarStopDebitDTO {

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The fecha de solicitud. */
	private String fechaDeSolicitud;

	/** The alias. */
	private String alias;

	/** The mensaje de inicio. */
	private String mensajeDeInicio;

	/** The mensaje informativo. */
	private String mensajeInformativo;

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the fecha de solicitud.
	 *
	 * @return the fecha de solicitud
	 */
	public String getFechaDeSolicitud() {
		return fechaDeSolicitud;
	}

	/**
	 * Sets the fecha de solicitud.
	 *
	 * @param fechaDeSolicitud
	 *            the new fecha de solicitud
	 */
	public void setFechaDeSolicitud(String fechaDeSolicitud) {
		this.fechaDeSolicitud = fechaDeSolicitud;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the mensaje de inicio.
	 *
	 * @return the mensaje de inicio
	 */
	public String getMensajeDeInicio() {
		return mensajeDeInicio;
	}

	/**
	 * Sets the mensaje de inicio.
	 *
	 * @param mensajeDeInicio
	 *            the new mensaje de inicio
	 */
	public void setMensajeDeInicio(String mensajeDeInicio) {
		this.mensajeDeInicio = mensajeDeInicio;
	}

	/**
	 * Gets the mensaje informativo.
	 *
	 * @return the mensaje informativo
	 */
	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	/**
	 * Sets the mensaje informativo.
	 *
	 * @param mensajeInformativo
	 *            the new mensaje informativo
	 */
	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}

}
