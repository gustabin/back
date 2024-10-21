package ar.com.santanderrio.obp.servicios.login.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

public interface ApiAuthManager {

	
	Respuesta<Boolean> limpiarCacheApiAuth();
	
}
