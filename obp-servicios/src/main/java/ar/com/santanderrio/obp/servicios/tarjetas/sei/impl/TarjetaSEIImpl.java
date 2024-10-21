/*
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.sei.impl;

import javax.ws.rs.core.Context;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CompraProtegidaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EmisionOfertaIntegradaView;
import ar.com.santanderrio.obp.servicios.seguros.entities.CompraProtegida;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.HTTPResponseUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.StopDebitIn;
import ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.BajaAdhesionTarjConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FeedbackFinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitConfirmacion;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI;
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
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.AdhesionTarjetaDebitoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.CancelarStopDebitTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsultaFinanciacionManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsumoPendienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.CuotasPendienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.DescargaResumenAnteriorManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.FinanciacionTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.InicioTarjetasManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.PagosTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.RecargaTarjetaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ResumenAnteriorManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimoResumenManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimosConsumosManager;
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
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.UltimoResumenView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * Created by pablo.martin.gore on 8/5/2016.
 */
@Component("tarjetaSEI")
public class TarjetaSEIImpl implements TarjetaSEI {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaSEIImpl.class);

    /** The Constant MOVIMIENTOS_MIME_TYPE. */
    //private static final String ULTIMOS_CONSUMOS_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String ULTIMOS_CONSUMOS_MIME_TYPE = "application/vnd.ms-excel";

    /** The Constant MOVIMIENTOS_FILE_NAME. */
    private static final String ULTIMOS_CONSUMOS_FILE_NAME = "export_ultimos_consumos.xls";

    /** The Constant TEXTO_LOGGER. */
    private static final String TEXTO_LOGGER = "Respuesta obtenida de manager: {}.";

    /** The manager. */
    @Autowired
    private InicioTarjetasManager manager;

    /** The manager. */
    @Autowired
    private UltimosConsumosManager managerUC;

    /** The manager. */
    @Autowired
    private UltimoResumenManager ultimoResumenTarjetaManager;

    /** The manager. */
    @Autowired
    private ConsumoPendienteManager consumoPendienteManager;

    /** The manager. */
    @Autowired
    private ConsultaFinanciacionManager consultaFinanciacionManager;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The pagos tarjeta manager. */
    @Autowired
    PagosTarjetaManager pagosTarjetaManager;

    /** The recarga tarjeta manager. */
    @Autowired
    RecargaTarjetaManager recargaTarjetaManager;

    /** The on demand puntual resumen manager. */
    @Autowired
    private DescargaResumenAnteriorManager onDemandPuntualResumenManager;

    /** The cuotas pendiente manager. */
    @Autowired
    CuotasPendienteManager cuotasPendienteManager;

    /** The resumen anterior manager. */
    @Autowired
    private ResumenAnteriorManager resumenAnteriorManager;

    /** The sesion cliente. */
    @Autowired
    public SesionCliente sesionCliente;

    /** The adhesion tarjeta debito automatico manager. */
    @Autowired
    private AdhesionTarjetaDebitoAutomaticoManager adhesionTarjetaDebitoAutomaticoManager;

    /** The cancelar stop debit tarjeta manager. */
    @Autowired
    private CancelarStopDebitTarjetaManager cancelarStopDebitTarjetaManager;

    /** The financiacion tarjetas manager. */
    @Autowired
    private FinanciacionTarjetaManager financiacionTarjetasManager;

    /** The respuesta factory. */
    @Autowired
    RespuestaFactory respuestaFactory;

    /** The cambio domicilio manager. */
    @Autowired
    private CambioDomicilioManager cambioDomicilioManager;

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#getTarjetas()
     */
    @Override
    public Respuesta<TarjetasView> getTarjetas(TarjetaSeleccionada tarjetaSeleccionada) {
        LOGGER.info("Post OK: /obtenerTarjetas.");
        Respuesta<TarjetasView> respuesta = manager.getTarjetas(tarjetaSeleccionada);
        LOGGER.debug("Respuesta obtenerTarjetas : {}.", respuesta);
        return respuesta;
    }
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#getlistaTarjetasCliente()
     */
    @Override
    public Respuesta<StackTarjetasView> getlistaTarjetasCliente() {
        LOGGER.info("Post OK: /getlistaTarjetasCliente.");
        Respuesta<StackTarjetasView> listaTarjetasCliente = manager.getlistaTarjetasCliente();
        LOGGER.debug("Respuesta getlistaTarjetasCliente : {}.", listaTarjetasCliente);
        return listaTarjetasCliente;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#homePost()
     */
    @Override
    public Respuesta<Void> homePost() {
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setRespuestaVacia(false);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        LOGGER.debug("Respuesta de la estadistica: {}.", respuesta.toString());
        return respuesta;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#actualizarAlias
     * (ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetasView)
     */
    @Override
    public Respuesta<Void> actualizarAlias(TarjetaActivaView view) {
        LOGGER.info("Post OK: /actualizarAliasTarjetas.");
        Respuesta<Void> respuestaAliasTarjeta = new Respuesta<Void>();
        respuestaAliasTarjeta = manager.actualizarAlias(view.getNumeroCuenta(), view.getAlias());
        LOGGER.debug("Respuesta obtenida del manager para actualizar la tarjeta favorita: {}.", respuestaAliasTarjeta);
        return respuestaAliasTarjeta;
    }

    /**
     * Se actualiza la tarjeta favorita.
     *
     * @param tarjetaActivaView
     *            the tarjeta activa view
     * @return Respuesta<Void>
     */
    @Override
    public Respuesta<Void> actualizarTarjetaFavorita(TarjetaActivaView tarjetaActivaView) {
        LOGGER.info("Post OK: /actualizarFavoritosTarjeta.");
        Respuesta<Void> respuestaTarjetaFavorita = manager
                .actualizarTarjetaFavorita(tarjetaActivaView.getNumeroCuenta());
        LOGGER.debug("Respuesta obtenida del manager para actualizar la tarjeta favorita: {}.",
                respuestaTarjetaFavorita);
        return respuestaTarjetaFavorita;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * obtenerUltimosConsumos(ar.com.santanderrio.obp.servicios.tarjetas.view.
     * TarjetasView)
     */
    @Override
    public Respuesta<ConsumosView> obtenerUltimosConsumos(TarjetaActivaView view) {
        LOGGER.info("Post OK: /ultimosConsumos.");
        Respuesta<ConsumosView> respuestaUltimosConsumos = managerUC.obtenerUltimosConsumos(view.getNumeroCuenta());
        LOGGER.debug(TEXTO_LOGGER, respuestaUltimosConsumos);
        return respuestaUltimosConsumos;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * verDetalleUltimosConsumos()
     */
    @Override
    public Respuesta<Void> verDetalleUltimosConsumos() {
        LOGGER.info("Post OK: /verDetalleUltimosConsumos");
        return managerUC.verDetalleUltimosConsumos();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * exportarUltimosConsumos(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void exportarUltimosConsumos(@Context org.apache.cxf.jaxrs.ext.MessageContext mc) {
        LOGGER.info("Inicio de exportacion UltimosConsumosController#exportarUltimosConsumos ");
        Respuesta<Reporte> respuestaReporte;
        respuestaReporte = managerUC.exportarUltimosConsumos();
        Reporte reporte = respuestaReporte.getRespuesta();
        if (reporte != null) {
            byte[] bytes = reporte.getBytes();

            HTTPResponseUtils.llenarHTTPResponse(mc.getHttpServletResponse(), bytes, ULTIMOS_CONSUMOS_MIME_TYPE,
                    ULTIMOS_CONSUMOS_FILE_NAME, false);
        }
    }

    /**
     * Obtiene el ultimo resumen de la tarjeta seleccionada.
     *
     * @param view
     *            the view
     * @return the respuesta
     */
    @Override
    public Respuesta<UltimoResumenView> obtenerUltimoResumenTarjeta(TarjetaActivaView view) {
        LOGGER.info("Post OK: /ultimosResumenes.");
        Respuesta<UltimoResumenView> respuestaUltimoResumen = ultimoResumenTarjetaManager
                .obtenerUltimoResumen(view.getNumeroCuenta());
        LOGGER.debug(TEXTO_LOGGER, respuestaUltimoResumen);
        return respuestaUltimoResumen;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * verDetalleUltimosConsumos()
     */
    @Override
    public Respuesta<Void> verDetalleUltimoResumen() {
        LOGGER.info("Post OK: /verDetalleUltimoResumen");
        return ultimoResumenTarjetaManager.verDetalleUltimoResumen();
    }

    /**
     * Obtener consumos pendientes.
     *
     * @return the respuesta
     */
    // TODO Quitar este metodo.
    @Deprecated
    @Override
    public Respuesta<ConsumosPendientesView> obtenerConsumosPendientes() {
        LOGGER.info("Post OK: /consumosPendientes.");
        Respuesta<ConsumosPendientesView> respuestaConsumosPendientes = consumoPendienteManager
                .obtenerConsumoPendiente();
        LOGGER.debug(TEXTO_LOGGER, respuestaConsumosPendientes);
        return respuestaConsumosPendientes;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * obtenerConsultaFinanciacion(ar.com.santanderrio.obp.servicios.tarjetas.
     * view.TarjetasView)
     */
    @Override
    public Respuesta<ConsultaFinanciacionView> obtenerConsultaFinanciacion(TarjetaActivaView view) {
        LOGGER.info("Post OK: /consultaFinanciacion.");
        Respuesta<ConsultaFinanciacionView> respuestaConsultaFinanciacion = consultaFinanciacionManager
                .obtenerConsultaFinanciacion(view.getNumeroCuenta());
        LOGGER.debug(TEXTO_LOGGER, respuestaConsultaFinanciacion);
        return respuestaConsultaFinanciacion;
    }

    /**
     * Obtiene los limites y disponibles de la tarjeta titular.
     *
     * @author florencia.n.martinez
     * @param view
     *            the view
     * @return Respuesta<LimitesYDisponiblesView>
     */
    @Override
    public Respuesta<LimitesYDisponiblesView> obtenerLimitesYDisponibles(TarjetaActivaView view) {
        LOGGER.info("Post OK: /limitesDisponibles.");
        Respuesta<LimitesYDisponiblesView> respuestaLimitesYDisp = manager
                .obtenerLimitesYDisponibles(view.getNumeroCuenta());
        LOGGER.debug(TEXTO_LOGGER, respuestaLimitesYDisp.toString());
        return respuestaLimitesYDisp;
    }

    /**
     * Nuevo pago de Tarjeta de credito.
     *
     * @param reintentar
     *            the reintentar
     * @return the respuesta
     */
    @Override
    public Respuesta<PagoTarjetaInfoView> obtenerDatosInicialesTarjeta(Reintentar reintentar) {
        LOGGER.info("Post OK: /nuevoPago.");
        Respuesta<PagoTarjetaInfoView> respNuevoPagoTC = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(reintentar);
        LOGGER.debug(TEXTO_LOGGER, respNuevoPagoTC.toString());
        return respNuevoPagoTC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<PagoTarjetaInfoView> pagoTarjetaDesdeUltimoResumen() {
        return pagosTarjetaManager.llamarEstadisticaUltimoResumenNuevoPago();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#pagar(ar.com.
     * santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView)
     */
    @Override
    public Respuesta<RecargaTarjetaInfoView> getDatosTarjetaRecargable(Reintentar reintentar) {
        return recargaTarjetaManager.getDatosTarjetaRecargable(sesionCliente.getCliente(), reintentar);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#pagar(ar.com.
     * santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView)
     */
    @Override
    public Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaRecarga(BajaAdhesionTarjView view) {
        return recargaTarjetaManager.obtenerDatosBajaAdhesionTarjeta(view.getNroTarjeta(), sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#pagar(ar.com
     * .santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView)
     */
    @Override
    public Respuesta<ComprobantePagoTarjeta> pagar(PagoTarjetaCreditoView pagoTarjeta) {
        return pagosTarjetaManager.pagarTarjeta(pagoTarjeta, sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#confirmarPagar(ar.
     * com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView)
     */
    @Override
    public Respuesta<ComprobantePagoTarjeta> confirmarPagar(PagoTarjetaCreditoView pagoTarjeta) {
        return pagosTarjetaManager.confirmarPagarTarjeta(pagoTarjeta);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#recargar(org.
     * apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.tarjetas.web.view.
     * ComprobanteRecargaTarjetaView)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaView> recargar(MessageContext mc,
                                                             ComprobanteRecargaTarjetaView recargaTarjeta) {
        return recargaTarjetaManager.recargarTarjeta(mc.getHttpServletRequest(), recargaTarjeta,
                sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * validarNuevaRecargaAgendar(org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.tarjetas.web.view.
     * ComprobanteRecargaTarjetaView)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaView> validarNuevaRecargaAgendar(MessageContext mc,
                                                                               ComprobanteRecargaTarjetaView recargaTarjeta) {
        return recargaTarjetaManager.programarRecargarTarjeta(mc.getHttpServletRequest(), recargaTarjeta,
                sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * modificarRecarga(org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView)
     */
    @Override
    public Respuesta<ComprobanteRecargaTarjetaView> modificarRecarga(MessageContext mc,
                                                                     RecargaTarjetaView recargaTarjeta) {
        return recargaTarjetaManager.modificarProgramarRecargarTarjeta(mc.getHttpServletRequest(), recargaTarjeta,
                sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * stopDebitRecargable(org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView)
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> stopDebitRecargable(MessageContext mc,
                                                                  RecargaTarjetaView recargaTarjeta) {
        return recargaTarjetaManager.stopDebitTarjetaRecargable(mc.getHttpServletRequest(), recargaTarjeta,
                sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#eliminarRecarga
     * (org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView)
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> eliminarRecarga(MessageContext mc, RecargaTarjetaView recargaTarjeta) {
        return recargaTarjetaManager.bajaRecargarTarjeta(mc.getHttpServletRequest(), recargaTarjeta,
                sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * generarComprobanteRecarga()
     */
    @Override
    public Respuesta<ReporteView> generarComprobanteRecarga() {
        return recargaTarjetaManager.generarComprobanteRecarga();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#programarPago(
     * ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView)
     */
    @Override
    public Respuesta<ComprobantePagoTarjeta> programarPago(PagoTarjetaCreditoView pagoTarjeta) {
        return pagosTarjetaManager.programarPago(pagoTarjeta, sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * aceptacionContratoPagoProgramado()
     */
    @Override
    public Respuesta<String> aceptacionContratoPagoProgramado() {
        return pagosTarjetaManager.aceptacionContratoPagoProgramado(sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#stopDebit(ar.
     * com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView)
     */
    @Override
    public Respuesta<StopDebitOut> stopDebit(StopDebitIn stopDebitIn) {
        return pagosTarjetaManager.llamarStopDebit(stopDebitIn, sesionCliente.getCliente());
    }

    /**
     * realiza la baja de Adhesion a Tarjeta de credito SR.
     *
     * @param view
     *            the view
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> bajaAdhesionTarjeta(BajaAdhesionTarjView view) {
        return adhesionTarjetaDebitoAutomaticoManager.bajaAdhesionTarjeta(view, sesionCliente.getCliente());
    }

    /**
     * obtiene los datos para la pantalla previa al stop debit.
     *
     * @param view
     *            the view
     * @return the respuesta
     */
    @Override
    public Respuesta<StopDebitConfirmacion> obtenerDatosStopDebit(StopDebitView view) {
        return pagosTarjetaManager.obtenerDatosStopDebit(view.getNroTarjeta(), sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * obtenerDatosBajaAdhesionTarjeta(ar.com.santanderrio.obp.servicios.
     * tarjetas.view.BajaAdhesionTarjView)
     */
    @Override
    public Respuesta<BajaAdhesionTarjConfirmacion> obtenerDatosBajaAdhesionTarjeta(BajaAdhesionTarjView view) {
        return pagosTarjetaManager.obtenerDatosBajaAdhesionTarjeta(view.getNroTarjeta(), sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * obtenerCuotasPendientes(ar.com.santanderrio.obp.servicios.tarjetas.view.
     * TarjetasView)
     */
    @Override
    public Respuesta<CuotasPendientesView> obtenerCuotasPendientes(TarjetaActivaView tarjetaActiva) {
        LOGGER.info("Post OK: /listarCuotasPendientes.");
        Respuesta<CuotasPendientesView> respuestaCuotasPendientes = cuotasPendienteManager
                .obtenerCuotasPendientes(tarjetaActiva);
        LOGGER.debug(TEXTO_LOGGER, respuestaCuotasPendientes);
        return respuestaCuotasPendientes;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * estadisticaComprobante()
     */
    @Override
    public void estadisticaComprobante() {
        estadisticaManager.add(EstadisticasConstants.PAGO_TARJETA_VISUALIZAR_COMPROBANTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * exportarResumenOnDemand(ar.com.santanderrio.obp.servicios.tarjetas.view.
     * TarjetaActivaView)
     */
    @Override
    public Respuesta<ReporteView> exportarResumenOnDemand(TarjetaActivaView view) {
        return onDemandPuntualResumenManager.exportarOnDemandPuntualResumenConEstadistica(view.getNumeroCuenta(),
                view.getFechas().get(0), view.getAlias());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * exportarResumenOnDemand(ar.com.santanderrio.obp.servicios.tarjetas.view.
     * TarjetaActivaView)
     */
    @Override
    public Respuesta<ReporteView> exportarUltimoResumenOnDemand(TarjetaActivaView view) {
        return ultimoResumenTarjetaManager.descargarResumenActualOnDemand(view.getNumeroCuenta());
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * exportarResumenOnDemand(ar.com.santanderrio.obp.servicios.tarjetas.view.
     * TarjetaActivaView)
     */
    @Override
    public Respuesta<ReporteView> exportarResumenOnDemandMultiple(TarjetaActivaMultipleView view) {
        return onDemandPuntualResumenManager.exportarOnDemandMultipleResumen(view.getNumeroCuenta(),
                view.getFechas().get(0), view.getCantidadADescargar(), view.getAlias());
    }

    /**
     * Obtener resumen resumen anterior.
     *
     * @param tarjetaActiva
     *            the tarjeta activa
     * @return the respuesta
     */
    @Override
    public Respuesta<ResumenAnteriorViewResponse> obtenerResumenesAnteriores(TarjetaActivaView tarjetaActiva) {
        return resumenAnteriorManager.obtenerResumenesAnteriores(tarjetaActiva);
    }

    /**
     * Obtener resumen resumen anterior.
     *
     * @param tarjetaActiva
     *            the tarjeta activa
     * @return the respuesta
     */
    @Override
    public Respuesta<ResumenAnteriorViewResponse> resumenesAnterioresDesdeUltimoResumen(
            TarjetaActivaView tarjetaActiva) {
        Respuesta<ResumenAnteriorViewResponse> res = resumenAnteriorManager.obtenerResumenesAnteriores(tarjetaActiva);
        resumenAnteriorManager.grabarEstadisticaDeResumenesAnteriores(res.getEstadoRespuesta());
        return res;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * tarjetasCuentasParaDebito(ar.com.santanderrio.obp.servicios.tarjetas.view
     * .Reintentar)
     */
    @Override
    public Respuesta<InfoTarjetaAdhesionDebitoView> tarjetasCuentasParaDebito(Reintentar reintentar) {
        return adhesionTarjetaDebitoAutomaticoManager.tarjetasCuentasParaDebito(sesionCliente.getCliente(), reintentar);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * adherirTarjetaDebitoAutomatico(ar.com.santanderrio.obp.servicios.tarjetas
     * .view.DatosAdhesionDebitoTarjetaView)
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> adherirTarjetaDebitoAutomatico(
            DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView) {
        return adhesionTarjetaDebitoAutomaticoManager.adherirTarjetaDebitoAutomatico(sesionCliente.getCliente(),
                datosAdhesionDebitoView);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * estadisticaVisualizacionComprobanteAdhesionDebito()
     */
    @Override
    public void estadisticaVisualizacionComprobanteAdhesionDebito() {
        adhesionTarjetaDebitoAutomaticoManager.estadisticaVisualizacionComprobanteAdhesionDebito();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * estadisticaVisualizacionComprobanteStopDebit()
     */
    @Override
    public void estadisticaVisualizacionComprobanteStopDebit() {
        pagosTarjetaManager.estadisticaVisualizacionComprobanteStopDebit();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * estadisticaVisualizacionComprobanteBajaAdhesion()
     */
    @Override
    public void estadisticaVisualizacionComprobanteBajaAdhesion() {
        pagosTarjetaManager.estadisticaVisualizacionComprobanteBajaAdhesion();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * inicioCancelarStopDebit(ar.com.santanderrio.obp.servicios.tarjetas.view.
     * DatosInicioCancelarStopDebit)
     */
    @Override
    public Respuesta<InicioCancelarStopDebitDTO> inicioCancelarStopDebit(
            DatosInicioCancelarStopDebit datosInicioCancelarStopDebit) {
        return cancelarStopDebitTarjetaManager.inicioCancelarStopDebit(sesionCliente.getCliente(),
                datosInicioCancelarStopDebit);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * ejecutarCancelacionStopDebit(ar.com.santanderrio.obp.servicios.tarjetas.
     * view.DatosCancelarStopDebit)
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> ejecutarCancelacionStopDebit(
            DatosCancelarStopDebit datosCancelarStopDebit) {
        return cancelarStopDebitTarjetaManager.cancelarStopDebit(sesionCliente.getCliente(), datosCancelarStopDebit);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#detalleDePago(
     * ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaPagoSeleccionView)
     */
    @Override
    public Respuesta<DetalleTarjetaPagoView> detalleDePago(TarjetaPagoSeleccionView tarjetaPagoSeleccionView) {
        return pagosTarjetaManager.detalleDePago(tarjetaPagoSeleccionView);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * solicitarFinanciacionTarjeta()
     */
    @Override
    public Respuesta<FinanciacionTarjetaView> solicitarFinanciacionTarjeta(
            FinanciacionTarjetaView financiacionTarjetaView) {
        return financiacionTarjetasManager.solicitarFinanciacionTarjeta(financiacionTarjetaView);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * ejecutarFinanciacionTarjeta()
     */
    @Override
    public Respuesta<FeedbackFinanciacionTarjetaView> ejecutarFinanciacionTarjeta(
            FinanciacionTarjetaView financiacionTarjetaView) {
        return financiacionTarjetasManager.ejecutarFinanciacionTarjeta(financiacionTarjetaView);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * simularFinanciacionTarjeta(ar.com.santanderrio.obp.servicios.tarjetas.
     * entities.FinanciacionTarjetaView)
     */
    @Override
    public Respuesta<FinanciacionTarjetaView> simularFinanciacionTarjeta(
            FinanciacionTarjetaView financiacionTarjetaView) {
        return financiacionTarjetasManager.simularFinanciacionTarjeta(financiacionTarjetaView);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * estadisticaVisualizacionComprobanteCancelacionStopDebit()
     */
    @Override
    public void estadisticaVisualizacionComprobanteCancelacionStopDebit() {
        cancelarStopDebitTarjetaManager.estadisticaVisualizacionComprobanteCancelacionStopDebit();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * inicioModificacionAdhesionDebito(ar.com.santanderrio.obp.servicios.
     * tarjetas.view.NroTarjetaView)
     */
    @Override
    public Respuesta<TarjetaModificacionAdhesionDebitoView> inicioModificacionAdhesionDebito(
            NroTarjetaView nroTarjeta) {
        return adhesionTarjetaDebitoAutomaticoManager.inicioModificacionAdhesionDebito(sesionCliente.getCliente(),
                nroTarjeta);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * modificarAdhesionDebito(ar.com.santanderrio.obp.servicios.tarjetas.view.
     * DatosAdhesionDebitoTarjetaView)
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> modificarAdhesionDebito(
            DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView) {
        return adhesionTarjetaDebitoAutomaticoManager.modificarAdhesionDebito(sesionCliente.getCliente(),
                datosAdhesionDebitoView);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * estadisticaVisualizacionComprobanteModificacionDebito()
     */
    @Override
    public void estadisticaVisualizacionComprobanteModificacionDebito() {
        adhesionTarjetaDebitoAutomaticoManager.estadisticaVisualizacionComprobanteModificacionDebito();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * comprobanteFinanciacionTarjeta(ar.com.santanderrio.obp.servicios.tarjetas
     * .entities.FinanciacionTarjetaView)
     */
    @Override
    public Respuesta<FinanciacionTarjetaView> comprobanteFinanciacionTarjeta(FinanciacionTarjetaView tarjeta) {
        return financiacionTarjetasManager.comprobanteFinanciacionTarjeta(tarjeta);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * validarNuevaRecarga(org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.tarjetas.web.view.
     * ComprobanteRecargaTarjetaView)
     */
    @Override
    public Object validarNuevaRecarga(MessageContext mc, ComprobanteRecargaTarjetaView recargaTarjeta) {
        return (Object) recargaTarjetaManager.recargarTarjeta(mc.getHttpServletRequest(), recargaTarjeta,
                sesionCliente.getCliente());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#vaciarDesafio()
     */
    @Override
    public Object vaciarDesafio() {
        recargaTarjetaManager.vaciarDesafio();
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * descargarComprobantePDF()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobantePDF() {
        return pagosTarjetaManager.descargarComprobantePDF();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * estadisticaVisualizacionDetalleImportePago()
     */
    @Override
    public void estadisticaVisualizacionDetalleImportePago() {
        pagosTarjetaManager.estadisticaVisualizacionDetalleImportePago();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * descargaExcelUltimosConsumos()
     */
    @Override
    public Respuesta<ReporteView> descargaExcelUltimosConsumos() {
        return managerUC.descargarExcelUltimosConsumos();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * descargaExcelCuotasPendientes()
     */
    @Override
    public Respuesta<ReporteView> descargaExcelCuotasPendientes() {
        return cuotasPendienteManager.descargarExcelCuotasPendientes();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.tarjetas.sei.TarjetaSEI#
     * descargaExcelConsultaFinanciacion()
     */
    @Override
    public Respuesta<ReporteView> descargaExcelConsultaFinanciacion() {
        return consultaFinanciacionManager.descargarExcelConsultaFinanciacion();
    }

    @Override
    public Respuesta<ReporteView> exportarUltimosConsumosPDF() {
        return managerUC.exportarUltimosConsumosPDF();
    }

    /**
     * Validar el reemplazo de tarjetas
     *
     * @return Respuesta<TarjetasView>
     */
    @Override
    public Respuesta<TarjetasView> validarReemplazoDeTarjetas() {
        return manager.validarReemplazoDeTarjetas();
    }

    /**
     * Consulta de domicilio principal y laboral.
     *
     * @return DomiciliosDisponiblesView
     */
    public Respuesta<DomiciliosDisponiblesView> consultarDomicilioPrincipalLaboral() {
        return cambioDomicilioManager.consultarDomicilioPrincipalLaboral();
    }

	@Override
	public Respuesta<String> tooltipAgendamientoRecarga() {
		return recargaTarjetaManager.tooltipAgendamientoRecarga();
	}

    public Respuesta<EmisionOfertaIntegradaView> emitirPolizaOfertaIntegrada(EmisionOfertaIntegrada emisionOfertaIntegrada){
        return pagosTarjetaManager.emitirPolizaOfertaIntegrada(emisionOfertaIntegrada);
    }

    public Respuesta<CompraProtegidaView> consultarCompraProtegida(CompraProtegida compraProtegida) {
        return pagosTarjetaManager.consultarCompraProtegida(compraProtegida);
    }
	/**
	 * Obtener token acceso resumen anual.
	 *
	 * @param tarjetaActiva the tarjeta activa
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TokenView> obtenerTokenAccesoResumenAnual(TarjetaActivaView tarjetaActiva) {
		return manager.obtenerTokenAccesoResumenAnual(tarjetaActiva);
	}

}
