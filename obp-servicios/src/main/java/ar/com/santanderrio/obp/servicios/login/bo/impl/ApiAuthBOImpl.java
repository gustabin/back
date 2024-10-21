/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.bo.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.jwt.JwtToken;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.loggedinusercache.dao.UserCacheDAO;
import ar.com.santanderrio.obp.servicios.login.bo.ApiAuthBO;
import ar.com.santanderrio.obp.servicios.login.dao.ApiAuthDAO;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthLoginResponse;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthToken;

/**
 * The Class AuthenticatorBOImpl.
 */
@Component
public class ApiAuthBOImpl implements ApiAuthBO {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAuthBOImpl.class);

    /** The cliente BO. */
    @Autowired
    private ApiAuthDAO apiAuthDAO;

    @Autowired
    private RespuestaFactory respuestaFactory;
    
    @Autowired
    UserCacheDAO userCacheDAO;

    @Override
    public Respuesta<ApiAuthToken> refreshToken(String jwt, String refreshToken) {

        Respuesta<ApiAuthToken> respuesta = null;

        try {
        	ApiAuthLoginResponse response = apiAuthDAO.refreshToken(jwt, refreshToken);
        	ApiAuthToken apiAuthToken =  new ApiAuthToken();
        	apiAuthToken.setAccessToken(response.getAccess_token());
        	apiAuthToken.setRefreshToken(response.getRefresh_token());
        	respuesta = respuestaFactory.crearRespuestaOk(apiAuthToken);
        } catch (DAOException e) {
        	respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        	LOGGER.error(e.getMessage(), e);
        }
        return respuesta;
    }
    private String getToken(HttpServletRequest request) {
        return JwtToken.parseToken(request.getHeader(JwtToken.AUTHORIZATION_HEADER)).getToken();
    }
    
    @Override
    public Respuesta<Void> logout(HttpServletRequest request) {
        Respuesta<Void> respuesta = null;
        String jwt = getToken(request);
        try {
        	LOGGER.info("Clear user cache");
        	userCacheDAO.clearCache(jwt);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            apiAuthDAO.logout(jwt);
            respuesta = respuestaFactory.crearRespuestaOk(Void.class);
        } catch (DAOException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
            LOGGER.error(e.getMessage(), e);
        }
        
        return respuesta;
    }
    
    @Override
    public Respuesta<Void> updateUserCache(String authJWT, ResumenCliente resumenCliente) {
    	
        try {
			userCacheDAO.setCache(authJWT, resumenCliente);
		} catch (DAOException e) {
			LOGGER.error("Error al guardar resumen cliente en obp-api-users: ");
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
        return respuestaFactory.crearRespuestaOk(Void.class);
    }
    
    @Override
	public boolean isApiAuthClient(String dni) {
    	boolean isApiAuthClient = Boolean.FALSE;
    	List<String> documentos = apiAuthDAO.getApiAuthClients("apiauthclients.txt");
    	for (String nup : documentos) {
    		if (nup.trim().equals(dni.trim())) {
    			isApiAuthClient= Boolean.TRUE;
    			break;
    		}   
    	}
    	return isApiAuthClient;
	}
    
    
    @Override
	public Respuesta<Boolean> limpiarCacheApiAuth() {
    	apiAuthDAO.limpiarCacheApiAuth();
    	return respuestaFactory.crearRespuestaOk(true);
	}

}
