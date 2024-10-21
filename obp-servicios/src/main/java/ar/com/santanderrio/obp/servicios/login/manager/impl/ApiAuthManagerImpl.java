package ar.com.santanderrio.obp.servicios.login.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.login.bo.ApiAuthBO;
import ar.com.santanderrio.obp.servicios.login.manager.ApiAuthManager;

@Component
public class ApiAuthManagerImpl implements ApiAuthManager {
	
	
	@Autowired
	ApiAuthBO apiAuthBO;
	
	@Override
	public Respuesta<Boolean> limpiarCacheApiAuth() {
		return apiAuthBO.limpiarCacheApiAuth();
	}
}
