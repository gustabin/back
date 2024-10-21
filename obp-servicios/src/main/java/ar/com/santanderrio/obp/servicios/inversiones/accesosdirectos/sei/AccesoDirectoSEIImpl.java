package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.manager.AccesosDirectosManager;



@Component("accesoDirectoSEIImpl")
public class AccesoDirectoSEIImpl implements AccesoDirectoSEI{

	@Autowired
	private AccesosDirectosManager accesosDirectosManager;
 
	
	@Override
	public Respuesta<AccesoDirectoProductoEntity> obtenerAccesoDirecto() {
		
		
		Respuesta<AccesoDirectoProductoEntity> respuesta=accesosDirectosManager.obtenerAccesoDirecto();
		 
		return respuesta;
		
	}


}
