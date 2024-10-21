package ar.com.santanderrio.obp.servicios.login.bo;

import javax.servlet.http.HttpServletRequest;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthToken;

public interface ApiAuthBO {

    Respuesta<ApiAuthToken> refreshToken(String jwt, String refreshToken);
    
    Respuesta<Void> logout(HttpServletRequest request);

	Respuesta<Void> updateUserCache(String authJWT, ResumenCliente resumenCliente);
	
	boolean isApiAuthClient(String dni);
	
	Respuesta<Boolean> limpiarCacheApiAuth();

}