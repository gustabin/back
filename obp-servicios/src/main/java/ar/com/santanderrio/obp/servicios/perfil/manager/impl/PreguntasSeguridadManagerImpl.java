/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.PreguntasSeguridadBO;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.manager.PreguntasSeguridadManager;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobantePreguntasSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntaSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;

/**
 * PreguntasSeguridadManagerImpl.
 *
 * @author Silvina_Luque
 */
@Component("preguntasSeguridadManager")
public class PreguntasSeguridadManagerImpl implements PreguntasSeguridadManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PreguntasSeguridadManagerImpl.class);

    /** The preguntasSeguridadBO BO. */
    @Autowired
    private PreguntasSeguridadBO preguntasSeguridadBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The legal BO. */
    @Autowired
    private LegalBO legalBO;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<PreguntasSeguridadView> desafioOperacionRSA;

    /** The valor desafio cambioDomicilio. */
    @Value("${TRJCOORD.OPERAINDISTINTO.PREGUNTASSEGURIDAD:3}")
    private Integer valorDesafioPerfilPreguntasSeguridad;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.manager.
     * PreguntasSeguridadManager#consultaPreguntasSeguridad()
     */
    @Override
    public Respuesta<PreguntasSeguridadView> consultaPreguntasSeguridad() {
        LOGGER.info("PreguntasSeguridadManagerImpl consultaPreguntasSeguridad");
        Respuesta<PreguntasSeguridadView> rta = preguntasSeguridadBO.consultarPreguntasSeguridad();
        if (rta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            estadisticaManager.add(EstadisticasConstants.PERFIL_CONSULTA_PREGUNTAS_SEGURIDAD,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return rta;
        }
        if (rta.getRespuesta().isTienePreguntasCargadas()) {
            Mensaje mensaje = mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_CONTEXTUAL_PREGUNTAS_SEGURIDAD);
            rta.getRespuesta().setMensajeContextual(mensaje.getMensaje());
            estadisticaManager.add(EstadisticasConstants.PERFIL_CONSULTA_PREGUNTAS_SEGURIDAD,
                    EstadisticasConstants.PERFIL_MODIF_PREG_SEGURIDAD_CODIGO_PREGCARGADAS);
        } else {
            estadisticaManager.add(EstadisticasConstants.PERFIL_CONSULTA_PREGUNTAS_SEGURIDAD,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        LOGGER.debug("CambioDomicilioManager Salida ObtenerInfoAdicionalDomTel");
        return rta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.manager.
     * PreguntasSeguridadManager#guardarPreguntasSeguridad(ar.com.santanderrio.
     * obp.servicios.perfil.web.view.PreguntasSeguridadView)
     */
    @Override
    public Respuesta<PreguntasSeguridadView> guardarPreguntasSeguridad(PreguntasSeguridadView preguntasSeguridadView) {
        LOGGER.info("PreguntasSeguridadManagerImpl guardarPreguntasSeguridad");

        if (sesionParametros.getContador() == null) {
            sesionParametros.setContador(new ContadorIntentos(2));
            preguntasSeguridadView.setPermiteReintentar(true);
        }

        Respuesta<PreguntasSeguridadView> respuestaFinal = validarOperacionRSA(preguntasSeguridadView);
        if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
            return respuestaFinal;
        }
        String mensajeFeedBack;
        Mensaje mensaje;
        List<PreguntaSeguridadView> listaPreguntas = preguntasSeguridadView.getListaPreguntas();
        Respuesta<ModificacionPreguntasSeguridadOutEntity> respuestaModifPreguntas = preguntasSeguridadBO
                .guardarPreguntasSeguridad(listaPreguntas);
        if (respuestaModifPreguntas.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.AVISO_PREGUNTAS_SEGURIDAD_OK);
            mensajeFeedBack = mensaje.getMensaje();
            preguntasSeguridadView.setMensajeFeedback(mensajeFeedBack);
            respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaFinal.setRespuesta(preguntasSeguridadView);
            sesionParametros.setContador(null);
            sesionParametros.setNroComprobante(respuestaModifPreguntas.getRespuesta().getNroComprobante());
            estadisticaManager.add(EstadisticasConstants.PERFIL_MODIFICACION_PREGUNTAS_SEGURIDAD,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        } else {
            if (!sesionParametros.getContador().permiteReintentar()) {
                sesionParametros.setContador(null);
                preguntasSeguridadView.setPermiteReintentar(false);
            }
            mensaje = mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PREGUNTAS_SEGURIDAD_MODIFICACION);
            mensajeFeedBack = mensaje.getMensaje();
            preguntasSeguridadView.setMensajeFeedback(mensajeFeedBack);
            respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
            List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setTipoError(TipoError.PREGUNTAS_SEGURIDAD_ERROR_GENERICO.getDescripcion());
            itemMensajeRespuesta.setMensaje(mensajeFeedBack);
            mensajesList.add(itemMensajeRespuesta);
            respuestaFinal.setItemMensajeRespuesta(mensajesList);
            respuestaFinal.setRespuesta(preguntasSeguridadView);
            estadisticaManager.add(EstadisticasConstants.PERFIL_MODIFICACION_PREGUNTAS_SEGURIDAD,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        LOGGER.debug("PreguntasSeguridadManagerImpl Salida guardarPreguntasSeguridad");
        return respuestaFinal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.manager.
     * PreguntasSeguridadManager#verComprobante()
     */
    @Override
    public Respuesta<ComprobantePreguntasSeguridadView> verComprobante() {
        LOGGER.info("PreguntasSeguridadManagerImpl verComprobante");
        Respuesta<ComprobantePreguntasSeguridadView> respuesta = respuestaFactory
                .crearRespuestaOk(ComprobantePreguntasSeguridadView.class);
        ComprobantePreguntasSeguridadView view = new ComprobantePreguntasSeguridadView();
        view.setNroComprobante(sesionParametros.getNroComprobante());
        view.setFecha(DateFormatUtils.format(new Date(), "dd/MM/yyyy"));
        try {
            String legales = legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_COMPROBANTE);
            view.setLegales(legales);
        } catch (DAOException e) {
            view.setLegales(StringUtils.EMPTY);
        }
        respuesta.setRespuesta(view);
        LOGGER.debug("PreguntasSeguridadManagerImpl finalizando verComprobante");
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.manager.
     * PreguntasSeguridadManager#descargarComprobante()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobante() {
        LOGGER.info("PreguntasSeguridadManagerImpl _  iniciando descargarComprobante");
        DatosComprobanteEntity datos = new DatosComprobanteEntity();
        datos.setNroComprobante(sesionParametros.getNroComprobante());
        datos.setFecha(DateFormatUtils.format(new Date(), "dd/MM/yyyy"));
        Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
        Respuesta<Reporte> respuestaReporte = preguntasSeguridadBO.descargarComprobante(datos);
        respuestaView.setEstadoRespuesta(respuestaReporte.getEstadoRespuesta());
        if (respuestaReporte.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            ReporteView reporteView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        } else {
            LOGGER.debug("PreguntasSeguridadManagerImpl _ Error descargando comprobante");
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_PREGUNTAS_SEGURIDAD_GENERICO);
        }

        LOGGER.debug("PreguntasSeguridadManagerImpl _  finalizando descargarComprobante");
        return respuestaView;
    }

    /**
     * Validar operacion RSA.
     *
     * @param preguntasView
     *            the preguntas view
     * @return the respuesta
     */
    private Respuesta<PreguntasSeguridadView> validarOperacionRSA(PreguntasSeguridadView preguntasView) {
        Respuesta<PreguntasSeguridadView> respEjecucionMetodoAutentificacion = desafioOperacionRSA
                .validarOperacionRSA(preguntasView, valorDesafioPerfilPreguntasSeguridad, null);
        grabarEstadisticaRSACambioPreguntas(preguntasView.getDesafio().getTipoDesafio(),
                respEjecucionMetodoAutentificacion.getEstadoRespuesta());
        return respEjecucionMetodoAutentificacion;
    }

    private void grabarEstadisticaRSACambioPreguntas(TipoDesafioEnum tipoDesafio, EstadoRespuesta estadoRespuesta) {
        if (tipoDesafio ==  null) {
            String codigoEstadistica = TipoDesafioEnum.BANELCO.equals(tipoDesafio)
                    ? EstadisticasConstants.RSA_CAMBIO_PREGUNTAS_8_DIGITOS
                            : EstadisticasConstants.RSA_CAMBIO_PREGUNTAS_TOKEN;
            String resultadoEstadistica = EstadoRespuesta.OK.equals(estadoRespuesta)
                    ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                            : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
            estadisticaManager.add(codigoEstadistica, resultadoEstadistica);
        } else {
            LOGGER.info("No se graba estadísticas tipoDesafio: {}", tipoDesafio);
        }
    }

}
