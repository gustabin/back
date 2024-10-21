/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chances.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceView;

/**
 * The Interface ChancesSEI.
 */
@Path("/chances")
public interface ChancesSEI {
	
	/**
	 * obtener Chance del Mes seleccionado.
	 *
	 * @param ChanceView
	 *            the ChanceView
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerChancePorMes")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ChanceView> obtenerChancesMes(ChanceView chanceView);
}
