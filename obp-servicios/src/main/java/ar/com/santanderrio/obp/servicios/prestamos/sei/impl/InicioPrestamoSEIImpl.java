package ar.com.santanderrio.obp.servicios.prestamos.sei.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultarPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI;
import ar.com.santanderrio.obp.servicios.prestamos.view.*;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.CuotasPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoPreaprobadoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SimuladorPrestamoManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.BajaPrestamoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The Class InicioPrestamoSEIImpl.
 *
 */
@Component("inicioPrestamoSEI")
public class InicioPrestamoSEIImpl implements InicioPrestamoSEI {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory.getLogger(InicioPrestamoSEIImpl.class);

    /** The Constant RESPUESTA. */
    private static final String RESPUESTA = "La respuesta del SEI es: {}.";

    private static final String TIPO_PREAPROBADO = "PREAPROBADO";

    /** The prestamo manager. */
    @Autowired
    private InicioPrestamoManager prestamoManager;

    /** The prestamos manager. */
    @Autowired
    private SimuladorPrestamoManager simuladorPrestamoManager;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The cuotas prestamo manager. */
    @Autowired
    private CuotasPrestamoManager cuotasPrestamoManager;
    
    @Autowired
    private PrestamoPreaprobadoManager prestamoPreaprobadoManager;

    @Autowired
    private SesionParametros sesionParametros;

    @Autowired
    private RespuestaFactory respuestaFactory;

    @Autowired
    private MensajeBO mensajeBO;

    @Autowired
    private PrestamoBO prestamoBO;

    /**
     * Obtener cabecera.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerCabecera()
     */
    @Override
    public Respuesta<CabeceraPrestamosView> obtenerCabecera() {
        return prestamoManager.obtenerCabecera();
    }

    /**
     * Obtener prestamos.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerPrestamos(ar.com.santanderrio.obp.servicios.prestamos.view.
     * ObtenerPrestamoInView)
     */
    @Override
    public Respuesta<PrestamosView> obtenerPrestamos(ObtenerPrestamoInView consultaPrestamo) {
        return prestamoManager.obtenerPrestamos(consultaPrestamo);
    }
    
    /**
     * Obtener prestamos encolados
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<PrestamosEncoladosView> obtenerPrestamosEncolados() {
        return prestamoManager.obtenerPrestamosEncolados();
    }


    /**
     * Obtener prestamo sueldos.
     *
     * @param consultaPrestamoSueldos the consulta prestamo sueldos
     * @return the respuesta
     */
    @Override
    public Respuesta<PrestamoSueldosView> obtenerPrestamoSueldos(ObtenerPrestamoInView consultaPrestamoSueldos) {
        return prestamoManager.obtenerPrestamoSueldos(consultaPrestamoSueldos);
    }
    
	/**
	 * Confirmar prestamo sueldos.
	 *
	 * @param prestamoSueldosConfirmacionView the prestamo sueldos confirmacion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PrestamoSueldosView> confirmarPrestamoSueldos(PrestamoSueldosConfirmacionView prestamoSueldosConfirmacionView) {
		return prestamoManager.confirmarPrestamoSueldos(prestamoSueldosConfirmacionView);
	}
	


    /**
     * Notificar acceso desde home.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * notificarAccesoDesdeHome()
     */
    @Override
    public Respuesta<Void> notificarAccesoDesdeHome() {
        return prestamoManager.notificarAccesoDesdeHome();
    }

    /**
     * Obtener detalle cuota.
     *
     * @param consultarPrestamo the consultar prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * getDetallePrestamos(ar.com.santanderrio.obp.servicios.pagos.web.view.
     * ConsultaPrestamo)
     */
    @Override
    public Respuesta<DetallePrestamoView> obtenerDetalleCuota(ConsultarPrestamo consultarPrestamo) {
        return prestamoManager.getDetallePrestamos(consultarPrestamo.getConsultaPrestamo(), true);
    }

