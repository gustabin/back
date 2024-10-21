/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debin.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;

/**
 * The Interface DebinSEI.
 *
 * @author sergio.e.goldentair
 */
@Path("/debin")
public interface DebinSEI {
    
    /**
	 * Verificar que una vez validado el segundo factor de autenticación, se
	 * redirija al sitio de PRISMA.
	 *
	 * @param debinView
	 *            the debin view
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    @POST
    @Path("/prepararSalto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<DebinView> saltoDebin(DebinView debinView, @Context MessageContext mc);

    /**
	 * Verificar que en caso de que el cliente no tenga asociados token, tarjeta
	 * de coordenadas ni tarjeta de débito en los casos que sea requerido, se
	 * visualice una pantalla con un ícono y un mensaje recuperado del código
	 * 1575 de la tabla de mensajes y errores.
	 *
	 * @return the respuesta
	 */
    @POST
    @Path("/iniciarFlujo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> habilitado();
}
