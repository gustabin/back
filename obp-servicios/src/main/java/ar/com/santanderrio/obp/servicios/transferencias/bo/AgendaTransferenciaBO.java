/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaEjecutadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackEliminarView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackStopDebitView;

/**
 * The Interface AgendaTransferenciaBO.
 *
 * @author B039637
 */
public interface AgendaTransferenciaBO {

	/**
	 * Obtener transferencias agendadas rio rio.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<List<TransferenciaAgendadaDTO>> obtenerTransferenciasAgendadasRioRio(Cliente cliente);

	/**
	 * Obtener transferencias agendadas otros bancos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<List<TransferenciaAgendadaDTO>> obtenerTransferenciasAgendadasOtrosBancos(Cliente cliente);

	/**
	 * Obtener recurrencia.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @return the frecuencia transferencia agendada
	 */
	FrecuenciaTransferenciaAgendada obtenerRecurrencia(TransferenciaAgendada transferenciaAgendada);

	/**
	 * Stop debit.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @return the respuesta
	 */
	Respuesta<FeedbackStopDebitView> stopDebit(Cliente cliente, TransferenciaAgendadaDTO transferenciaAgendadaDTO);

	/**
	 * Obtener datos cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @return the datos cliente
	 */
	DatosCliente obtenerDatosCliente(Cliente cliente, TransferenciaAgendadaDTO transferenciaAgendadaDTO);

	/**
	 * Eliminar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @return the respuesta
	 */
	Respuesta<FeedbackEliminarView> eliminarTransferencia(Cliente cliente,
			TransferenciaAgendadaDTO transferenciaAgendadaDTO);

	/**
	 * Ordenar lista por fecha.
	 *
	 * @param trasnferenciaAgendadaDTOList
	 *            the trasnferencia agendada DTO list
	 */
	void ordenarListaPorFecha(List<TransferenciaAgendadaDTO> trasnferenciaAgendadaDTOList);

	/**
	 * Ejecutar agendar transferencia.
	 *
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TransferenciaEjecutadaDTO> ejecutarTransferenciaAgendada(
			TransferenciaAgendadaDTO transferenciaAgendadaDTO, Cliente cliente);

	/**
	 * Ejecutar agendamiento recordatorio transferencia.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TransferenciaDTO> ejecutarAgendamientoRecordatorioTransferencia(TransferenciaDTO transferenciaDTO,
			Cliente cliente);

	/**
	 * Ejecutar agendamiento automatico transferencia.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TransferenciaDTO> ejecutarAgendamientoAutomaticoTransferencia(TransferenciaDTO transferenciaDTO,
			Cliente cliente);

	/**
	 * Ejecutar modificacion de transferencia agendada.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param tipoAgendamiento
	 *            the tipo agendamiento
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TransferenciaAgendadaDTO> ejecutarModificacionDeTransferenciaAgendada(
			TransferenciaAgendadaDTO transferenciaDTO, String tipoAgendamiento, Cliente cliente);

}
