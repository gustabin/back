/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.AdhesionRespuestaView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Interface TodoPagoSEI.
 */
@Path("/botonDePago")
public interface TodoPagoSEI {

	/**
	 * Adhesion TodoPago.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return View asociado
	 * @throws DAOException
	 *             the DAO exception
	 */
	@POST
	@Path("/datosFormulario")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TodoPagoView> adhesionTodoPago(TodoPagoView viewRequest) throws DAOException;

	/**
	 * Confirmar adhesion.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	@POST
	@Path("/confirmarAdhesion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AdhesionRespuestaView> confirmarAdhesion(TodoPagoView viewRequest) throws DAOException;

	/**
	 * Descarga de comprobante de adhesion a TodoPago.
	 *
	 * @return View asociado
	 */
	@POST
	@Path("/descargaComprobanteAdhesion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargaComprobanteAdhesion();

}
