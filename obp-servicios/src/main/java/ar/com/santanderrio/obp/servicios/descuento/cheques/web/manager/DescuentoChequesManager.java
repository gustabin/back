/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager;

import javax.activity.InvalidActivityException;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleHistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.HistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.TasasIndicativasView;

/**
 * The Interface DescuentoChequesManager.
 *
 * @author dante.omar.olmedo
 */
public interface DescuentoChequesManager {

	/**
	 * Obtener monto cesion.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosCesionView> obtenerMontoCesion();

	/**
	 * Obtener tasas indicativas.
	 *
	 * @return the respuesta
	 */
	Respuesta<TasasIndicativasView> obtenerTasasIndicativas();

	/**
	 * Obtener operaciones precargadas.
	 *
	 * @param operacionIn
	 *            the operacion in
	 * @return the respuesta
	 */
	Respuesta<OperacionesPrecargadasView> obtenerOperacionesPrecargadas(OperacionesPrecargadasView operacionIn);

	/**
	 * Obtener detalle operaciones precargadas.
	 *
	 * @param operacionIn
	 *            the operacion in
	 * @return the respuesta
	 * @throws InvalidActivityException
	 *             the invalid activity exception
	 */
	Respuesta<DetalleOperacionesPrecargadasView> obtenerDetalleOperacionesPrecargadas(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException;

	/**
	 * Eliminar operacion.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @return the respuesta
	 */
	Respuesta<Void> eliminarOperacion(String nroTramite);

	/**
	 * Obtener historial operaciones.
	 *
	 * @param operacionIn
	 *            the operacion in
	 * @return the respuesta
	 */
	Respuesta<HistorialOperacionesView> obtenerHistorialOperaciones(HistorialOperacionesView operacionIn);

	/**
	 * Obtener detalle historial operaciones.
	 *
	 * @param operacionIn
	 *            the operacion in
	 * @return the respuesta
	 * @throws InvalidActivityException
	 *             the invalid activity exception
	 */
	Respuesta<DetalleHistorialOperacionesView> obtenerDetalleHistorialOperaciones(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException;

	/**
	 * Descargar operacion PDF.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarOperacionPDF();

	/**
	 * Obtener simulacion de tasas.
	 *
	 * @param chequesView
	 *            the cheques view
	 * @return the respuesta
	 * @throws InvalidActivityException
	 *             the invalid activity exception
	 */
	Respuesta<AltaChequesViewOut> obtenerSimulacionDeTasas(AltaChequesViewOut chequesView) throws InvalidActivityException;

	/**
	 * Obtener efectivizacion de tasas.
	 *
	 * @return the respuesta
	 */
	Respuesta<AltaChequesViewOut> obtenerEfectivizacionDeTasas();

}
