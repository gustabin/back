/*
 * 
 */
package ar.com.santanderrio.obp.servicios.resumen.entities;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;

/**
 * The Class ResumenesMensualesDisponiblesCuenta.
 */
public class ResumenesMensualesDisponiblesCuenta {

	/** The cuenta. */
	private AbstractCuenta cuenta;

	/** The resumen mensual cuenta. */
	private Respuesta<List<ResumenMensualCuenta>> resumenMensualCuenta;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public AbstractCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(AbstractCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the resumenes mensuales cuenta.
	 *
	 * @return the resumenes mensuales cuenta
	 */
	public Respuesta<List<ResumenMensualCuenta>> getResumenesMensualesCuenta() {
		return resumenMensualCuenta;
	}

	/**
	 * Sets the resumen mensual cuenta.
	 *
	 * @param resumenesMensualesCuenta
	 *            the new resumen mensual cuenta
	 */
	public void setResumenMensualCuenta(Respuesta<List<ResumenMensualCuenta>> resumenesMensualesCuenta) {
		this.resumenMensualCuenta = resumenesMensualesCuenta;
	}

}
