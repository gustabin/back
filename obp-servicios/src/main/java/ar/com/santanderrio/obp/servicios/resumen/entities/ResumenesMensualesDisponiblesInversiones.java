package ar.com.santanderrio.obp.servicios.resumen.entities;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;

/**
 * The Class ResumenesMensualesDisponiblesInversiones.
 */
public class ResumenesMensualesDisponiblesInversiones {

	/** The cuenta. */
	private AbstractCuenta cuenta;

	/** The resumen mensual inversiones. */
	private Respuesta<List<ResumenMensualInversiones>> resumenMensualInversiones;

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
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(AbstractCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the resumenes mensuales inversiones.
	 *
	 * @return the resumenes mensuales inversiones
	 */
	public Respuesta<List<ResumenMensualInversiones>> getResumenesMensualesInversiones() {
		return resumenMensualInversiones;
	}

	/**
	 * Sets the resumen mensual inversiones.
	 *
	 * @param resumenesMensualesinversiones the new resumen mensual inversiones
	 */
	public void setResumenMensualInversiones(Respuesta<List<ResumenMensualInversiones>> resumenesMensualesInversiones) {
		this.resumenMensualInversiones = resumenesMensualesInversiones;
	}

}
