package ar.com.santanderrio.obp.servicios.segmento.dao.impl;

import java.sql.SQLException;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.oauth.bo.OAuthBO;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import ar.com.santanderrio.obp.servicios.oauth.config.ApicAppContext;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthGrantTypesEnum;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthTokenResponse;
import ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoSuscripcionesDAO;
import ar.com.santanderrio.obp.servicios.segmento.dto.CajaSeguridadEntity;
import ar.com.santanderrio.obp.servicios.segmento.dto.SubscriptionRequest;
import ar.com.santanderrio.obp.servicios.segmento.dto.SubscriptionResponse;

@Component
public class SegmentoSuscripcionesDAOImpl extends IatxBaseDAO implements SegmentoSuscripcionesDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(SegmentoSuscripcionesDAOImpl.class);
    private static final String SEGMENTS_API_WS = "SEGMENTS-API";

    @Value("${OBP.APIC.SEC_ID_2}")
    private String secId;

    @Value("${SEGMENTS-API.PATH}")
    private String pathRecuperarEstadoSuscripciones;

    @Value("${SEGMENTS-API.SCOPE}")
    private String oAuthCensoScope;

    @Autowired
    private OAuthBO oAuthBO;

    @Autowired
    private RestWebClient restWebClient;

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;
    
	@Autowired
	private IatxComm iatxComm;
    
    private static final String PARAMETRO_NUP = "{nup}";
    
    private static final String NO_RELLAMADA = "N";
    
    private static final String SI_RELLAMADA = "S";
    
    private static final String DIECISEIS_CEROS = "0000000000000000";
    
    private static final int OK_CODIGO_RETORNO = 0;
    
    private static final String HAY_MAS_DATOS = "S";
    
	
	@Override
	public SubscriptionResponse consultarEstadoSuscripciones(SubscriptionRequest request) throws DAOException {
        LOGGER.info("inicio llamado a api segmentos");
        try {
        	String path = pathRecuperarEstadoSuscripciones.replace(PARAMETRO_NUP, request.getNup().toString());
            final WebClient webClient = restWebClient.obtenerClienteRest(SEGMENTS_API_WS);
            webClient.type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(path);
            injectOAuthToken(webClient);
            return webClient.get(SubscriptionResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al consultar estado de las suscripciones");
        }
	}

    private void injectOAuthToken(final WebClient webClient) throws DAOException {
        final ApicAppContext apicAppContext = buildApicAppContext();
        final Respuesta<OAuthTokenResponse> oAuthTokenResponse =
                oAuthBO.getAccessToken(apicAppContext, buildTokenRequest());
        if(!EstadoRespuesta.OK.equals(oAuthTokenResponse.getEstadoRespuesta())) {
            throw new DAOException("Ocurrió un error al solicitar autorización de APIc");
        }
        OAuthTokenResponse tokenData = oAuthTokenResponse.getRespuesta();

        webClient.header(APIcConfigConstants.AUTH, "Bearer " + tokenData.getAccessToken());
        webClient.header(APIcConfigConstants.X_AUTH, tokenData.getJwtToken());
        webClient.header(APIcConfigConstants.APIC_ID, buildApicAppContext().getClientId());
    }

    private OAuthTokenRequestDTO buildTokenRequest(){
        return new OAuthTokenRequestDTO()
                .setGrantType(OAuthGrantTypesEnum.CREDENTIALS)
                .setScope(this.oAuthCensoScope);
    }

    private ApicAppContext buildApicAppContext() throws DAOException {
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(secId);
            if(credential == null) {
                throw new DAOException("Error al obtener credenciales");
            }
            return new ApicAppContext(credential.getUsuario(), credential.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Error al obtener credenciales");
        }
    }
    
    
    public CajaSeguridadEntity consultarCajasSeguridad(Cliente cliente) throws DAOException {
    	CajaSeguridadEntity respuesta = new CajaSeguridadEntity();
		IatxRequest request = buildIatxRequest(cliente);
		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				respuesta = buildDatosCajaSeguridad(iatxResponse);
				if (!respuesta.isHayError()) {
					revisarSiHayRellamada(cliente, respuesta);
				}
			} else {
				throw new DAOException("Error " + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		}
		
		return respuesta;
	}
	
	
	private IatxRequest buildIatxRequest(Cliente cliente) {
		IatxRequest requestIatx = new IatxRequest("CNSZSBCASE", "100");
		IatxRequestData requestDataIatx = new IatxRequestData(cliente);
		requestDataIatx.addBodyValue(NO_RELLAMADA);
		requestDataIatx.addBodyValue(DIECISEIS_CEROS);
		requestDataIatx.addBodyValue("2");
		requestIatx.setData(requestDataIatx);
		return requestIatx;
	}
	
	private IatxRequest buildIatxRequestRellamada(Cliente cliente, CajaSeguridadEntity cajaSeguridadEntity) {
		IatxRequest requestIatx = new IatxRequest("CNSZSBCASE", "100");
		IatxRequestData requestDataIatx = new IatxRequestData(cliente);
		requestDataIatx.addBodyValue(SI_RELLAMADA);
		requestDataIatx.addBodyValue(cajaSeguridadEntity.getClaveRellamada());
		requestDataIatx.addBodyValue("2");
		requestIatx.setData(requestDataIatx);
		return requestIatx;
	}
	
	private void revisarSiHayRellamada (Cliente cliente, CajaSeguridadEntity cajaSeguridadEntity) throws IatxException {
		
		if (HAY_MAS_DATOS.equals(cajaSeguridadEntity.getIndicadorRellamada())) {
			CajaSeguridadEntity cajaSeguridadEntityRellamada = new CajaSeguridadEntity();
			IatxRequest requestRellamada = buildIatxRequestRellamada(cliente, cajaSeguridadEntity);
			IatxResponse iatxResponseRellamada = iatxComm.exec(requestRellamada);
			if (iatxResponseRellamada.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				cajaSeguridadEntityRellamada = buildDatosCajaSeguridad(iatxResponseRellamada);
			}
			cajaSeguridadEntity.getListaCajas().addAll(cajaSeguridadEntityRellamada.getListaCajas());
			cajaSeguridadEntity.setIndicadorRellamada(cajaSeguridadEntityRellamada.getIndicadorRellamada());
			cajaSeguridadEntity.setClaveRellamada(cajaSeguridadEntityRellamada.getClaveRellamada());
			revisarSiHayRellamada(cliente, cajaSeguridadEntity);
		} 
	}
	
	private CajaSeguridadEntity buildDatosCajaSeguridad(IatxResponse iatxResponse){
		
		CajaSeguridadEntity cajaSeguridadEntity = new CajaSeguridadEntity();
		
		int errorCode = iatxResponse.getErrorCode();
		if (OK_CODIGO_RETORNO == errorCode) {
			cajaSeguridadEntity = processTrama(iatxResponse.getTrama(),
					CajaSeguridadEntity.class);
		} else {
			cajaSeguridadEntity.setHayError(true);
		}
		return cajaSeguridadEntity;
	}

}
