/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Transferencia con Alias desde la agenda de destinatatios o con un nuevo
 * destinatario.
 * 
 * @author Manuel.Vargas B041299
 * @version 1
 */
public interface TransferenciaPorAliasBO {

	/**
	 * Consultar Datos de Titularidad. invoca y gestiona resultado de la
	 * invocacion metodo de consulta de totularidad.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param cliente
	 *            the cliente
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	Respuesta<TransferenciaDTO> consultarDatosTitularidadExtendida(TransferenciaView transferenciaView, Cliente cliente,
			String userAgent);
}
