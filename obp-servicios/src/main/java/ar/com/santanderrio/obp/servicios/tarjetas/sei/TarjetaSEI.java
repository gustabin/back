/*
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CompraProtegidaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EmisionOfertaIntegradaView;
import ar.com.santanderrio.obp.servicios.seguros.entities.CompraProtegida;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.StopDebitIn;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.BajaAdhesionTarjConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FeedbackFinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.BajaAdhesionTarjView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosAdhesionDebitoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosInicioCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InfoTarjetaAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InicioCancelarStopDebitDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.NroTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.view.StopDebitView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaMultipleView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaModificacionAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaPagoSeleccionView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaSeleccionada;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosPendientesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.DetalleTarjetaPagoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LimitesYDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.StackTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TerminoYCondicionUltimoResumenView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.UltimoResumenView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * Created by pablo.martin.gore on 8/5/2016.
 */
@Path("/tarjetas")
public interface TarjetaSEI {

	/**
	 * Get the tarjetas.
	 *
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 * @return the tarjetas
	 */
	@POST
	@Path("/obtenerTarjetas")
	@CustomPreAuthorize(AccionController.IR_INICIO_TARJETAS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TarjetasView> getTarjetas(TarjetaSeleccionada tarjetaSeleccionada);

	/**
	 * Get the listaTarjetasCliente.
	 *
	 * @return the lista Tarjetas Cliente
	 */
	@POST
	@Path("/obtenerListaTarjetasCliente")
	@CustomPreAuthorize(AccionController.IR_INICIO_TARJETAS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<StackTarjetasView> getlistaTarjetasCliente();
	
	/**
	 * Home post.
	 *
	 * @return the respuesta
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> homePost();

	/**
	 * Actualizar alias.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/actualizarAliasTarjetas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> actualizarAlias(TarjetaActivaView view);

	/**
	 * Se actualiza la tarjeta favorita.
	 *
	 * @param tarjetaActivaView
	 *            the tarjeta activa view
	 * @return Respuesta<Void>
	 */
	@POST
	@Path("/actualizarFavoritosTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> actualizarTarjetaFavorita(TarjetaActivaView tarjetaActivaView);

	/**
	 * Obtener ultimos consumos.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/ultimosConsumos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ConsumosView> obtenerUltimosConsumos(TarjetaActivaView view);

	/**
	 * Ver detalle ultimos consumos(solo graba estadistica).
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verDetalleUltimosConsumos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> verDetalleUltimosConsumos();

	/**
	 * Exportar ultimos consumos.
	 *
	 * @param mc
	 *            the mc
	 */
	@RequestMapping("/exportarConsumos")
	void exportarUltimosConsumos(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Obtener ultimo resumen tarjeta.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/ultimosResumenes")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<UltimoResumenView> obtenerUltimoResumenTarjeta(TarjetaActivaView view);

	/**
	 * Ver detalle ultimo resumen(solo graba estadistica).
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verDetalleUltimoResumen")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> verDetalleUltimoResumen();

	/**
	 * Obtener consumos pendientes.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consumosPendientes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ConsumosPendientesView> obtenerConsumosPendientes();

	/**
	 * Obtener consulta financiacion.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/consultaFinanciacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ConsultaFinanciacionView> obtenerConsultaFinanciacion(TarjetaActivaView view);

	/**
	 * Obtiene los limites y disponibles de la tarjeta titular.
	 *
	 * @author florencia.n.martinez
	 * @param view
	 *            the view
	 * @return Respuesta<LimitesYDisponiblesView>
	 */
	@POST
	@Path("/limitesDisponibles")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<LimitesYDisponiblesView> obtenerLimitesYDisponibles(TarjetaActivaView view);

	/**
	 * Exportar ultimo resumen.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */

	/**
	 * Exportar resumen on demand.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Path("/exportarResumenPuntualOnDemand")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> exportarResumenOnDemand(TarjetaActivaView cuenta);

	/**
	 * Obtiene los datos para el pago de una tarjeta.
	 *
	 * @param reintentar
	 *            me indica si es la primera vez que llamo al flujo o es un
	 *            reintento
	 * @return the respuesta
	 */
	@POST
	@Path("/nuevoPago")
	@CustomPreAuthorize(AccionController.IR_INICIO_PAGO_DE_TARJETA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoTarjetaInfoView> obtenerDatosInicialesTarjeta(Reintentar reintentar);

	/**
	 * Llama a datosTarjeta, graba la estadistica correspondiente y retorna el
	 * resultado de datosTarjeta.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/pagoTarjetaDesdeUltimoResumen")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoTarjetaInfoView> pagoTarjetaDesdeUltimoResumen();

	/**
	 * Realiza el pago de una tarjeta de credito.
	 *
	 * @param pagoTarjeta
	 *            the pago tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarPagar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobantePagoTarjeta> confirmarPagar(PagoTarjetaCreditoView pagoTarjeta);

	/**
	 * Realiza el pago de una tarjeta de credito.
	 *
	 * @param pagoTarjeta
	 *            the pago tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/pagar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobantePagoTarjeta> pagar(PagoTarjetaCreditoView pagoTarjeta);

	/**
	 * Realiza la recarga de una tarjeta recargable.
	 *
	 * @param mc
	 *            the mc
	 * @param recargaTarjeta
	 *            the recarga tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/recargar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteRecargaTarjetaView> recargar(@Context MessageContext mc,ComprobanteRecargaTarjetaView recargaTarjeta);

	/**
	 * Validar nueva recarga agendar.
	 *
	 * @param mc
	 *            the mc
	 * @param recargaTarjeta
	 *            the recarga tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/validarNuevaRecargaAgendar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteRecargaTarjetaView> validarNuevaRecargaAgendar(@Context MessageContext mc,ComprobanteRecargaTarjetaView recargaTarjeta);

	/**
	 * Vaciar desafio.
	 *
	 * @return the object
	 */
	@POST
	@Path("/vaciarDesafio")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Object vaciarDesafio();

	/**
	 * Realiza la recarga de una tarjeta recargable.
	 *
	 * @param mc
	 *            the mc
	 * @param recargaTarjeta
	 *            the recarga tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/modificarRecarga")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteRecargaTarjetaView> modificarRecarga(@Context MessageContext mc,RecargaTarjetaView recargaTarjeta);

	/**
	 * Realiza la recarga de una tarjeta recargable.
	 *
	 * @param mc
	 *            the mc
	 * @param recargaTarjeta
	 *            the recarga tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/stopDebitRecarga")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteFeedbackView> stopDebitRecargable(@Context MessageContext mc,RecargaTarjetaView recargaTarjeta);

	/**
	 * Realiza la recarga de una tarjeta recargable.
	 *
	 * @param mc
	 *            the mc
	 * @param recargaTarjeta
	 *            the recarga tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/eliminarRecarga")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteFeedbackView> eliminarRecarga(@Context MessageContext mc,RecargaTarjetaView recargaTarjeta);

	/**
	 * Genera un reporte con el comprobante de la recarga.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteRecarga")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobanteRecarga();

	/**
	 * Estadistica comprobante.
	 */
	@POST
	@Path("/estadisticaComprobante")
	void estadisticaComprobante();

	/**
	 * Programa el pago de una tarjeta de credito.
	 *
	 * @param pagoTarjeta
	 *            the pago tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/pagoProgramado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobantePagoTarjeta> programarPago(PagoTarjetaCreditoView pagoTarjeta);

	/**
	 * Confirma el contrato de pago programado y prosigue con el pago de una
	 * tarjeta de credito.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/aceptacionContratoPagoProgramado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<String> aceptacionContratoPagoProgramado();

	/**
	 * Confirma el contrato de pago programado y prosigue con el pago de una
	 * tarjeta de credito.
	 *
	 * @param stopDebitIn
	 *            the stop debit in
	 * @return the respuesta
	 */
	@POST
	@Path("/stopDebit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<StopDebitOut> stopDebit(StopDebitIn stopDebitIn);

	/**
	 * obtiene los datos para hacer el stop debit.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosStopDebit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<StopDebitConfirmacion> obtenerDatosStopDebit(StopDebitView view);

	/**
	 * Obtener datos baja adhesion tarjeta.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosBajaAdhesionTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaAdhesionTarjeta(BajaAdhesionTarjView view);

	/**
	 * Baja adhesion tarjeta.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/bajaAdhesionTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteFeedbackView> bajaAdhesionTarjeta(BajaAdhesionTarjView view);

	/**
	 * Obtiene la lista cuotas pendientes pago de tarjeta de credito.
	 *
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return the respuesta
	 */
	@POST
	@Path("/listarCuotasPendientes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<CuotasPendientesView> obtenerCuotasPendientes(TarjetaActivaView tarjetaActiva);

	/**
	 * Lista para un Cliente titular los resúmenes de su tarjeta de credito. Los
	 * ultimos 18 meses visa y amex segun tarjeta ingresada por parametro.
	 * Realiza obtenerResumen en ResumenAnterior.
	 *
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/listarResumenesAnteriores")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ResumenAnteriorViewResponse> obtenerResumenesAnteriores(TarjetaActivaView tarjetaActiva);

	/**
	 * Tarjetas cuentas para debito.
	 *
	 * @param reintentar
	 *            the reintentar
	 * @return the respuesta
	 */
	@POST
	@Path("/inicioAdhesionDebito")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<InfoTarjetaAdhesionDebitoView> tarjetasCuentasParaDebito(Reintentar reintentar);

	/**
	 * Adherir tarjeta debito automatico.
	 *
	 * @param datosAdhesionDebitoView
	 *            the datos adhesion debito view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarServicioAdhesionDebito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteFeedbackView> adherirTarjetaDebitoAutomatico(
			DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView);

	/**
	 * Estadistica visualizacion comprobante adhesion debito.
	 */
	@POST
	@Path("/estadisticaVisualizacionComprobanteAdhesionDebito")
	void estadisticaVisualizacionComprobanteAdhesionDebito();

	/**
	 * Estadistica visualizacion comprobante stop debit.
	 */
	@POST
	@Path("/estadisticaVisualizacionComprobanteStopDebit")
	void estadisticaVisualizacionComprobanteStopDebit();

	/**
	 * Estadistica visualizacion comprobante baja adhesion.
	 */
	@POST
	@Path("/estadisticaVisualizacionComprobanteBajaAdhesion")
	void estadisticaVisualizacionComprobanteBajaAdhesion();

	/**
	 * Lista para un Cliente titular los resúmenes de su tarjeta de credito. Los
	 * ultimos 18 meses visa y amex segun tarjeta ingresada por parametro.
	 * Realiza obtenerResumen en ResumenAnterior. Crea estadistica
	 *
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/resumenesAnterioresDesdeUltimoResumen")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ResumenAnteriorViewResponse> resumenesAnterioresDesdeUltimoResumen(TarjetaActivaView tarjetaActiva);

	/**
	 * Inicio cancelar stop debit.
	 *
	 * @param datosInicioCancelarStopDebit
	 *            the datos inicio cancelar stop debit
	 * @return the respuesta
	 */
	@POST
	@Path("/inicioCancelarStopDebit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<InicioCancelarStopDebitDTO> inicioCancelarStopDebit(
			DatosInicioCancelarStopDebit datosInicioCancelarStopDebit);

	/**
	 * Ejecutar cancelacion stop debit.
	 *
	 * @param datosCancelarStopDebit
	 *            the datos cancelar stop debit
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarCancelacionStopDebit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteFeedbackView> ejecutarCancelacionStopDebit(DatosCancelarStopDebit datosCancelarStopDebit);

	/**
	 * Estadistica visualizacion comprobante cancelacion stop debit.
	 */
	@POST
	@Path("/estadisticaVisualizacionComprobanteCancelacionStopDebit")
	void estadisticaVisualizacionComprobanteCancelacionStopDebit();

	/**
	 * Detalle de pago.
	 *
	 * @param tarjetaPagoSeleccionView
	 *            the tarjeta pago seleccion view
	 * @return the respuesta
	 */
	@POST
	@Path("/detalleDePago")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DetalleTarjetaPagoView> detalleDePago(TarjetaPagoSeleccionView tarjetaPagoSeleccionView);

	/**
	 * Solicitar financiacion tarjeta.
	 *
	 * @author emilio.watemberg
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the respuesta
	 * @since Dec 12, 2016
	 */
	@POST
	@Path("/simularFinanciacionTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<FinanciacionTarjetaView> simularFinanciacionTarjeta(FinanciacionTarjetaView financiacionTarjetaView);

	/**
	 * Solicitar financiacion tarjeta.
	 *
	 * @author emilio.watemberg
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the respuesta
	 * @since Dec 5, 2016
	 */
	@POST
	@Path("/solicitarFinanciacionTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<FinanciacionTarjetaView> solicitarFinanciacionTarjeta(FinanciacionTarjetaView financiacionTarjetaView);

	/**
	 * Ejecutar financiacion tarjeta.
	 *
	 * @author emilio.watemberg
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the respuesta
	 * @since Dec 5, 2016
	 */
	@POST
	@Path("/ejecutarFinanciacionTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<FeedbackFinanciacionTarjetaView> ejecutarFinanciacionTarjeta(
			FinanciacionTarjetaView financiacionTarjetaView);

	/**
	 * Comprobante financiacion tarjeta.
	 *
	 * @param tarjeta
	 *            the marca tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteFinanciacionTarjeta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<FinanciacionTarjetaView> comprobanteFinanciacionTarjeta(FinanciacionTarjetaView tarjeta);

	/**
	 * Inicio modificacion adhesion debito.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the respuesta
	 */
	@POST
	@Path("/inicioModificacionAdhesionDebito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TarjetaModificacionAdhesionDebitoView> inicioModificacionAdhesionDebito(NroTarjetaView nroTarjeta);

	/**
	 * Modificar adhesion debito.
	 *
	 * @param datosAdhesionDebitoView
	 *            the datos adhesion debito view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarModificacionAdhesionDebito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteFeedbackView> modificarAdhesionDebito(DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView);

	/**
	 * Estadistica visualizacion comprobante modificacion debito.
	 */
	@POST
	@Path("/estadisticaVisualizacionComprobanteModificacionDebito")
	void estadisticaVisualizacionComprobanteModificacionDebito();

	/**
	 * Gets the datos tarjeta recargable.
	 *
	 * @param reintentar
	 *            the reintentar
	 * @return the datos tarjeta recargable
	 */
	@POST
	@Path("/obtenerDatosTarjetaRecargable")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RecargaTarjetaInfoView> getDatosTarjetaRecargable(Reintentar reintentar);

	/**
	 * Obtener datos baja recarga.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosStopDebitRecarga")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaRecarga(BajaAdhesionTarjView view);

	/**
	 * Validar nueva recarga.
	 *
	 * @param mc
	 *            the mc
	 * @param recargaTarjeta
	 *            the recarga tarjeta
	 * @return the object
	 */
	@POST
	@Path("/validarNuevaRecarga")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Object validarNuevaRecarga(@Context MessageContext mc,ComprobanteRecargaTarjetaView recargaTarjeta);

	/**
	 * Exportar resumen on demand multiple.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@Path("/exportarResumenMultipleOnDemand")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> exportarResumenOnDemandMultiple(TarjetaActivaMultipleView view);

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobantePDF();

	/**
	 * Estadistica visualizacion detalle importe pago.
	 */
	@POST
	@Path("/estadisticaVisualizacionDetalleImportePago")
	void estadisticaVisualizacionDetalleImportePago();

	/**
	 * Descarga excel ultimos consumos.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarExcelUltimosConsumos")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargaExcelUltimosConsumos();

	/**
	 * Descarga excel cuotas pendientes.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarExcelCuotasPendientes")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargaExcelCuotasPendientes();

	/**
	 * Descarga excel consulta financiacion.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarExcelConsultaFinanciacion")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargaExcelConsultaFinanciacion();

	/**
	 * Exportar ultimo resumen on demand.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@Path("/descargarResumenActual")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> exportarUltimoResumenOnDemand(TarjetaActivaView view);

	@POST
	@Path("/exportarUltimosConsumosPDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> exportarUltimosConsumosPDF();

	/**
	 * Validar el reemplazo de tarjetas
	 *
	 * @return Respuesta<TarjetasView>
	 */
	@Path("/validarReemplazoDeTarjetas")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TarjetasView> validarReemplazoDeTarjetas();

	/**
	 * Consulta de domicilio principal y laboral.
	 *
	 * @return DomiciliosDisponiblesView
	 */
	@Path("/consultarDomicilioPrincipalLaboral")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<DomiciliosDisponiblesView> consultarDomicilioPrincipalLaboral();

	/**
	 * Consulta mensaje tooltip agendamiento recarga
	 * @return Respuesta<String>
	 */
	@POST
	@Path("/tooltipAgendamientoRecarga")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<String> tooltipAgendamientoRecarga();

	@Path("/emitirCompraProtegida")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<EmisionOfertaIntegradaView> emitirPolizaOfertaIntegrada(EmisionOfertaIntegrada emisionOfertaIntegrada);

	@Path("/consultarCompraProtegida")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<CompraProtegidaView> consultarCompraProtegida(CompraProtegida compraProtegida);

    /**
     * Obtener token acceso resumen anual.
     *
     * @param tarjetaActiva the tarjeta activa
     * @return the respuesta
     */
    @POST
    @Path("/accesoResumenAnual")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<TokenView> obtenerTokenAccesoResumenAnual(TarjetaActivaView tarjetaActiva);

}
