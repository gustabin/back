package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;

public interface AccesosDirectosManager {
	
	Respuesta<AccesoDirectoProductoEntity> obtenerAccesoDirecto();


}
