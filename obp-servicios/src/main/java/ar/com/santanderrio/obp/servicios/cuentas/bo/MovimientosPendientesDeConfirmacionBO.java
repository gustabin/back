/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosPendientesDeConfirmacion;

/**
 * The Interface MovimientosPendientesDeConfirmacionBO.
 */
public interface MovimientosPendientesDeConfirmacionBO extends BusinessObject {

	/**
	 * Obtener movimientos pendientes de confirmacion por cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<MovimientosPendientesDeConfirmacion> obtenerMovimientosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta);
}
