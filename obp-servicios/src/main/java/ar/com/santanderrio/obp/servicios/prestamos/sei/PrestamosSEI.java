package ar.com.santanderrio.obp.servicios.prestamos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.LiquidacionPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoOutView;

/**
 * 
 *
 */
@Path("/prestamos")
public interface PrestamosSEI {

	/**
	 * Permite solicitar un préstamo personal a partir de una solicitud validando el
	 * estado del cliente en RSA.
	 * 
	 * @param solicitudPrestamoInView Datos de la solicitud del préstamo
	 * @return respuesta con el objeto que contiene la información del prestamo
	 *         solicitado y su estado
	 */
	@POST
	@Path("/solicitar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SolicitudPrestamoOutView> solicitar(SolicitudPrestamoInView solicitudPrestamoInView);

	/**
	 * Permite liquidar un préstamo personal validando el estado del cliente en RSA.
	 * 
	 * @param liquidacionPrestamoInView Datos para la liquidacion del prestamo
	 * @return respuesta con el objeto que contiene la información del prestamo
	 *         liquidado y su estado
	 */
	@POST
	@Path("/liquidar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SolicitudPrestamoOutView> liquidar(LiquidacionPrestamoInView liquidacionPrestamoInView);

	/**
	 * Permite pagar la cuota de un préstamo.
	 * 
	 * @param pagoCuotaView Datos para pagar la cuota
	 * @return respuesta con el objeto que contiene la información del pago de la
	 *         cuota del prestamo
	 */
	@POST
	@Path("/{numeroPrestamo}/cuotas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoCuotaOutView> pagar(@PathParam("numeroPrestamo") String numeroPrestamo,
			PagoCuotaInView pagoCuotaView);

	/**
	 * Permite cancelar el préstamo de manera anticipada.
	 * 
	 * @param pagoCuotaView Datos para pagar la cuota
	 * @return respuesta con el objeto que contiene la información del pago de la
	 *         cuota del prestamo
	 */
	@POST
	@Path("/{numeroPrestamo}/cancelacion-anticipada")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CancelacionAnticipadaOutView> cancelacionAnticipada(@PathParam("numeroPrestamo") String numeroPrestamo,
			CancelacionAnticipadaInView cancelacionAnticipadaInView);
}
