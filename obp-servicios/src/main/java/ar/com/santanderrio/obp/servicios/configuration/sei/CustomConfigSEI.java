package ar.com.santanderrio.obp.servicios.configuration.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.configuration.view.CustomConfigView;

/**
 * The Interface CustomConfigSEI.
 */
@Path("/customConfig")
public interface CustomConfigSEI {

    /**
     * Sets the.
     *
     * @param view the view
     * @return the respuesta
     */
	@POST
    @Path("/set")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> set(CustomConfigView view);

}
