package ar.com.santanderrio.obp.servicios.referidos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

public interface ReferidosBO {

	Respuesta<String> obtenerTokenReferidos(String nup) throws Exception;

	boolean tieneNupEnListado(); 
	
}
