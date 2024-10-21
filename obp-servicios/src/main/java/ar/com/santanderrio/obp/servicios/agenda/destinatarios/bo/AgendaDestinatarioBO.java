/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;

/**
 * The Interface AgendaDestinatarioBO.
 */
public interface AgendaDestinatarioBO {

	/**
	 * Obtener agenda destinatarios.
	 *
	 * @param cliente
	 *            the cliente
	 * @param fromTransferencia
	 *            the from transferencia
	 * @return the respuesta
	 */
	Respuesta<AgendaDestinatarioDTO> obtenerAgendaDestinatarios(Cliente cliente, boolean fromTransferencia);

	/**
	 * Obtener destinatario DTO.
	 *
	 * @param destinatarioEntity
	 *            the destinatario entity
	 * @return the destinatario agenda DTO
	 */
	DestinatarioAgendaDTO obtenerDestinatarioDTO(DestinatarioEntity destinatarioEntity);

	/**
	 * Obtener agenda destinatario puntual.
	 *
	 * @param cliente the cliente
	 * @param transferenciaAgendadaDTO the transferencia agendada DTO
	 * @return the destinatario agenda DTO
	 */
    DestinatarioAgendaDTO obtenerAgendaDestinatarioPuntual(Cliente cliente, TransferenciaAgendadaDTO transferenciaAgendadaDTO);

	/**
	 * Ordena alfabeticamente los destinatarios.
	 *
	 * @param cliente the cliente
	 * @param transferenciaAgendadaDTO the transferencia agendada DTO
	 * @return the destinatario agenda DTO
	 */
	List<DestinatarioAgendaDTO> ordenarAlfabeticamente(List<DestinatarioAgendaDTO> destinatarios);

}