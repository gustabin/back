/*
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CompraProtegidaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EmisionOfertaIntegradaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.StopDebitIn;
import ar.com.santanderrio.obp.servicios.seguros.entities.CompraProtegida;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.BajaAdhesionTarjConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaPagoSeleccionView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.DetalleTarjetaPagoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaInfoView;

/**
 * The Interface PagosTarjetaManager.
 */
public interface PagosTarjetaManager {

	/**
	 * Pagar tarjeta.
	 *
	 * @param pagoTarjetaView
	 *            the pago tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobantePagoTarjeta> pagarTarjeta(PagoTarjetaCreditoView pagoTarjetaView, Cliente cliente);

	/**
	 * Programar pago.
	 *
	 * @param pagoTarjeta
	 *            the pago tarjeta
	 * @param obtenerCliente
	 *            the obtener cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobantePagoTarjeta> programarPago(PagoTarjetaCreditoView pagoTarjeta, Cliente obtenerCliente);

	/**
	 * Aceptacion contrato pago programado.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<String> aceptacionContratoPagoProgramado(Cliente cliente);

	/**
	 * Gets the datos pago tarjeta.
	 *
	 * @param reintentar
	 *            the reintentar
	 * @return the datos pago tarjeta
	 */
	Respuesta<PagoTarjetaInfoView> obtenerDatosInicialesPagoTarjeta(Reintentar reintentar);

	/**
	 * Stop debit.
	 *
	 * @param pagoTarjetaView
	 *            the pago tarjeta view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<StopDebitOut> stopDebit(PagoTarjetaCreditoView pagoTarjetaView, Cliente cliente) throws BusinessException;

	/**
	 * stop debit llamado desde el calendario.
	 *
	 * @param stopDebitIn
	 *            the stop debit in
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<StopDebitOut> llamarStopDebit(StopDebitIn stopDebitIn, Cliente cliente);

	/**
	 * prepara los datos para hacer el stop debit.
	 */

	/** graba la estadistica al mirar el comprobante del stop */
	void estadisticaVisualizacionComprobanteStopDebit();

	/**
	 * Estadistica visualizacion comprobante baja adhesion.
	 */
	void estadisticaVisualizacionComprobanteBajaAdhesion();

	/**
	 * Obtener datos stop debit.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<StopDebitConfirmacion> obtenerDatosStopDebit(String nroTarjeta, Cliente cliente);

	/**
	 * Obtener datos baja adhesion tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaAdhesionTarjeta(String nroTarjeta, Cliente cliente);

	/**
	 * obtiene los detalles de un pago de tarjeta de credito.
	 *
	 * @param tarjetaPagoSeleccionView
	 *            the tarjeta pago seleccion view
	 * @return the respuesta
	 */
	Respuesta<DetalleTarjetaPagoView> detalleDePago(TarjetaPagoSeleccionView tarjetaPagoSeleccionView);

	/**
	 * Llamar estadistica ultimo resumen nuevo pago.
	 *
	 * @return the respuesta
	 */
	Respuesta<PagoTarjetaInfoView> llamarEstadisticaUltimoResumenNuevoPago();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobantePDF();

	/**
	 * Estadistica visualizacion detalle importe pago.
	 */
	void estadisticaVisualizacionDetalleImportePago();

	/**
	 * Confirmar pagar tarjeta.
	 *
	 * @param pagoTarjeta
	 *            the pago tarjeta
	 * @return the respuesta
	 */
	Respuesta<ComprobantePagoTarjeta> confirmarPagarTarjeta(PagoTarjetaCreditoView pagoTarjeta);


	/**
	 * Confirmar emitir poliza seguros.
	 *
	 *            the emitir poliza seguros
	 * @return the respuesta
	 */
	Respuesta<EmisionOfertaIntegradaView> emitirPolizaOfertaIntegrada(EmisionOfertaIntegrada emisionOfertaIntegrada);

	Respuesta<CompraProtegidaView> consultarCompraProtegida(CompraProtegida compraProtegida);
}
