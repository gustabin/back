/**
 * 
 */
package ar.com.santanderrio.obp.servicios.status.sei;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.status.view.StatusView;

/**
 * The Interface StatusSEI.
 *
 * @author sergio.e.goldentair
 */
@Path("/health")
public interface StatusSEI {

	/**
	 * Obtener un resumen con el estado de la app. -Version deployada. -Fecha
	 * del build del paquete. -Conexion con la base ok. -Consumir servicio iatx
	 * dummy. -Validar que funcione BouncyCastle para las firmas digitales.
	 *
	 * @param mc the mc
	 *
	 * @return the status
	 */
	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<StatusView> getStatus(@Context MessageContext mc);
}
