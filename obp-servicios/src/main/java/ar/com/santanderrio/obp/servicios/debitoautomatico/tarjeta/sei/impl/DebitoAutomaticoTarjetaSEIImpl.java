/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.manager.DebitoAutomaticoTarjetaManager;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.sei.DebitoAutomaticoTarjetaSEI;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ParametroAdhesionDebitoTarjetaInView;

/**
 * The Class DebitoAutomaticoTarjetaSEIImpl.
 *
 * @author florencia.n.martinez
 */
@Component("debitoAutomaticoTarjetaSEI")
public class DebitoAutomaticoTarjetaSEIImpl implements DebitoAutomaticoTarjetaSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitoAutomaticoTarjetaSEIImpl.class);

	/** The Constant POST_OK. */
	private static final String POST_OK = "Post OK: /{}.";

	/** The Constant REQUEST_FEEDBACK. */
	private static final String REQUEST_FEEDBACK = "adherir";

	/** The Constant REQUEST_COMPROBANTE. */
	private static final String REQUEST_COMPROBANTE = "verComprobante";

	/** The debito automaticotarjeta manager. */
	@Autowired
	private DebitoAutomaticoTarjetaManager debitoAutomaticotarjetaManager;

	/**
	 * Obtiene la confirmacion de la ahdesion al debito automatico en la tarjeta
	 * de credito.
	 *
	 * @param datosConfirmacionDebito
	 *            the datos confirmacion debito
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobanteDebitoAutomaticoTarjetaView> obtenerConfirmacionAhdesionDebitoTarjeta(
			ParametroAdhesionDebitoTarjetaInView datosConfirmacionDebito) {
		LOGGER.info(POST_OK, REQUEST_FEEDBACK);
		return debitoAutomaticotarjetaManager.obtenerAdhesionDebitoTarjeta(datosConfirmacionDebito);
	}

	/**
	 * Graba la estadistica del comprobante de la ahdesion al debito automatico
	 * en la tarjeta de credito.
	 *
	 * @return the boolean
	 */
	@Override
	public Boolean grabarEstadisticaComprobante() {
		LOGGER.info(POST_OK, REQUEST_COMPROBANTE);
		return debitoAutomaticotarjetaManager.grabarEstadisticaComprobante();
	}
}
