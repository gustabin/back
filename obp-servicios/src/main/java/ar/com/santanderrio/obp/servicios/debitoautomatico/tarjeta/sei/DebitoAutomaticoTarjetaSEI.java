/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ParametroAdhesionDebitoTarjetaInView;

/**
 * The Interface DebitoAutomaticoTarjetaSEI.
 *
 * @author florencia.n.martinez
 */
@Path("/debitoAutomaticoTarjeta")
public interface DebitoAutomaticoTarjetaSEI {

	/**
	 * Obtiene la confirmacion de la ahdesion al debito automatico en la tarjeta
	 * de credito.
	 *
	 * @param datosConfirmacionDebito
	 *            the datos confirmacion debito
	 * @return the respuesta
	 */
	@POST
	@Path("/adherir")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteDebitoAutomaticoTarjetaView> obtenerConfirmacionAhdesionDebitoTarjeta(
			ParametroAdhesionDebitoTarjetaInView datosConfirmacionDebito);

	/**
	 * Graba la estadistica del comprobante de la ahdesion al debito automatico
	 * en la tarjeta de credito.
	 *
	 * @return the boolean
	 */
	@POST
	@Path("/verComprobante")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Boolean grabarEstadisticaComprobante();
}
