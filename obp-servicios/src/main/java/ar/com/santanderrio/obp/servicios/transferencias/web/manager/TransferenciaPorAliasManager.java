/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Interface TransferenciaPorAliasManager.
 *
 * @author emilio.watemberg
 */
public interface TransferenciaPorAliasManager {

	/**
	 * Solicitar nueva transferencia.
	 *
	 * @author emilio.watemberg
	 * @param transferencia
	 *            the transferencia
	 * @param agent
	 *            the agent
	 * @return the respuesta
	 */
	Respuesta<TransferenciaView> solicitarNuevaTransferencia(TransferenciaView transferencia, String agent);

}
