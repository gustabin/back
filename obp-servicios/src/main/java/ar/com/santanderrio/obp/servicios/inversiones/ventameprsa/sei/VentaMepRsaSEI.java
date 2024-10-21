/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.MepRsaNotification;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.VentaMepRequest;

/**
 * The Interface AnalisisCarteraSEI.
 */
@Path("/mepRsa")
public interface VentaMepRsaSEI {
	
	
	@POST
	@Path("/notificarVenta")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<MepRsaNotification> notificarVtaMepRsa(VentaMepRequest request);
		
}
