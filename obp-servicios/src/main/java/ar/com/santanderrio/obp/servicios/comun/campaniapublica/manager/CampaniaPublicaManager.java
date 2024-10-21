package ar.com.santanderrio.obp.servicios.comun.campaniapublica.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

public interface CampaniaPublicaManager {

	Respuesta<Boolean> limpiarCampaniasPublicas();
	
}
