package ar.com.santanderrio.obp.servicios.debinws.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSAdhesionBO;
import ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSAdhesionManager;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;

@Component
@Qualifier("debinWSAdhesionManagerImpl")
public class DebinWSAdhesionManagerImpl extends DebinWSManagerImpl implements DebinWSAdhesionManager {

    /** Debin WS BO */
    @Autowired
    @Qualifier("debinWSAdhesionBOImpl")
    private DebinWSAdhesionBO debinwsAdhesionBO;

    @Value("${TRJCOORD.OPERAINDISTINTO.GESTIONARADHESION}")
    private String valorDesafioGestionAdhesionDebinWS;

    @Autowired
    private DesafioOperacionRSA<GestionarAdhesionDebinesView> gestionarAdhesionDebinesViewDesafioOperacionRSA;

    @Override
    public Respuesta<ConsultarAdhesionDebinesView> buscarCuentasParaAdhesionDebines() {
        //VALIDACION USUARIO CON TOKEN - se comenta para acceder con coordenadas tambien
        /*if (!(sesionCliente.getCliente().tieneSoftToken())) {
            LOGGER.debug("DebinWSAdhesionManager warning cliente sin token");
            ConsultarAdhesionDebinesView outView = new ConsultarAdhesionDebinesView();
            outView.setMensajeSinDesafio(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_SOLICITUDES_ERROR_TOKEN).getMensaje());
            outView.setUrlAyuda(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_URL_AYUDA).getMensaje());
            estadisticaManager.add(EstadisticasConstants.DEBINWS_ADHESION_TOKEN,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return this.respuestaFactory.crearRespuestaWarning(outView, "", TipoError.DEBINWS_ADHESION_ERROR_TOKEN, CodigoMensajeConstantes.DEBINWS_SOLICITUDES_ERROR_TOKEN);
        }*/
        estadisticaManager.add(EstadisticasConstants.DEBINWS_ADHESION_TOKEN,  EstadisticasConstants.CODIGO_ESTADISTICAS_OK);    	
        Respuesta<ConsultarAdhesionDebinesView> gestionarAdhesionView = debinwsAdhesionBO.buscarCuentasParaAdhesionDebines();
        if (EstadoRespuesta.OK.equals(gestionarAdhesionView.getEstadoRespuesta())) {
            String mensajeCuentaEspecial = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_MENSAJE_CUENTA_ESPECIAL).getMensaje();
            gestionarAdhesionView.getRespuesta().setMensajeCuentaEspecial(mensajeCuentaEspecial);	
        }
        return gestionarAdhesionView;
    }

    @Override
    public Respuesta<GestionarAdhesionDebinesView> gestionarAdhesionDebines(GestionarAdhesionDebinesView gestionAdhesionView) {
        //validacion de riesgo RSA
        if (gestionAdhesionView.isValidaRSA()) {
        	// RSA
            AutentificacionCodEstDTO autentificacionCodEstDTO = obtenerAutenticacionCodEstDTO(EstadisticasConstants.TOKEN_GESTION_ADHESION_DEBINWS);
            Respuesta<GestionarAdhesionDebinesView> respuestaRsa = gestionarAdhesionDebinesViewDesafioOperacionRSA.validarOperacionRSA(
            		gestionAdhesionView, Integer.parseInt(valorDesafioGestionAdhesionDebinWS), autentificacionCodEstDTO);
            if (respuestaRsa == null || !EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {	
                return respuestaRsa;
            }
        }
        return debinwsAdhesionBO.gestionarAdhesionDebines(gestionAdhesionView);
    }

    @Override
    public Respuesta<Reporte> descargarComprobanteAdhesion() {
        return debinwsAdhesionBO.descargarComprobanteAdhesion();
    }

}
