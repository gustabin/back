/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dto;

/**
 * The Class ModificacionCambioDomicilioDTO.
 */
public class ModificacionCambioDomicilioDTO extends CambioDomicilioDTO {

	/**
	 * The datosAdicionales que se obtienen de los servicios CMBDOMIPROD y
	 * CMBTELPROD_.
	 */
	private DatosDomTelOutDTO datosAdicionales;

	/** Resultado normalizacion MERLIN. */
	private DatosMerlinDTO datosMerlin;

	/** Resultado de guardar el cambio de domicilio CMBDOMYTEL. */
	private ResultadoModificacionDomicilioDTO resultadoModificacion;

	/**
	 * Gets the datos adicionales.
	 *
	 * @return the datos adicionales
	 */
	public DatosDomTelOutDTO getDatosAdicionales() {
		return datosAdicionales;
	}

	/**
	 * Sets the datos adicionales.
	 *
	 * @param datosAdicionales
	 *            the new datos adicionales
	 */
	public void setDatosAdicionales(DatosDomTelOutDTO datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
	}

	/**
	 * Gets the datos merlin.
	 *
	 * @return the datos merlin
	 */
	public DatosMerlinDTO getDatosMerlin() {
		return datosMerlin;
	}

	/**
	 * Sets the datos merlin.
	 *
	 * @param datosMerlin
	 *            the new datos merlin
	 */
	public void setDatosMerlin(DatosMerlinDTO datosMerlin) {
		this.datosMerlin = datosMerlin;
	}

	/**
	 * Gets the resultado modificacion.
	 *
	 * @return the resultado modificacion
	 */
	public ResultadoModificacionDomicilioDTO getResultadoModificacion() {
		return resultadoModificacion;
	}

	/**
	 * Sets the resultado modificacion.
	 *
	 * @param resultadoModificacion
	 *            the new resultado modificacion
	 */
	public void setResultadoModificacion(ResultadoModificacionDomicilioDTO resultadoModificacion) {
		this.resultadoModificacion = resultadoModificacion;
	}

}
