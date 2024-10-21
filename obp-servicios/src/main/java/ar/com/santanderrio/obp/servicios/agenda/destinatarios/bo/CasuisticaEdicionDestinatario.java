/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;

/**
 * The Interface CasuisticaEdicionDestinatario.
 * 
 * @author dante.omar.olmedo
 */
public interface CasuisticaEdicionDestinatario {

	/**
	 * Crear error time out.
	 *
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> crearErrorTimeOut();

	/**
	 * Crear respuesta envío.
	 *
	 * @param outEntity
	 *            the out entity
	 * @param inEntity
	 *            the in entity
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaEdicion(AgendaDestinatarioOutEntity outEntity,
			AgendaDestinatarioInEntity inEntity);

}
