/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EstadoDetalleMovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoValoresView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientosPendientesDeConfirmacionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface MovimientosManager.
 */
public interface MovimientosManager {

	/**
	 * Gets the movimientos.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimientos
	 */
	Respuesta<MovimientoView> getMovimientos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

	/**
	 * Obtener mas movimientos.
	 *
	 * @return the respuesta
	 */
	Respuesta<MovimientoView> obtenerMasMovimientos();

	/**
	 * Gets the movimientos pendientes.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimientos pendientes
	 */
	Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientes(
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

	/**
	 * Gets the movimientos valores.
	 *
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 * @return the movimientos valores
	 */
	Respuesta<MovimientoValoresView> getMovimientosValores(
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

	/**
	 * Gets the movimientos valores reporte.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the movimientos valores reporte
	 */
	Response getMovimientosValoresPendientesReporte(String numeroCuenta);

	/**
	 * Gets the movimientos pendientes de confirmacion reporte.
	 *
	 * @param movimientoDia
	 *            the movimiento dia
	 * @return the movimientos pendientes de confirmacion reporte
	 */

	/**
	 * Gets the detalle movimiento.
	 *
	 * @return the detalle movimiento
	 */
	Respuesta<DetalleMovimientosView> getDetalleMovimiento(EstadoDetalleMovimientoView movimientoDia);

	/**
	 * Gets the ultimos movimientos reporte.
	 *
	 * @return the ultimos movimientos reporte
	 */
	Response exportarMovimientos();
	
	Respuesta<ReporteView> exportarMovimientosPDF();

	/**
	 * Gets the identificacion cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the identificacion cuenta
	 */
	IdentificacionCuenta getIdentificacionCuenta(String cuenta);

	/**
	 * Gets the movimientos pendientes detalle.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param cliente
	 *            the cliente
	 * @return the movimientos pendientes detalle
	 */
	Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientesDetalle(String cuenta, Cliente cliente);

	/**
	 * Gets the movimientos valores pendientes detalle.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param cliente
	 *            the cliente
	 * @return the movimientos valores pendientes detalle
	 */
	Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosValoresPendientesDetalle(String cuenta,
			Cliente cliente);

	/**
	 * Checks if is filtro default.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @return true, if is filtro default
	 */
	boolean isFiltroDefault(ConsultaUltimosMovimientos consultaUltimosMovimientos);

	/**
	 * Gets the movimientos pendientes reporte.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return exporta movimiento de la grilla
	 */

	Response getMovimientosPendientesReporte(String numeroCuenta);

}
