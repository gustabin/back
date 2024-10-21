package ar.com.santanderrio.obp.servicios.referidos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.referidos.view.DatosEstadisticasReferidosView;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;

/**
 * Endpoints para referidos
 * @author A309331
 *
 */

@Path("referidos/")
public interface ReferidosSEI {

	@POST
	@Path("/obtenerInicioReferidos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReferidosInicioResponseView> obtenerInicioReferidos();
	

    @POST
    @Path("/grabarEstadisticaReferidos")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    void grabarEstadisticaReferidos(DatosEstadisticasReferidosView referidosView);

	@POST
	@Path("/inicioReferidos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReferidosInicioResponseView> inicioReferidos();
    
}
