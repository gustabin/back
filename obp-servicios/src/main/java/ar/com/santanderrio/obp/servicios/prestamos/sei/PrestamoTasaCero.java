/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ConfirmacionPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DatosPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoTasaCeroView;


@Path("/prestamos")
public interface PrestamoTasaCero {

	@POST
	@Path("/inicioPrestamosTasaCero")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DatosPrestamoTasaCeroView> inicioPrestamosTasaCero();
	
	
	@POST
	@Path("/solicitarPrestamosTasaCero")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobantePrestamoTasaCeroView> solicitarPrestamosTasaCero(ConfirmacionPrestamoTasaCeroView view);

}
