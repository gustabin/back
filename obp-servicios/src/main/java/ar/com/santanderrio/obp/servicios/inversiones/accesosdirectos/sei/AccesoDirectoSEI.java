package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;

@Path("/AccesosDirectosInversiones")
public interface AccesoDirectoSEI {
	
	@POST
    @Path("/obtenerAccesoDirecto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<AccesoDirectoProductoEntity> obtenerAccesoDirecto();
	
}
