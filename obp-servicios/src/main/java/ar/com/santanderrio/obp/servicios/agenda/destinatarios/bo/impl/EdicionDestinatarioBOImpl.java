/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaEdicionDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.EdicionDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class EdicionDestinatarioBOImpl.
 */
@Component
public class EdicionDestinatarioBOImpl implements EdicionDestinatarioBO {

	/** The agenda destinatario DAO. */
	@Autowired
	private AgendaDestinatarioDAO agendaDestinatarioDAO;

	/** The casuistica. */
	@Autowired
	private CasuisticaEdicionDestinatario casuistica;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EdicionDestinatarioBOImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioDTO> editarDestinatario(DestinatarioEntity destinatario, String ip,
			Cliente cliente, ConfirmacionAltaDestinatarioView datos) {
		LOGGER.info("Entro al BO para editar Destinatario");
		try {
			AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity(cliente, destinatario, datos);
			AgendaDestinatarioOutEntity outEntity = agendaDestinatarioDAO.eliminarUAgregar(inEntity, ip,
					TipoOperacionACTAGEDESTEnum.MODIFICACION);

			return casuistica.crearRespuestaEdicion(outEntity, inEntity);
		} catch (DAOException e) {
			LOGGER.error("Error de servicio", e);
			return casuistica.crearErrorTimeOut();
		}
	}

}
