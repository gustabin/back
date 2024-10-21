/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class OperacionDeRiesgo. Clase abstracta de la que deben extender las
 * operaciones que necesitan evaluación de riesgo.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 08/11/2016
 */
public abstract class OperacionDeRiesgo extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo operacion. */
	private OperacionesRSAEnum tipoOperacion;

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public OperacionesRSAEnum getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(OperacionesRSAEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Instantiates a new operacion de riesgo.
	 *
	 * @param operacion
	 *            the operacion
	 */
	protected OperacionDeRiesgo(OperacionesRSAEnum operacion) {
		this.tipoOperacion = operacion;
	}
}
