/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prelogin.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prelogin.web.view.PreLoginView;

/**
 * Obtener la RSA publica para que front pueda aplicar la nueva libreria de js
 * para los puntos en que el usuario carga sus datos.
 * 
 * @author sergio.e.goldentair
 *
 */
@Path("/inicial")
public interface PreLoginSEI {

    /**
	 * Operacion invocada al ingresar al sitio para obtener RSA publica.
	 *
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    @POST
    @Path("/doInit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<String> init(@Context MessageContext mc);

    /**
     * Pre login.
     *
     * @param datoEntrada the dato entrada
     * @return the respuesta
     */
    @POST
    @Path("/preLogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
    Respuesta<PreLoginView> preLogin(@FormParam("datoEntrada") String datoEntrada);

}
