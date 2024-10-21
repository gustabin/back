/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface EdicionDestinatarioBO.
 */
public interface EdicionDestinatarioBO {

	/**
	 * Editar destinatario.
	 *
	 * @param destinatario
	 *            the destinatario
	 * @param ip
	 *            the ip
	 * @param cliente
	 *            the cliente
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> editarDestinatario(DestinatarioEntity destinatario, String ip,
			Cliente cliente, ConfirmacionAltaDestinatarioView datos);

}
