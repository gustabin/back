package ar.com.santanderrio.obp.servicios.comun.campaniapublica.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.campaniapublica.bo.CampaniaPublicaBO;

@Component
public class CampaniaPublicaManagerImpl implements CampaniaPublicaManager{

	@Autowired
	private CampaniaPublicaBO campaniaPublicaBO;
	
	@Override
	public Respuesta<Boolean> limpiarCampaniasPublicas() {
		return campaniaPublicaBO.limpiarCampaniasPublicas();
	}

	
}