    /**
     * Obtener configuracion pago cuota prestamo.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerConfiguracionPagoCuotaPrestamo(ar.com.santanderrio.obp.servicios.
     * pagos.web.view.ConsultaPrestamo)
     */
    @Override
    public Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(
            ConsultaPrestamo consultaPrestamo) {
        return prestamoManager.obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, true);
    }

    /**
     * Grabar estadistica inicio simulador prestamo.
     *
     * @param puntoDeAcceso the punto de acceso
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * inicioSimulacionPrestamos()
     */
    @Override
    public Respuesta<Void> grabarEstadisticaInicioSimuladorPrestamo(PuntoDeAccesoView puntoDeAcceso) {
        return simuladorPrestamoManager.grabarEstadisticaInicioSimuladorPrestamo(puntoDeAcceso);
    }

    /**
     * Obtener resultado simulacion.
     *
     * @param solicitudSimulacion the solicitud simulacion
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerResultadoSimulacion(ar.com.santanderrio.obp.servicios.prestamos.
     * view.SolicitudSimulacionView)
     */
    @Override
    public Respuesta<ResultadoSimulacionView> obtenerResultadoSimulacion(SolicitudSimulacionView solicitudSimulacion) {
        return simuladorPrestamoManager.simular(solicitudSimulacion);
    }

    /**
     * Obtiene el feedback de la solicitud de un prestamo.
     *
     * @param solicitudPrestamo
     *            the solicitud prestamo
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> confirmacionSolicitudPrestamo(SolicitudSimulacionView solicitudPrestamo) {
        return simuladorPrestamoManager.adquirirPrestamo(solicitudPrestamo);
    }

    @Override
    public Respuesta<ComprobanteFeedbackView> confirmacionSolicitudPrestamoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView) {
        return simuladorPrestamoManager.adquirirPrestamoEncolado(liquidacionPrestamoEncoladoView);
    }

    @Override
    public Respuesta<BajaPrestamoView> eliminarSolicitudPendiente(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView) {
        //Obtenemos el prestamo encolado desde la api
        PrestamoEncoladoEntity prestamoEncoladoEntity;
        try {
            if (liquidacionPrestamoEncoladoView.getId() == null) {
                liquidacionPrestamoEncoladoView.setId(liquidacionPrestamoEncoladoView.getSolicitudId());
            }
            prestamoEncoladoEntity = prestamoBO.obtenerPrestamoEncolado(liquidacionPrestamoEncoladoView.getId());

            if (TIPO_PREAPROBADO.equals(prestamoEncoladoEntity.getLoanType())) {
                prestamoPreaprobadoManager.eliminarPrestamoPreaprobadoMonoproductoEncolado(liquidacionPrestamoEncoladoView, prestamoEncoladoEntity);
            } else {
                simuladorPrestamoManager.eliminarSolicitudPendiente(liquidacionPrestamoEncoladoView, prestamoEncoladoEntity);
            }

        } catch (DAOException e) {
            return respuestaFactory.crearRespuestaError(BajaPrestamoView.class, mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_PRESTAMO_ERROR).getMensaje(), null);
        }
        BajaPrestamoView bajaPrestamoView = new BajaPrestamoView();
        bajaPrestamoView.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        bajaPrestamoView.setComprobante(liquidacionPrestamoEncoladoView.getSolicitudId());
        bajaPrestamoView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_PRESTAMO_OK).getMensaje());
        return respuestaFactory.crearRespuestaOk(BajaPrestamoView.class, bajaPrestamoView);
    }

    /**
     * Obtiene el feedback de la solicitud de encolar un prestamo.
     *
     * @param solicitudPrestamo
     *            the solicitud prestamo
     * @return the respuesta
     */
    @Override
    public Respuesta<ComprobanteFeedbackView> encolarSolicitudPrestamo(SolicitudSimulacionView solicitudPrestamo) {
        return simuladorPrestamoManager.encolarPrestamo(solicitudPrestamo);
    }

    /**
     * Grabar estadistica visualizacion comprobante.
     *
     * @param comprobanteView the comprobante view
     * @return the boolean
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * grabarEstadisticaVisualizacionComprobante(ar.com.santanderrio.obp.
     * servicios.prestamos.view.ComprobantePrestamoView)
     */
    @Override
    public Boolean grabarEstadisticaVisualizacionComprobante(ComprobantePrestamoView comprobanteView) {
        return simuladorPrestamoManager.grabarEstadisticaVisualizacionComprobante(comprobanteView);
    }

    /**
     * Grabar estadistica visualizacion ayuda.
     *
     * @return the boolean
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * grabarEstadisticaVisualizacionAyuda()
     */
    @Override
    public Boolean grabarEstadisticaVisualizacionAyuda() {
        return estadisticaManager.add(EstadisticasConstants.VISUALIZACION_AYUDA_SIMULADOR_PRESTAMO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Grabar estadistica visualizacion tasas.
     *
     * @return the boolean
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * grabarEstadisticaVisualizacionTasas()
     */
    @Override
    public Boolean grabarEstadisticaVisualizacionTasas() {
        return estadisticaManager.add(EstadisticasConstants.VISUALIZACION_TASAS_SIMULADOR_PRESTAMO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener proximas cuotas.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerProximasCuotas(ar.com.santanderrio.obp.servicios.pagos.web.view.
     * ConsultaPrestamo)
     */
    @Override
    public Respuesta<ProximasCuotasView> obtenerProximasCuotas(ConsultaPrestamo consultaPrestamo) {
        return cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);
    }

    /**
     * Grabar estadistica detalle proxima cuota.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * grabarEstadisticaDetalleProximaCuota(ar.com.santanderrio.obp.servicios.
     * pagos.web.view.ConsultaPrestamo)
     */
    @Override
    public Respuesta<Void> grabarEstadisticaDetalleProximaCuota(ConsultaPrestamo consultaPrestamo) {
        return cuotasPrestamoManager.grabarEstadisticaDetalleProximaCuota(consultaPrestamo);
    }

    /**
     * Obtiene una respuesta para la grilla de cuotas pagas de un prestamo.
     *
     * @param consultaPrestamo
     *            the consulta prestamo
     * @return the respuesta
     */
    @Override
    public Respuesta<ProximasCuotasView> obtenerCuotasPagas(ConsultaPrestamo consultaPrestamo) {
        Respuesta<ProximasCuotasView> respuestaCuotasPagas = cuotasPrestamoManager.obtenerCuotasPagas(consultaPrestamo);
        LOGGER.info(RESPUESTA, respuestaCuotasPagas);
        return respuestaCuotasPagas;
    }

    /**
     * Grabar estadistica detalle cuota paga.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * grabarEstadisticaDetalleCuotaPaga(ar.com.santanderrio.obp.servicios.pagos
     * .web.view.ConsultaPrestamo)
     */
    @Override
    public Respuesta<Void> grabarEstadisticaDetalleCuotaPaga(ConsultaPrestamo consultaPrestamo) {
        return cuotasPrestamoManager.grabarEstadisticaDetalleCuotaPaga(consultaPrestamo);
    }

    /**
     * Obtener prestamos cancelados.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerPrestamosCancelados()
     */
    @Override
    public Respuesta<PrestamosCanceladosView> obtenerPrestamosCancelados() {
        return prestamoManager.obtenerPrestamosCancelados();
    }

    /**
     * Obtener cuotas de cancelado.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerCuotasDeCancelado(ar.com.santanderrio.obp.servicios.pagos.web.view
     * .ConsultaPrestamo)
     */
    @Override
    public Respuesta<ProximasCuotasView> obtenerCuotasDeCancelado(ConsultaPrestamo consultaPrestamo) {
        return cuotasPrestamoManager.obtenerCuotasDeCancelado(consultaPrestamo);
    }

    /**
     * Grabar estadistica detalle cuota de cancelado.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * grabarEstadisticaDetalleCuotaDeCancelado()
     */
    @Override
    public Respuesta<Void> grabarEstadisticaDetalleCuotaDeCancelado() {
        return cuotasPrestamoManager.grabarEstadisticaDetalleCuotaDeCancelado();
    }

    /**
     * Obtener detalle.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * obtenerDetalle(ar.com.santanderrio.obp.servicios.pagos.web.view.
     * ConsultaPrestamo)
     */
    @Override
    public Respuesta<ProximasCuotasView> obtenerDetalle(ConsultaPrestamo consultaPrestamo) {
        return cuotasPrestamoManager.obtenerDetalle(consultaPrestamo);
    }

    /**
     * Ver detalle prestamo.
     *
     * @param consultaPrestamo the consulta prestamo
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * verDetallePrestamo(ar.com.santanderrio.obp.servicios.pagos.web.view.
     * ConsultaPrestamo)
     */
    @Override
    public Respuesta<DetalleCuotaPrestamoView> verDetallePrestamo(ConsultaPrestamo consultaPrestamo) {
        return prestamoManager.verDetallePrestamo(consultaPrestamo);
    }

    /**
     * Descargar PDF calculador prestamos.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * descargarPDFCalculadorPrestamos()
     */
    @Override
    public Respuesta<ReporteView> descargarPDFCalculadorPrestamos() {
        return prestamoManager.descargarPDFCalculadorPrestamos();
    }

    /**
     * Obtiene simulacion del prestamo para visualizar
     */
    @Override
    public Respuesta<ResultadoSimulacionView> obtenerSolicitudPrestamo() {
        return simuladorPrestamoManager.obtenerSolicitudPrestamo();
    }

    /**
     * Descargar cuotas pagas PDF.
     *
     * @param proximaCuotaView the proxima cuota view
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * descargarProximasCuotasPDF(ar.com.santanderrio.obp.servicios.prestamos.
     * view.CuotaView)
     */
    @Override
    public Respuesta<ReporteView> descargarCuotasPagasPDF(ProximaCuotaView proximaCuotaView) {
        return prestamoManager.descargarCuotasPagasPDF(proximaCuotaView);
    }

    /**
     * Descargar comprobante detalle prestamo.
     *
     * @param detalleCuotaPrestamoView the detalle cuota prestamo view
     * @return the respuesta
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#descargarComprobanteDetallePrestamo(ar.com.santanderrio.obp.servicios.prestamos.view.DetalleCuotaPrestamoView)
     */
    @Override
    public Respuesta<ReporteView> descargarComprobanteDetallePrestamo(
            DetalleCuotaPrestamoView detalleCuotaPrestamoView) {
        return prestamoManager.descargarComprobanteDetallePrestamo(detalleCuotaPrestamoView);
    }

    /**
     * Descargar proximas cuotas PDF.
     *
     * @param proximaCuotaView the proxima cuota view
     * @return the respuesta
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#descargarProximasCuotasPDF(ar.com.santanderrio.obp.servicios.prestamos.view.CuotaView)
     */
    @Override
    public Respuesta<ReporteView> descargarProximasCuotasPDF(CuotaView proximaCuotaView) {

        return prestamoManager.descargarProximasCuotasPDF(proximaCuotaView);
    }

    /**
     * Descargar detalle prestamo PDF.
     *
     * @param detallePrestamosView the detalle prestamos view
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.prestamos.sei.InicioPrestamoSEI#
     * descargarDetallePrestamoPDF(ar.com.santanderrio.obp.servicios.prestamos.
     * view.DetallePrestamosView)
     */
    @Override
    public Respuesta<ReporteView> descargarDetallePrestamoPDF(DetallePrestamosView detallePrestamosView) {

        return prestamoManager.descargarDetallePrestamoPDF(detallePrestamosView);
    }
    
    /**
     * Exportar cuotas pagas.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<ReporteView> exportarCuotasPagas() {
        return cuotasPrestamoManager.exportarCuotasPagas();
    }
    
    /**
     * Exportar proximas cuotas.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<ReporteView> exportarProximasCuotas() {
        return cuotasPrestamoManager.exportarProximasCuotas();
    }

	@Override
	public Respuesta<ArchivoAdjuntoPrestamoSueldosView> adjuntarArchivoPrestamoSueldos(
			DocumentacionAdjuntaView documentacionAdjuntaView) {
		return prestamoManager.adjuntarArchivoPrestamoSueldos(documentacionAdjuntaView);
	}

	@Override
	public Respuesta<Void> borrarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView) {
		return prestamoManager.borrarArchivo(documentacionAdjuntaView);
	}

	@Override
	public Respuesta<ComprobanteStopDebitPrestamoView> confirmarStopDebitPrestamos(ConfirmacionStopDebitView confirmacionStopDebitView) {
		return prestamoManager.confirmarPrestamosStopDebit(confirmacionStopDebitView);
	}

	@Override
	public Respuesta<ReporteView> descargarCompStopDebitPrestamo(ComprobanteStopDebitPrestamoView comprobanteStopDebitPrestamoView) {
		return prestamoManager.descargarCompStopDebitPrestamo(comprobanteStopDebitPrestamoView);		
	}

	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> consultarPrestamoATPVigente() {
		return prestamoManager.consultarPrestamoATPVigente();
	}

	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> getPreparacionPrestamoTasaSubsidiada() {
		
		return prestamoManager.getPreparacionPrestamoTasaSubsidiada();
	}
	
	@Override
	public Respuesta<Void> grabarEstadisticaConfiguracionPrestamoTasaSubsidiada() {
		return prestamoManager.grabarEstadisticaConfiguracionPrestamoTasaSubsidiada();
	}
	
	@Override
	public Respuesta<Void> grabarEstadisticaConfirmacionPrestamoTasaSubsidiada() {
		return prestamoManager.grabarEstadisticaConfirmacionPrestamoTasaSubsidiada();
	}

	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> agregarNuevoCBU(PrestamoSueldoTasaSubsidiadaView prestamoSueldoTasaSubsidiadaView) {
		return prestamoManager.agregarNuevoCBU(prestamoSueldoTasaSubsidiadaView);

	}
	
	@Override
	public Respuesta<PrestamoSueldoTasaSubsidiadaView> confirmarPrestamoTasaSubsidiada(
			PrestamoSueldoTasaSubsidiadaView prestamoSueldoView) {
		return prestamoManager.confirmarPrestamoTasaSubsidiada(prestamoSueldoView);
	}
	
	@Override
	public Respuesta<ReporteView> descargarPrestamoTasaSubsidiada() {
		return prestamoManager.descargarPrestamoTasaSubsidiada();
	}

	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> simularPrestamoPreaprobadoMonoproducto(PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobado) {
		return prestamoPreaprobadoManager.simularPrestamosPreaprobadoMonoproducto(prestamoPreaprobado);
	}

	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio) {
		return prestamoPreaprobadoManager.confirmarPrestamoPreaprobadoMonoproducto(desafio);
	}

    @Override
    public Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproductoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView) {
        return prestamoPreaprobadoManager.confirmarPrestamoPreaprobadoMonoproductoEncolado(liquidacionPrestamoEncoladoView);
    }

    @Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> encolarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio) {
		return prestamoPreaprobadoManager.encolarPrestamoPreaprobadoMonoproducto(desafio);
	}

	@Override
	public Respuesta<ReporteView> descargarPrestamoPreaprobadoMonoproducto() {
		return prestamoPreaprobadoManager.descargarPrestamoPreaprobadoMonoproducto();
	}

	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> obtenerPrestamoPreaprobadoMonoproducto() {
		return prestamoPreaprobadoManager.obtenerPrestamoPreaprobadoMonoproducto();
	}

	@Override
	public Respuesta<Void> calcularOfertaPreaprobadoFromDeeplink() {
		return prestamoPreaprobadoManager.calcularOfertaPreaprobadoFromDeeplink();
	}
	
	@Override
	public Respuesta<CancelacionPrestamoView> simularCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView) {
		return prestamoManager.simularCancelacionPrestamo(cancelacionView);
	}

	@Override
	public Respuesta<CancelacionPrestamoView> ejecutarCancelacionPrestamo(CancelacionPrestamoRequestView cancelacionView) {
		return prestamoManager.ejecutarCancelacionPrestamo(cancelacionView);

	}
	
	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		return prestamoManager.generarComprobantePDF();
	}

    @Override
    public Respuesta<Void> grabarEstadisticaVisualizacionComprobanteCancelacionPrestamo() {
        return prestamoManager.grabarEstadisticaVisualizacionComprobanteCancelacionPrestamo();
    }

    @Override
    public Boolean grabarEstadisticaFeedbackCancelacionPrestamo(FeedbackCancelacionView view) {
        return estadisticaManager.add(view.cargarEstadistica());
    }

}
