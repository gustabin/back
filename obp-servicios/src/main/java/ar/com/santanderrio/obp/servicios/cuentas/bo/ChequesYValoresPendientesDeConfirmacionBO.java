/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;

/**
 * The Interface ChequesYValoresPendientesDeConfirmacionBO.
 */
public interface ChequesYValoresPendientesDeConfirmacionBO extends BusinessObject {

	/**
	 * Obtener creditos pendientes de confirmacion por cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<List<DetalleMovimientoChequesYValoresEntity>> obtenerCreditosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta);

	/**
	 * Obtener debitos pendientes de confirmacion por cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<List<DetalleMovimientoChequesYValoresEntity>> obtenerDebitosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta);

	/**
	 * Obtener reporte.
	 *
	 * @param object
	 *            the object
	 * @param proceso
	 *            the proceso
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerReporte(Object object, String proceso, Cliente cliente);

}
