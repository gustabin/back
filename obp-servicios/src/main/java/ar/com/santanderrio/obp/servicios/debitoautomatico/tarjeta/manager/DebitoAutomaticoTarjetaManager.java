/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ParametroAdhesionDebitoTarjetaInView;

/**
 * The Interface DebitoAutomaticoTarjetaManager.
 *
 * @author florencia.n.martinez
 */
public interface DebitoAutomaticoTarjetaManager {

	/**
	 * Obtiene la confirmacion de la ahdesion al debito automatico en la tarjeta
	 * de credito.
	 *
	 * @param datosConfirmacionDebito
	 *            the datos confirmacion debito
	 * @return the respuesta
	 */
	Respuesta<ComprobanteDebitoAutomaticoTarjetaView> obtenerAdhesionDebitoTarjeta(
			ParametroAdhesionDebitoTarjetaInView datosConfirmacionDebito);

	/**
	 * Graba la estadistica del comprobante de la ahdesion al debito automatico
	 * en la tarjeta de credito.
	 *
	 * @return the boolean
	 */
	Boolean grabarEstadisticaComprobante();
}
