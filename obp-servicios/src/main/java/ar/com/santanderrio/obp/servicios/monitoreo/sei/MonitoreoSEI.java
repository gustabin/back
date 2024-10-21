/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Api para validar que esten disponibles que los servicios IATX, BD, etc...
 * 
 * @author sergio.e.goldentair
 *
 */
@Path("/monitoreo")
public interface MonitoreoSEI {
    /**
     * Poder conocer si el servidor esta levantado, pedido schualle.
     *
     * @return the response
     */
    @GET
    @Path("/monitor")
    @Consumes(MediaType.TEXT_HTML)
    Response monitor();
}
