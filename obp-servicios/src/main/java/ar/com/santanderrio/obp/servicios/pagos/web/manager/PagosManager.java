/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.manager;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPagosView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagosPendientesView;

/**
 * The Interface PagosManager.
 *
 * @author B039636
 */
public interface PagosManager {

	/**
	 * Gets the pagos totales.
	 *
	 * @param consultaPagosView
	 *            the consulta pagos view
	 * @return the pagos totales
	 */
	Respuesta<PagosPendientesView> getPagosTotales(ConsultaPagosView consultaPagosView);

	/**
	 * Alta de informacion adicional.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<Boolean> altaDeInformacionAdicional(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar stop debit.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarStopDebit(PagoPendienteView pagoPendienteView);

	/**
	 * Ejecutar stop debit.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> ejecutarStopDebit(PagoPendienteView pagoPendienteView);

	/**
	 * Modificar adhesion pago auto.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> modificarAdhesionPagoAuto(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar adhesion pago auto.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarAdhesionPagoAuto(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar baja adhesion.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarBajaAdhesion(PagoPendienteView pagoPendienteView);

	/**
	 * Ejecutar baja adhesion de Debito y Pago Automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> ejecutarBajaAdhesion(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar eliminacion pago puntual.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarEliminacionPagoPuntual(PagoPendienteView pagoPendienteView);

	/**
	 * Eliminar pagao puntual.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> eliminarPagoPuntual(PagoPendienteView pagoPendienteView);

	/**
	 * Ver detalle.
	 *
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> verDetalle(PagoPendienteView pagoPendiente);

	/**
	 * Solicitar adhesion debito automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarAdhesionDebitoAutomatico(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar adhesion pago automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarAdhesionPagoAutomatico(PagoPendienteView pagoPendienteView);

	/**
	 * Ejecutarbaja pago programado de tarjeta credito. ver: DTF: 10303 iatx:
	 * CNSTJCPAGP
	 *
	 * @author B041299 Manuel.Vargas
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> ejecutarBajaPagoProgramadoDeTarjetaCredito(PagoPendienteView pagoPendienteView);

	/**
	 * Ejecutar baja, switch.
	 *
	 * @author B041299 Manuel.Vargas
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> eliminarPago(PagoPendienteView pagoPendienteView);

	/**
	 * Solicita ejecutar baja, switch.
	 *
	 * @author B041299 Manuel.Vargas
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarEliminacionPago(PagoPendienteView pagoPendienteView);

	/**
	 * Solicita ejecutar baja, switch.
	 *
	 * @author B041299 Manuel.Vargas
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	Respuesta<PagoPendienteView> solicitarEliminacionPagoProgramado(PagoPendienteView pagoPendienteView);
}
