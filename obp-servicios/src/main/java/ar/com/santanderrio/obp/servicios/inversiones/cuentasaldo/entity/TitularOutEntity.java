/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity;

import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaLoadTitular;

/**
 * The Class TitularOutEntity.
 */
public class TitularOutEntity {

	/** The respuesta. */
	private RtaLoadTitular respuesta;

	/** The error en consulta. */
	private Boolean errorEnConsulta = false;

	

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public RtaLoadTitular getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the new respuesta
	 */
	public void setRespuesta(RtaLoadTitular respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * Gets the error en consulta.
	 *
	 * @return the error en consulta
	 */
	public Boolean getErrorEnConsulta() {
		return errorEnConsulta;
	}

	/**
	 * Sets the error en consulta.
	 *
	 * @param errorEnConsulta
	 *            the new error en consulta
	 */
	public void setErrorEnConsulta(Boolean errorEnConsulta) {
		this.errorEnConsulta = errorEnConsulta;
	}

}
