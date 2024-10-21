/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionInView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraViewResponse;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;

/**
 * The Interface ChequeraSEI.
 */
@Path("/chequera")
public interface ChequeraSEI {

	/**
	 * realiza consultar en Chequera.
	 *
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ChequeraViewResponse> consultarChequera();

	/**
	 * realiza confirmar en Chequera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/confirmar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ChequeraConfirmacionViewResponse> confirmarChequera(ChequeraConfirmacionInView viewRequest);

	/**
	 * realiza reporte excel en Chequera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/comprobante")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobanteChequera(ChequeraConfirmacionViewResponse viewRequest);
}
