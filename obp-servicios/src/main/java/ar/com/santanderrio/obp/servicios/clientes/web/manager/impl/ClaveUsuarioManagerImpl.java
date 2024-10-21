/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.web.manager.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO;
import ar.com.santanderrio.obp.servicios.clientes.bo.ReporteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosComprobante;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClaveUsuarioManager;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.ComprobanteClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ClaveUsuarioManagerImpl.
 */
@Component("claveUsuarioManager")
public class ClaveUsuarioManagerImpl implements ClaveUsuarioManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaveUsuarioManagerImpl.class);

    /** The valor desafio monedero. */
    @Value("${TRJCOORD.OPERAINDISTINTO.CAMBIOCLAVEYUSUARIO}")
    private Integer valorDesafioPerfilCambioClaveUsr;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The credenciales BO. */
    @Autowired
    private CredencialesBO credencialesBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    @Autowired
    private DesafioOperacionRSA<CambioClaveUsuarioView> desafioOperacionRSA;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The legal BO. */
    @Autowired
    private LegalBO legalBO;

    /** The reporte BO. */
    @Autowired
    private ReporteBO reporteBO;
    
    @Autowired
    private ClienteBO clienteBO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.web.manager.
     * ClaveUsuarioManager#cambioUsuarioClave(javax.servlet.http.
     * HttpServletRequest, ar.com.santanderrio.obp.servicios.clientes.web.view.
     * CambioClaveUsuarioView)
     */
    @Override
    public Respuesta<CambioClaveUsuarioView> cambioUsuarioClave(HttpServletRequest request,
            CambioClaveUsuarioView cambioClaveUsuarioView) {
        LOGGER.info("Cambio Usuario Clave");
        if (sesionParametros.getContador() == null) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        Respuesta<CambioClaveUsuarioView> respuestaFinal = desafioOperacionRSA.validarOperacionRSA(
                cambioClaveUsuarioView, valorDesafioPerfilCambioClaveUsr, autentificacionCodEstDTO);

        if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
            return respuestaFinal;
        }

        LOGGER.info("LLamada al Seginform");
        CambioUsuarioEntity entity = crearCambioUsuarioEntity(cambioClaveUsuarioView);
        Respuesta<ResumenCliente> respuestaResumenCliente = credencialesBO.cambioUsuario(request, entity, false);

        respuestaFinal = Respuesta.copy(CambioClaveUsuarioView.class, respuestaResumenCliente);

        if (EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
            CambioClaveUsuarioView respCambioClaveUsuarioView = new CambioClaveUsuarioView();
            sesionParametros.setNroComprobante(respuestaResumenCliente.getRespuesta().getNroComprobante());
            respCambioClaveUsuarioView.setMensajeFeedback(
                    mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CAMBIO_CLAVE_USUARIO_FEEDBACK_OK).getMensaje());
            respuestaFinal.setRespuesta(respCambioClaveUsuarioView);
            
            try {
            	Respuesta<Boolean> resultado = clienteBO.actualizarUltimaFechaCambioClave(Long.valueOf(respuestaResumenCliente.getRespuesta().getNup()));
            	if(EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
	            	estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
	    	                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            	} else {
            		estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
        	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            	}
    		} catch (NumberFormatException e) {
    			estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
    	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    		} catch (BusinessException e) {
    			estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
    	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    		}
            
            estadisticaManager.add(EstadisticasConstants.CAMBIO_USUARIO_CLAVE_CONFIRMACION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (EstadoRespuesta.WARNING.equals(respuestaFinal.getEstadoRespuesta())) {
            return respuestaFinal;
        } else if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta()) && TipoError.ERROR_GENERICO
                .getDescripcion().equals(respuestaFinal.getItemsMensajeRespuesta().get(0).getTipoError())) {
            estadisticaManager.add(EstadisticasConstants.CAMBIO_USUARIO_CLAVE_CONFIRMACION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFinal;
        } else {
            if (!sesionParametros.getContador().permiteReintentar()) {
                sesionParametros.setContador(null);
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                        StringUtils.EMPTY);
            }
            respuestaFinal = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_CON_REINTENTOS,
                    StringUtils.EMPTY);
            estadisticaManager.add(EstadisticasConstants.CAMBIO_USUARIO_CLAVE_CONFIRMACION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        return respuestaFinal;
    }

    /**
     * Crear cambio usuario entity.
     *
     * @param cambioUsuarioView
     *            the cambio usuario view
     * @return the cambio usuario entity
     */
    private CambioUsuarioEntity crearCambioUsuarioEntity(CambioClaveUsuarioView cambioUsuarioView) {
        CambioUsuarioEntity entity = new CambioUsuarioEntity();

        entity.setCliente(sesionCliente.getCliente());
        entity.setStrNewUsr(cambioUsuarioView.getStrNewUsr());
        entity.setStrOldUsr(cambioUsuarioView.getStrOldUsr());
        entity.setStrNewPin(cambioUsuarioView.getStrNewPin());
        entity.setStrOldPin(cambioUsuarioView.getStrOldPin());
        return entity;
    }

    /**
     * Asignar estadisticas de autenticacion.
     * 
     * @return AutentificacionCodEstDTO
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.CAMBIO_USUARIO_CLAVE_VALIDAR_TOKEN);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.CAMBIO_USUARIO_CLAVE_SOLICITUD_COORDENADAS);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.CAMBIO_USUARIO_CLAVE_VALIDAR_COORDENADAS);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.CAMBIO_USUARIO_CLAVE_VALIDAR_BANELCO);
        return autentificacionCodEstDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.web.manager.
     * ClaveUsuarioManager#getComprobanteClaveUsuario()
     */
    @Override
    public Respuesta<ComprobanteClaveUsuarioView> getComprobanteClaveUsuario() {
        Respuesta<ComprobanteClaveUsuarioView> respuesta = respuestaFactory
                .crearRespuestaOk(ComprobanteClaveUsuarioView.class);
        ComprobanteClaveUsuarioView view = new ComprobanteClaveUsuarioView();
        view.setComprobante(sesionParametros.getNroComprobante());
        view.setFechaHora(DateFormatUtils.format(new Date(), "dd/MM/yyyy HH:mm"));
        try {
            String legales = legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_COMPROBANTE);
            view.setLegales(legales);
        } catch (DAOException e) {
            view.setLegales(StringUtils.EMPTY);
        }
        respuesta.setRespuesta(view);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.web.manager.
     * ClaveUsuarioManager#descargarComprobante(java.lang.String)
     */
    @Override
    public Respuesta<ReporteView> descargarComprobante(String tipoComprobante) {
        Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
        DatosComprobante datos = new DatosComprobante();
        ReporteView reporteView = new ReporteView();
        try {
            datos.setNroComprobante(sesionParametros.getNroComprobante());
            datos.setTipoComprobante(tipoComprobante);
            Respuesta<Reporte> respuestaReporte = reporteBO.descargarComprobante(datos);
            if (respuestaReporte.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                respuestaView.setEstadoRespuesta(respuestaReporte.getEstadoRespuesta());
                reporteView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
                respuestaView.setRespuesta(reporteView);
                estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                respuestaView.setEstadoRespuesta(respuestaReporte.getEstadoRespuesta());
                estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        } catch (Exception ex) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_REPORTE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

}
