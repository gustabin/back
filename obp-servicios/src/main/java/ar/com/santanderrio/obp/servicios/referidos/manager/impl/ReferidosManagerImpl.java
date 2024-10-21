package ar.com.santanderrio.obp.servicios.referidos.manager.impl;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.referidos.bo.ReferidosBO;
import ar.com.santanderrio.obp.servicios.referidos.manager.ReferidosManager;
import ar.com.santanderrio.obp.servicios.referidos.view.ComisionPaqueteReferidoView;
import ar.com.santanderrio.obp.servicios.referidos.view.DatosEstadisticasReferidosView;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * Manager para referidos
 * @author A309331
 *
 */
@Component
public class ReferidosManagerImpl implements ReferidosManager {

    private static final String UTM = "&utm_source=olb&utm_medium=contextual&utm_campaign=referidos_paquetes";

    private static final String SEPARADOR = "|";

    /** The Constant MOBILE *. */
    private static final String OBP_MOBILE = "TM";
    
    /** The Constant CERO *. */
    private static final String OBP_DESKTOP = "TD";
    
    /** Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferidosManagerImpl.class);

    /** App encoding. */
    @Value("${APP.ENCODING}")
    private String appEncoding;

    private static final String OPERACION_MENU_REFERIDOS = "MENU";
    
    private static final String OPERACION_CARRUSEL_REFERIDOS = "CARRUSEL";
    
    
    
    /**
     * Key property para url de invitacion
    */ 
    @Value("${REFERIDOS.URL}")
    private String urlInvitacion;
    
    @Value("${REFERIDOS.PREMIO}")
    private String premioReferido;
    
    
    /**
     * Datos del cliente (en sesion)
     */
    @Autowired
    private SesionCliente sesionCliente;
    
  /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /**
     * Factory para construir respuesta
     */
    @Autowired
    private RespuestaFactory respuestaFactory;

    @Autowired
    private EstadisticaManager estadisticaManager;
    
    @Autowired
    private ReferidosBO referidosBO;
    
	@Autowired
	LegalBO legalBO;
	
	@Autowired
	MensajeBO mensajeBO;
	
    
    /**
     * Obtiene datos para inicio de referidos: URL de formularios y NUP firmado
     */
    @Override
    public Respuesta<ReferidosInicioResponseView> obtenerInicioReferidos() {
        
        ReferidosInicioResponseView view = new ReferidosInicioResponseView();
        view.setUrlInvitacion(this.urlInvitacion);

        String nup = sesionCliente.getCliente().getNup();
        String tokenReferidos;
        try {
            tokenReferidos = buildTokenReferidos(nup, sesionCliente.getCliente().getProgramaBeneficios());
            view.setFirma(tokenReferidos);
            estadisticaManager.add(EstadisticasConstants.INICIO_REFERIDOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } catch (Exception e) {
            LOGGER.error("Error generando firma del NUP {} referente", nup);
            estadisticaManager.add(EstadisticasConstants.INICIO_REFERIDOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);           
        }
        return respuestaFactory.crearRespuestaOk(ReferidosInicioResponseView.class, view);
    }

    /**
     * Obtiene el token con el NUP del referente
     * Para ello es necesario consultar al servicio de referidos, que es el quien nos otorga los tokens de acceso (token handhshake)
     * @param nup
     * @return String
     * @throws Exception 
     */
    private String getTokenHandshakeReferidos(String nup) throws Exception {
        LOGGER.info("Obteniendo firma del NUP referente");      
        String token = null; 
        
        Respuesta<String>tokenRta = referidosBO.obtenerTokenReferidos(nup);
        if(tokenRta.getEstadoRespuesta().equals(EstadoRespuesta.OK)){
            token = StringUtils.trim(tokenRta.getRespuesta());
        } 
        
        return token; 
    }
    
    /**
     * Una vez obtenido el token, lo encriptamos junto al resto de los parametros a base64.
     * @param nup
     * @param programaBeneficios
     * @return
     * @throws Exception 
     */
    private String buildTokenReferidos(String nup, String programaBeneficios) throws Exception {
        
        String tokenHandshake = getTokenHandshakeReferidos(nup);        
        LOGGER.info("Generando Firma del NUP referente");       
        String dispositivo = sesionParametros.getRegistroSession().isMobile() ? OBP_MOBILE :OBP_DESKTOP;        
        byte[] firma;
        String firmaDesEnCriptada = tokenHandshake + SEPARADOR + programaBeneficios + SEPARADOR + dispositivo;
        LOGGER.info("getTokenReferidos = " + firmaDesEnCriptada);
        firma = Base64.encode(firmaDesEnCriptada.getBytes(appEncoding));
        return new String(firma) + UTM;
    }

    public String getAppEncoding() {
        return appEncoding;
    }

    public void setAppEncoding(String appEncoding) {
        this.appEncoding = appEncoding;
    }

    public String getUrlInvitacion() {
        return urlInvitacion;
    }

    public void setUrlInvitacion(String urlInvitacion) {
        this.urlInvitacion = urlInvitacion;
    }

    @Override
    public void grabarEstadisticaReferidos(DatosEstadisticasReferidosView referidosView) {      
        if (OPERACION_MENU_REFERIDOS.equals(referidosView.getOperacion())){
            estadisticaManager.add(EstadisticasConstants.INICIO_MENU_REFERIDOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }else if (OPERACION_CARRUSEL_REFERIDOS.equals(referidosView.getOperacion())){
            estadisticaManager.add(EstadisticasConstants.INICIO_CARRUSEL_REFERIDOS,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);  
        }

    }

	@Override
	public Respuesta<ReferidosInicioResponseView> inicioReferidos() {
		
        ReferidosInicioResponseView referidosView = new ReferidosInicioResponseView();
        Respuesta<ReferidosInicioResponseView> respuestaUrl = obtenerInicioReferidos();

        referidosView.setUrlInvitacion(respuestaUrl.getRespuesta().getUrlInvitacion()+respuestaUrl.getRespuesta().getFirma());
        referidosView.setTitulo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.STACK_REFERIDOS_TITULO).getMensaje());
        referidosView.setSubtitulo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.STACK_REFERIDOS_SUBTITULO).getMensaje());
        referidosView.setCuerpo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.STACK_REFERIDOS_CUERPO).getMensaje());
        referidosView.setBoton(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.STACK_REFERIDOS_TEXTO_BOTON).getMensaje());
        
        List<ComisionPaqueteReferidoView> comisionesPaquetes = new ArrayList<ComisionPaqueteReferidoView>();
		if (premioReferido != null && !premioReferido.isEmpty()) {
			String[] montoPremios = this.premioReferido.split("\\|");
			for (String montoPremio : montoPremios) {
				String[] valores = montoPremio.split("\\,");
				ComisionPaqueteReferidoView comisionPaquete = new ComisionPaqueteReferidoView();
				comisionPaquete.setPaquete(valores[0]);
				comisionPaquete.setComision(valores[1]);
				comisionesPaquetes.add(comisionPaquete);
			}
		}
		
		referidosView.setComisionesPaquetes(comisionesPaquetes);
		
        try {
			referidosView.setLegales(legalBO.obtenerLegal(CodigoMensajeConstantes.STACK_REFERIDOS_LEGAL));
		} catch (DAOException e) {
			LOGGER.info("No se pudo recuperar el legal " + CodigoMensajeConstantes.STACK_REFERIDOS_LEGAL);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
        
        return respuestaFactory.crearRespuestaOk(referidosView);
	}
    
}
