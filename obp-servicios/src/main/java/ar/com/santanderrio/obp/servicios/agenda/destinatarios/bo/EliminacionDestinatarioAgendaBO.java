/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Eliminacion de destinatario (agenda de destinatarios).
 *
 * @author federico.n.flores
 */
public interface EliminacionDestinatarioAgendaBO {

	/**
	 * Eliminacion destinatario.
	 *
	 * @param destinatario
	 *            the destinatario
	 * @param ip
	 *            the ip
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Void> eliminacionDestinatario(DestinatarioEntity destinatario, String ip, Cliente cliente);

}
