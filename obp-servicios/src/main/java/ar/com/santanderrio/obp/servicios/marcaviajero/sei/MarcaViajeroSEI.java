/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ConfirmarNuevoViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.EliminarViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.MarcaViajeroView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.NuevoViajeView;

/**
 * The Interface MarcaViajeroSEI.
 */
@Path("/marcaViajero")
public interface MarcaViajeroSEI {

	/**
	 * Obtener viajes.
	 *
	 * @return the respuesta
	 */

	@POST
	@Path("/obtenerViajes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<MarcaViajeroView> obtenerViajes();

	/**
	 * Nuevo viaje.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/nuevoViaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<NuevoViajeView> nuevoViaje();

	/**
	 * Nuevo viaje.
	 *
	 * @param nuevoViaje
	 *            the nuevo viaje
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarViaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> confirmarViaje(ConfirmarNuevoViajeView nuevoViaje);

    /**
     * Eliminar viaje.
     *
     * @param eliminarViaje
     *            the eliminar viaje
     * @return the respuesta
     */
    @POST
    @Path("/eliminarViaje")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<Void> eliminarViaje(EliminarViajeView eliminarViaje);

}
