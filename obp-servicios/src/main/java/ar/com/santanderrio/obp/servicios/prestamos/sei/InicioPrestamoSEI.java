package ar.com.santanderrio.obp.servicios.prestamos.sei;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultarPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.*;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.BajaPrestamoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Interface InicioPrestamoSEI.
 *
 */
@Path("/prestamos")
public interface InicioPrestamoSEI {

    /**
     * realiza consultar en Prestamo.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return respuesta con el objeto view response.
     */
    @POST
    @Path("/obtenerPrestamos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamosView> obtenerPrestamos(ObtenerPrestamoInView consultaPrestamo);


    @GET
    @Path("/obtenerPrestamosEncolados")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamosEncoladosView> obtenerPrestamosEncolados();


    /**
     * Stop debit prestamo.
     *
     * @param ConfirmacionStopDebitView the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/confirmarStopDebitPrestamos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ComprobanteStopDebitPrestamoView> confirmarStopDebitPrestamos(ConfirmacionStopDebitView ConfirmacionStopDebitView);


    /**
     * Obtener prestamo sueldos.
     *
     * @param consultaPrestamoSueldos the consulta prestamo sueldos
     * @return the respuesta
     */
    @POST
    @Path("/obtenerPrestamoSueldos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamoSueldosView> obtenerPrestamoSueldos(ObtenerPrestamoInView consultaPrestamoSueldos);

    /**
     * Confirmar prestamo sueldos.
     *
     * @param prestamoSueldosConfirmacionView the prestamo sueldos confirmacion view
     * @return the respuesta
     */
    @POST
    @Path("/confirmarPrestamoSueldos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamoSueldosView> confirmarPrestamoSueldos(PrestamoSueldosConfirmacionView prestamoSueldosConfirmacionView);

    /**
     * Adjuntar archivo prestamo sueldos.
     *
     * @param documentacionAdjuntaView the documentacion adjunta view
     * @return the respuesta
     */
    @POST
    @Path("/adjuntarArchivo")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ArchivoAdjuntoPrestamoSueldosView> adjuntarArchivoPrestamoSueldos(DocumentacionAdjuntaView documentacionAdjuntaView);

    /**
     * Borrar archivo.
     *
     * @param documentacionAdjuntaView the documentacion adjunta view
     * @return the respuesta
     */
    @POST
    @Path("/borrarArchivo")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> borrarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView);

    /**
     * obtiene calificacion crediticia.
     *
     * @return respuesta con el objeto view response.
     */
    @POST
    @Path("/obtenerCabecera")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<CabeceraPrestamosView> obtenerCabecera();

    /**
     * Notificar acceso desde home.
     *
     * @return the respuesta
     */
    @POST
    @Path("/notificarAccesoDesdeHome")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> notificarAccesoDesdeHome();

    /**
     * Gets the detalle prestamos.
     *
     * @param consultarPrestamo
     *            the consultar prestamo
     * @return the detalle prestamos
     */
    @POST
    @Path("/obtenerDetalleCuota")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<DetallePrestamoView> obtenerDetalleCuota(ConsultarPrestamo consultarPrestamo);

    /**
     * Obtener configuracion pago cuota prestamo.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/obtenerConfiguracionPagoCuotaPrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(ConsultaPrestamo consultaPrestamo);

    /**
     * Inicio simulacion prestamos.
     *
     * @param puntoDeAcceso
     *            the punto de acceso
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaInicioSimuladorPrestamo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> grabarEstadisticaInicioSimuladorPrestamo(PuntoDeAccesoView puntoDeAcceso);

    /**
     * Obtener resultado simulacion.
     *
     * @param solicitudSimulacion
     *            the solicitud simulacion
     * @return the respuesta
     */
    @POST
    @Path("/simulador")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ResultadoSimulacionView> obtenerResultadoSimulacion(SolicitudSimulacionView solicitudSimulacion);

    /**
     * Obtener resultado simulacion.
     *
     * @param solicitudSimulacion
     *            the solicitud simulacion
     * @return the respuesta
     */
    @POST
    @Path("/encolarSolicitudPrestamo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ComprobanteFeedbackView> encolarSolicitudPrestamo(SolicitudSimulacionView solicitudSimulacion);

    /**
     * Obtiene el feedback de la solicitud de un prestamo.
     *
     * @param solicitudPrestamo
     *            the solicitud prestamo
     * @return the respuesta
     */
    @POST
    @Path("/confirmacionSolicitudPrestamo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ComprobanteFeedbackView> confirmacionSolicitudPrestamo(SolicitudSimulacionView solicitudPrestamo);

    @POST
    @Path("/confirmacionSolicitudPrestamoEncolado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ComprobanteFeedbackView> confirmacionSolicitudPrestamoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView);


    @POST
    @Path("/eliminarSolicitudPendiente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<BajaPrestamoView> eliminarSolicitudPendiente(LiquidacionPrestamoEncoladoView solicitudPrestamo);

    /**
     * Grabar estadistica visualizacion comprobante.
     *
     * @param comprobanteView
     *            the comprobante view
     * @return the boolean
     */
    @POST
    @Path("/grabarEstadisticaVisualizacionComprobante")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Boolean grabarEstadisticaVisualizacionComprobante(ComprobantePrestamoView comprobanteView);

    /**
     * Grabar estadistica visualizacion ayuda.
     *
     * @return the boolean
     */
    @POST
    @Path("/grabarEstadisticaVisualizacionAyuda")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Boolean grabarEstadisticaVisualizacionAyuda();

    /**
     * Grabar estadistica visualizacion tasas.
     *
     * @return the boolean
     */
    @POST
    @Path("/grabarEstadisticaVisualizacionTasas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Boolean grabarEstadisticaVisualizacionTasas();

    /**
     * Obtener proximas cuotas.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/obtenerProximasCuotas")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ProximasCuotasView> obtenerProximasCuotas(ConsultaPrestamo consultaPrestamo);

    /**
     * Grabar estadistica detalle proxima cuota.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaDetalleProximaCuota")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> grabarEstadisticaDetalleProximaCuota(ConsultaPrestamo consultaPrestamo);

    /**
     * Obtener cuotas pagas.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/obtenerCuotasPagas")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ProximasCuotasView> obtenerCuotasPagas(ConsultaPrestamo consultaPrestamo);

    /**
     * Grabar estadistica detalle cuota paga.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaDetalleCuotaPaga")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> grabarEstadisticaDetalleCuotaPaga(ConsultaPrestamo consultaPrestamo);

    /**
     * Obtener prestamos cancelados.
     *
     * @return the respuesta
     */
    @POST
    @Path("/obtenerPrestamosCancelados")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamosCanceladosView> obtenerPrestamosCancelados();

    /**
     * Obtener cuotas de cancelado.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/obtenerCuotasDeCancelado")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ProximasCuotasView> obtenerCuotasDeCancelado(ConsultaPrestamo consultaPrestamo);

    /**
     * Grabar estadistica detalle cuota de cancelado.
     *
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaDetalleCuotaDeCancelado")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> grabarEstadisticaDetalleCuotaDeCancelado();

    /**
     * Obtener detalle.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/obtenerDetalle")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ProximasCuotasView> obtenerDetalle(ConsultaPrestamo consultaPrestamo);

    /**
     * Ver detalle prestamo.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @POST
    @Path("/verDetallePrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<DetalleCuotaPrestamoView> verDetallePrestamo(ConsultaPrestamo consultaPrestamo);

    /**
     * Ver detalle prestamo.
     *
     * @return the respuesta
     */
    @POST
    @Path("/descargarPDFCalculadorPrestamos")
    @Produces(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarPDFCalculadorPrestamos();

    /**
     * Obtiene simulacion del prestamo para visualizar
     */
    @GET
    @Path("/obtenerSolicitudPrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    Respuesta<ResultadoSimulacionView> obtenerSolicitudPrestamo();

    /**
     * Descargar cuotas pagas PDF.
     *
     * @param proximaCuotaView
     *            the proxima cuota view
     * @return the respuesta
     */
    @POST
    @Path("/descargarCuotasPagasPDF")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarCuotasPagasPDF(ProximaCuotaView proximaCuotaView);

    /**
     * Descargar comprobante detalle prestamo.
     *
     * @param detalleCuotaPrestamoView
     *            the detalle cuota prestamo view
     * @return the respuesta
     */
    @POST
    @Path("/descargarComprobanteDetallePrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarComprobanteDetallePrestamo(DetalleCuotaPrestamoView detalleCuotaPrestamoView);

    /**
     * Descargar proximas cuotas PDF.
     *
     * @param proximaCuotaView
     *            the proxima cuota view
     * @return the respuesta
     */
    @POST
    @Path("/descargarProximasCuotasPDF")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarProximasCuotasPDF(CuotaView proximaCuotaView);

    /**
     * Descargar detalle prestamo PDF.
     *
     * @param detallePrestamosView
     *            the detalle prestamos view
     * @return the respuesta
     */
    @POST
    @Path("/descargarDetallePrestamoPDF")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarDetallePrestamoPDF(DetallePrestamosView detallePrestamosView);

    /**
     * Exportar cuotas pagas.
     *
     * @return the respuesta
     */
    @POST
    @Path("/exportarCuotasPagas")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> exportarCuotasPagas();

    /**
     * Exportar proximas cuotas.
     *
     * @return the respuesta
     */
    @POST
    @Path("/exportarProximasCuotas")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> exportarProximasCuotas();

    @POST
    @Path("/descargarCompStopDebitPrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarCompStopDebitPrestamo(ComprobanteStopDebitPrestamoView comprobanteStopDebitPrestamoView);

    @POST
    @Path("/consultarDatosPrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamoSueldoTasaSubsidiadaView> consultarPrestamoATPVigente();

    @POST
    @Path("/getPreparacionPrestamoTasaSubsidiada")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamoSueldoTasaSubsidiadaView> getPreparacionPrestamoTasaSubsidiada();

    @POST
    @Path("/grabarEstadisticaConfiguracionPrestamoTasaSubsidiada")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> grabarEstadisticaConfiguracionPrestamoTasaSubsidiada();

    @POST
    @Path("/grabarEstadisticaConfirmacionPrestamoTasaSubsidiada")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> grabarEstadisticaConfirmacionPrestamoTasaSubsidiada();

    @POST
    @Path("/confirmarPrestamoTasaSubsidiada")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamoSueldoTasaSubsidiadaView> confirmarPrestamoTasaSubsidiada(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView);

    @POST
    @Path("/agregarNuevoCBU")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<PrestamoSueldoTasaSubsidiadaView> agregarNuevoCBU(PrestamoSueldoTasaSubsidiadaView prestamoSueldoTasaSubsidiadaView);

    @POST
    @Path("/descargarPrestamoTasaSubsidiada")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarPrestamoTasaSubsidiada();

    @POST
    @Path("/simularPrestamoPreaprobadoMonoproducto")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ResultadoAltaSimulacionPreaprobadoView> simularPrestamoPreaprobadoMonoproducto(PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobado);

    @POST
    @Path("/confirmarPrestamoPreaprobadoMonoproducto")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio);

    @POST
    @Path("/confirmarPrestamoPreaprobadoMonoproductoEncolado")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproductoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView);

    @POST
    @Path("/encolarPrestamoPreaprobadoMonoproducto")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ResultadoAltaSimulacionPreaprobadoView> encolarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio);

    @POST
    @Path("/descargarPrestamoPreaprobadoMonoproducto")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ReporteView> descargarPrestamoPreaprobadoMonoproducto();

    @GET
    @Path("/obtenerPrestamoPreaprobadoMonoproducto")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<ResultadoAltaSimulacionPreaprobadoView> obtenerPrestamoPreaprobadoMonoproducto();

    @POST
    @Path("/calcularOfertaPreaprobadoFromDeeplink")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> calcularOfertaPreaprobadoFromDeeplink();

    @POST
    @Path("/simularCancelacionPrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<CancelacionPrestamoView> simularCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView);

    @POST
    @Path("/ejecutarCancelacionPrestamo")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<CancelacionPrestamoView> ejecutarCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView);

    @POST
    @Path("/generarComprobantePDFCancelacionTotal")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ReporteView> generarComprobantePDF();

    @POST
    @Path("/grabarEstadisticaVisualizacionComprobanteCancelacionPrestamo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Respuesta<Void> grabarEstadisticaVisualizacionComprobanteCancelacionPrestamo();

    @POST
    @Path("/grabarEstadisticaFeedbackCancelacionPrestamo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON})
    Boolean grabarEstadisticaFeedbackCancelacionPrestamo(FeedbackCancelacionView view);

}
