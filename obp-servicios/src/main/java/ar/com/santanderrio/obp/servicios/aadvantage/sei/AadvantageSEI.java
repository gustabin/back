/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.ConsultaMillasAadvantageView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.GrabadoDeEstadisticaIngresoAadvantageInView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.SolicitudMillasAadvantageView;

/**
 * The Interface AadvantageSEI.
 */
@Path("/aadvantage")
public interface AadvantageSEI {

	/**
	 * Consultar millas.
	 *
	 * @param inView
	 *            the in view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerMillas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ConsultaMillasAadvantageView> consultarMillas(SolicitudMillasAadvantageView inView);
	
	/**
	 * Grabar estadistica desde menu.
	 */
	@POST
	@Path("/grabarEstadisticaIngresoAadvantage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	void grabarEstadisticaDesdeMenu(GrabadoDeEstadisticaIngresoAadvantageInView request);
	
}
