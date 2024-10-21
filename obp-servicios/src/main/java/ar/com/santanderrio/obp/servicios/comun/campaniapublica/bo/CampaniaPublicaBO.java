package ar.com.santanderrio.obp.servicios.comun.campaniapublica.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;

public interface CampaniaPublicaBO {

	List<OfertaComercialDTO> armarCampaniasPublicas();
	
	Respuesta<Boolean> limpiarCampaniasPublicas();
}
