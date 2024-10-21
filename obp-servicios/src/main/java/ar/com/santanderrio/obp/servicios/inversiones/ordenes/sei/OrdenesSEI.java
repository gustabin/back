/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;

/**
 * The Interface OrdenesSEI.
 *
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 */
@Path("/ordenes")
public interface OrdenesSEI {

	/**
	 * Iniciar ordenes operaciones. Carga la vista con los datos de inicio de la
	 * pantalla de busquedas y operaciones de inversiones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/iniciarOrdenesOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<OrdenesView> iniciarOrdenesOperaciones();

	/**
	 * Buscar ordenes operaciones por numero de cuenta.
	 *
	 * @param cuentaView
	 *            the cuenta view
	 * @return the respuesta
	 */
	@POST
	@Path("/buscarOrdenesOperacionesPorCuenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<OrdenesView> buscarOrdenesOperacionesPorCuenta(CuentaView cuentaView);

	/**
	 * Buscar ordenes operaciones por filtro.
	 *
	 * @param filtrosOrdenesView
	 *            the filtros ordenes view
	 * @return the respuesta
	 */
	@POST
	@Path("/buscarOrdenesOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<OrdenesView> buscarOrdenesOperaciones(FiltrosOrdenesView filtrosOrdenesView);

	/**
	 * Obtener filtros de busqueda.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerFiltrosDeBusqueda")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FiltrosOrdenesView> obtenerFiltrosDeBusqueda();

	/**
	 * Graba estadistica de Busqueda.
	 */
	@POST
	@Path("/grabarEstadisticaBusqueda")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaBusqueda();

}
